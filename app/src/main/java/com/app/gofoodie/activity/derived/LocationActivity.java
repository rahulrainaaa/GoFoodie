package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.areaResponse.AreaResponse;
import com.app.gofoodie.model.cityResponse.CityResponse;
import com.app.gofoodie.model.countryResponse.CountryResponse;
import com.app.gofoodie.model.countryResponse.Datum;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.LocationUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @class LocationActivity
 * @desc {@link BaseAppCompatActivity} class for handling Location preferences.
 */
@SuppressWarnings("unused")
public class LocationActivity extends BaseAppCompatActivity implements AdapterView.OnItemSelectedListener, NetworkCallbackListener {

    public final String TAG = "LocationActivity";

    /**
     * Class public data member(s).
     */
    private CountryResponse mCountryListResponse = null;
    private CityResponse mCityResponse = null;
    private AreaResponse mAreaResponse = null;
    private ArrayAdapter<String> mCountryAdapter, mCityAdapter, mAreaAdapter;
    private final ArrayList<String> mCountryList = new ArrayList<>();
    private final ArrayList<String> mCityList = new ArrayList<>();
    private final ArrayList<String> mAreaList = new ArrayList<>();
    private com.app.gofoodie.model.areaResponse.Datum mAreaLocationDatum = null;

    /**
     * {@link BaseAppCompatActivity} override methods.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mCountryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mCountryList);
        mCityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mCityList);
        mAreaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mAreaList);

        /*
      Class private data member(s).
     */
        Spinner mSpCountry = findViewById(R.id.sp_locpref_country);
        Spinner mSpCity = findViewById(R.id.sp_locpref_city);
        Spinner mSpArea = findViewById(R.id.sp_locpref_area);

        mSpCountry.setAdapter(mCountryAdapter);
        mSpCity.setAdapter(mCityAdapter);
        mSpArea.setAdapter(mAreaAdapter);

        mSpCountry.setOnItemSelectedListener(this);
        mSpCity.setOnItemSelectedListener(this);
        mSpArea.setOnItemSelectedListener(this);

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), Network.URL_GET_COUNTRIES, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();

        findViewById(R.id.save_btn).setOnClickListener(view -> {

            if (LocationActivity.this.mAreaLocationDatum == null) {

                Toast.makeText(LocationActivity.this, "Please select an Area", Toast.LENGTH_SHORT).show();
                return;
            }

            LocationUtils.getInstance().saveLocation(LocationActivity.this, mAreaLocationDatum.getAreaId(), mAreaLocationDatum.getAreaName());
            Toast.makeText(LocationActivity.this, "Location Saved", Toast.LENGTH_SHORT).show();
            GlobalData.applyLocationPref = true;
            finish();
        });
    }

    /**
     * {@link android.widget.AdapterView.OnItemSelectedListener} combo item selected listener callback method(s).
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        switch (adapterView.getId()) {

            case R.id.sp_locpref_country:

                mCityList.clear();
                mCityAdapter.notifyDataSetChanged();
                mAreaList.clear();
                mAreaAdapter.notifyDataSetChanged();
                countrySelected(position - 1);
                break;

            case R.id.sp_locpref_city:

                mAreaList.clear();
                mAreaAdapter.notifyDataSetChanged();
                citySelected(position - 1);
                break;

            case R.id.sp_locpref_area:

                areaSelected(position - 1);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * Method to be called on country select in spinner.
     *
     * @param position int
     */
    private void countrySelected(int position) {


        if (position == -1) {
            return;
        }

        mAreaLocationDatum = null;
        Datum datum = mCountryListResponse.getData().get(position);
        String url = Network.URL_GET_CITY + "" + datum.getCountryId().trim();
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(2, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    /**
     * Method to be called on city selected.
     *
     * @param position int
     */
    private void citySelected(int position) {


        if (position == -1) {
            return;
        }

        mAreaLocationDatum = null;
        com.app.gofoodie.model.cityResponse.Datum datum = mCityResponse.getData().get(position);
        String url = Network.URL_GET_AREA + "" + datum.getCityId().trim();
        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(3, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    /**
     * Method to be called in case of area selected.
     *
     * @param position int
     */
    private void areaSelected(int position) {

        if (position == -1) {
            return;
        }

        mAreaLocationDatum = mAreaResponse.getData().get(position);
    }

    /**
     * {@link NetworkHandler} Network response callback methods.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        ModelParser modelParser = new ModelParser();
        if (requestCode == 1) {

            // List of country.
            mCountryListResponse = (CountryResponse) modelParser.getModel(rawObject.toString(), CountryResponse.class, null);
            showCountry(mCountryListResponse);

        } else if (requestCode == 2) {

            mCityResponse = (CityResponse) modelParser.getModel(rawObject.toString(), CityResponse.class, null);
            showCity(mCityResponse);
            // List of city.

        } else if (requestCode == 3) {

            mAreaResponse = (AreaResponse) modelParser.getModel(rawObject.toString(), AreaResponse.class, null);
            showArea(mAreaResponse);
            // List of area.

        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        switch (requestCode) {
            case 1:

                Toast.makeText(this, "Unable to fetch country list.", Toast.LENGTH_SHORT).show();
                break;
            case 2:

                Toast.makeText(this, "Unable to fetch City list.", Toast.LENGTH_SHORT).show();
                break;
            case 3:

                Toast.makeText(this, "Unable to fetch Location Area list.", Toast.LENGTH_SHORT).show();
                break;
        }
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to show the list of countries.
     *
     * @param countryList reference
     */
    private void showCountry(CountryResponse countryList) {

        mCountryList.clear();

        if (countryList.getStatusCode() != 200) {
            Toast.makeText(this, countryList.getStatusMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        Iterator<Datum> listIterator = countryList.getData().iterator();

        mCountryList.add("Select Country");

        while (listIterator.hasNext()) {

            mCountryList.add("" + listIterator.next().getCountryName());
        }
        mCountryAdapter.notifyDataSetChanged();
    }

    /**
     * Method to show the list of Cities.
     *
     * @param cityResponse reference
     */
    private void showCity(CityResponse cityResponse) {

        mCityList.clear();

        if (cityResponse.getStatusCode() != 200) {
            Toast.makeText(this, cityResponse.getStatusMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        Iterator<com.app.gofoodie.model.cityResponse.Datum> listIterator = cityResponse.getData().iterator();

        mCityList.add("Select City");

        while (listIterator.hasNext()) {

            mCityList.add("" + listIterator.next().getCityName());

        }
        mCityAdapter.notifyDataSetChanged();
    }

    /**
     * Method to show the list of all the areas.
     *
     * @param areaResponse reference
     */
    private void showArea(AreaResponse areaResponse) {

        mAreaList.clear();

        if (areaResponse.getStatusCode() != 200) {
            Toast.makeText(this, areaResponse.getStatusMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        Iterator<com.app.gofoodie.model.areaResponse.Datum> listIterator = areaResponse.getData().iterator();

        mAreaList.add("Select Area");

        while (listIterator.hasNext()) {

            mAreaList.add("" + listIterator.next().getAreaName());
        }
        mAreaAdapter.notifyDataSetChanged();
    }
}
