
package com.app.gofoodie.model.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable {

    @SerializedName("login_id")
    @Expose
    public String loginId;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("login_date")
    @Expose
    public String loginDate;
    @SerializedName("platform")
    @Expose
    public String platform;
    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            Data instance = new Data();
            instance.loginId = ((String) in.readValue((String.class.getClassLoader())));
            instance.token = ((String) in.readValue((String.class.getClassLoader())));
            instance.loginDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.platform = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(loginId);
        dest.writeValue(token);
        dest.writeValue(loginDate);
        dest.writeValue(platform);
    }

    public int describeContents() {
        return 0;
    }

}