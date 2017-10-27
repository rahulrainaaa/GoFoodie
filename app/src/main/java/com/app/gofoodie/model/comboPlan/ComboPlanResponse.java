
package com.app.gofoodie.model.comboPlan;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComboPlanResponse extends BaseModel implements Parcelable {

    @SerializedName("comboplan")
    @Expose
    public List<Comboplan> comboplan = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
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

    protected ComboPlanResponse(Parcel in) {
        in.readList(this.comboplan, (com.app.gofoodie.model.comboPlan.Comboplan.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ComboPlanResponse() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(comboplan);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
