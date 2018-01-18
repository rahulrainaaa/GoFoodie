package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.customview.WeekSelectDialog;
import com.app.gofoodie.customview.WeekSelectDialogInterface;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.handler.profileDataHandler.ProfileUpdateListener;
import com.app.gofoodie.model.customer.Customer;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.SessionUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @class WeekPreferenceActivity
 * @desc Activity class to handle the week day preference (Set and Get).
 */
public class WeekPreferenceActivity extends BaseAppCompatActivity implements NetworkCallbackListener, WeekSelectDialogInterface {

    public static final String TAG = "WeekPreferenceActivity";

    /**
     * Class private data member(s).
     */
    private WeekSelectDialog mWeekDialog = null;

    /**
     * {@link BaseAppCompatActivity} activity callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_preference);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (CustomerProfileHandler.profileExist) {
            showWeekDialog();

        } else {

            CustomerProfileHandler customerProfileHandler = new CustomerProfileHandler(this);
            customerProfileHandler.refresh(this, new ProfileUpdateListener() {
                @Override
                public void profileUpdatedCallback(Customer customer) {

                    if (customer != null) {

                        showWeekDialog();

                    } else {

                        finish();
                        // Unable to fetch profile because of no internet connection.
                    }
                }
            });
        }
    }

    /**
     * @method showWeekDialog
     * @desc Method to show the Week Dialog over activity.
     */
    private void showWeekDialog() {
        mWeekDialog = new WeekSelectDialog(this, this);
        mWeekDialog.parseWeekPreference(CustomerProfileHandler.CUSTOMER.profile.daysYouWantTheCombo);
        mWeekDialog.show();
    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        switch (requestCode) {

            case 1:                 // Set week preference response.

                handleWeekPrefUpdatedResponse(rawObject);
                break;
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "http fail: " + message.trim(), Toast.LENGTH_SHORT).show();
        showWeekDialog();
    }

    /**
     * {@link WeekSelectDialogInterface} week dialog listener callback method(s).
     */
    @Override
    public void weekDialogOkClicked(WeekSelectDialog dialog) {

        handleWeekDialogSelected(dialog);
        Toast.makeText(this, "week dialog ok clicked.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void weekDialogCancelClicked(WeekSelectDialog dialog) {

        finish();
    }

    /**
     * @param dialog
     * @method handleWeekDialogSelected
     * @desc Method to handle the Week Dialog OK button click.
     */
    private void handleWeekDialogSelected(WeekSelectDialog dialog) {

        JSONArray jsonArrayWorkingDays = dialog.getWorkingDays();
        if (jsonArrayWorkingDays.length() < 5) {

            Toast.makeText(this, "Select minimum 5 days.", Toast.LENGTH_SHORT).show();
            showWeekDialog();
            return;
        }
        JSONObject jsonRequest = new JSONObject();
        try {

            jsonRequest.put("customer_id", SessionUtils.getInstance().getSession().getData().getCustomerId());
            jsonRequest.put("login_id", SessionUtils.getInstance().getSession().getData().getLoginId());
            jsonRequest.put("weekDays", jsonArrayWorkingDays);
            jsonRequest.put("token", SessionUtils.getInstance().getSession().getData().getToken());

            String url = Network.URL_SET_WEEK_PREF;
            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, this, this, jsonRequest, url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param json
     * @method handleWeekPrefUpdatedResponse
     * @desc Method handle week days updated http response.
     */
    private void handleWeekPrefUpdatedResponse(JSONObject json) {

        try {

            int statusCode = json.getInt("statusCode");
            String statusMessage = json.getString("statusMessage");

            if (statusCode != 200) {

                Toast.makeText(this, "" + statusMessage, Toast.LENGTH_SHORT).show();
                showWeekDialog();
                return;
            }
            CustomerProfileHandler customerProfileHandler = new CustomerProfileHandler(this);
            customerProfileHandler.refresh(this, new ProfileUpdateListener() {
                @Override
                public void profileUpdatedCallback(Customer customer) {
                    finish();
                    Toast.makeText(WeekPreferenceActivity.this, "Week Preference Updated", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception exc) {
            exc.printStackTrace();
            Toast.makeText(this, "Exception: " + exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
