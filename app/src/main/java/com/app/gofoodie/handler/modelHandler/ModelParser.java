package com.app.gofoodie.handler.modelHandler;


import android.util.Log;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.Gson;

/**
 * @class ModelParser
 * @desc Class to map JSON to Schema and vice-versa.
 */
public class ModelParser {

    public final String TAG = "ModelParser";

    /**
     * Class private data members.
     */
    private Gson mGson = null;

    /**
     * @constructor ModelParser
     */
    public ModelParser() {
        mGson = new Gson();
    }

    /**
     * @param jsonString
     * @param className
     * @return BaseModel
     * @method getModel
     * @desc Method to get {@link BaseModel} schema from {@link org.json.JSONObject} string.
     */
    public BaseModel getModel(String jsonString, Class<? extends BaseModel> className, ModelParsingListener listener) {

        BaseModel model;
        String statusMessage;
        try {
            model = mGson.fromJson(jsonString, className);
            statusMessage = className.getName();
        } catch (Exception exception) {
            statusMessage = exception.getLocalizedMessage();
            Log.d(TAG, "" + exception.getMessage());
            model = null;
        }
        if (listener != null) {
            listener.modelParsingCallback(model, statusMessage);
        }
        return mGson.fromJson(jsonString, className);
    }


}
