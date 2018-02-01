package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.fragment.derived.CartFragment;
import com.app.gofoodie.model.cart.Cart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @class CartListViewAdapter
 * @desc Class to handle the Adapter.ListView for Cart.
 */
public class CartListViewAdapter extends ArrayAdapter<Cart> {

    public static final String TAG = "RestaurantListViewAdapter";

    /**
     * Class private data member(s).
     */
    private Activity mActivity = null;
    private ArrayList<Cart> mListData = null;
    private int mLayoutResourceId;
    private CartFragment.CartItemClickListener mClickListener = null;

    public CartListViewAdapter(@NonNull Activity activity, CartFragment.CartItemClickListener clickListener, @LayoutRes int resource, ArrayList<Cart> list) {

        super(activity, resource, list);
        this.mActivity = activity;
        this.mListData = list;
        this.mLayoutResourceId = resource;
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View cell = convertView;
        Holder holder = null;
        if (convertView == null) {

            cell = mActivity.getLayoutInflater().inflate(mLayoutResourceId, null);
            holder = new Holder();
            holder.imgCombo = (ImageView) cell.findViewById(R.id.img_combo);
            holder.txtName = (TextView) cell.findViewById(R.id.txt_name);
            holder.txtPrice = (TextView) cell.findViewById(R.id.txt_price);
            holder.iBtnDelete = (ImageButton) cell.findViewById(R.id.ibtn_delete);
            holder.btnQty = (Button) cell.findViewById(R.id.btn_qty);
            holder.imgVeg = (ImageView) cell.findViewById(R.id.ibtn_veg);
            holder.imgNonveg = (ImageView) cell.findViewById(R.id.ibtn_nonveg);

            holder.imgCombo.setOnClickListener(mClickListener);
            holder.iBtnDelete.setOnClickListener(mClickListener);
            holder.btnQty.setOnClickListener(mClickListener);

            cell.setTag(holder);

        } else {

            holder = (Holder) cell.getTag();
        }

        Cart cart = mListData.get(position);

        try {

            Picasso.with(mActivity).load(cart.getImage()).error(R.drawable.img_default_combo).into(holder.imgCombo);
        } catch (Exception exception) {

            exception.printStackTrace();
        }

        holder.txtName.setText(cart.getComboName());
        holder.txtPrice.setText(cart.getPayPrice().trim() + " AED");// + " + " + cart.getZoneShippingCharge().trim());
        holder.btnQty.setText("Qty: " + cart.getQuantity());

        if (cart.getType().toLowerCase().equals("nonveg")) {

            holder.imgVeg.setVisibility(View.GONE);
            holder.imgNonveg.setVisibility(View.VISIBLE);

        } else {

            holder.imgVeg.setVisibility(View.VISIBLE);
            holder.imgNonveg.setVisibility(View.GONE);
        }

        holder.iBtnDelete.setTag(cart);
        holder.btnQty.setTag(cart);
        holder.imgCombo.setTag(cart);

        return cell;
    }

    /**
     * @class Holder
     * @desc Class to hold the reference/id of list view items component(s).
     */
    private static class Holder {

        public ImageView imgCombo = null;
        public TextView txtName = null;
        public TextView txtPrice = null;
        public ImageButton iBtnDelete = null;
        public Button btnQty = null;
        public ImageView imgVeg = null;
        public ImageView imgNonveg = null;
        public int tag = -1;
    }
}
