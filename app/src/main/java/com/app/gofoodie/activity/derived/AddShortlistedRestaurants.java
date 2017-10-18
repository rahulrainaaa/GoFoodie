package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.app.gofoodie.R;

/**
 * @class AddShortlistedRestaurants
 * @desc Activity class to show near by restaurant branches and adding them as shortlisted restaurants.
 */
public class AddShortlistedRestaurants extends AppCompatActivity {

    public static final String TAG = "AddShortlistedRestaurants";

    /**
     * Class private data members.
     */
    private ListView mListView = null;

    /**
     * {@link AddShortlistedRestaurants} activity callback method(s).
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shortlisted_restaurants);
    }
}
