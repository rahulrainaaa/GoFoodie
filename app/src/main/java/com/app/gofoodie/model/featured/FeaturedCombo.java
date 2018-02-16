
package com.app.gofoodie.model.featured;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings({"WeakerAccess", "unused"})
public class FeaturedCombo implements Parcelable
{

    @SerializedName("combo_id")
    @Expose
    public String comboId;
    @SerializedName("cb_id")
    @Expose
    public String cbId;
    @SerializedName("restaurant_id")
    @Expose
    public String restaurantId;
    @SerializedName("branch_id")
    @Expose
    public String branchId;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("comboName")
    @Expose
    public String comboName;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("comboItems")
    @Expose
    public final List<ComboItem> comboItems = null;
    public final static Creator<FeaturedCombo> CREATOR = new Creator<FeaturedCombo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public FeaturedCombo createFromParcel(Parcel in) {
            return new FeaturedCombo(in);
        }

        public FeaturedCombo[] newArray(int size) {
            return (new FeaturedCombo[size]);
        }

    }
    ;

    FeaturedCombo(Parcel in) {
        this.comboId = ((String) in.readValue((String.class.getClassLoader())));
        this.cbId = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantId = ((String) in.readValue((String.class.getClassLoader())));
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.comboName = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.comboItems, (ComboItem.class.getClassLoader()));
    }

    public FeaturedCombo() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(comboId);
        dest.writeValue(cbId);
        dest.writeValue(restaurantId);
        dest.writeValue(branchId);
        dest.writeValue(category);
        dest.writeValue(comboName);
        dest.writeValue(description);
        dest.writeValue(image);
        dest.writeValue(price);
        dest.writeList(comboItems);
    }

    public int describeContents() {
        return  0;
    }

}
