package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.maksim88.passwordedittext.PasswordEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity class to change signed in customer password.
 */
public class ChangePasswordActivity extends BaseAppCompatActivity implements View.OnClickListener, NetworkCallbackListener {

    public static final String TAG = "ChangePasswordActivity";

    /**
     * class private data member(s).
     */
    private PasswordEditText mEtOldPassword = null;
    private PasswordEditText mEtNewPassword = null;
    private PasswordEditText mEtCfmNewPassword = null;
    private Button mBtnChangePassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mEtOldPassword = (PasswordEditText) findViewById(R.id.et_old_password);
        mEtNewPassword = (PasswordEditText) findViewById(R.id.et_new_password);
        mEtCfmNewPassword = (PasswordEditText) findViewById(R.id.et_cfm_new_password);
        mBtnChangePassword = (Button) findViewById(R.id.btn_change_password);
        mBtnChangePassword.setOnClickListener(this);

    }

    /**
     * {@link android.view.View.OnClickListener} click event callback method.
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_change_password:

                changePassword();
                break;
        }
    }

    /**
     * @method changePassword
     * @desc Method to make HTTP change password API call.
     */
    private void changePassword() {

        String strOldPassword = mEtOldPassword.getText().toString().trim();
        String strNewPassword = mEtOldPassword.getText().toString().trim();
        String strCfmNewPassword = mEtOldPassword.getText().toString().trim();

        if (strNewPassword.isEmpty()) {

            mEtNewPassword.setError("New password cannot be empty");
            mEtCfmNewPassword.setError(null);
            mEtOldPassword.setError(null);
            return;
        } else if (!strNewPassword.equals(strCfmNewPassword.toString())) {

            mEtCfmNewPassword.setError("Confirm New password not matching");
            mEtNewPassword.setError(null);
            mEtOldPassword.setError(null);
            return;
        } else if (strOldPassword.isEmpty()) {

            mEtOldPassword.setError("Old Password cannot be empty");
            mEtCfmNewPassword.setError(null);
            mEtNewPassword.setError(null);
            return;
        }

        JSONObject jsonChangePasswordRequest = new JSONObject();
        try {
            jsonChangePasswordRequest.put("email", getSession().getData().getEmail());
            jsonChangePasswordRequest.put("login_id", getSession().getData().getLoginId());
            jsonChangePasswordRequest.put("newPwd", "" + strNewPassword);
            jsonChangePasswordRequest.put("oldPwd", "" + strOldPassword);
            jsonChangePasswordRequest.put("token", getSession().getData().getToken());
            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, this, this, jsonChangePasswordRequest, Network.URL_CHANGE_PASSWORD, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();
            getProgressDialog().show();
        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * {@link NetworkCallbackListener} http response callback method.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        getProgressDialog().hide();
        if (requestCode == 1) {

            changePasswordResponseHandler(rawObject);
        }

    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        getProgressDialog().hide();
        Toast.makeText(this, "Network Fail: " + message, Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {

        }
    }

    /**
     * @param raw {@link JSONObject} Http response from change password API call.
     * @method changePasswordResponseHandler
     * @desc Method to handle the response of Http changePassword web service.
     */
    private void changePasswordResponseHandler(JSONObject raw) {

        try {

            int statusCode = raw.getInt("statusCode");
            String statusMessage = raw.getString("statusMessage");
            if (statusCode == 200) {

                Toast.makeText(this, "Successfully changed password", Toast.LENGTH_SHORT).show();
                finish();
            } else {
            }
        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
