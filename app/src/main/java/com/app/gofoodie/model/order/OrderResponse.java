
package com.app.gofoodie.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model Class hold response after placing an order. Data contains all the Invoice related details.
 */
public class OrderResponse extends BaseModel implements Parcelable {

    @SerializedName("placedOrders")
    @Expose
    public PlacedOrders placedOrders;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
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

    protected OrderResponse(Parcel in) {
        this.placedOrders = ((PlacedOrders) in.readValue((PlacedOrders.class.getClassLoader())));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderResponse() {
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
