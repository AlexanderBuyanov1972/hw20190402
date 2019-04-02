package com.example.hw20190402;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Add extends AppCompatActivity implements View.OnClickListener {
    private static final String POSITION = "POSITION";
    private static final String ADD = "ADD";
    EditText nameTxt, lastNameTxt, phoneTxt, cityTxt;
    String name, lastName, phone, city;
    Button btnSave;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nameTxt = findViewById(R.id.add_name);
        lastNameTxt = findViewById(R.id.add_last_name);
        phoneTxt = findViewById(R.id.add_phone);
        cityTxt = findViewById(R.id.add_city);
        btnSave = findViewById(R.id.add_btn_save);
        btnSave.setOnClickListener(this);
        position = getIntent().getIntExtra(POSITION, -1);
        setValuesToTextFields();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn_save) {
            getTextFromTextFields();
            if (!name.equals("") && !lastName.equals("") && !phone.equals("") && !city.equals("")) {
                Profile profile = new Profile(name, lastName, phone, city);
                if (!storeContainsProfile(profile)) {
                    if (position < 0) {
                        StoreProvider.getInstance().putProfile(profile);

                    } else {
                        StoreProvider.getInstance().putProfile(position, profile);
                    }
                    startActivity(new Intent(this, MyListActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Profile already exists.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "We have empty fields.", Toast.LENGTH_SHORT).show();
            }


        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MyListActivity.class));
        finish();
    }

       private boolean storeContainsProfile(Profile profile) {
        List<Profile> list = StoreProvider.getInstance().getListProfiles();
        for (Profile prof : list) {
            if(prof.name.equals(profile.name) && prof.lastName.equals(profile.lastName)){
               return true;
            }
        }
        return false;
    }

    private void setValuesToTextFields() {
        if (position == -1) {
            nameTxt.setText("");
            lastNameTxt.setText("");
            phoneTxt.setText("");
            cityTxt.setText("");
        } else {
            Profile profile = StoreProvider.getInstance().getProfile(position);
            nameTxt.setText(profile.name);
            lastNameTxt.setText(profile.lastName);
            phoneTxt.setText(profile.phone);
            cityTxt.setText(profile.city);
        }
    }

    public void getTextFromTextFields() {
        name = nameTxt.getText().toString();
        lastName = lastNameTxt.getText().toString();
        phone = phoneTxt.getText().toString();
        city = cityTxt.getText().toString();
    }

}
