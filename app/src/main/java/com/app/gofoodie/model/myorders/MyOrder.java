package com.app.gofoodie.model.myorders;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyOrder implements Parcelable {

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

    };
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("co_id")
    @Expose
    private String coId;
    @SerializedName("order_set_id")
    @Expose
    private String orderSetId;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("price_paid")
    @Expose
    private String pricePaid;
    @SerializedName("admin_price")
    @Expose
    private String adminPrice;
    @SerializedName("restaurant_price")
    @Expose
    private String restaurantPrice;
    @SerializedName("logistic_price")
    @Expose
    private String logisticPrice;
    @SerializedName("other_tax")
    @Expose
    private String otherTax;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;
    @SerializedName("combo_id")
    @Expose
    private String comboId;
    @SerializedName("item_details")
    @Expose
    private List<ItemDetail> itemDetails = null;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("comboname")
    @Expose
    private String comboname;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("reviewer")
    @Expose
    private String reviewer;
    @SerializedName("review_id")
    @Expose
    private Integer reviewId;

    private MyOrder(Parcel in) {
        this.orderId = ((String) in.readValue((String.class.getClassLoader())));
        this.coId = ((String) in.readValue((String.class.getClassLoader())));
        this.orderSetId = ((String) in.readValue((String.class.getClassLoader())));
        this.orderDate = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.pricePaid = ((String) in.readValue((String.class.getClassLoader())));
        this.adminPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.logisticPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.otherTax = ((String) in.readValue((String.class.getClassLoader())));
        this.deliveryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.comboId = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.itemDetails, (ItemDetail.class.getClassLoader()));
        this.restaurantId = ((String) in.readValue((String.class.getClassLoader())));
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.comboname = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.comment = ((String) in.readValue((String.class.getClassLoader())));
        this.reviewer = ((String) in.readValue((String.class.getClassLoader())));
        this.reviewId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MyOrder() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }

    public String getOrderSetId() {
        return orderSetId;
    }

    public void setOrderSetId(String orderSetId) {
        this.orderSetId = orderSetId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(String pricePaid) {
        this.pricePaid = pricePaid;
    }

    public String getAdminPrice() {
        return adminPrice;
    }

    public void setAdminPrice(String adminPrice) {
        this.adminPrice = adminPrice;
    }

    public String getRestaurantPrice() {
        return restaurantPrice;
    }

    public void setRestaurantPrice(String restaurantPrice) {
        this.restaurantPrice = restaurantPrice;
    }

    public String getLogisticPrice() {
        return logisticPrice;
    }

    public void setLogisticPrice(String logisticPrice) {
        this.logisticPrice = logisticPrice;
    }

    public String getOtherTax() {
        return otherTax;
    }

    public void setOtherTax(String otherTax) {
        this.otherTax = otherTax;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getComboname() {
        return comboname;
    }

    public void setComboname(String comboname) {
        this.comboname = comboname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(orderId);
        dest.writeValue(coId);
        dest.writeValue(orderSetId);
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
        dest.writeValue(restaurantId);
        dest.writeValue(branchId);
        dest.writeValue(branchName);
        dest.writeValue(comboname);
        dest.writeValue(rating);
        dest.writeValue(createdDate);
        dest.writeValue(comment);
        dest.writeValue(reviewer);
        dest.writeValue(reviewId);
    }

    public int describeContents() {
        return 0;
    }

}
