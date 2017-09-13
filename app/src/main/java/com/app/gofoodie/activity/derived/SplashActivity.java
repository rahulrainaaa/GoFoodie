package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.utility.SessionUtils;

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
        SessionUtils.getInstance().loadSession(this);
        showFullScreen();
        mImgSplashLogo = (ImageView) findViewById(R.id.img_splash_logo);
        mHandler = new Handler();
        mHandler.postDelayed(this, 3000);
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
//        startActivity(new Intent(this, EditComboActivity.class));
//        startActivity(new Intent(this, LocationActivity.class));
//        startActivity(new Intent(this, RestaurantProfileActivity.class));
//        startActivity(new Intent(this, MealPreferenceActivity.class));
//        startActivity(new Intent(this, ShortlistedRestaurantsActivity.class));
//        startActivity(new Intent(this, RatingActivity.class));
//        startActivity(new Intent(this, SubscriptionActivity.class));
        finish();
    }
}
