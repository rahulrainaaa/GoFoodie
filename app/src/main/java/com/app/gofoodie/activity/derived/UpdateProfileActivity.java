package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.customer.Customer;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.LocationUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @class UpdateProfileActivity
 * @desc Activity class to update the profile details of a customer wrt customer_id.
 */
public class UpdateProfileActivity extends BaseAppCompatActivity implements View.OnClickListener, NetworkCallbackListener {

    /**
     * Class private data member(s).
     */
    private MaterialEditText mEtName, mEtMobile, mEtAltMobile, mAltEmail, mEtAddress, mEtLocation;
    private Button mButton = null;

    /**
     * {@link com.app.gofoodie.activity.base.BaseAppCompatActivity} callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        mEtName = (MaterialEditText) findViewById(R.id.et_name);
        mAltEmail = (MaterialEditText) findViewById(R.id.et_alt_email);
        mEtMobile = (MaterialEditText) findViewById(R.id.et_mobile);
        mEtAltMobile = (MaterialEditText) findViewById(R.id.et_alt_mobile);
        mEtAddress = (MaterialEditText) findViewById(R.id.et_address);
        mEtLocation = (MaterialEditText) findViewById(R.id.et_location_pref);

        Customer customer = CustomerProfileHandler.CUSTOMER;
        mEtName.setText("" + customer.profile.name);
        mEtMobile.setText("" + customer.profile.mobile1);
        mEtAltMobile.setText("" + customer.profile.mobile2);
        mAltEmail.setText("" + customer.profile.email2);
        mEtAddress.setText("" + customer.profile.address);

        mButton = (Button) findViewById(R.id.btn_update_profile);
        mButton.setOnClickListener(this);
        mEtLocation.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String location_name = LocationUtils.getInstance().getLocationName(this, "");
        mEtLocation.setText("" + location_name);
        if (location_name.trim().isEmpty()) {

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
    private void btnUpdateProfileClicked(View view) {

        Customer customer = CustomerProfileHandler.CUSTOMER;

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("customer_id", customer.profile.customerId);
            jsonRequest.put("token", getSessionData().getToken());
            jsonRequest.put("login_id", getSessionData().getLoginId());
            jsonRequest.put("name", customer.profile.name);
            jsonRequest.put("address", customer.profile.address);
            jsonRequest.put("location", LocationUtils.getInstance().getLocationId(this, ""));
            jsonRequest.put("geo_lat", "");
            jsonRequest.put("geo_lng", "");
            jsonRequest.put("mobile", customer.profile.mobile1);
            jsonRequest.put("mobile2", customer.profile.mobile2);
            jsonRequest.put("email", customer.profile.email);
            jsonRequest.put("email2", customer.profile.email2);

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
        Toast.makeText(this, "HTTP Success: " + rawObject.toString(), Toast.LENGTH_SHORT).show();
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

                Toast.makeText(this, "Profile Updated Successfully.", Toast.LENGTH_SHORT).show();
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

        if (mEtName.getText().toString().trim().isEmpty()) {

            flagValidation = false;
            mEtName.setError(getString(R.string.cannot_be_empty));
        } else {

            flagValidation = true;
            mEtName.setError(null);
        }

        if (mEtMobile.getText().toString().trim().isEmpty()) {

            flagValidation = false;
            mEtMobile.setError(getString(R.string.cannot_be_empty));
        } else {

            flagValidation = flagValidation && true;
            mEtMobile.setError(null);
        }

        if (mEtAltMobile.getText().toString().trim().isEmpty()) {

            flagValidation = false;
            mEtAltMobile.setError(getString(R.string.cannot_be_empty));
        } else {

            flagValidation = flagValidation && true;
            mEtAltMobile.setError(null);
        }

        if (mAltEmail.getText().toString().trim().isEmpty()) {

            flagValidation = false;
            mAltEmail.setError(getString(R.string.cannot_be_empty));
        } else {

            flagValidation = flagValidation && true;
            mAltEmail.setError(null);
        }

        if (mEtAddress.getText().toString().trim().isEmpty()) {

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

