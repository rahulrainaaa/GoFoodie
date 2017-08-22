package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.listviewadapter.TransactionListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * @class WalletFragment
 * @desc {@link BaseFragment} Fragment class to handle Wallet UI screen.
 */
public class WalletFragment extends BaseFragment {

    private ListView mListView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_wallet, container, false);
        Toast.makeText(getActivity(), "Wallet Fragment", Toast.LENGTH_SHORT).show();
        ImageButton imgAlert = (ImageButton) view.findViewById(R.id.image_alert);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_blink);
        imgAlert.setAnimation(animation);

        mListView = (ListView) view.findViewById(R.id.list_view);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item-" + i);
        }
        TransactionListViewAdapter adapter = new TransactionListViewAdapter(getActivity(), R.layout.item_listview_transactions, list);
        mListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void fragQuitCallback() {

    }
}
