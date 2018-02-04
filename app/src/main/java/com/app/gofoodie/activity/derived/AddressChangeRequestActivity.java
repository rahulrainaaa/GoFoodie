package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.spinnerAdapter.LocationSpinnerAdapter;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.location.LocationResponse;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Activity class for handling the address change request.
 */
public class AddressChangeRequestActivity extends BaseAppCompatActivity implements View.OnClickListener, NetworkCallbackListener {

    public static final String TAG = "AddressChangeRequestActivity";

    /**
     * Class private data member(s).
     */
    private Spinner mSpLocations = null;
    private EditText mEtAddress = null;
    private Button mBtnRequest = null;

    private ArrayList<String> mAreaList = null;
    private LocationSpinnerAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_address_change_request);

        mSpLocations = (Spinner) findViewById(R.id.sp_areas);
        mEtAddress = (EditText) findViewById(R.id.et_new_address);
        mBtnRequest = (Button) findViewById(R.id.btn_change);
        mBtnRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_change) {

            fetchLocations(view);
        }
    }

    /**
     * Method fetch areas from web api.
     *
     * @param view
     */
    public void fetchLocations(View view) {

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), "", NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
    }

    /**
     * Method to publish fetched Location(s) in spinner from json response.
     *
     * @param json
     */
    private void publishLocations(JSONObject json) {

        ModelParser parser = new ModelParser();
        LocationResponse response = (LocationResponse) parser.getModel(json.toString(), LocationResponse.class, null);

        if (response.getStatusCode() == 200) {


        } else {

            // alert no location fetched.
        }

    }

    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        switch (requestCode) {

            case 1:

                publishLocations(rawObject);
                break;
            case 2:

                break;
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        // internet fail.
    }
}
