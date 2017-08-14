package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;

import java.util.ArrayList;

/**
 * @class LocationActivity
 * @desc {@link BaseAppCompatActivity} class for handling Location preferences.
 */
public class LocationActivity extends BaseAppCompatActivity {

    /**
     * Class private data members.
     */
    private Spinner spCountry, spCity, spArea;

    /**
     * {@link BaseAppCompatActivity} override methods.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("list item: " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);

        spCountry = (Spinner) findViewById(R.id.sp_locpref_country);
        spCity = (Spinner) findViewById(R.id.sp_locpref_city);
        spArea = (Spinner) findViewById(R.id.sp_locpref_area);

        spCountry.setAdapter(adapter);
        spCity.setAdapter(adapter);
        spArea.setAdapter(adapter);


    }
}
