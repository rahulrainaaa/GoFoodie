package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.comboPlan.ComboPlanResponse;
import com.app.gofoodie.model.comboPlan.Comboplan;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Activity class to show full info/details of a single combo plan.
 */
public class ComboDescriptionActivity extends BaseAppCompatActivity implements NetworkCallbackListener {

    public static final String TAG = "ComboDescriptionActivity";

    /**
     * Class private data member(s).
     */
    private TextView Name = null;
    private TextView comboPrice = null;
    private TextView Cuisine = null;
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
    private Button mBtnCartItems = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_description);

        Name = (TextView) findViewById(R.id.txt_name);
        comboPrice = (TextView) findViewById(R.id.txt_rate_count);
        Cuisine = (TextView) findViewById(R.id.txt_cuisine);
        Address = (TextView) findViewById(R.id.txt_address);
        Postal = (TextView) findViewById(R.id.txt_postal_code);
        Description = (TextView) findViewById(R.id.txt_description);
        AboutUs = (TextView) findViewById(R.id.txt_about_us);
        mBtnCartItems = (Button) findViewById(R.id.btn_combo_items);

        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);

        Veg = (ImageView) findViewById(R.id.img_veg);
        NonVeg = (ImageView) findViewById(R.id.img_nonveg);
        Profile = (ImageView) findViewById(R.id.img_profile);

        Call = (ImageButton) findViewById(R.id.btn_call);
        Email = (ImageButton) findViewById(R.id.btn_email);
        Map = (ImageButton) findViewById(R.id.btn_map);
        Review = (ImageButton) findViewById(R.id.btn_rate);

        mBtnCartItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ComboPlanResponse combo = (ComboPlanResponse) v.getTag();
                showItems(combo);

            }
        });
        mRatingBar.setEnabled(false);
        Call.setOnClickListener(null);
        Email.setOnClickListener(null);
        Map.setOnClickListener(null);
        Review.setOnClickListener(null);

        Call.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
        Map.setVisibility(View.GONE);
        Review.setVisibility(View.GONE);

        String url = Network.URL_GET_COMBO_DETAIL + getIntent().getStringExtra("combo_id");

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();

    }

    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        ModelParser parser = new ModelParser();
        ComboPlanResponse comboDetailResponse = (ComboPlanResponse) parser.getModel(rawObject.toString(), ComboPlanResponse.class, null);

        if (comboDetailResponse.getStatusCode() == 200) {


            showComboInfo(comboDetailResponse.getComboplans().get(0));
            mBtnCartItems.setTag(comboDetailResponse);
        } else {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Alert")
                    .setContentText("" + comboDetailResponse.getStatusMessage())
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            finish();
                        }
                    })
                    .show();
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http fail: " + message, Toast.LENGTH_SHORT).show();
    }

    private void showComboInfo(Comboplan comboplan) {

        Name.setText(comboplan.getComboName());
        comboPrice.setText("AED " + comboplan.getComboPayPrice());
        Cuisine.setText(comboplan.getCuisineName());
        Address.setText(comboplan.getBranchName());
        Postal.setText(comboplan.getBranchAddress());
        Description.setText(comboplan.getComboDescription());
//        AboutUs.setText("");

        Picasso.with(this).load(comboplan.getComboImage()).into(Profile);

        mRatingBar.setRating(comboplan.getAvgRating());


        if (comboplan.getComboType().equals("veg")) {

            Veg.setVisibility(View.VISIBLE);
            NonVeg.setVisibility(View.GONE);

        } else {

            Veg.setVisibility(View.GONE);
            NonVeg.setVisibility(View.VISIBLE);

        }

        Veg = null;
        NonVeg = null;
        Profile = null;
    }

    /**
     * Method to show combo items in bottom action sheet.
     */
    private void showItems(ComboPlanResponse comboPlanResponse) {


    }


}
