package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.listviewadapter.ShortlistedRestaurantListViewAdapter;

import java.util.ArrayList;

public class ShortlistedRestaurantsActivity extends AppCompatActivity {

    private ListView mListView = null;
    private ArrayList<String> mList = new ArrayList<>();
    private ShortlistedRestaurantListViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortlisted_restaurants);

        mListView = (ListView) findViewById(R.id.list_view);

        for (int i = 0; i < 20; i++) {
            mList.add("item-" + i);
        }

        mAdapter = new ShortlistedRestaurantListViewAdapter(this, R.layout.item_shortlisted_restaurants, mList);
        mListView.setAdapter(mAdapter);
    }

}
