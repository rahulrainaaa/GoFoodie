package com.app.gofoodie.handler.profileDataHandler;

import android.content.Context;
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

    /**
     * Class Private data members.
     */
    public Context mContext = null;
    public ProfileUpdateListener mListener = null;

    /**
     * {@link Customer} static object to hold the customer full data.
     */
    public static Customer CUSTOMER = null;

    /**
     * Class private static data member.
     */
    private static Date sPrevTime = null;   // Last data refreshed DateTime.

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
     * @param context
     * @method refresh
     * @desc Method to fetch the customer full profile from web API.
     */
    public void refresh(Context context, BaseAppCompatActivity activity, ProfileUpdateListener listener) {

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
            Toast.makeText(context, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {

            ModelParser parser = new ModelParser();
            Customer customer = (Customer) parser.getModel(rawObject.toString(), Customer.class, null);
            CUSTOMER = customer;
            if (mListener != null) {

                mListener.profileUpdatedCallback(CUSTOMER);
            }
        }

    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(mContext, "Customer Fetch fail:\n" + message, Toast.LENGTH_SHORT).show();

        if (mListener != null) {

            mListener.profileUpdatedCallback(null);
        }
    }
}
