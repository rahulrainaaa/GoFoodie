package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @class ChangePasswordFragment
 * @desc {@link BaseFragment} Fragment class to handle New Customer change password UI screen.
 */
public class ChangePasswordFragment extends BaseFragment implements View.OnClickListener, NetworkCallbackListener {

    private MaterialEditText mEtOldPassword = null;
    private MaterialEditText mEtNewPassword = null;
    private MaterialEditText mEtCfmNewPassword = null;
    private Button mBtnChangePassword = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_change_password, container, false);

        mEtOldPassword = (MaterialEditText) view.findViewById(R.id.et_old_password);
        mEtNewPassword = (MaterialEditText) view.findViewById(R.id.et_new_password);
        mEtCfmNewPassword = (MaterialEditText) view.findViewById(R.id.et_cfm_new_password);
        mBtnChangePassword = (Button) view.findViewById(R.id.btn_change_password);
        mBtnChangePassword.setOnClickListener(this);

        return view;
    }

    @Override
    public void fragQuitCallback() {

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

        if (!strNewPassword.equals(strCfmNewPassword.toString())) {

            mEtCfmNewPassword.setError("Conform New password not matching");
            return;
        }

        JSONObject jsonChangePasswordRequest = new JSONObject();
        try {
            jsonChangePasswordRequest.put("", "");
            jsonChangePasswordRequest.put("", "");
            jsonChangePasswordRequest.put("", "");
        } catch (JSONException excJson) {
            Toast.makeText(getActivity(), "EXCEPTION: " + excJson.getMessage(), Toast.LENGTH_SHORT).show();
        }

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, getDashboardActivity(), this, jsonChangePasswordRequest, Network.URL_CHANGE_PASSWORD, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executePost();
        getDashboardActivity().getProgressDialog().show();
    }

    /**
     * {@link NetworkCallbackListener} http response callback method.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        getDashboardActivity().getProgressDialog().hide();
        Toast.makeText(getActivity(), "Network Success: " + rawObject.toString(), Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {

            changePasswordResponseHandler(rawObject);
        }

    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        getDashboardActivity().getProgressDialog().hide();
        Toast.makeText(getActivity(), "Network Fail: " + message, Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {

        }
    }

    /**
     * @param raw {@link JSONObject} Http response from change password API call.
     * @method changePasswordResponseHandler
     * @desc Method to handle the response of Http changePassword web service.
     */
    private void changePasswordResponseHandler(JSONObject raw) {

    }
}
