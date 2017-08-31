package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.utils.DashboardInterruptListener;
import com.app.gofoodie.activity.utils.FacebookLoginHandler;
import com.app.gofoodie.activity.utils.FacebookLoginListener;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @class LoginFragment
 * @desc {@link BaseFragment} Fragment class to handle Login UI screen.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, NetworkCallbackListener, FacebookLoginListener, GoogleApiClient.OnConnectionFailedListener {

    public final String TAG = "LoginFragment";
    private final int FACEBOOK_LOGIN = 12121;   // Facebook Intent Request code.
    private final int GOOGLE_SIGN_IN = 34343;   // Google Intent request code

    /**
     * Class private data members.
     */
    private MaterialEditText mEtMobileEmail = null;
    private MaterialEditText mEtPassword = null;
    private Button mBtnSignin, mBtnForgot, mBtnSignup;
    private LoginButton mLoginButton = null;
    private SignInButton mSignInButtion = null;
    private CallbackManager callbackManager = null;
    private FacebookLoginHandler mFacebookLoginHandler = null;
    private GoogleApiClient mGoogleApiClient = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login, container, false);
        doViewMapping(view);
        Toast.makeText(getActivity(), "Login Fragment", Toast.LENGTH_SHORT).show();

        /**
         * Facebook button and login code.
         */
        callbackManager = CallbackManager.Factory.create();
        mFacebookLoginHandler = new FacebookLoginHandler(getActivity(), this);
        mLoginButton = (LoginButton) view.findViewById(R.id.facebook_login_button);
        mLoginButton.setReadPermissions("email");
        mLoginButton.registerCallback(callbackManager, mFacebookLoginHandler);

        /**
         * Google button and login code.
         */
        SignInButton signInButton = (SignInButton) view.findViewById(R.id.google_login_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getFragmentActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {

            // Google SignIn intent result.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            // Facebook login intent result.
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * @param result
     * @method handleSignInResult
     * @desc Method to handle the result after google login.
     */
    private void handleSignInResult(GoogleSignInResult result) {

        Log.d(TAG, "Google SignIn Result: " + result.isSuccess());
        if (result.isSuccess()) {

            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(getActivity(), "" + acct.getDisplayName(), Toast.LENGTH_SHORT).show();

        } else {
            // Signed out, show unauthenticated UI.

        }
    }

    /**
     * {@link com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener} callback method(s).
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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

        mBtnSignin.setOnClickListener(this);
        mBtnForgot.setOnClickListener(this);
        mBtnSignup.setOnClickListener(this);

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
            case R.id.google_login_button:

                googleSignIn();
                break;
            case R.id.btn_sign_up:

                getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.REGISTER_NEW_USER);
                break;
            case R.id.btn_forgot_password:

                getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.FORGOT_PASSWORD);
                break;
        }
    }

    /**
     * @method googleSignIn
     * @desc Method to be called for google signing in.
     */
    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
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
        networkHandler.httpCreate(1, getDashboardActivity(), this, jsonHttpLoginRequest, Network.URL_LOGIN, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executePost();
        getDashboardActivity().getProgressDialog().show();
        getDashboardActivity().getProgressDialog().setMessage("Connecting...");

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

            isValid = false;
            mEtMobileEmail.setError("Cannot be empty");
        } else {

            isValid = true;
        }

        // Validate Password
        if (strPassword.isEmpty()) {

            isValid = false;
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

    /**
     * {@link FacebookLoginListener} listener callback methods.
     */
    @Override
    public void onFacebookLogin(LoginResult loginResult) {

        Toast.makeText(getActivity(), "Login as facebook.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFacebookGraphAPIInformation(JSONObject object, GraphResponse response) {

        Toast.makeText(getActivity(), "Graph API: " + object.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFacebookError(FacebookException e) {

        if (e == null) {

            /**
             * Facebook login cancelled.
             */
            Toast.makeText(getActivity(), "Facebook Error while login.", Toast.LENGTH_SHORT).show();
        } else {

            /**
             * {@link FacebookException} caught while login.
             */
            Toast.makeText(getActivity(), "FacebookException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
