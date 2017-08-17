package com.app.gofoodie.fragment.base;

import android.app.Fragment;

import com.app.gofoodie.activity.derived.DashboardActivity;
import com.app.gofoodie.activity.utils.DashboardInterruptListener;

/**
 * @class BaseFragment
 * @desc Base class for all the Fragment classes for application customization.
 */
public abstract class BaseFragment extends Fragment implements FragmentQuitHandler {

    public static final String TAG = "BaseFragment";

    public static Class<? extends BaseFragment> PREV_FRAG = null;

    private boolean mFlagSaveBeforeExit = false;        //false = nothing; true = something to save.

    /**
     * @param status boolean set/reset flag.
     * @method setFlagSaveBeforeExit
     * @desc Method to set/reset the flag for Fragment.
     */
    protected void setFlagSaveBeforeExit(boolean status) {

        this.mFlagSaveBeforeExit = status;
    }

    /**
     * @return boolean false = unload; true = don't unload.
     * @method exitWork
     * @desc Method need to be called before proceeding fragment unload.
     */
    public boolean exitWork() {

        if (mFlagSaveBeforeExit == true) {
            // Don't unload fragment.
            // Do callback for something to save.
            fragQuitCallback();
            return true;
        }
        return false;
    }

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

    /**
     * {@link Fragment} callback method(s).
     */
    @Override
    public void onDetach() {
        super.onDetach();
        PREV_FRAG = getClass();
    }
}
