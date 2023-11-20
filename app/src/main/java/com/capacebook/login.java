package com.capacebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.capacebook.DBhandler.DBHandler;

public class login extends AppCompatActivity {

    EditText email;
    EditText password;
    String email1;
    String password1;
    Button login1;
    TextView register1;
    String userid1;

    DBHandler dbHandler;

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login1 = findViewById(R.id.login1);
        register1 = findViewById(R.id.register1);
        dbHandler = new DBHandler(login.this);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email1 = email.getText().toString();
                password1 = password.getText().toString();

                if (email1.isEmpty() || password1.isEmpty()) {
                    Toast.makeText(login.this, "Please Enter both email and password", Toast.LENGTH_LONG).show();
                } else {
                    String data = dbHandler.login(email1, password1);

                    if (data != null && !data.isEmpty()) {
                        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                        SharedPreferences.Editor myedit = sharedPreferences.edit();
                        myedit.putString("userid", data);
                        myedit.apply();
                       String profile = dbHandler.checkprofile(data);
                        if (profile != null && !profile.isEmpty()) {

                            Intent intent = new Intent(login.this, addpost.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(login.this, addprofile.class);
                            startActivity(intent);
                        }
                        Toast.makeText(login.this, "Successfully logged in", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(login.this, "Email not found or password is incorrect. Please sign up or try again.", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }
}
