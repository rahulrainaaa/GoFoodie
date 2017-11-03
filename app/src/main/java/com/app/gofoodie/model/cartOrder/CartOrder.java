package com.app.gofoodie.model.cartOrder;

import com.app.gofoodie.model.cart.Cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Android on 11/1/2017.
 */

public class CartOrder {

    public String date = "";
    public String cartItemId;
    public String restaurantName;
    public String restaurantId;
    public String branchId;
    public String comboId;
    public String comboName;
    public String image;
    public String type;
    public String comboPrice;
    public String quantity;
    public List<Description> description = new ArrayList<>();

    public CartOrder(Cart cart) {

        this.cartItemId = cart.cartItemId;
        this.restaurantName = cart.restaurantName;
        this.branchId = cart.branchId;
        this.restaurantId = cart.restaurantId;
        this.comboId = cart.comboId;
        this.comboName = cart.comboName;
        this.image = cart.image;
        this.type = cart.type;
        this.comboPrice = cart.comboPrice;
        this.quantity = cart.quantity;

        description.clear();
        Iterator<com.app.gofoodie.model.cart.Description> descriptionIterator = cart.description.iterator();
        while (descriptionIterator.hasNext()) {

            this.description.add(new Description(descriptionIterator.next()));
        }
    }

}
