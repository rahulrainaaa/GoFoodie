package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.model.restaurant.Restaurant;
import com.app.gofoodie.model.shortlisted.Shortlisted;
import com.app.gofoodie.utility.ProfileUtils;
import com.squareup.picasso.Picasso;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * @class RestaurantProfileActivity
 * @desc Activity class to simply show restaurant profile details given in the model class.
 */
@SuppressWarnings("unused")
public class RestaurantProfileActivity extends BaseAppCompatActivity implements View.OnClickListener {

    public static final String TAG = "RestaurantProfileActivity";

    /**
     * Class private data member(s).
     */
    private TextView mEtName = null;
    private TextView mEtReviewCount = null;
    private TextView mEtCuisine = null;
    private TextView mEtAddress = null;
    private TextView mEtPostal = null;
    private TextView mEtDescription = null;
    private TextView mEtAboutUs = null;
    private MaterialRatingBar mRatingBar = null;
    private ImageView mImgVeg = null;
    private ImageView mImgNonVeg = null;
    private ImageView mImgProfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEtName = findViewById(R.id.txt_name);
        mEtReviewCount = findViewById(R.id.txt_rate_count);
        mEtCuisine = findViewById(R.id.txt_cuisine);
        mEtAddress = findViewById(R.id.txt_address);
        mEtPostal = findViewById(R.id.txt_postal_code);
        mEtDescription = findViewById(R.id.txt_description);
        mEtAboutUs = findViewById(R.id.txt_about_us);

        mRatingBar = findViewById(R.id.rating_bar);

        mImgVeg = findViewById(R.id.img_veg);
        mImgNonVeg = findViewById(R.id.img_nonveg);
        mImgProfile = findViewById(R.id.img_profile);

        findViewById(R.id.btn_call).setOnClickListener(this);
        findViewById(R.id.btn_email).setOnClickListener(this);
        findViewById(R.id.btn_map).setOnClickListener(this);
        findViewById(R.id.btn_rate).setOnClickListener(this);

        MODE mode = (MODE) getIntent().getSerializableExtra("mode");
        if (mode == MODE.SHORTLISTED) {

            showShortlistedRestaurantProfile();
        } else if (mode == MODE.REST_BRANCH) {

            showRestaurantBranchProfile();
        }
    }

    /**
     * {@link android.view.View.OnClickListener} click event callback method(s).
     */
    @Override
    public void onClick(View view) {

        String email = "";
        //String coordinates = "";
        String branchId = "";
        MODE mode = (MODE) getIntent().getSerializableExtra("mode");

        if (mode == MODE.SHORTLISTED) {

            Shortlisted shortlisted = getIntent().getParcelableExtra("data");
            email = shortlisted.branchEmail.trim();
            //coordinates = shortlisted.branchGeoLat + "," + shortlisted.branchGeoLng;
            branchId = shortlisted.branchId.trim();

        } else if (mode == MODE.REST_BRANCH) {

            Restaurant restaurant = getIntent().getParcelableExtra("data");
            email = restaurant.branchEmail;
            //coordinates = restaurant.geoLat + "," + restaurant.geoLng;
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

                comboClicked(view, branchId.trim());
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
    private void showShortlistedRestaurantProfile() {
        Shortlisted shortlisted = getIntent().getParcelableExtra("data");

        mEtName.setText(shortlisted.branchName);
        mEtReviewCount.setText("(" + shortlisted.countRating + ")");
        mEtCuisine.setText(shortlisted.tags);
        mEtAddress.setText(shortlisted.branchAddress);
        mEtPostal.setText(shortlisted.branchPostalCode);
        mEtDescription.setText(shortlisted.description);
        mEtAboutUs.setText(shortlisted.aboutUs);
        mRatingBar.setRating(Float.parseFloat(shortlisted.avgRating.trim()));

        if (shortlisted.type.trim().toLowerCase().equals("1")) {      // 1 = veg

            mImgVeg.setVisibility(View.VISIBLE);
            mImgNonVeg.setVisibility(View.GONE);
        } else if (shortlisted.type.toLowerCase().equals("2")) {      // 2 = non veg.

            mImgVeg.setVisibility(View.GONE);
            mImgNonVeg.setVisibility(View.VISIBLE);
        } else {                                                      // else = both

            mImgVeg.setVisibility(View.VISIBLE);
            mImgNonVeg.setVisibility(View.VISIBLE);
        }

        try {

            Picasso.with(this).load(shortlisted.profileIcon.trim()).into(mImgProfile);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed loading profile image", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @method showRestaurantBranchProfile
     * @desc Method to show the Restaurant Branch profile of selected Restaurant (which is not shortlisted restaurant).
     */
    private void showRestaurantBranchProfile() {
        Restaurant restaurant = getIntent().getParcelableExtra("data");

        mEtName.setText(restaurant.branchName);
        mEtReviewCount.setText("(" + restaurant.countRating + ")");
        mEtCuisine.setText(restaurant.tags);
        mEtAddress.setText(restaurant.branchAddress);
        mEtPostal.setText(restaurant.branchPostalCode);
        mEtDescription.setText(restaurant.description);
        mEtAboutUs.setText(restaurant.aboutUs);
        mRatingBar.setRating(Float.parseFloat(restaurant.avgRating.trim()));

        // Check the type of restaurant branch.
        if (restaurant.type.trim().toLowerCase().equals("1")) {     // veg = 1.

            mImgVeg.setVisibility(View.VISIBLE);
            mImgNonVeg.setVisibility(View.GONE);
        } else if (restaurant.type.toLowerCase().equals("2")) {     // nonveg = 2.

            mImgVeg.setVisibility(View.GONE);
            mImgNonVeg.setVisibility(View.VISIBLE);
        } else {                                                    // else = any (both).

            mImgVeg.setVisibility(View.VISIBLE);
            mImgNonVeg.setVisibility(View.VISIBLE);
        }

        try {

            Picasso.with(this).load(restaurant.profileIcon.trim()).into(mImgProfile);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed loading profile image", Toast.LENGTH_SHORT).show();
        }
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
     * @param view  reference
     * @param email String
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
     * Method to show all branch combos.
     *
     * @param view      reference
     * @param branch_id String
     */
    private void comboClicked(View view, String branch_id) {

        MODE mode = (MODE) getIntent().getSerializableExtra("mode");
        if (mode == MODE.SHORTLISTED) {         // show combos with add to cart.

            Intent intent = new Intent(this, RestaurantComboActivity.class);
            intent.putExtra("branch_id", branch_id);
            startActivity(intent);

        } else {            // show combos grid only.

            Intent intent = new Intent(this, RestaurantComboActivity.class);
            intent.putExtra("branch_id", branch_id);
            startActivity(intent);

        }


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
//        }
    }

    /**
     * Method to handle login on reviews click.
     *
     * @param view     reference
     * @param branchId String
     */
    private void reviewClicked(View view, String branchId) {

        Intent intent = new Intent(this, RatingActivity.class);
        intent.putExtra("branch_id", branchId.trim());
        startActivity(intent);
    }

    public enum MODE {SHORTLISTED, REST_BRANCH}

}
