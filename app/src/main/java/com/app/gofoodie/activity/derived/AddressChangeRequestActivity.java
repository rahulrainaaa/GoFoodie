package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.gofoodie.R;

import java.util.ArrayList;

/**
 * Activity class for handling the address change request.
 */
public class AddressChangeRequestActivity extends AppCompatActivity {

    public static final String TAG = "AddressChangeRequestActivity";

    /**
     * Class private data member(s).
     */
    private Spinner mSpLocations = null;
    private EditText mEtAddress = null;
    private Button mBtnRequest = null;

    private ArrayList<String> mAreaList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_address_change_request);

        // mSpLocations =

    }
}
