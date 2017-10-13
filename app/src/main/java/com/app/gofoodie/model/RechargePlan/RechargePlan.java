
package com.app.gofoodie.model.RechargePlan;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RechargePlan extends BaseModel implements Parcelable {

    @SerializedName("subscriptionplan")
    @Expose
    public List<Subscriptionplan> subscriptionplan = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Creator<RechargePlan> CREATOR = new Creator<RechargePlan>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RechargePlan createFromParcel(Parcel in) {
            return new RechargePlan(in);
        }

        public RechargePlan[] newArray(int size) {
            return (new RechargePlan[size]);
        }

    };

    protected RechargePlan(Parcel in) {
        in.readList(this.subscriptionplan, (com.app.gofoodie.model.RechargePlan.Subscriptionplan.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RechargePlan() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(subscriptionplan);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
