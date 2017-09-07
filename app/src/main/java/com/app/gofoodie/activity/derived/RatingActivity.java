package com.app.gofoodie.activity.derived;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.app.gofoodie.R;
import com.app.gofoodie.adapter.listviewadapter.RatingListViewAdapter;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {

    private ListView mListView = null;
    private ArrayList<String> mList = new ArrayList<>();
    private RatingListViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        for (int i = 0; i < 30; i++) {
            mList.add("Rating-" + i);
        }
        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new RatingListViewAdapter(this, R.layout.item_list_rating, mList);
        mListView.setAdapter(mAdapter);

        showDialog();
    }

    public void showDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_combo_details, null);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Combo Plan Name");
        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
