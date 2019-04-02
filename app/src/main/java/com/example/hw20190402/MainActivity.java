package com.example.hw20190402;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailTxt, passwordTxt;
    Button btnLogin;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StoreProvider.getInstance().setContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailTxt = findViewById(R.id.main_email);
        passwordTxt = findViewById(R.id.main_password);
        btnLogin = findViewById(R.id.main_login);
        btnLogin.setOnClickListener(this);

        if (!StoreProvider.getInstance().getToken().equals("")) {
            goToNextActivity();
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_login) {
            email = emailTxt.getText().toString();
            password = passwordTxt.getText().toString();
            if (!email.equals("") && !password.equals("")) {
                StoreProvider.getInstance().login(email, password);
                goToNextActivity();
            } else {
                Toast.makeText(this, "We have empty fields.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void goToNextActivity() {
        Intent intent = new Intent(this, MyListActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_CANCELED) {
            finish();
        }
    }
}
