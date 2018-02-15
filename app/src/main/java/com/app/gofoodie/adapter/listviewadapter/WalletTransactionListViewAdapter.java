package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.transaction.WalletTransaction;

import java.util.ArrayList;

/**
 * Adapter class for showing Wallet transaction in List View on {@link com.app.gofoodie.fragment.derived.WalletFragment}.
 */
public class WalletTransactionListViewAdapter extends ArrayAdapter<WalletTransaction> {

    public static final String TAG = "WalletTransactionListViewAdapter";

    private Activity mActivity = null;
    private ArrayList<WalletTransaction> mListData = null;

    public WalletTransactionListViewAdapter(@NonNull Activity activity, ArrayList<WalletTransaction> list) {

        super(activity, R.layout.item_listview_transactions, list);
        this.mActivity = activity;
        this.mListData = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        WalletTransaction transaction = mListData.get(position);
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

//        holder.imgIcon
//        holder.txtTransactionId.setText("Invoice: " + transaction.getInvoiceId());
        holder.txtTransactionId.setText("Invoice ID: " + transaction.getWalletTransactionId());
        holder.txtDate.setText(transaction.getDatetime());
        holder.txtReviews.setText(transaction.getRemarks());
        holder.txtPrice.setText(transaction.getAmount() + " AED");

        if (transaction.getType().contains("credit")) {

            holder.txtPrice.setCompoundDrawablesWithIntrinsicBounds(null, mActivity.getResources().getDrawable(R.drawable.icon_receive), null, null);

        } else {

            holder.txtPrice.setCompoundDrawablesWithIntrinsicBounds(null, mActivity.getResources().getDrawable(R.drawable.icon_sent), null, null);

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
