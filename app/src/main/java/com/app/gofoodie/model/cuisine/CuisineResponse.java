package com.app.gofoodie.model.cuisine;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuisineResponse extends BaseModel implements Parcelable {

    public final static Creator<CuisineResponse> CREATOR = new Creator<CuisineResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CuisineResponse createFromParcel(Parcel in) {
            return new CuisineResponse(in);
        }

        public CuisineResponse[] newArray(int size) {
            return (new CuisineResponse[size]);
        }

    };
    private List<Cuisine> cuisine = null;
    private Integer statusCode;
    private String statusMessage;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected CuisineResponse(Parcel in) {
        in.readList(this.cuisine, (Cuisine.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object>) in.readValue((Map.class.getClassLoader())));
    }

    public CuisineResponse() {
    }

    public List<Cuisine> getCuisine() {
        return cuisine;
    }

    public void setCuisine(List<Cuisine> cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(cuisine);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}
