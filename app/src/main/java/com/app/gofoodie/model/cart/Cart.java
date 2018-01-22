package com.app.gofoodie.model.cart;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart implements Parcelable {

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

    };
    @SerializedName("cart_item_id")
    @Expose
    private String cartItemId;
    @SerializedName("zone_shipping_charge")
    @Expose
    private String zoneShippingCharge;
    @SerializedName("restaurantName")
    @Expose
    private String restaurantName;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("combo_id")
    @Expose
    private String comboId;
    @SerializedName("comboName")
    @Expose
    private String comboName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("comboPrice")
    @Expose
    private String comboPrice;
    @SerializedName("pay_price")
    @Expose
    private String payPrice;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("description")
    @Expose
    private List<Description> description = null;

    protected Cart(Parcel in) {
        this.cartItemId = ((String) in.readValue((String.class.getClassLoader())));
        this.zoneShippingCharge = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantName = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantId = ((String) in.readValue((String.class.getClassLoader())));
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.comboId = ((String) in.readValue((String.class.getClassLoader())));
        this.comboName = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.comboPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.payPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.quantity = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.description, (Description.class.getClassLoader()));
    }

    public Cart() {
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getZoneShippingCharge() {
        return zoneShippingCharge;
    }

    public void setZoneShippingCharge(String zoneShippingCharge) {
        this.zoneShippingCharge = zoneShippingCharge;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(String comboPrice) {
        this.comboPrice = comboPrice;
    }

    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<Description> getDescription() {
        return description;
    }

    public void setDescription(List<Description> description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cartItemId);
        dest.writeValue(zoneShippingCharge);
        dest.writeValue(restaurantName);
        dest.writeValue(restaurantId);
        dest.writeValue(branchId);
        dest.writeValue(comboId);
        dest.writeValue(comboName);
        dest.writeValue(image);
        dest.writeValue(type);
        dest.writeValue(comboPrice);
        dest.writeValue(payPrice);
        dest.writeValue(quantity);
        dest.writeList(description);
    }

    public int describeContents() {
        return 0;
    }

}
