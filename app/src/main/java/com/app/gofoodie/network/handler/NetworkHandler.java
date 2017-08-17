package com.app.gofoodie.network.handler;


import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.custom.GoFoodieJsonArrayRequest;
import com.app.gofoodie.network.custom.GoFoodieJsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @class NetworkHandler
 * @desc Network Handler Class (using Volley library) for the project.
 */
public class NetworkHandler implements Response.ErrorListener {

    /**
     * Enumerated Response type from {@link NetworkHandler}.
     */
    public static enum RESPONSE_TYPE {
        JSON_OBJECT, JSON_ARRAY
    }

    /**
     * Volley {@link RequestQueue} for this application.
     */
    private static RequestQueue VolleyRequestQueue = null;

    /**
     * @param context Application context.
     * @return boolean
     * @desc Static Method to init the Volley {@link RequestQueue} for application.
     * @method initRequestQueue
     */
    public static boolean initRequestQueue(Context context) {

        /**
         * Init RequestQueue only if it is null and return true.
         * Else return false.
         */
        if (VolleyRequestQueue == null) {
            VolleyRequestQueue = Volley.newRequestQueue(context);
            return true;
        }
        return false;
    }

    /**
     * @desc Static Method to clear the Volley {@link RequestQueue} requests, in Application.
     * @method clearRequestQueue
     */
    public static void clearRequestQueue() {

        /**
         * If {@link RequestQueue} is not null, then clear the queue.
         */
        if (VolleyRequestQueue == null) {
            VolleyRequestQueue.stop();
        }
    }

    /**
     * Counter for tracking number of network hits.
     */
    public static int OBJECTS_CREATED = 0;
    public static int OBJECTS_RELEASED = 0;
    public static int HTTP_TOTAL_SENT = 0;
    public static int HTTP_FAILED = 0;
    public static int HTTP_SUCCESS = 0;

    /**
     * Class private data members.
     */
    private String mUrl = null;
    private RESPONSE_TYPE mResponseType = RESPONSE_TYPE.JSON_OBJECT;    // default response type = JSONObject
    private int mRequestCode = -1;
    private JSONObject mJsonRequest = null;
    private NetworkCallbackListener mNetworkCallbackListener = null;

    /**
     * @constructor NetworkHandler
     * @desc default Constructor for Network Handler.
     */
    public NetworkHandler() {
        OBJECTS_CREATED++;
    }

    /**
     * @param requestCode             user specific code to determine the request among multiple.
     * @param networkCallbackListener instance for network response callback using {@link NetworkCallbackListener}.
     * @param jsonRequest             API request packet.
     * @param url                     API url.
     * @param responseType            JSONObject or JSONArray.
     * @method httpCreate
     * @desc Method to initialize the class data members and create network handler.
     */
    public void httpCreate(int requestCode, NetworkCallbackListener networkCallbackListener, JSONObject jsonRequest, String url, RESPONSE_TYPE responseType) {

        this.mUrl = url;
        this.mRequestCode = requestCode;
        this.mJsonRequest = jsonRequest;
        this.mNetworkCallbackListener = networkCallbackListener;
        this.mResponseType = responseType;
    }

    /**
     * @method stopExecute
     * @desc Method to remove callback and stop the network api call execution.
     */
    public void stopExecute() {

        mNetworkCallbackListener = null;
    }

    /**
     * @method executeGet
     * @desc Method to execute POST request with the available JSON data.
     */
    public void executePost() {

        execute(Request.Method.POST);
    }


    /**
     * @method executeGet
     * @desc Method to execute network API call with GET Method via Volley.
     */
    public void executeGet() {

        execute(Request.Method.GET);
    }

    /**
     * @param method {@link Request.Method} GET/POST.
     * @method execute
     * @desc Method to call REST API with given Http Method.
     */
    private void execute(int method) {

        // Validation: Check for Packet data.
        if (mUrl == null) {
            return;
        } else if (mUrl.isEmpty()) {
            return;
        }

        HTTP_TOTAL_SENT++;

        RequestQueue requestQueue = VolleyRequestQueue;             // Application Volley Request Queue.

        if (mResponseType == RESPONSE_TYPE.JSON_OBJECT) {           // if JSONObject response.

            GoFoodieJsonObjectRequest goFoodieJsonObjectRequest = new GoFoodieJsonObjectRequest(method, mUrl, mJsonRequest.toString(), new JsonObjectResponse(), this);

            goFoodieJsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            requestQueue.add(goFoodieJsonObjectRequest);

        } else if (mResponseType == RESPONSE_TYPE.JSON_ARRAY) {    // if JSONArray response.

            GoFoodieJsonArrayRequest goFoodieJsonArrayRequest = new GoFoodieJsonArrayRequest(method, mUrl, mJsonRequest.toString(), new JsonArrayResponse(), this);

            goFoodieJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            requestQueue.add(goFoodieJsonArrayRequest);
        }
    }

    /**
     * @class JsonObjectResponse
     * @desc Class to handle the success response in case of JSONObject response.
     */
    private class JsonObjectResponse implements Response.Listener<JSONObject> {

        /**
         * {@link Response.Listener} interface implemented method.
         */
        @Override
        public void onResponse(JSONObject response) {

            HTTP_SUCCESS++;

            if (NetworkHandler.this.mNetworkCallbackListener != null) {
                mNetworkCallbackListener.networkSuccessResponse(NetworkHandler.this.mRequestCode, response, null);
            }
        }
    }

    /**
     * @class JSONArrayResponse
     * @desc Class to handle the success response in case of JSONArray response.
     */
    private class JsonArrayResponse implements Response.Listener<JSONArray> {

        /**
         * {@link Response.Listener} interface implemented method.
         */
        @Override
        public void onResponse(JSONArray response) {

            HTTP_SUCCESS++;

            if (NetworkHandler.this.mNetworkCallbackListener != null) {
                try {
                    mNetworkCallbackListener.networkSuccessResponse(NetworkHandler.this.mRequestCode, null, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * {@link Response.ErrorListener} interface method implemented.
     */
    @Override
    public void onErrorResponse(VolleyError error) {

        HTTP_FAILED++;

        String responseMessage = null;
        if (error.getCause() == null) {
            responseMessage = error.getMessage();       // API authentication fail error.
        } else {
            responseMessage = error.getCause().getMessage();    // Network issue.
        }

        if (this.mNetworkCallbackListener != null) {
            try {
                mNetworkCallbackListener.networkFailResponse(this.mRequestCode, responseMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @throws Throwable
     * @destructor
     */
    @Override
    protected void finalize() throws Throwable {

        OBJECTS_RELEASED++;
        mUrl = null;
        mJsonRequest = null;
        mNetworkCallbackListener = null;
        super.finalize();
    }


}