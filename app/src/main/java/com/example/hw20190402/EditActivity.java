package com.example.hw20190402;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    TextView nameTxt, lastNameTxt, phoneTxt, cityTxt;
    String name, lastName, phone, city;
    Button editBtn;
    private static final String POSITION = "POSITION";
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        nameTxt = findViewById(R.id.edit_name);
        lastNameTxt = findViewById(R.id.edit_last_name);
        phoneTxt = findViewById(R.id.edit_phone);
        cityTxt = findViewById(R.id.edit_city);
        editBtn = findViewById(R.id.edit_btn_edit);
        editBtn.setOnClickListener(this);
        position = getIntent().getIntExtra(POSITION, -1);
        if (position == -1) {
            setValuesToTextFields("");
        } else {
            setValuesToTextFields(StoreProvider.getInstance().getProfile(position));
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit_btn_edit) {
            Intent intent = new Intent(this, Add.class);
            intent.putExtra(POSITION, position);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MyListActivity.class));
        finish();
    }

    private void setValuesToTextFields(String s) {
        nameTxt.setText(s);
        lastNameTxt.setText(s);
        phoneTxt.setText(s);
        cityTxt.setText(s);
    }

    private void setValuesToTextFields(Profile profile) {
        nameTxt.setText(profile.name);
        lastNameTxt.setText(profile.lastName);
        phoneTxt.setText(profile.phone);
        cityTxt.setText(profile.city);
    }
}
