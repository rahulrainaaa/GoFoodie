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
     * @param context
     * @param locationId
     * @param locationName
     * @method saveLocation
     * @desc Method to save the location_id and location_name into the preferences.
     */
    public void saveLocation(Context context, String locationId, String locationName) {

        CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).edit().putString(KEY_LOCATION_NAME, locationName).commit();
        CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).edit().putString(KEY_LOCATION_ID, locationId).commit();
    }

    /**
     * @param context
     * @return Location/Area id saved in the preferences.
     * @method getLocationId
     * @desc Method to get the location name saved in the preferences.
     */
    public String getLocationId(Context context, String defVal) {

        return CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).getString(KEY_LOCATION_ID, defVal);
    }


    /**
     * @param context
     * @return Location/Area name saved in the preferences.
     * @method getLocationName
     * @desc Method to get the location name from the cache preferences.
     */
    public String getLocationName(Context context, String defVal) {

        return CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_AREA_LOCATION).getString(KEY_LOCATION_NAME, defVal);
    }

}

