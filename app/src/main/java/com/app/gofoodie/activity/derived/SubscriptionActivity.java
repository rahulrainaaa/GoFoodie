package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.RechargePlanListViewAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.rechargePlan.RechargePlan;
import com.app.gofoodie.model.rechargePlan.Subscriptionplan;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class SubscriptionActivity
 * @desc Activity class to show Subscription/Recharge plan list.
 */
@SuppressWarnings({"unused", "ConstantConditions"})
public class SubscriptionActivity extends BaseAppCompatActivity implements NetworkCallbackListener, AdapterView.OnItemClickListener {

    public static final String TAG = "SubscriptionActivity";

    /**
     * Class private data member(s).
     */
    private ListView mListView = null;
    private ArrayList<String> mList = new ArrayList<>();
    private RechargePlan mRechargePlan = null;

    /**
     * {@link BaseAppCompatActivity} Activity callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        mListView = findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);


        boolean flagSubscription = CustomerProfileHandler.CUSTOMER.getProfile().getValidUpto().trim().isEmpty();

        // flagSubscription = true [only subscription plans needed], false = [all plans needed]
        String url = Network.URL_GET_RECHARGE_PLANS + (flagSubscription ? "" : "rechargePlan");

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
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
     * Method to fetch the recharge plans from web API.
     *
     * @param json reference
     */
    private void fetchedPlans(JSONObject json) {

        ModelParser modelParser = new ModelParser();
        RechargePlan rechargePlan = (RechargePlan) modelParser.getModel(json.toString(), RechargePlan.class, null);
        mRechargePlan = rechargePlan;
        RechargePlanListViewAdapter mAdapter = new RechargePlanListViewAdapter(this, R.layout.item_list_recharge_plan, (ArrayList<Subscriptionplan>) rechargePlan.subscriptionplan);
        mListView.setAdapter(mAdapter);
    }

    /**
     * {@link android.widget.AdapterView.OnItemClickListener} list item select event callback method.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        GlobalData.subscriptionplan = mRechargePlan.subscriptionplan.get(position);
        startActivity(new Intent(this, PaymentGatewayActivity.class));
        finish();
    }
}
