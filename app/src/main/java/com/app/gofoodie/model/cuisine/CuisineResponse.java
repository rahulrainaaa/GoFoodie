package com.app.gofoodie.model.cuisine;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CuisineResponse extends BaseModel implements Parcelable {

    public final static Parcelable.Creator<CuisineResponse> CREATOR = new Creator<CuisineResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CuisineResponse createFromParcel(Parcel in) {
            return new CuisineResponse(in);
        }

        public CuisineResponse[] newArray(int size) {
            return (new CuisineResponse[size]);
        }

    };
    @SerializedName("cuisine")
    @Expose
    private List<Cuisine> cuisine = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;

    protected CuisineResponse(Parcel in) {
        in.readList(this.cuisine, (com.app.gofoodie.model.cuisine.Cuisine.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CuisineResponse() {
    }

    public List<Cuisine> getCuisine() {
        return cuisine;
    }

    public void setCuisine(List<Cuisine> cuisine) {
        this.cuisine = cuisine;
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
        dest.writeList(cuisine);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}