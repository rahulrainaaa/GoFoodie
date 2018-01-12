package com.app.gofoodie.model.cuisine;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Cuisine implements Parcelable {

    public final static Creator<Cuisine> CREATOR = new Creator<Cuisine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cuisine createFromParcel(Parcel in) {
            return new Cuisine(in);
        }

        public Cuisine[] newArray(int size) {
            return (new Cuisine[size]);
        }

    };
    public boolean isChecked = true;
    private String cuisineId;
    private String cuisineName;
    private String cateId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected Cuisine(Parcel in) {
        this.cuisineId = ((String) in.readValue((String.class.getClassLoader())));
        this.cuisineName = ((String) in.readValue((String.class.getClassLoader())));
        this.cateId = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object>) in.readValue((Map.class.getClassLoader())));
    }

    public Cuisine() {
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

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cuisineId);
        dest.writeValue(cuisineName);
        dest.writeValue(cateId);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}
