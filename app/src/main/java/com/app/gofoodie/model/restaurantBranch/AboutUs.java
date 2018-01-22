
package com.app.gofoodie.model.restaurantBranch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutUs implements Parcelable
{

    @SerializedName("aboutus")
    @Expose
    private String aboutus;
    @SerializedName("isManager")
    @Expose
    private String isManager;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("banner")
    @Expose
    private String banner;
    public final static Creator<AboutUs> CREATOR = new Creator<AboutUs>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AboutUs createFromParcel(Parcel in) {
            return new AboutUs(in);
        }

        public AboutUs[] newArray(int size) {
            return (new AboutUs[size]);
        }

    }
    ;

    protected AboutUs(Parcel in) {
        this.aboutus = ((String) in.readValue((String.class.getClassLoader())));
        this.isManager = ((String) in.readValue((String.class.getClassLoader())));
        this.logo = ((String) in.readValue((String.class.getClassLoader())));
        this.banner = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AboutUs() {
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getIsManager() {
        return isManager;
    }

    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(aboutus);
        dest.writeValue(isManager);
        dest.writeValue(logo);
        dest.writeValue(banner);
    }

    public int describeContents() {
        return  0;
    }

}
