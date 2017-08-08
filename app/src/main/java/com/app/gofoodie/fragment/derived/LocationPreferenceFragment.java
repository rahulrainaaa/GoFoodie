package com.app.gofoodie.fragment.derived;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.gofoodie.R;
import com.app.gofoodie.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * @class LocationPreferenceFragment
 * @desc {@link BaseFragment} Fragment class to handle Location preference UI screen.
 */
public class LocationPreferenceFragment extends BaseFragment {


    private Spinner spCountry, spCity, spArea;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("list item: " + i);
        }


        View view = inflater.inflate(R.layout.frag_location_preferences, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, list);

        spCountry = (Spinner) view.findViewById(R.id.sp_locpref_country);
        spCity = (Spinner) view.findViewById(R.id.sp_locpref_city);
        spArea = (Spinner) view.findViewById(R.id.sp_locpref_area);

        spCountry.setAdapter(adapter);
        spCity.setAdapter(adapter);
        spArea.setAdapter(adapter);

        return view;
    }
}
