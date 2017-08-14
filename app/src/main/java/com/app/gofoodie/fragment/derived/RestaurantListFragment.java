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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.DashboardActivity;
import com.app.gofoodie.activity.derived.LocationActivity;
import com.app.gofoodie.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * @class RestaurantListFragment
 * @desc {@link BaseFragment} Fragment class to show list if restaurants of selected location UI screen.
 */
public class RestaurantListFragment extends BaseFragment {

    private ListView mListView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_restaurant_list, container, false);
        Toast.makeText(getActivity(), "Restaurant List fragment", Toast.LENGTH_SHORT).show();
        setHasOptionsMenu(true);
        mListView = (ListView) view.findViewById(R.id.listview_restaurants);
        ArrayList list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add("Restaurant: " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked, list);
        mListView.setAdapter(adapter);
        getDashboardActivity().startActivity(new Intent(getActivity(), LocationActivity.class));
        return view;
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


}
