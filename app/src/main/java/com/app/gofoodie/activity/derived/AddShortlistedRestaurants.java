package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.listviewadapter.RestaurantListViewAdapter;
import com.app.gofoodie.model.shortlisted.Shortlisted;

import java.util.ArrayList;

/**
 * @class AddShortlistedRestaurants
 * @desc Activity class to show near by restaurant branches and adding them as shortlisted restaurants.
 */
public class AddShortlistedRestaurants extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static final String TAG = "AddShortlistedRestaurants";

    /**
     * Class private data members.
     */
    private ListView mListView = null;
    private RestaurantListViewAdapter mAdapter = null;
    private ArrayList<Shortlisted> mList = null;

    /**
     * {@link AddShortlistedRestaurants} activity callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shortlisted_restaurants);

        mList = new ArrayList<Shortlisted>();
        for (int i = 0; i < 50; i++) {

            new Shortlisted();
        }

        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new RestaurantListViewAdapter(this, R.layout.item_shortlist_restaurant, mList, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    /**
     * {@link android.widget.AdapterView.OnItemClickListener} list view item click listener callback method(s).
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Shortlisted item = mList.get(position);

        Toast.makeText(this, position + " item selected", Toast.LENGTH_SHORT).show();
    }

    /**
     * {@link android.view.View.OnClickListener} View click listener event callback method(s).
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ibtn_remove:

                addToShortlist(view);
                break;

            case R.id.ibtn_view:

                showProfile(view);
                break;
        }
    }

    /**
     * @param view {@link android.widget.ImageButton} clicked reference.
     * @method showProfile
     * @desc Method to show the complete profile description.
     */
    private void showProfile(View view) {

        Shortlisted shortlisted = (Shortlisted) view.getTag();
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        intent.putExtra("data", shortlisted);
    }

    /**
     * @param view {@link android.widget.ImageButton} clicked reference.
     * @method addToShortlist
     * @desc Method to remove this particular restaurant
     */
    private void addToShortlist(View view) {

        Shortlisted shortlisted = (Shortlisted) view.getTag();
        Toast.makeText(this, "add to shortlisted restaurant--- under progress.", Toast.LENGTH_SHORT).show();
    }
}
