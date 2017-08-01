package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;

/**
 * @class DashboardActivity
 * @desc {@link BaseAppCompatActivity} Activity class to handle the main navigating screen (Dashboard Screen).
 */
public class DashboardActivity extends BaseAppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public final String TAG = "DashboardActivity";

    /**
     * Class private data members
     */
    private TextView mTextMessage;

    /**
     * {@link BaseAppCompatActivity} callback methods.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        hideNavigationBar();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
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

                mTextMessage.setText(R.string.navigation_dashboard);
                return true;
            case R.id.navigation_combos:

                mTextMessage.setText(R.string.navigation_combos);
                return true;
            case R.id.navigation_wallet:

                mTextMessage.setText(R.string.navigation_wallet);
                return true;
            case R.id.navigation_cart:

                mTextMessage.setText(R.string.navigation_cart);
                return true;
            case R.id.navigation_profile:

                mTextMessage.setText(R.string.navigation_profile);
                return true;
        }
        return false;
    }
}
