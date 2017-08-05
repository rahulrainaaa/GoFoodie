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
     * @return DashboardActivity object
     * @method getDashboardActivity
     * @desc Method to get the {@link DashboardActivity} activity instance.
     */
    protected DashboardActivity getDashboardActivity() {

        return ((DashboardActivity) getActivity());
    }

    /**
     * @return DashboardInterruptListener object
     * @method getDashboardInterruptListener
     * @desc Method to get the {@link DashboardInterruptListener} activity instance.
     */
    protected DashboardInterruptListener getDashboardInterruptListener() {

        return ((DashboardInterruptListener) getActivity());
    }
}
