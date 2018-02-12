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
import com.app.gofoodie.utility.LocationUtils;
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
     * Class public static data member(s) to share.
     */
    public static Customer CUSTOMER = null;
    public static boolean profileExist = false;
    /**
     * Class private static data member.
     */
    private static Date sPrevTime = null;   // Last data refreshed DateTime.
    private static boolean inProgress = false;      // Http request in progress or not.
    private static boolean isProgressdDialogVisible = false;
    /**
     * Class Private data members.
     */
    private Context mContext = null;
    private BaseAppCompatActivity mActivity = null;
    private ProfileUpdateListener mListener = null;

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
            String customer_id = SessionUtils.getInstance().getSession().getData().getCustomerId();

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("login_id", login_id);
            jsonRequest.put("customer_id", customer_id);
            jsonRequest.put("token", token);

            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, activity, this, jsonRequest, Network.URL_GET_CUST_PROFILE, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();

            if (activity != null) {

                activity.getProgressDialog().show();
                this.mActivity = activity;
                isProgressdDialogVisible = true;
            }

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
        }

    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (mActivity != null) {
            mActivity.getProgressDialog().hide();
            mActivity = null;
        }

        inProgress = false;

        Log.d(TAG, "Http Success: " + rawObject.toString());
        if (requestCode == 1) {

            ModelParser parser = new ModelParser();
            Customer customer = (Customer) parser.getModel(rawObject.toString(), Customer.class, null);
            CUSTOMER = customer;

            /**
             * Check of location preference is empty.
             * Then set customer profile location into preference.
             */
            String location_name = LocationUtils.getInstance().getLocationName(mContext, "").trim();
            String location_id = LocationUtils.getInstance().getLocationId(mContext, "").trim();

            if ((location_name.isEmpty() || location_id.isEmpty()) && customer != null) {

                location_name = CustomerProfileHandler.CUSTOMER.getProfile().getAreaName().trim();
                location_id = CustomerProfileHandler.CUSTOMER.getProfile().getArea().trim();
                LocationUtils.getInstance().saveLocation(mContext, location_id.trim(), location_name.trim());
            }

            profileExist = true;
            if (mListener != null) {

                mListener.profileUpdatedCallback(CUSTOMER);
            }
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        if (mActivity != null) {
            mActivity.getProgressDialog().hide();
            mActivity = null;
        }

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
