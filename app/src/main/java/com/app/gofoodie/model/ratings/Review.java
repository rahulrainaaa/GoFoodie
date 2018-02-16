
package com.app.gofoodie.model.ratings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Review implements Parcelable {

    @SerializedName("rating")
    @Expose
    public String rating;
    @SerializedName("comment")
    @Expose
    public String comment;
    @SerializedName("created_date")
    @Expose
    public String createdDate;
    @SerializedName("customer_name")
    @Expose
    public String customerName;
    public final static Creator<Review> CREATOR = new Creator<Review>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        public Review[] newArray(int size) {
            return (new Review[size]);
        }

    };

    Review(Parcel in) {
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
        this.comment = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.customerName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Review() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(rating);
        dest.writeValue(comment);
        dest.writeValue(createdDate);
        dest.writeValue(customerName);
    }

    public int describeContents() {
        return 0;
    }

}
