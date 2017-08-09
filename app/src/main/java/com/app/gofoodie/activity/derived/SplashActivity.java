package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;

/**
 * @class SplashActivity
 * @desc Activity Class to show Splash screen on the application start.
 */
public class SplashActivity extends BaseAppCompatActivity implements Runnable {

    public static final String TAG = "SplashActivity";

    /**
     * Class private data objects.
     */
    private Handler mHandler = null;
    private ImageView mImgSplashLogo = null;

    /**
     * {@link BaseAppCompatActivity} callback method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showFullScreen();
        mImgSplashLogo = (ImageView) findViewById(R.id.img_splash_logo);
        mHandler = new Handler();
        mHandler.postDelayed(this, 2000);
    }

    @Override
    protected void onPause() {
        mHandler.removeCallbacks(this);
        finish();
        super.onPause();
    }

    /**
     * {@link Runnable} interface callback method.
     */
    @Override
    public void run() {

        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }
}
