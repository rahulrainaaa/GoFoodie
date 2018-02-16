
package com.app.gofoodie.model.shortlisted;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShortlistedRestaurants extends BaseModel implements Parcelable {

    @SerializedName("shortlisted")
    @Expose
    public final List<Shortlisted> shortlisted = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Creator<ShortlistedRestaurants> CREATOR = new Creator<ShortlistedRestaurants>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ShortlistedRestaurants createFromParcel(Parcel in) {
            return new ShortlistedRestaurants(in);
        }

        public ShortlistedRestaurants[] newArray(int size) {
            return (new ShortlistedRestaurants[size]);
        }

    };

    protected ShortlistedRestaurants(Parcel in) {
        in.readList(this.shortlisted, (Shortlisted.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ShortlistedRestaurants() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(shortlisted);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
