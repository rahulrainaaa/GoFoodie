package com.app.gofoodie.global.constants;


import com.app.gofoodie.R;

/**
 * @class Constants
 * @desc Constants class to hold all the global constants within the application.
 */
public class Constants {

    public static final String TAG = "Constants";

    /**
     * Dashboard Screen
     */
    public static final String[] BANNER_IMAGES = new String[]{

            "http://gofoodie.drushtiindia.com/homeassets/images/Banner1.png",
            "http://gofoodie.drushtiindia.com/homeassets/images/Banner2.png",
            "http://gofoodie.drushtiindia.com/homeassets/images/Banner3.png",
            "http://gofoodie.drushtiindia.com/homeassets/images/Banner4.png"
    };
    public static final String[] WELCOME_TITLE_TEXT = new String[]{

            "TITLE TEXT 1",
            "TITLE TEXT 2",
            "TITLE TEXT 3"
    };

    public static final String[] WELCOME_COMMENT_TEXT = new String[]{
            "Some description for title 1.",
            "Some description for title 2",
            "Some description for title 3"
    };

    public static final int[] WELCOME_SCREEN_IMAGES = {
            R.drawable.gf_logo,
            R.drawable.gf_logo,
            R.drawable.gf_logo
    };

    public static String ADMIN_PHONE_NUMBER = "0987654321";
    public static String ADMIN_EMAIL = "info@gofoodie.com";

}
