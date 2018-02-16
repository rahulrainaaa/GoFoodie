package com.app.gofoodie.adapter.gridViewAdapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.model.comboPlan.Comboplan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @class ComboPlanGridAdapter
 * @desc Adapter class for handling Restaurant Grid View.
 */
@SuppressWarnings("unused")
public class ComboPlanGridAdapter extends ArrayAdapter<Comboplan> {

    public static final String TAG = "ComboPlanGridAdapter";
    private final int mLayoutResource;
    /**
     * Adapter Class private data members.
     */
    private LayoutInflater mInflater = null;
    private Activity mActivity = null;
    private ArrayList<Comboplan> mComboList = null;
    private View.OnClickListener mClickListener = null;

    public ComboPlanGridAdapter(@NonNull Activity activity, View.OnClickListener clickListener, @LayoutRes int resource, ArrayList<Comboplan> list) {

        super(activity, resource, list);
        this.mActivity = activity;
        this.mComboList = list;
        this.mLayoutResource = resource;
        this.mClickListener = clickListener;
        this.mInflater = activity.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Comboplan comboplan = mComboList.get(position);

        View view = convertView;
        Holder holder;

        if (view == null) {
            view = mInflater.inflate(mLayoutResource, null);
            holder = new Holder();

            holder.imgCombo = view.findViewById(R.id.image_combo);
            holder.txtComboName = view.findViewById(R.id.txt_combo_name);
            holder.imgVeg = view.findViewById(R.id.img_veg);
            holder.imgNonveg = view.findViewById(R.id.img_nonveg);
            holder.iBtnAddToCart = view.findViewById(R.id.ibtn_cart);
            holder.txtTags = view.findViewById(R.id.txt_tags);
            holder.txtPrice = view.findViewById(R.id.txt_price);

            holder.iBtnAddToCart.setOnClickListener(mClickListener);
            holder.imgCombo.setOnClickListener(mClickListener);
            view.setTag(holder);
        } else {

            holder = (Holder) view.getTag();
        }

        holder.iBtnAddToCart.setTag(comboplan);
        holder.imgCombo.setTag(comboplan);
        holder.txtComboName.setText(comboplan.getComboName());
        holder.txtTags.setText(comboplan.getCuisineName() != null ? comboplan.getCuisineName() : "");
        holder.txtPrice.setText(comboplan.getComboPayPrice() + " AED");

        holder.imgCombo.setOnClickListener(mClickListener);
        try {

            Picasso.with(mActivity).load(comboplan.getComboImage()).error(R.drawable.img_default_combo).into(holder.imgCombo);
        } catch (Exception exception) {

            exception.printStackTrace();
            Toast.makeText(mActivity, "Exception: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (comboplan.getComboType().contains("nonveg")) {

            holder.imgVeg.setVisibility(View.GONE);
            holder.imgNonveg.setVisibility(View.VISIBLE);
        } else {

            holder.imgVeg.setVisibility(View.VISIBLE);
            holder.imgNonveg.setVisibility(View.GONE);
        }

        return view;
    }

    /**
     * @class Holder
     * @desc Public static holder class for holding the xml view reference.
     */
    @SuppressWarnings("unused")
    public static class Holder {

        public ImageView imgCombo = null;
        public TextView txtComboName = null;
        public ImageView imgVeg = null;
        public ImageView imgNonveg = null;
        public ImageButton iBtnAddToCart = null;
        public TextView txtTags = null;
        public TextView txtPrice = null;
        public int tag = -1;
    }
}
