package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.transaction.PaymentTransaction;

import java.util.ArrayList;

/**
 * Adapter class for showing Bank payments in List View on {@link com.app.gofoodie.fragment.derived.WalletFragment}.
 */
public class PaymentTransactionListViewAdapter extends ArrayAdapter<PaymentTransaction> {

    public static final String TAG = "PaymentTransactionListViewAdapter";

    private Activity mActivity = null;
    private ArrayList<PaymentTransaction> mListData = null;

    public PaymentTransactionListViewAdapter(@NonNull Activity activity, ArrayList<PaymentTransaction> list) {

        super(activity, R.layout.item_listview_transactions, list);
        this.mActivity = activity;
        this.mListData = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        PaymentTransaction transaction = mListData.get(position);
        View cell = convertView;
        Holder holder;
        if (convertView == null) {

            cell = mActivity.getLayoutInflater().inflate(R.layout.item_listview_transactions, null);
            holder = new Holder();
            holder.txtTransactionId = cell.findViewById(R.id.transaction_id);
            holder.txtDate = cell.findViewById(R.id.text_view_date);
            holder.txtReviews = cell.findViewById(R.id.text_view_remark);
            holder.txtPrice = cell.findViewById(R.id.text_view_price);
            cell.setTag(holder);
        } else {
            holder = (Holder) cell.getTag();
        }

        holder.txtTransactionId.setText("Ref ID: " + transaction.getPgTransactionId());
        holder.txtDate.setText(transaction.getDatetime());
        holder.txtReviews.setText(String.valueOf(transaction.getRemarks()));
        holder.txtPrice.setText(transaction.getPaidAmount() + " AED");

        if (transaction.getTransactionResponse().contains("fail")) {

            holder.txtPrice.setCompoundDrawablesWithIntrinsicBounds(null, mActivity.getResources().getDrawable(R.drawable.icon_alert_warning), null, null);
        } else {

            holder.txtPrice.setCompoundDrawablesWithIntrinsicBounds(null, mActivity.getResources().getDrawable(R.drawable.icon_receive), null, null);
        }

        return cell;
    }

    private static class Holder {

        public TextView txtTransactionId = null;
        public TextView txtDate = null;
        public TextView txtReviews = null;
        public TextView txtPrice = null;
        public int tag = -1;
    }
}
