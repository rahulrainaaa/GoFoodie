package com.app.gofoodie.model.comboPlan;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComboPlanResponse extends BaseModel implements Parcelable {

    public final static Creator<ComboPlanResponse> CREATOR = new Creator<ComboPlanResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ComboPlanResponse createFromParcel(Parcel in) {
            return new ComboPlanResponse(in);
        }

        public ComboPlanResponse[] newArray(int size) {
            return (new ComboPlanResponse[size]);
        }

    };
    @SerializedName("comboplans")
    @Expose
    private List<Comboplan> comboplans = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;

    protected ComboPlanResponse(Parcel in) {
        in.readList(this.comboplans, (Comboplan.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ComboPlanResponse() {
    }

    public List<Comboplan> getComboplans() {
        return comboplans;
    }

    public void setComboplans(List<Comboplan> comboplans) {
        this.comboplans = comboplans;
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
        dest.writeList(comboplans);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
