package com.app.gofoodie.activity.utils;


/**
 * @interface DashboardInterruptListener
 * @desc Interface to implement to listen the interrupts for fragment loading.
 */
public interface DashboardInterruptListener {

    /**
     * Fragment Enumeration
     */
    public enum FRAGMENT_TYPE {
        DASHBOARD, WALLET, COMBOS, CART, PROFILE, LOGIN
    }

    /**
     * Signal Code Enumeration
     */
    public enum SIGNAL_CODE {
        HIDE_NAGIVATION_BAR
    }

    /**
     * @return
     * @method interruptLoadFragment
     * @desc Callback method to load the fragment, depending upon the parameters provided.
     */
    public boolean interruptLoadFragment(FRAGMENT_TYPE fragmentType);

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
