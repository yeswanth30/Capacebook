package com.capacebook;

import static android.content.Context.MODE_PRIVATE;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capacebook.DBhandler.DBHandler;
import com.capacebook.Models.post;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.capacebook.Models.register;

public class UserChatAdapter extends RecyclerView.Adapter<UserChatAdapter.ViewHolder> {
    List<register> registerss;
    Context context;
    DBHandler dbHandler;

    public UserChatAdapter(List<register> registerchat, Context context) {
        this.context = context;
        this.registerss = registerchat;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chat_names_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final register registersss = registerss.get(position);

        holder.username1.setText(registersss.getName());

        holder.chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
                String userId = sharedPreferences.getString("userid", null);
                String names = holder.username1.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("userId", userId);
                bundle.putString("userName", names);
                bundle.putString("registerName", registersss.getName());
                bundle.putString("recieverid", registersss.getUserid());

                Intent intent = new Intent(context, chatpage.class);

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return registerss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username1;
        Button chat1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username1 = itemView.findViewById(R.id.username1);
            chat1 = itemView.findViewById(R.id.chat1);
            dbHandler = new DBHandler(context);
        }
    }
}
