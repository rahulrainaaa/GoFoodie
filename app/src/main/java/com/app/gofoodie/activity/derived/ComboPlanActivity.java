package com.app.gofoodie.activity.derived;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.gridViewAdapter.ComboPlanGridAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.comboPlan.ComboItem;
import com.app.gofoodie.model.comboPlan.ComboPlanResponse;
import com.app.gofoodie.model.comboPlan.Comboplan;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @method ComboPlanActivity
 * @desc {@link BaseAppCompatActivity} Activity class to show the Restaurant's ComboPlan(s) with filtering applied.
 */
public class ComboPlanActivity extends BaseAppCompatActivity implements NetworkCallbackListener, View.OnClickListener {

    public static final String TAG = "ComboPlanActivity";

    /**
     * Class private data members.
     */
    private GridView mComboGridView = null;
    private ComboPlanGridAdapter mAdapter = null;
    private ArrayList<Comboplan> mComboPlanList = null;

    /**
     * {@link BaseAppCompatActivity} Activity callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_plan);
        mComboGridView = (GridView) findViewById(R.id.combo_plan_grid_layout);

        refreshComboList(null);
    }

    /**
     * @param search search sub string of combo name.
     * @desc Method to http request to get all the combo plans.
     * @method refreshComboList
     */
    private void refreshComboList(String search) {

        String branchId = getIntent().getStringExtra("branch_id");
        String url = Network.URL_GET_BRANCH_COMBOS + branchId;
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();

    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {         // Fetched combo plan(s).

            handleComboPlanResponse(rawObject);
        } else if (requestCode == 2) {          // combo added to cart.

            handleAddToCart(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http Fail: " + message, Toast.LENGTH_SHORT).show();

    }


    /**
     * @param json
     * @desc Method to handle the combo plan response from http web API.
     * @method handleComboPlanResponse
     */
    private void handleComboPlanResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        ComboPlanResponse comboPlanResponse = (ComboPlanResponse) parser.getModel(json.toString(), ComboPlanResponse.class, null);
        mComboPlanList = (ArrayList<Comboplan>) comboPlanResponse.comboplan;

        mAdapter = new ComboPlanGridAdapter(this, this, R.layout.item_gridview_combo_plan, mComboPlanList);
        mComboGridView.setAdapter(mAdapter);

    }

    /**
     * @param json
     * @desc Method to do handle the add cart http response.
     * @method handleAddToCart
     */
    private void handleAddToCart(JSONObject json) {

        try {

            String statusMessage = json.getString("statusMessage");
            Toast.makeText(this, "" + statusMessage, Toast.LENGTH_SHORT).show();

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            SweetAlertDialog sad = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
            sad.setContentText(jsonExc.getMessage());
            sad.setTitleText("Error");
        }

    }

    /**
     * {@link android.view.View.OnClickListener} click event listener callback method(s).
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ibtn_cart:

                addToCartClicked(view);
                break;
            case R.id.image_combo:

                showComboDescription(view);
                break;
        }

    }

    /**
     * @param view
     * @method addToCartClicked
     * @desc Method handling logic on add to cart button clicked on a cell.
     */
    private void addToCartClicked(View view) {

        Comboplan comboplan = (Comboplan) view.getTag();
        String url = Network.URL_ADD_TO_CART;
        try {

            JSONArray jsonArrayItems = new JSONArray();

            Iterator<ComboItem> comboplanIterator = comboplan.comboItems.iterator();

            while (comboplanIterator.hasNext()) {

                ComboItem item = comboplanIterator.next();

                JSONArray jsonArrOptionList = new JSONArray(item.options);

                JSONObject jsonItem = new JSONObject();
                jsonItem.put("item_id", item.comboItemId);
                jsonItem.put("name", item.itemName);
                jsonItem.put("value", item.options.get(0));
                jsonItem.put("options", jsonArrOptionList);

                jsonArrayItems.put(jsonItem);
            }

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("customer_id", getSessionData().getCustomerId());
            jsonRequest.put("login_id", getSessionData().getLoginId());
            jsonRequest.put("branch_id", comboplan.branchId);
            jsonRequest.put("combo_id", comboplan.comboId);
            jsonRequest.put("quantity", "1");
            jsonRequest.put("token", getSessionData().getToken());
            jsonRequest.put("description", jsonArrayItems);

            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(2, this, this, jsonRequest, url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    public void showComboDescription(View v) {

        Comboplan comboplan = (Comboplan) v.getTag();
        View view = getLayoutInflater().inflate(R.layout.dialog_combo_details, null);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(comboplan.comboName);
        alertDialog.setView(view);
        ((RatingBar) view.findViewById(R.id.rating_bar)).setRating(Float.parseFloat(comboplan.avgRating.trim()));
        ((TextView) view.findViewById(R.id.type_n_cuisine)).setText(comboplan.cuisineName);
        ((TextView) view.findViewById(R.id.desc)).setText(comboplan.description);
        ((TextView) view.findViewById(R.id.txt_price)).setText("AED " + comboplan.price);
        if (comboplan.type.trim().toLowerCase().equals("nonveg")) {

            view.findViewById(R.id.img_veg).setVisibility(View.GONE);
            view.findViewById(R.id.img_nonveg).setVisibility(View.VISIBLE);
        } else {

            view.findViewById(R.id.img_veg).setVisibility(View.VISIBLE);
            view.findViewById(R.id.img_nonveg).setVisibility(View.GONE);
        }
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
