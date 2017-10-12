
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
    public List<WalletTransaction> walletTransactions = null;
    @SerializedName("PaymentTransactions")
    @Expose
    public List<PaymentTransaction> paymentTransactions = null;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
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
        in.readList(this.walletTransactions, (com.app.gofoodie.model.transaction.WalletTransaction.class.getClassLoader()));
        in.readList(this.paymentTransactions, (com.app.gofoodie.model.transaction.PaymentTransaction.class.getClassLoader()));
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Transaction() {
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
