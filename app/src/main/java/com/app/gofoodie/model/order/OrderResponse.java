package com.app.gofoodie.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse extends BaseModel implements Parcelable {

    public final static Creator<OrderResponse> CREATOR = new Creator<OrderResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderResponse createFromParcel(Parcel in) {
            return new OrderResponse(in);
        }

        public OrderResponse[] newArray(int size) {
            return (new OrderResponse[size]);
        }

    };
    @SerializedName("placedOrders")
    @Expose
    private PlacedOrders placedOrders;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;

    protected OrderResponse(Parcel in) {
        this.placedOrders = ((PlacedOrders) in.readValue((PlacedOrders.class.getClassLoader())));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderResponse() {
    }

    public PlacedOrders getPlacedOrders() {
        return placedOrders;
    }

    public void setPlacedOrders(PlacedOrders placedOrders) {
        this.placedOrders = placedOrders;
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
        dest.writeValue(placedOrders);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
