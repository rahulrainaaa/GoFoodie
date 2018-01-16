
package com.app.gofoodie.model.comboPlan;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comboplan implements Parcelable
{

    @SerializedName("combo_id")
    @Expose
    private String comboId;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("combo_name")
    @Expose
    private String comboName;
    @SerializedName("combo_type")
    @Expose
    private String comboType;
    @SerializedName("combo_description")
    @Expose
    private String comboDescription;
    @SerializedName("combo_pay_price")
    @Expose
    private String comboPayPrice;
    @SerializedName("combo_image")
    @Expose
    private String comboImage;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("branch_address")
    @Expose
    private String branchAddress;
    @SerializedName("resturant_type")
    @Expose
    private String resturantType;
    @SerializedName("cuisine_id")
    @Expose
    private String cuisineId;
    @SerializedName("cuisine_name")
    @Expose
    private String cuisineName;
    @SerializedName("combo_options")
    @Expose
    private List<ComboOption> comboOptions = null;
    @SerializedName("avg_rating")
    @Expose
    private Integer avgRating;
    @SerializedName("count_rating")
    @Expose
    private Integer countRating;
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

    }
    ;

    protected Comboplan(Parcel in) {
        this.comboId = ((String) in.readValue((String.class.getClassLoader())));
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.comboName = ((String) in.readValue((String.class.getClassLoader())));
        this.comboType = ((String) in.readValue((String.class.getClassLoader())));
        this.comboDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.comboPayPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.comboImage = ((String) in.readValue((String.class.getClassLoader())));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.branchAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.resturantType = ((String) in.readValue((String.class.getClassLoader())));
        this.cuisineId = ((String) in.readValue((String.class.getClassLoader())));
        this.cuisineName = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.comboOptions, (ComboOption.class.getClassLoader()));
        this.avgRating = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.countRating = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Comboplan() {
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getComboType() {
        return comboType;
    }

    public void setComboType(String comboType) {
        this.comboType = comboType;
    }

    public String getComboDescription() {
        return comboDescription;
    }

    public void setComboDescription(String comboDescription) {
        this.comboDescription = comboDescription;
    }

    public String getComboPayPrice() {
        return comboPayPrice;
    }

    public void setComboPayPrice(String comboPayPrice) {
        this.comboPayPrice = comboPayPrice;
    }

    public String getComboImage() {
        return comboImage;
    }

    public void setComboImage(String comboImage) {
        this.comboImage = comboImage;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getResturantType() {
        return resturantType;
    }

    public void setResturantType(String resturantType) {
        this.resturantType = resturantType;
    }

    public String getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(String cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public List<ComboOption> getComboOptions() {
        return comboOptions;
    }

    public void setComboOptions(List<ComboOption> comboOptions) {
        this.comboOptions = comboOptions;
    }

    public Integer getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getCountRating() {
        return countRating;
    }

    public void setCountRating(Integer countRating) {
        this.countRating = countRating;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(comboId);
        dest.writeValue(branchId);
        dest.writeValue(comboName);
        dest.writeValue(comboType);
        dest.writeValue(comboDescription);
        dest.writeValue(comboPayPrice);
        dest.writeValue(comboImage);
        dest.writeValue(branchName);
        dest.writeValue(branchAddress);
        dest.writeValue(resturantType);
        dest.writeValue(cuisineId);
        dest.writeValue(cuisineName);
        dest.writeList(comboOptions);
        dest.writeValue(avgRating);
        dest.writeValue(countRating);
    }

    public int describeContents() {
        return  0;
    }

}
