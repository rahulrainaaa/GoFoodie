package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
 * @class LoginFragment
 * @desc {@link BaseFragment} Fragment class to handle Login UI screen.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, NetworkCallbackListener {

    public final String TAG = "LoginFragment";

    /**
     * Class private data members.
     */
    private MaterialEditText mEtMobileEmail = null;
    private MaterialEditText mEtPassword = null;
    private Button mBtnSignin, mBtnForgot, mBtnSignup;
    private ImageButton mImgBtnFacebook, mImgBtnGoogle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login, container, false);
        doViewMapping(view);
        Toast.makeText(getActivity(), "Login Fragment", Toast.LENGTH_SHORT).show();
        //getDashboardActivity().getProgressDialog().show();
        return view;
    }

    @Override
    public void fragQuitCallback() {

    }

    /**
     * @method doViewMapping
     * @desc Method to do mapping of all xml view to class objects.
     */
    private void doViewMapping(View view) {

        mEtMobileEmail = (MaterialEditText) view.findViewById(R.id.et_mobile_email);
        mEtPassword = (MaterialEditText) view.findViewById(R.id.et_password);
        mBtnSignin = (Button) view.findViewById(R.id.btn_sign_in);
        mBtnForgot = (Button) view.findViewById(R.id.btn_forgot_password);
        mBtnSignup = (Button) view.findViewById(R.id.btn_sign_up);
        mImgBtnFacebook = (ImageButton) view.findViewById(R.id.btn_facebook);
        mImgBtnGoogle = (ImageButton) view.findViewById(R.id.btn_google);

        mBtnSignin.setOnClickListener(this);
        mBtnForgot.setOnClickListener(this);
        mBtnSignup.setOnClickListener(this);
        mImgBtnFacebook.setOnClickListener(this);
        mImgBtnGoogle.setOnClickListener(this);

    }

    /**
     * {@link android.view.View.OnClickListener} event callback method.
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_sign_in:

                SignIn();
                break;
            case R.id.btn_sign_up:

                break;
            case R.id.btn_facebook:

                break;
            case R.id.btn_google:

                break;
            case R.id.btn_forgot_password:

                break;
        }

    }

    /**
     * @method SignIn
     * @desc Method to signIn with the entered credentials.
     */
    public void SignIn() {

        String strUsername = mEtMobileEmail.getText().toString().trim();
        String strPassword = mEtPassword.getText().toString().trim();

        if (!validateCredentials(strUsername, strPassword)) {
            return;
        }

        JSONObject jsonHttpLoginRequest = new JSONObject();
        try {
            jsonHttpLoginRequest.put("username", "cust2@email.com");
            jsonHttpLoginRequest.put("password", "newpassword");
            jsonHttpLoginRequest.put("social_login", "false");

        } catch (JSONException excJson) {
            Toast.makeText(getActivity(), "EXCEPTION: " + excJson.getMessage(), Toast.LENGTH_SHORT).show();
        }

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, jsonHttpLoginRequest, Network.URL_LOGIN, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executePost();


    }

    /**
     * @param strPassword
     * @param strUsername
     * @return boolean true = valid, false = invalid.
     * @method validateCredentials
     * @desc Method to check for credential validations and raise the error message appropriately.
     */
    private boolean validateCredentials(String strUsername, String strPassword) {

        boolean isValid = false;

        // Validate Username
        if (strUsername.isEmpty()) {
            mEtMobileEmail.setError("Cannot be empty");
        } else {
            isValid = true;
        }

        // Validate Password
        if (strPassword.isEmpty()) {
            mEtPassword.setError("Cannot be empty");
        } else {
            isValid = true;
        }

        return isValid;
    }

    /**
     * {@link NetworkCallbackListener} Http response callback methods.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        getDashboardActivity().getProgressDialog().hide();
        Toast.makeText(getActivity(), "Network Success: " + rawObject.toString(), Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {

        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        getDashboardActivity().getProgressDialog().hide();
        Toast.makeText(getActivity(), "Network Fail: " + message, Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {

        }
    }

    /**
     * @method loginResponseHandling
     * @desc Method to handle the http login response.
     */
    private void loginResponseHandling(JSONObject raw) {

        
    }

}
