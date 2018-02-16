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
import com.app.gofoodie.activity.derived.ForgotPasswordActivity;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.dashboardHandler.DashboardInterruptListener;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.handler.socialHandler.FacebookLoginHandler;
import com.app.gofoodie.handler.socialHandler.FacebookLoginListener;
import com.app.gofoodie.model.customer.Customer;
import com.app.gofoodie.model.login.Login;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.LocationUtils;
import com.app.gofoodie.utility.SessionUtils;
import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

/**
 * @class LoginFragment
 * @desc {@link BaseFragment} Fragment class to handle Login UI screen.
 */
@SuppressWarnings({"SameParameterValue", "unused"})
public class LoginFragment extends BaseFragment implements View.OnClickListener, NetworkCallbackListener, FacebookLoginListener, GoogleApiClient.OnConnectionFailedListener {

    private final String TAG = "LoginFragment";
    /**
     * class private constant(s).
     */
    private final int FACEBOOK_LOGIN = 12121;   // Facebook Intent Request code.
    private final int GOOGLE_SIGN_IN = 34343;   // Google Intent request code
    /**
     * Class private data members.
     */
    private MaterialEditText mEtMobileEmail = null;
    private MaterialEditText mEtPassword = null;
    private SignInButton mSignInButton = null;
    private CallbackManager callbackManager = null;

