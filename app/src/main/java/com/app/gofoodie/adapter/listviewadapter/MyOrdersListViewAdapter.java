package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.myorders.MyOrder;

import java.util.ArrayList;

public class MyOrdersListViewAdapter extends ArrayAdapter<MyOrder> {

    public static final String TAG = "MyOrdersListViewAdapter";

    private Activity mActivity = null;
    private ArrayList<MyOrder> mList = null;
    private int mLayoutResourceId;
    private View.OnClickListener mClickListener = null;

    public MyOrdersListViewAdapter(@NonNull Activity activity, View.OnClickListener listener, @LayoutRes int resource, ArrayList<MyOrder> list) {

        super(activity, resource, list);
        this.mActivity = activity;
        this.mList = list;
        this.mLayoutResourceId = resource;
        this.mClickListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MyOrder myOrder = mList.get(position);
        View cell = convertView;
        Holder holder = null;
        if (convertView == null) {

            cell = mActivity.getLayoutInflater().inflate(mLayoutResourceId, null);
            holder = new Holder();

            holder.ComboName = (TextView) cell.findViewById(R.id.txt_combo_name);
            holder.Price = (TextView) cell.findViewById(R.id.txt_price);
            holder.Date = (TextView) cell.findViewById(R.id.txt_date);
            holder.Status = (TextView) cell.findViewById(R.id.txt_status);

            holder.ibtnRate = (ImageButton) cell.findViewById(R.id.add_rating);
            holder.ibtnDescription = (ImageButton) cell.findViewById(R.id.show_desc);
            holder.ibtnEdit = (ImageButton) cell.findViewById(R.id.edit);

            holder.ibtnRate.setOnClickListener(mClickListener);
            holder.ibtnDescription.setOnClickListener(mClickListener);
            holder.ibtnEdit.setOnClickListener(mClickListener);

            cell.setTag(holder);
        } else {
            holder = (Holder) cell.getTag();
        }

        holder.ibtnRate.setTag(myOrder);
        holder.ibtnDescription.setTag(myOrder);
        holder.ibtnEdit.setTag(myOrder);

        holder.ComboName.setText(myOrder.comboname);
        holder.Date.setText(myOrder.deliveryDate);
        String p_status = myOrder.status.trim().substring(0, 1).toUpperCase() + myOrder.status.trim().substring(1);
        holder.Status.setText(p_status);
        holder.Price.setText(myOrder.pricePaid + " AED");

        return cell;
    }

    private static class Holder {

        public TextView ComboName = null;
        public TextView Date = null;
        public TextView Price = null;
        public TextView Status = null;

        public ImageButton ibtnRate = null;
        public ImageButton ibtnDescription = null;
        public ImageButton ibtnEdit = null;

        public int tag = -1;
    }
}
