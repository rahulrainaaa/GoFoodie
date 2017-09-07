package com.app.gofoodie.activity.derived;

import android.os.Bundle;
import android.widget.ListView;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.CheckedListViewAdapter;
import com.app.gofoodie.model.category.Category;

import java.util.ArrayList;

public class MealPreferenceActivity extends BaseAppCompatActivity {

    private ListView mListView = null;
    private ArrayList<Category> mList = new ArrayList<>();
    private CheckedListViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_preference);

        for (int i = 0; i < 40; i++) {
            mList.add(new Category(i, "Category: " + i, true));
        }

        mListView = (ListView) findViewById(R.id.category_list_view);
        mAdapter = new CheckedListViewAdapter(this, mList);
        mListView.setAdapter(mAdapter);

    }

}
