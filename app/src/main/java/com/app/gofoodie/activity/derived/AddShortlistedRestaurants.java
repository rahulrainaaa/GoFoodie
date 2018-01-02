package com.app.gofoodie.activity.derived;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.RestaurantListViewAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.restaurant.Restaurant;
import com.app.gofoodie.model.restaurant.RestaurantResponse;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.CacheUtils;
import com.app.gofoodie.utility.LocationUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class AddShortlistedRestaurants
 * @desc Activity class to show near by restaurant branches and adding them as shortlisted restaurants.
 */
public class AddShortlistedRestaurants extends BaseAppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, NetworkCallbackListener {

    public static final String TAG = "AddShortlistedRestaurants";

    /**
     * Class private data members.
     */
    private ListView mListView = null;
    private RestaurantListViewAdapter mAdapter = null;
    private ArrayList<Restaurant> mList = null;

    /**
     * {@link AddShortlistedRestaurants} activity callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shortlisted_restaurants);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        fetchRestaurants(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_shortlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_item_search:

                menuSearch();
                break;

            case R.id.menu_item_location:

                startActivity(new Intent(this, LocationActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * @method menuSearch
     * @desc Method to handle on search menu item selected.
     */
    private void menuSearch() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setSingleLine();
        input.setSingleLine(true);
        builder.setView(input);

        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String search = input.getText().toString();

                fetchRestaurants(search.trim());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    /**
     * {@link android.widget.AdapterView.OnItemClickListener} list view item click listener callback method(s).
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Restaurant item = mList.get(position);

        Toast.makeText(this, position + " item selected", Toast.LENGTH_SHORT).show();
    }

    /**
     * {@link android.view.View.OnClickListener} View click listener event callback method(s).
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ibtn_remove:

                addToShortlist(view);
                break;

            case R.id.ibtn_view:

                showProfile(view);
                break;
        }
    }

    /**
     * @method fetchRestaurants
     * @desc Method to fetch restaurants from API with filter/parameter applied.
     */
    private void fetchRestaurants(String search) {

        /**
         * Fetch the location preferences.
         */
        String location_id = LocationUtils.getInstance().getLocationId(this, "");
        String location_name = LocationUtils.getInstance().getLocationName(this, "");

        if (location_id.trim().isEmpty()) {

            Toast.makeText(this, "Please select the Location Preference.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LocationActivity.class));
            return;
        }

        /**
         * Fetch the cuisine preference.
         */
//        String meal_cat_pref = CacheUtils.getInstance().getPref(this, CacheUtils.PREF_NAME.PREF_MEAL).getString(CacheUtils.PREF_MEAL_CAT_KEY, " ");
//        String meal_type_pref = CacheUtils.getInstance().getPref(this, CacheUtils.PREF_NAME.PREF_MEAL).getString(CacheUtils.PREF_MEAL_TYPE_KEY, " ");

        /**
         * Append parameters to url string.
         */
        String url = Network.URL_GET_RESTAURANT + "?areas=" + location_id;

//        if (!meal_cat_pref.trim().isEmpty()) {
//
//            url = url + "&category=" + meal_cat_pref;
//        }
//
//        if (!meal_type_pref.trim().isEmpty()) {
//
//            url = url + "&type=" + meal_type_pref;
//        }

        if (search != null) {

            url = url + "&keyword=" + search;
        }

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    /**
     * @param json
     * @method handleRestaurantResponse
     * @desc Method to handle the http response of {@link com.app.gofoodie.model.restaurant.RestaurantResponse} and publish in ListView.
     */
    private void handleRestaurantResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        RestaurantResponse restaurantResponse = (RestaurantResponse) parser.getModel(json.toString(), RestaurantResponse.class, null);
        int statusCode = restaurantResponse.statusCode;
        String statusMessage = restaurantResponse.statusMessage;

        if (statusCode == 200) {

            publishRestaurantList((ArrayList<Restaurant>) restaurantResponse.restaurant);
        } else {

            try {

                mList.clear();
                mAdapter.notifyDataSetChanged();
                
            } catch (Exception e) {

                e.printStackTrace();
            }

            Toast.makeText(this, "" + statusMessage.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param restaurants
     * @method publishRestaurantList
     * @desc Method to public restaurant list in the UI ListView.
     */
    private void publishRestaurantList(ArrayList<Restaurant> restaurants) {

        mList = restaurants;
        mAdapter = new RestaurantListViewAdapter(this, R.layout.item_shortlist_restaurant, mList, this);
        mListView.setAdapter(mAdapter);

    }

    /**
     * @param view {@link android.widget.ImageButton} clicked reference.
     * @method showProfile
     * @desc Method to show the complete profile description.
     */
    private void showProfile(View view) {

        Restaurant restaurant = (Restaurant) view.getTag();
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        intent.putExtra("data", restaurant);
        intent.putExtra("mode", RestaurantProfileActivity.MODE.REST_BRANCH);
        startActivity(intent);
    }

    /**
     * @param view {@link android.widget.ImageButton} clicked reference.
     * @method addToShortlist
     * @desc Method to remove this particular restaurant
     */
    private void addToShortlist(View view) {

        final Restaurant restaurant = (Restaurant) view.getTag();
        Snackbar.make(view, "Add as Shortlisted Restaurant ?", Snackbar.LENGTH_LONG).setAction("Add", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    JSONObject json = new JSONObject();

                    json.put("customer_id", getSessionData().getCustomerId());
                    json.put("branch_id", restaurant.branchId);
                    json.put("login_id", getSessionData().getLoginId());
                    json.put("token", getSessionData().getToken());

                    NetworkHandler networkHandler = new NetworkHandler();
                    networkHandler.httpCreate(2, AddShortlistedRestaurants.this, AddShortlistedRestaurants.this, json, Network.URL_ADD_AS_SHORT_REST, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                    networkHandler.executePost();

                } catch (JSONException jsonExc) {

                    jsonExc.printStackTrace();
                    Toast.makeText(AddShortlistedRestaurants.this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).show();
    }

    /**
     * @param json
     * @method handleRestaurantResponse
     * @desc Method to handle the response of restaurant add to customer's shortlist.
     */
    private void handleRestaurantAdded(JSONObject json) {

        try {
            int statusCode = json.getInt("statusCode");
            String statusMessage = json.getString("statusMessage");
            Toast.makeText(this, statusCode + ". " + statusMessage, Toast.LENGTH_SHORT).show();

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {

            handleRestaurantResponse(rawObject);
        } else if (requestCode == 2) {

            handleRestaurantAdded(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http Fail: " + message, Toast.LENGTH_SHORT).show();
    }
}
