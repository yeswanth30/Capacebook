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
import com.capacebook.Models.post;
import com.capacebook.Models.register;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.ViewHolder> {
    List<post> mypost;
    Context context;
    DBHandler dbHandler;

    public MyPostsAdapter(List<post> mypost, Context context) {
        this.context = context;
        this.mypost = mypost;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.mypost_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final post myposts = mypost.get(position);
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(myposts.getPhotovideo(),0,myposts.getText().length());
        holder.postphoto.setImageBitmap(imageBitmap);
        holder.postcaption.setText(myposts.getText());
    };

    @Override
    public int getItemCount() {

        return mypost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView postcaption;
        ImageView postphoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postcaption = itemView.findViewById(R.id.postcaption);
            postphoto = itemView.findViewById(R.id.postphoto);
        }
    }
}