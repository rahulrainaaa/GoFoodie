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
        DASHBOARD, WALLET, COMBOS, CART, PROFILE
    }

    /**
     * @return
     * @method interruptLoadFragment
     * @desc Callback method to load the fragment, depending upon the parameters provided.
     */
    public boolean interruptLoadFragment(FRAGMENT_TYPE fragmentType);

}
