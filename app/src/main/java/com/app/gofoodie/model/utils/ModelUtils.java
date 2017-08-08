package com.app.gofoodie.model.utils;


import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.Gson;

/**
 * @class ModelUtils
 * @desc Class to map JSON to Schema and vice-versa.
 */
class ModelUtils {

    private Gson mGson = null;

    private ModelUtils() {
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

        BaseModel model = null;
        String statusMessage = "";
        try {
            model = mGson.fromJson(jsonString, className);
            statusMessage = className.getName();
        } catch (Exception exception) {
            statusMessage = exception.getLocalizedMessage();
            model = null;
        }
        if (listener != null) {
            listener.modelParsingCallback(model, statusMessage);
        }
        return mGson.fromJson(jsonString, className);
    }
}
