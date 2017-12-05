package com.app.gofoodie.handler.cancellationHandler;


import org.json.JSONObject;

public interface OrderCancellationListener {

    public void orderCancellationApplied(OrderCancellationHandler.RESP_CODE responseCode, OrderCancellationHandler.OP_CODE operationCode, JSONObject jsonResponse, String message);
}
