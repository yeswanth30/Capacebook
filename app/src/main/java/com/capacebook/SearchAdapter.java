package com.capacebook;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capacebook.DBhandler.DBHandler;
import com.capacebook.Models.register;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    List<register> registers;
    Context context;
    Button searchButton;
    Button addfriend;
    DBHandler dbHandler;

    public SearchAdapter(List<register> name, Context context) {
        this.context = context;
        this.searchButton = searchButton;
        this.registers = name;
        this.addfriend = addfriend;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_user_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final register registerss = registers.get(position);

        holder.username.setText(registerss.getName());

        holder.addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long times = System.currentTimeMillis();
                SimpleDateFormat datesssss = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formatsssss = datesssss.format(new Date(times));

                SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
                String userId = sharedPreferences.getString("userid", null);
                dbHandler.request(formatsssss, userId, registerss.getUserid() );
                Toast.makeText(context, "Friend Request Sent", Toast.LENGTH_LONG).show();
            }
        });
    };

    @Override
    public int getItemCount() {

        return registers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        Button searchButton;
        Button addfriend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            searchButton = itemView.findViewById(R.id.searchButton);
            addfriend = itemView.findViewById(R.id.addfriend);
        }
    }
}