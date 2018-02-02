package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.transaction.PaymentTransaction;

import java.util.ArrayList;

public class PaymentTransactionListViewAdapter extends ArrayAdapter<PaymentTransaction> {

    public static final String TAG = "PaymentTransactionListViewAdapter";

    private Activity mActivity = null;
    private ArrayList<PaymentTransaction> mListData = null;
    private int mLayoutResourceId;

    public PaymentTransactionListViewAdapter(@NonNull Activity activity, @LayoutRes int resource, ArrayList<PaymentTransaction> list) {

        super(activity, resource, list);
        this.mActivity = activity;
        this.mListData = list;
        this.mLayoutResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        PaymentTransaction transaction = mListData.get(position);
        View cell = convertView;
        Holder holder = null;
        if (convertView == null) {

            cell = mActivity.getLayoutInflater().inflate(mLayoutResourceId, null);
            holder = new Holder();
            holder.imgType = (ImageView) cell.findViewById(R.id.image_alert);
            holder.txtTransactionId = (TextView) cell.findViewById(R.id.transaction_id);
            holder.txtDate = (TextView) cell.findViewById(R.id.text_view_date);
            holder.txtReviews = (TextView) cell.findViewById(R.id.text_view_remark);
            holder.txtPrice = (TextView) cell.findViewById(R.id.text_view_price);
            cell.setTag(holder);
        } else {
            holder = (Holder) cell.getTag();
        }

        holder.txtTransactionId.setText("Ref ID: " + transaction.getPgTransactionId());
        holder.txtDate.setText(transaction.getDatetime());
        holder.txtReviews.setText(String.valueOf(transaction.getRemarks()));
        holder.txtPrice.setText(transaction.getPaidAmount() + " AED");

        holder.imgType.setImageResource(R.drawable.icon_receive);

        return cell;
    }

    private static class Holder {

        public ImageView imgType = null;
        public TextView txtTransactionId = null;
        public TextView txtDate = null;
        public TextView txtReviews = null;
        public TextView txtPrice = null;
        public int tag = -1;
    }
}
