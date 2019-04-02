package com.example.hw20190402;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MyListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String MYLIST = "MYLIST";
    private MyAdapter adapter;
    Button btnLogout, btnAdd;
    private final String POSITION = "POSITION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        ListView list = findViewById(R.id.mylist);
        adapter = new MyAdapter();
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        btnAdd = findViewById(R.id.mylist_btn_add);
        btnLogout = findViewById(R.id.mylist_btn_logout);
        btnAdd.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(POSITION, position);
        startActivity(intent);
        finish();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mylist_btn_logout) {
            StoreProvider.getInstance().logout();
            setResult(RESULT_CANCELED);
            finish();
        }

        if (v.getId() == R.id.mylist_btn_add) {
            Intent intent = new Intent(this, Add.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
