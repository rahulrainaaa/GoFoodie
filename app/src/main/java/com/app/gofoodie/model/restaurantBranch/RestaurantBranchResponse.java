package com.app.gofoodie.model.restaurantBranch;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantBranchResponse extends BaseModel implements Parcelable {

    public final static Creator<RestaurantBranchResponse> CREATOR = new Creator<RestaurantBranchResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RestaurantBranchResponse createFromParcel(Parcel in) {
            return new RestaurantBranchResponse(in);
        }

        public RestaurantBranchResponse[] newArray(int size) {
            return (new RestaurantBranchResponse[size]);
        }

    };
    @SerializedName("restaurant_branch")
    @Expose
    private RestaurantBranch restaurantBranch;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;

    protected RestaurantBranchResponse(Parcel in) {
        this.restaurantBranch = ((RestaurantBranch) in.readValue((RestaurantBranch.class.getClassLoader())));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RestaurantBranchResponse() {
    }

    public RestaurantBranch getRestaurantBranch() {
        return restaurantBranch;
    }

    public void setRestaurantBranch(RestaurantBranch restaurantBranch) {
        this.restaurantBranch = restaurantBranch;
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
        dest.writeValue(restaurantBranch);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
