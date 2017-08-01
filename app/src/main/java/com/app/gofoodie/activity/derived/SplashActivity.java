package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;

/**
 * @class SplashActivity
 * @desc Activity Class to show Splash screen on the application start.
 */
public class SplashActivity extends BaseAppCompatActivity implements Runnable {

    /**
     * Class private data objects.
     */
    private Handler mHandler = null;

    /**
     * {@link BaseAppCompatActivity} callback method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        showFullScreen();
        mHandler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(this, 500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(this);
    }

    /**
     * {@link Runnable} interface callback method.
     */
    @Override
    public void run() {
        Toast.makeText(this, "Runnable splash toast...", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(this, DashboardActivity.class);
//        finish();
    }
}
