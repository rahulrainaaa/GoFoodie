package com.app.gofoodie.activity.derived;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gofoodie.R;
import com.app.gofoodie.activity.base.BaseAppCompatActivity;
import com.app.gofoodie.adapter.listviewadapter.EditComboListViewAdapter;
import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.model.cartOrder.CartOrder;
import com.app.gofoodie.model.cartOrder.Description;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Activity to edit/customize your combo meal items (based on plan).
 */
public class EditComboActivity extends BaseAppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String TAG = "EditComboActivity";

    /**
     * Class private data member(s).
     */
    private ListView mListView = null;
    private ArrayList<Description> mList = new ArrayList<>();
    private EditComboListViewAdapter mAdapter = null;
    private CartOrder mCartOrder = null;
    private TextView mTxtComboName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_combo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int position = getIntent().getIntExtra("position", -1);
        if (position < 0) {

            Toast.makeText(this, "'position' not passed with Intent.", Toast.LENGTH_SHORT).show();
            return;
        }
        mCartOrder = GlobalData.cartOrderArrayList.get(position);
        mList = (ArrayList<Description>) mCartOrder.description;
        mListView = findViewById(R.id.list_view);
        mTxtComboName = findViewById(R.id.txt_combo_name);
        mAdapter = new EditComboListViewAdapter(this, R.layout.item_listview_edit_combo, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mTxtComboName.setText(mCartOrder.comboName);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        showListViewDialog(position);
    }

    /**
     * Method to show the list view dialog for customization.
     *
     * @param position
     */
    private void showListViewDialog(final int position) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setCancelable(true);
        String match = mList.get(position).value;
        ArrayList<String> choices = new ArrayList<>();
        Iterator<String> iterator = mList.get(position).options.iterator();
        while (iterator.hasNext()) {

            choices.add(iterator.next().trim());
        }
        final ItemAdapter arrayAdapter = new ItemAdapter(this, choices, match);

        builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
            String strName = arrayAdapter.getItem(which);
            mList.get(position).value = strName.trim();
            mAdapter.notifyDataSetChanged();

        });
        builderSingle.show();

    }

    /**
     * {@link ItemAdapter} adapter class to show the options (choice) for combo item(s).
     */
    private class ItemAdapter extends ArrayAdapter<String> {

        /**
         * Inner class private data member(s).
         */

        private String match = null;
        private ArrayList<String> choices = null;

        public ItemAdapter(@NonNull Context context, ArrayList<String> list, String match) {
            super(context, R.layout.item_combo_item, list);

            this.match = match;
            this.choices = list;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            CheckedTextView view = (CheckedTextView) getLayoutInflater().inflate(R.layout.item_combo_item, null);
            view.setText(choices.get(position));
            view.setChecked(choices.get(position).trim().contains(match.trim()));
            return view;
        }
    }


}
