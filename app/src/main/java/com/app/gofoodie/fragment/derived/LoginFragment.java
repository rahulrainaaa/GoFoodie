package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * @class LoginFragment
 * @desc {@link BaseFragment} Fragment class to handle Login UI screen.
 */
public class LoginFragment extends BaseFragment {

    public final String TAG = "LoginFragment";

    /**
     * Class private data members.
     */
    private MaterialEditText mEtMobileEmail = null;
    private MaterialEditText mEtPassword = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login, container, false);
        doViewMapping(view);
        Toast.makeText(getActivity(), "Login Fragment", Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void fragQuitCallback() {

    }

    /**
     * @method doViewMapping
     * @desc Method to do mapping of all xml view to class objects.
     */
    private void doViewMapping(View view) {

        mEtMobileEmail = (MaterialEditText) view.findViewById(R.id.et_mobile_email);
        mEtPassword = (MaterialEditText) view.findViewById(R.id.et_password);

    }

}
