package com.app.gofoodie.global.data;


import com.app.gofoodie.model.customer.Customer;
import com.app.gofoodie.model.login.Login;

/**
 * @class GlobalData
 * @desc Class to hold all the data within application lifetime & global scope.
 */
public class GlobalData {

    /**
     * Session and user profile related data here.
     */
    public static Login login = null;
    public static Customer customer = null;
    public static boolean isSession = false;


}
