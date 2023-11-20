package com.capacebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.capacebook.DBhandler.DBHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class register extends AppCompatActivity {

    EditText name;
    EditText phone;
    EditText address;
    EditText email;
    EditText password;
    Button signup;
    TextView message;

    private AppBarConfiguration appBarConfiguration;

    DBHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        message = findViewById(R.id.message);
        dbhandler = new DBHandler(register.this);

        EditText name = findViewById(R.id.name);
        EditText phone = findViewById(R.id.phone);

        String namePattern = "^[A-Za-z]+$";
        String phonePattern = "^[0-9]+$";

//        String name = name.getText().toString();
        String phoneNumber = phone.getText().toString();

//        if (!name.matches(namePattern)) {
//            name.setError("Please enter a valid name with only letters.");
//        }

        if (!phoneNumber.matches(phonePattern)) {
            phone.setError("Please enter a valid phone number with only numbers.");
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long times = System.currentTimeMillis();
                SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formats = dates.format(new Date(times));

                dbhandler.register(
                        name.getText().toString(),
                        phone.getText().toString(),
                        address.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        formats
                );
                Toast.makeText(register.this, "data submitted", Toast.LENGTH_LONG).show();
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });
    }

}
