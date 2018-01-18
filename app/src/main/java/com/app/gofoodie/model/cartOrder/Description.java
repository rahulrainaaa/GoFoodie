
package com.app.gofoodie.model.cartOrder;

import java.util.ArrayList;

public class Description {

    public String itemId;

    public String name;

    public String value;

    public ArrayList<String> options = null;

    public Description(com.app.gofoodie.model.cart.Description description) {
        this.itemId = description.getItemId();
        this.name = description.getName();
        this.value = description.getValue();
        this.options = (ArrayList<String>) ((ArrayList<String>) description.getOptions()).clone();
    }

}
