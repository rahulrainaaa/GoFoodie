package com.app.gofoodie.adapter.recyclerviewadapter;

import android.app.Activity;
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


public class CartOrderRecyclerAdapter extends RecyclerView.Adapter<CartOrderRecyclerAdapter.ItemHolder> {

    /**
     * private class Data members.
     */
    private List<CartOrder> mList = null;
    private Activity mActivity = null;
    private int lastPosition;
    private View.OnClickListener onClickListener = null;
    private int mLayoutResourceId = -1;

    /**
     * @constructor CategoryRecyclerAdapter
     * @desc Constructor method for this class.
     */
    public CartOrderRecyclerAdapter(Activity activity, int layoutResourceId, View.OnClickListener onClickListener, List<CartOrder> categories) {
        this.mActivity = activity;
        this.mList = categories;
        this.onClickListener = onClickListener;
        this.mLayoutResourceId = layoutResourceId;
    }

    /**
     * {@link RecyclerView.Adapter} adapter class override methods.
     */
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(this.mLayoutResourceId, parent, false);
        ItemHolder itemHolder = new ItemHolder(itemView, onClickListener);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {

        CartOrder cartOrder = mList.get(position);
        holder.ibtnEdit.setTag(position);

        holder.txtComboName.setText(cartOrder.comboName);
        holder.txtDate.setText("" + cartOrder.date);

        String extrsPrice = "";


        if (Float.valueOf(cartOrder.zoneShippingCharge.trim()) > 0f) {

            extrsPrice = " + " + cartOrder.zoneShippingCharge.trim();
        }

        holder.txtPrice.setText("AED " + cartOrder.payPrice + extrsPrice);

        if (cartOrder.type.toLowerCase().trim().equals("nonveg")) {

            holder.layoutVeg.setVisibility(View.GONE);
            holder.layoutNonveg.setVisibility(View.VISIBLE);
        } else {

            holder.layoutVeg.setVisibility(View.VISIBLE);
            holder.layoutNonveg.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * @class ItemHolder
     * @desc {@link RecyclerView.ViewHolder} holder static class for Recycler View items.
     */
    public static class ItemHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public LinearLayout layoutVeg;
        public LinearLayout layoutNonveg;
        public TextView txtComboName;
        public TextView txtDate;
        public TextView txtPrice;
        public ImageButton ibtnEdit;

        ItemHolder(View itemView, View.OnClickListener onClickListener) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            layoutVeg = (LinearLayout) itemView.findViewById(R.id.layout_veg);
            layoutNonveg = (LinearLayout) itemView.findViewById(R.id.layout_nonveg);
            txtComboName = (TextView) itemView.findViewById(R.id.txt_combo_name);
            txtDate = (TextView) itemView.findViewById(R.id.txt_date);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price);
            ibtnEdit = (ImageButton) itemView.findViewById(R.id.ibtn_edit);
            ibtnEdit.setOnClickListener(onClickListener);
        }
    }

}
