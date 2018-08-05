package com.sndp.newproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
String id;
TextView username,password,address,mail,phone;
DatabaseHelper databaseHelper;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id=getIntent().getStringExtra("id");
        databaseHelper=new DatabaseHelper(this);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        address=findViewById(R.id.address);
        mail=findViewById(R.id.mail);
        phone=findViewById(R.id.phone);
        imageView=findViewById(R.id.image);

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this,RegisterActivity.class);
                intent.putExtra("id",Integer.parseInt(id));
                startActivity(intent);
            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
showAlertDialog();
            }
        });
    }


    public void showAlertDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Delete user!");
        dialog.setMessage("are you sure to delete?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteuser(id);
                finish();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }

    public void showData(){
        UserInfo info = databaseHelper.getUserInfo(id);
        username.setText(info.username);
        password.setText(info.password);
        address.setText(info.address);
        mail.setText(info.mail);
        phone.setText(info.phone);
        if (info.image!=null)
        imageView.setImageBitmap(RegisterActivity.getBipmap(info.image));
    }
}
