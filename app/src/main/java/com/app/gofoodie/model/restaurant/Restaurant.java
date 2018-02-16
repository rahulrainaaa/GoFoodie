package com.app.gofoodie.model.restaurant;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Restaurant branch information.
 * Inner class of {@link RestaurantResponse}.
 */
public class Restaurant implements Parcelable {

    public final static Creator<Restaurant> CREATOR = new Creator<Restaurant>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        public Restaurant[] newArray(int size) {
            return (new Restaurant[size]);
        }

    };
    @SerializedName("branch_id")
    @Expose
    public String branchId;
    @SerializedName("restaurant_id")
    @Expose
    public String restaurantId;
    @SerializedName("avg_rating")
    @Expose
    public String avgRating;
    @SerializedName("count_rating")
    @Expose
    public String countRating;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("branch_name")
    @Expose
    public String branchName;
    @SerializedName("branch_email")
    @Expose
    public String branchEmail;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("profile_icon")
    @Expose
    public String profileIcon;
    @SerializedName("tags")
    @Expose
    public String tags;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("branch_address")
    @Expose
    public String branchAddress;
    @SerializedName("branch_postal_code")
    @Expose
    public String branchPostalCode;
    @SerializedName("geo_lat")
    @Expose
    public String geoLat;
    @SerializedName("geo_lng")
    @Expose
    public String geoLng;
    @SerializedName("about_us")
    @Expose
    public String aboutUs;

    Restaurant(Parcel in) {
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantId = ((String) in.readValue((String.class.getClassLoader())));
        this.avgRating = ((String) in.readValue((String.class.getClassLoader())));
        this.countRating = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.branchEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.profileIcon = ((String) in.readValue((String.class.getClassLoader())));
        this.tags = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.branchAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.branchPostalCode = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLat = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLng = ((String) in.readValue((String.class.getClassLoader())));
        this.aboutUs = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Restaurant() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(branchId);
        dest.writeValue(restaurantId);
        dest.writeValue(avgRating);
        dest.writeValue(countRating);
        dest.writeValue(name);
        dest.writeValue(branchName);
        dest.writeValue(branchEmail);
        dest.writeValue(description);
        dest.writeValue(profileIcon);
        dest.writeValue(tags);
        dest.writeValue(type);
        dest.writeValue(branchAddress);
        dest.writeValue(branchPostalCode);
        dest.writeValue(geoLat);
        dest.writeValue(geoLng);
        dest.writeValue(aboutUs);
    }

    public int describeContents() {
        return 0;
    }

}
