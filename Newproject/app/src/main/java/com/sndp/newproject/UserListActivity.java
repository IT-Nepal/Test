package com.sndp.newproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
LinearLayout container;
DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
         databaseHelper=new DatabaseHelper(this);
        container=findViewById(R.id.container);


    }

    @Override
    protected void onResume() {
        super.onResume();
         displayuser();

        }

    public void displayuser(){
    ArrayList<UserInfo>list=databaseHelper.getUserList();
    container.removeAllViews();
//    for (int i=0;i<list.size();i++) {
//        UserInfo info = list.get(i);
//    }
    for (final UserInfo info:list ) {
        TextView textView=new TextView(this);
        textView.setText(info.username+"\naddress:"+info.address);

        View view= LayoutInflater.from(this).inflate(R.layout.item_layout,null);
        TextView username=view.findViewById(R.id.name),
                address=view.findViewById(R.id.address);
        username.setText(info.username);
        address.setText(info.address);

        ImageView imageView=view.findViewById(R.id.image);
        if(info.image!=null)
        imageView.setImageBitmap(RegisterActivity.getBipmap(info.image));
view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(UserListActivity.this,DetailActivity.class);
        intent.putExtra("id",info.id);
            startActivity(intent);
    }
});
        container.addView(view);


    }
}



}

