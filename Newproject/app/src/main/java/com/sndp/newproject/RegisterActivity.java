package com.sndp.newproject;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, mail, phone, address;
    Button signup,clickme;
    SharedPreferences preferences;
    DatabaseHelper databaseHelper;
    ImageView image;
    int id;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
       databaseHelper = new DatabaseHelper(this);
       id=getIntent().getIntExtra("id",0);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        mail = findViewById(R.id.mail);
        phone = findViewById(R.id.phone);
        signup=findViewById(R.id.signup);
        clickme=findViewById(R.id.clickme);
        image=findViewById(R.id.image);
if (id!=0){
    //UserInfo info = databaseHelper.getUserInfo(id+"");
    UserInfo info =databaseHelper.getUserInfo(String.valueOf(id));
    username.setText(info.username);
    password.setText(info.password);
    address.setText(info.address);
    mail.setText(info.mail);
    phone.setText(info.phone);
    signup.setText("update");

}


        preferences = getSharedPreferences("Userinfo:", 0);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String usernamevalue = username.getText().toString();
                String passwordvalue = password.getText().toString();
                String mailvalue = mail.getText().toString();
                String phonevalue = phone.getText().toString();
                String addressvalue = address.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("username", usernamevalue);
                editor.putString("password", passwordvalue);
                editor.putString("address", addressvalue);
                editor.putString("mail", mailvalue);
                editor.putString("phone", phonevalue);
                editor.apply();


                ContentValues contentValues = new ContentValues();
                contentValues.put("name", usernamevalue);
                contentValues.put("password", passwordvalue);
                contentValues.put("address", addressvalue);
                contentValues.put("mail", mailvalue);
                contentValues.put("phone", phonevalue);
                if (bitmap!=null)
                    contentValues.put("image",getBlob(bitmap));


                if (id == 0) {
                    databaseHelper.insertuser(contentValues);


                    Toast.makeText(RegisterActivity.this, "username:" + usernamevalue + "\naddess:" + addressvalue, Toast.LENGTH_LONG).show();
                } else {
                    databaseHelper.updateuser(id + "", contentValues);
                    Toast.makeText(RegisterActivity.this, "user update", Toast.LENGTH_SHORT);
                    finish();
                }

            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,101);
            }
        });

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==101){
               bitmap=(Bitmap) data.getExtras().get("data");
               image.setImageBitmap(bitmap);
            }
        }
    }

    public static byte[] getBlob(Bitmap bitmap){
    ByteArrayOutputStream bos=new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
    byte[] bArray=bos.toByteArray();
    return bArray;
}
public static Bitmap getBipmap(byte[] byteArray){
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
}
        }
