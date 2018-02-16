package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.model.shortlisted.Shortlisted;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @class ShortlistedRestaurantListViewAdapter
 * @desc {@link ArrayAdapter<Shortlisted>} class to populate list of shortlisted restaurant(s) to view and remove single.
 */
@SuppressWarnings("unused")
public class ShortlistedRestaurantListViewAdapter extends ArrayAdapter<Shortlisted> {

    public static final String TAG = "ShortlistedRestaurantListViewAdapter";

    /**
     * Class private data member(s).
     */
    private Activity mActivity = null;
    private ArrayList<Shortlisted> mList = null;
    private final int mLayoutResourceId;
    private View.OnClickListener mClickListener = null;

    /**
     * @param activity reference
     * @param resource reference
     * @param list     reference
     * @param listener reference
     */
    public ShortlistedRestaurantListViewAdapter(@NonNull Activity activity, @LayoutRes int resource, ArrayList<Shortlisted> list, View.OnClickListener listener) {

        super(activity, resource, list);
        this.mActivity = activity;
        this.mList = list;
        this.mLayoutResourceId = resource;
        this.mClickListener = listener;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Shortlisted shortlisted = mList.get(position);
        View cell = convertView;
        Holder holder;
        if (convertView == null) {

            cell = mActivity.getLayoutInflater().inflate(mLayoutResourceId, null);
            holder = new Holder();
            holder.imgIcon = cell.findViewById(R.id.img_restaurant);
            holder.imgVeg = cell.findViewById(R.id.img_veg);
            holder.imgNonVeg = cell.findViewById(R.id.img_nonveg);
            holder.ratingBar = cell.findViewById(R.id.rating_bar);
            holder.txtName = cell.findViewById(R.id.txt_branch_name);
            holder.txtTags = cell.findViewById(R.id.txt_branch_tags);
            holder.txtCount = cell.findViewById(R.id.txt_rating_count);
            holder.BtnCellType = cell.findViewById(R.id.ibtn_remove);
            holder.BtnView = cell.findViewById(R.id.ibtn_view);


            cell.setTag(holder);
        } else {

            holder = (Holder) cell.getTag();
        }

        holder.BtnView.setTag(shortlisted);
        holder.BtnCellType.setTag(shortlisted);

        if (shortlisted.type.toLowerCase().equals("1")) { // 1 = veg

            holder.imgVeg.setVisibility(View.VISIBLE);
            holder.imgNonVeg.setVisibility(View.GONE);

        } else if (shortlisted.type.toLowerCase().equals("2")) {    // 2 = nonveg

            holder.imgVeg.setVisibility(View.GONE);
            holder.imgNonVeg.setVisibility(View.VISIBLE);

        } else {                                                    // else = any (both)

            holder.imgVeg.setVisibility(View.VISIBLE);
            holder.imgNonVeg.setVisibility(View.VISIBLE);

        }

        try {

            Picasso.with(mActivity).load(shortlisted.profileIcon.trim()).error(R.drawable.icon_restaurant_default).into(holder.imgIcon);
            holder.ratingBar.setRating(Float.parseFloat(shortlisted.avgRating.trim()));

        } catch (Exception exc) {

            exc.printStackTrace();
            Toast.makeText(mActivity, "EXCEPTION: " + exc.getMessage(), Toast.LENGTH_SHORT).show();
        }

        holder.txtName.setText(shortlisted.branchName);
        holder.txtTags.setText(shortlisted.tags);
        holder.txtCount.setText("(" + shortlisted.countRating + ")");
        holder.BtnCellType.setOnClickListener(mClickListener);
        holder.BtnView.setOnClickListener(mClickListener);

        return cell;
    }

    /**
     * Holder class to hold the reference of list view cell.
     */
    @SuppressWarnings("unused")
    private static class Holder {

        public ImageView imgIcon = null;
        public ImageView imgVeg = null;
        public ImageView imgNonVeg = null;
        public Button BtnView = null;
        public Button BtnCellType = null;
        public TextView txtName = null;
        public TextView txtTags = null;
        public TextView txtCount = null;
        public RatingBar ratingBar = null;
        public int tag = -1;
    }
}
