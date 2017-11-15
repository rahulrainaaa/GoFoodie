package com.app.gofoodie.handler.profileDataHandler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.customer.Customer;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.SessionUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * @class CustomerProfileHandler
 * @desc Handler class to handle the customer full profile based on the application login session.
 */
public class CustomerProfileHandler implements NetworkCallbackListener {

    public static final String TAG = "CustomerProfileHandler";

    /**
     * Class Private data members.
     */
    public Context mContext = null;
    public ProfileUpdateListener mListener = null;

    /**
     * Class public static data member(s) to share.
     */
    public static Customer CUSTOMER = null;
    public static boolean profileExist = false;

    /**
     * Class private static data member.
     */
    private static Date sPrevTime = null;   // Last data refreshed DateTime.
    private static boolean inProgress = false;      // Http request in progress or not.

    /**
     * @param context
     * @constructor CustomerProfileHandler
     * @desc Constructor to init the class data member(s) [object].
     */
    public CustomerProfileHandler(Context context) {

        this.mContext = context;
    }

    /**
     * @return BaseModel object with reference to CustomerFullProfile.
     * @method getFullProfile
     * @desc Method to provide customer full profile detail OR get simply refresh in case if needed (refresh after 2 min).
     */
    public final Customer getFullProfile() {

        return CUSTOMER;
    }

    /**
     * @param activity instance to run progress dialog on Http request while refreshing from Web API. (can be null)
     * @param listener Profile updated on http response listener. (can be null).
     * @method refresh
     * @desc Method to fetch the customer full profile from web API.
     */
    public void refresh(BaseAppCompatActivity activity, ProfileUpdateListener listener) {

        /**
         * If already in progress to refresh customer profile.
         */
        if (inProgress) {

            return;
        }
        inProgress = true;
        this.mListener = listener;
        try {

            String login_id = SessionUtils.getInstance().getSession().getData().getLoginId();
            String token = SessionUtils.getInstance().getSession().getData().getToken();

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("login_id", login_id);
            jsonRequest.put("token", token);

            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, activity, this, jsonRequest, Network.URL_GET_CUST_PROFILE, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
        }

    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        inProgress = false;

        Log.d(TAG, "Http Success: " + rawObject.toString());
        if (requestCode == 1) {

            ModelParser parser = new ModelParser();
            Customer customer = (Customer) parser.getModel(rawObject.toString(), Customer.class, null);
            CUSTOMER = customer;
            profileExist = true;
            if (mListener != null) {

                mListener.profileUpdatedCallback(CUSTOMER);
            }
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        inProgress = false;
        profileExist = false;
        Log.d(TAG, "Http Fail: " + message);

        if (mContext != null) {

            Toast.makeText(mContext, "Unable to fetch profile data", Toast.LENGTH_SHORT).show();
        }

        if (mListener != null) {

            mListener.profileUpdatedCallback(null);
        }
    }
}
