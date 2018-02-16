package com.app.gofoodie.utility;

import android.content.Context;

/**
 * @class LocationUtils
 * @desc Singleton class to handle the location based preferences.
 */
public class LocationUtils {
    private static final LocationUtils ourInstance = new LocationUtils();
    /**
     * Location preferences key fields.
     */
    public final String KEY_LOCATION_NAME = "KL_NAME";
    public final String KEY_LOCATION_ID = "KL_ID";

    private LocationUtils() {
    }

    public static LocationUtils getInstance() {
        return ourInstance;
    }

    /**
     * Method to save the location_id and location_name into the preferences.
     *
     * @param context      reference
     * @param locationId   reference
     * @param locationName reference
     */
    public void saveLocation(Context context, String locationId, String locationName) {

        CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).edit().putString(KEY_LOCATION_NAME, locationName).apply();
        CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).edit().putString(KEY_LOCATION_ID, locationId).apply();
    }

    /**
     * Method to get the location name saved in the preferences.
     *
     * @param context reference
     * @return Location/Area id saved in the preferences.
     */
    public String getLocationId(Context context, String defVal) {

        return CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).getString(KEY_LOCATION_ID, defVal);
    }


    /**
     * Method to get the location name from the cache preferences.
     *
     * @param context reference
     * @return Location/Area name saved in the preferences.
     */
    public String getLocationName(Context context, String defVal) {

        return CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).getString(KEY_LOCATION_NAME, defVal);
    }

    /**
     * Method to remove and reset the Location preference cache data.
     *
     * @param context reference
     */
    public void resetLocationPref(Context context) {

        CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).edit().remove(KEY_LOCATION_ID).apply();
        CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).edit().remove(KEY_LOCATION_NAME).apply();
    }

}

