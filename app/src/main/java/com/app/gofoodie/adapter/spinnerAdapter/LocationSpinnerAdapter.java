package com.app.gofoodie.adapter.spinnerAdapter;


import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.location.Locaton;

import java.util.ArrayList;

/**
 * Adapter class for handing data in spinner for picking location on {@link com.app.gofoodie.activity.derived.AddressChangeRequestActivity}.
 */
public class LocationSpinnerAdapter extends ArrayAdapter<Locaton> {

    public static final String TAG = "LocationSpinnerAdapter";

    /**
     * Class private data member(s).
     */
    private ArrayList<Locaton> mLocationList = null;
    private Activity mActivity = null;
    private int mResourceId = -1;

    public LocationSpinnerAdapter(@NonNull Activity activity, int resource, ArrayList<Locaton> arrayList) {
        super(activity, resource, arrayList);

        this.mActivity = activity;
        this.mResourceId = resource;
        this.mLocationList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView textView;

        if (convertView != null) {

            textView = (TextView) convertView;

        } else {

            textView = (TextView) mActivity.getLayoutInflater().inflate(R.layout.item_spinner_text_view, null);
        }

        textView.setTextColor(Color.BLACK);
        textView.setText(mLocationList.get(position).getAreaName());
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_drop_down, 0);
        textView.setPadding(20, 20, 4, 20);

        if (position == 0) {

            textView.setTextColor(Color.GRAY);
        } else {

            textView.setTextColor(Color.BLACK);
        }
        return textView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView textView;

        if (convertView == null) {

            textView = (TextView) mActivity.getLayoutInflater().inflate(R.layout.item_spinner_text_view, null);
            textView.setTextColor(Color.BLACK);
            textView.setPadding(20, 20, 4, 20);
        } else {

            textView = (TextView) convertView;
        }

        textView.setText(mLocationList.get(position).getAreaName());


        if (position == 0) {

            textView.setTextColor(Color.GRAY);
        } else {

            textView.setTextColor(Color.BLACK);
        }

        return textView;

    }
}
