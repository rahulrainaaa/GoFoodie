package com.app.gofoodie.activity.derived;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.handler.dashboardHandler.DashboardFragmentHandler;
import com.app.gofoodie.handler.dashboardHandler.DashboardInterruptListener;
import com.app.gofoodie.utility.SessionUtils;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @class DashboardActivity
 * @desc {@link BaseAppCompatActivity} Activity class to handle the main navigating screen (Dashboard Screen).
 */
@SuppressWarnings("unused")
public class DashboardActivity extends BaseAppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, DashboardInterruptListener {

    public final String TAG = "DashboardActivity";

    /**
     * Class private data members
     */
    private BaseFragment mFragment = null;
    private FragmentManager mFragmentManager = null;
    private BottomNavigationView mNavigationPanel = null;
    private DashboardFragmentHandler mDashboardFragmentHandler = null;
    private FRAGMENT_TYPE mFragmentType = FRAGMENT_TYPE.DASHBOARD;      // Default fragment - Dashboard.

    /**
     * {@link BaseAppCompatActivity} callback methods.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_parent);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        doViewMapping();
        mDashboardFragmentHandler = new DashboardFragmentHandler();
        mFragmentManager = getFragmentManager();
        mNavigationPanel.setOnNavigationItemSelectedListener(this);

        disableShiftMode(mNavigationPanel);
        loadFragmentOnStart();

//        if (getSharedPreferences("welcome", 0).getBoolean("welcome", true)) {
//            startActivity(new Intent(this, AssistantActivity.class));
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mFragment != null) {

            mFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public Toolbar getToolBar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * {@link BottomNavigationView.OnNavigationItemSelectedListener} event callback method.
     *
     * @param item reference
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_dashboard:

                navigationDashboard();
                return true;
            case R.id.navigation_combos:

                navigationCombos();
                return true;
            case R.id.navigation_wallet:

                navigationWallet();
                return true;
            case R.id.navigation_cart:

                navigationCart();
                return true;
            case R.id.navigation_profile:

                navigationProfile();
                return true;
        }
        return false;
    }

    /**
     * Method to handle all the mapping from xml-view-id to corresponding object.
     */
    private void doViewMapping() {

        mNavigationPanel = findViewById(R.id.navigation_panel);
        LinearLayout mFragmentLayout = findViewById(R.id.dashboard_fragment);
    }

    /**
     * Method call on click on Profile in Dashboard/Home navigation panel.
     */
    private void navigationDashboard() {

        mFragmentType = FRAGMENT_TYPE.DASHBOARD;
        loadFragment();
    }

    /**
     * Method call on click on Profile in Combos navigation panel.
     */
    private void navigationCombos() {

        mFragmentType = FRAGMENT_TYPE.SHORTLISTED_RESTAURANTS;
        loadFragment();
    }

    /**
     * Method call on click on Profile in Wallet navigation panel.
     */
    private void navigationWallet() {

        mFragmentType = FRAGMENT_TYPE.WALLET;
        loadFragment();
    }

    /**
     * Method call on click on Profile in Cart navigation panel.
     */
    private void navigationCart() {

        mFragmentType = FRAGMENT_TYPE.CART;
        loadFragment();
    }

    /**
     * Method call on click on Profile in Dashboard navigation panel.
     */
    private void navigationProfile() {

        // Load fragment based on - if session exist or not.
        if (SessionUtils.getInstance().isSessionExist()) {

            mFragmentType = FRAGMENT_TYPE.PROFILE;
        } else {

            mFragmentType = FRAGMENT_TYPE.LOGIN;
        }
        loadFragment();
    }

    /**
     * Method will decide and load fragment on start.
     */
    private void loadFragmentOnStart() {

        loadFragment();
    }

    /**
     * Method to load the set marked Fragment in mFragmentType ENUM.
     */
    private void loadFragment() {

        // Check if fragment has unsaved work before unloading.
        if (mFragment != null) {
            if (mFragment.exitWork()) {
                return;
            }
        }

        // Now begin the fragment transaction.
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        if (!mFragmentTransaction.isEmpty()) {                                      // Remove older fragment if any.
            mFragmentTransaction.remove(mFragment);
        }
        mFragment = mDashboardFragmentHandler.getFragmentClass(mFragmentType);      // Get a new Fragment for dashboard.
        BaseFragment.CURRENT_FRAG = mFragmentType;
        mFragmentTransaction.replace(R.id.dashboard_fragment, mFragment);           // Replace with new fragment in the container.
        mFragmentTransaction.commit();
    }

    /**
     * {@link DashboardInterruptListener} interface callback methods.
     */
    @Override
    public boolean signalLoadFragment(FRAGMENT_TYPE fragmentType) {

        this.mFragmentType = fragmentType;
        loadFragment();
        return true;
    }

    @Override
    public boolean reloadCurrentFragment() {

        loadFragment();
        return true;
    }

    @Override
    public boolean signalMessage(SIGNAL_CODE signalCode) {

        return SIGNAL_CODE.HIDE_NAVIGATION_BAR == signalCode;
    }

    @Override
    public void onBackPressed() {

        if (mFragmentType != FRAGMENT_TYPE.DASHBOARD) {

            signalLoadFragment(FRAGMENT_TYPE.DASHBOARD);
            return;
        }

        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        pDialog.setTitleText("Exit");
        pDialog.setContentText("Do you want to exit?");
        pDialog.setCancelable(false);
        pDialog.setConfirmText("Exit");
        pDialog.setConfirmClickListener(sweetAlertDialog -> {

            sweetAlertDialog.dismissWithAnimation();
            DashboardActivity.this.finish();
        });
        pDialog.setCancelText("Cancel");
        pDialog.setCancelClickListener(SweetAlertDialog::dismissWithAnimation);
        pDialog.show();

    }
}
