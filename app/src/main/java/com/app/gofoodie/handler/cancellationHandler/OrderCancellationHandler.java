package com.app.gofoodie.handler.cancellationHandler;


import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.DashboardActivity;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.myorders.MyOrder;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderCancellationHandler {

    public static final String TAG = "OrderCancellationHandler";

    public enum OP_CODE {CANCEL, LONGTERM, SHORTTERM, EMERGENCY}

    public enum RESP_CODE {RESP_SUCCESS, RESP_FAIL, RESP_EXCEPTION}

    private MyOrder myOrder = null;

    private DashboardActivity mActivity = null;
    private BottomSheetDialog mBottomSheetDialog = null;
    private OrderCancellationListener mCancellationListener = null;

    public OrderCancellationHandler(DashboardActivity activity) {

        this.mActivity = activity;
    }

    public void showCancellationOptions(MyOrder order, OrderCancellationListener cancellationListener) {

        this.myOrder = order;
        this.mCancellationListener = cancellationListener;

        View bottomView = mActivity.getLayoutInflater().inflate(R.layout.layout_bottom_sheet_my_orders, null);
        mBottomSheetDialog = new BottomSheetDialog(mActivity);

        Button btnCancel = (Button) bottomView.findViewById(R.id.btn_cancel_order);
        Button btnLongTerm = (Button) bottomView.findViewById(R.id.btn_long_term_vacation);
        Button btnShortTerm = (Button) bottomView.findViewById(R.id.btn_short_term_vacation);
        Button btnEmergency = (Button) bottomView.findViewById(R.id.btn_emergency_mode);

        btnCancel.setOnClickListener(mOnClickListener);
        btnLongTerm.setOnClickListener(mOnClickListener);
        btnShortTerm.setOnClickListener(mOnClickListener);
        btnEmergency.setOnClickListener(mOnClickListener);

        mBottomSheetDialog.setContentView(bottomView);
        mBottomSheetDialog.show();
    }

    private void cancelSingleOrder() {

        AlertDialog alertDialog = new AlertDialog.Builder(mActivity).create();
        alertDialog.setTitle("Confirm Cancel");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Are you sure to cancel this order?");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try {

                            final JSONObject jsonRequest = new JSONObject();
                            jsonRequest.put("login_id", mActivity.getSession().getData().getLoginId());
                            jsonRequest.put("customer_id", CustomerProfileHandler.CUSTOMER.profile.customerId.trim());
                            jsonRequest.put("wallet_id", CustomerProfileHandler.CUSTOMER.profile.walletId.trim());
                            jsonRequest.put("order_id", myOrder.orderId.trim());
                            jsonRequest.put("price_paid", myOrder.pricePaid.trim());
                            jsonRequest.put("order_set_id", myOrder.orderSetId.trim());
                            jsonRequest.put("branch_id", myOrder.branchId.trim());
                            jsonRequest.put("token", mActivity.getSession().getData().getToken());

                            NetworkHandler networkHandler = new NetworkHandler();
                            networkHandler.httpCreate(1, mActivity, new NetworkCallbackListener() {
                                @Override
                                public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

                                    respondCallback(RESP_CODE.RESP_SUCCESS, OP_CODE.CANCEL, rawObject, "Done");
                                }

                                @Override
                                public void networkFailResponse(int requestCode, String message) {

                                    respondCallback(RESP_CODE.RESP_FAIL, OP_CODE.CANCEL, null, message);
                                }
                            }, jsonRequest, Network.URL_CANCEL_ORDER, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);

                            networkHandler.executePost();

                        } catch (JSONException jsonExc) {

                            jsonExc.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }

    private void cancelLongTerm() {

        Toast.makeText(mActivity, "Long term cancellation", Toast.LENGTH_SHORT).show();
    }

    private void cancelShortTerm() {

        Toast.makeText(mActivity, "Short term cancellation", Toast.LENGTH_SHORT).show();
    }

    private void cancelEmergency() {

        Toast.makeText(mActivity, "Emergency mode cancellation", Toast.LENGTH_SHORT).show();
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mBottomSheetDialog.hide();
            mActivity.getProgressDialog().show();
            switch (view.getId()) {

                case R.id.btn_cancel_order:

                    cancelSingleOrder();
                    break;
                case R.id.btn_long_term_vacation:

                    cancelLongTerm();
                    break;
                case R.id.btn_short_term_vacation:

                    cancelShortTerm();
                    break;
                case R.id.btn_emergency_mode:

                    cancelEmergency();
                    break;
            }

        }
    };

    private void respondCallback(RESP_CODE responseCode, OP_CODE opCode, JSONObject jsonResponse, String message) {

        try {

            mActivity.getProgressDialog().hide();

            if (mCancellationListener != null) {

                mCancellationListener.orderCancellationApplied(responseCode, opCode, jsonResponse, message);
            }
        } catch (Exception e) {

            mCancellationListener.orderCancellationApplied(responseCode, opCode, jsonResponse, message);
            e.printStackTrace();
        }

    }

}
