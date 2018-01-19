package com.app.gofoodie.activity.derived;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.assistActivity.AssistantActivity;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.handler.dashboardHandler.DashboardFragmentHandler;
import com.app.gofoodie.handler.dashboardHandler.DashboardInterruptListener;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.utility.SessionUtils;

/**
 * @class DashboardActivity
 * @desc {@link BaseAppCompatActivity} Activity class to handle the main navigating screen (Dashboard Screen).
 */
public class DashboardActivity extends BaseAppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, DashboardInterruptListener {

    public final String TAG = "DashboardActivity";

    /**
     * Class private data members
     */
    private BaseFragment mFragment = null;
    private LinearLayout mFragmentLayout = null;
    private FragmentTransaction mFragmentTransaction = null;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomerProfileHandler customerProfileHandler = new CustomerProfileHandler(this);
        customerProfileHandler.getFullProfile();

        doViewMapping();
        mDashboardFragmentHandler = new DashboardFragmentHandler();
        mFragmentManager = getFragmentManager();
        mNavigationPanel.setOnNavigationItemSelectedListener(this);
        loadFragmentOnStart();

        if (getSharedPreferences("welcome", 0).getBoolean("welcome", true)) {
            // true (default) = show welcome screen.

            startActivity(new Intent(this, AssistantActivity.class));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //hideNavigationBar();
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
     * @param item
     * @return boolean
     * @method onNavigationItemSelected
     * @desc {@link BottomNavigationView.OnNavigationItemSelectedListener} event callback method.
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
     * @method doViewMapping
     * @desc Method to handle all the mapping from xml-view-id to corresponding object.
     */
    private void doViewMapping() {

        mNavigationPanel = (BottomNavigationView) findViewById(R.id.navigation_panel);
        mFragmentLayout = (LinearLayout) findViewById(R.id.dashboard_fragment);
    }

    /**
     * @method navigationDashboard
     * @desc Method call on click on Profile in Dashboard/Home navigation panel.
     */
    private void navigationDashboard() {

        mFragmentType = FRAGMENT_TYPE.DASHBOARD;
        loadFragment();
    }

    /**
     * @method navigationCombos
     * @desc Method call on click on Profile in Combos navigation panel.
     */
    private void navigationCombos() {

        mFragmentType = FRAGMENT_TYPE.SHORTLISTED_RESTAURANTS;
        loadFragment();
    }

    /**
     * @method navigationWallet
     * @desc Method call on click on Profile in Wallet navigation panel.
     */
    private void navigationWallet() {

        mFragmentType = FRAGMENT_TYPE.WALLET;
        loadFragment();
    }

    /**
     * @method navigationCart
     * @desc Method call on click on Profile in Cart navigation panel.
     */
    private void navigationCart() {

        mFragmentType = FRAGMENT_TYPE.CART;
        loadFragment();
    }

    /**
     * @method navigationProfile
     * @desc Method call on click on Profile in Dashboard navigation panel.
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
     * @method loadFragmentOnStart
     * @desc Method will decide and load fragment on start.
     */
    private void loadFragmentOnStart() {

        loadFragment();
    }

    /**
     * @method loadFragment
     * @desc Method to load the set marked Fragment in mFragmentType ENUM.
     */
    private void loadFragment() {

        // Check if fragment has unsaved work before unloading.
        if (mFragment != null) {
            if (mFragment.exitWork()) {
                return;
            }
        }

        // Now begin the fragment transaction.
        mFragmentTransaction = mFragmentManager.beginTransaction();                 // Begin with fragment transaction.
        if (!mFragmentTransaction.isEmpty()) {                                      // Remove older fragment if any.
            mFragmentTransaction.remove(mFragment);
        }
        mFragment = mDashboardFragmentHandler.getFragmentClass(mFragmentType);      // Get a new Fragment for dashboard.
        mFragment.CURRENT_FRAG = mFragmentType;
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

        if (SIGNAL_CODE.HIDE_NAGIVATION_BAR == signalCode) {

            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Alert");
        builder.setMessage("Do you want to exit?");
        builder.setIcon(R.drawable.icon_error_alert);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Exit from application.
                dialog.dismiss();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Dont exit. Remain same as it is.
                dialog.dismiss();
            }
        });
        builder.show();

    }
}
