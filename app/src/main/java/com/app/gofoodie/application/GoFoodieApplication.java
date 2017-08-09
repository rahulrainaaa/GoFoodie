package com.app.gofoodie.application;

import android.app.Application;

import com.app.gofoodie.network.handler.NetworkHandler;

/**
 * @class GoFoodieApplication
 * @desc Application class for this application.
 */
public class GoFoodieApplication extends Application {

    public static final String TAG = "GoFoodieApplication";

    /**
     * {@link Application} class lifecycle callbacks.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Init Network Handler for Application.
        NetworkHandler.initRequestQueue(getApplicationContext());
    }
}
