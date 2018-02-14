package com.app.gofoodie.handler.modelHandler;

import com.app.gofoodie.model.base.BaseModel;

/**
 * @interface ModelParsingListener
 * @desc Listener to model
 */
public interface ModelParsingListener {

    /**
     * Callback method to get status of model parsing.
     *
     * @param statusMessage status Message in case of Exception in parsing. Else Class name if successfully parsed.
     * @param baseModel     model object reference
     */
    void modelParsingCallback(BaseModel baseModel, String statusMessage);
}
