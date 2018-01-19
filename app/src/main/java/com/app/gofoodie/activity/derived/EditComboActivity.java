package com.app.gofoodie.activity.derived;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.EditComboListViewAdapter;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.model.cartOrder.CartOrder;
import com.app.gofoodie.model.cartOrder.Description;

import java.util.ArrayList;
import java.util.Iterator;

public class EditComboActivity extends BaseAppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView = null;
    private ArrayList<Description> mList = new ArrayList<>();
    private EditComboListViewAdapter mAdapter = null;
    private CartOrder mCartOrder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_combo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int position = getIntent().getIntExtra("position", -1);
        if (position < 0) {

            Toast.makeText(this, "'position' not passed with Intent.", Toast.LENGTH_SHORT).show();
            return;
        }
        mCartOrder = GlobalData.cartOrderArrayList.get(position);
        mList = (ArrayList<Description>) mCartOrder.description;
        mListView = (ListView) findViewById(R.id.list_view);


        mAdapter = new EditComboListViewAdapter(this, R.layout.item_listview_edit_combo, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        showListViewDialog(position);
    }

    private void showListViewDialog(final int position) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.icon_error_alert);
        builderSingle.setTitle("Select Option:");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);
        Iterator<String> iterator = mList.get(position).options.iterator();
        while (iterator.hasNext()) {

            arrayAdapter.add(iterator.next().trim());
        }

        builderSingle.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                mList.get(position).value = strName.trim();
                mAdapter.notifyDataSetChanged();

            }
        });
        builderSingle.show();
    }


}
