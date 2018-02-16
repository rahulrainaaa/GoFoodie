package com.app.gofoodie.model.transaction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class WalletTransaction implements Parcelable {

    public final static Creator<WalletTransaction> CREATOR = new Creator<WalletTransaction>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WalletTransaction createFromParcel(Parcel in) {
            return new WalletTransaction(in);
        }

        public WalletTransaction[] newArray(int size) {
            return (new WalletTransaction[size]);
        }

    };
    @SerializedName("wallet_id")
    @Expose
    private String walletId;
    @SerializedName("wallet_transaction_id")
    @Expose
    private String walletTransactionId;
    @SerializedName("invoice_id")
    @Expose
    private String invoiceId;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("type")
    @Expose
    private String type;

    WalletTransaction(Parcel in) {
        this.walletId = ((String) in.readValue((String.class.getClassLoader())));
        this.walletTransactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.invoiceId = ((String) in.readValue((String.class.getClassLoader())));
        this.datetime = ((String) in.readValue((String.class.getClassLoader())));
        this.remarks = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WalletTransaction() {
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getWalletTransactionId() {
        return walletTransactionId;
    }

    public void setWalletTransactionId(String walletTransactionId) {
        this.walletTransactionId = walletTransactionId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(walletId);
        dest.writeValue(walletTransactionId);
        dest.writeValue(invoiceId);
        dest.writeValue(datetime);
        dest.writeValue(remarks);
        dest.writeValue(amount);
        dest.writeValue(type);
    }

    public int describeContents() {
        return 0;
    }

}
