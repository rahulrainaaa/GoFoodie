package com.app.gofoodie.activity.utils;

import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.fragment.derived.ProfileFragment;

/**
 * @class DashboardFragmentHandler
 * @desc Class responsible to decide which Fragment class to load and return the class.
 */
public class DashboardFragmentHandler {

    /**
     * @param fragmentType
     * @return FragmentClass
     * @method getFragment
     * @desc Method to get the decide and get the fragment class.
     */
    public Class<? extends BaseFragment> getFragmentClass(DashboardInterruptListener.FRAGMENT_TYPE fragmentType) {

        // Check for the match case for fragmentType.
        if (DashboardInterruptListener.FRAGMENT_TYPE.DASHBOARD == fragmentType) {

            return ProfileFragment.class;
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.COMBOS == fragmentType) {

        } else if (DashboardInterruptListener.FRAGMENT_TYPE.WALLET == fragmentType) {

        } else if (DashboardInterruptListener.FRAGMENT_TYPE.CART == fragmentType) {

        } else if (DashboardInterruptListener.FRAGMENT_TYPE.PROFILE == fragmentType) {

        }

        // Return a default fragment in case no match found (in else case).
        return ProfileFragment.class;
    }

}

