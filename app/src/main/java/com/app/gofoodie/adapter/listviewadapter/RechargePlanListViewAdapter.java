package com.app.gofoodie.adapter.listviewadapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.model.RechargePlan.Subscriptionplan;

import java.util.ArrayList;

public class RechargePlanListViewAdapter extends ArrayAdapter<Subscriptionplan> {

    public static final String TAG = "RechargePlanListViewAdapter";

    private Activity mActivity = null;
    private ArrayList<Subscriptionplan> mListData = null;
    private int mLayoutResourceId;

    private static class Holder {

        public TextView txtRechargePlan = null;
        public TextView txtRechargeDesc = null;
        public TextView txtRechargePrice = null;
        public TextView txtRechargeDays = null;
        public int tag = -1;
    }

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
            holder.txtRechargePlan = (TextView) cell.findViewById(R.id.recharge_plan);
            holder.txtRechargeDesc = (TextView) cell.findViewById(R.id.recharge_desc);
            holder.txtRechargePrice = (TextView) cell.findViewById(R.id.recharge_price);
            holder.txtRechargeDays = (TextView) cell.findViewById(R.id.recharge_days);
            cell.setTag(holder);

        } else {

            holder = (Holder) cell.getTag();
        }

        holder.txtRechargePlan.setText(plan.name);
        holder.txtRechargeDesc.setText(plan.description);
        holder.txtRechargePrice.setText("AED " + plan.payAmount);
        holder.txtRechargeDays.setText(plan.validityDays + " Days");
        return cell;
    }
}
