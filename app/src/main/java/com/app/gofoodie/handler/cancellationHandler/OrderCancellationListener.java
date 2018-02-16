package com.app.gofoodie.handler.cancellationHandler;


import org.json.JSONObject;

/**
 * Interface for Order cancelled/vacation callbacks applied.
 * Subscribed callback comes from {@link OrderCancellationHandler}.
 */
public interface OrderCancellationListener {

    /**
     * Callback method for the cancellation/vacation of order.
     *
     * @param responseCode  Enum
     * @param operationCode Enum
     * @param jsonResponse  reference to {@link JSONObject} object
     * @param message       String process message.
     */
    void orderCancellationApplied(OrderCancellationHandler.RESP_CODE responseCode, OrderCancellationHandler.OP_CODE operationCode, JSONObject jsonResponse, String message);
}
