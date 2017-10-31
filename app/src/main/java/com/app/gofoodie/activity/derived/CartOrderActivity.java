package com.app.gofoodie.activity.derived;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.recyclerviewadapter.CartOrderRecyclerAdapter;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.model.cart.Cart;
import com.app.gofoodie.model.cartOrder.CartOrder;

import java.util.ArrayList;
import java.util.Iterator;

public class CartOrderActivity extends BaseAppCompatActivity implements View.OnClickListener {

    public static final String TAG = "CartOrderActivity";

    private RecyclerView mRecyclerView = null;
    private CartOrderRecyclerAdapter mCartOrderRecyclerAdapter = null;
    private ArrayList<CartOrder> mList = new ArrayList<>();
    public ArrayList<Cart> cartArrayList = GlobalData.cartArrayList;

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

        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(categoryLayoutManager);
        mCartOrderRecyclerAdapter = new CartOrderRecyclerAdapter(this, R.layout.item_rv_cart_order, this, mList);
        mRecyclerView.setAdapter(mCartOrderRecyclerAdapter);
        mCartOrderRecyclerAdapter.notifyDataSetChanged();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createCallbackHelper());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }


    private ItemTouchHelper.Callback createCallbackHelper() {


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                Log.d("gggggggggggggggg", "getMovementFlags");
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean isLongPressDragEnabled() {

                Log.d("gggggggggggggggg", "isLongPressDragEnabled");
                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {

                Log.d("gggggggggggggggg", "isItemViewSwipeEnabled");
                return true;
            }


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                Log.d("gggggggggggggggg", "onMove");
                return true;
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {

                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);

                Log.d("gggggggggggggggg", "onMoved fromPos = " + fromPos + " toPos = " + toPos + " x = " + x + " y = " + y);
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                Log.d("gggggggggggggggg", "onSwiped");
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

        CartOrder cartOrder = (CartOrder) view.getTag();
        startActivity(new Intent(this, EditComboActivity.class));
    }

}
