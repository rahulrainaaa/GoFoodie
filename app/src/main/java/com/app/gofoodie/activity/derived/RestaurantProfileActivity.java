package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
        ReviewCount = (TextView) findViewById(R.id.txt_name);
        Cuizine = (TextView) findViewById(R.id.txt_name);
        Address = (TextView) findViewById(R.id.txt_name);
        Postal = (TextView) findViewById(R.id.txt_name);
        Description = (TextView) findViewById(R.id.txt_name);
        AboutUs = (TextView) findViewById(R.id.txt_name);

        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);

        Veg = (ImageView) findViewById(R.id.img_veg);
        NonVeg = (ImageView) findViewById(R.id.img_veg);
        Profile = (ImageView) findViewById(R.id.img_veg);

        Call = (ImageButton) findViewById(R.id.ibtn_remove);
        Email = (ImageButton) findViewById(R.id.ibtn_remove);
        Map = (ImageButton) findViewById(R.id.ibtn_remove);
        Review = (ImageButton) findViewById(R.id.ibtn_remove);

        Call.setOnClickListener(this);
        Email.setOnClickListener(this);
        Map.setOnClickListener(this);
        Review.setOnClickListener(this);

        Shortlisted shortlisted = getIntent().getParcelableExtra("data");
    }

    /**
     * {@link android.view.View.OnClickListener} click event callback method(s).
     */
    @Override
    public void onClick(View view) {

    }

    /**
     * @param view
     * @desc Method to handle the call.
     * @method callClicked
     */
    private void callClicked(View view) {

    }

    private void emailClicked(View view) {

    }

    private void mapClicked(View view) {

    }

    private void reviewClicked(View view) {

    }

}
