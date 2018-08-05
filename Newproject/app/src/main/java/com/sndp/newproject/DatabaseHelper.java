package com.sndp.newproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name="MyDATabase";
    static int version=1;
    String Sqlcreateusertable="CREATE TABLE if not exists`User` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`name`\tTEXT,\n" +
            "\t`password`\tTEXT,\n" +
            "\t`address`\tTEXT,\n" +
            "\t`mail`\tTEXT,\n" +
            "\t`phone`\tTEXT,\n" +
            "\t`image`\tBLOB\n" +
            ");";



    public DatabaseHelper(Context context) {
        super(context, name,null, version);
        getWritableDatabase().execSQL(Sqlcreateusertable);
    }
    public void insertuser(ContentValues contentValues){
        getWritableDatabase().insert("User","",contentValues );
    }
    public void updateuser(String id,ContentValues contentValues){
        getWritableDatabase().update("User",contentValues,"id="+id,null);
    }
    public void deleteuser(String id){
        getWritableDatabase().delete("user","id="+id,null);
    }
    public boolean isloginSuccessful (String username,String password){
        String sql ="select count(*) from User where name ='"+username+"'and password='"+password+"'";
        SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
        long l=statement.simpleQueryForLong();
        statement.close();
        if (l==1){
            return true;
        }
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  ArrayList<UserInfo>  getUserList(){
        String sql="select * from User";
        Cursor c =getReadableDatabase().rawQuery(sql,null);
        ArrayList<UserInfo>list=new ArrayList<>();
        while(c.moveToNext()){
            UserInfo info  = new UserInfo();
            info.id=c.getString(c.getColumnIndex("id"));
            info.username=c.getString(c.getColumnIndex("name"));
            info.password=c.getString(c.getColumnIndex("password"));
            info.address=c.getString(c.getColumnIndex("address"));
            info.mail=c.getString(c.getColumnIndex("mail"));
            info.phone=c.getString(c.getColumnIndex("phone"));
            info.image=c.getBlob(c.getColumnIndex("image"));
            list.add(info);

     }

     c.close();
        return list;
}

    public  UserInfo  getUserInfo(String id){
        String sql="select * from User where id="+id;
        Cursor c =getReadableDatabase().rawQuery(sql,null);
        UserInfo info  = new UserInfo();
        while(c.moveToNext()){

            info.id=c.getString(c.getColumnIndex("id"));
            info. username=c.getString(c.getColumnIndex("name"));
            info.password=c.getString(c.getColumnIndex("password"));
            info.address=c.getString(c.getColumnIndex("address"));
            info. mail=c.getString(c.getColumnIndex("mail"));
            info.phone=c.getString(c.getColumnIndex("phone"));
            info.image=c.getBlob(c.getColumnIndex("image"));

        }

        c.close();
        return info;
    }
}
