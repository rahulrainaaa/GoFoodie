package com.app.gofoodie.handler.profileDataHandler;

import com.app.gofoodie.model.customer.Customer;

/**
 * @interface ProfileUpdateListener
 * @desc Interface for {@link com.app.gofoodie.model.customer.Customer} profile update callback.
 */
public interface ProfileUpdateListener {

    /**
     * @param customer
     * @method profileUpdatedCallback
     * @desc Callback method for {@link Customer} profile update listener.
     */
    public void profileUpdatedCallback(Customer customer);

}
