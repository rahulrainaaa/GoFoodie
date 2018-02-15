package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.gridViewAdapter.ComboPlanGridAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.comboPlan.ComboOption;
import com.app.gofoodie.model.comboPlan.ComboPlanResponse;
import com.app.gofoodie.model.comboPlan.Comboplan;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.CacheUtils;
import com.app.gofoodie.utility.VibrationUtil;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private boolean flagRefreshed = false;
    private Button btnViewCart = null;
    private TextView txtCartItems = null;

    /**
     * {@link BaseAppCompatActivity} Activity callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_plan);
        mComboGridView = findViewById(R.id.combo_plan_grid_layout);

        btnViewCart = findViewById(R.id.btn_view_cart);
        btnViewCart.setOnClickListener(this);
        txtCartItems = findViewById(R.id.txt_cart_items);
        txtCartItems.setText("Cart Items: " + CustomerProfileHandler.CUSTOMER.getCartCount());


        String cuisinePref = CacheUtils.getInstance().getPref(this, CacheUtils.PREF_NAME.PREF_MEAL).getString(CacheUtils.PREF_MEAL_CUISINE_KEY, "");
        String typePref = CacheUtils.getInstance().getPref(this, CacheUtils.PREF_NAME.PREF_MEAL).getString(CacheUtils.PREF_MEAL_TYPE_KEY, "");

        if (cuisinePref.trim().isEmpty() || typePref.trim().isEmpty()) {

            startActivity(new Intent(this, MealPreferenceActivity.class));
            Toast.makeText(this, "Select your preference", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!flagRefreshed) {

            flagRefreshed = true;
            refreshComboList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_combo_plans, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_filter) {

            flagRefreshed = false;
            startActivity(new Intent(this, MealPreferenceActivity.class));
        }

        return true;
    }

    /**
     * @desc Method to http request to get all the combo plans.
     * @method refreshComboList
     */
    private void refreshComboList() {

        String cuisinePref = CacheUtils.getInstance().getPref(this, CacheUtils.PREF_NAME.PREF_MEAL).getString(CacheUtils.PREF_MEAL_CUISINE_KEY, "");
        String typePref = CacheUtils.getInstance().getPref(this, CacheUtils.PREF_NAME.PREF_MEAL).getString(CacheUtils.PREF_MEAL_TYPE_KEY, "");

        String mealPreference = "&cuisine=" + cuisinePref + "&combo_type=" + typePref;

        String branchId = getIntent().getStringExtra("branch_id");
        String url = Network.URL_GET_BRANCH_COMBOS + branchId + mealPreference.trim();
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

        if (comboPlanResponse.getStatusCode() != 200) {

            if (mComboPlanList != null) {
                mComboPlanList.clear();
                mAdapter.notifyDataSetChanged();
            }

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(comboPlanResponse.getStatusMessage())
                    .setConfirmClickListener(sweetAlertDialog -> {

                        sweetAlertDialog.dismissWithAnimation();
                    })
                    .show();

        } else {

            mComboPlanList = (ArrayList<Comboplan>) comboPlanResponse.getComboplans();
            mAdapter = new ComboPlanGridAdapter(this, this, R.layout.item_gridview_combo_plan, mComboPlanList);
            mComboGridView.setAdapter(mAdapter);
        }
    }

    /**
     * @param json
     * @desc Method to do handle the add cart http response.
     * @method handleAddToCart
     */
    private void handleAddToCart(JSONObject json) {

        try {

            VibrationUtil.getInstance().vibrate(this);
            int statusCode = json.getInt("statusCode");

            if (statusCode == 200) {

                CustomerProfileHandler.CUSTOMER.setCartCount(CustomerProfileHandler.CUSTOMER.getCartCount() + 1);
                txtCartItems.setText("Cart Items: " + CustomerProfileHandler.CUSTOMER.getCartCount());

                Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();

            } else if (statusCode == 406) {

                Toast.makeText(this, "Already present in cart", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, json.getString("statusMessage"), Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();

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
            case R.id.btn_view_cart:

                btnViewCartClicked(view);
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

            for (ComboOption item : comboplan.getComboOptions()) {

                JSONArray jsonArrOptionList = new JSONArray(item.getOptions());

                JSONObject jsonItem = new JSONObject();
                jsonItem.put("item_id", item.getComboItemId());
                jsonItem.put("name", item.getName());
                jsonItem.put("value", item.getOptions().get(0));
                jsonItem.put("options", jsonArrOptionList);

                jsonArrayItems.put(jsonItem);
            }

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("customer_id", getSessionData().getCustomerId());
            jsonRequest.put("login_id", getSessionData().getLoginId());
            jsonRequest.put("branch_id", comboplan.getBranchId());
            jsonRequest.put("combo_id", comboplan.getComboId());
            jsonRequest.put("quantity", "1");
            jsonRequest.put("token", getSessionData().getToken());
            jsonRequest.put("description", jsonArrayItems);
            jsonRequest.put("area", CustomerProfileHandler.CUSTOMER.getProfile().getArea().trim());

            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(2, this, this, jsonRequest, url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Method to close this activity and start cart fragment activity.
     *
     * @param view
     */
    private void btnViewCartClicked(View view) {

        GlobalData.ShowCart = true;
        finish();
    }

    /**
     * Method to show the combo description.
     *
     * @param view
     */
    private void showComboDescription(View view) {

        Comboplan comboplan = (Comboplan) view.getTag();
        Intent intent = new Intent(this, ComboDescriptionActivity.class);
        intent.putExtra("combo_id", comboplan.getComboId().trim());
        startActivity(intent);
    }
}
