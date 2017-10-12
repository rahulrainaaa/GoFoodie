
package com.app.gofoodie.model.transaction;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletTransaction implements Parcelable
{

    @SerializedName("wallet_id")
    @Expose
    public String walletId;
    @SerializedName("wallet_transaction_id")
    @Expose
    public String walletTransactionId;
    @SerializedName("datetime")
    @Expose
    public String datetime;
    @SerializedName("remarks")
    @Expose
    public String remarks;
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("type")
    @Expose
    public String type;
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
        this.datetime = ((String) in.readValue((String.class.getClassLoader())));
        this.remarks = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WalletTransaction() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(walletId);
        dest.writeValue(walletTransactionId);
        dest.writeValue(datetime);
        dest.writeValue(remarks);
        dest.writeValue(amount);
        dest.writeValue(type);
    }

    public int describeContents() {
        return  0;
    }

}
