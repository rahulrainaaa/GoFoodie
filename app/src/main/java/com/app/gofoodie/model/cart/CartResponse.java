
package com.app.gofoodie.model.cart;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartResponse extends BaseModel implements Parcelable {

    @SerializedName("Cart")
    @Expose
    public List<Cart> cart = null;
    @SerializedName("totalPrice")
    @Expose
    public Integer totalPrice;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
    public final static Creator<CartResponse> CREATOR = new Creator<CartResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CartResponse createFromParcel(Parcel in) {
            return new CartResponse(in);
        }

        public CartResponse[] newArray(int size) {
            return (new CartResponse[size]);
        }

    };

    protected CartResponse(Parcel in) {
        in.readList(this.cart, (Cart.class.getClassLoader()));
        this.totalPrice = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CartResponse() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(cart);
        dest.writeValue(totalPrice);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
