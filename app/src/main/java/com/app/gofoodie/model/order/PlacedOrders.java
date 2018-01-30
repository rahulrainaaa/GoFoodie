package com.app.gofoodie.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlacedOrders implements Parcelable {

    public final static Creator<PlacedOrders> CREATOR = new Creator<PlacedOrders>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PlacedOrders createFromParcel(Parcel in) {
            return new PlacedOrders(in);
        }

        public PlacedOrders[] newArray(int size) {
            return (new PlacedOrders[size]);
        }

    };
    @SerializedName("order_set_id")
    @Expose
    private Integer orderSetId;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("price_paid")
    @Expose
    private String pricePaid;
    @SerializedName("order_count")
    @Expose
    private Integer orderCount;
    @SerializedName("delivery_address")
    @Expose
    private String deliveryAddress;
    @SerializedName("geo_lat")
    @Expose
    private String geoLat;
    @SerializedName("geo_lng")
    @Expose
    private String geoLng;

    protected PlacedOrders(Parcel in) {
        this.orderSetId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderDate = ((String) in.readValue((String.class.getClassLoader())));
        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
        this.endDate = ((String) in.readValue((String.class.getClassLoader())));
        this.pricePaid = ((String) in.readValue((String.class.getClassLoader())));
        this.orderCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.deliveryAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLat = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLng = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PlacedOrders() {
    }

    public Integer getOrderSetId() {
        return orderSetId;
    }

    public void setOrderSetId(Integer orderSetId) {
        this.orderSetId = orderSetId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(String pricePaid) {
        this.pricePaid = pricePaid;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
    }

    public String getGeoLng() {
        return geoLng;
    }

    public void setGeoLng(String geoLng) {
        this.geoLng = geoLng;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(orderSetId);
        dest.writeValue(orderDate);
        dest.writeValue(startDate);
        dest.writeValue(endDate);
        dest.writeValue(pricePaid);
        dest.writeValue(orderCount);
        dest.writeValue(deliveryAddress);
        dest.writeValue(geoLat);
        dest.writeValue(geoLng);
    }

    public int describeContents() {
        return 0;
    }

}
