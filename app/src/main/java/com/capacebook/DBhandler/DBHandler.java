package com.capacebook.DBhandler;

import static android.app.ProgressDialog.show;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.capacebook.Models.activity;
import com.capacebook.Models.chat;
import com.capacebook.Models.post;
import com.capacebook.Models.profile;
import com.capacebook.Models.register;
import com.capacebook.login;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "capacebook";
    private static final int DB_VERSION = 3;
    private static final String TABLE_USER = "user";
    private static final String USERID_COL = "userid";
    private static final String NAME_COL = "name";
    private static final String PHONE_COL = "phone";
    private static final String ADDRESS_COL = "address";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";
    private static final String CREATED_AT_COL = "created_at";


    private static final String TABLE_PROFILE = "profile";
    private static final String PROFILE_ID_COL = "profileid";
    private static final String PROFILE_USERID_COL = "userid";
    private static final String BIO_COL = "bio";
    private static final String GENDER_COL = "gender";
    private static final String AGE_COL = "age";
    private static final String PHOTO_COL = "photo";
    private static final String PROFILE_CREATED_AT_COL = "created_at";


    private static final String TABLE_POST = "post";
    private static final String POST_ID_COL = "postid";
    private static final String POST_USERID_COL = "userid";
    private static final String PHOTOVIDEO_COL = "photovideo";
    private static final String TEXT_COL = "text";
    private static final String POST_CREATED_AT_COL = "created_at";


    private static final String TABLE_SESSION = "session";
    private static final String SESSION_USERID_COL = "userid";
    private static final String SESSIONID_COL = "sessionid";
    private static final String TOKEN_COL = "token";
    private static final String TIMEIN_COL = "timein";
    private static final String SESSION_CREATED_AT_COL = "created_at";


    private static final String TABLE_ACTIVITY = "activity";
    private static final String ACTIVITY_ID_COL = "activityid";
    private static final String ACTIVITY_POST_ID_COL = "postid";
    private static final String ACTIVITY_USERID_COL = "userid";
    private static final String LIKE_COL = "like";
    private static final String COMMENT_COL = "comment";
    private static final String ACTIVTY_CREATED_AT_COL = "created_at";


    private static final String TABLE_REQUEST = "request";
    private static final String REQUEST_ID_COL = "requestid";
    private static final String USERID_TO_COL = "useridto";
    private static final String USERID_FROM_COL = "useridfrom";
    private static final String STATUS_COL = "status";
    private static final String REQUEST_CREATED_AT_COL = "created_at";


    private static final String TABLE_CHAT = "chat";
    private static final String CHAT_ID_COL = "chatid";
    private static final String SENDERID_COL = "senderid";
    private static final String RECIEVERID_COL = "recieverid";
    private static final String MESSAGE_COL = "message";
    private static final String TIME_COL = "time";

    byte[] imageInBytes;

    byte[] imageInBytes1;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_USER + " ("
                + USERID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + ADDRESS_COL + " TEXT, "
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT, "
                + CREATED_AT_COL + " TEXT)";
        db.execSQL(query);

        String query1 = "CREATE TABLE " + TABLE_PROFILE + " ("
                + PROFILE_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PROFILE_USERID_COL + " TEXT,"
                + BIO_COL + " TEXT, "
                + GENDER_COL + " TEXT,"
                + AGE_COL + " TEXT,"
                + PHOTO_COL + " TEXT,"
                + PROFILE_CREATED_AT_COL + " TEXT)";
        db.execSQL(query1);

        String query2 = "CREATE TABLE " + TABLE_POST + " ("
                + POST_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + POST_USERID_COL + " TEXT,"
                + PHOTOVIDEO_COL + " TEXT, "
                + TEXT_COL + " TEXT,"
                + POST_CREATED_AT_COL + " TEXT)";
        db.execSQL(query2);


        String query3 = "CREATE TABLE " + TABLE_SESSION + " ("
                + SESSIONID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SESSION_USERID_COL + " TEXT,"
                + TOKEN_COL + " TEXT, "
                + TIMEIN_COL + " TEXT,"
                + SESSION_CREATED_AT_COL + " TEXT)";
        db.execSQL(query3);


        String query4 = "CREATE TABLE " + TABLE_ACTIVITY + " ("
                + ACTIVITY_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACTIVITY_POST_ID_COL + " TEXT,"
                + ACTIVITY_USERID_COL + " TEXT,"
                + LIKE_COL + "TEXT,"
                + COMMENT_COL + " TEXT,"
                + ACTIVTY_CREATED_AT_COL + " TEXT)";
        db.execSQL(query4);


        String query5 = "CREATE TABLE " + TABLE_REQUEST + " ("
                + REQUEST_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERID_TO_COL + " TEXT,"
                + USERID_FROM_COL + " TEXT, "
                + STATUS_COL + " TEXT,"
                + REQUEST_CREATED_AT_COL + " TEXT)";
        db.execSQL(query5);


        String query6 = "CREATE TABLE " + TABLE_CHAT + " ("
                + CHAT_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SENDERID_COL + " TEXT,"
                + RECIEVERID_COL + " TEXT, "
                + MESSAGE_COL + " TEXT,"
                + TIME_COL + " TEXT)";
        db.execSQL(query6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT);
        onCreate(db);
    }

    public void register(String name, String phone, String address, String email, String password, String dates) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(PHONE_COL, phone);
        values.put(ADDRESS_COL, address);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);
        values.put(CREATED_AT_COL, dates);
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public String login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String id = "null";
        String sql = " SELECT * FROM user WHERE email = '" + email + "'  AND password = '" + password + "' ;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            id = cursor.getString(0);
        }
        return id;
    }

    public void addprofile(profile profiles, Bitmap image, String datess, String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        ByteArrayOutputStream objectByteOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 20, objectByteOutputStream);
        imageInBytes = objectByteOutputStream.toByteArray();
        values.put(PHOTO_COL, imageInBytes);
        values.put(BIO_COL, profiles.getBio());
        values.put(PROFILE_USERID_COL, userid);
        values.put(GENDER_COL, profiles.getGender());
        values.put(AGE_COL, profiles.getAge());
        values.put(PROFILE_CREATED_AT_COL, datess);
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }

    public void addpost(post posts, Bitmap image, String datesss, String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        ByteArrayOutputStream objectByteOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 20, objectByteOutputStream);
        imageInBytes1 = objectByteOutputStream.toByteArray();
        values.put(PHOTOVIDEO_COL, imageInBytes1);
        values.put(TEXT_COL, posts.getText());
        values.put(POST_CREATED_AT_COL, datesss);
        values.put(POST_USERID_COL, userid);
        db.insert(TABLE_POST, null, values);
        db.close();
    }

    public List<register> getuserList(String search, String userid) {
        String sql = " select * from " + TABLE_USER + " WHERE name LIKE '%" + search + "%' ; ";
        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
        List<register> storeuser = new ArrayList<>();
        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("userid"));
                String name = cursor.getString(1);
                storeuser.add(new register(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeuser;
    }


    public List<post> getpostList() {
        String sql = "select * from " + TABLE_POST;
        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
        List<post> storepost = new ArrayList<>();
        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("userid"));
                byte[] photovideo = cursor.getBlob(2);
                String text = cursor.getString(3);
                String postid = cursor.getString(cursor.getColumnIndexOrThrow("postid"));
                storepost.add(new post(id, photovideo, text, postid));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storepost;
    }

    public String checkprofile(String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String sql = " SELECT * FROM profile WHERE userid = '" + userid + "' ;";
        Cursor cursor = db.rawQuery(sql, null);
        String id = null;
        if (cursor.moveToFirst()) {
            id = cursor.getString(1);
        }
        return id;
    }

    public long addactivity( String comment, String datessss,String userid, String postid) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_ACTIVITY + " WHERE " + ACTIVITY_USERID_COL + " = '"+userid+"' AND " + ACTIVITY_POST_ID_COL + " = '"+postid+"' ; ";
        Cursor cursor = db.rawQuery(query, null);
        long newcomment = -1;
        if (cursor.moveToFirst()) {
            newcomment = 0;
        } else {
            ContentValues values = new ContentValues();
            values.put(ACTIVITY_USERID_COL, userid);
            values.put(COMMENT_COL, comment);
            values.put(ACTIVTY_CREATED_AT_COL, datessss);
            values.put(ACTIVITY_POST_ID_COL, postid);
            newcomment =  db.insert(TABLE_ACTIVITY, null, values);
            db.close();
        }
        return newcomment;
    }

    public boolean hasLiked(String userId, String postId) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean liked = false;

        String query = "SELECT * FROM " + TABLE_ACTIVITY +
                " WHERE " + ACTIVITY_USERID_COL + " = ? AND " + ACTIVITY_POST_ID_COL + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{userId, postId});

        if (cursor != null) {
            liked = cursor.getCount() > 0;
            cursor.close();
        }

        db.close();
        return liked;
    }
    public void request(String datessss, String useridfrom, String useridto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USERID_FROM_COL, useridfrom);
        values.put(USERID_TO_COL, useridto);
        values.put(REQUEST_CREATED_AT_COL, datessss);
        values.put(STATUS_COL, 0);
        db.insert(TABLE_REQUEST, null, values);
        db.close();
    }

    public List<post> getmypostList(String userid) {
        String sql = " SELECT * FROM post WHERE userid = '" +userid+ "' ;";
        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
        List<post> storemypost = new ArrayList<>();
        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("userid"));
                byte[] photovideo = cursor.getBlob(2);
                String caption = cursor.getString(3);
                Log.e("post ka data", caption);
                storemypost.add(new post(id, photovideo, caption));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storemypost;
    }

    public List<register> getuserchatList(String userid) {
        String sql = " select * from user ;";
        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
        List<register> storeuserchat = new ArrayList<>();
        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("userid"));
                String name = cursor.getString(1);
                storeuserchat.add(new register(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeuserchat;
    }

    public String messaging (String senderid, String recieverid, String time, String message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String id=null;
        values.put(SENDERID_COL, senderid);
        values.put(RECIEVERID_COL, recieverid);
        values.put(TIME_COL, time);
        values.put(MESSAGE_COL, message);
        db.insert(TABLE_CHAT, null, values);
        id = String.valueOf(Math.toIntExact(db.insert(TABLE_USER, null, values)));
        db.close();
        return id;
    }

    public List<chat> getmessagedone (String id) {
       String sql = " SELECT * FROM " + TABLE_CHAT + " WHERE senderid = '" + id + "' ;";
        String sql1 = " SELECT * FROM " + TABLE_CHAT + " WHERE recieverid = '" + id + "' ;";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<chat> storechatting = new ArrayList<>();
      Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        Cursor cursor1 = sqLiteDatabase.rawQuery(sql1, null);
        if (cursor.moveToFirst()) {
            do {
                Integer chatid = cursor.getColumnIndex("message");
                String chatss = cursor.getString(chatid);
                storechatting.add(new chat(chatss));
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (cursor1.moveToFirst()) {
            do {
                Integer nameid1 = cursor1.getColumnIndex("message");
                String chatss = cursor1.getString(nameid1);
                storechatting.add(new chat("received", chatss));
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        return storechatting;
    }
}


//    public void session(String token, String timein, String userid) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(TOKEN_COL, token);
//        values.put(TIMEIN_COL, timein);
//        values.put(SESSION_CREATED_AT_COL, );
//        values.put(SESSION_USERID_COL, userid);
//        db.insert(TABLE_SESSION, null, values);
//        db.close();
//    }

//    public long updatelike(String like, String userid, String postid) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String sql = " SELECT * FROM activity WHERE userid = '" + userid + "'  AND postid = '" + postid + "';";
//        Cursor cursor = db.rawQuery(sql, null);
//        long newlike = -1;
//        if (cursor.moveToFirst()) {
//            newlike = 0;
//        } else {
//            ContentValues values = new ContentValues();
//            values.put(LIKE_COL, 1);
//            newlike =  db.insert(TABLE_ACTIVITY, null, values);
//            db.close();
//        }
//        return newlike;
//    }


