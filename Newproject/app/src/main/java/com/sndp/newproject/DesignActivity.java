package com.sndp.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class DesignActivity extends AppCompatActivity {
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_layout);
        preferences = getSharedPreferences("Userinfo:", 0);

       /* findViewById(R.id.popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupmenu(v);
            }
        });
*/

    }

    public void showPopupmenu(View anchor) {

        PopupMenu popupMenu = new PopupMenu(this, anchor);
        getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               startActivity(new Intent(DesignActivity.this,SettingActivity.class ));
                return false;
            }
        });
        popupMenu.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        switch (id){
            case R.id.menu1:
                Toast.makeText(this,"menu1",Toast.LENGTH_SHORT).show();
            break;
            case R.id.menu2:
                Toast.makeText(this,"menu2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(this,"menu3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout1:
                preferences.edit().putBoolean("rememberme:",false).apply();
                Intent intent=new Intent(DesignActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();


                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
