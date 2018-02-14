package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.gridViewAdapter.ComboPlanGridAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.comboPlan.ComboPlanResponse;
import com.app.gofoodie.model.comboPlan.Comboplan;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Activity class to show combo plans of a particular branch wrt branch_id.
 * Filters are also applied.
 * No add to cart is applied in this case.
 */
public class RestaurantComboActivity extends BaseAppCompatActivity implements NetworkCallbackListener {

    public static final String TAG = "RestaurantComboActivity";

    /**
     * Class private data members.
     */
    private GridView mComboGridView = null;
    private ComboPlanGridAdapter mAdapter = null;
    private ArrayList<Comboplan> mComboPlanList = null;
    private boolean flagRefreshed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_restaurant_combo);

        mComboGridView = findViewById(R.id.combo_plan_grid_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!flagRefreshed) {

            flagRefreshed = true;
            refreshComboList();
        }
    }

    /**
     * @desc Method to http request to get all the combo plans.
     * @method refreshComboList
     */
    private void refreshComboList() {

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
                    .setContentText("No combo plans")
                    .setConfirmClickListener(sweetAlertDialog -> finish())
                    .show();

        } else {

            mComboPlanList = (ArrayList<Comboplan>) comboPlanResponse.getComboplans();
            mAdapter = new ComboPlanGridAdapter(this, null, R.layout.item_gridview_branch_combo_plan, mComboPlanList);
            mComboGridView.setAdapter(mAdapter);
        }
    }
}
