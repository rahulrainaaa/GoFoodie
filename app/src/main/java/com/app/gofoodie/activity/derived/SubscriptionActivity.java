package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.widget.ListView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.RechargePlanListViewAdapter;
import com.app.gofoodie.customview.WeekSelectDialog;

import java.util.ArrayList;

public class SubscriptionActivity extends BaseAppCompatActivity {

    private ListView mListView = null;
    private RechargePlanListViewAdapter mAdapter = null;
    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        mListView = (ListView) findViewById(R.id.list_view);

        mList.add("Regular");
        mList.add("Premium");
        mList.add("Executive");
        mList.add("Recharge Plan 1");
        mList.add("Recharge Plan 2");
        mList.add("Recharge Plan 3");
        mList.add("Recharge Plan 4");
        mList.add("Recharge Plan 5");

        mAdapter = new RechargePlanListViewAdapter(this, R.layout.item_list_recharge_plan, mList);
        mListView.setAdapter(mAdapter);

        WeekSelectDialog weekSelectDialog = new WeekSelectDialog(this);
        weekSelectDialog.show();

    }

}
