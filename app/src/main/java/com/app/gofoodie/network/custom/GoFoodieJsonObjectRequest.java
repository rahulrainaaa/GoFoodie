package com.app.gofoodie.network.custom;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GoFoodieJsonObjectRequest extends JsonObjectRequest {

    /**
     * @param method        The API calling method(POS/GET/...).
     * @param url           Web API URL to call.
     * @param request       the JSONObject String format http API request packet.
     * @param listener      instance for success response handler callback, in case of {@link JSONObject} or {@link org.json.JSONArray}.
     * @param errorListener instance for error response handler callback.
     */
    public GoFoodieJsonObjectRequest(int method, String url, String request, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        super(method, url, request, listener, errorListener);
    }

    /**
     * {@link JsonObjectRequest} Class Override methods.
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
