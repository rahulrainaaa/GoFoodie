package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;

public class RestaurantProfileActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
