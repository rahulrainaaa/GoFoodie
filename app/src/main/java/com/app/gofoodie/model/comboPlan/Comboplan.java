
package com.app.gofoodie.model.comboPlan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comboplan implements Parcelable {

    @SerializedName("combo_id")
    @Expose
    public String comboId;
    @SerializedName("cb_id")
    @Expose
    public String cbId;
    @SerializedName("branch_id")
    @Expose
    public String branchId;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("branch_name")
    @Expose
    public String branchName;
    @SerializedName("restaurant_name")
    @Expose
    public String restaurantName;
    @SerializedName("combo_name")
    @Expose
    public String comboName;
    @SerializedName("cate_name")
    @Expose
    public String cateName;
    @SerializedName("cuisine_name")
    @Expose
    public String cuisineName;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("avg_rating")
    @Expose
    public String avgRating;
    @SerializedName("count_rating")
    @Expose
    public String countRating;
    @SerializedName("comboItems")
    @Expose
    public List<ComboItem> comboItems = null;
    public final static Creator<Comboplan> CREATOR = new Creator<Comboplan>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Comboplan createFromParcel(Parcel in) {
            return new Comboplan(in);
        }

        public Comboplan[] newArray(int size) {
            return (new Comboplan[size]);
        }

    };

    protected Comboplan(Parcel in) {
        this.comboId = ((String) in.readValue((String.class.getClassLoader())));
        this.cbId = ((String) in.readValue((String.class.getClassLoader())));
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantName = ((String) in.readValue((String.class.getClassLoader())));
        this.comboName = ((String) in.readValue((String.class.getClassLoader())));
        this.cateName = ((String) in.readValue((String.class.getClassLoader())));
        this.cuisineName = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.avgRating = ((String) in.readValue((String.class.getClassLoader())));
        this.countRating = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.comboItems, (com.app.gofoodie.model.comboPlan.ComboItem.class.getClassLoader()));
    }

    public Comboplan() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(comboId);
        dest.writeValue(cbId);
        dest.writeValue(branchId);
        dest.writeValue(type);
        dest.writeValue(branchName);
        dest.writeValue(restaurantName);
        dest.writeValue(comboName);
        dest.writeValue(cateName);
        dest.writeValue(cuisineName);
        dest.writeValue(description);
        dest.writeValue(image);
        dest.writeValue(price);
        dest.writeValue(avgRating);
        dest.writeValue(countRating);
        dest.writeList(comboItems);
    }

    public int describeContents() {
        return 0;
    }

}
