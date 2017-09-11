
package com.app.gofoodie.model.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login extends BaseModel implements Parcelable {

    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Parcelable.Creator<Login> CREATOR = new Creator<Login>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Login createFromParcel(Parcel in) {
            Login instance = new Login();
            instance.data = ((Data) in.readValue((Data.class.getClassLoader())));
            instance.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Login[] newArray(int size) {
            return (new Login[size]);
        }

    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(data);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}