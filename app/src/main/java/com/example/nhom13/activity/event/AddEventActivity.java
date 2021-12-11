package com.example.nhom13.activity.event;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.nhom13.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class AddEventActivity extends AppCompatActivity {
    TextView eventDate;
    CardView event_repeat;
    String s = "";
    CardView event_date;
    CardView event_setting;
    CardView event_time;
    TextView eventTime;
    TextView cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_add);
        anhxa();


    }

    private void anhxa(){
        event_repeat = findViewById(R.id.event_repeat);
        event_date = findViewById(R.id.event_date);
        event_time = findViewById(R.id.event_time);
        event_setting = findViewById(R.id.event_setting);
        eventTime = findViewById(R.id.eventTime);
        cancel = findViewById(R.id.cancel);
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
                Intent intent = new Intent(AddEventActivity.this, EventActivity.class);
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

                eventDate.setText(day + "/" + month +"/" + year);
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

        final TextView eventRepeat = findViewById(R.id.eventRepeat);
        RadioGroup radioRepeat = viewBottomDialog.findViewById(R.id.radio_repeat);
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

}
