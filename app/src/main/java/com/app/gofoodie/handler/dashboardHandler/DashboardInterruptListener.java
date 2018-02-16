package com.app.gofoodie.handler.dashboardHandler;


/**
 * Interface to implement to listen the interrupts for fragment loading.
 */
@SuppressWarnings({"SameReturnValue", "unused"})
public interface DashboardInterruptListener {

    /**
     * Callback method to load the fragment, depending upon the parameters provided.
     *
     * @return boolean
     */
    boolean signalLoadFragment(FRAGMENT_TYPE fragmentType);

    /**
     * Method to reload the current fragment into the main screen. Also reset the older values of the reloading screen.
     *
     * @return boolean
     */
    boolean reloadCurrentFragment();

    /**
     * Callback method to notify received signal with signal code.
     *
     * @param signal signalling code from the caller fragment.
     * @return boolean
     */
    boolean signalMessage(SIGNAL_CODE signal);

    /**
     * Fragment Enumeration
     */
    @SuppressWarnings("unused")
    enum FRAGMENT_TYPE {
        DASHBOARD, WALLET, CART, PROFILE, LOGIN,
        NETWORK_ERROR, CHANGE_PASSWORD, FORGOT_PASSWORD,
        REGISTER_NEW_USER, REGISTER_NEW_SOCIAL, EMPTY_LIST,
        RESTAURANT_LIST, MY_ORDERS, SHORTLISTED_RESTAURANTS
    }

    /**
     * Signal Code Enumeration
     */
    enum SIGNAL_CODE {
        HIDE_NAVIGATION_BAR
    }

}
