package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.ShortlistedRestaurantListViewAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.shortlisted.Shortlisted;
import com.app.gofoodie.model.shortlisted.ShortlistedRestaurants;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class ShortlistedRestaurantsActivity
 * @desc Activity class for showing the shortlisted restaurants (branch) to see and remove.
 */
public class ShortlistedRestaurantsActivity extends BaseAppCompatActivity implements NetworkCallbackListener, View.OnClickListener {

    public static final String TAG = "ShortlistedRestaurantsActivity";

    /**
     * Class private data member(s).
     */
    private ListView mListView = null;
    private ArrayList<Shortlisted> mList = null;
    private ShortlistedRestaurantListViewAdapter mAdapter = null;

    /**
     * {@link BaseAppCompatActivity} activity callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortlisted_restaurants);

        mListView = findViewById(R.id.list_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shortlist_restaurant_frag, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new:

                startActivity(new Intent(this, AddShortlistedRestaurants.class));
                break;
        }
        return true;
    }

    /**
     * @method refreshList
     * @desc Method to refresh the list of shortlisted restaurant(s).
     */
    private void refreshList() {
        String url = Network.URL_GET_SLR + getSession().getData().getCustomerId();
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    /**
     * {@link NetworkCallbackListener} http response callback listener.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {         // Fetched all shortlisted restaurant(s).

            handleShortlistRestaurantResponse(rawObject);
        } else if (requestCode == 2) {

            refreshList();
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to handle the shortlisted restaurants response from API.
     *
     * @param json reference
     */
    private void handleShortlistRestaurantResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        ShortlistedRestaurants shortlistedRestaurants = (ShortlistedRestaurants) parser.getModel(json.toString(), ShortlistedRestaurants.class, null);
        if (shortlistedRestaurants.statusCode == 200) {
            mList = (ArrayList<Shortlisted>) shortlistedRestaurants.shortlisted;
            mAdapter = new ShortlistedRestaurantListViewAdapter(this, R.layout.item_shortlisted_restaurants, mList, this);
            mListView.setAdapter(mAdapter);

        } else {

            try {
                mList.clear();
                mAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SweetAlertDialog s = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("No shortlisted restaurant");
            s.setCancelable(false);
            s.show();

        }

    }

    /**
     * {@link android.view.View.OnClickListener} click listener callback method(s).
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ibtn_remove:

                removeShortlistedRestaurant(view);
                break;

            case R.id.ibtn_view:

                showProfile(view);
                break;
        }
    }

    /**
     * @param view {@link android.widget.ImageButton} clicked reference.
     * @method showProfile
     * @desc Method to show the complete profile description.
     */
    private void showProfile(View view) {

        Shortlisted shortlisted = (Shortlisted) view.getTag();
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        intent.putExtra("mode", RestaurantProfileActivity.MODE.SHORTLISTED);
        intent.putExtra("data", shortlisted);
        startActivity(intent);
    }

    /**
     * @param view {@link android.widget.ImageButton} clicked reference.
     * @method removeShortlistedRestaurant
     * @desc Method to remove this particular restaurant
     */
    private void removeShortlistedRestaurant(View view) {

        final Shortlisted shortlisted = (Shortlisted) view.getTag();

        Snackbar.make(view, "Remove Restaurant ?", Snackbar.LENGTH_LONG).setAction("Remove", view1 -> {

            try {

                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("customer_id", getSessionData().getCustomerId());
                jsonRequest.put("login_id", getSessionData().getLoginId());
                jsonRequest.put("branch_id", shortlisted.branchId);
                jsonRequest.put("token", getSessionData().getToken());
                NetworkHandler networkHandler = new NetworkHandler();
                networkHandler.httpCreate(2, ShortlistedRestaurantsActivity.this, ShortlistedRestaurantsActivity.this, jsonRequest, Network.URL_REM_SR, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                networkHandler.executePost();
            } catch (JSONException jsonExc) {

                jsonExc.printStackTrace();
                Toast.makeText(ShortlistedRestaurantsActivity.this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).show();

    }
}
