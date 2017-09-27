package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class WalletFragment extends BaseFragment implements View.OnClickListener, TabLayout.OnTabSelectedListener {

    private ListView mListView = null;
    private TabLayout mTabLayout = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_wallet, container, false);

        ImageButton imgAlert = (ImageButton) view.findViewById(R.id.image_alert);
        ImageButton imgBtnSubscribe = (ImageButton) view.findViewById(R.id.img_btn_subscibe);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mListView = (ListView) view.findViewById(R.id.list_view);

        mTabLayout.setOnTabSelectedListener(this);
        imgBtnSubscribe.setOnClickListener(this);


        ArrayList<String> list = new ArrayList<>();

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


    /**
     * {@link android.support.design.widget.TabLayout.OnTabSelectedListener} event listener callback method.
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        int tag = (int) tab.getTag();
        switch (tag) {
            case 1:

                showWalletTransactions();
                break;
            case 2:

                showBankPaymentTransactions();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * @method showWalletTransactions
     * @desc Method to show all the wallet related transactions of the login user.
     */
    private void showWalletTransactions() {

    }

    /**
     * @method getBankPaymentTransactions
     * @desc Method to show all the Bank Payment related Transaction for the login user.
     */
    private void showBankPaymentTransactions() {

    }


}
