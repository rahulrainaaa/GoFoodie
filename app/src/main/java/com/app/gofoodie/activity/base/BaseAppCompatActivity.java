package com.app.gofoodie.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.gofoodie.customview.GoFoodieProgressDialog;
import com.app.gofoodie.model.login.Login;
import com.app.gofoodie.utility.SessionUtils;

/**
 * @class BaseAppCompatActivity
 * @desc {@link AppCompatActivity} Base class for application customization.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    public final String TAG = "BaseAppCompatActivity";

    private GoFoodieProgressDialog mGoFoodieProgressDialog = null;

    /**
     * {@link AppCompatActivity} override callback method(s).
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoFoodieProgressDialog = new GoFoodieProgressDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoFoodieProgressDialog.dismiss();
    }

    /**
     * @return GoFoodieProgressDialog
     * @method getProgressDialog
     * @desc Method to get the progress dialog for the activity.
     */
    public GoFoodieProgressDialog getProgressDialog() {

        return this.mGoFoodieProgressDialog;
    }

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
        Runtime.getRuntime().gc();
    }

    /**
     * @return
     * @method getSession
     * @desc Method to fetch the current session within the activity.
     */
    protected Login getSession() {

        return SessionUtils.getInstance().getSession();
    }

}
