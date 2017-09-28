
package com.app.gofoodie.model.login;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("login_id")
    @Expose
    public String loginId;
    @SerializedName("customer_id")
    @Expose
    public String customerId;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("login_date")
    @Expose
    public String loginDate;
    @SerializedName("platform")
    @Expose
    public String platform;
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

    protected Data(Parcel in) {
        this.loginId = ((String) in.readValue((String.class.getClassLoader())));
        this.customerId = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.loginDate = ((String) in.readValue((String.class.getClassLoader())));
        this.platform = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Data() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(loginId);
        dest.writeValue(customerId);
        dest.writeValue(token);
        dest.writeValue(loginDate);
        dest.writeValue(platform);
    }

    public int describeContents() {
        return  0;
    }

}
