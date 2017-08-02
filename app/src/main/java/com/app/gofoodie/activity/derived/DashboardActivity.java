package com.app.gofoodie.activity.derived;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.activity.utils.DashboardInterruptListener;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.fragment.derived.ProfileFragment;

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

    /**
     * {@link BaseAppCompatActivity} callback methods.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        hideNavigationBar();
        doViewMapping();
        mFragmentManager = getFragmentManager();
        mNavigationPanel.setOnNavigationItemSelectedListener(this);
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

    private void navigationDashboard() {

        loadFragment();
        Toast.makeText(this, "dashboard clicked", Toast.LENGTH_SHORT).show();
    }

    private void navigationCombos() {

        loadFragment();
        Toast.makeText(this, "combos clicked", Toast.LENGTH_SHORT).show();
    }

    private void navigationWallet() {

        loadFragment();
        Toast.makeText(this, "wallet clicked", Toast.LENGTH_SHORT).show();
    }

    private void navigationCart() {

        loadFragment();
        Toast.makeText(this, "cart clicked", Toast.LENGTH_SHORT).show();
    }

    private void navigationProfile() {

        loadFragment();
        Toast.makeText(this, "profile clicked", Toast.LENGTH_SHORT).show();
    }

    private void loadFragment() {

        mFragmentTransaction = mFragmentManager.beginTransaction();          // Begin with fragment transaction.
        if (!mFragmentTransaction.isEmpty()) {                               // Remove older fragment if any.
            mFragmentTransaction.remove(mFragment);
        }
        mFragment = new ProfileFragment();                                   // Get a new Fragment for dashboard.
        mFragmentTransaction.replace(R.id.dashboard_fragment, mFragment);    // Replace with new fragment in the container.
        mFragmentTransaction.commit();
    }


    @Override
    public boolean interruptLoadFragment(FRAGMENT_TYPE fragmentType) {



        return false;
    }
}
