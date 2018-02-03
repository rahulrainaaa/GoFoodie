package com.app.gofoodie.adapter.spinnerAdapter;


import android.app.Activity;
import android.location.Location;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class LocationSpinnerAdapter extends ArrayAdapter<Location> {

    public static final String TAG = "LocationSpinnerAdapter";

    /**
     * Class private data member(s).
     */
    private ArrayList<Location> mLocationList = null;
    private Activity mActivity = null;
    private int mResourceId = -1;

    public LocationSpinnerAdapter(@NonNull Activity activity, int resource, ArrayList<Location> arrayList) {
        super(activity, resource, arrayList);

        this.mActivity = activity;
        this.mResourceId = resource;
        this.mLocationList = arrayList;
    }


}
