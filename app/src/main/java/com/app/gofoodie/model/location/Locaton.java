package com.app.gofoodie.model.location;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Locaton implements Parcelable {

    public final static Creator<Locaton> CREATOR = new Creator<Locaton>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Locaton createFromParcel(Parcel in) {
            return new Locaton(in);
        }

        public Locaton[] newArray(int size) {
            return (new Locaton[size]);
        }

    };
    @SerializedName("area_id")
    @Expose
    private String areaId;
    @SerializedName("area_name")
    @Expose
    private String areaName;

    Locaton(Parcel in) {
        this.areaId = ((String) in.readValue((String.class.getClassLoader())));
        this.areaName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Locaton() {
    }

    public Locaton(String id, String name) {

        this.areaId = id;
        this.areaName = name;

    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(areaId);
        dest.writeValue(areaName);
    }

    public int describeContents() {
        return 0;
    }

}
