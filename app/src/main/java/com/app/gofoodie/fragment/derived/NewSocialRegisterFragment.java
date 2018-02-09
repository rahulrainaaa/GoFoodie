package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.LocationActivity;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.dashboardHandler.DashboardInterruptListener;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.customer.Customer;
import com.app.gofoodie.model.login.Login;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.LocationUtils;
import com.app.gofoodie.utility.SessionUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

/**
 * @class NewRegisterFragment
 * @desc {@link BaseFragment} Fragment class to handle New Social (Google/Facebook) Customer - Registration Fragment UI screen.
 */
public class NewSocialRegisterFragment extends BaseFragment implements View.OnClickListener, NetworkCallbackListener {

    public static final String TAG = "NewSocialRegisterFragment";

    /**
     * Class private data member(s).
     */
    private MaterialEditText mEtFirstName, mEtLastName, mEtEmail, mEtAltEmail, mEtMobile, mEtAltMobile, mEtAddress, mEtLocationName;
    private Button mBtnRegister = null;
    private CheckBox mChkAcceptTerms = null;
    private String locationId = "";
    private String locationName = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "Enter your profile info", Toast.LENGTH_SHORT).show();

        View view = inflater.inflate(R.layout.frag_register_new_social, container, false);

        mEtFirstName = (MaterialEditText) view.findViewById(R.id.et_first_name);
        mEtLastName = (MaterialEditText) view.findViewById(R.id.et_last_name);
        mEtEmail = (MaterialEditText) view.findViewById(R.id.et_email);
        mEtAltEmail = (MaterialEditText) view.findViewById(R.id.et_alt_email);
        mEtMobile = (MaterialEditText) view.findViewById(R.id.et_mobile);
        mEtAltMobile = (MaterialEditText) view.findViewById(R.id.et_alt_mobile);
        mEtAddress = (MaterialEditText) view.findViewById(R.id.et_address);
        mEtLocationName = (MaterialEditText) view.findViewById(R.id.et_location_pref);
        mChkAcceptTerms = (CheckBox) view.findViewById(R.id.chk_agree_terms);
        mBtnRegister = (Button) view.findViewById(R.id.btn_register_new);

        mEtEmail.setText(GlobalData.newSocialEmail.trim());

        mBtnRegister.setOnClickListener(this);
        mEtLocationName.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Load the user location preference into object and update UI.
        locationId = LocationUtils.getInstance().getLocationId(getActivity(), "");
        locationName = LocationUtils.getInstance().getLocationName(getActivity(), "");

        mEtLocationName.setText(locationName);
        if (GlobalData.newSocialEmail.trim().isEmpty()) {

            mEtEmail.setText(GlobalData.newSocialEmail);
            mEtEmail.setEnabled(false);
        }
    }

    @Override
    public void fragQuitCallback() {

    }

    /**
     * {@link View.OnClickListener} click event callback method.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_register_new:

                registerNewUser();
                break;

            case R.id.et_location_pref:

                startActivity(new Intent(getActivity(), LocationActivity.class));
                break;
        }
    }

    /**
     * @method registerNewUser
     * @desc Method to validate and register new user, calling the Http API.
     */
    private void registerNewUser() {

        // Fetch the data from UI fields.
        String strFirstName = mEtFirstName.getText().toString().trim();
        String strLastName = mEtLastName.getText().toString().trim();
        String strEmail = mEtEmail.getText().toString().trim();
        String strAltEmail = mEtAltEmail.getText().toString().trim();
        String strMobile = mEtMobile.getText().toString().trim();
        String strAltMobile = mEtAltMobile.getText().toString().trim();
        String strAddress = mEtAddress.getText().toString().trim();
        String strLocationName = mEtLocationName.getText().toString().trim();

        boolean isValid = false;

        /**
         * Name Field validation.
         */
        if (strFirstName.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));

        } else if (!Pattern.compile(Constants.REGEX_NAME).matcher(strFirstName).matches()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.alpha_allowed));

        } else {

            isValid = true;
            mEtFirstName.setError(null);
        }

        if (strLastName.isEmpty()) {

            isValid = false;
            mEtLastName.setError(getString(R.string.cannot_be_empty));

        } else if (!Pattern.compile(Constants.REGEX_NAME).matcher(strLastName).matches()) {

            isValid = false;
            mEtLastName.setError(getString(R.string.alpha_allowed));

        } else {

            isValid = true & isValid;
            mEtLastName.setError(null);
        }

        /**
         * Email field validation.
         */
        if (strEmail.isEmpty()) {

            isValid = false;
            mEtEmail.setError(getString(R.string.cannot_be_empty));

        } else if (!Pattern.compile(Constants.REGEX_EMAIL).matcher(strEmail).matches()) {

            isValid = false;
            mEtEmail.setError(getString(R.string.proper_email_id));

        } else {

            isValid = true && isValid;
            mEtEmail.setError(null);
        }

        if (strAltEmail.trim().isEmpty()) {

            isValid = true & isValid;
            mEtAltEmail.setError(null);

        } else if (!Pattern.compile(Constants.REGEX_EMAIL).matcher(strAltEmail).matches()) {

            isValid = false;
            mEtAltEmail.setError(getString(R.string.proper_email_id));

        } else {

            isValid = true & isValid;
            mEtAltEmail.setError(null);
        }

        /**
         * Mobile number validation.
         */
        if (strMobile.isEmpty()) {

            isValid = false;
            mEtMobile.setError(getString(R.string.cannot_be_empty));

        } else if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(strMobile).matches()) {

            isValid = false;
            mEtMobile.setError(getString(R.string.proper_mobile_number));

        } else {

            isValid = true && isValid;
            mEtMobile.setError(null);
        }

        if (strAltMobile.trim().isEmpty()) {

            isValid = true & isValid;
            mEtAltMobile.setError(null);

        } else if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(strAltMobile).matches()) {

            isValid = false;
            mEtAltMobile.setError(getString(R.string.proper_mobile_number));

        } else {

            isValid = true & isValid;
            mEtAltMobile.setError(null);
        }

        /**
         * Address field validation.
         */
        if (strAddress.isEmpty()) {

            isValid = false;
            mEtAddress.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true && isValid;
            mEtAddress.setError(null);
        }

        if (strLocationName.isEmpty()) {

            mEtLocationName.setError(getString(R.string.cannot_be_empty));
            isValid = false;
            startActivity(new Intent(getActivity(), LocationActivity.class));
        } else {

            mEtLocationName.setError(null);
            isValid = true && isValid;
            mEtLocationName.setError(null);
        }

        /**
         * Check if terms accepted.
         */
        if (!mChkAcceptTerms.isChecked()) {

            isValid = false;
            mChkAcceptTerms.setError(getString(R.string.accept_the_terms));
            return;
        }

        /**
         * Decide and proceed after validation check.
         */
        if (!isValid) {

            return;
        }


        mEtLocationName.setText(locationName);

        // Check if the customer has selected his location preference.
        if (locationId.isEmpty() || locationName.isEmpty()) {

            Toast.makeText(getActivity(), R.string.select_location_first, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LocationActivity.class));
            return;
        }
        JSONObject jsonNewUserRegisterRequest = new JSONObject();

        try {

            jsonNewUserRegisterRequest.put("name", strFirstName + " " + strLastName);
            jsonNewUserRegisterRequest.put("address", strAddress);
            jsonNewUserRegisterRequest.put("company_name", strLocationName);
            jsonNewUserRegisterRequest.put("social_login", "yes");
            jsonNewUserRegisterRequest.put("mobile", strMobile);
            jsonNewUserRegisterRequest.put("mobile2", strAltMobile);
            jsonNewUserRegisterRequest.put("email", strEmail);
            jsonNewUserRegisterRequest.put("email2", strAltEmail);
            jsonNewUserRegisterRequest.put("location", mEtLocationName.getText().toString().trim());
            jsonNewUserRegisterRequest.put("location_id", locationId);
            jsonNewUserRegisterRequest.put("geo_lat", "");
            jsonNewUserRegisterRequest.put("geo_lng", "");
            jsonNewUserRegisterRequest.put("password", ""); // No password in social_login = yes
            jsonNewUserRegisterRequest.put("username", "");

            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, getDashboardActivity(), this, jsonNewUserRegisterRequest, Network.URL_NEW_REGISTRATION, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();

        } catch (JSONException excJson) {

            excJson.printStackTrace();
            Toast.makeText(getActivity(), "EXCEPTION: " + excJson.getMessage(), Toast.LENGTH_SHORT);
        }
    }

    /**
     * {@link NetworkCallbackListener} http response callback method.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        getDashboardActivity().getProgressDialog().hide();
        if (requestCode == 1) {             // New social register response.

            socialUserRegisterResponse(rawObject);
        } else if (requestCode == 2) {      // Login social customer response.

            socialUserLoginResponse(rawObject);
        } else if (requestCode == 3) {      // Customer Social Full Profile.

            socialGetProfile(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "HTTP Failed: " + message, Toast.LENGTH_SHORT).show();
        getDashboardActivity().getProgressDialog().hide();
    }

    /**
     * @param json Http API response.
     * @method socialUserRegisterResponse
     * @desc Method to register response.
     */
    private void socialUserRegisterResponse(JSONObject json) {

        // Check if registered and navigate to login page.
        try {

            int statusCode = json.getInt("statusCode");
            String statusMessage = json.getString("statusMessage");

            if (statusCode == 200) {        // registered new social user.

                // Now request for login.
                String email = mEtEmail.getText().toString();
                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("email", email.trim());
                jsonRequest.put("social_login", "yes");
                jsonRequest.put("password", "");    ///. No password if social_login = yes;

                NetworkHandler networkHandler = new NetworkHandler();
                networkHandler.httpCreate(2, getDashboardActivity(), this, jsonRequest, Network.URL_LOGIN, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                networkHandler.executePost();

            } else {
                Toast.makeText(getActivity(), statusCode + "#" + statusMessage, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(getActivity(), "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.PROFILE);
    }

    /**
     * @param json JSONString response.
     * @method socialUserLoginResponse
     * @desc Method to handle Customer Login API (social_login = yes).
     */
    private void socialUserLoginResponse(JSONObject json) {

        ModelParser modelParser = new ModelParser();
        Login login = (Login) modelParser.getModel(json.toString(), Login.class, null);

        SessionUtils.getInstance().saveSession(getActivity(), json);

        try {

            if (login.getStatusCode() == 200) {        // Successfully login.

                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("login_id", login.getData().getLoginId());
                jsonRequest.put("token", login.getData().getToken());
                jsonRequest.put("customer_id", login.getData().getCustomerId());

                NetworkHandler networkHandler = new NetworkHandler();
                networkHandler.httpCreate(3, getDashboardActivity(), this, jsonRequest, Network.URL_GET_CUST_PROFILE, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                networkHandler.executePost();

            } else {     // Invalid Credentials.

                Toast.makeText(getActivity(), "" + login.getStatusMessage(), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception exc) {

            Toast.makeText(getActivity(), "Exception: " + exc.getMessage(), Toast.LENGTH_SHORT).show();
            exc.printStackTrace();
        }
    }

    /**
     * @param json Social Login http response packet.
     * @method socialGetProfile
     * @desc Method to get social profile wrt to the login response.
     */
    private void socialGetProfile(JSONObject json) {

        ModelParser modelParser = new ModelParser();
        CustomerProfileHandler.CUSTOMER = (Customer) modelParser.getModel(json.toString(), Customer.class, null);
        getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.PROFILE);

    }

}
