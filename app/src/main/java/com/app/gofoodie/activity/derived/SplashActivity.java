package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.handler.profileDataHandler.ProfileUpdateListener;
import com.app.gofoodie.model.customer.Customer;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.SessionUtils;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @class SplashActivity
 * @desc Activity Class to show Splash screen on the application start.
 */
public class SplashActivity extends BaseAppCompatActivity implements Runnable, ProfileUpdateListener {

    public static final String TAG = "SplashActivity";

    /**
     * Class private data objects.
     */
    private Handler mHandler = null;
    private ImageView mImgSplashLogo = null;

    /**
     * {@link BaseAppCompatActivity} callback method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Facebook SDK initialize.
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        // Init Network Handler for Application.
        NetworkHandler.initRequestQueue(getApplicationContext());
        SessionUtils.getInstance().loadSession(this);

        // Fetch profile if login session exists.
        if (SessionUtils.getInstance().isSessionExist()) {

            CustomerProfileHandler customerProfileHandler = new CustomerProfileHandler(this);
            customerProfileHandler.refresh(null, this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        showFullScreen();
        mImgSplashLogo = findViewById(R.id.img_splash_logo);
        mHandler = new Handler();
        if (!SessionUtils.getInstance().isSessionExist()) {
            mHandler.postDelayed(this, 3000);
        }
    }

    @Override
    protected void onPause() {
        mHandler.removeCallbacks(this);
        finish();
        super.onPause();
    }

    /**
     * {@link Runnable} interface callback method.
     */
    @Override
    public void run() {

        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    /**
     * {@link ProfileUpdateListener} profile update listener.
     */
    @Override
    public void profileUpdatedCallback(Customer customer) {

        if (customer.getStatusCode() == 404) {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Unable to fetch the profile.")
                    .setConfirmText("OK")
                    .setCancelClickListener(sDialog -> {

                        sDialog.cancel();
                        SplashActivity.this.finish();
                    })
                    .show();

        } else if (customer.getStatusCode() == 401 || customer.getStatusCode() == 403) {

            SessionUtils.getInstance().removeSession(this);
            Toast.makeText(this, "" + customer.getStatusMessage() + "\nPlease login again.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, DashboardActivity.class));
            finish();

        } else if (customer.getStatusCode() == 200 && customer.getProfile() != null) {

            startActivity(new Intent(this, DashboardActivity.class));
            finish();

        } else if (customer.getStatusCode() == 404) {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Unexpected server error.\nPlease try later.")
                    .setConfirmText("OK")
                    .setCancelClickListener(sDialog -> {

                        sDialog.cancel();
                        SplashActivity.this.finish();
                    })
                    .show();

        } else {

            Toast.makeText(this, "" + customer.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
