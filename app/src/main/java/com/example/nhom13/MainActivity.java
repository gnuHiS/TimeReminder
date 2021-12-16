package com.example.nhom13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nhom13.adapter.EventAdapter;
import com.example.nhom13.database.DBHelper;
import com.example.nhom13.database.EventHelper;
import com.example.nhom13.model.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView btnv;
    ArrayList<Event> arrEvent;
    EventAdapter adapter;
    DBHelper dbHelper;
    EventHelper db;

    ListView lv;

    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        db = new EventHelper(dbHelper);
        navigation();

        arrEvent = new ArrayList<>();
        adapter = new EventAdapter(arrEvent, this);
        String dateCurrent = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        getByDate(dateCurrent);
        lv = findViewById(R.id.lv);


        lv.setAdapter(adapter);
        calendarView = findViewById(R.id.calendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i1++;
                String dateSelect = i2 + "-" + i1 + "-" + i;
                getByDate(dateSelect);
            }
        });

    }

    private void getByDate(String date){
        Cursor events = db.getByDate(date);
        if(events == null){
            Toast.makeText(this, "Không có sự kiện nào", Toast.LENGTH_SHORT).show();
        }else{
            arrEvent.clear();
            while (events.moveToNext()){
                arrEvent.add(new Event(events.getInt(0), events.getString(1), events.getString(2), events.getString(3), events.getString(4), events.getInt(5), events.getInt(6)));
            }
            adapter.notifyDataSetChanged();
        }
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
    }
    private void navigation(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.calender);

        btnv = findViewById(R.id.btnv);
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
                        Intent dashboard = new Intent(MainActivity.this, DashBoardActivity.class);
                        startActivity(dashboard);
                        break;
                    }
                    case R.id.user: {
                        Intent user = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(user);
                        break;
                    }
                }
                return false;
            }
        });
    }


}