package com.app.gofoodie.global.constants;


import com.app.gofoodie.R;

/**
 * @class Constants
 * @desc Constants class to hold all the global constants within the application.
 */
public class Constants {

    /**
     * Dashboard Screen
     */
    public static final String[] BANNER_IMAGES = new String[]{

            "http://gofoodie.drushtiindia.com/homeassets/images/Banner1.png",
            "http://gofoodie.drushtiindia.com/homeassets/images/Banner2.png",
            "http://gofoodie.drushtiindia.com/homeassets/images/Banner3.png",
            "http://gofoodie.drushtiindia.com/homeassets/images/Banner4.png"
    };

    public static final int[] WELCOME_SCREEN_IMAGES = {
            R.drawable.welcome_a,
            R.drawable.welcome_b,
            R.drawable.welcome_c
    };

    public static final String ADMIN_PHONE_NUMBER = "0987654321";
    public static final String ADMIN_EMAIL = "info@gofoodie.com";

    public static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String REGEX_MOBILE = "^[0-9]{10,15}$";
    public static final String REGEX_NAME = "^[\\p{L} .'-]+$";

}
