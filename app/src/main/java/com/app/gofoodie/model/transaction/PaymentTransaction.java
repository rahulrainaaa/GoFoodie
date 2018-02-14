package com.app.gofoodie.model.transaction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentTransaction implements Parcelable {

    public final static Creator<PaymentTransaction> CREATOR = new Creator<PaymentTransaction>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaymentTransaction createFromParcel(Parcel in) {
            return new PaymentTransaction(in);
        }

        public PaymentTransaction[] newArray(int size) {
            return (new PaymentTransaction[size]);
        }

    };
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("wallet_id")
    @Expose
    private String walletId;
    @SerializedName("pg_transaction_id")
    @Expose
    private String pgTransactionId;
    @SerializedName("pg_response")
    @Expose
    private String pgResponse;
    @SerializedName("transaction_response")
    @Expose
    private String transactionResponse;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("paid_amount")
    @Expose
    private String paidAmount;

    private PaymentTransaction(Parcel in) {
        this.transactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.walletId = ((String) in.readValue((String.class.getClassLoader())));
        this.pgTransactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.pgResponse = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionResponse = ((String) in.readValue((String.class.getClassLoader())));
        this.datetime = ((String) in.readValue((String.class.getClassLoader())));
        this.remarks = ((String) in.readValue((String.class.getClassLoader())));
        this.paidAmount = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PaymentTransaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getPgTransactionId() {
        return pgTransactionId;
    }

    public void setPgTransactionId(String pgTransactionId) {
        this.pgTransactionId = pgTransactionId;
    }

    public String getPgResponse() {
        return pgResponse;
    }

    public void setPgResponse(String pgResponse) {
        this.pgResponse = pgResponse;
    }

    public String getTransactionResponse() {
        return transactionResponse;
    }

    public void setTransactionResponse(String transactionResponse) {
        this.transactionResponse = transactionResponse;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(transactionId);
        dest.writeValue(walletId);
        dest.writeValue(pgTransactionId);
        dest.writeValue(pgResponse);
        dest.writeValue(transactionResponse);
        dest.writeValue(datetime);
        dest.writeValue(remarks);
        dest.writeValue(paidAmount);
    }

    public int describeContents() {
        return 0;
    }

}
