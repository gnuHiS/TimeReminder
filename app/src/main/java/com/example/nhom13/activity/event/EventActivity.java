package com.example.nhom13.activity.event;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhom13.DashBoardActivity;
import com.example.nhom13.R;
import com.example.nhom13.UserActivity;
import com.example.nhom13.adapter.EventAdapter;
import com.example.nhom13.database.DBHelper;
import com.example.nhom13.database.EventHelper;
import com.example.nhom13.model.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventActivity extends AppCompatActivity {
    public static final int ADD_RESULT = 100;
    public static final int EDIT_RESULT = 200;
    private static final String EVENT_ACTION = "EVENT_ACTION";
    Toolbar toolbar;
    BottomNavigationView btnv;
    Button openAdd;
    ArrayList<Event> arrEvent;
    ListView lv;
    EventAdapter adapter;
    DBHelper dbHelper;
    EventHelper db;
    int position;
    boolean isEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        dbHelper = new DBHelper(this);
        db = new EventHelper(dbHelper);
        anhxa();
        arrEvent = new ArrayList<>();
        adapter = new EventAdapter(arrEvent, this);
        lv.setAdapter(adapter);

        navigation();
        openAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(EventActivity.this, AddEventActivity.class);
                startActivityForResult(add, ADD_RESULT);
            }
        });
        loader();
        registerForContextMenu(lv);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                return false;
            }
        });
    }


    private void anhxa(){
        toolbar = findViewById(R.id.toolbar);
        btnv = findViewById(R.id.btnv);
        openAdd = findViewById(R.id.btn_add);
        lv = findViewById(R.id.lv);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manage, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.detail_btn:{
                Toast.makeText(this, "Xem", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.edit_btn:{
                editEvent();
//                Toast.makeText(this, "Sửa", Toast.LENGTH_SHORT).show();

                break;
            }
            case R.id.delete_btn:{
                deleteEvent();

                break;
            }
        }

        return super.onContextItemSelected(item);
    }


    private void addEvent(){
        arrEvent.add(new Event(1, "Giáng sinh", "25/12/2021", "00:00", "1", 2, 1));
        arrEvent.add(new Event(2, "Sinh nhật mẹ", "21/10/2021", "00:00", "1", 2, 1));
        adapter.notifyDataSetChanged();
    }
    private void editEvent(){
        Event e = arrEvent.get(position);
        Intent editEvent = new Intent(this, EditEventActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("event",e);
        editEvent.putExtra("event", b);
        startActivityForResult(editEvent, EDIT_RESULT);
    }
    private void deleteEvent(){
        Event e = arrEvent.get(position);
        int result = db.delete(e.getId());
        if(result == 1){
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);                   //assigining alaram manager object to set alaram
            Intent intent = new Intent(getApplicationContext(), AlarmEvent.class);
            intent.setAction(EVENT_ACTION);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), e.getId(),
                    intent, PendingIntent.FLAG_ONE_SHOT);
            if(pendingIntent != null) {
                am.cancel(pendingIntent);
            }
            Toast.makeText(this,"Xoá thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        loader();
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
                        startActivityForResult(user, ADD_RESULT);
                        break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == ADD_RESULT){
            int result = data.getIntExtra("result",0);
            String name = data.getStringExtra("name");
            String date = data.getStringExtra("date");
            String time = data.getStringExtra("time");
            Long timeRepeat = data.getLongExtra("timeRepeat",0);
            loader();
            isEdit = false;
            if(result==1){
                Event event = arrEvent.get(arrEvent.size()-1);
                setAlarm(event.getId(), name, date, time, timeRepeat );
                Toast.makeText(this, "Thêm thành công.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Thất bại!!!", Toast.LENGTH_SHORT).show();

            }
        }else if(requestCode == EDIT_RESULT){
            int result = data.getIntExtra("result",0);
            int id = data.getIntExtra("id", -1);
            String name = data.getStringExtra("name");
            String date = data.getStringExtra("date");
            String time = data.getStringExtra("time");
            Long timeRepeat = data.getLongExtra("timeRepeat",0);
            loader();
            isEdit = false;
            if(result==1){
                isEdit = true;
                setAlarm(id, name, date, time, timeRepeat );
                Toast.makeText(this, "Thêm thành công.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Thất bại!!!", Toast.LENGTH_SHORT).show();

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loader(){
        Cursor events = db.getAll();

        arrEvent.clear();
        while(events.moveToNext()){
            arrEvent.add(new Event(events.getInt(0), events.getString(1), events.getString(2), events.getString(3), events.getString(4), events.getInt(5), events.getInt(6)));
        }
        adapter.notifyDataSetChanged();

    }
    private void setAlarm(int id, String name, String date, String time, Long timeRepeat){
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);                   //assigining alaram manager object to set alaram

        Intent intent = new Intent(getApplicationContext(), AlarmEvent.class);
        intent.setAction(EVENT_ACTION);
        intent.putExtra("name", name);                                                       //sending data to alarm class to create channel and notification
        intent.putExtra("time", date);
        intent.putExtra("date", time);

        PendingIntent i = PendingIntent.getBroadcast(getApplicationContext(), id,
                intent, PendingIntent.FLAG_ONE_SHOT);
        if(isEdit){
            am.cancel(i);
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id,
                intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = date + " " + time;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
            if(timeRepeat !=0){
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, date1.getTime(), timeRepeat, pendingIntent);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Intent intentBack = new Intent(getApplicationContext(), EventActivity.class);                //this intent will be called once the setting alaram is completes
        intentBack.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentBack);
    }
}
