package com.app.gofoodie.global.data;


import com.app.gofoodie.model.cart.Cart;
import com.app.gofoodie.model.rechargePlan.Subscriptionplan;

import java.util.ArrayList;

/**
 * @class GlobalData
 * @desc Class to hold all the data within application lifetime & global scope.
 */
public class GlobalData {

    public static String newSocialEmail = "";                   // Take email from social login fragment to register new fragment.
    public static Subscriptionplan subscriptionplan = null;

    public static ArrayList<Cart> cartArrayList = null;         //ArrayList share from cart to order-cart.
}
