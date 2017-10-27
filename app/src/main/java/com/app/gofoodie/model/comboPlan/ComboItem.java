
package com.app.gofoodie.model.comboPlan;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComboItem implements Parcelable
{

    @SerializedName("combo_id")
    @Expose
    public String comboId;
    @SerializedName("combo_item_id")
    @Expose
    public String comboItemId;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("options")
    @Expose
    public List<String> options = null;
    public final static Creator<ComboItem> CREATOR = new Creator<ComboItem>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ComboItem createFromParcel(Parcel in) {
            return new ComboItem(in);
        }

        public ComboItem[] newArray(int size) {
            return (new ComboItem[size]);
        }

    }
    ;

    protected ComboItem(Parcel in) {
        this.comboId = ((String) in.readValue((String.class.getClassLoader())));
        this.comboItemId = ((String) in.readValue((String.class.getClassLoader())));
        this.itemName = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.options, (String.class.getClassLoader()));
    }

    public ComboItem() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(comboId);
        dest.writeValue(comboItemId);
        dest.writeValue(itemName);
        dest.writeList(options);
    }

    public int describeContents() {
        return  0;
    }

}
