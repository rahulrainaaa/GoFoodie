package com.app.gofoodie.model.location;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model to hold Location (id, name).
 */
public class LocationResponse extends BaseModel implements Parcelable {

    public final static Creator<LocationResponse> CREATOR = new Creator<LocationResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LocationResponse createFromParcel(Parcel in) {
            return new LocationResponse(in);
        }

        public LocationResponse[] newArray(int size) {
            return (new LocationResponse[size]);
        }

    };
    @SerializedName("locatons")
    @Expose
    private List<Locaton> locatons = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;

    protected LocationResponse(Parcel in) {
        in.readList(this.locatons, (com.app.gofoodie.model.location.Locaton.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LocationResponse() {
    }

    public List<Locaton> getLocatons() {
        return locatons;
    }

    public void setLocatons(List<Locaton> locatons) {
        this.locatons = locatons;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(locatons);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
