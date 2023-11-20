package com.capacebook;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capacebook.DBhandler.DBHandler;
import com.capacebook.Models.chat;

import java.util.List;

public class PersonalChatAdapter extends RecyclerView.Adapter<PersonalChatAdapter.ViewHolder> {
    List<chat> chatting;
    Context context;
    DBHandler dbHandler;

    public PersonalChatAdapter(List<chat> dochat, Context context) {
        this.context = context;
        this.chatting = dochat;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chatting_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final chat chatter = chatting.get(position);

        holder.sender.setText(chatter.getSenderid());
        holder.reciever.setText(chatter.getRecieverid());

//        holder.sendbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
//                String userId = sharedPreferences.getString("userid", null);
//                String messages = holder.messagetext.getText().toString();
//
//                dbHandler.messaging(chatter.getSenderid(), chatter.getRecieverid(), chatter.getTime(), messages);
//            }
//        });

    }

    @Override
    public int getItemCount() {

        return chatting.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText sender;
        EditText reciever;
        Button sendbutton;
        EditText messagetext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender);
            reciever = itemView.findViewById(R.id.reciever);
            sendbutton = itemView.findViewById(R.id.sendbutton);
            messagetext = itemView.findViewById(R.id.messagetext);
            dbHandler = new DBHandler(context);
        }
    }
}
