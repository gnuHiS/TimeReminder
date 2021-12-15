package com.example.nhom13.activity.event;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.nhom13.R;
import com.example.nhom13.database.DBHelper;
import com.example.nhom13.database.EventHelper;
import com.example.nhom13.model.Event;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class EditEventActivity extends AppCompatActivity {
    EditText event_name;
    TextView eventDate;
    TextView eventRepeat;
    CardView event_repeat;
    String s = "";
    CardView event_date;
    CardView event_setting;
    CardView event_time;
    TextView cancel;
    TextView eventTime;
    TextView eventSetting;
    TextView save;
    RadioButton event_important;
    long timeRepeat;
    long timeSetting;
    String timeTonotify;
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;
    DBHelper dbHelper;
    EventHelper db;
    int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_add);
        anhxa();
        dbHelper = new DBHelper(this);
        db = new EventHelper(dbHelper);
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("event");
        Event e = (Event) b.getSerializable("event");
        Toast.makeText(this, e.getDate(), Toast.LENGTH_SHORT).show();
        id = e.getId();
        event_name.setText(e.getName());
        eventDate.setText(e.getDate());
        eventTime.setText(e.getTime());
        eventRepeat.setText(e.getRepeat());
        eventSetting.setText(String.valueOf(e.getSetting()));

    }

    private void anhxa(){
        event_name = findViewById(R.id.event_name);
        event_repeat = findViewById(R.id.event_repeat);
        event_date = findViewById(R.id.event_date);
        event_time = findViewById(R.id.event_time);
        event_setting = findViewById(R.id.event_setting);
        eventTime = findViewById(R.id.eventTime);
        eventDate = findViewById(R.id.eventDate);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        eventRepeat = findViewById(R.id.eventRepeat);
        event_important = findViewById(R.id.event_important);
        eventSetting = findViewById(R.id.eventSetting);
    }

    @Override
    protected void onResume() {

        event_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomRepeat();
            }
        });
        event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomDate();
            }
        });
        event_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomTime();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditEventActivity.this, EventActivity.class);
                startActivity(intent);
                finish();
            }
        });

        event_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSetting();
            }
        });
        super.onResume();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = event_name.getText().toString().trim();
                String date = eventDate.getText().toString().trim();
                String time = eventTime.getText().toString().trim();
                String repeat = eventRepeat.getText().toString().trim();

                String setting = String.valueOf(timeSetting);
                if (name.equals("") || date.equals("") || time.equals("") || repeat.equals("") || setting.equals("")){
                    Toast.makeText(EditEventActivity.this, "Vui lòng nhập dữ liệu ", Toast.LENGTH_SHORT).show();
                    return;
                }
                int important = 0;
                boolean isImportant = event_important.isChecked();
                if(isImportant){
                    important = 1;
                }

                int result = db.update(id,name, date, time, repeat, 1, important);
