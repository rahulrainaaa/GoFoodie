package com.app.gofoodie.activity.derived;

import android.os.Bundle;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;

/**
 * @class SplashActivity
 * @desc Activity Class to show Splash screen on the application start.
 */
public class SplashActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
