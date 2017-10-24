package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.model.shortlisted.Shortlisted;

/**
 * @class RestaurantProfileActivity
 * @desc Activity class to simply show restaurant profile details with some actions.
 */
public class RestaurantProfileActivity extends BaseAppCompatActivity implements View.OnClickListener {

    public static final String TAG = "RestaurantProfileActivity";

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
        setContentView(R.layout.activity_restaurant_profile);
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
    }

    /**
     * {@link android.view.View.OnClickListener} click event callback method(s).
     */
    @Override
    public void onClick(View view) {

        Shortlisted shortlisted = getIntent().getParcelableExtra("data");
        switch (view.getId()) {


            case R.id.btn_call:

                callClicked(view, shortlisted);
                break;
            case R.id.btn_email:

                emailClicked(view, shortlisted);
                break;
            case R.id.btn_map:

                mapClicked(view, shortlisted);
                break;
            case R.id.btn_rate:

                reviewClicked(view, shortlisted);
                break;
        }
    }

    /**
     * @param shortlisted
     * @param view
     * @desc Method to handle the call.
     * @method callClicked
     */
    private void callClicked(View view, Shortlisted shortlisted) {

        Toast.makeText(this, "Calling Not Allowed.", Toast.LENGTH_SHORT).show();

    }

    /**
     * @param view
     * @param shortlisted
     * @desc Method to handle logic on email click.
     * @method emailClicked
     */
    private void emailClicked(View view, Shortlisted shortlisted) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{shortlisted.branchEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT, "CustomerID:" + shortlisted.customerId);
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
     * @param shortlisted
     * @method mapClicked
     * @desc Method to handle logic on map clicked.
     */
    private void mapClicked(View view, Shortlisted shortlisted) {

        Toast.makeText(this, "start Map under development.", Toast.LENGTH_SHORT).show();
    }

    /**
     * @param view
     * @param shortlisted
     * @method reviewClicked
     * @desc Method to handle login on reviews click.
     */
    private void reviewClicked(View view, Shortlisted shortlisted) {

        startActivity(new Intent(this, RatingActivity.class));
    }

}
