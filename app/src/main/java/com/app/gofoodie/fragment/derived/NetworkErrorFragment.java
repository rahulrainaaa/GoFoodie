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
 * @class NetworkErrorFragment
 * @desc {@link BaseFragment} Fragment class to handle Network-Error UI screen.
 */
public class NetworkErrorFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_network_failure, container, false);
        Toast.makeText(getActivity(), "Network Error Fragment", Toast.LENGTH_SHORT).show();
        return view;
    }
}
