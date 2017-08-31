package com.app.gofoodie.model.handler;

import com.app.gofoodie.model.base.BaseModel;

/**
 * @interface ModelParsingListener
 * @desc Listener to model
 */
public interface ModelParsingListener {

    /**
     * @param statusMessage status Message in case of Exception in parsing. Else Class name if successfully parsed.
     * @param baseModel
     * @method modelParsingCallback
     * @desc Callback method to get status of model parsing.
     */
    public void modelParsingCallback(BaseModel baseModel, String statusMessage);
}
