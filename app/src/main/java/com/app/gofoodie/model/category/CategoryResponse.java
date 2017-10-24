
package com.app.gofoodie.model.category;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse extends BaseModel implements Parcelable
{

    @SerializedName("category")
    @Expose
    public List<Category> category = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Creator<CategoryResponse> CREATOR = new Creator<CategoryResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CategoryResponse createFromParcel(Parcel in) {
            return new CategoryResponse(in);
        }

        public CategoryResponse[] newArray(int size) {
            return (new CategoryResponse[size]);
        }

    }
    ;

    protected CategoryResponse(Parcel in) {
        in.readList(this.category, (Category.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CategoryResponse() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(category);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return  0;
    }

}
