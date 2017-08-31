package com.app.gofoodie.activity.utils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

/**
 * @class FacebookLoginHandler
 * @desc Class for handling the Facebook login for the application.
 */
public class FacebookLoginHandler implements FacebookCallback<LoginResult>, GraphRequest.GraphJSONObjectCallback {

    public final String TAG = "FacebookLoginHandler";

    /**
     * Class private data members.
     */
    private Activity mActivity = null;
    private FacebookLoginListener mListener = null;
    private LoginResult mLoginResult = null;

    /**
     * @param activity
     * @param listener instance for sending the callback to main class, related to facebook login activity.
     * @constructor FacebookLoginHandler
     */
    public FacebookLoginHandler(Activity activity, FacebookLoginListener listener) {

        this.mActivity = activity;
        this.mListener = listener;
    }

    /**
     * {@link FacebookCallback<LoginResult>} Login response callback methods.
     */
    @Override
    public void onSuccess(LoginResult loginResult) {

        this.mLoginResult = loginResult;
        Log.d(TAG, "Facebook Login Success: " + loginResult.toString());
        triggerGraphAPI();
    }

    @Override
    public void onCancel() {

        if (mListener != null) {
            mListener.onFacebookError(null);
        }
        Log.d(TAG, "Facebook onCancel");
    }

    @Override
    public void onError(FacebookException error) {

        if (mListener != null) {
            mListener.onFacebookError(error);
        }
        Log.d(TAG, "FacebookException:" + error.toString());
    }

    /**
     * {@link com.facebook.GraphRequest.GraphJSONObjectCallback} Graph API response callback methods
     */
    @Override
    public void onCompleted(JSONObject object, GraphResponse response) {

        if (mListener != null) {
            mListener.onFacebookGraphAPIInformation(object, response);
        }
        Log.d(TAG, object.toString());
//        LoginManager.getInstance().logOut();
    }

    /**
     * @desc Getting facebook user data using Graph API.
     * @method triggerGraphAPI
     */
    public void triggerGraphAPI() {

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), this);
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender,  location");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
