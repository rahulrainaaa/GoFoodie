package com.app.gofoodie.model.customer;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Profile implements Parcelable {

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
    @SerializedName("login_id")
    @Expose
    private String loginId;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("salt_key")
    @Expose
    private Object saltKey;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("register_date")
    @Expose
    private String registerDate;
    @SerializedName("modify_date")
    @Expose
    private String modifyDate;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("mobile1")
    @Expose
    private String mobile1;
    @SerializedName("mobile_verified")
    @Expose
    private String mobileVerified;
    @SerializedName("mobile2")
    @Expose
    private String mobile2;
    @SerializedName("email1")
    @Expose
    private String email1;
    @SerializedName("email_verified")
    @Expose
    private String emailVerified;
    @SerializedName("email2")
    @Expose
    private String email2;
    @SerializedName("geo_lat")
    @Expose
    private String geoLat;
    @SerializedName("geo_lng")
    @Expose
    private String geoLng;
    @SerializedName("how_many_days")
    @Expose
    private String howManyDays;
    @SerializedName("days_you_want_the_combo")
    @Expose
    private String daysYouWantTheCombo;
    @SerializedName("veg_nonveg")
    @Expose
    private String vegNonveg;
    @SerializedName("days_no_nonveg")
    @Expose
    private String daysNoNonveg;
    @SerializedName("wallet_id")
    @Expose
    private String walletId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("wl_id")
    @Expose
    private String wlId;
    @SerializedName("valid_upto")
    @Expose
    private String validUpto;
    @SerializedName("area_name")
    @Expose
    private String areaName;

    private Profile(Parcel in) {
        this.loginId = ((String) in.readValue((String.class.getClassLoader())));
        this.username = in.readValue((Object.class.getClassLoader()));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.saltKey = in.readValue((Object.class.getClassLoader()));
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
        this.geoLat = ((String) in.readValue((String.class.getClassLoader())));
        this.geoLng = ((String) in.readValue((String.class.getClassLoader())));
        this.howManyDays = ((String) in.readValue((String.class.getClassLoader())));
        this.daysYouWantTheCombo = ((String) in.readValue((String.class.getClassLoader())));
        this.vegNonveg = ((String) in.readValue((String.class.getClassLoader())));
        this.daysNoNonveg = ((String) in.readValue((String.class.getClassLoader())));
        this.walletId = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.wlId = ((String) in.readValue((String.class.getClassLoader())));
        this.validUpto = ((String) in.readValue((String.class.getClassLoader())));
        this.areaName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Profile() {
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getSaltKey() {
        return saltKey;
    }

    public void setSaltKey(Object saltKey) {
        this.saltKey = saltKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(String mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
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

    public String getHowManyDays() {
        return howManyDays;
    }

    public void setHowManyDays(String howManyDays) {
        this.howManyDays = howManyDays;
    }

    public String getDaysYouWantTheCombo() {
        return daysYouWantTheCombo;
    }

    public void setDaysYouWantTheCombo(String daysYouWantTheCombo) {
        this.daysYouWantTheCombo = daysYouWantTheCombo;
    }

    public String getVegNonveg() {
        return vegNonveg;
    }

    public void setVegNonveg(String vegNonveg) {
        this.vegNonveg = vegNonveg;
    }

    public String getDaysNoNonveg() {
        return daysNoNonveg;
    }

    public void setDaysNoNonveg(String daysNoNonveg) {
        this.daysNoNonveg = daysNoNonveg;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWlId() {
        return wlId;
    }

    public void setWlId(String wlId) {
        this.wlId = wlId;
    }

    public String getValidUpto() {
        return validUpto;
    }

    public void setValidUpto(String validUpto) {
        this.validUpto = validUpto;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
        dest.writeValue(areaName);
    }

    public int describeContents() {
        return 0;
    }

}
