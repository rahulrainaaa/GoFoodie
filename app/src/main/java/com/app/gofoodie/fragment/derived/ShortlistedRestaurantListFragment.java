package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.ComboPlanActivity;
import com.app.gofoodie.activity.derived.RestaurantProfileActivity;
import com.app.gofoodie.adapter.listviewadapter.ShortlistedRestaurantListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.shortlisted.Shortlisted;
import com.app.gofoodie.model.shortlisted.ShortlistedRestaurants;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class ShortlistedRestaurantListFragment
 * @desc {@link BaseFragment} Fragment to show list of shortlisted restaurant(s) to view profile and see combo plan(s).
 */
public class ShortlistedRestaurantListFragment extends BaseFragment implements NetworkCallbackListener, View.OnClickListener {

    public static final String TAG = "ShortlistedRestaurantListFragment";

    /**
     * Class private data member(s).
     */
    private ListView mListView = null;
    private ArrayList<Shortlisted> mList = null;
    private ShortlistedRestaurantListViewAdapter mAdapter = null;

    /**
     * {@link BaseFragment} fragment callback method(s).
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_restaurant_list_main, container, false);
        Toast.makeText(getActivity(), "Restaurant List fragment", Toast.LENGTH_SHORT).show();
        setHasOptionsMenu(true);
        mListView = (ListView) view.findViewById(R.id.listview_restaurants);

        String url = Network.URL_GET_SLR + "1"; //SessionUtils.getInstance().getSession().getData().getCustomerId();
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, getDashboardActivity(), this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.menu_frag_list, menu);
//        MenuItem item = menu.findItem(R.id.menu_item_search);
//        SearchView searchView = new SearchView(((DashboardActivity) getActivity()).getSupportActionBar().getThemedContext());
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//        MenuItemCompat.setActionView(item, searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getActivity(), query + ": Searching...", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }


    /**
     * {@link NetworkCallbackListener} http response callback listener.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        Toast.makeText(getActivity(), "Http Success: " + rawObject.toString(), Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {         // Fetched all shortlisted restaurant(s).

            handleShortlistRestaurantResponse(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "Http fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param json
     * @method handleShortlistRestaurantResponse
     * @desc Method to handle the shortlisted restaurants response from API.
     */
    private void handleShortlistRestaurantResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        ShortlistedRestaurants shortlistedRestaurants = (ShortlistedRestaurants) parser.getModel(json.toString(), ShortlistedRestaurants.class, null);
        if (shortlistedRestaurants.statusCode == 200) {
            mList = (ArrayList<Shortlisted>) shortlistedRestaurants.shortlisted;
            mAdapter = new ShortlistedRestaurantListViewAdapter(getDashboardActivity(), R.layout.item_see_shortlisted_restaurant, mList, this);
            mListView.setAdapter(mAdapter);
        } else {
            Toast.makeText(getActivity(), "" + shortlistedRestaurants.statusMessage, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * {@link android.view.View.OnClickListener} click listener callback method(s).
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ibtn_remove:

                viewComboPlans(view);
                break;

            case R.id.ibtn_view:

                showProfile(view);
                break;
        }
    }

    /**
     * @param view {@link android.widget.ImageButton} clicked reference.
     * @method showProfile
     * @desc Method to show the complete profile description.
     */
    private void showProfile(View view) {

        Shortlisted shortlisted = (Shortlisted) view.getTag();

        Intent intent = new Intent(getActivity(), RestaurantProfileActivity.class);
        intent.putExtra("data", shortlisted);
        intent.putExtra("mode", RestaurantProfileActivity.MODE.SHORTLISTED);
        startActivity(intent);
    }

    /**
     * @param view {@link android.widget.ImageButton} clicked reference.
     * @method viewComboPlans
     * @desc Method to see all combo plans from particular shortlisted restaurant.
     */
    private void viewComboPlans(View view) {

        Shortlisted shortlisted = (Shortlisted) view.getTag();

        Toast.makeText(getActivity(), "viewComboPlans", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ComboPlanActivity.class);
        intent.putExtra("branch_id", shortlisted.branchId);
        startActivity(intent);
    }


    @Override
    public void fragQuitCallback() {

    }

}
