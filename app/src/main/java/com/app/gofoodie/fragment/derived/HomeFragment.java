package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.recyclerviewadapter.ShortlistRestaurantsRecyclerAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * @class HomeFragment
 * @desc {@link BaseFragment} Fragment class to handle Home UI screen.
 */
public class HomeFragment extends BaseFragment {

    /**
     * Class private data members for Shortlisted Restaurants. {@link RecyclerView}.
     */
    private RecyclerView mRVShortlistRestaurant = null;
    private ShortlistRestaurantsRecyclerAdapter mShortlistRestaurantRVAdapter = null;
    private ArrayList<String> mListShortlistRestautant = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_home, container, false);

        for (int i = 0; i < 20; i++) {
            mListShortlistRestautant.add("Rest: " + i);
        }
        // RecyclerView - Shortlisted Restaurants Handling Code.
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRVShortlistRestaurant = (RecyclerView) view.findViewById(R.id.rv_shortlist_restaurants);
        mRVShortlistRestaurant.setHasFixedSize(true);
        mRVShortlistRestaurant.setLayoutManager(categoryLayoutManager);
        mShortlistRestaurantRVAdapter = new ShortlistRestaurantsRecyclerAdapter(getActivity(), R.layout.item_rv_shortlist_restaurant, null, mListShortlistRestautant);
        mRVShortlistRestaurant.setAdapter(mShortlistRestaurantRVAdapter);
        mShortlistRestaurantRVAdapter.notifyDataSetChanged();

        Toast.makeText(getActivity(), "Home Fragment.", Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void fragQuitCallback() {

    }
}
