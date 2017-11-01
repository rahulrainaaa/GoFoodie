
package com.app.gofoodie.model.cart;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart implements Parcelable
{

    @SerializedName("cart_item_id")
    @Expose
    public String cartItemId;
    @SerializedName("restaurantName")
    @Expose
    public String restaurantName;
    @SerializedName("restaurant_id")
    @Expose
    public String restaurantId;
    @SerializedName("branch_id")
    @Expose
    public String branchId;
    @SerializedName("combo_id")
    @Expose
    public String comboId;
    @SerializedName("comboName")
    @Expose
    public String comboName;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("comboPrice")
    @Expose
    public String comboPrice;
    @SerializedName("quantity")
    @Expose
    public String quantity;
    @SerializedName("description")
    @Expose
    public List<Description> description = null;
    public final static Creator<Cart> CREATOR = new Creator<Cart>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        public Cart[] newArray(int size) {
            return (new Cart[size]);
        }

    }
    ;

    protected Cart(Parcel in) {
        this.cartItemId = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantName = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantId = ((String) in.readValue((String.class.getClassLoader())));
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.comboId = ((String) in.readValue((String.class.getClassLoader())));
        this.comboName = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.comboPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.quantity = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.description, (Description.class.getClassLoader()));
    }

    public Cart() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cartItemId);
        dest.writeValue(restaurantName);
        dest.writeValue(restaurantId);
        dest.writeValue(branchId);
        dest.writeValue(comboId);
        dest.writeValue(comboName);
        dest.writeValue(image);
        dest.writeValue(type);
        dest.writeValue(comboPrice);
        dest.writeValue(quantity);
        dest.writeList(description);
    }

    public int describeContents() {
        return  0;
    }

}
