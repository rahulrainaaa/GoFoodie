package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.gofoodie.R;

import java.util.ArrayList;

public class SubscriptionActivity extends AppCompatActivity {

    private Spinner mSpSubscriptionPlan = null;
    private TextView mTextDescription = null;
    private ArrayAdapter<String> mAdapter = null;
    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        mSpSubscriptionPlan = (Spinner) findViewById(R.id.sp_subscription_plan);
        mTextDescription = (TextView) findViewById(R.id.text_view_description);

        mList.add("Select Subscription");
        mList.add("Regular");
        mList.add("Premium");
        mList.add("Executive");

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mList);
        mSpSubscriptionPlan.setAdapter(mAdapter);
    }
}
