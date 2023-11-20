package com.capacebook;

import static android.content.Context.MODE_PRIVATE;

import static com.capacebook.DBhandler.sharedpreference.getSharedPreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
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
import com.capacebook.Models.activity;
import com.capacebook.Models.post;
import com.capacebook.ui.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    List<post> posts;
    Context context;
    Button like;
    EditText comment;
    String comments;
    Button commentbutton;
    ImageView photo;
    DBHandler dbHandler;

    public PostAdapter(List<post> name, Context context) {
        this.context = context;
        this.like = like;
        this.commentbutton = commentbutton;
        this.comment = comment;
        this.photo = photo;
        this.posts = name;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.post_content_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final post postss = posts.get(position);

        holder.text.setText(postss.getText());
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(postss.getPhotovideo(),0,postss.getPhotovideo().length);
        holder.photo.setImageBitmap(imageBitmap);

        holder.commentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long times = System.currentTimeMillis();
                SimpleDateFormat datessss = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formatssss = datessss.format(new Date(times));

                SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
                String userId = sharedPreferences.getString("userid", null);
                String comments = holder.comment.getText().toString();
               long id = dbHandler.addactivity( comments, formatssss, userId, postss.getPostid());
               if(id == 0){
                   Toast.makeText(context, "Comment Already Added", Toast.LENGTH_LONG).show();
               }else{
                   Toast.makeText(context, "Comment added", Toast.LENGTH_LONG).show();
               }

            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
                String userId = sharedPreferences.getString("userid", null);

                // Check if the user has already liked the post
                boolean hasLiked = dbHandler.hasLiked(userId, postss.getPostid());

                if (hasLiked) {
                    // The user has already liked the post
                    Toast.makeText(context, "Already Liked post", Toast.LENGTH_LONG).show();
                } else {
                    // The user hasn't liked the post, so insert the like
                    dbHandler.hasLiked(userId, postss.getPostid());
                    Toast.makeText(context, "Post Liked", Toast.LENGTH_LONG).show();
                }
            }
        });

//
//        holder.like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
//                String userId = sharedPreferences.getString("userid", null);
//                dbHandler.updatelike(likes, postss.getUserid(), postss.getPostid());
//                long id = dbHandler.updatelike(likes, userId, postss.getPostid());
//                if(id == 0){
//                    Toast.makeText(context, "Already Liked post", Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(context, "Post Liked", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {

        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView photo;
        Button like;
        EditText comment;
        Button commentbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            comment = itemView.findViewById(R.id.comment);
            like = itemView.findViewById(R.id.like);
            photo = itemView.findViewById(R.id.photo);
            commentbutton = itemView.findViewById(R.id.commentbutton);
        }
    }
}