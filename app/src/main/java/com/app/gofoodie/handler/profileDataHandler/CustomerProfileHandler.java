package com.app.gofoodie.handler.profileDataHandler;

import android.content.Context;

import com.app.gofoodie.model.customer.Customer;

import java.util.Date;

/**
 * @class CustomerProfileHandler
 * @desc Handler class to handle the customer full profile based on the application login session.
 */
public class CustomerProfileHandler {

    /**
     * Class Private data members.
     */
    public Context mContext = null;

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
    public Customer getFullProfile() {

        return CUSTOMER;
    }

    /**
     * @return BaseModel reference to customerFullProfile response model.
     * @method refreshProfile
     * @desc Method to fetch customer profile from WEB API.
     */
    public Customer refreshProfile() {

        return new Customer();
    }
}
