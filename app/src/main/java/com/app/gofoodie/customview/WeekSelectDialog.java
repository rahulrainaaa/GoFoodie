package com.app.gofoodie.customview;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ToggleButton;

import com.app.gofoodie.R;

public class WeekSelectDialog implements View.OnClickListener {

    /**
     * Class private data members.
     */
    private Activity mActivity = null;
    private AlertDialog mAlertDialog = null;

    public boolean sun = true;
    public boolean mon = true;
    public boolean tue = true;
    public boolean wed = true;
    public boolean thu = true;
    public boolean fri = false;
    public boolean sat = false;

    /**
     * @param activity
     * @constructor WeekSelectDialog
     */
    public WeekSelectDialog(Activity activity) {

        this.mActivity = activity;
    }

    /**
     * @method show
     * @desc Method to show Dialog and show UI for selecting week days.
     */
    public void show() {

        View view = mActivity.getLayoutInflater().inflate(R.layout.dialog_week, null);

        mAlertDialog = new AlertDialog.Builder(mActivity).create();
        mAlertDialog.setView(view);
        mAlertDialog.setCancelable(false);
        mAlertDialog.setTitle("Week Schedule");

        mAlertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

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
}
