
package com.app.gofoodie.model.featured;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings({"WeakerAccess", "unused"})
public class FeaturedRestaurant implements Parcelable
{

    @SerializedName("branch_id")
    @Expose
    public String branchId;
    @SerializedName("branch_name")
    @Expose
    public String branchName;
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
    @SerializedName("avg_rating")
    @Expose
    public String avgRating;
    public final static Creator<FeaturedRestaurant> CREATOR = new Creator<FeaturedRestaurant>() {


        @SuppressWarnings({
            "unchecked"
        })
        public FeaturedRestaurant createFromParcel(Parcel in) {
            return new FeaturedRestaurant(in);
        }

        public FeaturedRestaurant[] newArray(int size) {
            return (new FeaturedRestaurant[size]);
        }

    }
    ;

    FeaturedRestaurant(Parcel in) {
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.profileIcon = ((String) in.readValue((String.class.getClassLoader())));
        this.tags = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.branchAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.branchPostalCode = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLat = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLng = ((String) in.readValue((String.class.getClassLoader())));
        this.avgRating = ((String) in.readValue((String.class.getClassLoader())));
    }

    public FeaturedRestaurant() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(branchId);
        dest.writeValue(branchName);
        dest.writeValue(description);
        dest.writeValue(profileIcon);
        dest.writeValue(tags);
        dest.writeValue(type);
        dest.writeValue(branchAddress);
        dest.writeValue(branchPostalCode);
        dest.writeValue(geoLat);
        dest.writeValue(geoLng);
        dest.writeValue(avgRating);
    }

    public int describeContents() {
        return  0;
    }

}
