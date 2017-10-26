package com.app.gofoodie.customview;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.app.gofoodie.R;

import org.json.JSONArray;

/**
 * @class WeekSelectDialog
 * @desc Class to handle the dialog box to select the week day(s) preference(s).
 */
public class WeekSelectDialog implements View.OnClickListener {

    public static final String TAG = "WeekSelectDialog";

    /**
     * Class private data members.
     */
    private Activity mActivity = null;
    private WeekSelectDialogInterface mDialogListener = null;
    private AlertDialog mAlertDialog = null;

    private boolean sun = true;
    private boolean mon = true;
    private boolean tue = true;
    private boolean wed = true;
    private boolean thu = true;
    private boolean fri = true;
    private boolean sat = true;

    private ToggleButton tglBtnSun = null;
    private ToggleButton tglBtnMon = null;
    private ToggleButton tglBtnTue = null;
    private ToggleButton tglBtnWed = null;
    private ToggleButton tglBtnThu = null;
    private ToggleButton tglBtnFri = null;
    private ToggleButton tglBtnSat = null;

    /**
     * @param activity
     * @constructor WeekSelectDialog
     */
    public WeekSelectDialog(Activity activity, WeekSelectDialogInterface listener) {

        this.mActivity = activity;
        this.mDialogListener = listener;
    }

