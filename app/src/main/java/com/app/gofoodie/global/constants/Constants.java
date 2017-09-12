package com.app.gofoodie.global.constants;


/**
 * @class Constants
 * @desc Constants class to hold all the global constants within the application.
 */
public class Constants {

    public static final String TAG = "Constants";

    /**
     * Dashboard Screen
     */
    public static String[] BANNER_IMAGES = new String[]{

            "http://gofoodie.drushtiindia.com/homeassets/images/Banner1.png",
            "http://gofoodie.drushtiindia.com/homeassets/images/Banner2.png",
            "http://gofoodie.drushtiindia.com/homeassets/images/Banner3.png",
            "http://gofoodie.drushtiindia.com/homeassets/images/Banner4.png"
    };

    /**
     * KEY for saving the Area location in preference.
     */
    public static enum PREF_AREA_LOCATION {
        ID("id", 1), NAME("name", 2);

        private int id;
        private String value;

        private PREF_AREA_LOCATION(String value, int id) {
            this.value = value;
            this.id = id;
        }
    }


}
