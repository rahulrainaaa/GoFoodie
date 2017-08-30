package com.app.gofoodie.activity.utils;

import android.app.Activity;

/**
 * @class GoogleLoginHandler
 * @desc Class for handling the Google login for the application.
 */
public class GoogleLoginHandler {

    /**
     * Class private data members.
     */
    private Activity mActivity = null;

    /**
     * @param activity
     * @constructor GoogleLoginHandler
     */
    public GoogleLoginHandler(Activity activity) {

        this.mActivity = activity;
    }
}
