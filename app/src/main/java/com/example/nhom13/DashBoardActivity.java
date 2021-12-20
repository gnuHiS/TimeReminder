package com.example.nhom13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nhom13.activity.countdown.CountDownActivity;
import com.example.nhom13.activity.event.EventActivity;
import com.example.nhom13.activity.habit.HabitActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoardActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView btnv;
    Button eventOpen;
    Button habitOpen;
    Button memoryOpen;
    Button countdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        anhxa();
        navigation();
        itemNavigation();
    }

    private void anhxa(){
        toolbar = findViewById(R.id.toolbar);
        btnv = findViewById(R.id.btnv);
        eventOpen = findViewById(R.id.event);
        habitOpen = findViewById(R.id.habit);
        memoryOpen = findViewById(R.id.memory);
        countdown = findViewById(R.id.countdown);
    }
    private void itemNavigation(){
        eventOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent event = new Intent(DashBoardActivity.this, EventActivity.class);
                startActivity(event);
            }
        });

        habitOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent habit = new Intent(DashBoardActivity.this, HabitActivity.class);
                startActivity(habit);
            }
        });
        countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoardActivity.this, CountDownActivity.class);
                startActivity(i);
            }
        });
    }

    private void navigation(){
        toolbar.setTitle(R.string.dashboard);
        btnv.setSelectedItemId(R.id.dashboard);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home:{
                        Intent main = new Intent(DashBoardActivity.this, MainActivity.class);
                        startActivity(main);
                        break;
                    }
                    case R.id.dashboard:{
                        break;
                    }
                    case R.id.user: {
                        Intent user = new Intent(DashBoardActivity.this, UserActivity.class);
                        startActivity(user);
                        break;
                    }
                }
                return false;
            }
        });
    }
}