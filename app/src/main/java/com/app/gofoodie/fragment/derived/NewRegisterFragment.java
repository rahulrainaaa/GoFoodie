package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @class NewRegisterFragment
 * @desc {@link BaseFragment} Fragment class to handle New Customer Registration UI screen.
 */
public class NewRegisterFragment extends BaseFragment implements View.OnClickListener, NetworkCallbackListener {

    private MaterialEditText mEtFirstName, mEtLastName, mEtEmail, mAltEmail, mEtMobile, mEtAltMobile, mEtAddress, mEtCompanyName, mEtPassword, mEtCfmPassword;
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
        mEtCompanyName = (MaterialEditText) view.findViewById(R.id.et_company_name);
        mEtPassword = (MaterialEditText) view.findViewById(R.id.et_password);
        mEtCfmPassword = (MaterialEditText) view.findViewById(R.id.et_conform_password);
        mchkAcceptTerms = (CheckBox) view.findViewById(R.id.chk_agree_terms);
        mBtnRegsiter = (Button) view.findViewById(R.id.btn_register_new);

        mBtnRegsiter.setOnClickListener(this);

        return view;
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

            isValid = true;
        }

        if (strEmail.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true;
        }

        if (strMobile.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true;
        }

        if (strAddress.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true;
        }

        if (strCompanyName.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true;
        }

        if (strPassword.isEmpty()) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.cannot_be_empty));
        } else {

            isValid = true;
        }

        if (strConfirmPassword.equals(strPassword.trim())) {

            isValid = false;
            mEtFirstName.setError(getString(R.string.confirm_password_not_match));
        } else {

            isValid = true;
        }

        if (!mchkAcceptTerms.isChecked()) {

            isValid = false;
            mchkAcceptTerms.setError("Accept the terms");
        }

        if (!isValid) {

            return;
        }

        JSONObject jsonNewUserRegisterRequest = new JSONObject();

        try {

            jsonNewUserRegisterRequest.put("name", strFirstName + " " + strLastName);
            jsonNewUserRegisterRequest.put("address", strAddress);
            jsonNewUserRegisterRequest.put("company_name", strCompanyName);
            jsonNewUserRegisterRequest.put("mobile", strMobile);
            jsonNewUserRegisterRequest.put("mobile2", strAltMobile);
            jsonNewUserRegisterRequest.put("email", strEmail);
            jsonNewUserRegisterRequest.put("email2", strAltEmail);
            jsonNewUserRegisterRequest.put("geo_lat", "");
            jsonNewUserRegisterRequest.put("geo_lng", "");
            jsonNewUserRegisterRequest.put("password", strPassword);

        } catch (JSONException excJson) {

            excJson.printStackTrace();
            Toast.makeText(getActivity(), "EXCEPTON: " + excJson.getMessage(), Toast.LENGTH_SHORT);
        }

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, jsonNewUserRegisterRequest, Network.URL_NEW_REGISTERATION, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executePost();
        getDashboardActivity().getProgressDialog().show();
    }

    /**
     * {@link NetworkCallbackListener} http response callback method.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        Toast.makeText(getActivity(), "HTTP Success: " + rawObject, Toast.LENGTH_SHORT).show();
        getDashboardActivity().getProgressDialog().hide();
        if (requestCode == 1) {

        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "HTTP Success: " + message, Toast.LENGTH_SHORT).show();
        getDashboardActivity().getProgressDialog().hide();
        if (requestCode == 1) {

        }
    }
}
