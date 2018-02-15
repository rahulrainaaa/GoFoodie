package com.app.gofoodie.adapter.gridViewAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.RestaurantBranchProfileActivity;
import com.app.gofoodie.model.featured.FeaturedRestaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @class FeaturedRestaurantGridAdapter
 * @desc Adapter class for showing Featured Restaurant Grid View on Dashboard Home Fragment UI.
 */
public class FeaturedRestaurantGridAdapter extends ArrayAdapter<FeaturedRestaurant> implements View.OnClickListener {

    public static final String TAG = "ComboPlanGridAdapter";

    /**
     * Adapter Class private data members.
     */
    private Activity mActivity = null;
    private ArrayList<FeaturedRestaurant> mComboList = null;
    private int mLayoutResource;
    private LayoutInflater mInflater = null;
    private FeaturedRestaurant mFeaturedRestaurant = null;

    public FeaturedRestaurantGridAdapter(@NonNull Activity activity, @LayoutRes int resource, ArrayList<FeaturedRestaurant> list) {
        super(activity, resource, list);
        this.mActivity = activity;
        this.mComboList = list;
        this.mLayoutResource = resource;
        this.mInflater = activity.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        mFeaturedRestaurant = mComboList.get(position);
        View view = convertView;
        Holder holder;

        if (view == null) {
            view = mInflater.inflate(mLayoutResource, null);
            holder = new Holder();
            holder.imgCombo = view.findViewById(R.id.image_restaurant);
            holder.imgVeg = view.findViewById(R.id.img_veg);
            holder.imgNonVeg = view.findViewById(R.id.img_nonveg);
            holder.txtComboName = view.findViewById(R.id.restaurant_name);
            view.setTag(holder);
            holder.imgCombo.setOnClickListener(this);
        } else {

            holder = (Holder) view.getTag();
        }

        try {
            holder.imgCombo.setTag(mFeaturedRestaurant.branchId);
            Picasso.with(mActivity).load(mFeaturedRestaurant.profileIcon).error(R.drawable.icon_restaurant_default).into(holder.imgCombo);
        } catch (Exception exc) {
            Log.e(TAG, "Picasso Exception while loading restaurant profile image: " + exc.getMessage());
        }
        holder.txtComboName.setText(mFeaturedRestaurant.branchName);

        // Check the type of restaurant branch.
        if (mFeaturedRestaurant.type.contains("1")) {        // veg = 1.

            holder.imgVeg.setVisibility(View.VISIBLE);
            holder.imgNonVeg.setVisibility(View.GONE);

        } else if (mFeaturedRestaurant.type.contains("2")) {        // nonveg = 2.

            holder.imgVeg.setVisibility(View.GONE);
            holder.imgNonVeg.setVisibility(View.VISIBLE);

        } else {                                                    // else = any (both).

            holder.imgVeg.setVisibility(View.GONE);
            holder.imgNonVeg.setVisibility(View.GONE);

        }

        return view;
    }

    @Override
    public void onClick(View view) {

        String branchId = (String) view.getTag();
        Intent intent = new Intent(mActivity, RestaurantBranchProfileActivity.class);
        intent.putExtra("branch_id", "" + branchId);
        mActivity.startActivity(intent);


    }

    /**
     * @class Holder
     * @desc Public static holder class for holding the xml view reference.
     */
    public static class Holder {

        public TextView txtComboName = null;
        public ImageView imgVeg = null;
        public ImageView imgNonVeg = null;
        public ImageView imgCombo = null;
        public int tag = -1;
    }


}
