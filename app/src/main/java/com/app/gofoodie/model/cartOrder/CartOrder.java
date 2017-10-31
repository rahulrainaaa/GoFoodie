package com.app.gofoodie.model.cartOrder;

import com.app.gofoodie.model.cart.Cart;
import com.app.gofoodie.model.cart.Description;

import java.util.List;

/**
 * Created by Android on 11/1/2017.
 */

public class CartOrder {

    public String date = "";

    public String restaurantName;
    public String comboId;
    public String comboName;
    public String image;
    public String type;
    public String comboPrice;
    public String quantity;
    public List<Description> description = null;

    public CartOrder(Cart cart) {

        this.restaurantName = cart.restaurantName;
        this.comboId = cart.comboId;
        this.comboName = cart.comboName;
        this.image = cart.image;
        this.type = cart.type;
        this.comboPrice = cart.comboPrice;
        this.quantity = cart.quantity;
        this.description = cart.description;
    }

}
