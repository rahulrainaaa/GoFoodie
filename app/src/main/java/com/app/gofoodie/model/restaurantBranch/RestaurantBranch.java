package com.app.gofoodie.model.restaurantBranch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Contains restaurant branch information.
 * Inner class of {@link RestaurantBranchResponse}.
 */
public class RestaurantBranch implements Parcelable {

    public final static Creator<RestaurantBranch> CREATOR = new Creator<RestaurantBranch>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RestaurantBranch createFromParcel(Parcel in) {
            return new RestaurantBranch(in);
        }

        public RestaurantBranch[] newArray(int size) {
            return (new RestaurantBranch[size]);
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

    protected RestaurantBranch(Parcel in) {
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

    public RestaurantBranch() {
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
