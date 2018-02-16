package com.app.gofoodie.customview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.app.gofoodie.R;

/**
 * @class GoFoodieProgressDialog
 * @desc ProgressBar dialog box to show the processing icon on foreground and freeze UI.
 */
public class GoFoodieProgressDialog {

    /**
     * Private class data members.
     */
    private Activity mActivity = null;
    private String mText = "";
    private AlertDialog mAlertDialog = null;
    private View mView = null;
    private TextView mTextView = null;
    private boolean isShowing = false;
    private boolean isCreated = false;

    /**
     * Initialize the data members.
     *
     * @param activity reference
     */
    public GoFoodieProgressDialog(Activity activity) {

        this.mActivity = activity;
    }

    /**
     * Method to create the ProgressDialog only first time (if called) in an Activity.
     */
    @SuppressWarnings("ConstantConditions")
    private void createFirstTime() {

        mView = mActivity.getLayoutInflater().inflate(R.layout.process_dialog, null);
        mTextView = mView.findViewById(R.id.process_text);
        mAlertDialog = new AlertDialog.Builder(mActivity).create();
        mAlertDialog.setView(mView);
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mAlertDialog.setCancelable(false);
        isCreated = true;
        mAlertDialog.show();
    }

    /**
     * Method to setText in progress dialog.
     *
     * @param text String object reference
     */
    public void setMessage(String text) {

        if (text == null) {
            this.mText = "";
        }
        this.mText = text.trim();
        mTextView.setText("" + mText.trim());
    }

    /**
     * Method to show the {@link GoFoodieProgressDialog} over activity.
     */
    public void show() {

        // Create it only first time and reuse it.
        if (!isCreated) {
            createFirstTime();
        }

        if (!isShowing) {
            mAlertDialog.show();
        }
        isShowing = true;
    }

    /**
     * Method to hide the progress dialog from the activity, but keep it for next use.
     */
    public void hide() {
        setMessage("");
        if (mAlertDialog != null) {
            mAlertDialog.hide();
        }
        isShowing = false;
    }

    /**
     *  Method to dismiss and release the memory.
     */
    public void dismiss() {

        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
        isCreated = false;
        isShowing = false;
    }

}
