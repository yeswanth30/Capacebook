package com.capacebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.capacebook.DBhandler.DBHandler;
import com.capacebook.Models.profile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class addprofile extends AppCompatActivity {

    TextView bio;
    TextView age;
    Button BSelectImage;
    Button BCoverPhoto;
    ImageView IVPreviewImage;
    RadioGroup gender;
    private Bitmap Imagetostore;
    private final int SELECT_PICTURE = 1000;
    Button submit;
    private Uri selectedImageUri;
    private AppBarConfiguration appBarConfiguration;

    DBHandler dbhandler;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addprofile);
        bio = findViewById(R.id.bio);
        age = findViewById(R.id.age);
        BSelectImage = findViewById(R.id.BSelectImage);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        submit = findViewById(R.id.submit);
        gender = findViewById(R.id.gender);
        int selectedRadioButtonId = gender.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

        if (selectedRadioButton != null) {
            String selectedGender = selectedRadioButton.getText().toString();
        }
        dbhandler = new DBHandler(addprofile.this);

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long times = System.currentTimeMillis();
                SimpleDateFormat datess = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formatss = datess.format(new Date(times));

                String imagePath = getRealPathFromURI(selectedImageUri);
                String bios = bio.getText().toString();
                RadioGroup gender = findViewById(R.id.gender);
                String ages = age.getText().toString();
                String genders = "";

                int selectedRadioButtonId = gender.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    genders = selectedRadioButton.getText().toString();
                } else {
                }

                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                String userId = sharedPreferences.getString("userid", null);
                profile profilemodeldata = new profile( bios, genders, ages);
                dbhandler.addprofile(profilemodeldata, Imagetostore, formatss, userId);
                Toast.makeText(addprofile.this, "Profile added", Toast.LENGTH_LONG).show();

                if (userId != null) {
                    // The "userid" value exists in SharedPreferences, and you can use it as needed
                    // For example, you can log it or display it in your app
                    Log.d("SharedPreference", "Retrieved User ID: " + userId);

                    // Or use it in your application logic
                    // For example, setting it to a TextView:
                //    TextView textView = findViewById(R.id.textView); // Replace with your TextView's ID
                //    textView.setText("User ID: " + userId);
                } else {
                    // The "userid" value was not found in SharedPreferences, handle this case if needed
                    Log.d("SharedPreference", "User ID not found in SharedPreferences");
                    // You can also set a default value or show an error message to the user
                }

                Intent intent = new Intent(addprofile.this, addpost.class);
                startActivity(intent);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                IVPreviewImage.setImageURI(data.getData());
                try {
                    Imagetostore = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);
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
