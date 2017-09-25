
package com.app.gofoodie.model.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable {

    public boolean checked = true;

    @SerializedName("cate_id")
    @Expose
    public String cateId;
    @SerializedName("cate_name")
    @Expose
    public String cateName;
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            Datum instance = new Datum();
            instance.cateId = ((String) in.readValue((String.class.getClassLoader())));
            instance.cateName = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cateId);
        dest.writeValue(cateName);
    }

    public int describeContents() {
        return 0;
    }

}
