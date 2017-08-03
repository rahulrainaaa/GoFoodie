package com.app.gofoodie.activity.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @class BaseAppCompatActivity
 * @desc {@link AppCompatActivity} Base class for application customization.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    public final String TAG = "BaseAppCompatActivity";

    /**
     * @method showFullScreen
     * @desc Method to show full screen by hiding title, navigation and status bar.
     */
    protected void showFullScreen() {

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * @method hideNavigationBar
     * @desc Method to hide the navigation bar within the activity.
     */
    protected void hideNavigationBar() {

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.gc();
        System.gc();
    }

}
