package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.customview.WeekSelectDialog;

import java.util.ArrayList;

public class SubscriptionActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Spinner mSpSubscriptionPlan = null;
    private Button mBtnSubscription = null;
    private TextView mTextDescription = null;
    private ArrayAdapter<String> mAdapter = null;
    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        mBtnSubscription = (Button) findViewById(R.id.btn_subscribe);
        mSpSubscriptionPlan = (Spinner) findViewById(R.id.sp_subscription_plan);
        mTextDescription = (TextView) findViewById(R.id.text_view_description);

        mList.add("-Select Subscription-");
        mList.add("Regular");
        mList.add("Premium");
        mList.add("Executive");

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mList);
        mSpSubscriptionPlan.setAdapter(mAdapter);


        mBtnSubscription.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        WeekSelectDialog weekSelectDialog = new WeekSelectDialog(this);
        weekSelectDialog.show();
    }
}
