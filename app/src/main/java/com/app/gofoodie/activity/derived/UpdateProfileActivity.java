package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.handler.profileDataHandler.ProfileUpdateListener;
import com.app.gofoodie.model.customer.Customer;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.LocationUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

/**
 * @class UpdateProfileActivity
 * @desc Activity class to update the profile details of a customer wrt customer_id.
 */
public class UpdateProfileActivity extends BaseAppCompatActivity implements View.OnClickListener, NetworkCallbackListener {

    public static final String TAG = "UpdateProfileActivity";

    /**
     * Class private data member(s).
     */
    private MaterialEditText mEtName, mEtMobile, mEtAltMobile, mEtAltEmail, mEtAddress, mEtLocation;
    private Button mButton = null;
    private String mLocationName, mLocationId;

    /**
     * {@link com.app.gofoodie.activity.base.BaseAppCompatActivity} callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        mEtName = (MaterialEditText) findViewById(R.id.et_name);
        mEtAltEmail = (MaterialEditText) findViewById(R.id.et_alt_email);
        mEtMobile = (MaterialEditText) findViewById(R.id.et_mobile);
        mEtAltMobile = (MaterialEditText) findViewById(R.id.et_alt_mobile);
        mEtAddress = (MaterialEditText) findViewById(R.id.et_address);
        mEtLocation = (MaterialEditText) findViewById(R.id.et_location_pref);

        try {

            Customer customer = CustomerProfileHandler.CUSTOMER;
            mEtName.setText("" + customer.getProfile().getName());
            mEtMobile.setText("" + customer.getProfile().getMobile1());
            mEtAltMobile.setText("" + customer.getProfile().getMobile2());
            mEtAltEmail.setText("" + customer.getProfile().getEmail2());
            mEtAddress.setText("" + customer.getProfile().getAddress());

            mButton = (Button) findViewById(R.id.btn_update_profile);
            mButton.setOnClickListener(this);

            /**
             * Check if there is not order present.
             */
            if (CustomerProfileHandler.CUSTOMER.getOrderCount() == 0) {

                mEtLocation.setOnClickListener(this);

            } else {

                mEtAddress.setClickable(false);
                mEtLocation.setClickable(false);

                mEtAddress.setFocusable(false);
                mEtLocation.setFocusable(false);

            }


        } catch (Exception e) {

            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * Check for the customer order count.
         * Check if there is not order present.
         */
        if (CustomerProfileHandler.CUSTOMER.getOrderCount() == 0) {

            mLocationId = LocationUtils.getInstance().getLocationId(this, "");
            mLocationName = LocationUtils.getInstance().getLocationName(this, "");

        } else {

            mLocationId = CustomerProfileHandler.CUSTOMER.getProfile().getArea().trim();
            mLocationName = CustomerProfileHandler.CUSTOMER.getProfile().getAreaName().trim();

        }

        mEtLocation.setText("" + mLocationName.trim());

        /**
         * Get from location preference of not present.
         */
        if (mLocationName.trim().isEmpty() || mLocationId.trim().isEmpty()) {

            startActivity(new Intent(this, LocationActivity.class));
        }
    }

    /**
     * {@link android.view.View.OnClickListener} click event callback method(s).
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_update_profile:

                btnUpdateProfileClicked(view);
                break;

            case R.id.et_location_pref:

                startActivity(new Intent(this, LocationActivity.class));
                break;
        }

    }

    /**
     * @param view {@link android.widget.Button} clicked reference.
     * @method btn_update_profile
     * @desc Method will handle the logic after update button is clicked.
     */
    private void btnUpdateProfileClicked(final View view) {

        if (!checkValidations()) {

            // return if validation fails.
            return;
        }

        sendUpdateProfileRequest(view, true);

    }

    private void sendUpdateProfileRequest(View view, boolean changeAddress) {

        Customer customer = CustomerProfileHandler.CUSTOMER;

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("customer_id", customer.getProfile().getCustomerId());
            jsonRequest.put("token", getSessionData().getToken());
            jsonRequest.put("login_id", getSessionData().getLoginId());
            jsonRequest.put("name", mEtName.getText().toString().trim());
            jsonRequest.put("address", mEtAddress.getText().toString().trim());
            jsonRequest.put("location", LocationUtils.getInstance().getLocationId(this, ""));
            jsonRequest.put("location_id", LocationUtils.getInstance().getLocationId(this, ""));
            jsonRequest.put("area", LocationUtils.getInstance().getLocationId(this, ""));
            jsonRequest.put("geo_lat", "");
            jsonRequest.put("geo_lng", "");
            jsonRequest.put("mobile", mEtMobile.getText().toString().trim());
            jsonRequest.put("mobile2", mEtAltMobile.getText().toString().trim());
            jsonRequest.put("email", customer.getProfile().getEmail());
            jsonRequest.put("email2", mEtAltEmail.getText().toString().trim());
            jsonRequest.put("change_delivery_address", changeAddress);

            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, this, this, jsonRequest, Network.URL_UPDATE_PROFILE, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();
            view.setEnabled(false);


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

        mButton.setEnabled(true);

        if (requestCode == 1) {

            handleProfileUpdatedResponse(rawObject);
        }

    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        mButton.setEnabled(true);
        Toast.makeText(this, "HTTP Fail: " + message, Toast.LENGTH_SHORT).show();
    }


    /**
     * @param json Http {@link JSONObject} response.
     * @method handleProfileUpdatedResponse
     * @desc Method to handle after the profile update response comes.
     */
    private void handleProfileUpdatedResponse(JSONObject json) {

        try {

            int statusCode = json.getInt("statusCode");
            String statusMessage = json.getString("statusMessage");

            if (statusCode == 200) {

                CustomerProfileHandler customerProfileHandler = new CustomerProfileHandler(this);
                customerProfileHandler.refresh(this, new ProfileUpdateListener() {
                    @Override
                    public void profileUpdatedCallback(Customer customer) {

                        Toast.makeText(getApplicationContext(), "Profile Updated Successfully.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            } else {

                Toast.makeText(this, "Update failed. " + statusMessage, Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(this, "JSONException:" + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * @return boolean true = valid, false = validation failed.
     * @method checkValidations
     * @desc Method to check for the form field validations.
     */
    private boolean checkValidations() {

        boolean flagValidation = false;

        /**
         * Name field validation.
         */
        if (mEtName.getText().toString().trim().isEmpty()) {

            flagValidation = false;
            mEtName.setError(getString(R.string.cannot_be_empty));
        } else {

            flagValidation = true;
            mEtName.setError(null);
        }

        /**
         * Email field validations.
         */
        String strAltEmail = mEtAltEmail.getText().toString().trim();
        if (strAltEmail.trim().isEmpty()) {

            flagValidation = true & flagValidation;
            mEtAltEmail.setError(null);

        } else if (!Pattern.compile(Constants.REGEX_EMAIL).matcher(strAltEmail).matches()) {

            flagValidation = false;
            mEtAltEmail.setError(getString(R.string.proper_email_id));

        } else {

            flagValidation = true & flagValidation;
            mEtAltEmail.setError(null);
        }

        /**
         * Mobile field validation.
         */
        String strMobile = mEtMobile.getText().toString().trim();
        if (strMobile.isEmpty()) {

            flagValidation = false;
            mEtMobile.setError(getString(R.string.cannot_be_empty));

        } else if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(strMobile).matches()) {

            flagValidation = false;
            mEtMobile.setError(getString(R.string.proper_mobile_number));

        } else {

            flagValidation = true && flagValidation;
            mEtMobile.setError(null);
        }

        String strAltMobile = mEtAltMobile.getText().toString().trim();
        if (strAltMobile.isEmpty()) {

            flagValidation = true & flagValidation;
            mEtAltMobile.setError(null);

        } else if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(strAltMobile).matches()) {

            flagValidation = false;
            mEtAltMobile.setError(getString(R.string.proper_mobile_number));

        } else {

            flagValidation = true & flagValidation;
            mEtAltMobile.setError(null);
        }

        /**
         * Address field validation check.
         */
        if (mEtLocation.getText().toString().trim().isEmpty()) {

            flagValidation = false;
            startActivity(new Intent(this, LocationActivity.class));

        } else {

            flagValidation = flagValidation && true;

        }

        if (mEtAddress.getText().toString().trim().isEmpty()) {

            flagValidation = false;
            mEtAddress.setError(getString(R.string.cannot_be_empty));
        } else {

            flagValidation = flagValidation && true;
            mEtAddress.setError(null);
        }

        return flagValidation;

    }
}


