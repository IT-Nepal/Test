package com.sndp.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username1,password1;
    Button login2,sign2;

    CheckBox rememberme;
    SharedPreferences preferences;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        preferences = getSharedPreferences("Userinfo:", 0);

databaseHelper=new DatabaseHelper(this);
        username1 = findViewById(R.id.username);
        password1 = findViewById(R.id.password);
        login2 = findViewById(R.id.login1);
        sign2 = findViewById(R.id.sign);
        rememberme = findViewById(R.id.remember);



        if (preferences.getBoolean("rememberme:",false)) {
            Intent intent = new Intent(LoginActivity.this, DesignActivity.class);
            startActivity(intent);
            finish();


     }


        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernamevalue = username1.getText().toString();
                String passwordvalue = password1.getText().toString();

                String regisdteredusername = preferences.getString("username", " ");
                String regisdteredpassword = preferences.getString("password", " ");
                if (databaseHelper.isloginSuccessful(usernamevalue,passwordvalue)){
                    Intent intent = new Intent(LoginActivity.this, DesignActivity.class);
                    startActivity(intent);

                    if (rememberme.isChecked()){
                        preferences.edit().putBoolean("rememberme:",true).apply();
                    }

                   finish();
                    Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT);
                }
                else {
                    startActivity(new Intent(LoginActivity.this,UserListActivity.class));
                    Toast.makeText(LoginActivity.this, "login fail", Toast.LENGTH_SHORT);
                }

                //Log.i("check", "username:" + usernamvalue);


            }
        });


        sign2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}