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
import com.app.gofoodie.activity.utils.DashboardInterruptListener;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.CacheUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @class NewRegisterFragment
 * @desc {@link BaseFragment} Fragment class to handle New Customer Registration UI screen.
 */
public class NewRegisterFragment extends BaseFragment implements View.OnClickListener, NetworkCallbackListener {

    private MaterialEditText mEtFirstName, mEtLastName, mEtEmail, mAltEmail, mEtMobile, mEtAltMobile, mEtAddress, mEtCompanyName, mEtPassword, mEtCfmPassword, mLocationPref;
    private Button mBtnRegsiter;
    private CheckBox mchkAcceptTerms;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_register_new_user, container, false);

        mEtFirstName = (MaterialEditText) view.findViewById(R.id.et_first_name);
        mEtLastName = (MaterialEditText) view.findViewById(R.id.et_last_name);
        mEtEmail = (MaterialEditText) view.findViewById(R.id.et_email);
        mAltEmail = (MaterialEditText) view.findViewById(R.id.et_alt_email);
        mEtMobile = (MaterialEditText) view.findViewById(R.id.et_mobile);
        mEtAltMobile = (MaterialEditText) view.findViewById(R.id.et_alt_mobile);
        mEtAddress = (MaterialEditText) view.findViewById(R.id.et_address);
        mLocationPref = (MaterialEditText) view.findViewById(R.id.et_location_pref);
        mEtCompanyName = (MaterialEditText) view.findViewById(R.id.et_location_pref);
        mEtPassword = (MaterialEditText) view.findViewById(R.id.et_password);
        mEtCfmPassword = (MaterialEditText) view.findViewById(R.id.et_conform_password);
        mchkAcceptTerms = (CheckBox) view.findViewById(R.id.chk_agree_terms);
        mBtnRegsiter = (Button) view.findViewById(R.id.btn_register_new);

        mBtnRegsiter.setOnClickListener(this);
        startActivity(new Intent(getActivity(), LocationActivity.class));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Load the user location preference into object and update UI.
        String locationId = CacheUtils.getInstance().getPref(getActivity(), CacheUtils.PREF_NAME.PREF_AREA_LOCATION).getString(Constants.PREF_AREA_LOCATION.ID.name(), "");
        String locationName = CacheUtils.getInstance().getPref(getActivity(), CacheUtils.PREF_NAME.PREF_AREA_LOCATION).getString(Constants.PREF_AREA_LOCATION.NAME.name(), "");

        mLocationPref.setText(locationName);
    }

    @Override
    public void fragQuitCallback() {

    }

    /**
     * {@link android.view.View.OnClickListener} click event callback method.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_register_new:

                registerNewUser();
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
        String strAltEmail = mAltEmail.getText().toString().trim();
        String strMobile = mEtMobile.getText().toString().trim();
        String strAltMobile = mEtAltMobile.getText().toString().trim();
        String strAddress = mEtAddress.getText().toString().trim();
        String strCompanyName = mEtCompanyName.getText().toString().trim();
        String strPassword = mEtPassword.getText().toString().trim();
        String strConfirmPassword = mEtCfmPassword.getText().toString().trim();

        boolean isValid = false;

        // Check for empty field.
        if (strFirstName.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true;
        }

        if (strLastName.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true && isValid;
        }

        if (strEmail.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true && isValid;
        }

        if (strMobile.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true && isValid;
        }

        if (strAddress.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true && isValid;
        }

        if (strCompanyName.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true && isValid;
        }

        if (strPassword.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true && isValid;
        }

        // Check for confirm password match.
        if (!strConfirmPassword.equals(strPassword.trim())) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.confirm_password_not_match));
        } else {

            isValid = true && isValid;
        }

        // Check if accepted terms & conditions.
        if (!mchkAcceptTerms.isChecked()) {

            isValid = false;
            mchkAcceptTerms.setError(getString(R.string.accept_the_terms));
            return;
        }

        if (!isValid) {

            return;
        }

        String locationId = CacheUtils.getInstance().getPref(getActivity(), CacheUtils.PREF_NAME.PREF_AREA_LOCATION).getString(Constants.PREF_AREA_LOCATION.ID.name(), "");
        String locationName = CacheUtils.getInstance().getPref(getActivity(), CacheUtils.PREF_NAME.PREF_AREA_LOCATION).getString(Constants.PREF_AREA_LOCATION.NAME.name(), "");

        mLocationPref.setText(locationName);

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
            jsonNewUserRegisterRequest.put("company_name", strCompanyName);
            jsonNewUserRegisterRequest.put("social_login", "no");
            jsonNewUserRegisterRequest.put("mobile", strMobile);
            jsonNewUserRegisterRequest.put("mobile2", strAltMobile);
            jsonNewUserRegisterRequest.put("email", strEmail);
            jsonNewUserRegisterRequest.put("email2", strAltEmail);
            jsonNewUserRegisterRequest.put("location", strAltEmail);
            jsonNewUserRegisterRequest.put("geo_lat", "");
            jsonNewUserRegisterRequest.put("geo_lng", "");
            jsonNewUserRegisterRequest.put("password", strPassword);
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

        Toast.makeText(getActivity(), "HTTP Success: " + rawObject, Toast.LENGTH_SHORT).show();
        getDashboardActivity().getProgressDialog().hide();
        if (requestCode == 1) {

            userRegisterResponse(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "HTTP Success: " + message, Toast.LENGTH_SHORT).show();
        getDashboardActivity().getProgressDialog().hide();
        if (requestCode == 1) {

        }
    }

    /**
     * @param json Http API response.
     * @method userRegisterResponse
     * @desc Method to register response.
     */
    private void userRegisterResponse(JSONObject json) {

        // Check if registered and navigate to login page.
        getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.PROFILE);
    }
}
