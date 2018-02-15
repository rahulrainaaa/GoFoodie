package com.app.gofoodie.activity.derived;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.recyclerviewadapter.CartOrderRecyclerAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.cart.Cart;
import com.app.gofoodie.model.cartOrder.CartOrder;
import com.app.gofoodie.model.cartOrder.Description;
import com.app.gofoodie.model.order.OrderResponse;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.app.gofoodie.utility.DateUtils;
import com.app.gofoodie.utility.VibrationUtil;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

/**
 * Activity class to handle the CartOrders, date assigning and combo item option selection.
 */
public class CartOrderActivity extends BaseAppCompatActivity implements View.OnClickListener, NetworkCallbackListener {

    public static final String TAG = "CartOrderActivity";

    /**
     * Class private data members.
     */
    private RecyclerView mRecyclerView = null;
    private CartOrderRecyclerAdapter mCartOrderRecyclerAdapter = null;
    private ArrayList<CartOrder> mList = new ArrayList<>();
    private ArrayList<Cart> cartArrayList = GlobalData.cartArrayList;
    private Date mStartDate = null;

    // Price data fields.
    private float mTotalPrice = 0f;
    private float mComboPrice = 0f;
    private float mTaxPrice = 0f;
    private float mPayPrice = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_order);
        mRecyclerView = findViewById(R.id.recycler_view);

        // Price analysis for pay price and tax payments.
        for (Cart cart : cartArrayList) {

            int qty = Integer.valueOf(cart.getQuantity().trim());
            for (int i = 0; i < qty; i++) {

                try {
                    mTotalPrice = mTotalPrice + Float.parseFloat(cart.getPayPrice().trim()) + Float.parseFloat(cart.getZoneShippingCharge().trim());
                    mComboPrice = mComboPrice + Float.parseFloat(cart.getPayPrice().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                    mTotalPrice = -9999999;     // make price -ve if price not parsed. -ve price means wrong price.
                }
                mList.add(new CartOrder(cart));
            }
        }

        float mTaxPercent = getIntent().getFloatExtra("taxPercent", 0f);
        mTaxPrice = (mTaxPercent * mComboPrice) / 100;

        mPayPrice = mTotalPrice + mTaxPrice;

        GlobalData.cartOrderArrayList = mList;
        Date startDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 2);
        assignDate(cal.getTime());

        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(categoryLayoutManager);
        mCartOrderRecyclerAdapter = new CartOrderRecyclerAdapter(this, mList);
        mRecyclerView.setAdapter(mCartOrderRecyclerAdapter);
        mCartOrderRecyclerAdapter.notifyDataSetChanged();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createCallbackHelper());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_cart_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_item_pick_date:

                pickStartDate();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback method for 'Proceed' button on click.
     *
     * @param view reference.
     */
    public void btnClickProceed(View view) {


        // Check if there is any error in the calculation. Then show error message box.
        if (mTotalPrice < 0) {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Some Error occurred in total price calculation.")
                    .show();
            return;
        }

        // Check if customer has the subscription plan.
        if (CustomerProfileHandler.CUSTOMER.getProfile().getValidUpto().trim().isEmpty()) {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Failed")
                    .setContentText("No subscription plan.\nFirst go for subscription plan.")
                    .show();
            return;
        }


        //Prompt to place an order.
        //Show the price and tax payment.
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Place Order");
        pDialog.setContentText("Total Price: " + mComboPrice + " AED"
                + "\n Shipping Price: " + (mTotalPrice - mComboPrice) + " AED"
                + "\nApplied Tax: " + mTaxPrice + " AED"
                + "\nFinal Price: " + mPayPrice + " AED");
        pDialog.setCancelable(false);
        pDialog.setConfirmText("Place Order");
        pDialog.setConfirmClickListener(sweetAlertDialog -> {

            // Create a request packet and proceed to place and order.
            JSONObject jsonRequest = getOrderRequestPacket();
            if (jsonRequest == null) {

                Toast.makeText(CartOrderActivity.this, "Json Request NULL", Toast.LENGTH_SHORT).show();
                return;
            }
            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, CartOrderActivity.this, CartOrderActivity.this, jsonRequest, Network.URL_PLACE_ORDER, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
            networkHandler.executePost();

            sweetAlertDialog.dismissWithAnimation();

        });
        pDialog.setCancelClickListener(SweetAlertDialog::dismissWithAnimation);
        pDialog.setCancelText("Cancel");
        pDialog.show();

    }

    /**
     * {@link ItemTouchHelper} class to handle the RecyclerView gestures and item changing positions.
     */
    private ItemTouchHelper.Callback createCallbackHelper() {

        return new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean isLongPressDragEnabled() {

                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {

                return true;
            }


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                int a = (int) ((CartOrderRecyclerAdapter.ItemHolder) viewHolder).iBtnEdit.getTag();
                int b = (int) ((CartOrderRecyclerAdapter.ItemHolder) target).iBtnEdit.getTag();

                ((CartOrderRecyclerAdapter.ItemHolder) viewHolder).iBtnEdit.setTag(b);
                ((CartOrderRecyclerAdapter.ItemHolder) target).iBtnEdit.setTag(a);

                String date1 = ((CartOrderRecyclerAdapter.ItemHolder) viewHolder).txtDate.getText().toString();
                String date2 = ((CartOrderRecyclerAdapter.ItemHolder) target).txtDate.getText().toString();

                ((CartOrderRecyclerAdapter.ItemHolder) viewHolder).txtDate.setText(date2);
                ((CartOrderRecyclerAdapter.ItemHolder) target).txtDate.setText(date1);

                VibrationUtil.getInstance().vibrate(CartOrderActivity.this);
                return true;
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {

                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
                Collections.swap(mList, fromPos, toPos);

                // Date should not swap.
                String temp = mList.get(fromPos).date;
                mList.get(fromPos).date = mList.get(toPos).date;
                mList.get(toPos).date = temp;
                mCartOrderRecyclerAdapter.notifyItemMoved(fromPos, toPos);
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtn_edit:

                customizeComboPlan(view);
                break;
        }
    }

    /**
     * Method to start activity to customize a combo plan.
     *
     * @param view reference.
     */
    private void customizeComboPlan(View view) {

        int position = (int) view.getTag();
        Intent intent = new Intent(this, EditComboActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    /**
     * Method to show DatePicker to pick the starting date of Orders.
     */
    private void pickStartDate() {

        Calendar todayCal = Calendar.getInstance();
        int curDay = todayCal.get(Calendar.DAY_OF_MONTH);
        int curMonth = todayCal.get(Calendar.MONTH);
        int curYear = todayCal.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year, month, day) -> {

            Calendar selectedDay = Calendar.getInstance();
            selectedDay.set(Calendar.DAY_OF_MONTH, day);
            selectedDay.set(Calendar.MONTH, month);
            selectedDay.set(Calendar.YEAR, year);
            selectedDay.set(Calendar.HOUR, 0);
            selectedDay.set(Calendar.MINUTE, 0);
            selectedDay.set(Calendar.SECOND, 0);

            Date selectedDate = selectedDay.getTime();

            Calendar todayCAL = Calendar.getInstance();
            todayCAL.set(Calendar.HOUR, 0);
            todayCAL.set(Calendar.MINUTE, 0);
            todayCAL.set(Calendar.HOUR, 0);
            Date currentDate = todayCAL.getTime();

            selectedDay.set(Calendar.HOUR, 0);
            selectedDay.set(Calendar.MINUTE, 0);
            selectedDay.set(Calendar.SECOND, 0);
            long diff = selectedDay.getTimeInMillis();
            long dayDiff = diff / (24 * 60 * 60 * 1000);

            if (diff < 0) {

                Toast.makeText(CartOrderActivity.this, "Wrong date selected.\nSelecting min after 2 days from today", Toast.LENGTH_SHORT).show();
                pickStartDate();

            } else if (dayDiff < 2) {

                Toast.makeText(CartOrderActivity.this, "Order date should start from min 2 days after today.", Toast.LENGTH_SHORT).show();
                pickStartDate();

            } else {

                assignDate(selectedDate);
            }


        }, curYear, curMonth, curDay);


        datePickerDialog.show();
    }

    /**
     * Method to assign date of orders from picked date (or after 2 days in default case).
     *
     * @param startDate starting date for the order set.
     */
    private void assignDate(Date startDate) {

        if (startDate == null) {
            startDate = mStartDate;
        } else {
            mStartDate = startDate;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, -1);

        int pos = 0;
        while (pos < mList.size()) {

            cal.add(Calendar.DATE, 1);
            String dayOfWeek = DateUtils.getInstance().weekName(cal.get(Calendar.DAY_OF_WEEK));

            if (checkForWeekDay(dayOfWeek)) {

                Date newDate = cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                mList.get(pos++).date = sdf.format(newDate) + " (" + dayOfWeek + ")";

            }
        }
        if (mCartOrderRecyclerAdapter != null) {

            mCartOrderRecyclerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Method to check, if week day is working of off.
     *
     * @param weekDay week day to check with.
     * @return true = if day is working.
     */
    private boolean checkForWeekDay(String weekDay) {

        String[] days = CustomerProfileHandler.CUSTOMER.getProfile().getDaysYouWantTheCombo().split(",");

        for (String day : days) {

            if (day.trim().toLowerCase().equals(weekDay.toLowerCase().trim())) {

                return true;
            }
        }

        return false;

    }

    /**
     * Method to process the response packet of OrderRequest (Placed order).
     *
     * @return JSONObject
     */
    private JSONObject getOrderRequestPacket() {

        try {
            JSONObject jsonRequest = new JSONObject();
            JSONArray arrCartOrder = new JSONArray();

            for (CartOrder cartOrder : mList) {

                JSONArray arrDescription = new JSONArray();

                for (Description description : cartOrder.description) {

                    JSONArray optionsJsonArray = new JSONArray(cartOrder.description);

                    JSONObject objDescription = new JSONObject();
                    objDescription.put("options", optionsJsonArray);
                    objDescription.put("name", description.name);
                    objDescription.put("value", description.value);
                    objDescription.put("item_id", description.itemId);

                    arrDescription.put(objDescription);
                }

                JSONObject objCartOrder = new JSONObject();
                objCartOrder.put("description", arrDescription);
                objCartOrder.put("combo_id", cartOrder.comboId);
                objCartOrder.put("comboPrice", cartOrder.comboPrice.trim());
                objCartOrder.put("pay_price", cartOrder.payPrice.trim());
                objCartOrder.put("branch_id", cartOrder.branchId);
                objCartOrder.put("delivery_date", cartOrder.date.trim().substring(0, 10));
                objCartOrder.put("restaurant_id", cartOrder.restaurantId);


                arrCartOrder.put(objCartOrder);
            }

            JSONArray cartItemIdArray = new JSONArray();

            for (Cart cart : GlobalData.cartArrayList) {

                cartItemIdArray.put(cart.getCartItemId().trim());
            }

            jsonRequest.put("delivery_address", CustomerProfileHandler.CUSTOMER.getProfile().getAddress());
            jsonRequest.put("cart_item_id", cartItemIdArray);
            jsonRequest.put("cartorders", arrCartOrder);
            jsonRequest.put("customer_id", CustomerProfileHandler.CUSTOMER.getProfile().getCustomerId());
            jsonRequest.put("token", getSessionData().getToken());
            jsonRequest.put("login_id", getSessionData().getLoginId());
            jsonRequest.put("area", CustomerProfileHandler.CUSTOMER.getProfile().getArea());
            jsonRequest.put("geo_lat", CustomerProfileHandler.CUSTOMER.getProfile().getGeoLat());
            jsonRequest.put("geo_lng", CustomerProfileHandler.CUSTOMER.getProfile().getGeoLng());
            jsonRequest.put("wallet_id", CustomerProfileHandler.CUSTOMER.getProfile().getWalletId());
            jsonRequest.put("finalPrice", String.valueOf(mPayPrice));
            jsonRequest.put("customer_id", CustomerProfileHandler.CUSTOMER.getProfile().getCustomerId().trim());
            jsonRequest.put("customer_name", CustomerProfileHandler.CUSTOMER.getProfile().getName().trim());
            jsonRequest.put("customer_email", CustomerProfileHandler.CUSTOMER.getProfile().getEmail().trim());

            return jsonRequest;

        } catch (JSONException jsonExc) {

            jsonExc.printStackTrace();
            Toast.makeText(this, "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();

            return null;
        }

    }

    /**
     * {@link NetworkCallbackListener} http response listener callback method(s).
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {

            handleOrderPlacedResponse(rawObject);
        }
    }


    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http Fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * method to handle the order placed response.
     *
     * @param json {@link JSONObject} http response packet.
     */
    private void handleOrderPlacedResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        OrderResponse orderResponse = (OrderResponse) parser.getModel(json.toString(), OrderResponse.class, null);

        Toast.makeText(this, "" + orderResponse.getStatusMessage(), Toast.LENGTH_SHORT).show();
        if (orderResponse.getStatusCode() == 200) {


            // Check of the placed orders object is null.
            // Raise an error.
            if (orderResponse.getPlacedOrders() == null) {

                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Failed")
                        .setContentText("" + orderResponse.getStatusMessage())
                        .show();
                return;
            }
            Intent intent = new Intent(this, InvoiceActivity.class);
            intent.putExtra("data", orderResponse);
            startActivity(intent);
            finish();
        } else {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Failed")
                    .setContentText("" + orderResponse.getStatusMessage())
                    .show();

        }

    }
}
