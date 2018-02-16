package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.customer.Customer;
import com.app.gofoodie.model.login.Login;
import com.app.gofoodie.model.rechargePlan.Subscriptionplan;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.VibrationUtil;
import com.telr.mobile.sdk.activty.WebviewActivity;
import com.telr.mobile.sdk.entity.response.status.StatusResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity class for handling failed payment.
 */
public class PaymentGatewayFailActivity extends BaseAppCompatActivity implements NetworkCallbackListener {

    public static final String TAG = "PaymentGatewayFailActivity";

    /**
     * Class private data member(s).
     */
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway_fail);
        VibrationUtil.getInstance().vibrate(this);

        Intent intent = getIntent();
        Object object = intent.getParcelableExtra(WebviewActivity.PAYMENT_RESPONSE);
        TextView textView = findViewById(R.id.text_payment_result2);

        if (object instanceof StatusResponse) {
            StatusResponse status = (StatusResponse) object;
            textView.setText("Transaction Reference:\n" + status.getTrace());

            if (status.getAuth() != null) {
                String telrTrace = status.getTrace();
                String telrStatus = status.getAuth().getStatus();   // Authorisation status. A indicates an authorised transaction. H also indicates an authorised transaction, but where the transaction has been placed on hold. Any other value indicates that the request could not be processed.
                String telrAvs = status.getAuth().getAvs();      /* Result of the AVS check:
                                            Y = AVS matched OK
                                            P = Partial match (for example, post-code only)
                                            N = AVS not matched
                                            X = AVS not checked
                                            E = Error, unable to check AVS */
                String telrCode = status.getAuth().getCode();     // If the transaction was authorised, this contains the authorisation code from the card issuer. Otherwise it contains a code indicating why the transaction could not be processed.
                String telrMessage = status.getAuth().getMessage();  // The authorisation or processing error message.
                String telrCa_valid = status.getAuth().getCa_valid();
                String telrCardcode = status.getAuth().getCardcode(); // Code to indicate the card type used in the transaction. See the code list at the end of the document for a list of card codes.
                String telrCardlast4 = status.getAuth().getCardlast4();// The last 4 digits of the card number used in the transaction. This is supplied for all payment types (including the Hosted Payment Page method) except for PayPal.
                String telrCvv = status.getAuth().getCvv();      /* Result of the CVV check:
                                           Y = CVV matched OK
                                           N = CVV not matched
                                           X = CVV not checked
                                           E = Error, unable to check CVV */
                String telrTranref = status.getAuth().getTranref(); //The payment gateway transaction reference allocated to this request.
                status.getAuth().getAvs();     /* Result of the AVS check:
                                           Y = AVS matched OK
                                           P = Partial match (for example, post-code only)
                                           N = AVS not matched
                                           X = AVS not checked
                                           E = Error, unable to check AVS */

                JSONObject jsonRequest = new JSONObject();
                try {

                    Login login = getSession();
                    Customer customer = CustomerProfileHandler.CUSTOMER;
                    Subscriptionplan subscriptionplan = GlobalData.subscriptionplan;

                    int days = Integer.parseInt(subscriptionplan.validityDays);
                    String planType = (days > 0) ? "subscription for " + days + " days" : "recharge";
                    String remarks = "Failed: " + planType + " with amount = " + subscriptionplan.payAmount + ", Plan ID = " + subscriptionplan.planId;

                    // Profile details.
                    jsonRequest.put("login_id", login.getData().getLoginId());
                    jsonRequest.put("customer_id", login.getData().getCustomerId());
                    jsonRequest.put("token", login.getData().getToken());
                    jsonRequest.put("wallet_id", customer.getProfile().getWalletId());

                    // Payment Transaction information.
                    jsonRequest.put("plan_type", (days > 0) ? "subscription" : "recharge");
                    jsonRequest.put("pg_transaction_id", telrTrace);
                    jsonRequest.put("pg_response", "fail");
                    jsonRequest.put("transaction_response", "fail");
                    jsonRequest.put("remarks", remarks);

                    // Plan information.
                    jsonRequest.put("plan_id", subscriptionplan.planId);
                    jsonRequest.put("price", subscriptionplan.payAmount);
                    jsonRequest.put("validity", subscriptionplan.validityDays);

                    // Payment gateway response information.
                    jsonRequest.put("telr_trace", telrTrace);
                    jsonRequest.put("telr_status", telrStatus);
                    jsonRequest.put("telr_avs", telrAvs);
                    jsonRequest.put("telr_code", telrCode);
                    jsonRequest.put("telr_message", telrMessage);
                    jsonRequest.put("telr_ca_valid", telrCa_valid);
                    jsonRequest.put("telr_cardcode", "");
                    jsonRequest.put("telr_cardlast4", "");
                    jsonRequest.put("telr_cvv", telrCvv);
                    jsonRequest.put("telr_tranref", telrTranref);

                    NetworkHandler networkHandler = new NetworkHandler();
                    networkHandler.httpCreate(1, this, this, jsonRequest, Network.URL_APPLY_SUBS_PLAN, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                    networkHandler.executePost();

                } catch (JSONException jsonExc) {

                    jsonExc.printStackTrace();
                    Toast.makeText(this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        } else if (object instanceof String) {
            String errorMessage = (String) object;
            textView.setText(textView.getText() + " : " + errorMessage);
        }
    }


    public void closeWindow(View view) {
        this.finish();
    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {
            handleResponse(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http Fail:  " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to handle the transaction response.
     *
     * @param json reference
     */
    private void handleResponse(JSONObject json) {

        try {

            int statusCode = json.getInt("statusCode");
            String statusMessage = json.getString("statusMessage");
            //Toast.makeText(this, "" + statusMessage, Toast.LENGTH_SHORT).show();

        } catch (JSONException jsonExc) {


            jsonExc.printStackTrace();
            Toast.makeText(this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
