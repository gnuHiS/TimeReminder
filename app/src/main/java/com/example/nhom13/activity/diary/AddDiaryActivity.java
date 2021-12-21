package com.example.nhom13.activity.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom13.R;
import com.example.nhom13.database.DiaryHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class AddDiaryActivity extends AppCompatActivity {

    TextView cancel,save;

    EditText editTextTitle;
    TextView textViewDate;
    EditText editTextContentMain,editTextContent;
    ImageView imageView;
    Button btnAddImage;

    DiaryHelper diaryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_add);
        diaryHelper = new DiaryHelper(this);

        anhxa();

        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewBottomDiaLog = getLayoutInflater().inflate(R.layout.bottom_event_date, null);
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AddDiaryActivity.this);
                bottomSheetDialog.setContentView(viewBottomDiaLog);
                bottomSheetDialog.show();

                Button acceptBtn = viewBottomDiaLog.findViewById(R.id.accecpt_btn);
                DatePicker datePicker = viewBottomDiaLog.findViewById(R.id.eventDatePicker);
                TextView tv_event_date = viewBottomDiaLog.findViewById(R.id.tv_date_event);



                acceptBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int th = datePicker.getFirstDayOfWeek() + 1;
                        int day = datePicker.getDayOfMonth();
                        int year = datePicker.getYear();
                        int month = (datePicker.getMonth() + 1);

                        textViewDate.setText(day + "-" + month +"-" + year);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePressed();
            }
        });

    }

    private void SavePressed() {
        if(editTextTitle.getText().toString().isEmpty() || editTextContentMain.getText().toString().isEmpty()){
            Toast.makeText(this, "Khong duoc de trong", Toast.LENGTH_LONG).show();
        }
        else{
            boolean done = diaryHelper.Insert(editTextTitle.getText().toString(),textViewDate.getText().toString(),editTextContentMain.getText().toString(),editTextContent.getText().toString());
            if(done){
                Toast.makeText(this,"Them thanh cong",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
            else{
                Toast.makeText(this,"That bai",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void anhxa() {
        cancel = findViewById(R.id.diary_add_cancel);
        save = findViewById(R.id.diary_add_save);

        editTextTitle = findViewById(R.id.diary_add_title_EditText);
        textViewDate = findViewById(R.id.diary_add_date_TV);
        editTextContentMain = findViewById(R.id.diary_add_mainContent_EditText);
        editTextContent = findViewById(R.id.diary_add_content_EditText);
        imageView = findViewById(R.id.diary_add_image);
        btnAddImage = findViewById(R.id.diary_add_button_addImage);
    }
}