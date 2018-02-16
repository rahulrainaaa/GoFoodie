package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.order.OrderResponse;
import com.app.gofoodie.model.order.PlacedOrders;

/**
 * Activity class to get the {@link OrderResponse} as intent and show it as Invoice on Activity UI.
 */
public class InvoiceActivity extends BaseAppCompatActivity {

    private static final String TAG = "InvoiceActivity";

    /**
     * {@link BaseAppCompatActivity} activity lifecycle callback method(s).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        OrderResponse orderResponse = getIntent().getParcelableExtra("data");

        if (orderResponse == null) {

            Toast.makeText(this, "Data not found in Intent.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        PlacedOrders order = orderResponse.getPlacedOrders();

        /*
      Class private data member(s).
     */
        TextView txtOrderId = findViewById(R.id.txt_order_id);
        TextView txtPaidPrice = findViewById(R.id.price_paid);
        TextView txtDate = findViewById(R.id.date);
        TextView txtTotalOrders = findViewById(R.id.total_orders);
        Button btnDone = findViewById(R.id.btn_done);

        txtOrderId.setText("OrderID: " + order.getOrderSetId());
        txtPaidPrice.setText("Paid " + order.getPricePaid() + " AED");
        txtDate.setText("From: " + order.getStartDate() + "\nTill: " + order.getEndDate());
        txtTotalOrders.setText(order.getOrderCount() + " Orders placed");

        btnDone.setOnClickListener(view -> finish());

        CustomerProfileHandler customerProfileHandler = new CustomerProfileHandler(this);
        customerProfileHandler.refresh(this, null);

        Log.d(TAG, orderResponse.toString());
    }

}
