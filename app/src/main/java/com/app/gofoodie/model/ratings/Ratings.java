
package com.app.gofoodie.model.ratings;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Ratings extends BaseModel implements Parcelable {

    @SerializedName("review")
    @Expose
    public final List<Review> review = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Creator<Ratings> CREATOR = new Creator<Ratings>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Ratings createFromParcel(Parcel in) {
            return new Ratings(in);
        }

        public Ratings[] newArray(int size) {
            return (new Ratings[size]);
        }

    };

    protected Ratings(Parcel in) {
        in.readList(this.review, (Review.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Ratings() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(review);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
