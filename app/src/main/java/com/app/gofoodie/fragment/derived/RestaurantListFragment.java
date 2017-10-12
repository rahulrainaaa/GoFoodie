package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.DashboardActivity;
import com.app.gofoodie.activity.derived.LocationActivity;
import com.app.gofoodie.adapter.listviewadapter.RestaurantListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.LocationUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class RestaurantListFragment
 * @desc {@link BaseFragment} Fragment class to show list if restaurants of selected location UI screen.
 */
public class RestaurantListFragment extends BaseFragment implements NetworkCallbackListener {

    private ListView restaurantListView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_restaurant_list_main, container, false);
        Toast.makeText(getActivity(), "Restaurant List fragment", Toast.LENGTH_SHORT).show();
        setHasOptionsMenu(true);
        restaurantListView = (ListView) view.findViewById(R.id.listview_restaurants);
        ArrayList list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add("Restaurant: " + i);
        }
        RestaurantListViewAdapter adapter = new RestaurantListViewAdapter(getActivity(), R.layout.item_listview_restaurant, list);
        restaurantListView.setAdapter(adapter);

//        getDashboardActivity().startActivity(new Intent(getActivity(), LocationActivity.class));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        String location_id = LocationUtils.getInstance().getLocationId(getActivity(), "");
        String location_name = LocationUtils.getInstance().getLocationName(getActivity(), "");

        if (location_id.trim().isEmpty()) {

            Toast.makeText(getActivity(), "Pick your location.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LocationActivity.class));
        }

        String url = Network.URL_GET_RESTAURANT + location_id;
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, getDashboardActivity(), this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_frag_list, menu);
        MenuItem item = menu.findItem(R.id.menu_item_search);
        SearchView searchView = new SearchView(((DashboardActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), query + ": Searching...", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void fragQuitCallback() {

    }

    /**
     * {@link NetworkCallbackListener} network response callback method.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        switch (requestCode) {
            case 1:

                Toast.makeText(getActivity(), "" + rawObject.toString(), Toast.LENGTH_SHORT).show();
                break;
            case 2:

                break;
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        switch (requestCode) {
            case 1:

                Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                break;
            case 2:

                break;
        }
    }
}
