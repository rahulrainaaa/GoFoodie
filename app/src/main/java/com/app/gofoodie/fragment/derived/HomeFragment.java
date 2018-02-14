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
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.gridViewAdapter.FeaturedRestaurantGridAdapter;
import com.app.gofoodie.adapter.recyclerviewadapter.FeaturedCombosRecyclerAdapter;
import com.app.gofoodie.adapter.viewflipperadapter.HomeImageViewFlipperAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Constants;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.featured.Featured;
import com.app.gofoodie.model.featured.FeaturedCombo;
import com.app.gofoodie.model.featured.FeaturedRestaurant;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.ListViewUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class HomeFragment
 * @desc {@link BaseFragment} Fragment class to handle Home UI screen.
 */
public class HomeFragment extends BaseFragment implements View.OnTouchListener, NetworkCallbackListener {

    public static final String TAG = "";
    private Featured mFeatured = null;

    /**
     * Data members for Banner slide show {@link android.widget.AdapterViewFlipper}.
     */
    private AdapterViewFlipper mFlipperBanner = null;
    private HomeImageViewFlipperAdapter mFlipperAdapter = null;
    private ScrollView mScrollView = null;

    /**
     * Data members for Shortlisted Restaurants {@link RecyclerView}.
     */
    private RecyclerView mRVShortlistRestaurant = null;
    private FeaturedCombosRecyclerAdapter mShortlistRestaurantRVAdapter = null;
    private ArrayList<FeaturedCombo> mListFeaturedCombos = new ArrayList<>();

    /**
     * Data members for Features restaurants {@link android.widget.GridView}.
     */
    private GridView mFeaturedRestaurantsGrid = null;
    private FeaturedRestaurantGridAdapter mFeaturedRestaurantAdapter = null;
    private ArrayList<FeaturedRestaurant> mListFeaturedRestaurant = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (getSession() != null) {

            Toast.makeText(getActivity(), "login_id: " + getSession().getData().getLoginId(), Toast.LENGTH_SHORT).show();
        }

        View view = inflater.inflate(R.layout.frag_home, container, false);
        mScrollView = view.findViewById(R.id.scroll_view);
        mRVShortlistRestaurant = view.findViewById(R.id.rv_shortlist_restaurants);
        mFeaturedRestaurantsGrid = view.findViewById(R.id.grid_view_banner);

        // AdapterViewFlipper - Banner slide show Handling Code.
        mFlipperBanner = view.findViewById(R.id.banner_adapterviewflipper);
        mFlipperAdapter = new HomeImageViewFlipperAdapter(getActivity(), R.layout.image_banner_layout, Constants.BANNER_IMAGES);
        mFlipperBanner.setAdapter(mFlipperAdapter);
        mFlipperBanner.setFlipInterval(4000);
        mFlipperBanner.startFlipping();
        mFlipperBanner.setAutoStart(true);
        mFlipperBanner.setOnTouchListener(this);
        mFlipperBanner.setInAnimation(getActivity(), android.R.animator.fade_in);
        mFlipperBanner.setOutAnimation(getActivity(), android.R.animator.fade_out);

        // Network Handler to get the dashboard home screen content.
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, getDashboardActivity(), this, new JSONObject(), Network.URL_GET_DASHBOARD, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();

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

    /**
     * {@link NetworkCallbackListener} HTTP callback listener.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        switch (requestCode) {

            case 1:

                handleDashboardResponse(rawObject);
                break;
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "Http Fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param json
     * @desc Method to handle the dashboard response got from http.
     * @method handleDashboardResponse
     */
    private void handleDashboardResponse(JSONObject json) {

        //Parse the json response into model.
        ModelParser modelParser = new ModelParser();
        mFeatured = (Featured) modelParser.getModel(json.toString(), Featured.class, null);

        // populate Featured Combos.
        mListFeaturedCombos = (ArrayList<FeaturedCombo>) mFeatured.featuredCombos;
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRVShortlistRestaurant.setHasFixedSize(true);
        mRVShortlistRestaurant.setLayoutManager(categoryLayoutManager);
        mShortlistRestaurantRVAdapter = new FeaturedCombosRecyclerAdapter(getActivity(), R.layout.item_rv_shortlist_restaurant, null, mListFeaturedCombos);
        mRVShortlistRestaurant.setAdapter(mShortlistRestaurantRVAdapter);
        mShortlistRestaurantRVAdapter.notifyDataSetChanged();

        // populate Featured Restaurants.
        mListFeaturedRestaurant = (ArrayList<FeaturedRestaurant>) mFeatured.featuredRestaurants;
        mFeaturedRestaurantAdapter = new FeaturedRestaurantGridAdapter(getDashboardActivity(), R.layout.item_gridview_featured_restaurants, mListFeaturedRestaurant);
        mFeaturedRestaurantsGrid.setAdapter(mFeaturedRestaurantAdapter);
        ListViewUtils.setGridViewHeightBasedOnChildren(mFeaturedRestaurantsGrid);

        mScrollView.fullScroll(ScrollView.FOCUS_UP);

    }

}
