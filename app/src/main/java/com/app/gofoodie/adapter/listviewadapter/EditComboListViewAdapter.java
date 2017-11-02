package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.cartOrder.Description;

import java.util.ArrayList;

public class EditComboListViewAdapter extends ArrayAdapter<Description> {

    public static final String TAG = "RestaurantListViewAdapter";

    private Activity mActivity = null;
    private ArrayList<Description> mListData = null;
    private int mLayoutResourceId;

    private static class Holder {

        public TextView txtName = null;
        public TextView txtValue = null;
        public int tag = -1;
    }

    public EditComboListViewAdapter(@NonNull Activity activity, @LayoutRes int resource, ArrayList<Description> list) {

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

            holder.txtName = (TextView) cell.findViewById(R.id.txt_name);
            holder.txtValue = (TextView) cell.findViewById(R.id.txt_value);

            cell.setTag(holder);
        } else {
            holder = (Holder) cell.getTag();
        }

        Description description = mListData.get(position);

        holder.txtName.setText(description.name);
        holder.txtValue.setText(description.value);
        return cell;
    }
}
