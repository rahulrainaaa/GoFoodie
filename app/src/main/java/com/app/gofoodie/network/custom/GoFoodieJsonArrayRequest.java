package com.app.gofoodie.network.custom;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;


public class GoFoodieJsonArrayRequest extends JsonArrayRequest {

    /**
     * @param method        This API calling method (POST/GET).
     * @param url           Web API URL to call.
     * @param requestBody   this JSONObject String format http API request packet.
     * @param listener      instance for success response handler callback, in case of {@link org.json.JSONObject} or {@link JSONArray}.
     * @param errorListener instance for error response handler callback.
     */
    public GoFoodieJsonArrayRequest(int method, String url, String requestBody, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {

        super(method, url, requestBody, listener, errorListener);
    }

    /**
     * {@link JsonArrayRequest} Class Override methods.
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

}
