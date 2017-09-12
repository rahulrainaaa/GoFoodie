
package com.app.gofoodie.model.profile;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("login_id")
    @Expose
    public String loginId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("address")
    @Expose
    public Object address;
    @SerializedName("area")
    @Expose
    public String area;
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("mobile")
    @Expose
    public Object mobile;
    @SerializedName("mobile2")
    @Expose
    public Object mobile2;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("email2")
    @Expose
    public String email2;
    @SerializedName("geo_lat")
    @Expose
    public Object geoLat;
    @SerializedName("geo_lng")
    @Expose
    public Object geoLng;
    @SerializedName("user_type")
    @Expose
    public String userType;
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            Datum instance = new Datum();
            instance.loginId = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.area = ((String) in.readValue((String.class.getClassLoader())));
            instance.companyName = ((String) in.readValue((String.class.getClassLoader())));
            instance.mobile = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mobile2 = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.email = ((String) in.readValue((String.class.getClassLoader())));
            instance.email2 = ((String) in.readValue((String.class.getClassLoader())));
            instance.geoLat = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.geoLng = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.userType = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(loginId);
        dest.writeValue(name);
        dest.writeValue(address);
        dest.writeValue(area);
        dest.writeValue(companyName);
        dest.writeValue(mobile);
        dest.writeValue(mobile2);
        dest.writeValue(email);
        dest.writeValue(email2);
        dest.writeValue(geoLat);
        dest.writeValue(geoLng);
        dest.writeValue(userType);
    }

    public int describeContents() {
        return  0;
    }

}
