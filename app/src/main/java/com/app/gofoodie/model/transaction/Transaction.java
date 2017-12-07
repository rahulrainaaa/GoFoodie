
package com.app.gofoodie.model.transaction;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.gofoodie.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transaction extends BaseModel implements Parcelable {

    @SerializedName("WalletTransactions")
    @Expose
    private List<WalletTransaction> walletTransactions = null;
    @SerializedName("PaymentTransactions")
    @Expose
    private List<PaymentTransaction> paymentTransactions = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;
    public final static Creator<Transaction> CREATOR = new Creator<Transaction>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        public Transaction[] newArray(int size) {
            return (new Transaction[size]);
        }

    };

    protected Transaction(Parcel in) {
        in.readList(this.walletTransactions, (WalletTransaction.class.getClassLoader()));
        in.readList(this.paymentTransactions, (PaymentTransaction.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Transaction() {
    }

    public List<WalletTransaction> getWalletTransactions() {
        return walletTransactions;
    }

    public void setWalletTransactions(List<WalletTransaction> walletTransactions) {
        this.walletTransactions = walletTransactions;
    }

    public List<PaymentTransaction> getPaymentTransactions() {
        return paymentTransactions;
    }

    public void setPaymentTransactions(List<PaymentTransaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(walletTransactions);
        dest.writeList(paymentTransactions);
        dest.writeValue(statusCode);
        dest.writeValue(statusMessage);
    }

    public int describeContents() {
        return 0;
    }

}
