
package com.app.gofoodie.model.category;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category extends BaseModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            Category instance = new Category();
            in.readList(instance.data, (Datum.class.getClassLoader()));
            instance.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    }
    ;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return  0;
    }

}
