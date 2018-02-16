package com.app.gofoodie.model.restaurant;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Response model class to hold the information of branche(s) in array list.
 * Holds multiple branch information in array.
 */
@SuppressWarnings("unused")
public class RestaurantResponse extends BaseModel implements Parcelable {

    public final static Creator<RestaurantResponse> CREATOR = new Creator<RestaurantResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RestaurantResponse createFromParcel(Parcel in) {
            return new RestaurantResponse(in);
        }

        public RestaurantResponse[] newArray(int size) {
            return (new RestaurantResponse[size]);
        }

    };
    @SerializedName("restaurant")
    @Expose
    public final List<Restaurant> restaurant = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;

    protected RestaurantResponse(Parcel in) {
        in.readList(this.restaurant, (Restaurant.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RestaurantResponse() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(restaurant);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
