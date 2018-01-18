package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.restaurantBranch.RestaurantBranch;
import com.app.gofoodie.model.restaurantBranch.RestaurantBranchResponse;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.ProfileUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class RestaurantBranchProfileActivity extends BaseAppCompatActivity implements View.OnClickListener {

    public static final String TAG = "RestaurantBranchProfileActivity";

    /**
     * Class private data member(s).
     */
    private TextView Name = null;
    private TextView ReviewCount = null;
    private TextView Cuizine = null;
    private TextView Address = null;
    private TextView Postal = null;
    private TextView Description = null;
    private TextView AboutUs = null;

    private RatingBar mRatingBar = null;

    private ImageView Veg = null;
    private ImageView NonVeg = null;
    private ImageView Profile = null;

    private ImageButton Call = null;
    private ImageButton Email = null;
    private ImageButton Map = null;
    private ImageButton Review = null;

    private RestaurantBranch restaurant = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_branch_pofile);

        Name = (TextView) findViewById(R.id.txt_name);
        ReviewCount = (TextView) findViewById(R.id.txt_rate_count);
        Cuizine = (TextView) findViewById(R.id.txt_cuisine);
        Address = (TextView) findViewById(R.id.txt_address);
        Postal = (TextView) findViewById(R.id.txt_postal_code);
        Description = (TextView) findViewById(R.id.txt_description);
        AboutUs = (TextView) findViewById(R.id.txt_about_us);

        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);

        Veg = (ImageView) findViewById(R.id.img_veg);
        NonVeg = (ImageView) findViewById(R.id.img_nonveg);
        Profile = (ImageView) findViewById(R.id.img_profile);

        Call = (ImageButton) findViewById(R.id.btn_call);
        Email = (ImageButton) findViewById(R.id.btn_email);
        Map = (ImageButton) findViewById(R.id.btn_map);
        Review = (ImageButton) findViewById(R.id.btn_rate);

        fetchBranchDetails();
    }

    private void fetchBranchDetails() {

        String branch_id = getIntent().getStringExtra("branch_id");
        String url = Network.URL_GET_BRANCH_DETAILS + branch_id;
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, new NetworkCallbackListener() {
            @Override
            public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

                showRestaurantBranchProfile(rawObject);
            }

            @Override
            public void networkFailResponse(int requestCode, String message) {

                Toast.makeText(RestaurantBranchProfileActivity.this, "Http Fail: " + message, Toast.LENGTH_SHORT).show();
            }
        }, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);

        networkHandler.executeGet();


    }

    /**
     * {@link android.view.View.OnClickListener} click event callback method(s).
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.btn_call:

                callClicked(view);
                break;
            case R.id.btn_email:

                emailClicked(view);
                break;
            case R.id.btn_map:

                mapClicked(view);
                break;
            case R.id.btn_rate:

                reviewClicked(view);
                break;
        }
    }


    /**
     * @method showRestaurantBranchProfile
     * @desc Method to show the Restaurant Branch profile of selected Restaurant (which is not shortlisted restaurant).
     */
    public void showRestaurantBranchProfile(JSONObject json) {

        ModelParser parser = new ModelParser();
        RestaurantBranchResponse restaurantBranchResponse = (RestaurantBranchResponse) parser.getModel(json.toString(), RestaurantBranchResponse.class, null);

        if (restaurantBranchResponse.statusCode != 200) {

            Toast.makeText(this, "" + restaurantBranchResponse.statusMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        restaurant = restaurantBranchResponse.restaurantBranch;

        Name.setText(restaurant.branchName);
        ReviewCount.setText("(" + restaurant.countRating + ")");
        Cuizine.setText(restaurant.tags);
        Address.setText(restaurant.branchAddress);
        Postal.setText(restaurant.branchPostalCode);
        Description.setText(restaurant.description);
        AboutUs.setText(restaurant.aboutUs);
        mRatingBar.setRating(Float.parseFloat(restaurant.avgRating.trim()));

        /**
         * Check they type of restaurant branch.
         */
        if (restaurant.type.trim().toLowerCase().equals("1")) {     // veg = 1

            Veg.setVisibility(View.VISIBLE);
            NonVeg.setVisibility(View.GONE);
        } else if (restaurant.type.toLowerCase().equals("2")) {     // nonveg = 2

            Veg.setVisibility(View.GONE);
            NonVeg.setVisibility(View.VISIBLE);
        } else {                                                    // else = any (both).

            Veg.setVisibility(View.VISIBLE);
            NonVeg.setVisibility(View.VISIBLE);
        }

        try {

            Picasso.with(this).load(restaurant.profileIcon.trim()).into(Profile);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed loading profile image", Toast.LENGTH_SHORT).show();
        }

        Call.setOnClickListener(this);
        Email.setOnClickListener(this);
        Map.setOnClickListener(this);
        Review.setOnClickListener(this);
    }

    /**
     * @param view
     * @desc Method to handle the call.
     * @method callClicked
     */
    private void callClicked(View view) {

        ProfileUtils.call(this, Constants.ADMIN_PHONE_NUMBER);
    }

    /**
     * @param view
     * @desc Method to handle logic on email click.
     * @method emailClicked
     */
    private void emailClicked(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{
                Constants.ADMIN_EMAIL
        });
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(intent);
        } catch (Exception exc) {

            Log.e("TAG", exc.getMessage());
            Toast.makeText(this, "EXCEPTION: " + exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param view
     * @method mapClicked
     * @desc Method to handle logic on map clicked.
     */
    private void mapClicked(View view) {

        String coordinates = restaurant.geoLat.trim() + "," + restaurant.geoLng.trim();
        Toast.makeText(this, "" + coordinates, Toast.LENGTH_SHORT).show();

        if (coordinates == null) {

            Snackbar.make(view, "Coordinates not present.", Snackbar.LENGTH_SHORT).show();

        } else if (coordinates.trim().isEmpty()) {

            Snackbar.make(view, "Coordinates not present.", Snackbar.LENGTH_SHORT).show();

        } else {

            ProfileUtils.mapLocation(this, coordinates);
        }
    }

    /**
     * @param view
     * @method reviewClicked
     * @desc Method to handle login on reviews click.
     */
    private void reviewClicked(View view) {

        Intent intent = new Intent(this, RatingActivity.class);
        intent.putExtra("branch_id", restaurant.branchId.trim());
        startActivity(intent);
    }
}
