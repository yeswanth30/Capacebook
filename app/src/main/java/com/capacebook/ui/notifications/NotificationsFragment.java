package com.capacebook.ui.notifications;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capacebook.DBhandler.DBHandler;
import com.capacebook.Models.post;
import com.capacebook.MyPostsAdapter;
import com.capacebook.PostAdapter;
import com.capacebook.R;
import com.capacebook.databinding.FragmentHomeBinding;
import com.capacebook.databinding.FragmentNotificationsBinding;
import com.capacebook.login;

import java.util.List;

public class NotificationsFragment extends Fragment {

    RecyclerView recyclerView4;
    DBHandler dbHandler;
    ImageView profilephoto;
    TextView profilename;
    TextView profilebio;
    ImageView postphoto;
    ImageView postcaption;
    Button logout;
    private FragmentHomeBinding binding;

    private LinearLayout contentLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView4 = view.findViewById(R.id.recyclerView4);
        recyclerView4.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView4.setHasFixedSize(true);

        DBHandler dbHandler = new DBHandler(getContext());
        profilephoto = view.findViewById(R.id.profilephoto);
        profilename = view.findViewById(R.id.profilename);
        profilebio = view.findViewById(R.id.profilebio);
        logout = view.findViewById(R.id.logout);
        postcaption = view.findViewById(R.id.postcaption);
        postphoto = view.findViewById(R.id.postphoto);
        dbHandler = new DBHandler(getContext());

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userid", null);
        List<post> mypost = dbHandler.getmypostList(userId);

        if (mypost.size() > 0){
            MyPostsAdapter mypost1 = new MyPostsAdapter(mypost,getContext());
            recyclerView4.setAdapter(mypost1);
        }else {
            Toast.makeText(getContext(), "There is no post yet", Toast.LENGTH_SHORT).show();
        }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getContext().getSharedPreferences("my preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getContext(), login.class);
                startActivity(intent);
                //Toast.makeText(getContext(), "Welcome to Cart Page", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}