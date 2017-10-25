package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.customview.WeekSelectDialog;
import com.app.gofoodie.customview.WeekSelectDialogInterface;
import com.app.gofoodie.network.callback.NetworkCallbackListener;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @class WeekPreferenceActivity
 * @desc Activity class to handle the week day preference (Set and Get).
 */
public class WeekPreferenceActivity extends BaseAppCompatActivity implements NetworkCallbackListener, WeekSelectDialogInterface {

    public static final String TAG = "WeekPreferenceActivity";

    /**
     * Class private data member(s).
     */
    private WeekSelectDialog mWeekDialog = null;

    /**
     * {@link BaseAppCompatActivity} activity callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_preference);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mWeekDialog = new WeekSelectDialog(this, this);
        mWeekDialog.show();

    }

    /**
     * {@link NetworkCallbackListener} http response callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        Toast.makeText(this, "http success: " + rawObject.toString(), Toast.LENGTH_SHORT).show();

        switch (requestCode) {

            case 1:                 // Get week preference.

                break;

            case 2:                 // Set week preference.

                break;
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "http fail: " + message.trim(), Toast.LENGTH_SHORT).show();
    }


    /**
     * {@link WeekSelectDialogInterface} week dialog listener callback method(s).
     */
    @Override
    public void weekDialogOkClicked(WeekSelectDialog dialog) {

        Toast.makeText(this, "week dialog ok clicked.", Toast.LENGTH_SHORT).show();
    }
}
