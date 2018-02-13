package com.app.gofoodie.handler.cancellationHandler;


import org.json.JSONObject;

/**
 * Interface for Order cancelled/vacation callbacks applied.
 * Subscribed callback comes from {@link OrderCancellationHandler}.
 */
public interface OrderCancellationListener {

    /**
     * Callback method for the cancellation/vacation of order.
     * @param responseCode
     * @param operationCode
     * @param jsonResponse
     * @param message
     */
    public void orderCancellationApplied(OrderCancellationHandler.RESP_CODE responseCode, OrderCancellationHandler.OP_CODE operationCode, JSONObject jsonResponse, String message);
}
