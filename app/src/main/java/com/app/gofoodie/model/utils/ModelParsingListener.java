package com.app.gofoodie.model.utils;

import com.app.gofoodie.model.base.BaseModel;

/**
 * @interface ModelParsingListener
 * @desc Listener to model
 */
public interface ModelParsingListener {

    /**
     * @method modelParsingCallback
     * @desc Callback method to get status of model parsing.
     */
    public void modelParsingCallback(BaseModel baseModel, String statusMessage);
}
