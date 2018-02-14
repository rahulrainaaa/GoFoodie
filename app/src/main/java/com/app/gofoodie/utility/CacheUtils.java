package com.app.gofoodie.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @class CacheUtils
 * @desc Utility class to get and save values to cache.
 */
public class CacheUtils {

    /**
     * {@link SharedPreferences} Preference naming.
     */
    public enum PREF_NAME {
        PREF_LOGIN, PREF_MEAL, PREF_AREA_LOCATION
    }

    /**
     * Preference Key(s).
     */
    public static final String PREF_KEY = "PREF_KEY";
    public static final String PREF_MEAL_CUISINE_KEY = "PREF_MEAL_CUISINE_KEY";
    public static final String PREF_MEAL_TYPE_KEY = "PREF_MEAL_TYPE_KEY";

    /**
     * Class private data members.
     */
    private SharedPreferences mPreferences = null;

    private static final CacheUtils ourInstance = new CacheUtils();

    public static CacheUtils getInstance() {
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
     * @desc Private method to init, save pref-detail and get {@link SharedPreferences}.
     */
    private SharedPreferences initPref(Context context, PREF_NAME prefName) {

        if (prefName == PREF_NAME.PREF_LOGIN) {

            return context.getSharedPreferences("PREF_LOGIN", 0);
        } else if (prefName == PREF_NAME.PREF_MEAL) {

            return context.getSharedPreferences("PREF_MEAL", 0);
        } else if (prefName == PREF_NAME.PREF_AREA_LOCATION) {

            return context.getSharedPreferences("PREF_AREA_LOCATION", 0);
        } else {

            return null;
        }
    }
}
