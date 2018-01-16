package com.app.gofoodie.model.cuisine;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuisine implements Parcelable {

    public final static Parcelable.Creator<Cuisine> CREATOR = new Creator<Cuisine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cuisine createFromParcel(Parcel in) {
            return new Cuisine(in);
        }

        public Cuisine[] newArray(int size) {
            return (new Cuisine[size]);
        }

    };
    public boolean isChecked = false;
    @SerializedName("cuisine_id")
    @Expose
    private String cuisineId;
    @SerializedName("cuisine_name")
    @Expose
    private String cuisineName;
    @SerializedName("cate_id")
    @Expose
    private String cateId;

    protected Cuisine(Parcel in) {
        this.cuisineId = ((String) in.readValue((String.class.getClassLoader())));
        this.cuisineName = ((String) in.readValue((String.class.getClassLoader())));
        this.cateId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Cuisine() {
    }

    public String getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(String cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cuisineId);
        dest.writeValue(cuisineName);
        dest.writeValue(cateId);
    }

    public int describeContents() {
        return 0;
    }

}