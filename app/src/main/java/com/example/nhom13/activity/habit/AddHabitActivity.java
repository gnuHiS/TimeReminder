package com.example.nhom13.activity.habit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nhom13.R;
import com.example.nhom13.activity.event.AddEventActivity;
import com.example.nhom13.activity.event.EventActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class AddHabitActivity extends AppCompatActivity {
    EditText edtName;
    TextView tvtime;
    TextView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        anhxa();
        tvtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomTime();
            }
        });
    }
    private void anhxa(){
        edtName = findViewById(R.id.habit_name);
        tvtime = findViewById(R.id.habit_time);
        cancel = findViewById(R.id.cancel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddHabitActivity.this, HabitActivity.class);
                startActivity(intent);
                finish();
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
                tvtime.setText(thour +":"+ tminute+" "+am_pm);
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
}