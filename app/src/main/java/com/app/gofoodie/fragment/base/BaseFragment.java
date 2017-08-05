package com.app.gofoodie.fragment.base;

import android.app.Fragment;

import com.app.gofoodie.activity.derived.DashboardActivity;
import com.app.gofoodie.activity.utils.DashboardInterruptListener;

/**
 * @class BaseFragment
 * @desc Base class for all the Fragment classes for application customization.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * Private class data member objects.
     */
    private DashboardActivity mDashboardActivity = null;
    private DashboardInterruptListener mDashboardInterruptListener = null;

    /**
     * @constructor BaseFragment
     */
    private BaseFragment() {

        this.mDashboardActivity = initDashboardActivity();
        this.mDashboardInterruptListener = initDashboardInterruptListener();
    }

    /**
     * @return DashboardActivity object
     * @method initDashboardActivity
     * @desc Method to init the {@link DashboardActivity} activity instance.
     */
    private DashboardActivity initDashboardActivity() {

        if (getActivity() instanceof DashboardActivity) {
            return ((DashboardActivity) getActivity());
        }
        return null;
    }

    /**
     * @return DashboardInterruptListener object
     * @method initDashboardInterruptListener
     * @desc Method to init the {@link DashboardInterruptListener} activity instance.
     */
    private DashboardInterruptListener initDashboardInterruptListener() {

        if (getActivity() instanceof DashboardInterruptListener) {
            return ((DashboardInterruptListener) getActivity());
        }
        return null;
    }

    /**
     * @return DashboardActivity object
     * @method getDashboardActivity
     * @desc Method to get the {@link DashboardActivity} activity instance.
     */
    private DashboardActivity getDashboardActivity() {

        if (getActivity() instanceof DashboardActivity) {
            return ((DashboardActivity) getActivity());
        }
        return null;
    }

    /**
     * @return DashboardInterruptListener object
     * @method getDashboardInterruptListener
     * @desc Method to get the {@link DashboardInterruptListener} activity instance.
     */
    private DashboardInterruptListener getDashboardInterruptListener() {

        if (getActivity() instanceof DashboardInterruptListener) {
            return ((DashboardInterruptListener) getActivity());
        }
        return null;
    }
}
