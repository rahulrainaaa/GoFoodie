package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.recyclerviewadapter.ShortlistRestaurantsRecyclerAdapter;
import com.app.gofoodie.adapter.viewflipperadapter.HomeImageViewFlipperAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class HomeFragment
 * @desc {@link BaseFragment} Fragment class to handle Home UI screen.
 */
public class HomeFragment extends BaseFragment implements View.OnTouchListener {

    /**
     * Data members for Banner slide show {@link android.widget.AdapterViewFlipper}.
     */
    private AdapterViewFlipper mFlipperBanner = null;
    private HomeImageViewFlipperAdapter mFlipperAdapter = null;

    /**
     * Data members for Shortlisted Restaurants {@link RecyclerView}.
     */
    private RecyclerView mRVShortlistRestaurant = null;
    private ShortlistRestaurantsRecyclerAdapter mShortlistRestaurantRVAdapter = null;
    private ArrayList<String> mListShortlistRestaurant = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_home, container, false);

        // AdapterViewFlipper - Banner slide show Handling Code.
        mFlipperBanner = (AdapterViewFlipper) view.findViewById(R.id.banner_adapterviewflipper);
        mFlipperAdapter = new HomeImageViewFlipperAdapter(getActivity(), R.layout.image_banner_layout, Constants.BANNER_IMAGES);
        mFlipperBanner.setAdapter(mFlipperAdapter);
        mFlipperBanner.setFlipInterval(4000);
        mFlipperBanner.startFlipping();
        mFlipperBanner.setAutoStart(true);
        mFlipperBanner.setOnTouchListener(this);
        mFlipperBanner.setInAnimation(getActivity(), android.R.animator.fade_in);
        mFlipperBanner.setOutAnimation(getActivity(), android.R.animator.fade_out);

        for (int i = 0; i < 20; i++) {
            mListShortlistRestaurant.add("Restaurant " + i);
        }

        // RecyclerView - Shortlisted Restaurants Handling Code.
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRVShortlistRestaurant = (RecyclerView) view.findViewById(R.id.rv_shortlist_restaurants);
        mRVShortlistRestaurant.setHasFixedSize(true);
        mRVShortlistRestaurant.setLayoutManager(categoryLayoutManager);
        mShortlistRestaurantRVAdapter = new ShortlistRestaurantsRecyclerAdapter(getActivity(), R.layout.item_rv_shortlist_restaurant, null, mListShortlistRestaurant);
        mRVShortlistRestaurant.setAdapter(mShortlistRestaurantRVAdapter);
        mShortlistRestaurantRVAdapter.notifyDataSetChanged();

        JSONObject jsonHomeDashboardRequest = new JSONObject();
        try {
            jsonHomeDashboardRequest.put("", "");
        } catch (JSONException excJson) {
            excJson.printStackTrace();
        }

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, null, jsonHomeDashboardRequest, "URL", NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executePost();

        return view;
    }

    @Override
    public void fragQuitCallback() {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:       // Action to show next banner display image.
                mFlipperBanner.showNext();
                break;
        }

        return false;
    }
}
