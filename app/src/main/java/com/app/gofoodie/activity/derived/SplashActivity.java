package com.app.gofoodie.activity.derived;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.app.gofoodie.model.restaurantBranch.RestaurantBranch;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.SessionUtils;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

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
        mImgSplashLogo = (ImageView) findViewById(R.id.img_splash_logo);
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

        if (customer == null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setIcon(R.drawable.icon_error_alert);
            builder.setMessage("Failed to connect to server.\nPlease try later.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                    finish();
                }
            });
            builder.show();

        } else if (customer.statusCode == 401 || customer.statusCode == 403) {

            SessionUtils.getInstance().removeSession(this);
            Toast.makeText(this, "" + customer.statusMessage + "\nPlease login again.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, DashboardActivity.class));
            finish();

        } else if (customer.statusCode == 200) {

            startActivity(new Intent(this, DashboardActivity.class));
            finish();

        } else if (customer.statusCode == 404) {

            Toast.makeText(this, "" + customer.statusMessage, Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setIcon(R.drawable.icon_error_alert);
            builder.setMessage("Unexpected error occurred in server.\nPlease try later.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                    finish();
                }
            });
            builder.show();
        } else {

            Toast.makeText(this, "" + customer.statusMessage, Toast.LENGTH_SHORT).show();
        }

    }
}
