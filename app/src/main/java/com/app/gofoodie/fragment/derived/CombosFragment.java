package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.gridViewAdapter.ComboPlanGridAdapter;
import com.app.gofoodie.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * @class CombosFragment
 * @desc {@link BaseFragment} Fragment class to handle Combos Plan UI screen.
 */
public class CombosFragment extends BaseFragment {

    /**
     * Class private data members.
     */
    private GridView mComboGridView = null;
    private ComboPlanGridAdapter mAdapter = null;
    private ArrayList<String> mComboPlanList = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_combos, container, false);
        mComboGridView = (GridView) view.findViewById(R.id.combo_plan_grid_layout);

        mComboPlanList = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            mComboPlanList.add("Item: " + i);
        }
        mAdapter = new ComboPlanGridAdapter(getActivity(), R.layout.item_gridview_combo_plan, mComboPlanList);
        mComboGridView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void fragQuitCallback() {

    }


}
