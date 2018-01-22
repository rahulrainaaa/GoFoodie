
package com.app.gofoodie.model.restaurantBranch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantBranch implements Parcelable
{

    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("avg_rating")
    @Expose
    private String avgRating;
    @SerializedName("count_rating")
    @Expose
    private String countRating;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("branch_email")
    @Expose
    private String branchEmail;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("profile_icon")
    @Expose
    private String profileIcon;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("branch_address")
    @Expose
    private String branchAddress;
    @SerializedName("branch_postal_code")
    @Expose
    private Object branchPostalCode;
    @SerializedName("geo_lat")
    @Expose
    private String geoLat;
    @SerializedName("geo_lng")
    @Expose
    private String geoLng;
    @SerializedName("about_us")
    @Expose
    private AboutUs aboutUs;
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

    }
    ;

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
        this.branchPostalCode = ((Object) in.readValue((Object.class.getClassLoader())));
        this.geoLat = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLng = ((String) in.readValue((String.class.getClassLoader())));
        this.aboutUs = ((AboutUs) in.readValue((AboutUs.class.getClassLoader())));
    }

    public RestaurantBranch() {
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getCountRating() {
        return countRating;
    }

    public void setCountRating(String countRating) {
        this.countRating = countRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchEmail() {
        return branchEmail;
    }

    public void setBranchEmail(String branchEmail) {
        this.branchEmail = branchEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(String profileIcon) {
        this.profileIcon = profileIcon;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public Object getBranchPostalCode() {
        return branchPostalCode;
    }

    public void setBranchPostalCode(Object branchPostalCode) {
        this.branchPostalCode = branchPostalCode;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
    }

    public String getGeoLng() {
        return geoLng;
    }

    public void setGeoLng(String geoLng) {
        this.geoLng = geoLng;
    }

    public AboutUs getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(AboutUs aboutUs) {
        this.aboutUs = aboutUs;
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
        return  0;
    }

}
