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
 * @class NewRegisterFragment
 * @desc {@link BaseFragment} Fragment class to handle New Customer Registration UI screen.
 */
public class ForgotPasswordFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_forgot_password, container, false);
        Toast.makeText(getActivity(), "Request forgot password Fragment.", Toast.LENGTH_SHORT).show();
        return view;
    }
}
