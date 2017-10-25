
package com.app.gofoodie.model.rechargePlan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscriptionplan implements Parcelable
{

    @SerializedName("plan_id")
    @Expose
    public String planId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("pay_amount")
    @Expose
    public String payAmount;
    @SerializedName("validity_days")
    @Expose
    public String validityDays;
    public final static Creator<Subscriptionplan> CREATOR = new Creator<Subscriptionplan>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Subscriptionplan createFromParcel(Parcel in) {
            return new Subscriptionplan(in);
        }

        public Subscriptionplan[] newArray(int size) {
            return (new Subscriptionplan[size]);
        }

    }
    ;

    protected Subscriptionplan(Parcel in) {
        this.planId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.payAmount = ((String) in.readValue((String.class.getClassLoader())));
        this.validityDays = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Subscriptionplan() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(planId);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(image);
        dest.writeValue(payAmount);
        dest.writeValue(validityDays);
    }

    public int describeContents() {
        return  0;
    }

}
