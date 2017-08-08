package com.app.gofoodie.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @class CacheUtils
 * @desc Utility class to get and save values to cache.
 */
class CacheUtils {

    /**
     * {@link SharedPreferences} Enumerated naming.
     */
    public static enum PREF_NAME {
        PREF_LOGIN
    }

    /**
     * Class private data members.
     */
    private SharedPreferences mPreferences = null;
    private Context mContext = null;
    private PREF_NAME mPref = null;

    private static final CacheUtils ourInstance = new CacheUtils();

    static CacheUtils getInstance() {
        return ourInstance;
    }

    private CacheUtils() {
    }

    /**
     * @param context
     * @param prefName
     * @return SharedPreferences
     * @desc Method to get SharedPreferences from given {@link Context} and {@link PREF_NAME} and save.
     * @@method getPref
     */
    public SharedPreferences getPref(Context context, PREF_NAME prefName) {

        this.mPreferences = initPref(context, prefName);
        this.mContext = context;
        this.mPref = prefName;
        return this.mPreferences;

    }

    /**
     * @return SharedPreferences
     * @desc Method to get SharedPreferences from older saved {@link Context} and {@link PREF_NAME}.
     * @@method getPref
     */
    public SharedPreferences getPref() {

        return this.mPreferences;
    }

    /**
     * @param context
     * @param prefName
     * @return SharedPreferences
     * @desc Private method to init and get {@link SharedPreferences}.
     */
    private SharedPreferences initPref(Context context, PREF_NAME prefName) {


        if (prefName == PREF_NAME.PREF_LOGIN) {

            return context.getSharedPreferences("PREF_NAME", 0);
        } else {

            return null;
        }
    }
}
