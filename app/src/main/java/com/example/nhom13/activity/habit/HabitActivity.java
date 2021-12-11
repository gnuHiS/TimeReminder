package com.example.nhom13.activity.habit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.nhom13.DashBoardActivity;
import com.example.nhom13.R;
import com.example.nhom13.UserActivity;
import com.example.nhom13.activity.event.AddEventActivity;
import com.example.nhom13.activity.event.EventActivity;
import com.example.nhom13.adapter.HabitAdapter;
import com.example.nhom13.model.Habit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HabitActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView btnv;
    Button openAdd;
    ListView lv;
    HabitAdapter adapter;
    ArrayList<Habit> arrHabit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        anhxa();
        navigation();
        arrHabit = new ArrayList<>();
        adapter = new HabitAdapter(this, arrHabit);
        lv.setAdapter(adapter);
        openAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(HabitActivity.this, AddHabitActivity.class);
                startActivity(add);
            }
        });
        addHabit();
    }

    private void addHabit(){
        arrHabit.add(new Habit(1,"Ngu day", 2, "6:00"));
        arrHabit.add(new Habit(2,"Tap the duc", 7, "6:30"));
        adapter.notifyDataSetChanged();
    }
    private void anhxa(){
        toolbar = findViewById(R.id.toolbar);
        btnv = findViewById(R.id.btnv);
        openAdd = findViewById(R.id.btn_add);
        lv = findViewById(R.id.lv);
    }

    private void navigation(){
        toolbar.setTitle(R.string.habit);
        btnv.setSelectedItemId(R.id.dashboard);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home:{
                        Intent main = new Intent(HabitActivity.this, EventActivity.class);
                        startActivity(main);
                        break;
                    }
                    case R.id.dashboard:{
                        Intent dashboard = new Intent(HabitActivity.this, DashBoardActivity.class);
                        startActivity(dashboard);
                        break;
                    }
                    case R.id.user: {
                        Intent user = new Intent(HabitActivity.this, UserActivity.class);
                        startActivity(user);
                        break;
                    }
                }
                return false;
            }
        });
    }
}