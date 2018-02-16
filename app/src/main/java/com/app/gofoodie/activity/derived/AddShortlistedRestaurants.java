package com.app.gofoodie.activity.derived;

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
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.restaurant.Restaurant;
import com.app.gofoodie.model.restaurant.RestaurantResponse;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.LocationUtils;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Activity class to show near by restaurant branches and adding them as shortlisted restaurants.
 */
@SuppressWarnings("unused")
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

        mListView = findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        GlobalData.applyLocationPref = false;
    }

    @Override
    protected void onResume() {
        super.onResume();


        fetchRestaurants(null, GlobalData.applyLocationPref);
        GlobalData.applyLocationPref = false;
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
     * Method to handle on search menu item selected.
     */
    private void menuSearch() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setSingleLine();
        input.setSingleLine(false);
        builder.setView(input);

        builder.setPositiveButton("Search", (dialog, which) -> {

            String search = input.getText().toString();
            fetchRestaurants(search.trim(), false);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.setNeutralButton("Reset", (dialog, which) -> fetchRestaurants(null, false));

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
     * Method to fetch restaurants from API with filter/parameter applied.
     */
    private void fetchRestaurants(String search, boolean applyLocation) {

        //Fetch the location preferences.
        String location_id = LocationUtils.getInstance().getLocationId(this, CustomerProfileHandler.CUSTOMER.getProfile().getArea().trim());
        String location_name = LocationUtils.getInstance().getLocationName(this, CustomerProfileHandler.CUSTOMER.getProfile().getName().trim());

        if (location_id.trim().isEmpty()) {

            Toast.makeText(this, "Please select the Location Preference.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LocationActivity.class));
            return;
        }

        String url = Network.URL_GET_RESTAURANT;

        // Check if location filter has to be applied.
        // else, Append keyword parameter to url string.
        if (applyLocation) {

            url = url + "?areas=" + location_id;

        } else if (search != null) {

            url = url + "?keyword=" + search;
        }

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    /**
     * Method to handle the http response of {@link com.app.gofoodie.model.restaurant.RestaurantResponse} and publish in ListView.
     *
     * @param json reference
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

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("No such restaurant found.")
                    .setConfirmClickListener(SweetAlertDialog::dismissWithAnimation)
                    .show();
        }
    }

    /**
     * Method to public restaurant list in the UI ListView.
     *
     * @param restaurants reference
     */
    private void publishRestaurantList(ArrayList<Restaurant> restaurants) {

        mList = restaurants;
        mAdapter = new RestaurantListViewAdapter(this, R.layout.item_shortlist_restaurant, mList, this);
        mListView.setAdapter(mAdapter);

    }

    /**
     * Method to show the complete profile description.
     *
     * @param view {@link android.widget.ImageButton} clicked reference.
     */
    private void showProfile(View view) {

        Restaurant restaurant = (Restaurant) view.getTag();
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        intent.putExtra("data", restaurant);
        intent.putExtra("mode", RestaurantProfileActivity.MODE.REST_BRANCH);
        startActivity(intent);
    }

    /**
     * Method to remove this particular restaurant
     *
     * @param view {@link android.widget.ImageButton} clicked view reference.
     */
    private void addToShortlist(View view) {

        final Restaurant restaurant = (Restaurant) view.getTag();
        Snackbar.make(view, "Add as Shortlisted Restaurant ?", Snackbar.LENGTH_LONG).setAction("Add", view1 -> {

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
        }).show();
    }

    /**
     * Method to handle the response of restaurant add to customer's shortlist.
     *
     * @param json reference
     */
    private void handleRestaurantAdded(JSONObject json) {

        try {
            int statusCode = json.getInt("statusCode");
            if (statusCode == 200) {

                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();

            } else {

                String statusMessage = json.getString("statusMessage");
                Toast.makeText(this, "" + statusMessage, Toast.LENGTH_SHORT).show();
            }


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

        if (requestCode == 1) {     // fetched all restaurants

            handleRestaurantResponse(rawObject);

        } else if (requestCode == 2) {      // http response - added restaurant(s).

            handleRestaurantAdded(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http Fail: " + message, Toast.LENGTH_SHORT).show();
    }
}
