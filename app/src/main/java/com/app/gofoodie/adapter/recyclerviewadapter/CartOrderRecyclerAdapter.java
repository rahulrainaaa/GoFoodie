package com.app.gofoodie.adapter.recyclerviewadapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.cartOrder.CartOrder;

import java.util.List;

/**
 * Activity to show all the orders to be placed with delivery date assigned.
 */
@SuppressWarnings("unused")
public class CartOrderRecyclerAdapter extends RecyclerView.Adapter<CartOrderRecyclerAdapter.ItemHolder> {

    public static final String TAG = "CartOrderRecyclerAdapter";

    /**
     * private class Data members.
     */
    private List<CartOrder> mList = null;
    private View.OnClickListener onClickListener = null;

    public CartOrderRecyclerAdapter(View.OnClickListener onClickListener, List<CartOrder> categories) {

        this.mList = categories;
        this.onClickListener = onClickListener;
    }

    /**
     * {@link RecyclerView.Adapter} adapter class override methods.
     */
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_cart_order, parent, false);
        return new ItemHolder(itemView, onClickListener);

    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {

        CartOrder cartOrder = mList.get(position);
        holder.iBtnEdit.setTag(position);

        holder.txtComboName.setText(cartOrder.comboName);
        holder.txtDate.setText("" + cartOrder.date);
        float payPrice = Float.valueOf(cartOrder.payPrice.trim());
//        float zonePrice = Float.valueOf(cartOrder.zoneShippingCharge.trim());
        holder.txtPrice.setText(payPrice + " AED");

        if (cartOrder.type.toLowerCase().trim().equals("nonveg")) {

            holder.layoutVeg.setVisibility(View.GONE);
            holder.layoutNonVeg.setVisibility(View.VISIBLE);
        } else {

            holder.layoutVeg.setVisibility(View.VISIBLE);
            holder.layoutNonVeg.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * @class ItemHolder
     * @desc {@link RecyclerView.ViewHolder} holder static class for Recycler View items.
     */
    @SuppressWarnings("unused")
    public static class ItemHolder extends RecyclerView.ViewHolder {

        public final CardView cv;
        public final LinearLayout layoutVeg;
        public final LinearLayout layoutNonVeg;
        public final TextView txtComboName;
        public final TextView txtDate;
        public final TextView txtPrice;
        public final ImageButton iBtnEdit;

        ItemHolder(View itemView, View.OnClickListener onClickListener) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            layoutVeg = itemView.findViewById(R.id.layout_veg);
            layoutNonVeg = itemView.findViewById(R.id.layout_nonveg);
            txtComboName = itemView.findViewById(R.id.txt_combo_name);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtPrice = itemView.findViewById(R.id.txt_price);
            iBtnEdit = itemView.findViewById(R.id.ibtn_edit);
            iBtnEdit.setOnClickListener(onClickListener);
        }
    }

}
