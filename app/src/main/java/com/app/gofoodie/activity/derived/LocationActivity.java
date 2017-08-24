package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @class LocationActivity
 * @desc {@link BaseAppCompatActivity} class for handling Location preferences.
 */
public class LocationActivity extends BaseAppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * Class private data members.
     */
    private Spinner mSpCountry, mSpCity, mSpArea;
    private ArrayAdapter<String> mCountryAdapter, mCityAdapter, mAreaAdapter;
    private ArrayList<String> mCountryList = new ArrayList<>();
    private ArrayList<String> mCityList = new ArrayList<>();
    private ArrayList<String> mAreaList = new ArrayList<>();

    /**
     * {@link BaseAppCompatActivity} override methods.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        for (int i = 0; i < 20; i++) {
            mCountryAdapter.add("Country: " + i);
            mCityAdapter.add("City: " + i);
            mAreaAdapter.add("Area: " + i);
        }

        mCountryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mCountryList);
        mCityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mCityList);
        mAreaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mAreaList);

        mSpCountry = (Spinner) findViewById(R.id.sp_locpref_country);
        mSpCity = (Spinner) findViewById(R.id.sp_locpref_city);
        mSpArea = (Spinner) findViewById(R.id.sp_locpref_area);

        mSpCountry.setAdapter(mCountryAdapter);
        mSpCity.setAdapter(mCityAdapter);
        mSpArea.setAdapter(mAreaAdapter);

        mSpCountry.setOnItemClickListener(this);
        mSpCity.setOnItemClickListener(this);
        mSpArea.setOnItemClickListener(this);

        JSONObject jsonCountryRequest = new JSONObject();
        try {
            jsonCountryRequest.put("", "");
            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, this, null, jsonCountryRequest, "URL", NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();
        } catch (JSONException excJson) {
            excJson.printStackTrace();
        }

    }

    /**
     * {@link android.widget.AdapterView.OnItemClickListener} spinner item select callback method.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (view.getId()) {

            case R.id.sp_locpref_country:

                countrySelected(1);
                break;

            case R.id.sp_locpref_city:

                citySelected(1);
                break;

            case R.id.sp_locpref_area:

                areaSelected(1);
                break;
        }
    }

    /**
     * @param countryId - country primary key in cloud database.
     * @method countrySelected
     * @desc Method to be callec on country select in spinner.
     */
    private void countrySelected(int countryId) {

    }

    /**
     * @param cityId
     * @method citySelected
     * @desc Method to be called on city selected.
     */
    private void citySelected(int cityId) {

    }

    /**
     * @param areaId
     * @method areaSelected
     * @desc Method to be called in case of area selected.
     */
    private void areaSelected(int areaId) {

    }
}
