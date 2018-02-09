package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

/**
 * Activity to handle the forgot password.
 */
public class ForgotPasswordActivity extends BaseAppCompatActivity implements View.OnClickListener, NetworkCallbackListener {

    public static final String TAG = "ForgotPasswordActivity";

    /**
     * Class private data member(s).
     */
    private MaterialEditText mEtEmail = null;
    private Button mBtnForgot = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mEtEmail = (MaterialEditText) findViewById(R.id.et_forgot_email);
        mBtnForgot = (Button) findViewById(R.id.btn_forgot_password);
        mBtnForgot.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_forgot_password:

                try {
                    sendForgotPassword();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * Method to initiate for forgot password.
     *
     * @throws JSONException
     */
    private void sendForgotPassword() throws JSONException {

        String strEmail = mEtEmail.getText().toString().trim();

        /**
         * Check for the email field validation.
         */
        if (strEmail.isEmpty()) {

            mEtEmail.setError("Enter Email");
            return;

        } else if (!Pattern.compile(Constants.REGEX_EMAIL).matcher(strEmail).matches()) {

            mEtEmail.setError(getString(R.string.proper_email_id));
            return;

        } else {

            mEtEmail.setError(null);
        }

        /**
         * Send http forgot password request.
         */
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", strEmail.trim());
        NetworkHandler handler = new NetworkHandler();
        handler.httpCreate(1, this, this, jsonRequest, Network.URL_FORGOT_PASSWORD, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        handler.executePost();
    }

    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        try {

            int statusCode = rawObject.getInt("statusCode");
            String statusMessage = rawObject.getString("statusMessage");

            if (statusCode == 200) {

                Toast.makeText(this, "Please check your email", Toast.LENGTH_SHORT).show();
                finish();

            } else if (statusCode == 202) {

                showSweetAlert(SweetAlertDialog.ERROR_TYPE, "Error", "Something unexpected wrong happened.\nPlease try later.");

            } else if (statusCode == 203) {

                showSweetAlert(SweetAlertDialog.WARNING_TYPE, "Information", "You can directly with your social account.");

            } else if (statusCode == 204) {

                showSweetAlert(SweetAlertDialog.WARNING_TYPE, "Alert", "Invalid email entered.");

            } else {

                Toast.makeText(this, "" + statusMessage, Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http Error: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to show alertType dialog box of given type and with given content.
     *
     * @param alertType Type of {@link com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog}
     * @param title     Text to show in title.
     * @param content   Text to show in the content.
     */
    private void showSweetAlert(int alertType, String title, String content) {

        new SweetAlertDialog(this, alertType)
                .setTitleText(title)
                .setContentText(content)
                .show();
    }

}
