package com.app.gofoodie.model.myorders;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class ItemDetail implements Parcelable {

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

    };
    @SerializedName("options")
    @Expose
    private List<String> options = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("item_id")
    @Expose
    private String itemId;

    ItemDetail(Parcel in) {
        in.readList(this.options, (String.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((String) in.readValue((String.class.getClassLoader())));
        this.itemId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ItemDetail() {
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(options);
        dest.writeValue(name);
        dest.writeValue(value);
        dest.writeValue(itemId);
    }

    public int describeContents() {
        return 0;
    }

}