    /**
     * @method show
     * @desc Method to show Dialog and show UI for selecting week days.
     */
    public void show() {

        View view = mActivity.getLayoutInflater().inflate(R.layout.dialog_week, null);

        tglBtnSun = (ToggleButton) view.findViewById(R.id.tglBtn_sunday);
        tglBtnMon = (ToggleButton) view.findViewById(R.id.tglBtn_monday);
        tglBtnTue = (ToggleButton) view.findViewById(R.id.tglBtn_tuesday);
        tglBtnWed = (ToggleButton) view.findViewById(R.id.tglBtn_wednesday);
        tglBtnThu = (ToggleButton) view.findViewById(R.id.tglBtn_thursday);
        tglBtnFri = (ToggleButton) view.findViewById(R.id.tglBtn_friday);
        tglBtnSat = (ToggleButton) view.findViewById(R.id.tglBtn_saturday);

        tglBtnSun.setChecked(sun);
        tglBtnMon.setChecked(mon);
        tglBtnTue.setChecked(tue);
        tglBtnWed.setChecked(wed);
        tglBtnThu.setChecked(thu);
        tglBtnFri.setChecked(fri);
        tglBtnSat.setChecked(sat);

        checkChanged(tglBtnSun, tglBtnSun.isChecked(), 1);
        checkChanged(tglBtnMon, tglBtnMon.isChecked(), 2);
        checkChanged(tglBtnTue, tglBtnTue.isChecked(), 3);
        checkChanged(tglBtnWed, tglBtnWed.isChecked(), 4);
        checkChanged(tglBtnThu, tglBtnThu.isChecked(), 5);
        checkChanged(tglBtnFri, tglBtnFri.isChecked(), 6);
        checkChanged(tglBtnSat, tglBtnSat.isChecked(), 7);

        mAlertDialog = new AlertDialog.Builder(mActivity).create();
        mAlertDialog.setView(view);
        mAlertDialog.setCancelable(false);
        mAlertDialog.setTitle("Week Schedule");

        mAlertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (mDialogListener != null) {

                    mDialogListener.weekDialogOkClicked(WeekSelectDialog.this);
                }
                dismiss();

            }
        });

        mAlertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dismiss();
            }
        });
        mAlertDialog.show();

        view.findViewById(R.id.tglBtn_sunday).setOnClickListener(this);
        view.findViewById(R.id.tglBtn_monday).setOnClickListener(this);
        view.findViewById(R.id.tglBtn_tuesday).setOnClickListener(this);
        view.findViewById(R.id.tglBtn_wednesday).setOnClickListener(this);
        view.findViewById(R.id.tglBtn_thursday).setOnClickListener(this);
        view.findViewById(R.id.tglBtn_friday).setOnClickListener(this);
        view.findViewById(R.id.tglBtn_saturday).setOnClickListener(this);
    }

    /**
     * @method dismiss
     * @desc Method to show dismiss and clear the dialog alert from memory.
     */
    public void dismiss() {

        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
    }

    /**
     * {@link android.view.View.OnClickListener} {@link ToggleButton} event listener callback method.
     */
    @Override
    public void onClick(View view) {
        ToggleButton tglBtn = (ToggleButton) view;
        switch (view.getId()) {
            case R.id.tglBtn_sunday:

                checkChanged(tglBtn, tglBtn.isChecked(), 1);
                sun = tglBtn.isChecked();
                break;
            case R.id.tglBtn_monday:

                checkChanged(tglBtn, tglBtn.isChecked(), 2);
                mon = tglBtn.isChecked();
                break;
            case R.id.tglBtn_tuesday:

                checkChanged(tglBtn, tglBtn.isChecked(), 3);
                tue = tglBtn.isChecked();
                break;
            case R.id.tglBtn_wednesday:

                checkChanged(tglBtn, tglBtn.isChecked(), 4);
                wed = tglBtn.isChecked();
                break;
            case R.id.tglBtn_thursday:

                checkChanged(tglBtn, tglBtn.isChecked(), 5);
                thu = tglBtn.isChecked();
                break;
            case R.id.tglBtn_friday:

                checkChanged(tglBtn, tglBtn.isChecked(), 6);
                fri = tglBtn.isChecked();
                break;
            case R.id.tglBtn_saturday:

                checkChanged(tglBtn, tglBtn.isChecked(), 7);
                sat = tglBtn.isChecked();
                break;
        }

    }

    /**
     * @param btn     {@link ToggleButton}
     * @param checked true = working day, false = off day.
     * @param day     Day of the week.
     * @method checkChanged
     * @desc Method to update day status according to UI input using toggleButton.
     */
    private void checkChanged(ToggleButton btn, boolean checked, int day) {

        if (checked) {

            btn.setTextColor(Color.rgb(89, 153, 60));
        } else {

            btn.setTextColor(Color.RED);
        }
    }

    /**
     * @return JSONArray
     * @method getWorkingDays
     * @desc Method to get JSONArray of working days.
     */
    public JSONArray getWorkingDays() {

        JSONArray arr = new JSONArray();
        try {

            if (sun) {

                arr.put("Sun");
            }
            if (mon) {

                arr.put("Mon");
            }
            if (tue) {

                arr.put("Tue");
            }
            if (wed) {

                arr.put("Wed");
            }
            if (thu) {

                arr.put("Thu");
            }
            if (fri) {

                arr.put("Fri");
            }
            if (sat) {

                arr.put("Sat");
            }
        } catch (Exception exc) {

            exc.printStackTrace();
            Toast.makeText(mActivity, "JSONException: " + exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return arr;
    }

    /**
     * @return JSONArray
     * @method getNonWorkingDays
     * @desc Method to get JSONArray of non working days in a week (week days off).
     */
    public JSONArray getNonWorkingDays() {

        JSONArray arr = new JSONArray();
        try {

            if (!sun) {

                arr.put("Sun");
            }
            if (!mon) {

                arr.put("Mon");
            }
            if (!tue) {

                arr.put("Tue");
            }
            if (!wed) {

                arr.put("Wed");
            }
            if (!thu) {

                arr.put("Thu");
            }
            if (!fri) {

                arr.put("Fri");
            }
            if (!sat) {

                arr.put("Sat");
            }
        } catch (Exception exc) {

            exc.printStackTrace();
            Toast.makeText(mActivity, "JSONException: " + exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return arr;
    }

    /**
     * @return is Sunday working.
     */
    public boolean isSunday() {

        return sun;
    }

    /**
     * @return is Monday working.
     */
    public boolean isMonday() {

        return mon;
    }

    /**
     * @return is Tuesday working.
     */
    public boolean isTuesday() {

        return tue;
    }

    /**
     * @return is Wednesday working.
     */
    public boolean isWednesday() {

        return wed;
    }

    /**
     * @return is Thursday working.
     */
    public boolean isThursday() {

        return thu;
    }

    /**
     * @return is Friday working.
     */
    public boolean isFriday() {

        return fri;
    }

    /**
     * @return is Saturday Working.
     */
    public boolean isSaturday() {

        return sat;
    }

    /**
     * @return boolean true = successful parsed, false = unable to parse.
     * @method parseWeekPreference
     * @desc Method to parse week days separated by commas [,].
     */
    public boolean parseWeekPreference(String str) {

        sun = false;
        mon = false;
        tue = false;
        wed = false;
        thu = false;
        fri = false;
        sat = false;

        String[] strArrWeekDays = str.split(",");

        for (int i = 0; i < strArrWeekDays.length; i++) {

            if (strArrWeekDays[i].trim().equals("Sun")) {

                sun = true;
                continue;
            }

            if (strArrWeekDays[i].trim().equals("Mon")) {

                mon = true;
                continue;
            }

            if (strArrWeekDays[i].trim().equals("Tue")) {

                tue = true;
                continue;
            }

            if (strArrWeekDays[i].trim().equals("Wed")) {

                wed = true;
                continue;
            }

            if (strArrWeekDays[i].trim().equals("Thu")) {

                thu = true;
                continue;
            }

            if (strArrWeekDays[i].trim().equals("Fri")) {

                fri = true;
                continue;
            }

            if (strArrWeekDays[i].trim().equals("Sat")) {

                sat = true;
                continue;
            }
        }
        return true;
    }

}
