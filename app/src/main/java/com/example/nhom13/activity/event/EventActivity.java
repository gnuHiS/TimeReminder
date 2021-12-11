package com.example.nhom13.activity.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhom13.DashBoardActivity;
import com.example.nhom13.MainActivity;
import com.example.nhom13.R;
import com.example.nhom13.UserActivity;
import com.example.nhom13.adapter.EventAdapter;
import com.example.nhom13.model.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView btnv;
    Button openAdd;
    ArrayList<Event> arrEvent;
    ListView lv;
    EventAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        anhxa();
        arrEvent = new ArrayList<>();
        adapter = new EventAdapter(arrEvent, this);
        lv.setAdapter(adapter);

        navigation();
        openAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(EventActivity.this, AddEventActivity.class);
                startActivity(add);
            }
        });
        addEvent();
    }

    private void anhxa(){
        toolbar = findViewById(R.id.toolbar);
        btnv = findViewById(R.id.btnv);
        openAdd = findViewById(R.id.btn_add);
        lv = findViewById(R.id.lv);
    }

    private void addEvent(){
        arrEvent.add(new Event(1, "Giáng sinh", "25/12/2021", "00:00", 1, 2, 1));
        arrEvent.add(new Event(2, "Sinh nhật mẹ", "21/10/2021", "00:00", 1, 2, 1));
        adapter.notifyDataSetChanged();
    }
    private void navigation(){
        toolbar.setTitle(R.string.event);
        btnv.setSelectedItemId(R.id.home);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home:{
                        break;
                    }
                    case R.id.dashboard:{
                        Intent dashboard = new Intent(EventActivity.this, DashBoardActivity.class);
                        startActivity(dashboard);
                        break;
                    }
                    case R.id.user: {
                        Intent user = new Intent(EventActivity.this, UserActivity.class);
                        startActivity(user);
                        break;
                    }
                }
                return false;
            }
        });
    }
}
