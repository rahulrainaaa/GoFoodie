package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.listviewadapter.MyOrdersListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

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
        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_my_orders, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_item_date:

                menuItemDateSelected();
                break;
            case R.id.menu_item_long_vacation:

                menuItemLongVacationSelected();
                break;
            case R.id.menu_item_short_vacation:

                menuItemShortVacationSelected();
                break;
            case R.id.menu_item_emergency:

                menuItemEmergencySelected();
                break;

        }
        return true;
    }

    @Override
    public void fragQuitCallback() {

    }

    private void menuItemDateSelected() {

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {

                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAutoHighlight(true);
        dpd.show(getFragmentManager(), "Select Date Range");

    }

    private void menuItemLongVacationSelected() {

    }

    private void menuItemShortVacationSelected() {

    }

    private void menuItemEmergencySelected() {

    }


}
