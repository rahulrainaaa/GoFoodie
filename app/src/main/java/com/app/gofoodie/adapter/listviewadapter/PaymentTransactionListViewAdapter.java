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

    public static final String TAG = "RestaurantListViewAdapter";

    private Activity mActivity = null;
    private ArrayList<PaymentTransaction> mListData = null;
    private int mLayoutResourceId;

    private static class Holder {

        public ImageView imgRestaurant = null;
        public TextView txtTransactionId = null;
        public TextView txtDate = null;
        public TextView txtReviews = null;
        public TextView txtPrice = null;
        public int tag = -1;
    }

    public PaymentTransactionListViewAdapter(@NonNull Activity activity, @LayoutRes int resource, ArrayList<PaymentTransaction> list) {

        super(activity, resource, list);
        this.mActivity = activity;
        this.mListData = list;
        this.mLayoutResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View cell = convertView;
        Holder holder = null;
        if (convertView == null) {

            cell = mActivity.getLayoutInflater().inflate(mLayoutResourceId, null);
            holder = new Holder();
            holder.imgRestaurant = (ImageView) cell.findViewById(R.id.image_alert);
            holder.txtTransactionId = (TextView) cell.findViewById(R.id.transaction_id);
            holder.txtDate = (TextView) cell.findViewById(R.id.text_view_date);
            holder.txtReviews = (TextView) cell.findViewById(R.id.text_view_remark);
            holder.txtPrice = (TextView) cell.findViewById(R.id.text_view_price);
            cell.setTag(holder);
        } else {
            holder = (Holder) cell.getTag();
        }

        return cell;
    }
}
