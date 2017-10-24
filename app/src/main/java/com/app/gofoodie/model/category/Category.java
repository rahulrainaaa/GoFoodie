
package com.app.gofoodie.model.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {

    @SerializedName("cate_id")
    @Expose
    public String cateId;
    @SerializedName("cate_name")
    @Expose
    public String cateName;
    public final static Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    };

    protected Category(Parcel in) {
        this.cateId = ((String) in.readValue((String.class.getClassLoader())));
        this.cateName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Category() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cateId);
        dest.writeValue(cateName);
    }

    public int describeContents() {
        return 0;
    }

    public boolean isChecked = true;

}
