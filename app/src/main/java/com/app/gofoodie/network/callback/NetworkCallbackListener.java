package com.app.gofoodie.network.callback;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Interface to callback from {@link com.app.gofoodie.network.handler.NetworkHandler}.
 */
@SuppressWarnings("unused")
public interface NetworkCallbackListener {

    /**
     * Callback method for success response from server and response parsed successfully.
     *
     * @param requestCode (int) http request code tag assigned before sending the request.
     * @param rawArray    http response {@link JSONObject} object reference.
     * @param rawObject   http response {@link JSONArray} object reference.
     */
    void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray);

    /**
     * Callback method for Fail in server response, connection error. Server status code != 200.
     *
     * @param requestCode The request id by user to handle distinctly.
     * @param message     The status message with the response.
     */
    void networkFailResponse(int requestCode, String message);
}
