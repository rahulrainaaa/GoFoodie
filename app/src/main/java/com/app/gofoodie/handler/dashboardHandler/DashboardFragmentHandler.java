package com.app.gofoodie.handler.dashboardHandler;

import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.fragment.derived.CartFragment;
import com.app.gofoodie.fragment.derived.EmptyListFragment;
import com.app.gofoodie.fragment.derived.HomeFragment;
import com.app.gofoodie.fragment.derived.LoginFragment;
import com.app.gofoodie.fragment.derived.NewRegisterFragment;
import com.app.gofoodie.fragment.derived.NewSocialRegisterFragment;
import com.app.gofoodie.fragment.derived.ProfileFragment;
import com.app.gofoodie.fragment.derived.ShortlistedRestaurantListFragment;
import com.app.gofoodie.fragment.derived.WalletFragment;
import com.app.gofoodie.utility.SessionUtils;

/**
 * @class DashboardFragmentHandler
 * @desc Class responsible to decide which Fragment class to load and return the class.
 */
public class DashboardFragmentHandler {

    /**
     * Method to get the decide and get the fragment class.
     *
     * @param fragmentType Enum
     * @return FragmentClass
     */
    public BaseFragment getFragmentClass(DashboardInterruptListener.FRAGMENT_TYPE fragmentType) {

        // Check for the match case for fragmentType.
        if (DashboardInterruptListener.FRAGMENT_TYPE.DASHBOARD == fragmentType) {

            return new HomeFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.REGISTER_NEW_USER == fragmentType) {

            return new NewRegisterFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.REGISTER_NEW_SOCIAL == fragmentType) {

            return new NewSocialRegisterFragment();
        } else if (!SessionUtils.getInstance().isSessionExist()) {      // After cases need login session.

            return new LoginFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.SHORTLISTED_RESTAURANTS == fragmentType) {

            return new ShortlistedRestaurantListFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.WALLET == fragmentType) {

            return new WalletFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.CART == fragmentType) {

            return new CartFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.PROFILE == fragmentType) {

            return new ProfileFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.LOGIN == fragmentType) {

            return new LoginFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.RESTAURANT_LIST == fragmentType) {

            return new ShortlistedRestaurantListFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.EMPTY_LIST == fragmentType) {

            return new EmptyListFragment();
        }

        // Return a default fragment in case no match found (in else case).
        return new EmptyListFragment();
    }


}

