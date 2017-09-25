
package com.app.gofoodie.model.customer;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile implements Parcelable {

    @SerializedName("login_id")
    @Expose
    public String loginId;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("salt_key")
    @Expose
    public String saltKey;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("register_date")
    @Expose
    public String registerDate;
    @SerializedName("modify_date")
    @Expose
    public String modifyDate;
    @SerializedName("customer_id")
    @Expose
    public String customerId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("area")
    @Expose
    public String area;
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("mobile1")
    @Expose
    public String mobile1;
    @SerializedName("mobile_verified")
    @Expose
    public String mobileVerified;
    @SerializedName("mobile2")
    @Expose
    public String mobile2;
    @SerializedName("email1")
    @Expose
    public String email1;
    @SerializedName("email_verified")
    @Expose
    public String emailVerified;
    @SerializedName("email2")
    @Expose
    public String email2;
    @SerializedName("geo_lat")
    @Expose
    public Float geoLat;
    @SerializedName("geo_lng")
    @Expose
    public Float geoLng;
    @SerializedName("how_many_days")
    @Expose
    public String howManyDays;
    @SerializedName("days_you_want_the_combo")
    @Expose
    public String daysYouWantTheCombo;
    @SerializedName("veg_nonveg")
    @Expose
    public String vegNonveg;
    @SerializedName("days_no_nonveg")
    @Expose
    public String daysNoNonveg;
    @SerializedName("wallet_id")
    @Expose
    public String walletId;
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("wl_id")
    @Expose
    public String wlId;
    @SerializedName("valid_upto")
    @Expose
    public String validUpto;
    public final static Creator<Profile> CREATOR = new Creator<Profile>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        public Profile[] newArray(int size) {
            return (new Profile[size]);
        }

    };

    protected Profile(Parcel in) {
        this.loginId = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.saltKey = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.registerDate = ((String) in.readValue((String.class.getClassLoader())));
        this.modifyDate = ((String) in.readValue((String.class.getClassLoader())));
        this.customerId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.area = ((String) in.readValue((String.class.getClassLoader())));
        this.companyName = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile1 = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileVerified = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile2 = ((String) in.readValue((String.class.getClassLoader())));
        this.email1 = ((String) in.readValue((String.class.getClassLoader())));
        this.emailVerified = ((String) in.readValue((String.class.getClassLoader())));
        this.email2 = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLat = ((Float) in.readValue((Float.class.getClassLoader())));
        this.geoLng = ((Float) in.readValue((Float.class.getClassLoader())));
        this.howManyDays = ((String) in.readValue((String.class.getClassLoader())));
        this.daysYouWantTheCombo = ((String) in.readValue((String.class.getClassLoader())));
        this.vegNonveg = ((String) in.readValue((String.class.getClassLoader())));
        this.daysNoNonveg = ((String) in.readValue((String.class.getClassLoader())));
        this.walletId = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.wlId = ((String) in.readValue((String.class.getClassLoader())));
        this.validUpto = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Profile() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(loginId);
        dest.writeValue(username);
        dest.writeValue(email);
        dest.writeValue(phone);
        dest.writeValue(saltKey);
        dest.writeValue(status);
        dest.writeValue(registerDate);
        dest.writeValue(modifyDate);
        dest.writeValue(customerId);
        dest.writeValue(name);
        dest.writeValue(address);
        dest.writeValue(area);
        dest.writeValue(companyName);
        dest.writeValue(mobile1);
        dest.writeValue(mobileVerified);
        dest.writeValue(mobile2);
        dest.writeValue(email1);
        dest.writeValue(emailVerified);
        dest.writeValue(email2);
        dest.writeValue(geoLat);
        dest.writeValue(geoLng);
        dest.writeValue(howManyDays);
        dest.writeValue(daysYouWantTheCombo);
        dest.writeValue(vegNonveg);
        dest.writeValue(daysNoNonveg);
        dest.writeValue(walletId);
        dest.writeValue(amount);
        dest.writeValue(wlId);
        dest.writeValue(validUpto);
    }

    public int describeContents() {
        return 0;
    }

}
