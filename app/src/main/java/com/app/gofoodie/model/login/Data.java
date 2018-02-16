
package com.app.gofoodie.model.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Data implements Parcelable
{

    @SerializedName("login_id")
    @Expose
    private String loginId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("social")
    @Expose
    private String social;
    @SerializedName("login_date")
    @Expose
    private String loginDate;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;

    Data(Parcel in) {
        this.loginId = ((String) in.readValue((String.class.getClassLoader())));
        this.customerId = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.social = ((String) in.readValue((String.class.getClassLoader())));
        this.loginDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Data() {
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(loginId);
        dest.writeValue(customerId);
        dest.writeValue(email);
        dest.writeValue(token);
        dest.writeValue(social);
        dest.writeValue(loginDate);
    }

    public int describeContents() {
        return  0;
    }

}
