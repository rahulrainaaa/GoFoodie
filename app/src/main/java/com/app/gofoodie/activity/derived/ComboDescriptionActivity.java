package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.comboPlan.ComboOption;
import com.app.gofoodie.model.comboPlan.ComboPlanResponse;
import com.app.gofoodie.model.comboPlan.Comboplan;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Activity class to show full info/details of a single combo plan.
 */
public class ComboDescriptionActivity extends BaseAppCompatActivity implements NetworkCallbackListener {

    public static final String TAG = "ComboDescriptionActivity";

    /**
     * Class private data member(s).
     */
    private TextView mTxtName = null;
    private TextView mTxtComboPrice = null;
    private TextView mTxtCuisine = null;
    private TextView mTxtRestaurantName = null;
    private TextView mTxtRestaurantAddress = null;
    private TextView mTxtDescription = null;
    private TextView AboutUs = null;
    private RatingBar mRatingBar = null;
    private ImageView Veg = null;
    private ImageView NonVeg = null;
    private ImageView Profile = null;
    private Button mBtnCartItems = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_description);

        mTxtName = (TextView) findViewById(R.id.txt_name);
        mTxtComboPrice = (TextView) findViewById(R.id.txt_rate_count);
        mTxtCuisine = (TextView) findViewById(R.id.txt_cuisine);
        mTxtRestaurantName = (TextView) findViewById(R.id.txt_address);
        mTxtRestaurantAddress = (TextView) findViewById(R.id.txt_postal_code);
        mTxtDescription = (TextView) findViewById(R.id.txt_description);
        AboutUs = (TextView) findViewById(R.id.txt_about_us);
        mBtnCartItems = (Button) findViewById(R.id.btn_combo_items);

        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);

        Veg = (ImageView) findViewById(R.id.img_veg);
        NonVeg = (ImageView) findViewById(R.id.img_nonveg);
        Profile = (ImageView) findViewById(R.id.img_profile);

        mBtnCartItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ComboPlanResponse combo = (ComboPlanResponse) v.getTag();
                showItems(combo);

            }
        });

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

    /**
     * Method to publish the combo info in the fields.
     *
     * @param comboplan
     */
    private void showComboInfo(Comboplan comboplan) {

        mTxtName.setText(comboplan.getComboName());
        mTxtComboPrice.setText(comboplan.getComboPayPrice() + " AED");
        mTxtCuisine.setText(comboplan.getCuisineName());
        mTxtRestaurantName.setText(comboplan.getBranchName());
        mTxtRestaurantAddress.setText(comboplan.getBranchAddress());
        mTxtDescription.setText(comboplan.getComboDescription());
//        AboutUs.setText("");

        try {
            Picasso.with(this).load(comboplan.getComboImage()).into(Profile);
        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

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
     *
     * @param comboPlanResponse
     */
    private void showItems(ComboPlanResponse comboPlanResponse) {

        /**
         * Create a bottom sheet dialog.
         */
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = (View) getLayoutInflater().inflate(R.layout.layout_combo_attributes, null);
        LinearLayout containerLayout = (LinearLayout) view.findViewById(R.id.container_layout);

        try {

            /**
             * Populate combo list items (contents) into bottom action sheet.
             */
            int attrLength = comboPlanResponse.getComboplans().get(0).getComboOptions().size();
            List<ComboOption> items = comboPlanResponse.getComboplans().get(0).getComboOptions();
            for (int i = 0; i < attrLength; i++) {

                View attrCell = (View) getLayoutInflater().inflate(R.layout.layout_combo_items, null);
                TextView txtAttributeKey = (TextView) attrCell.findViewById(R.id.txt_key);
                TextView txtAttributeValue = (TextView) attrCell.findViewById(R.id.txt_key_value);
                txtAttributeKey.setText("" + items.get(i).getName().trim());
                txtAttributeValue.setText("" + items.get(i).getOptions().get(0).trim());
                containerLayout.addView(attrCell);
            }

        } catch (Exception e) {
            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }

}
