package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.fragment.base.BaseFragment;

/**
 * @class LoginFragment
 * @desc {@link BaseFragment} Fragment class to handle Login UI screen.
 */
public class LoginFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login_screen, container, false);
        Toast.makeText(getActivity(), "fragment-onCreateView()-method called.", Toast.LENGTH_SHORT).show();
        return view;
    }

}
