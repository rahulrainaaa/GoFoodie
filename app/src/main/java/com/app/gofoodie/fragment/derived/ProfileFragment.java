package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.SplashActivity;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.utility.SessionUtils;

/**
 * @class ProfileFragment
 * @desc {@link BaseFragment} Fragment class to handle Profile UI screen.
 */
public class ProfileFragment extends BaseFragment implements View.OnClickListener {

    /**
     * {@link BaseFragment} callback methods.
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_profile, container, false);

        view.findViewById(R.id.btn_my_profile).setOnClickListener(this);
        view.findViewById(R.id.btn_my_orders).setOnClickListener(this);
        view.findViewById(R.id.btn_preference).setOnClickListener(this);
        view.findViewById(R.id.btn_shortlisted_restaurants).setOnClickListener(this);
        view.findViewById(R.id.btn_change_password).setOnClickListener(this);
        view.findViewById(R.id.btn_about_us).setOnClickListener(this);
        view.findViewById(R.id.btn_logout).setOnClickListener(this);

        return view;
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
     */

    private void btnMyProfileClicked(View view) {

    }

    private void btnMyOrdersClicked(View view) {

    }

    private void btnMyPreferencesClicked(View view) {

    }

    private void btnShortlistRestaurantsClicked(View view) {

    }

    private void btnChangePasswordClicked(View view) {

    }

    private void btnAboutUsClicked(View view) {

    }

    private void btnLogoutClicked(View view) {

        // Also Check for social login and logout from that.
        Snackbar.make(view, "Are you sure", Snackbar.LENGTH_LONG).setAction("Logout ?", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SessionUtils.getInstance().removeSession(ProfileFragment.this.getActivity());
                startActivity(new Intent(ProfileFragment.this.getActivity(), SplashActivity.class));
            }
        }).show();
    }
}
