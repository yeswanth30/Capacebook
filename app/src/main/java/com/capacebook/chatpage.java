package com.capacebook;

import android.annotation.SuppressLint;
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
import com.capacebook.Models.chat;
import com.capacebook.Models.register;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class chatpage extends AppCompatActivity {

    RecyclerView recyclerView6;
    DBHandler dbHandler;
    EditText sender;
    EditText messagetext;
    Button sendbutton;
//    String messagetext;
    String reciever;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatpage);

        recyclerView6 = findViewById(R.id.recyclerView6);
        recyclerView6.setLayoutManager(new LinearLayoutManager(this));
        recyclerView6.setHasFixedSize(true);

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userid", null);
        DBHandler dbHandler = new DBHandler(this);
        sender = findViewById(R.id.sender);
        reciever = String.valueOf(findViewById(R.id.reciever));
        sendbutton = findViewById(R.id.sendbutton);
        messagetext = findViewById(R.id.messagetext);

        List<chat> chattingg = dbHandler.getmessagedone(userId);

        if (chattingg.size() > 0){
            PersonalChatAdapter home2 = new PersonalChatAdapter(chattingg, this);
            recyclerView6.setAdapter(home2);
        }else {
            Toast.makeText(chatpage.this, "There is no message here", Toast.LENGTH_SHORT).show();
        }

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long times = System.currentTimeMillis();
                SimpleDateFormat datessssss = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formatssssss = datessssss.format(new Date(times));

                Bundle bundle = getIntent().getExtras();
                reciever = bundle.getString("recieverid");

                dbHandler.messaging(
                        userId,
                        reciever,
                        formatssssss,
                        messagetext.getText().toString());

                Toast.makeText(chatpage.this, "Message sent", Toast.LENGTH_LONG).show();
            }
        });
    }
}

