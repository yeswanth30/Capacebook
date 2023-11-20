package com.capacebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.capacebook.DBhandler.DBHandler;
import com.capacebook.Models.post;
import com.capacebook.Models.profile;
import com.capacebook.ui.home.HomeFragment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class addpost extends AppCompatActivity {

    EditText caption;
    Button post;
    Button skip;
    Button BSelectPhoto;
    ImageView IVPreviewPhoto;
    private Bitmap Imagetostore1;
    private final int SELECT_PICTURE = 1000;

    private Uri selectedImageUri;
    private AppBarConfiguration appBarConfiguration;

    DBHandler dbhandler;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpost);
        caption = findViewById(R.id.caption);
        post = findViewById(R.id.post);
        BSelectPhoto = findViewById(R.id.BSelectPhoto);
        IVPreviewPhoto = findViewById(R.id.IVPreviewPhoto);
        skip = findViewById(R.id.skip);
        dbhandler = new DBHandler(addpost.this);

        BSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long times = System.currentTimeMillis();
                SimpleDateFormat datesss = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formatsss = datesss.format(new Date(times));

                String imagePath = getRealPathFromURI(selectedImageUri);
                String captions = caption.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                String userId = sharedPreferences.getString("userid", null);
                post postmodeldata = new post(captions);
                dbhandler.addpost(postmodeldata, Imagetostore1, formatsss, userId);
                Toast.makeText(addpost.this, "Post added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(addpost.this, MainActivity.class);
                startActivity(intent);
            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addpost.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                IVPreviewPhoto.setImageURI(data.getData());
                try {
                    Imagetostore1 = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);
                } catch (IOException a) {
                    throw new RuntimeException(a);
                }
            }
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                String filePath = cursor.getString(columnIndex);
                cursor.close();
                return filePath;
            }
            cursor.close();
        }
        return contentUri.getPath();
    }
}