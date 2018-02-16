package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.AddressChangeRequestActivity;
import com.app.gofoodie.activity.derived.ChangePasswordActivity;
import com.app.gofoodie.activity.derived.MyOrdersActivity;
import com.app.gofoodie.activity.derived.ShortlistedRestaurantsActivity;
import com.app.gofoodie.activity.derived.SplashActivity;
import com.app.gofoodie.activity.derived.UpdateProfileActivity;
import com.app.gofoodie.activity.derived.WeekPreferenceActivity;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.utility.SessionUtils;

/**
 * @class ProfileFragment
 * @desc {@link BaseFragment} Fragment class to handle Profile UI screen.
 */
@SuppressWarnings("unused")
public class ProfileFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = "ProfileFragment";

    /**
     * Class private data member(s).
     */
    private TextView mTxtName = null;
    private TextView mTxtMobile = null;
    private TextView mTxtEmail = null;

    /**
     * {@link BaseFragment} callback methods.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_profile, container, false);

        mTxtName = view.findViewById(R.id.txt_customer_name);
        mTxtMobile = view.findViewById(R.id.txt_email);
        mTxtEmail = view.findViewById(R.id.btn_phone);

        view.findViewById(R.id.btn_my_profile).setOnClickListener(this);
        view.findViewById(R.id.btn_my_orders).setOnClickListener(this);
        view.findViewById(R.id.btn_address_change_request).setOnClickListener(this);
        view.findViewById(R.id.btn_preference).setOnClickListener(this);
        view.findViewById(R.id.btn_shortlisted_restaurants).setOnClickListener(this);
        view.findViewById(R.id.btn_change_password).setOnClickListener(this);
        view.findViewById(R.id.btn_about_us).setOnClickListener(this);
        view.findViewById(R.id.btn_logout).setOnClickListener(this);

        // If customer = social user then hide change password option.
        if (getSession().getData().getSocial().toLowerCase().contains("yes")) {

            view.findViewById(R.id.btn_change_password).setVisibility(View.GONE);
        }

        CustomerProfileHandler customerProfileHandler = new CustomerProfileHandler(getActivity());
        customerProfileHandler.refresh(getDashboardActivity(), null);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        try {

            // Include 0s to make customer Id 7 characters in length.
            int len = CustomerProfileHandler.CUSTOMER.getProfile().getCustomerId().trim().length();

            StringBuilder cid = new StringBuilder();

            while (len < 7) {

                cid.append("0");
                len++;
            }

            cid.append(CustomerProfileHandler.CUSTOMER.getProfile().getCustomerId().trim());
            mTxtName.setText("" + CustomerProfileHandler.CUSTOMER.getProfile().getName().trim());
            mTxtEmail.setText("" + CustomerProfileHandler.CUSTOMER.getProfile().getEmail().trim() + "\nCustomer ID: C_" + cid.toString() + "");
            mTxtMobile.setText("" + CustomerProfileHandler.CUSTOMER.getProfile().getMobile1().trim());

        } catch (Exception e) {

            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void fragQuitCallback() {

    }

    /**
     * {@link android.view.View.OnClickListener} button click event callback method.
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_my_profile:

                btnMyProfileClicked(view);
                break;
            case R.id.btn_my_orders:

                btnMyOrdersClicked(view);
                break;
            case R.id.btn_address_change_request:

                btnAddressChangeRequest(view);
                break;
            case R.id.btn_preference:

                btnMyPreferencesClicked(view);
                break;
            case R.id.btn_shortlisted_restaurants:

                btnShortlistRestaurantsClicked(view);
                break;
            case R.id.btn_change_password:

                btnChangePasswordClicked(view);
                break;
            case R.id.btn_about_us:

                btnAboutUsClicked(view);
                break;
            case R.id.btn_logout:

                btnLogoutClicked(view);
                break;
        }
    }

    /**
     * Button(s) onClick Logic method(s).
     *
     * @param view reference
     */
    private void btnMyProfileClicked(View view) {

        // refresh the customer profile first and then proceed for profile update.
        CustomerProfileHandler customerProfileHandler = new CustomerProfileHandler(getActivity());
        customerProfileHandler.refresh(getDashboardActivity(), customer -> startActivity(new Intent(getActivity(), UpdateProfileActivity.class)));

    }

    private void btnMyOrdersClicked(View view) {

        startActivity(new Intent(getActivity(), MyOrdersActivity.class));
        //getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.MY_ORDERS);
    }

    private void btnAddressChangeRequest(View view) {

        startActivity(new Intent(getActivity(), AddressChangeRequestActivity.class));
    }

    private void btnMyPreferencesClicked(View view) {

        startActivity(new Intent(getActivity(), WeekPreferenceActivity.class));
    }

    private void btnShortlistRestaurantsClicked(View view) {

        startActivity(new Intent(getActivity(), ShortlistedRestaurantsActivity.class));
    }

    private void btnChangePasswordClicked(View view) {

        startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
        //getDashboardActivity().signalLoadFragment(DashboardInterruptListener.FRAGMENT_TYPE.CHANGE_PASSWORD);
    }

    private void btnAboutUsClicked(View view) {

        String url = "http://gofoodie.drushtiindia.com/#";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void btnLogoutClicked(View view) {

        // Also Check for social login and logout from that.
        Snackbar.make(view, "Are you sure", Snackbar.LENGTH_LONG).setAction("Logout ?", view1 -> {

            SessionUtils.getInstance().removeSession(ProfileFragment.this.getActivity());
            startActivity(new Intent(ProfileFragment.this.getActivity(), SplashActivity.class));
        }).show();
    }


}
