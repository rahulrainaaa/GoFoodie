package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.CheckedListViewAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.category.Category;
import com.app.gofoodie.model.category.Datum;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.CacheUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MealPreferenceActivity extends BaseAppCompatActivity implements NetworkCallbackListener {

    private ListView mListView = null;
    private ArrayList<Datum> mList = new ArrayList<>();
    private CheckedListViewAdapter mAdapter = null;

    private RadioGroup mRgpMealType = null;
    private String mStrMealType = "both";
    private StringBuilder mStrCuzType = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_preference);

        mRgpMealType = (RadioGroup) findViewById(R.id.radio_group_meal_type);
        mListView = (ListView) findViewById(R.id.category_list_view);
        mAdapter = new CheckedListViewAdapter(this, mList);
        mListView.setAdapter(mAdapter);

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), Network.URL_GET_CUISINE, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
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

            case R.id.menu_item_save:

                savePreference();
                break;
        }
        return true;
    }

    /**
     * @method savePreference
     * @desc Method to save Meal preferences.
     */
    private void savePreference() {

        switch (mRgpMealType.getCheckedRadioButtonId()) {
            case R.id.radio_veg:

                mStrMealType = "veg";
                break;
            case R.id.radio_non_veg:

                mStrMealType = "nonveg";
                break;
            case R.id.radio_both:

                mStrMealType = "both";
                break;
        }

        Iterator<Datum> iterator = mList.iterator();
        while (iterator.hasNext()) {

            Datum datum = iterator.next();
            if (datum.checked) {

                mStrCuzType.append("" + datum.cateId + ",");
            }
        }

        // Save the preference into the cache (offline).
        CacheUtils.getInstance().getPref(this, CacheUtils.PREF_NAME.PREF_MEAL).edit().putString(CacheUtils.PREF_MEAL_CUZ, mStrCuzType.toString()).commit();
        CacheUtils.getInstance().getPref(this, CacheUtils.PREF_NAME.PREF_MEAL).edit().putString(CacheUtils.PREF_MEAL_TYPE, mStrMealType.toString()).commit();

        Toast.makeText(this, "Meal Preferences saved.", Toast.LENGTH_SHORT).show();
    }

    /**
     * {@link NetworkCallbackListener} http response callback methods.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        switch (requestCode) {

            case 1:

                listResponseHandler(rawObject);
                break;
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "HTTP_FAIL: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param json response.
     * @method listResponseHandler
     * @desc Method to update Category list as per the list got from API.
     */
    private void listResponseHandler(JSONObject json) {

        ModelParser modelParser = new ModelParser();
        Category cuisine = (Category) modelParser.getModel(json.toString(), Category.class, null);

        // Check if the http response is non-success (!=200).
        if (cuisine.statusCode != 200) {

            Toast.makeText(this, "Unable to fetch data.", Toast.LENGTH_SHORT).show();
            return;
        }

        mList = (ArrayList<Datum>) cuisine.data;
        mAdapter = new CheckedListViewAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        if (mList.size() == 0) {

            Toast.makeText(this, "Category list is empty.", Toast.LENGTH_SHORT).show();
        }
    }
}