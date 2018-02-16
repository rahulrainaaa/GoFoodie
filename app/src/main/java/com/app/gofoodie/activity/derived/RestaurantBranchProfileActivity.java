package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Activity class to show the description info of a single restaurant branch with given branch_id.
 */
public class RestaurantBranchProfileActivity extends BaseAppCompatActivity implements View.OnClickListener {

    public static final String TAG = "RestaurantBranchProfileActivity";

    /**
     * Class private data member(s).
     */
    private TextView Name = null;
    private TextView ReviewCount = null;
    private TextView Cuisine = null;
    private TextView Address = null;
    private TextView Postal = null;
    private TextView Description = null;
    private TextView AboutUs = null;

    private MaterialRatingBar mRatingBar = null;

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

        Name = findViewById(R.id.txt_name);
        ReviewCount = findViewById(R.id.txt_rate_count);
        Cuisine = findViewById(R.id.txt_cuisine);
        Address = findViewById(R.id.txt_address);
        Postal = findViewById(R.id.txt_postal_code);
        Description = findViewById(R.id.txt_description);
        AboutUs = findViewById(R.id.txt_about_us);

        mRatingBar = findViewById(R.id.rating_bar);

        Veg = findViewById(R.id.img_veg);
        NonVeg = findViewById(R.id.img_nonveg);
        Profile = findViewById(R.id.img_profile);

        Call = findViewById(R.id.btn_call);
        Email = findViewById(R.id.btn_email);
        Map = findViewById(R.id.btn_map);
        Review = findViewById(R.id.btn_rate);

        fetchBranchDetails();
    }

    /**
     * Method to handle the http response packet of restaurant branch info and publish it on UI.
     */
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

                new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("No Internet")
                        .setConfirmClickListener(sweetAlertDialog -> finish())
                        .show();
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
    private void showRestaurantBranchProfile(JSONObject json) {

        ModelParser parser = new ModelParser();
        RestaurantBranchResponse restaurantBranchResponse = (RestaurantBranchResponse) parser.getModel(json.toString(), RestaurantBranchResponse.class, null);

        if (restaurantBranchResponse.getStatusCode() != 200) {

            Toast.makeText(this, "" + restaurantBranchResponse.getStatusMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        restaurant = restaurantBranchResponse.getRestaurantBranch();

        Name.setText(restaurant.getBranchName());
        ReviewCount.setText("(" + restaurant.getCountRating() + ")");
        Cuisine.setText(restaurant.getTags());
        Address.setText(restaurant.getBranchAddress());
        Postal.setText(restaurant.getBranchPostalCode());
        Description.setText(restaurant.getDescription());
        AboutUs.setText(restaurant.getAboutUs().getAboutus());
        mRatingBar.setRating(Float.parseFloat(restaurant.getAvgRating()));

        // Check they type of restaurant branch.
        if (restaurant.getType().trim().toLowerCase().equals("1")) {     // veg = 1

            Veg.setVisibility(View.VISIBLE);
            NonVeg.setVisibility(View.GONE);
        } else if (restaurant.getType().toLowerCase().equals("2")) {     // nonveg = 2

            Veg.setVisibility(View.GONE);
            NonVeg.setVisibility(View.VISIBLE);
        } else {                                                    // else = any (both).

            Veg.setVisibility(View.VISIBLE);
            NonVeg.setVisibility(View.VISIBLE);
        }

        try {

            String imageUrl = restaurantBranchResponse.getRestaurantBranch().getProfileIcon().trim() + "" + restaurant.getAboutUs().getBanner().trim();
            Picasso.with(this).load(imageUrl.trim()).into(Profile);
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
     * Method to handle the call.
     *
     * @param view reference
     */
    private void callClicked(View view) {

        ProfileUtils.call(this, Constants.ADMIN_PHONE_NUMBER);
    }

    /**
     * Method to handle logic on email click.
     *
     * @param view reference
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
     * Method to handle logic on map clicked.
     *
     * @param view reference
     */
    private void mapClicked(View view) {

        String branch_id = restaurant.getBranchId().trim();

        Intent intent = new Intent(this, RestaurantComboActivity.class);
        intent.putExtra("branch_id", branch_id);
        startActivity(intent);

//        String coordinates = restaurant.getGeoLat().trim() + "," + restaurant.getGeoLng().trim();
//        Toast.makeText(this, "" + coordinates, Toast.LENGTH_SHORT).show();
//
//        if (coordinates == null) {
//
//            Snackbar.make(view, "Coordinates not present.", Snackbar.LENGTH_SHORT).show();
//
//        } else if (coordinates.trim().isEmpty()) {
//
//            Snackbar.make(view, "Coordinates not present.", Snackbar.LENGTH_SHORT).show();
//
//        } else {
//
//            ProfileUtils.mapLocation(this, coordinates);
//    }

    }

    /**
     * Method to handle login on reviews click.
     *
     * @param view reference
     */
    private void reviewClicked(View view) {

        Intent intent = new Intent(this, RatingActivity.class);
        intent.putExtra("branch_id", restaurant.getBranchId().trim());
        startActivity(intent);
    }
}
