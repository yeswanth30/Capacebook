package com.capacebook.ui.dashboard;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capacebook.DBhandler.DBHandler;
import com.capacebook.Models.register;
import com.capacebook.R;
import com.capacebook.SearchAdapter;
import com.capacebook.databinding.FragmentDashboardBinding;
import com.capacebook.databinding.FragmentHomeBinding;

import java.util.List;

public class DashboardFragment extends Fragment {

        RecyclerView recyclerView1;
        DBHandler dbHandler;
        EditText searchtext;
        Button searchbutton;
        private FragmentHomeBinding binding;

        private LinearLayout contentLayout;

        @SuppressLint("MissingInflatedId")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

            recyclerView1 = view.findViewById(R.id.recyclerView1);
            recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView1.setHasFixedSize(true);


            searchtext = view.findViewById(R.id.searchtext);
            searchbutton = view.findViewById(R.id.searchButton);
            dbHandler = new DBHandler(getContext());

            searchbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                    String userId = sharedPreferences.getString("userid", null);
                    String serachdata = searchtext.getText().toString();
                    List<register> registers = dbHandler.getuserList(serachdata, userId);

                    if (registers.size() > 0){
                        SearchAdapter register1 = new SearchAdapter(registers,getContext());
                        recyclerView1.setAdapter(register1);
                    }else {
                        Toast.makeText(getContext(), "Sorry, no user found", Toast.LENGTH_SHORT).show();
                    }
//                    Toast.makeText(getContext(), "Searching user", Toast.LENGTH_LONG).show();
                }
            });


            return view;
        }
    }