
package com.app.gofoodie.model.cuisine;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuisine extends BaseModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Creator<Cuisine> CREATOR = new Creator<Cuisine>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Cuisine createFromParcel(Parcel in) {
            Cuisine instance = new Cuisine();
            in.readList(instance.data, (Datum.class.getClassLoader()));
            instance.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Cuisine[] newArray(int size) {
            return (new Cuisine[size]);
        }

    }
    ;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return  0;
    }

}
