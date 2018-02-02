package com.app.gofoodie.handler.cancellationHandler;


import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.customer.Profile;
import com.app.gofoodie.model.myorders.MyOrder;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.shawnlin.numberpicker.NumberPicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Handler class to handle order cancellation.
 */
public class OrderCancellationHandler {

    public static final String TAG = "OrderCancellationHandler";

    /**
     * class private data member(s).
     */
    private MyOrder myOrder = null;
    private BaseAppCompatActivity mActivity = null;
    private BottomSheetDialog mBottomSheetDialog = null;
    private OrderCancellationListener mCancellationListener = null;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mBottomSheetDialog.hide();

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

    public OrderCancellationHandler(BaseAppCompatActivity activity) {

        this.mActivity = activity;
    }

    public void showCancellationOptions(MyOrder order, OrderCancellationListener cancellationListener, int mode) {

        this.myOrder = order;
        this.mCancellationListener = cancellationListener;

        /**
         * Check if (1) means single order cancellation.
         */
        if (mode == 1) {

            cancelSingleOrder();
            return;
//            btnLongTerm.setVisibility(View.GONE);
//            btnShortTerm.setVisibility(View.GONE);
        }

        /**
         * proceed for else case.
         * Inflate view and show in Bottom Action Sheet.
         */

        View bottomView = mActivity.getLayoutInflater().inflate(R.layout.layout_bottom_sheet_my_orders, null);
        mBottomSheetDialog = new BottomSheetDialog(mActivity);

        Button btnCancel = (Button) bottomView.findViewById(R.id.btn_cancel_order);
        Button btnLongTerm = (Button) bottomView.findViewById(R.id.btn_long_term_vacation);
        Button btnShortTerm = (Button) bottomView.findViewById(R.id.btn_short_term_vacation);
        Button btnEmergency = (Button) bottomView.findViewById(R.id.btn_emergency_mode);

        if (mode == 2) {     // Any Vacation mode.

            btnCancel.setVisibility(View.GONE);
            btnEmergency.setVisibility(View.GONE);
        }

        btnCancel.setOnClickListener(mOnClickListener);
        btnLongTerm.setOnClickListener(mOnClickListener);
        btnShortTerm.setOnClickListener(mOnClickListener);
        btnEmergency.setOnClickListener(mOnClickListener);

        mBottomSheetDialog.setContentView(bottomView);
        mBottomSheetDialog.show();
    }

