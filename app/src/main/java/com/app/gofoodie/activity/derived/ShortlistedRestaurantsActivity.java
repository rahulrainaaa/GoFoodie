package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
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

        mListView = (ListView) findViewById(R.id.list_view);

        String url = Network.URL_GET_SLR + "1";// getSessionData().getCustomerId();
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_meal_pref, menu);
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
     * {@link NetworkCallbackListener} http response callback listener.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        Toast.makeText(this, "Http Success: " + rawObject.toString(), Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {         // Fetched all shortlisted restaurant(s).

            handleShortlistRestaurantResponse(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param json
     * @method handleShortlistRestaurantResponse
     * @desc Method to handle the shortlisted restaurants response from API.
     */
    private void handleShortlistRestaurantResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        ShortlistedRestaurants shortlistedRestaurants = (ShortlistedRestaurants) parser.getModel(json.toString(), ShortlistedRestaurants.class, null);
        if (shortlistedRestaurants.statusCode == 200) {
            mList = (ArrayList<Shortlisted>) shortlistedRestaurants.shortlisted;
            mAdapter = new ShortlistedRestaurantListViewAdapter(this, R.layout.item_shortlisted_restaurants, mList, this);
            mListView.setAdapter(mAdapter);
        } else {
            Toast.makeText(this, "" + shortlistedRestaurants.statusMessage, Toast.LENGTH_SHORT).show();
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

        Toast.makeText(this, "showProfile", Toast.LENGTH_SHORT).show();
    }

    /**
     * @param view {@link android.widget.ImageButton} clicked reference.
     * @method removeShortlistedRestaurant
     * @desc Method to remove this particular restaurant
     */
    private void removeShortlistedRestaurant(View view) {

        Toast.makeText(this, "removeShortlistedRestaurant", Toast.LENGTH_SHORT).show();
    }
}
