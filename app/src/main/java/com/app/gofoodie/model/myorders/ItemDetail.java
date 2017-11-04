
package com.app.gofoodie.model.myorders;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDetail implements Parcelable
{

    @SerializedName("options")
    @Expose
    public List<String> options = null;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("value")
    @Expose
    public String value;
    @SerializedName("item_id")
    @Expose
    public String itemId;
    public final static Creator<ItemDetail> CREATOR = new Creator<ItemDetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ItemDetail createFromParcel(Parcel in) {
            return new ItemDetail(in);
        }

        public ItemDetail[] newArray(int size) {
            return (new ItemDetail[size]);
        }

    }
    ;

    protected ItemDetail(Parcel in) {
        in.readList(this.options, (String.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((String) in.readValue((String.class.getClassLoader())));
        this.itemId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ItemDetail() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(options);
        dest.writeValue(name);
        dest.writeValue(value);
        dest.writeValue(itemId);
    }

    public int describeContents() {
        return  0;
    }

}
