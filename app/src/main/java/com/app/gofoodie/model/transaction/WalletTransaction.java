
package com.app.gofoodie.model.transaction;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class WalletTransaction implements Parcelable
{

    private String walletId;
    private String walletTransactionId;
    private String invoiceId;
    private String datetime;
    private String remarks;
    private String amount;
    private String type;
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

    }
    ;

    protected WalletTransaction(Parcel in) {
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
        return  0;
    }

}
