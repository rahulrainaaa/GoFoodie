package com.app.gofoodie.activity.utils;

import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.fragment.derived.CartFragment;
import com.app.gofoodie.fragment.derived.ChangePasswordFragment;
import com.app.gofoodie.fragment.derived.EmptyListFragment;
import com.app.gofoodie.fragment.derived.ForgotPasswordFragment;
import com.app.gofoodie.fragment.derived.LoginFragment;
import com.app.gofoodie.fragment.derived.MyOrdersFragment;
import com.app.gofoodie.fragment.derived.NewRegisterFragment;
import com.app.gofoodie.fragment.derived.RestaurantListFragment;
import com.app.gofoodie.fragment.derived.WalletFragment;

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
    public BaseFragment getFragmentClass(DashboardInterruptListener.FRAGMENT_TYPE fragmentType) {

        // Check for the match case for fragmentType.
        if (DashboardInterruptListener.FRAGMENT_TYPE.DASHBOARD == fragmentType) {

            return new MyOrdersFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.COMBOS == fragmentType) {

            return new RestaurantListFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.WALLET == fragmentType) {

            return new WalletFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.CART == fragmentType) {

            return new CartFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.PROFILE == fragmentType) {

            return new LoginFragment();
//            return new ProfileFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.LOGIN == fragmentType) {

            return new LoginFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.FORGOT_PASSWORD == fragmentType) {

            return new ForgotPasswordFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.REGISTER_NEW_USER == fragmentType) {

            return new NewRegisterFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.CHANGE_PASSWORD == fragmentType) {

            return new ChangePasswordFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.RESTAURANT_LIST == fragmentType) {

            return new RestaurantListFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.EMPTY_LIST == fragmentType) {

            return new EmptyListFragment();
        } else if (DashboardInterruptListener.FRAGMENT_TYPE.MY_ORDERS == fragmentType) {

            return new MyOrdersFragment();
        }

        // Return a default fragment in case no match found (in else case).
        return new EmptyListFragment();
    }


}

