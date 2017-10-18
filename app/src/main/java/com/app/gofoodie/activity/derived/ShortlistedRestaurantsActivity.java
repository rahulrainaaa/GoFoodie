package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.ShortlistedRestaurantListViewAdapter;

import java.util.ArrayList;

/**
 * @class ShortlistedRestaurantsActivity
 * @desc Activity class for showing the shortlisted restaurants (branch) for a customer profile.
 */
public class ShortlistedRestaurantsActivity extends BaseAppCompatActivity {

    public static final String TAG = "ShortlistedRestaurantsActivity";

    /**
     * Class private data member(s).
     */
    private ListView mListView = null;
    private ArrayList<String> mList = new ArrayList<>();
    private ShortlistedRestaurantListViewAdapter mAdapter = null;

    /**
     * {@link BaseAppCompatActivity} activity callback method(s).
     */
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_meal_pref, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new:

                startActivity(new Intent(this, AddShortlistedRestaurants.class));
                break;
        }
        return true;
    }

}
