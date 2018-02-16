package com.app.gofoodie.handler.modelHandler;


import android.util.Log;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.Gson;

/**
 * @class ModelParser
 * @desc Class to map JSON to Schema and vice-versa.
 */
@SuppressWarnings("SameParameterValue")
public class ModelParser {

    private static final String TAG = "ModelParser";

    /**
     * Class private data members.
     */
    private Gson mGson = null;


    public ModelParser() {
        mGson = new Gson();
    }

    /**
     * Method to get {@link BaseModel} schema from {@link org.json.JSONObject} string.
     *
     * @param jsonString reference
     * @param className  reference
     * @return BaseModel
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
