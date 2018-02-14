package com.app.gofoodie.model.featured;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Featured extends BaseModel implements Parcelable {

    public final static Creator<Featured> CREATOR = new Creator<Featured>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Featured createFromParcel(Parcel in) {
            return new Featured(in);
        }

        public Featured[] newArray(int size) {
            return (new Featured[size]);
        }

    };
    @SerializedName("featured_restaurants")
    @Expose
    public List<FeaturedRestaurant> featuredRestaurants = null;
    @SerializedName("featured_combos")
    @Expose
    public List<FeaturedCombo> featuredCombos = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;

    protected Featured(Parcel in) {
        in.readList(this.featuredRestaurants, (FeaturedRestaurant.class.getClassLoader()));
        in.readList(this.featuredCombos, (FeaturedCombo.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Featured() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(featuredRestaurants);
        dest.writeList(featuredCombos);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
