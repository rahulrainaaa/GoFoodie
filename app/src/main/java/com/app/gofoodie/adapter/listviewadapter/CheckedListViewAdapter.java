package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.app.gofoodie.model.cuisine.Cuisine;

import java.util.ArrayList;

public class CheckedListViewAdapter extends ArrayAdapter<Cuisine> implements View.OnClickListener {

    private Activity mActivity = null;
    private ArrayList<Cuisine> mList = null;

    public CheckedListViewAdapter(@NonNull Activity activity, ArrayList<Cuisine> list) {
        super(activity, android.R.layout.simple_list_item_checked, list);
        this.mActivity = activity;
        this.mList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CheckedTextView mCheckTextView = null;
        if (convertView == null) {
            mCheckTextView = (CheckedTextView) mActivity.getLayoutInflater().inflate(android.R.layout.simple_list_item_checked, null);
            mCheckTextView.setOnClickListener(this);
        } else {
            mCheckTextView = (CheckedTextView) convertView;
        }

        mCheckTextView.setTag(position);
        mCheckTextView.setText(mList.get(position).getCuisineName());
        mCheckTextView.setChecked(mList.get(position).isChecked);

        return mCheckTextView;
    }

    @Override
    public void onClick(View view) {

        CheckedTextView ctv = (CheckedTextView) view;
        ctv.setChecked(!ctv.isChecked());
        int position = (int) ctv.getTag();
        mList.get(position).isChecked = ctv.isChecked();
    }

}
