
package com.app.gofoodie.model.shortlisted;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shortlisted implements Parcelable
{

    @SerializedName("branch_id")
    @Expose
    public String branchId;
    @SerializedName("restaurant_id")
    @Expose
    public String restaurantId;
    @SerializedName("customer_id")
    @Expose
    public String customerId;
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
    public final static Creator<Shortlisted> CREATOR = new Creator<Shortlisted>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Shortlisted createFromParcel(Parcel in) {
            return new Shortlisted(in);
        }

        public Shortlisted[] newArray(int size) {
            return (new Shortlisted[size]);
        }

    }
    ;

    protected Shortlisted(Parcel in) {
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantId = ((String) in.readValue((String.class.getClassLoader())));
        this.customerId = ((String) in.readValue((String.class.getClassLoader())));
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

    public Shortlisted() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(branchId);
        dest.writeValue(restaurantId);
        dest.writeValue(customerId);
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
        return  0;
    }

}
