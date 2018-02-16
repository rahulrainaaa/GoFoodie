package com.app.gofoodie.handler.socialHandler;

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
@SuppressWarnings("unused")
public class FacebookLoginHandler implements FacebookCallback<LoginResult>, GraphRequest.GraphJSONObjectCallback {

    private final String TAG = "FacebookLoginHandler";

    private FacebookLoginListener mListener = null;

    /**
     * @param activity reference
     * @param listener instance for sending the callback to main class, related to facebook login activity.
     */
    public FacebookLoginHandler(Activity activity, FacebookLoginListener listener) {

        /*
      Class private data members.
     */
        this.mListener = listener;
    }

    /**
     * {@link FacebookCallback<LoginResult>} Login response callback methods.
     */
    @Override
    public void onSuccess(LoginResult loginResult) {

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
    private void triggerGraphAPI() {

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), this);
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender,  location");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
