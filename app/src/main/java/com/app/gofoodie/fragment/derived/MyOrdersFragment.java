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
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.listviewadapter.MyOrdersListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @class NetworkErrorFragment
 * @desc {@link BaseFragment} Fragment class to handle My Order list UI screen.
 */
public class MyOrdersFragment extends BaseFragment implements NetworkCallbackListener {

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


    private void fetchMyOrders(String fromDate, String toDate) {

        String url = Network.URL_GET_MY_ORDER + "?customerid=" + getSession().getData().getCustomerId();
        if (fromDate != null && toDate != null) {

            url = url + "&from=" + fromDate.trim() + "&to=" + toDate.trim();
        }

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, getDashboardActivity(), this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    private void menuItemDateSelected() {

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year1, int month1, int day1, int year2, int month2, int day2) {

                        month1++;
                        month2++;
                        String fromDate = year1 + "-" + month1 + "-" + day1;
                        String toDate = year2 + "-" + month2 + "-" + day2;
                        fetchMyOrders(fromDate, toDate);
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

    /**
     * {@link NetworkCallbackListener} http response listener callback method.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        Toast.makeText(getActivity(), "Http Success: " + rawObject.toString(), Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {


        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "Http Fail: " + message, Toast.LENGTH_SHORT).show();
    }
}
