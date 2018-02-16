package com.app.gofoodie.handler.socialHandler;

import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

/**
 * Interface for sending response callback from {@link FacebookLoginHandler} to the UI Activity.
 */
@SuppressWarnings("unused")
public interface FacebookLoginListener {

    /**
     * Callback, when application facebook successfully login happens.
     *
     * @param loginResult Facebook login result.
     */
    void onFacebookLogin(LoginResult loginResult);

    /**
     * Callback, when the facebook user detail is fetched completely.
     *
     * @param object   {@link org.json.JSONArray} parameters asked from Graph API.
     * @param response Facebook Graph API response.
     */
    void onFacebookGraphAPIInformation(JSONObject object, GraphResponse response);

    /**
     * Callback, when there is error/failure/exception in facebook login, within this application.
     *
     * @param e {@link FacebookException}
     */
    void onFacebookError(FacebookException e);
}