//                if(result==1){
//                    setAlarm(name, date, time);
//                }
                Intent i = getIntent();
                i.putExtra("id",id);
                i.putExtra("name",name);
                i.putExtra("date", date);
                i.putExtra("time", timeTonotify);
                i.putExtra("result", result);
                i.putExtra("timeRepeat", timeRepeat);
                setResult(EventActivity.EDIT_RESULT, i);
                finish();
            }
        });
    }

    private void openBottomSetting() {
        View viewBottomDialog = getLayoutInflater().inflate(R.layout.bottom_event_setting, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewBottomDialog);
        bottomSheetDialog.show();

        final TextView eventRepeat = findViewById(R.id.eventSetting);
        RadioGroup radioRepeat = viewBottomDialog.findViewById(R.id.radio_setting);
        Button acceptBtn = viewBottomDialog.findViewById(R.id.accpect_btn);
        radioRepeat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.level0:{
                        RadioButton r = viewBottomDialog.findViewById(R.id.level0);
                        s = r.getText().toString();
                        break;
                    }
                    case R.id.level1:{
                        RadioButton r = viewBottomDialog.findViewById(R.id.level1);
                        s = r.getText().toString();
                        break;
                    }
                    case R.id.level2:{
                        RadioButton r = viewBottomDialog.findViewById(R.id.level2);
                        s = r.getText().toString();
                        break;
                    }
                    case R.id.level3:{
                        RadioButton r = viewBottomDialog.findViewById(R.id.level3);
                        s = r.getText().toString();
                        break;
                    }
                    case R.id.level4:{
                        RadioButton r = viewBottomDialog.findViewById(R.id.level4);
                        s = r.getText().toString();
                        break;
                    }
                }
            }
        });

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventRepeat.setText(s);
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void openBottomDate() {
        View viewBottomDiaLog = getLayoutInflater().inflate(R.layout.bottom_event_date, null);
        final  BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewBottomDiaLog);
        bottomSheetDialog.show();

        Button acceptBtn = viewBottomDiaLog.findViewById(R.id.accecpt_btn);
        DatePicker datePicker = viewBottomDiaLog.findViewById(R.id.eventDatePicker);
        TextView tv_event_date = viewBottomDiaLog.findViewById(R.id.tv_date_event);

        eventDate = findViewById(R.id.eventDate);



        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int th = datePicker.getFirstDayOfWeek() + 1;
                int day = datePicker.getDayOfMonth();
                int year = datePicker.getYear();
                int month = (datePicker.getMonth() + 1);

                eventDate.setText(day + "-" + month +"-" + year);
                bottomSheetDialog.dismiss();
            }
        });


    }
    private void openBottomTime(){
        View viewBottomDialog = getLayoutInflater().inflate(R.layout.bottom_habit_time, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewBottomDialog);
        bottomSheetDialog.show();

        Button btnCancel = viewBottomDialog.findViewById(R.id.btn_cancel);
        Button acceptBtn = viewBottomDialog.findViewById(R.id.btn_accept);
        TimePicker timePicker = viewBottomDialog.findViewById(R.id.time);
        timePicker.setIs24HourView(true);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour, minute;
                String am_pm;
                String thour = "";
                String tminute = "";
                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                }
                else{
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }
                timeTonotify = hour + ":" + minute;
                if(hour > 12) {
                    am_pm = "PM";
                    hour = hour - 12;
                }
                else
                {
                    am_pm="AM";
                }
                if(hour < 10){
                    thour = "0" + hour;
                }else{
                    thour = "" + hour;
                }
                if(minute < 10){
                    tminute = "0" + minute;
                }else{
                    tminute = "" + minute;
                }

                eventTime.setText(thour +":"+ tminute+" "+am_pm);
                bottomSheetDialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }
    private void openBottomRepeat() {
        View viewBottomDialog = getLayoutInflater().inflate(R.layout.bottom_event_repeat, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewBottomDialog);
        bottomSheetDialog.show();


        RadioGroup radioRepeat = viewBottomDialog.findViewById(R.id.radio_repeat);
        Button acceptBtn = viewBottomDialog.findViewById(R.id.accpect_btn);
        radioRepeat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.level0: {
                        RadioButton r = viewBottomDialog.findViewById(R.id.level0);
                        s = r.getText().toString();
                        timeRepeat = 0;
                        break;
                    }
                    case R.id.level1: {
                        RadioButton r = viewBottomDialog.findViewById(R.id.level1);
                        s = r.getText().toString();
                        timeRepeat = milDay;
                        break;
                    }
                    case R.id.level2: {
                        RadioButton r = viewBottomDialog.findViewById(R.id.level2);
                        s = r.getText().toString();
                        timeRepeat = milWeek;
                        break;
                    }
                    case R.id.level3: {
                        RadioButton r = viewBottomDialog.findViewById(R.id.level3);
                        s = r.getText().toString();
                        timeRepeat = milMonth;
                        break;
                    }
                    case R.id.level4: {
                        RadioButton r = viewBottomDialog.findViewById(R.id.level4);
                        s = r.getText().toString();
                        timeRepeat = milMonth * 12;
                        break;
                    }
                }
            }
        });

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventRepeat.setText(s);
                bottomSheetDialog.dismiss();
            }
        });


    }
    }