    private String mNewSocialEmail = "";             // to share with email.
    private String mSocialType = "";      // empty = manual, google = google signin & facebook = facebook Login (Lowercase String).
    private boolean isSocialLoginAttempt = false;   // to check if the login is social.

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login, container, false);
        doViewMapping(view);

        LoginManager.getInstance().logOut();

        // Facebook button and login code.
        callbackManager = CallbackManager.Factory.create();
        FacebookLoginHandler mFacebookLoginHandler = new FacebookLoginHandler(getActivity(), this);
        LoginButton mLoginButton = view.findViewById(R.id.facebook_login_button);
        mLoginButton.setReadPermissions("email");
        mLoginButton.registerCallback(callbackManager, mFacebookLoginHandler);

        // Google button and login code.
        SignInButton signInButton = view.findViewById(R.id.google_login_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        try {
            if (GlobalData.mGoogleApiClient == null) {

                GlobalData.mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                        .enableAutoManage(getDashboardActivity(), this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getActivity(), "" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Remove older location preference in case new user enters.
        LocationUtils.getInstance().resetLocationPref(getActivity());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {

            // Google SignIn intent result.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGoogleSignInResult(result);
        } else {
            // Facebook login intent result.
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Method to handle the result after GOOGLE Sign In.
     *
     * @param result reference
     */
    private void handleGoogleSignInResult(GoogleSignInResult result) {

        Log.d(TAG, "Google SignIn Result: " + result.isSuccess());
        mSocialType = "google";
        if (result.isSuccess()) {

            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            assert acct != null;
            Toast.makeText(getActivity(), "" + acct.getDisplayName(), Toast.LENGTH_SHORT).show();
            mNewSocialEmail = acct.getEmail();
            loginRequest(acct.getEmail(), "yes", "");
        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(getActivity(), "Failed Google Login.", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * {@link com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener} callback method(s).
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(getActivity(), "Google SignIn Failed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fragQuitCallback() {

    }

    /**
     * Method to do mapping of all xml view to class objects.
     *
     * @param view reference
     */
    private void doViewMapping(View view) {

        mEtMobileEmail = view.findViewById(R.id.et_mobile_email);
        mEtPassword = view.findViewById(R.id.et_password);
        Button mBtnSignin = view.findViewById(R.id.btn_sign_in);
        Button mBtnForgot = view.findViewById(R.id.btn_forgot_password);
        Button mBtnSignup = view.findViewById(R.id.btn_sign_up);

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

                startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
                break;
        }
    }

    /**
     * @method googleSignIn
     * @desc Method to be called for google signing in.
     */
    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(GlobalData.mGoogleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    /**
     * @method SignIn
     * @desc Method to signIn with the entered credentials.
     */
    private void SignIn() {

        String strEmailMobile = mEtMobileEmail.getText().toString().trim();
        String strPassword = mEtPassword.getText().toString().trim();

        if (validateCredentials(strEmailMobile, strPassword)) {

            mSocialType = "";   // manual type login (non social) = empty string.
            loginRequest(strEmailMobile, "no", strPassword);
        }
    }

    /**
     * Method to create Login request packet and send it over http for response.
     *
     * @param email        String
     * @param social_login String
     * @param password     String
     */

    private void loginRequest(String email, String social_login, String password) {

        if (social_login.toLowerCase().contains("yes")) {
            isSocialLoginAttempt = true;
            mNewSocialEmail = "" + email;
        } else {
            isSocialLoginAttempt = false;
            mNewSocialEmail = "";
        }
        JSONObject jsonHttpLoginRequest = new JSONObject();
        try {
            jsonHttpLoginRequest.put("password", password);
            jsonHttpLoginRequest.put("social_login", social_login);
            jsonHttpLoginRequest.put("email", email);

        } catch (JSONException excJson) {
            Toast.makeText(getActivity(), "EXCEPTION: " + excJson.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, getDashboardActivity(), this, jsonHttpLoginRequest, Network.URL_LOGIN, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executePost();
        getDashboardActivity().getProgressDialog().show();
        getDashboardActivity().getProgressDialog().setMessage(getResources().getString(R.string.connecting));

    }

    /**
     * Method to check for credential validations and raise the error message appropriately.
     *
     * @param strPassword    String
     * @param strEmailMobile String
     * @return boolean true = validation success/ false = validation failed.
     */
    private boolean validateCredentials(String strEmailMobile, String strPassword) {

        boolean isValid;

        // Check Email field validation.
        if (strEmailMobile.isEmpty()) {

            isValid = false;
            mEtMobileEmail.setError(getActivity().getString(R.string.cannot_be_empty));
        } else if (!Pattern.compile(Constants.REGEX_EMAIL).matcher(strEmailMobile).matches()) {

            mEtMobileEmail.setError(getString(R.string.proper_email_id));
            isValid = false;

        } else {

            isValid = true;
        }


        // Validate Password
        if (strPassword.isEmpty()) {

            isValid = false;
            mEtPassword.setError(getActivity().getString(R.string.cannot_be_empty));

        } else {

            mEtPassword.setError(null);
        }

        return isValid;
    }

    /**
     * {@link NetworkCallbackListener} Http response callback methods.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        getDashboardActivity().getProgressDialog().hide();
        if (requestCode == 1) {     // Login Response.

            loginResponseHandling(rawObject);
        } else if (requestCode == 2) {      // Customer Full Profile Response.

            userProfileResponse(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        getDashboardActivity().getProgressDialog().hide();
        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();

    }

    /**
     * Method to handle the http login response.
     */
    private void loginResponseHandling(JSONObject raw) {

        ModelParser modelParser = new ModelParser();
        Login loginModel = (Login) modelParser.getModel(raw.toString(), Login.class, null);

        SessionUtils.getInstance().saveSession(getActivity(), raw);

        switch (loginModel.getStatusCode()) {

            case 200:       // Success Login.

                getCustomerProfile(loginModel.getData().getLoginId(), loginModel.getData().getToken(), loginModel.getData().getCustomerId());
                break;
            case 400:       // Bad Request.

                showAlertDialog(SweetAlertDialog.ERROR_TYPE, "Invalid credentials", "Please enter valid email and password.");
                break;
            case 406:       // Not Active or not registered.

                showAlertDialog(SweetAlertDialog.ERROR_TYPE, "Error", loginModel.getStatusMessage().trim());
                if (isSocialLoginAttempt) {

                    registerNewSocialUser(mNewSocialEmail);
                }
                break;
            case 403:

                showAlertDialog(SweetAlertDialog.ERROR_TYPE, "Error", loginModel.getStatusMessage());
                break;
        }
    }

    /**
     * Get Customer Profile data as per loginId and token.
     *
     * @param loginId String
     * @param token   String
     */
    private void getCustomerProfile(String loginId, String token, String customer_id) {

        JSONObject profileJsonRequest = new JSONObject();
        try {

            profileJsonRequest.put("login_id", loginId);
            profileJsonRequest.put("token", token);
            profileJsonRequest.put("customer_id", customer_id);
            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(2, getDashboardActivity(), this, profileJsonRequest, Network.URL_GET_CUST_PROFILE, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();
        } catch (JSONException excJson) {

            excJson.printStackTrace();
            Toast.makeText(getActivity(), "EXCEPTION: " + excJson.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to process user profile data response and finally proceed to profile activity.
     *
     * @param raw http response json object reference
     */
    private void userProfileResponse(JSONObject raw) {

        ModelParser modelParser = new ModelParser();
        CustomerProfileHandler.CUSTOMER = (Customer) modelParser.getModel(raw.toString(), Customer.class, null);
        SessionUtils.getInstance().loadSession(getActivity());
        getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.PROFILE);
    }

    /**
     * {@link FacebookLoginListener} listener callback methods.
     */
    @Override
    public void onFacebookLogin(LoginResult loginResult) {

        Toast.makeText(getActivity(), "Logged in facebook.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFacebookGraphAPIInformation(JSONObject object, GraphResponse response) {

        // Fetch the data from graph API and proceed for the social login.
        try {
            mSocialType = "facebook";
            String fbEmail = object.getString("email");
            loginRequest(fbEmail, "yes", "");

        } catch (JSONException jsonExc) {
            Toast.makeText(getActivity(), "JSONException:" + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e(TAG, jsonExc.getMessage());
            jsonExc.printStackTrace();
        }
    }

    @Override
    public void onFacebookError(FacebookException e) {

        if (e == null) {

            // Facebook login cancelled.
            Toast.makeText(getActivity(), "Facebook Error while login.", Toast.LENGTH_SHORT).show();
        } else {

            // {@link FacebookException} caught while login.
            Toast.makeText(getActivity(), "FacebookException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to get new social user email and navigate to NewUserRegister Fragment with data.
     *
     * @param email social user email id to be registered.
     */
    private void registerNewSocialUser(String email) {

        GlobalData.newSocialEmail = mNewSocialEmail;
        getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.REGISTER_NEW_SOCIAL);

    }

    /**
     * Method to simply show sweet alert dialog box of given type and content.
     *
     * @param alertType Type of {@link SweetAlertDialog}.
     * @param title     Text to show in the title.
     * @param content   Text to show in the content.
     */
    private void showAlertDialog(int alertType, String title, String content) {

        new SweetAlertDialog(getActivity(), alertType)
                .setTitleText(title)
                .setContentText(content)
                .show();

    }

}
