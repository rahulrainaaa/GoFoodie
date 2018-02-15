package com.app.gofoodie.adapter.viewflipperadapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Home Page Banner {@link android.widget.AdapterViewFlipper} adapter class.
 */
public class HomeImageViewFlipperAdapter extends ArrayAdapter<String> {

    /**
     * private data members.
     */
    private String[] mList = null;
    private Activity mActiity = null;
    private LayoutInflater mInflater = null;
    private int mResourceId;

    /**
     * Constructor method to initialize the class data members.
     *
     * @param activity reference
     * @param resource reference
     * @param list     reference
     */
    public HomeImageViewFlipperAdapter(@NonNull Activity activity, @LayoutRes int resource, String[] list) {
        super(activity, resource, list);
        this.mList = list;
        this.mResourceId = resource;
        this.mActiity = activity;
        this.mInflater = activity.getLayoutInflater();
    }

    /**
     * {@link ArrayAdapter} class override method.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Holder holder;
        View v = convertView;
        ImageView view = null;

        if (v == null) {

            v = mInflater.inflate(mResourceId, null);
            view = (ImageView) v;
            holder = new Holder();
            holder.i = mList[position];
            holder.img = view;
            view.setTag(holder);

        } else {

            holder = (Holder) view.getTag();
            view = holder.img;

            if (holder.i != mList[position]) {

                Picasso.with(mActiity).load(mList[position]).into(view);
                holder.i = mList[position];
                view.setTag(holder);
            }
        }
        Picasso.with(mActiity).load(mList[position]).into(view);
        return view;
    }

    /**
     * Static child class to hold the view id for cell reusability.
     */
    private static class Holder {
        ImageView img = null;
        String i = null;

    }
}
