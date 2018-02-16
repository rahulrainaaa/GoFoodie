package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.CartOrderActivity;
import com.app.gofoodie.adapter.listviewadapter.CartListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.cart.Cart;
import com.app.gofoodie.model.cart.CartResponse;
import com.app.gofoodie.model.cart.Description;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.SessionUtils;
import com.app.gofoodie.utility.VibrationUtil;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.shawnlin.numberpicker.NumberPicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * {@link BaseFragment} Fragment class to handle Cart UI screen.
 */
@SuppressWarnings("unused")
public class CartFragment extends BaseFragment implements NetworkCallbackListener {

    private final String TAG = "CartFragment";

    private TextView mTxtLabel = null;
    private CartItemClickListener mCartItemClickListener = null;
    /**
     * Class private data member(s).
     */
    private ListView mListView = null;
    private ArrayList<Cart> mCartList = null;
    private float taxPercent = 0f;

    /**
     * {@link BaseFragment} Fragment callback method(s).
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_cart, container, false);

        // Reset global flag to show cart.
        GlobalData.ShowCart = false;
        mCartItemClickListener = new CartItemClickListener();
        mListView = view.findViewById(R.id.list_view_cart_items);
        mTxtLabel = view.findViewById(R.id.txt_label);
        Button btnProceed = view.findViewById(R.id.btn_proceed);
//        btnProceed.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.icon_cart_proceed), null);
        btnProceed.setOnClickListener(view1 -> {

            if (mCartList == null) {

                Toast.makeText(getDashboardActivity(), "Cart is Empty.", Toast.LENGTH_SHORT).show();
                return;
            } else if (mCartList.size() == 0) {

                Toast.makeText(getDashboardActivity(), "Cart is Empty.", Toast.LENGTH_SHORT).show();
                return;
            }

            GlobalData.cartArrayList = mCartList;
            Intent intent = new Intent(getActivity(), CartOrderActivity.class);
            intent.putExtra("taxPercent", taxPercent);
            startActivity(intent);
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshCartList();
    }

    @Override
    public void fragQuitCallback() {

    }

    /**
     * Method to refresh the cart list by sending the http request.
     */
    private void refreshCartList() {

        String customerId = SessionUtils.getInstance().getSession().getData().getCustomerId();
        String url = Network.URL_GET_CART + customerId;

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, getDashboardActivity(), this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    /**
     * {@link NetworkCallbackListener} http response listener callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {     // Get all cart items.

            handleViewCartResponse(rawObject);
        } else if (requestCode == 2) { // Http response for cart item delete and update.

            refreshCartList();
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "HTTP Fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to handle the view cart response from http web service.
     *
     * @param json http {@link JSONObject} response.
     */
    private void handleViewCartResponse(JSONObject json) {

        VibrationUtil.getInstance().vibrate(getActivity());
        ModelParser parser = new ModelParser();
        CartResponse cartResponse = (CartResponse) parser.getModel(json.toString(), CartResponse.class, null);
        if (cartResponse.getStatusCode() != 200) {

            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(cartResponse.getStatusMessage())
                    .show();
            cartResponse.setCart(new ArrayList<>());
            mTxtLabel.setText("Cart");

        } else {

            // Parse the response on success and also parse the taxPercent and
            taxPercent = cartResponse.getTaxPerc();
            CustomerProfileHandler.CUSTOMER.setCartCount(cartResponse.getCart().size());
            float totalPayablePrice = cartResponse.getTotalPrice();
            mTxtLabel.setText("Total Price: " + cartResponse.getTotalPrice() + " AED");// + ",  Price: AED " + cartResponse.totalPrice.toString());
        }
        mCartList = (ArrayList<Cart>) cartResponse.getCart();
        CartListViewAdapter mAdapter = new CartListViewAdapter(getActivity(), mCartItemClickListener, R.layout.item_listview_cart, mCartList);
        mListView.setAdapter(mAdapter);
    }

    /**
     * Method to handle the delete button clicked.
     *
     * @param view reference
     */
    private void deleteClicked(View view) {

        VibrationUtil.getInstance().vibrate(getActivity());
        final Cart cart = (Cart) view.getTag();

        Snackbar.make(view, "Are you sure ?", Snackbar.LENGTH_LONG).setAction("Remove", view1 -> {

            try {

                JSONObject jsonRequest = new JSONObject();

                jsonRequest.put("customer_id", getSession().getData().getCustomerId());
                jsonRequest.put("login_id", getSession().getData().getLoginId());
                jsonRequest.put("cart_item_id", cart.getCartItemId());
                jsonRequest.put("token", getSession().getData().getToken());
                NetworkHandler networkHandler = new NetworkHandler();
                networkHandler.httpCreate(1, getDashboardActivity(), CartFragment.this, jsonRequest, Network.URL_CART_ITEM_REMOVE, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                networkHandler.executePost();

            } catch (JSONException jsonExc) {

                jsonExc.printStackTrace();
                Toast.makeText(getActivity(), "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    /**
     * Method to handle the Qty button clicked.
     *
     * @param view reference
     */
    private void qtySelected(final View view) {

        final NumberPicker numberPicker = (NumberPicker) getActivity().getLayoutInflater().inflate(R.layout.layout_number_picker, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Quantity");
        builder.setView(numberPicker)
                .setCancelable(true)
                .setPositiveButton("Update Qty", (dialog, id) -> {

                    int qtySelected = numberPicker.getValue();
                    Cart cart = (Cart) view.getTag();
                    int oldQty = Integer.valueOf(cart.getQuantity().trim());

                    if (oldQty == qtySelected) {

                        Log.d(TAG, "Same quantity picked for cart item.");
                        return;
                    }

                    JSONObject jsonRequest = new JSONObject();

                    try {

                        JSONArray jsonArrayItems = new JSONArray();

                        for (Description description : cart.getDescription()) {

                            JSONArray jsonArrayOptions = new JSONArray(description.getOptions());
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("options", jsonArrayOptions);
                            jsonObject.put("name", description.getValue());
                            jsonObject.put("value", description.getValue());
                            jsonObject.put("item_id", description.getItemId());
                            jsonArrayItems.put(jsonObject);
                        }

                        jsonRequest.put("description", jsonArrayItems);
                        jsonRequest.put("customer_id", getSession().getData().getCustomerId());
                        jsonRequest.put("login_id", getSession().getData().getLoginId());
                        jsonRequest.put("combo_id", cart.getComboId());
                        jsonRequest.put("cart_item_id", cart.getCartItemId());
                        jsonRequest.put("branch_id", cart.getBranchId());
                        jsonRequest.put("quantity", "" + qtySelected);
                        jsonRequest.put("token", getSession().getData().getToken());
                        jsonRequest.put("area", CustomerProfileHandler.CUSTOMER.getProfile().getArea().trim());

                        NetworkHandler networkHandler = new NetworkHandler();
                        networkHandler.httpCreate(1, getDashboardActivity(), CartFragment.this, jsonRequest, Network.URL_UPDATE_CART_ITEM, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                        networkHandler.executePost();

                    } catch (JSONException jsonExc) {

                        jsonExc.printStackTrace();
                        Toast.makeText(getActivity(), "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Method to handle the combo image view clicked. Show the combo description.
     *
     * @param view reference
     */
    private void imgComboClicked(View view) {

        Cart cart = (Cart) view.getTag();

    }

    /**
     * Sub-Class to handle the click events of cart item(s).
     */
    public class CartItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.ibtn_delete:

                    deleteClicked(view);
                    break;
                case R.id.btn_qty:

                    qtySelected(view);
                    break;
                case R.id.img_combo:

                    imgComboClicked(view);
                    break;
            }
        }
    }
}
