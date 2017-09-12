
package com.app.gofoodie.model.profile;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profile extends BaseModel implements Parcelable {

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Creator<Profile> CREATOR = new Creator<Profile>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Profile createFromParcel(Parcel in) {
            Profile instance = new Profile();
            in.readList(instance.data, (Datum.class.getClassLoader()));
            instance.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Profile[] newArray(int size) {
            return (new Profile[size]);
        }

    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
