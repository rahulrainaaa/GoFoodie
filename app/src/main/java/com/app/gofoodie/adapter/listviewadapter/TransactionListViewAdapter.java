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

import java.util.ArrayList;

public class TransactionListViewAdapter extends ArrayAdapter<String> {

    public static final String TAG = "RestaurantListViewAdapter";

    private Activity mActivity = null;
    private ArrayList<String> mListData = null;
    private int mLayoutResourceId;

    private static class Holder {

        public ImageView imgRestaurant = null;
        public TextView txtRestaurantName = null;
        public int tag = -1;
    }

    public TransactionListViewAdapter(@NonNull Activity activity, @LayoutRes int resource, ArrayList<String> list) {

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
            holder.imgRestaurant = (ImageView) cell.findViewById(R.id.image_restaurant);
            cell.setTag(holder);
        } else {
            holder = (Holder) cell.getTag();
        }

        return cell;
    }
}
