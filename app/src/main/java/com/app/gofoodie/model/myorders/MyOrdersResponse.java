
package com.app.gofoodie.model.myorders;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyOrdersResponse extends BaseModel implements Parcelable {

    @SerializedName("my_orders")
    @Expose
    public List<MyOrder> myOrders = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Creator<MyOrdersResponse> CREATOR = new Creator<MyOrdersResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MyOrdersResponse createFromParcel(Parcel in) {
            return new MyOrdersResponse(in);
        }

        public MyOrdersResponse[] newArray(int size) {
            return (new MyOrdersResponse[size]);
        }

    };

    protected MyOrdersResponse(Parcel in) {
        in.readList(this.myOrders, (MyOrder.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MyOrdersResponse() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(myOrders);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
