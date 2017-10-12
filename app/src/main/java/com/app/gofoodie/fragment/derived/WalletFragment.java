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
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.SubscriptionActivity;
import com.app.gofoodie.adapter.listviewadapter.PaymentTransactionListViewAdapter;
import com.app.gofoodie.adapter.listviewadapter.WalletTransactionListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.transaction.PaymentTransaction;
import com.app.gofoodie.model.transaction.Transaction;
import com.app.gofoodie.model.transaction.WalletTransaction;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class WalletFragment
 * @desc {@link BaseFragment} Fragment class to handle Wallet UI screen.
 */
public class WalletFragment extends BaseFragment implements View.OnClickListener, TabLayout.OnTabSelectedListener, NetworkCallbackListener {

    /**
     * Class private data members.
     */
    private ListView mListView = null;
    private TabLayout mTabLayout = null;
    private Transaction mTransaction = null;

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


        String url = Network.URL_GET_TRANSACTION + "?customerLoginId=" + getSession().getData().getCustomerId();
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, getDashboardActivity(), this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();

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

        WalletTransactionListViewAdapter adapter = new WalletTransactionListViewAdapter(getActivity(), R.layout.item_listview_transactions, (ArrayList<WalletTransaction>) mTransaction.walletTransactions);
        mListView.setAdapter(adapter);

    }

    /**
     * @method getBankPaymentTransactions
     * @desc Method to show all the Bank Payment related Transaction for the login user.
     */
    private void showBankPaymentTransactions() {

        PaymentTransactionListViewAdapter adapter = new PaymentTransactionListViewAdapter(getActivity(), R.layout.item_listview_transactions, (ArrayList<PaymentTransaction>) mTransaction.paymentTransactions);
        mListView.setAdapter(adapter);
    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        Toast.makeText(getActivity(), "Http Success: " + rawObject.toString(), Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {

            parseModel(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(getActivity(), "Http fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param json
     * @method parseModel
     * @desc Method to parse the responsse into model class.
     */
    private void parseModel(JSONObject json) {

        ModelParser parser = new ModelParser();
        Transaction transaction = (Transaction) parser.getModel(json.toString(), Transaction.class, null);
        mTransaction = transaction;
        showWalletTransactions();
    }

}
