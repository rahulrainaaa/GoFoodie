
package com.app.gofoodie.model.myorders;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrder implements Parcelable
{

    @SerializedName("order_id")
    @Expose
    public String orderId;
    @SerializedName("co_id")
    @Expose
    public String coId;
    @SerializedName("order_date")
    @Expose
    public String orderDate;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("price_paid")
    @Expose
    public String pricePaid;
    @SerializedName("admin_price")
    @Expose
    public String adminPrice;
    @SerializedName("restaurant_price")
    @Expose
    public String restaurantPrice;
    @SerializedName("logistic_price")
    @Expose
    public String logisticPrice;
    @SerializedName("other_tax")
    @Expose
    public String otherTax;
    @SerializedName("delivery_date")
    @Expose
    public String deliveryDate;
    @SerializedName("combo_id")
    @Expose
    public String comboId;
    @SerializedName("item_details")
    @Expose
    public List<ItemDetail> itemDetails = null;
    @SerializedName("branch_name")
    @Expose
    public String branchName;
    @SerializedName("comboname")
    @Expose
    public String comboname;
    public final static Creator<MyOrder> CREATOR = new Creator<MyOrder>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MyOrder createFromParcel(Parcel in) {
            return new MyOrder(in);
        }

        public MyOrder[] newArray(int size) {
            return (new MyOrder[size]);
        }

    }
    ;

    protected MyOrder(Parcel in) {
        this.orderId = ((String) in.readValue((String.class.getClassLoader())));
        this.coId = ((String) in.readValue((String.class.getClassLoader())));
        this.orderDate = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.pricePaid = ((String) in.readValue((String.class.getClassLoader())));
        this.adminPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.logisticPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.otherTax = ((String) in.readValue((String.class.getClassLoader())));
        this.deliveryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.comboId = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.itemDetails, (com.app.gofoodie.model.myorders.ItemDetail.class.getClassLoader()));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.comboname = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MyOrder() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(orderId);
        dest.writeValue(coId);
        dest.writeValue(orderDate);
        dest.writeValue(status);
        dest.writeValue(pricePaid);
        dest.writeValue(adminPrice);
        dest.writeValue(restaurantPrice);
        dest.writeValue(logisticPrice);
        dest.writeValue(otherTax);
        dest.writeValue(deliveryDate);
        dest.writeValue(comboId);
        dest.writeList(itemDetails);
        dest.writeValue(branchName);
        dest.writeValue(comboname);
    }

    public int describeContents() {
        return  0;
    }

}
