package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.listviewadapter.CartListViewAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * @class CartFragment
 * @desc {@link BaseFragment} Fragment class to handle Cart UI screen.
 */
public class CartFragment extends BaseFragment implements View.OnClickListener {

    public final String TAG = "CartFragment";

    /**
     * Class private data member(s).
     */
    private ListView mListView = null;
    private CartListViewAdapter mAdapter = null;
    private ArrayList<String> mCartList = new ArrayList<String>();

    /**
     * {@link BaseFragment} Fragment callback method(s).
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_cart, container, false);
        Toast.makeText(getActivity(), "cart fragment", Toast.LENGTH_SHORT).show();


        mCartList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mCartList.add("List-" + i);
        }

        mListView = (ListView) view.findViewById(R.id.list_view_cart_items);
        mAdapter = new CartListViewAdapter(getActivity(), R.layout.item_listview_cart, mCartList);
        mListView.setAdapter(mAdapter);

        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void fragQuitCallback() {

    }

    /**
     * {@link android.view.View.OnClickListener} click event listener callback method(s).
     */
    @Override
    public void onClick(View view) {


    }
}
