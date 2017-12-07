package com.app.gofoodie.fragment.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.derived.SubscriptionActivity;
import com.app.gofoodie.adapter.listviewadapter.PaymentTransactionListViewAdapter;
import com.app.gofoodie.adapter.listviewadapter.WalletTransactionListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.transaction.PaymentTransaction;
import com.app.gofoodie.model.transaction.Transaction;
import com.app.gofoodie.model.transaction.WalletTransaction;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @class WalletFragment
 * @desc {@link BaseFragment} Fragment class to handle Wallet UI screen.
 */
public class WalletFragment extends BaseFragment implements View.OnClickListener, TabLayout.OnTabSelectedListener, NetworkCallbackListener {

    /**
     * Class private data members.
     */
    private ListView mListView = null;
    private TextView mTxtWalletAmount, mTxtValidUpto;
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
        mTxtWalletAmount = (TextView) view.findViewById(R.id.wallet_amount);
        mTxtValidUpto = (TextView) view.findViewById(R.id.valid_upto);
        mTabLayout.setOnTabSelectedListener(this);
        imgBtnSubscribe.setOnClickListener(this);

        if (CustomerProfileHandler.profileExist) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date expDate = format.parse(CustomerProfileHandler.CUSTOMER.profile.validUpto);
                Date curDate = new Date();
                if (curDate.before(expDate)) {

                    imgAlert.setVisibility(View.GONE);
                } else {

                    imgAlert.setVisibility(View.VISIBLE);
                    imgAlert.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_blink));
                }
            } catch (ParseException e) {
                Toast.makeText(getActivity(), "Get Subscription plan to proceed.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            String url = Network.URL_GET_TRANSACTION + "?customerLoginId=" + getSession().getData().getLoginId();
            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, getDashboardActivity(), this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executeGet();

        } else {

            Toast.makeText(getActivity(), "Check internet connection", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mTxtWalletAmount.setText("AED " + CustomerProfileHandler.CUSTOMER.profile.amount);
        mTxtValidUpto.setText("Subscription till: " + CustomerProfileHandler.CUSTOMER.profile.validUpto);
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

        switch (tab.getPosition()) {
            case 0:

                showWalletTransactions();
                break;
            case 1:

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

        if (mTransaction.getWalletTransactions() == null) {

            Toast.makeText(getActivity(), "No Wallet Transactions", Toast.LENGTH_SHORT).show();
            return;
        } else if (mTransaction.getWalletTransactions().size() == 0) {

            Toast.makeText(getActivity(), "No Wallet Transactions", Toast.LENGTH_SHORT).show();
            return;
        }
        WalletTransactionListViewAdapter adapter = new WalletTransactionListViewAdapter(getActivity(), R.layout.item_listview_transactions, (ArrayList<WalletTransaction>) mTransaction.getWalletTransactions());
        mListView.setAdapter(adapter);

    }

    /**
     * @method getBankPaymentTransactions
     * @desc Method to show all the Bank Payment related Transaction for the login user.
     */
    private void showBankPaymentTransactions() {

        if (mTransaction.getPaymentTransactions() == null) {

            Toast.makeText(getActivity(), "No Bank Transaction", Toast.LENGTH_SHORT).show();
            return;
        } else if (mTransaction.getPaymentTransactions().size() == 0) {

            Toast.makeText(getActivity(), "No Bank Transaction", Toast.LENGTH_SHORT).show();
            return;
        }
        PaymentTransactionListViewAdapter adapter = new PaymentTransactionListViewAdapter(getActivity(), R.layout.item_listview_transactions, (ArrayList<PaymentTransaction>) mTransaction.getPaymentTransactions());
        mListView.setAdapter(adapter);
    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

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
     * @desc Method to parse the response into model class.
     */
    private void parseModel(JSONObject json) {

        ModelParser parser = new ModelParser();
        Transaction transaction = (Transaction) parser.getModel(json.toString(), Transaction.class, null);
        mTransaction = transaction;
        showWalletTransactions();
    }

}
