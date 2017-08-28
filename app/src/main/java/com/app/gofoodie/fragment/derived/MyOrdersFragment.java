package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.listviewadapter.MyOrdersListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * @class NetworkErrorFragment
 * @desc {@link BaseFragment} Fragment class to handle My Order list UI screen.
 */
public class MyOrdersFragment extends BaseFragment {

    ListView mListViewOrders = null;
    private ArrayList<String> mOrderList = new ArrayList<String>();
    private MyOrdersListViewAdapter mAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_my_order, container, false);

        mListViewOrders = (ListView) view.findViewById(R.id.list_view_my_orders);

        mOrderList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {

            mOrderList.add("My Order " + i);
        }

        mAdapter = new MyOrdersListViewAdapter(getDashboardActivity(), R.layout.item_listview_my_orders, mOrderList);
        mListViewOrders.setAdapter(mAdapter);
        return view;

    }

    @Override
    public void fragQuitCallback() {

    }
}
