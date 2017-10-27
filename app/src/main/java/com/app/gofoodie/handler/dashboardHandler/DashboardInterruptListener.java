package com.app.gofoodie.handler.dashboardHandler;


/**
 * @interface DashboardInterruptListener
 * @desc Interface to implement to listen the interrupts for fragment loading.
 */
public interface DashboardInterruptListener {

    /**
     * Fragment Enumeration
     */
    public enum FRAGMENT_TYPE {
        DASHBOARD, WALLET, CART, PROFILE, LOGIN,
        NETWORK_ERROR, CHANGE_PASSWORD, FORGOT_PASSWORD,
        REGISTER_NEW_USER, REGISTER_NEW_SOCIAL, EMPTY_LIST,
        RESTAURANT_LIST, MY_ORDERS, SHORTLISTED_RESTAURANTS
    }

    /**
     * Signal Code Enumeration
     */
    public enum SIGNAL_CODE {
        HIDE_NAGIVATION_BAR
    }

    /**
     * @return
     * @method signalLoadFragment
     * @desc Callback method to load the fragment, depending upon the parameters provided.
     */
    public boolean signalLoadFragment(FRAGMENT_TYPE fragmentType);

    /**
     * @return boolean
     * @method reloadCurrentFragment
     * @desc Method to reload the current fragment into the main screen. Also reset the older values of the reloading screen.
     */
    public boolean reloadCurrentFragment();

    /**
     * @param signal
     * @return boolean
     * @method signalMessage signal code
     * @desc Callback method to notify received signal with signal code.
     */
    public boolean signalMessage(SIGNAL_CODE signal);

}
