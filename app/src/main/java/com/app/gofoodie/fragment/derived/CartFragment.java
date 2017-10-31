package com.app.gofoodie.fragment.derived;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.app.gofoodie.model.cart.Cart;
import com.app.gofoodie.model.cart.CartResponse;
import com.app.gofoodie.model.cart.Description;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.SessionUtils;
import com.shawnlin.numberpicker.NumberPicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @class CartFragment
 * @desc {@link BaseFragment} Fragment class to handle Cart UI screen.
 */
public class CartFragment extends BaseFragment implements NetworkCallbackListener {

    public final String TAG = "CartFragment";

    /**
     * Class private data member(s).
     */
    private ListView mListView = null;
    private CartListViewAdapter mAdapter = null;
    private ArrayList<Cart> mCartList = null;
    public CartItemClickListener mCartItemClickListener = null;
    public TextView mTxtLabel = null;
    private ImageButton mIBtnProceed = null;

    /**
     * {@link BaseFragment} Fragment callback method(s).
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_cart, container, false);
        mCartItemClickListener = new CartItemClickListener();
        mListView = (ListView) view.findViewById(R.id.list_view_cart_items);
        mTxtLabel = (TextView) view.findViewById(R.id.txt_label);
        mIBtnProceed = (ImageButton) view.findViewById(R.id.ibtn_proceed);
        mIBtnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mCartList == null) {

                    Toast.makeText(getDashboardActivity(), "Cart is Empty.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mCartList.size() == 0) {

                    Toast.makeText(getDashboardActivity(), "Cart is Empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                GlobalData.cartArrayList = mCartList;
                Intent intent = new Intent(getActivity(), CartOrderActivity.class);
                startActivity(intent);
            }
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
     * @method refreshCartList
     * @desc Method to refresh the cart list by sending the http request.
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

        Toast.makeText(getActivity(), "HTTP Success: " + rawObject, Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {     // Get cart items.

            handleViewCartResponse(rawObject);
        } else if (requestCode == 2) {

        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "HTTP Fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param json http {@link JSONObject} response.
     * @method handleViewCartResponse
     * @desc Method to handle the view cart response from http web service.
     */
    private void handleViewCartResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        CartResponse cartResponse = (CartResponse) parser.getModel(json.toString(), CartResponse.class, null);
        if (cartResponse.statusCode != 200) {

            Toast.makeText(getActivity(), "" + cartResponse.statusMessage, Toast.LENGTH_SHORT).show();
            cartResponse.cart = new ArrayList<Cart>();
        }
        mCartList = (ArrayList<Cart>) cartResponse.cart;
        mAdapter = new CartListViewAdapter(getActivity(), mCartItemClickListener, R.layout.item_listview_cart, mCartList);
        mListView.setAdapter(mAdapter);
    }

    /**
     * @param view
     * @method deleteClicked
     * @desc Method to handle the delete button clicked.
     */
    private void deleteClicked(View view) {

        Cart cart = (Cart) view.getTag();

        try {

            JSONObject jsonRequest = new JSONObject();
            JSONArray jsonArrayItems = new JSONArray();
            Iterator<Description> descriptionIterator = cart.description.iterator();
            while (descriptionIterator.hasNext()) {

                Description description = descriptionIterator.next();
                JSONArray jsonArrayOptions = new JSONArray(description.options);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("options", jsonArrayOptions);
                jsonObject.put("name", description.value);
                jsonObject.put("value", description.value);
                jsonObject.put("item_id", description.itemId);
                jsonArrayItems.put(jsonObject);
            }

            jsonRequest.put("description", jsonArrayItems);
            jsonRequest.put("customer_id", getSession().getData().getCustomerId());
            jsonRequest.put("login_id", getSession().getData().getLoginId());
            jsonRequest.put("combo_id", cart.comboId);
            jsonRequest.put("token", getSession().getData().getToken());
            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, getDashboardActivity(), this, jsonRequest, Network.URL_CART_ITEM_REMOVE, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(getActivity(), "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param view
     * @method qtySelected
     * @desc Method to handle the Qty button clicked.
     */
    private void qtySelected(final View view) {


        final NumberPicker numberPicker = (NumberPicker) getActivity().getLayoutInflater().inflate(R.layout.layout_number_picker, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Quantity");
        builder.setView(numberPicker)
                .setCancelable(true)
                .setPositiveButton("Update Qty", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        int qtySelected = numberPicker.getValue();
                        Cart cart = (Cart) view.getTag();
                        JSONObject jsonRequest = new JSONObject();

                        try {

                            JSONArray jsonArrayItems = new JSONArray();

                            Iterator<Description> descriptionIterator = cart.description.iterator();
                            while (descriptionIterator.hasNext()) {

                                Description description = descriptionIterator.next();
                                JSONArray jsonArrayOptions = new JSONArray(description.options);
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("options", jsonArrayOptions);
                                jsonObject.put("name", description.value);
                                jsonObject.put("value", description.value);
                                jsonObject.put("item_id", description.itemId);
                                jsonArrayItems.put(jsonObject);
                            }

                            jsonRequest.put("description", jsonArrayItems);
                            jsonRequest.put("customer_id", getSession().getData().getCustomerId());
                            jsonRequest.put("login_id", getSession().getData().getLoginId());
                            jsonRequest.put("combo_id", cart.comboId);
                            jsonRequest.put("branch_id", "1");
                            jsonRequest.put("quantity", "" + qtySelected);
                            jsonRequest.put("token", getSession().getData().getToken());

                            NetworkHandler networkHandler = new NetworkHandler();
                            networkHandler.httpCreate(1, getDashboardActivity(), CartFragment.this, jsonRequest, Network.URL_UPDATE_CART_ITEM, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                            networkHandler.executePost();

                        } catch (JSONException jsonExc) {

                            jsonExc.printStackTrace();
                            Toast.makeText(getActivity(), "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();


    }

    /**
     * @method updateQuantity
     * @desc Method to update Quantity of selected item in cart.
     */
    private void updateQuantity(final View view) {


    }

    /**
     * @param view
     * @method imgComboClicked
     * @desc Method to handle the combo image view clicked. Show the combo descriprion.
     */
    private void imgComboClicked(View view) {

        Cart cart = (Cart) view.getTag();


    }

    /**
     * @class CartItemClickListener
     * @desc Sub-Class to handle the click events of cart item(s).
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