    /**
     * Simple cancel a single order.
     */
    private void cancelSingleOrder() {

        new SweetAlertDialog(mActivity)
                .setTitleText("Order cancel")
                .setContentText("Are you sure to cancel this order?")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        try {

                            final JSONObject jsonRequest = new JSONObject();
                            jsonRequest.put("login_id", mActivity.getSession().getData().getLoginId());
                            jsonRequest.put("customer_id", CustomerProfileHandler.CUSTOMER.getProfile().getCustomerId().trim());
                            jsonRequest.put("wallet_id", CustomerProfileHandler.CUSTOMER.getProfile().getWalletId().trim());
                            jsonRequest.put("order_id", myOrder.getOrderId().trim());
                            jsonRequest.put("price_paid", myOrder.getPricePaid().trim());
                            jsonRequest.put("order_set_id", myOrder.getOrderSetId().trim());
                            jsonRequest.put("branch_id", myOrder.getBranchId().trim());
                            jsonRequest.put("token", mActivity.getSession().getData().getToken());
                            jsonRequest.put("delivery_date", myOrder.getDeliveryDate().trim());

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
                        } finally {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .setConfirmText("Yes")
                .setCancelText("No")
                .show();
    }

    /**
     * Apply long term cancellation.
     */
    private void cancelLongTerm() {

        final NumberPicker numberPicker = (NumberPicker) mActivity.getLayoutInflater().inflate(R.layout.layout_number_picker, null);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(20);

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Long-Term Vacation");
        builder.setCancelable(false);
        builder.setView(numberPicker);
        builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int days = numberPicker.getValue();

                JSONObject jsonRequest = new JSONObject();
                try {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    jsonRequest.put("login_id", mActivity.getSession().getData().getLoginId());
                    jsonRequest.put("customer_id", mActivity.getSession().getData().getCustomerId());
                    jsonRequest.put("customer_name", CustomerProfileHandler.CUSTOMER.getProfile().getName());
                    jsonRequest.put("customer_email", CustomerProfileHandler.CUSTOMER.getProfile().getEmail());
                    jsonRequest.put("order_set_id", myOrder.getOrderSetId());
                    jsonRequest.put("from_date", myOrder.getDeliveryDate());
                    Calendar calendar = Calendar.getInstance();
                    Date date = sdf.parse(myOrder.getDeliveryDate());
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, days - 1);
                    jsonRequest.put("to_date", sdf.format(calendar.getTime()));
                    jsonRequest.put("comment", "Long term vacation.");
                    jsonRequest.put("token", mActivity.getSession().getData().getToken());

                    Toast.makeText(mActivity, "Applying Long Term Vacation\nFrom: " + myOrder.getDeliveryDate() + "\nTo: " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();

                    NetworkHandler networkHandler = new NetworkHandler();
                    networkHandler.httpCreate(1, mActivity, new NetworkCallbackListener() {
                        @Override
                        public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

                            respondCallback(RESP_CODE.RESP_SUCCESS, OP_CODE.LONGTERM, rawObject, "Done");
                        }

                        @Override
                        public void networkFailResponse(int requestCode, String message) {

                            respondCallback(RESP_CODE.RESP_FAIL, OP_CODE.LONGTERM, null, message);
                        }
                    }, jsonRequest, Network.URL_LONG_VACATION, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);

                    networkHandler.executePost();

                } catch (JSONException jsonE) {

                    jsonE.printStackTrace();
                } catch (Exception e) {

                    e.printStackTrace();
                }

                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    /**
     * Method to apply short term vacation mode.
     */
    private void cancelShortTerm() {

        final NumberPicker numberPicker = (NumberPicker) mActivity.getLayoutInflater().inflate(R.layout.layout_number_picker, null);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(7);

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Short-Term Vacation");
        builder.setCancelable(false);
        builder.setView(numberPicker);
        builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int days = numberPicker.getValue();

                JSONObject jsonRequest = new JSONObject();
                try {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    jsonRequest.put("login_id", mActivity.getSession().getData().getLoginId());
                    jsonRequest.put("customer_id", mActivity.getSession().getData().getCustomerId());
                    jsonRequest.put("customer_name", CustomerProfileHandler.CUSTOMER.getProfile().getName());
                    jsonRequest.put("customer_email", CustomerProfileHandler.CUSTOMER.getProfile().getEmail());
                    jsonRequest.put("order_set_id", myOrder.getOrderSetId());
                    jsonRequest.put("from_date", myOrder.getDeliveryDate());
                    Calendar calendar = Calendar.getInstance();
                    Date date = sdf.parse(myOrder.getDeliveryDate());
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, days - 1);
                    jsonRequest.put("to_date", sdf.format(calendar.getTime()));
                    jsonRequest.put("comment", "Short term vacation.");
                    jsonRequest.put("token", mActivity.getSession().getData().getToken());

                    Toast.makeText(mActivity, "Applying Short Term Vacation\nFrom: " + myOrder.getDeliveryDate() + "\nTo: " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();

                    NetworkHandler networkHandler = new NetworkHandler();
                    networkHandler.httpCreate(1, mActivity, new NetworkCallbackListener() {
                        @Override
                        public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

                            respondCallback(RESP_CODE.RESP_SUCCESS, OP_CODE.SHORTTERM, rawObject, "Done");
                        }

                        @Override
                        public void networkFailResponse(int requestCode, String message) {

                            respondCallback(RESP_CODE.RESP_FAIL, OP_CODE.SHORTTERM, null, message);
                        }
                    }, jsonRequest, Network.URL_SHORT_VACATION, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);

                    networkHandler.executePost();

                } catch (JSONException jsonE) {

                    jsonE.printStackTrace();
                } catch (Exception e) {

                    e.printStackTrace();
                }

                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    /**
     * Method to apply cancellation with emergency mode.
     */
    private void cancelEmergency() {

        final EditText txtReason = new EditText(mActivity);
        txtReason.setHint("Reason");
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setCancelable(false);
        builder.setTitle("Emergency Cancellation");
        builder.setView(txtReason);
        builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String strReason = txtReason.getText().toString();

                if (strReason.trim().isEmpty()) {
                    Toast.makeText(mActivity, "Give reason for Emergency cancellation", Toast.LENGTH_LONG).show();
                    cancelEmergency();
                    return;
                }

                try {
                    Profile profile = CustomerProfileHandler.CUSTOMER.getProfile();

                    JSONObject jsonRequest = new JSONObject();
                    jsonRequest.put("login_id", profile.getLoginId());
                    jsonRequest.put("customer_id", profile.getCustomerId());
                    jsonRequest.put("customer_name", profile.getName());
                    jsonRequest.put("customer_email", profile.getEmail());
                    jsonRequest.put("order_set_id", myOrder.getOrderSetId());
                    jsonRequest.put("order_id", myOrder.getOrderId());
                    jsonRequest.put("from_date", myOrder.getDeliveryDate());
                    jsonRequest.put("to_date", myOrder.getDeliveryDate());
                    jsonRequest.put("comment", strReason);
                    jsonRequest.put("token", mActivity.getSession().getData().getToken());

                    NetworkHandler networkHandler = new NetworkHandler();
                    networkHandler.httpCreate(1, mActivity, new NetworkCallbackListener() {
                        @Override
                        public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

                            respondCallback(RESP_CODE.RESP_SUCCESS, OP_CODE.EMERGENCY, rawObject, "Done");
                        }

                        @Override
                        public void networkFailResponse(int requestCode, String message) {

                            respondCallback(RESP_CODE.RESP_FAIL, OP_CODE.EMERGENCY, null, message);
                        }
                    }, jsonRequest, Network.URL_EMERGENCY_CANCEL, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                    networkHandler.executePost();

                } catch (JSONException jsonE) {

                    jsonE.printStackTrace();
                }

                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                dialogInterface.dismiss();
            }
        });
        builder.show();

    }

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


    public enum OP_CODE {CANCEL, LONGTERM, SHORTTERM, EMERGENCY}

    public enum RESP_CODE {RESP_SUCCESS, RESP_FAIL, RESP_EXCEPTION}

}
