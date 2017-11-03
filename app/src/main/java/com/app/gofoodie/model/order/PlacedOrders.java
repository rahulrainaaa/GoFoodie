
package com.app.gofoodie.model.order;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlacedOrders implements Parcelable
{

    @SerializedName("order_set_id")
    @Expose
    public Integer orderSetId;
    @SerializedName("order_date")
    @Expose
    public String orderDate;
    @SerializedName("start_date")
    @Expose
    public String startDate;
    @SerializedName("end_date")
    @Expose
    public String endDate;
    @SerializedName("price_paid")
    @Expose
    public Integer pricePaid;
    @SerializedName("order_count")
    @Expose
    public Integer orderCount;
    @SerializedName("delivery_address")
    @Expose
    public String deliveryAddress;
    @SerializedName("geo_lat")
    @Expose
    public Integer geoLat;
    @SerializedName("geo_lng")
    @Expose
    public Integer geoLng;
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

    }
    ;

    protected PlacedOrders(Parcel in) {
        this.orderSetId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderDate = ((String) in.readValue((String.class.getClassLoader())));
        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
        this.endDate = ((String) in.readValue((String.class.getClassLoader())));
        this.pricePaid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.deliveryAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLat = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.geoLng = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public PlacedOrders() {
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
        return  0;
    }

}
