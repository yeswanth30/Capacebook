package com.capacebook;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capacebook.DBhandler.DBHandler;
import com.capacebook.Models.post;
import com.capacebook.Models.register;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class messagepage extends AppCompatActivity {

    RecyclerView recyclerView5;
    DBHandler dbHandler;
    EditText senderid;
    EditText recieverid;
    EditText message;
    TextView username1;
    Button chat1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagepage);

        recyclerView5 = findViewById(R.id.recyclerView5);
        recyclerView5.setLayoutManager(new LinearLayoutManager(this));
        recyclerView5.setHasFixedSize(true);

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userid", null);
        DBHandler dbHandler = new DBHandler(this);
        username1 = findViewById(R.id.username1);
        chat1 = findViewById(R.id.chat1);

        List<register> registors = dbHandler.getuserchatList(userId);

        if (registors.size() > 0){
            UserChatAdapter home1 = new UserChatAdapter(registors, this);
            recyclerView5.setAdapter(home1);
        }else {
            Toast.makeText(messagepage.this, "There is no chat yet", Toast.LENGTH_SHORT).show();
        }
//
//        chat1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(messagepage.this, chatpage.class);
//                startActivity(intent);
//            }
//        });
    }
}

