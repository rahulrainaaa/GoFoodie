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
import com.app.gofoodie.activity.derived.AddShortlistedRestaurants;
import com.app.gofoodie.activity.derived.ComboPlanActivity;
import com.app.gofoodie.activity.derived.RestaurantProfileActivity;
import com.app.gofoodie.adapter.listviewadapter.ShortlistedRestaurantListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.dashboardHandler.DashboardInterruptListener;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.shortlisted.Shortlisted;
import com.app.gofoodie.model.shortlisted.ShortlistedRestaurants;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class ShortlistedRestaurantListFragment
 * @desc {@link BaseFragment} Fragment to show list of shortlisted restaurant(s) to view profile and see combo plan(s).
 */
@SuppressWarnings("unused")
public class ShortlistedRestaurantListFragment extends BaseFragment implements NetworkCallbackListener, View.OnClickListener {

    public static final String TAG = "ShortlistedRestaurantListFragment";

    /**
     * Class private data member(s).
     */
    private ListView mListView = null;

    /**
     * {@link BaseFragment} fragment callback method(s).
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_restaurant_list_main, container, false);
        setHasOptionsMenu(true);
        mListView = view.findViewById(R.id.listview_restaurants);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (GlobalData.ShowCart) {

            GlobalData.ShowCart = false;
            getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.CART);
        } else {

            refreshList();
        }
    }

    /**
     * Method to refresh the list of shortlisted restaurant(s).
     */
    private void refreshList() {

        String url = Network.URL_GET_SLR + getSession().getData().getCustomerId();
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, getDashboardActivity(), this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    /**
     * {@link NetworkCallbackListener} http response callback listener.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {         // Fetched all shortlisted restaurant(s).

            handleShortlistRestaurantResponse(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "Http fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to handle the shortlisted restaurants response from API.
     *
     * @param json reference
     */
    private void handleShortlistRestaurantResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        ShortlistedRestaurants shortlistedRestaurants = (ShortlistedRestaurants) parser.getModel(json.toString(), ShortlistedRestaurants.class, null);
        if (shortlistedRestaurants.statusCode == 200) {
            ArrayList<Shortlisted> mList = (ArrayList<Shortlisted>) shortlistedRestaurants.shortlisted;
            ShortlistedRestaurantListViewAdapter mAdapter = new ShortlistedRestaurantListViewAdapter(getDashboardActivity(), R.layout.item_see_shortlisted_restaurant, mList, this);
            mListView.setAdapter(mAdapter);
        } else if (shortlistedRestaurants.statusCode == 204) {

            callToShortlistActivity();

        } else {

            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("" + shortlistedRestaurants.statusMessage)
                    .setConfirmText("Ok")
                    .show();
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

        Intent intent = new Intent(getActivity(), ComboPlanActivity.class);
        intent.putExtra("branch_id", shortlisted.branchId);
        startActivity(intent);
    }

    /**
     * Method to call shortlist Activity if user select on Alert Dialog.
     */
    private void callToShortlistActivity() {


        // Show alert dialog to show AddNewRestaurantActivity.
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Alert")
                .setContentText("No Shortlist Restaurant found.\nDo you want to add new?")
                .setConfirmText("Yes")
                .setConfirmClickListener(sDialog -> {

                    startActivity(new Intent(getActivity(), AddShortlistedRestaurants.class));
                    sDialog.dismissWithAnimation();
                })
                .setCancelText("No")
                .setCancelClickListener(sweetAlertDialog -> {

                    Toast.makeText(getActivity(), "You have not shortlisted any restaurant.", Toast.LENGTH_SHORT).show();
                    sweetAlertDialog.dismissWithAnimation();
                })
                .show();

    }

    @Override
    public void fragQuitCallback() {

    }

}
