package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.spinnerAdapter.LocationSpinnerAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.location.LocationResponse;
import com.app.gofoodie.model.location.Locaton;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Activity class for handling the address change request.
 */
public class AddressChangeRequestActivity extends BaseAppCompatActivity implements View.OnClickListener, NetworkCallbackListener {

    public static final String TAG = "AddressChangeRequestActivity";

    /**
     * Class private data member(s).
     */
    private Spinner mSpLocations = null;
    private EditText mEtAddress = null;
    private Button mBtnRequest = null;

    private ArrayList<Locaton> mAreaList = null;
    private LocationSpinnerAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_address_change_request);

        mSpLocations = (Spinner) findViewById(R.id.sp_areas);
        mEtAddress = (EditText) findViewById(R.id.et_new_address);
        mBtnRequest = (Button) findViewById(R.id.btn_change);
        mBtnRequest.setOnClickListener(this);
        fetchLocations();

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_change) {

            sendAddressChangeRequest();
        }
    }

    /**
     * Method fetch areas from web api.
     */
    public void fetchLocations() {

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), Network.URL_GET_LOCATIONS, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    /**
     * Method to publish fetched Location(s) in spinner from json response.
     *
     * @param json
     */
    private void publishLocations(JSONObject json) {

        ModelParser parser = new ModelParser();
        LocationResponse response = (LocationResponse) parser.getModel(json.toString(), LocationResponse.class, null);

        if (response.getStatusCode() == 200) {

            mAreaList = (ArrayList<Locaton>) response.getLocatons();
            mAreaList.add(0, new Locaton("-1", "Pick new area"));
            mAdapter = new LocationSpinnerAdapter(this, R.layout.item_spinner_text_view, mAreaList);
            mSpLocations.setAdapter(mAdapter);

        } else {

            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Alert")
                    .setContentText(response.getStatusMessage())
                    .show();
        }
    }

    /**
     * Method to handle the AddressChangeRequest Web-API response.
     *
     * @param json Web-API response {@link JSONObject}
     */
    private void handleAddressChangeResponse(JSONObject json) {

        try {

            int statusCode = json.getInt("statusCode");
            String statusMessage = json.getString("statusMessage");

            if (statusCode == 200) {

                Toast.makeText(this, "Request placed successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {

                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText(statusMessage.trim())
                        .show();
            }

        } catch (Exception e) {

            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        switch (requestCode) {

            case 1:         // http response with list of all location(s).

                publishLocations(rawObject);
                break;
            case 2:         // address change request - http response.

                handleAddressChangeResponse(rawObject);
                break;
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        /**
         * Get main thread to show UI response.
         */
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText(message)
                        .show();
            }
        });
    }

    /**
     * Method to send address change request to WEB-API.
     */
    private void sendAddressChangeRequest() {


        if (!validateFields()) {

            /**
             * Validation failed.
             * Return from method.
             */
            return;
        }

        int position = mSpLocations.getSelectedItemPosition();

        String newAddress = mEtAddress.getText().toString().trim();
        String oldAddress = CustomerProfileHandler.CUSTOMER.getProfile().getAddress().trim();
        String newLocationId = mAreaList.get(position).getAreaId().trim();
        String oldLocationId = CustomerProfileHandler.CUSTOMER.getProfile().getArea().trim();

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("customer_id", CustomerProfileHandler.CUSTOMER.getProfile().getCustomerId().trim());
            jsonObject.put("login_id", CustomerProfileHandler.CUSTOMER.getProfile().getLoginId().trim());
            jsonObject.put("current_area", oldLocationId.trim());
            jsonObject.put("current_address", oldAddress.trim());
            jsonObject.put("newarea", newLocationId.trim());
            jsonObject.put("new_address", newAddress.trim());
            jsonObject.put("email", CustomerProfileHandler.CUSTOMER.getProfile().getEmail());
            jsonObject.put("token", getSessionData().getToken());

            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(2, this, this, jsonObject, Network.URL_ADDRESS_CHANGE_REQUEST, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();

        } catch (JSONException jsonException) {

            Toast.makeText(this, "JSONException: " + jsonException.getMessage(), Toast.LENGTH_SHORT).show();
            jsonException.printStackTrace();
        }
    }

    /**
     * Method to check for validation before sending address change request.
     * Also shows appropriate message(s).
     *
     * @return boolean
     */
    private boolean validateFields() {

        int position = mSpLocations.getSelectedItemPosition();

        if (mEtAddress.getText().toString().trim().isEmpty()) {

            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Alert")
                    .setContentText("New address cannot be empty")
                    .show();

            return false;

        } else if (position < 1) {

            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Alert")
                    .setContentText("Pick an area")
                    .show();

            return false;
        }

        return true;
    }
}
