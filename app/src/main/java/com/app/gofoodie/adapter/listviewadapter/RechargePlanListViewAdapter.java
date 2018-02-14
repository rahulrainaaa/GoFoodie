package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.rechargePlan.Subscriptionplan;

import java.util.ArrayList;

public class RechargePlanListViewAdapter extends ArrayAdapter<Subscriptionplan> {

    public static final String TAG = "RechargePlanListViewAdapter";

    private Activity mActivity = null;
    private ArrayList<Subscriptionplan> mListData = null;
    private int mLayoutResourceId;


    public RechargePlanListViewAdapter(@NonNull Activity activity, @LayoutRes int resource, ArrayList<Subscriptionplan> list) {

        super(activity, resource, list);
        this.mActivity = activity;
        this.mListData = list;
        this.mLayoutResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Subscriptionplan plan = mListData.get(position);
        View cell = convertView;
        Holder holder = null;
        if (convertView == null) {

            cell = mActivity.getLayoutInflater().inflate(mLayoutResourceId, null);
            holder = new Holder();
            holder.txtRechargePlan = cell.findViewById(R.id.recharge_plan);
            holder.txtRechargeDesc = cell.findViewById(R.id.recharge_desc);
            holder.txtRechargePrice = cell.findViewById(R.id.recharge_price);
            holder.txtRechargeDays = cell.findViewById(R.id.recharge_days);
            cell.setTag(holder);

        } else {

            holder = (Holder) cell.getTag();
        }

        holder.txtRechargePlan.setText(plan.name);
        holder.txtRechargeDesc.setText(plan.description);
        holder.txtRechargePrice.setText(plan.payAmount + " AED");

        if (Integer.parseInt(plan.validityDays.trim()) <= 0) {   // Recharge Plan.

            holder.txtRechargePlan.setTextColor(Color.parseColor("#519f2d"));   // Green
            holder.txtRechargeDays.setText("");

        } else {        // Subscription Plan.

            holder.txtRechargePlan.setTextColor(Color.parseColor("#F6333A"));   // Red
            holder.txtRechargeDays.setText(plan.validityDays + " Days");
        }

        return cell;
    }

    private static class Holder {

        public TextView txtRechargePlan = null;
        public TextView txtRechargeDesc = null;
        public TextView txtRechargePrice = null;
        public TextView txtRechargeDays = null;
        public int tag = -1;
    }
}
