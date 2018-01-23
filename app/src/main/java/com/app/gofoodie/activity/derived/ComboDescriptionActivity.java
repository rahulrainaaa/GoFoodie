package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.model.restaurant.Restaurant;
import com.app.gofoodie.model.shortlisted.Shortlisted;
import com.app.gofoodie.utility.ProfileUtils;
import com.squareup.picasso.Picasso;

public class ComboDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        Call.setOnClickListener(this);
        Email.setOnClickListener(this);
        Map.setOnClickListener(this);
        Review.setOnClickListener(this);


        RestaurantProfileActivity.MODE mode = (RestaurantProfileActivity.MODE) getIntent().getSerializableExtra("mode");
        if (mode == RestaurantProfileActivity.MODE.SHORTLISTED) {

            showShortlistedRestaurantProfile();
        } else if (mode == RestaurantProfileActivity.MODE.REST_BRANCH) {

            showRestaurantBranchProfile();
        }
    }

    /**
     * {@link android.view.View.OnClickListener} click event callback method(s).
     */
    @Override
    public void onClick(View view) {

        String email = "";
        String coordinates = "";
        String branchId = "";
        RestaurantProfileActivity.MODE mode = (RestaurantProfileActivity.MODE) getIntent().getSerializableExtra("mode");
        if (mode == RestaurantProfileActivity.MODE.SHORTLISTED) {

            Shortlisted shortlisted = getIntent().getParcelableExtra("data");
            email = shortlisted.branchEmail.trim();
            coordinates = shortlisted.branchGeoLat + "," + shortlisted.branchGeoLng;
            branchId = shortlisted.branchId.trim();
        } else if (mode == RestaurantProfileActivity.MODE.REST_BRANCH) {

            Restaurant restaurant = getIntent().getParcelableExtra("data");
            email = restaurant.branchEmail;
            coordinates = restaurant.geoLat + "," + restaurant.geoLng;
            branchId = restaurant.branchId.trim();
        }
        switch (view.getId()) {


            case R.id.btn_call:

                callClicked(view);
                break;
            case R.id.btn_email:

                emailClicked(view, email);
                break;
            case R.id.btn_map:

                mapClicked(view, coordinates);
                break;
            case R.id.btn_rate:

                reviewClicked(view, branchId);
                break;
        }
    }

    /**
     * @method showShortlistedRestaurantProfile
     * @desc Method to show the Restaurant Branch profile of a Shortlisted Restaurant.
     */
    public void showShortlistedRestaurantProfile() {
        Shortlisted shortlisted = getIntent().getParcelableExtra("data");

        Name.setText(shortlisted.branchName);
        ReviewCount.setText("(" + shortlisted.countRating + ")");
        Cuizine.setText(shortlisted.tags);
        Address.setText(shortlisted.branchAddress);
        Postal.setText(shortlisted.branchPostalCode);
        Description.setText(shortlisted.description);
        AboutUs.setText(shortlisted.aboutUs);
        mRatingBar.setRating(Float.parseFloat(shortlisted.avgRating.trim()));

        if (shortlisted.type.trim().toLowerCase().equals("veg")) {

            Veg.setVisibility(View.VISIBLE);
            NonVeg.setVisibility(View.GONE);
        } else if (shortlisted.type.toLowerCase().equals("both")) {

            Veg.setVisibility(View.VISIBLE);
            NonVeg.setVisibility(View.VISIBLE);
        } else {

            Veg.setVisibility(View.GONE);
            NonVeg.setVisibility(View.VISIBLE);
        }

        try {

            Picasso.with(this).load(shortlisted.profileIcon.trim()).into(Profile);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed loading profile image", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @method showRestaurantBranchProfile
     * @desc Method to show the Restaurant Branch profile of selected Restaurant (which is not shortlisted restaurant).
     */
    public void showRestaurantBranchProfile() {
        Restaurant restaurant = getIntent().getParcelableExtra("data");

        Name.setText(restaurant.branchName);
        ReviewCount.setText("(" + restaurant.countRating + ")");
        Cuizine.setText(restaurant.tags);
        Address.setText(restaurant.branchAddress);
        Postal.setText(restaurant.branchPostalCode);
        Description.setText(restaurant.description);
        AboutUs.setText(restaurant.aboutUs);
        mRatingBar.setRating(Float.parseFloat(restaurant.avgRating.trim()));

        /**
         * Check the type of restaurant branch.
         */
        if (restaurant.type.trim().toLowerCase().equals("1")) {     // veg = 1.

            Veg.setVisibility(View.VISIBLE);
            NonVeg.setVisibility(View.GONE);
        } else if (restaurant.type.toLowerCase().equals("2")) {     // nonveg = 2.

            Veg.setVisibility(View.GONE);
            NonVeg.setVisibility(View.VISIBLE);
        } else {                                                    // else = any (both).

            Veg.setVisibility(View.VISIBLE);
            NonVeg.setVisibility(View.VISIBLE);
        }
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
     * @param email
     * @desc Method to handle logic on email click.
     * @method emailClicked
     */
    private void emailClicked(View view, String email) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.ADMIN_EMAIL});
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
     * @param coordinates
     * @method mapClicked
     * @desc Method to handle logic on map clicked.
     */
    private void mapClicked(View view, String coordinates) {

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
     * @param branchId
     * @method reviewClicked
     * @desc Method to handle login on reviews click.
     */
    private void reviewClicked(View view, String branchId) {

        Intent intent = new Intent(this, RatingActivity.class);
        intent.putExtra("branch_id", branchId.trim());
        startActivity(intent);
    }

    public static enum MODE {SHORTLISTED, REST_BRANCH}

}
