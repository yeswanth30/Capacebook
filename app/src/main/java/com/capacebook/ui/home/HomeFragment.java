package com.capacebook.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.capacebook.PostAdapter;
import com.capacebook.R;
import com.capacebook.databinding.FragmentHomeBinding;
import com.capacebook.login;
import com.capacebook.messagepage;
import com.capacebook.register;

import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    DBHandler dbHandler;
    TextView name;
    ImageView chat;
    ImageView photo;
    TextView text;
    Button like;
    Button commentbutton;
    EditText comment;
    private FragmentHomeBinding binding;

    private LinearLayout contentLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        contentLayout = view.findViewById(R.id.layout1);

        DBHandler dbHandler = new DBHandler(getContext());
        name = view.findViewById(R.id.name);
        photo = view.findViewById(R.id.photo);
        text = view.findViewById(R.id.text);
        like = view.findViewById(R.id.like);
        chat = view.findViewById(R.id.chat);
        commentbutton = view.findViewById(R.id.commentbutton);
        comment = view.findViewById(R.id.comment);
        dbHandler = new DBHandler(getContext());

        List<post> postt = dbHandler.getpostList();

        if (postt.size() > 0){
            PostAdapter home1 = new PostAdapter(postt,getContext());
            recyclerView.setAdapter(home1);
        }else {
            Toast.makeText(getContext(), "There is no post yet", Toast.LENGTH_SHORT).show();
        }

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), messagepage.class);
                startActivity(intent);
            }
            });
        return view;
        }
    }