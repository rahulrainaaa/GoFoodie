package com.app.gofoodie.activity.derived;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CartOrderActivity extends BaseAppCompatActivity implements View.OnClickListener, NetworkCallbackListener {

    public static final String TAG = "CartOrderActivity";

    private RecyclerView mRecyclerView = null;
    private CartOrderRecyclerAdapter mCartOrderRecyclerAdapter = null;
    private ArrayList<CartOrder> mList = new ArrayList<>();
    public ArrayList<Cart> cartArrayList = GlobalData.cartArrayList;
    private Date mStartDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_order);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Iterator<Cart> cartIterator = cartArrayList.iterator();
        while (cartIterator.hasNext()) {

            Cart cart = cartIterator.next();
            int qty = Integer.valueOf(cart.quantity.trim());
            for (int i = 0; i < qty; i++) {

                mList.add(new CartOrder(cart));
            }
        }

        GlobalData.cartOrderArrayList = mList;
        Date startDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 2);
        assignDate(cal.getTime());

        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(categoryLayoutManager);
        mCartOrderRecyclerAdapter = new CartOrderRecyclerAdapter(this, R.layout.item_rv_cart_order, this, mList);
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
            case R.id.menu_item_proceed:

                menuProceed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private ItemTouchHelper.Callback createCallbackHelper() {


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {

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

                int a = (int) ((CartOrderRecyclerAdapter.ItemHolder) viewHolder).ibtnEdit.getTag();
                int b = (int) ((CartOrderRecyclerAdapter.ItemHolder) target).ibtnEdit.getTag();

                ((CartOrderRecyclerAdapter.ItemHolder) viewHolder).ibtnEdit.setTag(b);
                ((CartOrderRecyclerAdapter.ItemHolder) target).ibtnEdit.setTag(a);

                String date1 = ((CartOrderRecyclerAdapter.ItemHolder) viewHolder).txtDate.getText().toString();
                String date2 = ((CartOrderRecyclerAdapter.ItemHolder) target).txtDate.getText().toString();

                ((CartOrderRecyclerAdapter.ItemHolder) viewHolder).txtDate.setText(date2);
                ((CartOrderRecyclerAdapter.ItemHolder) target).txtDate.setText(date1);

                return true;
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {

                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);

                Log.d("gggggggggggg", "onMoved fromPos = " + fromPos + " toPos = " + toPos);

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

        return simpleCallback;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtn_edit:

                customizeComboPlan(view);
                break;
        }
    }

    private void customizeComboPlan(View view) {

        int position = (int) view.getTag();
        Intent intent = new Intent(this, EditComboActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private void pickStartDate() {


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Toast.makeText(CartOrderActivity.this, "year  = " + year + ", month = " + month + ", day = " + day, Toast.LENGTH_SHORT).show();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                try {

                    month++;
                    Date date = sdf.parse(day + "-" + month + "-" + year);
                    assignDate(date);

                } catch (ParseException e) {

                    e.printStackTrace();
                    Toast.makeText(CartOrderActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        }, 2017, 10, 1);

        datePickerDialog.show();

    }

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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                mList.get(pos++).date = sdf.format(newDate) + " (" + dayOfWeek + ")";

            }
        }
        if (mCartOrderRecyclerAdapter != null) {

            mCartOrderRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private boolean checkForWeekDay(String weekDay) {

        String[] days = CustomerProfileHandler.CUSTOMER.profile.daysYouWantTheCombo.split(",");

        for (int i = 0; i < days.length; i++) {

            if (days[i].trim().toLowerCase().equals(weekDay.toLowerCase().trim())) {

                return true;
            }
        }

        return false;

    }

    private void menuProceed() {


        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setTitle("Place Order");
        pDialog.setCancelable(true);
        pDialog.show();
        pDialog.setConfirmText("Confirm");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                JSONObject jsonRequest = getOrderRequestPacket();
                if (jsonRequest == null) {

                    Toast.makeText(CartOrderActivity.this, "Json Request NULL", Toast.LENGTH_SHORT).show();
                    return;
                }
                NetworkHandler networkHandler = new NetworkHandler();
                networkHandler.httpCreate(1, CartOrderActivity.this, CartOrderActivity.this, jsonRequest, Network.URL_PLACE_ORDER, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                networkHandler.executePost();
                sweetAlertDialog.dismiss();
            }
        });

        pDialog.setCancelText("Cancel");

        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                sweetAlertDialog.dismiss();
            }
        });

    }

    private JSONObject getOrderRequestPacket() {

        try {
            JSONObject jsonRequest = new JSONObject();
            JSONArray arrCartOrder = new JSONArray();

            Iterator<CartOrder> cartOrderIterator = mList.iterator();
            while (cartOrderIterator.hasNext()) {

                CartOrder cartOrder = cartOrderIterator.next();
                JSONArray arrDescription = new JSONArray();

                Iterator<Description> descriptionIterator = cartOrder.description.iterator();
                while (descriptionIterator.hasNext()) {

                    Description description = descriptionIterator.next();
                    JSONArray optionsJsonArray = new JSONArray();

                    Iterator<String> optionIterator = description.options.iterator();
                    while (optionIterator.hasNext()) {

                        optionsJsonArray.put("" + optionIterator.next());
                    }
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
                objCartOrder.put("comboPrice", cartOrder.comboPrice);
                objCartOrder.put("delivery_date", cartOrder.date.trim().substring(0, 10));
                objCartOrder.put("restaurant_id", cartOrder.restaurantId);
                objCartOrder.put("branch_id", cartOrder.branchId);

                arrCartOrder.put(objCartOrder);
            }

            jsonRequest.put("cartorders", arrCartOrder);
            jsonRequest.put("customer_id", CustomerProfileHandler.CUSTOMER.profile.customerId);
            jsonRequest.put("token", getSessionData().getToken());
            jsonRequest.put("login_id", getSessionData().getLoginId());
            jsonRequest.put("delivery_address", CustomerProfileHandler.CUSTOMER.profile.address);
            jsonRequest.put("area", CustomerProfileHandler.CUSTOMER.profile.area);
            jsonRequest.put("geo_lat", CustomerProfileHandler.CUSTOMER.profile.geoLat);
            jsonRequest.put("geo_lng", CustomerProfileHandler.CUSTOMER.profile.geoLng);
            jsonRequest.put("wallet_id", CustomerProfileHandler.CUSTOMER.profile.walletId);

            JSONArray cartItemIdArray = new JSONArray();
            Iterator<Cart> cartIterator = GlobalData.cartArrayList.iterator();
            while (cartIterator.hasNext()) {

                cartItemIdArray.put(cartIterator.next().cartItemId.trim());
            }
            jsonRequest.put("cart_item_id", cartItemIdArray);
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

    private void handleOrderPlacedResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        OrderResponse orderResponse = (OrderResponse) parser.getModel(json.toString(), OrderResponse.class, null);

        if (orderResponse.statusCode == 200) {

            Intent intent = new Intent(this, InvoiceActivity.class);
            intent.putExtra("data", orderResponse);
            startActivity(intent);
            finish();
        } else {
            SweetAlertDialog sad = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
            sad.setTitleText("Failed");
            sad.setContentText("" + orderResponse.statusMessage);
        }

    }
}
