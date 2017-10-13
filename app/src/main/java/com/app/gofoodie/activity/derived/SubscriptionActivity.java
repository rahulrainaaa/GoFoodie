package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.RechargePlanListViewAdapter;
import com.app.gofoodie.customview.WeekSelectDialog;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.RechargePlan.RechargePlan;
import com.app.gofoodie.model.RechargePlan.Subscriptionplan;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubscriptionActivity extends BaseAppCompatActivity implements NetworkCallbackListener, AdapterView.OnItemClickListener {

    private ListView mListView = null;
    private RechargePlanListViewAdapter mAdapter = null;
    private ArrayList<String> mList = new ArrayList<>();
    private RechargePlan mRechargePlan = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);

        WeekSelectDialog weekSelectDialog = new WeekSelectDialog(this);
        weekSelectDialog.show();

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), Network.URL_GET_RECHARGE_PLANS, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();


    }

    /**
     * {@link NetworkCallbackListener} http response callback method.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {

            fetchedPlans(rawObject);
        }

    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param json
     * @method fetchedPlans
     * @desc Method to fetch the recharge plans from web API.
     */
    private void fetchedPlans(JSONObject json) {

        ModelParser modelParser = new ModelParser();
        RechargePlan rechargePlan = (RechargePlan) modelParser.getModel(json.toString(), RechargePlan.class, null);
        mRechargePlan = rechargePlan;
        mAdapter = new RechargePlanListViewAdapter(this, R.layout.item_list_recharge_plan, (ArrayList<Subscriptionplan>) rechargePlan.subscriptionplan);
        mListView.setAdapter(mAdapter);
    }

    /**
     * {@link android.widget.AdapterView.OnItemClickListener} list item select event callback method.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Subscriptionplan plan = mRechargePlan.subscriptionplan.get(position);
        Toast.makeText(this, "Please do the payment", Toast.LENGTH_SHORT).show();

    }
}
