package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.squareup.picasso.Picasso;

/**
 * @class SplashActivity
 * @desc Activity Class to show Splash screen on the application start.
 */
public class SplashActivity extends BaseAppCompatActivity implements Runnable {

    public final String TAG = "SplashActivity";

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
        //mHandler.postDelayed(this, 2000);
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

        // Toast.makeText(this, "Runnable splash toast...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }
}
