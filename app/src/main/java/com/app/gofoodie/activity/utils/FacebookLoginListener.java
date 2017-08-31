package com.app.gofoodie.activity.utils;

import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

/**
 * @interface FacebookLoginListener
 * @desc Interface for sending response callback from {@link FacebookLoginHandler} to the UI Activity.
 */
public interface FacebookLoginListener {

    /**
     * @param loginResult Facebook login result.
     * @method onFacebookLogin
     * @desc Callback, when application facebook successfully login happens.
     */
    public void onFacebookLogin(LoginResult loginResult);

    /**
     * @param object   {@link org.json.JSONArray} parameters asked from Graph API.
     * @param response Facebook Graph API response.
     * @method onFacebookGraphAPIInformation
     * @desc Callback, when the facebook user detail is fetched completely.
     */
    public void onFacebookGraphAPIInformation(JSONObject object, GraphResponse response);

    /**
     * @param e {@link FacebookException}
     * @method onFacebookError
     * @desc Callback, when there is error/failure/exception in facebook login, within this application.
     */
    public void onFacebookError(FacebookException e);
}
