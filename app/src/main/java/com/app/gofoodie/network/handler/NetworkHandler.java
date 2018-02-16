package com.app.gofoodie.network.handler;


import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.custom.GoFoodieJsonArrayRequest;
import com.app.gofoodie.network.custom.GoFoodieJsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Network Handler Class (using Volley library) for the project.
 */
public class NetworkHandler implements Response.ErrorListener {

    public static final String TAG = "NetworkHandler";
    /**
     * Volley {@link RequestQueue} for this application.
     */
    private static RequestQueue VolleyRequestQueue = null;
    /**
     * Counter for monitoring number of network hits.
     */
    private static int OBJECTS_CREATED = 0;
    private static int OBJECTS_RELEASED = 0;
    private static int HTTP_TOTAL_SENT = 0;
    private static int HTTP_FAILED = 0;
    private static int HTTP_SUCCESS = 0;
    /**
     * Class private data members.
     */
    private String mUrl = null;
    private int mRequestCode = -1;
    private JSONObject mJsonRequestBody = null;
    private JsonRequest<?> mHttpJsonRequest = null;
    private BaseAppCompatActivity mBaseAppCompatActivity = null;
    private NetworkCallbackListener mNetworkCallbackListener = null;
    private RESPONSE_TYPE mResponseType = RESPONSE_TYPE.JSON_OBJECT;    // default response type = JSONObject

    /**
     * default Constructor for Network Handler.
     */
    public NetworkHandler() {
        OBJECTS_CREATED++;
    }

    /**
     * Static Method to init the Volley {@link RequestQueue} for application.
     *
     * @param context Application context.
     */
    public static void initRequestQueue(Context context) {

        // Init RequestQueue only if it is null and return true.
        // Else return false.
        if (VolleyRequestQueue == null) {
            VolleyRequestQueue = Volley.newRequestQueue(context);

        }
    }

    /**
     * Static Method to clear the Volley {@link RequestQueue} requests, in Application.
     */
    public static void clearRequestQueue() {

        // If {@link RequestQueue} is not null, then clear the queue.
        assert VolleyRequestQueue != null;
        VolleyRequestQueue.stop();
    }

    /**
     * Method to initialize the class data members and create network handler.
     *
     * @param requestCode             user specific code to determine the request among multiple.
     * @param appCompatActivity       {@link BaseAppCompatActivity} reference.
     * @param networkCallbackListener instance for network response callback using {@link NetworkCallbackListener}.
     * @param jsonRequestBody         API request packet.
     * @param url                     API url.
     * @param responseType            JSONObject or JSONArray.
     */
    public void httpCreate(int requestCode, BaseAppCompatActivity appCompatActivity, NetworkCallbackListener networkCallbackListener, JSONObject jsonRequestBody, String url, RESPONSE_TYPE responseType) {

        this.mUrl = url;
        this.mRequestCode = requestCode;
        this.mJsonRequestBody = jsonRequestBody;
        this.mBaseAppCompatActivity = appCompatActivity;
        this.mNetworkCallbackListener = networkCallbackListener;
        this.mResponseType = responseType;
    }

    /**
     * Method to execute POST request with the available JSON data.
     */
    public void executePost() {

        execute(Request.Method.POST);
    }

    /**
     * Method to execute network API call with GET Method via Volley.
     */
    public void executeGet() {

        execute(Request.Method.GET);
    }

    /**
     * Method to call REST API with given Http Method.
     *
     * @param method {@link Request.Method} GET/POST.
     */
    private void execute(int method) {

        // Validation: Check for Packet data.
        if (mUrl == null) {

            Log.d(TAG, "URL is null.");
            return;
        } else if (mUrl.isEmpty()) {

            Log.d(TAG, "URL is empty.");
            return;
        }

        HTTP_TOTAL_SENT++;                                          // Handler counter monitor.
        setProcessingDialogVisibility(true);

        Log.d(TAG, "HTTP Request S.No: " + HTTP_TOTAL_SENT);

        if (mResponseType == RESPONSE_TYPE.JSON_OBJECT) {           // if JSONObject response.

            GoFoodieJsonObjectRequest goFoodieJsonObjectRequest = new GoFoodieJsonObjectRequest(method, mUrl, mJsonRequestBody.toString(), new JsonObjectResponse(), this);

            goFoodieJsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            VolleyRequestQueue.add(goFoodieJsonObjectRequest);
            mHttpJsonRequest = goFoodieJsonObjectRequest;

        } else if (mResponseType == RESPONSE_TYPE.JSON_ARRAY) {    // if JSONArray response.

            GoFoodieJsonArrayRequest goFoodieJsonArrayRequest = new GoFoodieJsonArrayRequest(method, mUrl, mJsonRequestBody.toString(), new JsonArrayResponse(), this);

            goFoodieJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            VolleyRequestQueue.add(goFoodieJsonArrayRequest);
            mHttpJsonRequest = goFoodieJsonArrayRequest;
        }
    }

    /**
     * {@link Response.ErrorListener} interface method implemented.
     */
    @Override
    public void onErrorResponse(VolleyError error) {

        HTTP_FAILED++;
        setProcessingDialogVisibility(false);

        String responseMessage;
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
     * Method to cancel the HTTP request. But don't destroy the object. Also send the callback about cancelled.
     */
    public void cancel() {

        mHttpJsonRequest.cancel();
        setProcessingDialogVisibility(false);
        if (mNetworkCallbackListener == null) {
            return;
        }
        mNetworkCallbackListener.networkFailResponse(mRequestCode, "Request Cancelled");
        mNetworkCallbackListener = null;
    }

    /**
     * @throws Throwable throws exception in case of any exception.
     */
    @Override
    protected void finalize() throws Throwable {

        OBJECTS_RELEASED++;
        mUrl = null;
        mJsonRequestBody = null;
        mNetworkCallbackListener = null;
        super.finalize();
    }

    /**
     * Method to set the visibility of {@link com.app.gofoodie.customview.GoFoodieProgressDialog} as param.
     *
     * @param status boolean
     */
    private void setProcessingDialogVisibility(boolean status) {

        // Check if instance is present.
        if (mBaseAppCompatActivity == null) {
            return;
        }

        // Set the visibility on the basis of status.
        if (status) {
            mBaseAppCompatActivity.getProgressDialog().show();
        } else {
            mBaseAppCompatActivity.getProgressDialog().hide();
        }
    }

    /**
     * Enumerated Response type from {@link NetworkHandler}.
     */
    public enum RESPONSE_TYPE {
        JSON_OBJECT, JSON_ARRAY
    }

    /**
     * Class to handle the success response in case of JSONObject response.
     */
    private class JsonObjectResponse implements Response.Listener<JSONObject> {

        /**
         * {@link Response.Listener} interface implemented method.
         */
        @Override
        public void onResponse(JSONObject response) {

            HTTP_SUCCESS++;
            setProcessingDialogVisibility(false);

            if (NetworkHandler.this.mNetworkCallbackListener != null) {
                mNetworkCallbackListener.networkSuccessResponse(NetworkHandler.this.mRequestCode, response, null);
            }
        }
    }

    /**
     * Class to handle the success response in case of JSONArray response.
     */
    private class JsonArrayResponse implements Response.Listener<JSONArray> {

        /**
         * {@link Response.Listener} interface implemented method.
         */
        @Override
        public void onResponse(JSONArray response) {

            HTTP_SUCCESS++;
            setProcessingDialogVisibility(false);

            if (NetworkHandler.this.mNetworkCallbackListener != null) {
                try {
                    mNetworkCallbackListener.networkSuccessResponse(NetworkHandler.this.mRequestCode, null, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
