
package com.app.gofoodie.model.transaction;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentTransaction implements Parcelable
{

    @SerializedName("transaction_id")
    @Expose
    public String transactionId;
    @SerializedName("wallet_id")
    @Expose
    public String walletId;
    @SerializedName("pg_transaction_id")
    @Expose
    public String pgTransactionId;
    @SerializedName("pg_response")
    @Expose
    public String pgResponse;
    @SerializedName("transaction_response")
    @Expose
    public String transactionResponse;
    @SerializedName("datetime")
    @Expose
    public String datetime;
    @SerializedName("remarks")
    @Expose
    public Object remarks;
    @SerializedName("paid_amount")
    @Expose
    public String paidAmount;
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

    }
    ;

    protected PaymentTransaction(Parcel in) {
        this.transactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.walletId = ((String) in.readValue((String.class.getClassLoader())));
        this.pgTransactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.pgResponse = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionResponse = ((String) in.readValue((String.class.getClassLoader())));
        this.datetime = ((String) in.readValue((String.class.getClassLoader())));
        this.remarks = ((Object) in.readValue((Object.class.getClassLoader())));
        this.paidAmount = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PaymentTransaction() {
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
        return  0;
    }

}
