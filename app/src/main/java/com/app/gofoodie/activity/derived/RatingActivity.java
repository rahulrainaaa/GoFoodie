package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.RatingListViewAdapter;
import com.app.gofoodie.global.constants.Network;
import com.app.gofoodie.handler.modelHandler.ModelParser;
import com.app.gofoodie.model.ratings.Ratings;
import com.app.gofoodie.model.ratings.Review;
import com.app.gofoodie.network.callback.NetworkCallbackListener;
import com.app.gofoodie.network.handler.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RatingActivity extends BaseAppCompatActivity implements NetworkCallbackListener {

    private ListView mListView = null;
    private ArrayList<Review> mList = new ArrayList<>();
    private RatingListViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new RatingListViewAdapter(this, R.layout.item_list_rating, mList);
        mListView.setAdapter(mAdapter);

        String url = Network.URL_GET_BRANCH_REV + "1";

        NetworkHandler networkHandler = new NetworkHandler();
        networkHandler.httpCreate(1, this, this, new JSONObject(), url, NetworkHandler.RESPONSE_TYPE.JSON_OBJECT);
        networkHandler.executeGet();
    }

    @Override
    public void networkSuccessResponse(int requestCode, JSONObject rawObject, JSONArray rawArray) {

        Toast.makeText(this, "Http Success: " + rawObject.toString(), Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {

            handleReviewResponse(rawObject);
        }
    }

    @Override
    public void networkFailResponse(int requestCode, String message) {

        Toast.makeText(this, "Http fail: " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @method handleReviewResponse
     * @desc Method to handle the {@link android.media.Rating} http response.
     */
    private void handleReviewResponse(JSONObject json) {

        ModelParser parser = new ModelParser();
        Ratings ratings = (Ratings) parser.getModel(json.toString(), Ratings.class, null);

        mList = (ArrayList<Review>) ratings.review;
        RatingListViewAdapter mAdapter = new RatingListViewAdapter(this, R.layout.item_list_rating, mList);
        mListView.setAdapter(mAdapter);
    }
}
