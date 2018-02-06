package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
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
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MyOrdersActivity extends BaseAppCompatActivity implements NetworkCallbackListener, View.OnClickListener, RatingDialogListener {

    public static final String TAG = "MyOrdersActivity";

    /**
     * Class private data member(s).
     */
    ListView mListViewOrders = null;
    private ArrayList<MyOrder> mList = null;
    private MyOrdersListViewAdapter mAdapter = null;
    private MyOrder reviewMyOrder = null;
    private String mStrFromDate = null;
    private String mStrToDate = null;
    private OrderCancellationListener mOrderCancellationListener = new OrderCancellationListener() {
        @Override
        public void orderCancellationApplied(OrderCancellationHandler.RESP_CODE responseCode, OrderCancellationHandler.OP_CODE operationCode, JSONObject jsonResponse, String message) {

            if (responseCode != OrderCancellationHandler.RESP_CODE.RESP_SUCCESS) {

                new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error...")
                        .setContentText(message)
                        .show();
            } else {

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

    /**
     * Method to fetch all my orders within the selected date range.
     * Use default value for today's date.
     *
     * @param fromDate
     * @param toDate
     */
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

        if (myOrdersResponse.getStatusCode() != 200 || myOrdersResponse.getMyOrders() == null) {

            SweetAlertDialog s = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(myOrdersResponse.getStatusMessage().trim())
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            finish();
                        }
                    });
            s.setCancelable(false);
            s.show();
            return;
        }

        mList = (ArrayList<MyOrder>) myOrdersResponse.getMyOrders();
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

        reviewMyOrder = (MyOrder) v.getTag();

        int defaultRating = 0;

        try {
            defaultRating = Integer.parseInt(reviewMyOrder.getRating().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNeutralButtonText("Later")
                .setNoteDescriptions(Arrays.asList("Worst", "Poor", "Average", "Good", "Excellent !!!"))
                .setDefaultRating(defaultRating)
                .setTitle("Rate Order")
                .setDescription("Rate It!")
                .setDefaultComment(reviewMyOrder.getComment().trim() + " ")
                .setStarColor(R.color.colorPrimary)
                .setNoteDescriptionTextColor(R.color.text_color)
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.text_color)
                .setHint("Your comment...")
                .setHintTextColor(R.color.text_hint_color)
                .setCommentTextColor(R.color.text_color)
                .setCommentBackgroundColor(R.color.colorLittleWhite)
                .setWindowAnimation(R.anim.modal_in)
                .create(MyOrdersActivity.this)
                .show();


    }

    /**
     * Method to show the order description click.
     *
     * @param v
     */
    private void showDescription(View v) {

        MyOrder myOrder = (MyOrder) v.getTag();

        String status = "Order status is already " + myOrder.getStatus();
        /**
         * check if the order is accepted then only proceed.
         */
        if (!myOrder.getStatus().toLowerCase().trim().equals("accepted")) {

            Toast.makeText(this, status.trim(), Toast.LENGTH_SHORT).show();
            return;
        }

        OrderCancellationHandler orderCancellationHandler = new OrderCancellationHandler(this);
        orderCancellationHandler.showCancellationOptions(myOrder, mOrderCancellationListener, 2);


//        View view = getLayoutInflater().inflate(R.layout.dialog_combo_details, null);
//        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle(order.comboname);
//        alertDialog.setView(view);
//        ((RatingBar) view.findViewById(R.id.rating_bar)).setVisibility(View.GONE);
//        ((TextView) view.findViewById(R.id.type_n_cuisine)).setText("  " + order.deliveryDate);
//        ((TextView) view.findViewById(R.id.desc)).setText(order.status);
//        ((TextView) view.findViewById(R.id.txt_price)).setText("AED " + order.pricePaid);
//        view.findViewById(R.id.img_veg).setVisibility(View.GONE);
//        view.findViewById(R.id.img_nonveg).setVisibility(View.GONE);
//        alertDialog.setCancelable(false);
//        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        alertDialog.show();

    }

    /**
     * Method to edit: cancel the combo order.
     *
     * @param view
     */
    private void editCancelOrder(View view) {

        MyOrder order = (MyOrder) view.getTag();

        String status = "Status is already " + order.getStatus();
        /**
         * Check if the order already cancelled.
         */
        if (!order.getStatus().toLowerCase().trim().equals("accepted")) {

            Toast.makeText(this, status.trim(), Toast.LENGTH_SHORT).show();
            return;
        }

        OrderCancellationHandler orderCancellationHandler = new OrderCancellationHandler(this);
        orderCancellationHandler.showCancellationOptions(order, mOrderCancellationListener, 1);

    }

    @Override
    public void onPositiveButtonClicked(int rating, String comment) {

        try {

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("customer_id", getSession().getData().getCustomerId());
            jsonRequest.put("login_id", getSession().getData().getLoginId());
            jsonRequest.put("restaurant_id", reviewMyOrder.getRestaurantId().trim());
            jsonRequest.put("branch_id", reviewMyOrder.getBranchId().trim());
            jsonRequest.put("combo_id", reviewMyOrder.getComboId().trim());
            jsonRequest.put("order_id", reviewMyOrder.getOrderId().trim());
            jsonRequest.put("rating", rating + "");
            jsonRequest.put("comment", comment);
            jsonRequest.put("reviewer", CustomerProfileHandler.CUSTOMER.getProfile().getName());
            jsonRequest.put("token", getSession().getData().getToken().trim());

            NetworkHandler networkHandler = new NetworkHandler();
            networkHandler.httpCreate(1, MyOrdersActivity.this, new NetworkCallbackListener() {
                @Override
                public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

                    reviewMyOrder.setRating("" + rating);
                    reviewMyOrder.setComment("" + comment);
                    Toast.makeText(getApplicationContext(), "Thank you", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }
}
