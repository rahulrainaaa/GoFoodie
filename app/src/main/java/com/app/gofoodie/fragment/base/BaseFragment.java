package com.app.gofoodie.fragment.base;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.app.gofoodie.activity.derived.DashboardActivity;
import com.app.gofoodie.handler.dashboardHandler.DashboardInterruptListener;
import com.app.gofoodie.model.login.Login;
import com.app.gofoodie.utility.SessionUtils;

import java.util.Stack;

/**
 * @class BaseFragment
 * @desc Base class for all the Fragment classes for application customization.
 */
public abstract class BaseFragment extends Fragment implements FragmentQuitHandler {

    public static final String TAG = "BaseFragment";

    /**
     * Class public data member(s).
     */
    public static DashboardInterruptListener.FRAGMENT_TYPE CURRENT_FRAG = null;

    /**
     * Class private data member(s).
     */
    private static Stack<DashboardInterruptListener.FRAGMENT_TYPE> FRAG_STACK = new Stack<>();
    private boolean flagPushIntoStack = true;       // If this has to push into stack (Forward flow).
    private boolean mFlagSaveBeforeExit = false;        //false = nothing; true = something to save (Do not exit).

    /**
     * @param status boolean set/reset flag.
     * @method setExitFlag
     * @desc Method to set/reset the flag for Fragment.
     */
    protected void setExitFlag(boolean status) {

        this.mFlagSaveBeforeExit = status;
    }

    /**
     * @return boolean false = unload; true = don't unload.
     * @method exitWork
     * @desc Method need to be called before proceeding fragment unload (final = important to be called).
     */
    public final boolean exitWork() {

        if (mFlagSaveBeforeExit) {
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
     * @return AppCompatActivity
     * @method getFragmentActivity
     * @desc Method to get the instance of {@link AppCompatActivity} of parent activity, from fragment.
     */
    public AppCompatActivity getFragmentActivity() {

        return (AppCompatActivity) getActivity();
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

        /**
         * Forward flowing process will be pushed into stack.
         * Backward flow will be popped. It will never be pushed into stack.
         */
        if (flagPushIntoStack) {
            FRAG_STACK.push(CURRENT_FRAG);
        }
    }

    /**
     * @return
     * @method getSession
     * @desc Method to fetch the current session within the fragment.
     */
    protected Login getSession() {

        return SessionUtils.getInstance().getSession();
    }
}
