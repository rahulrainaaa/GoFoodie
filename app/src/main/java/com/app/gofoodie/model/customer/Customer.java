package com.app.gofoodie.model.customer;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer extends BaseModel implements Parcelable {

    public final static Creator<Customer> CREATOR = new Creator<Customer>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        public Customer[] newArray(int size) {
            return (new Customer[size]);
        }

    };
    @SerializedName("profile")
    @Expose
    private Profile profile;
    @SerializedName("cart_count")
    @Expose
    private Integer cartCount;
    @SerializedName("order_count")
    @Expose
    private Integer orderCount;
    @SerializedName("min_combo_order")
    @Expose
    private Integer minComboOrder;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;

    protected Customer(Parcel in) {
        this.profile = ((Profile) in.readValue((Profile.class.getClassLoader())));
        this.cartCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.minComboOrder = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Customer() {
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getMinComboOrder() {
        return minComboOrder;
    }

    public void setMinComboOrder(Integer minComboOrder) {
        this.minComboOrder = minComboOrder;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(profile);
        dest.writeValue(cartCount);
        dest.writeValue(orderCount);
        dest.writeValue(minComboOrder);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
