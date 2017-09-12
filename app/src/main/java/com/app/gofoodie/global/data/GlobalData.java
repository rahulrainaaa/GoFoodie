package com.app.gofoodie.global.data;


import com.app.gofoodie.model.login.Login;
import com.app.gofoodie.model.profile.Profile;

/**
 * @class GlobalData
 * @desc Class to hold all the data within application lifetime & global scope.
 */
public class GlobalData {

    /**
     * Session and user profile related data here.
     */
    public static Login login = null;
    public static Profile profile = null;
    public static boolean isSession = false;


}
