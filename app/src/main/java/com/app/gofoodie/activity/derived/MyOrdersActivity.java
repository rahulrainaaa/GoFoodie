package com.app.gofoodie.activity.derived;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.MyOrdersListViewAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.cancellationHandler.OrderCancellationHandler;
import com.app.gofoodie.handler.cancellationHandler.OrderCancellationListener;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.handler.profileDataHandler.CustomerProfileHandler;
import com.app.gofoodie.model.myorders.MyOrder;
import com.app.gofoodie.model.myorders.MyOrdersResponse;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class MyOrdersActivity extends BaseAppCompatActivity implements NetworkCallbackListener, View.OnClickListener {

    public static final String TAG = "MyOrdersActivity";

    /**
     * Class private data member(s).
     */
    ListView mListViewOrders = null;
    private ArrayList<MyOrder> mList = null;
    private MyOrdersListViewAdapter mAdapter = null;

    private String mStrFromDate = null;
    private String mStrToDate = null;
    private OrderCancellationListener mOrderCancellationListener = new OrderCancellationListener() {
        @Override
        public void orderCancellationApplied(OrderCancellationHandler.RESP_CODE responseCode, OrderCancellationHandler.OP_CODE operationCode, JSONObject jsonResponse, String message) {

            if (responseCode != OrderCancellationHandler.RESP_CODE.RESP_SUCCESS) {

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
            fetchMyOrders(null, null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        mListViewOrders = (ListView) findViewById(R.id.list_view_my_orders);
        fetchMyOrders(null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_orders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_item_date:

                menuItemDateSelected();
                break;

        }
        return true;
    }

    private void fetchMyOrders(String fromDate, String toDate) {

        mStrFromDate = fromDate;
        mStrToDate = toDate;

        String url = Network.URL_GET_MY_ORDER + "?customerid=" + getSession().getData().getCustomerId();
        if (fromDate != null && toDate != null) {

            url = url + "&from=" + fromDate.trim() + "&to=" + toDate.trim();
        }

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    private void menuItemDateSelected() {

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year1, int month1, int day1, int year2, int month2, int day2) {

                        month1++;
                        month2++;
                        String fromDate = year1 + "-" + month1 + "-" + day1;
                        String toDate = year2 + "-" + month2 + "-" + day2;
                        fetchMyOrders(fromDate, toDate);
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAutoHighlight(true);
        dpd.show(getFragmentManager(), "Select Date Range");

    }

    /**
     * {@link NetworkCallbackListener} http response listener callback method.
     */
    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        if (requestCode == 1) {

            handleMyOrdersResponse(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http Fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param json
     * @method handleMyOrdersResponse
     * @desc Method to handle the MyOrder response from web api.
     */
    private void handleMyOrdersResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        MyOrdersResponse myOrdersResponse = (MyOrdersResponse) parser.getModel(json.toString(), MyOrdersResponse.class, null);

        if (myOrdersResponse.statusCode != 200 || myOrdersResponse.myOrders == null) {

            Toast.makeText(this, "" + myOrdersResponse.statusMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        mList = (ArrayList<MyOrder>) myOrdersResponse.myOrders;
        mAdapter = new MyOrdersListViewAdapter(this, this, R.layout.item_listview_my_orders, mList);
        mListViewOrders.setAdapter(mAdapter);

    }

    /**
     * {@link android.view.View.OnClickListener} click event listener callback method(s).
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.add_rating:

                addRating(view);
                break;
            case R.id.show_desc:

                showDescription(view);
                break;
            case R.id.edit:

                editCancelOrder(view);
                break;
        }
    }

    private void addRating(View v) {

        final MyOrder order = (MyOrder) v.getTag();

        View view = getLayoutInflater().inflate(R.layout.rating_bar_layout, null);
        final RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(order.comboname);
        alertDialog.setView(view);

        alertDialog.setCancelable(true);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Rate",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        int rating = (int) (ratingBar.getRating());
                        String comment = "";
                        switch (rating) {

                            case 0:
                                comment = "Worst";
                                break;
                            case 1:
                                comment = "Poor";
                                break;
                            case 2:
                                comment = "Below Average";
                                break;
                            case 3:
                                comment = "Average";
                                break;
                            case 4:
                                comment = "Good";
                                break;
                            case 5:
                                comment = "Excellent";
                                break;
                        }

                        try {

                            JSONObject jsonRequest = new JSONObject();
                            jsonRequest.put("customer_id", getSession().getData().getCustomerId());
                            jsonRequest.put("login_id", getSession().getData().getLoginId());
                            jsonRequest.put("restaurant_id", order.restaurantId.trim());
                            jsonRequest.put("branch_id", order.branchId.trim());
                            jsonRequest.put("combo_id", order.comboId.trim());
                            jsonRequest.put("order_id", order.orderId.trim());
                            jsonRequest.put("rating", rating + "");
                            jsonRequest.put("comment", comment);
                            jsonRequest.put("reviewer", CustomerProfileHandler.CUSTOMER.profile.name);
                            jsonRequest.put("token", getSession().getData().getToken().trim());

                            NetworkHandler networkHandler = new NetworkHandler();
                            networkHandler.httpCreate(1, MyOrdersActivity.this, new NetworkCallbackListener() {
                                @Override
                                public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

                                    try {

                                        Toast.makeText(getApplicationContext(), "" + rawObject.getString("statusMessage").trim(), Toast.LENGTH_SHORT).show();

                                    } catch (JSONException jsonExc) {

                                        jsonExc.printStackTrace();
                                        Toast.makeText(getApplicationContext(), "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void networkFailResponse(int requestCode, String message) {

                                    Toast.makeText(getApplicationContext(), "Http Fail: " + message, Toast.LENGTH_SHORT).show();

                                }
                            }, jsonRequest, Network.URL_POST_REVIEW, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
                            networkHandler.executePost();

                        } catch (JSONException jsonExc) {

                            jsonExc.printStackTrace();
                            Toast.makeText(getApplicationContext(), "JSONException: " + jsonExc.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    private void showDescription(View v) {

        MyOrder order = (MyOrder) v.getTag();

        View view = getLayoutInflater().inflate(R.layout.dialog_combo_details, null);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(order.comboname);
        alertDialog.setView(view);
        ((RatingBar) view.findViewById(R.id.rating_bar)).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.type_n_cuisine)).setText("  " + order.deliveryDate);
        ((TextView) view.findViewById(R.id.desc)).setText(order.status);
        ((TextView) view.findViewById(R.id.txt_price)).setText("AED " + order.pricePaid);
        view.findViewById(R.id.img_veg).setVisibility(View.GONE);
        view.findViewById(R.id.img_nonveg).setVisibility(View.GONE);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    private void editCancelOrder(View view) {

        MyOrder order = (MyOrder) view.getTag();

        if (!order.status.toLowerCase().trim().equals("accepted")) {

            Toast.makeText(this, "Cannot cancel this order.", Toast.LENGTH_SHORT).show();
            return;
        }

        OrderCancellationHandler orderCancellationHandler = new OrderCancellationHandler(this);
        orderCancellationHandler.showCancellationOptions(order, mOrderCancellationListener);

    }
}
