package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.SubscriptionActivity;
import com.app.gofoodie.adapter.listviewadapter.TransactionListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * @class WalletFragment
 * @desc {@link BaseFragment} Fragment class to handle Wallet UI screen.
 */
public class WalletFragment extends BaseFragment implements View.OnClickListener {

    private ListView mListView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_wallet, container, false);

        ImageButton imgAlert = (ImageButton) view.findViewById(R.id.image_alert);
        ImageButton imgBtnSubscribe = (ImageButton) view.findViewById(R.id.img_btn_subscibe);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_blink);

        imgBtnSubscribe.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_btn_subscibe:

                getActivity().startActivity(new Intent(getActivity(), SubscriptionActivity.class));
                break;
        }
    }
}
