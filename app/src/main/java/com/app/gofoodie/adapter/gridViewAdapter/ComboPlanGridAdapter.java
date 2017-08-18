package com.app.gofoodie.adapter.gridViewAdapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gofoodie.R;

import java.util.ArrayList;

/**
 * @class ComboPlanGridAdapter
 * @desc Adapter class for handling Restaurant Grid View.
 */
public class ComboPlanGridAdapter extends ArrayAdapter<String> {

    public static final String TAG = "ComboPlanGridAdapter";

    /**
     * Adapter Class private data members.
     */
    private Activity mActivity = null;
    private ArrayList<String> mComboList = null;
    private int mLayoutResource;
    public LayoutInflater mInflater = null;

    public ComboPlanGridAdapter(@NonNull Activity activity, @LayoutRes int resource, ArrayList<String> list) {
        super(activity, resource, list);
        this.mActivity = activity;
        this.mComboList = list;
        this.mLayoutResource = resource;
        this.mInflater = activity.getLayoutInflater();
    }

    /**
     * @class Holder
     * @desc Public static holder class for holding the xml view reference.
     */
    public static class Holder {

        public TextView txtComboName = null;
        public ImageView imgCombo = null;
        public int tag = -1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        Holder holder = null;

        if (view == null) {
            view = mInflater.inflate(mLayoutResource, null);
            holder = new Holder();

            view.setTag(holder);
        } else {

            holder = (Holder) view.getTag();
        }


        return view;
    }
}
