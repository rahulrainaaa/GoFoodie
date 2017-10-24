package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.ratings.Review;

import java.util.ArrayList;

/**
 * @class RatingListViewAdapter
 * @desc Activity class to handle and show the reviews of restaurant branch_id.
 */
public class RatingListViewAdapter extends ArrayAdapter<Review> {

    public static final String TAG = "RatingListViewAdapter";

    /**
     * Class private data member(s).
     */
    private Activity mActivity = null;
    private ArrayList<Review> mListData = null;
    private int mLayoutResourceId;

    /**
     * Holder class to hold the List View Cell reference.
     */
    private static class Holder {


        public TextView Name = null;
        public TextView Review = null;
        public RatingBar ratingBar = null;
        public int tag = -1;
    }

    public RatingListViewAdapter(@NonNull Activity activity, @LayoutRes int resource, ArrayList<Review> list) {

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

            holder.ratingBar = (RatingBar) cell.findViewById(R.id.rating_bar);
            holder.Name = (TextView) cell.findViewById(R.id.name);
            holder.Review = (TextView) cell.findViewById(R.id.review);

            cell.setTag(holder);
        } else {

            holder = (Holder) cell.getTag();
        }

        Review review = mListData.get(position);

        holder.Name.setText(review.customerName);
        holder.Review.setText(review.comment);
        holder.ratingBar.setRating(Float.parseFloat(review.rating.trim()));

        return cell;
    }
}
