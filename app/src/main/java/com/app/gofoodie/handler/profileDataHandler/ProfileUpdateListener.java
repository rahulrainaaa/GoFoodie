package com.app.gofoodie.handler.profileDataHandler;

import com.app.gofoodie.model.customer.Customer;

/**
 * Interface for {@link com.app.gofoodie.model.customer.Customer} profile update callback.
 */
public interface ProfileUpdateListener {

    /**
     * Callback method for {@link Customer} profile update listener.
     *
     * @param customer reference
     */
    void profileUpdatedCallback(Customer customer);

}
