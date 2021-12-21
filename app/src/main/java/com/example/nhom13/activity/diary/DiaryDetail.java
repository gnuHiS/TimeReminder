package com.example.nhom13.activity.diary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom13.R;
import com.example.nhom13.database.DiaryHelper;
import com.example.nhom13.model.Diary;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DiaryDetail extends AppCompatActivity {
    Toolbar toolbar;

    AlertDialog dialog;
    AlertDialog.Builder builder;

    EditText editTextTitle;
    TextView textViewDate;
    EditText editTextContentMain,editTextContent;
    ImageView imageView;
    ImageButton btnUpdate,btnDelete;

    Diary diary;
    DiaryHelper diaryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_detail);
        diaryHelper = new DiaryHelper(this);

        anhxa();

        toolbar.setTitle("Chi tiet nhật ký");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewBottomDiaLog = getLayoutInflater().inflate(R.layout.bottom_event_date, null);
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DiaryDetail.this);
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
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePressed();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletePressed();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            diary = new Diary(bundle.getInt("id"),bundle.getString("title"),bundle.getString("date"),bundle.getString("contentMain"),bundle.getString("content"));
            editTextTitle.setText(bundle.getString("title"));
            textViewDate.setText(bundle.getString("date"));
            editTextContentMain.setText(bundle.getString("contentMain"));
            editTextContent.setText(bundle.getString("content"));
        }
    }

    private void UpdatePressed(){
        if(editTextTitle.getText().toString().isEmpty() || editTextContentMain.getText().toString().isEmpty()){
            Toast.makeText(this, "Khong duoc de trong", Toast.LENGTH_LONG).show();
        }
        else{
            boolean done = diaryHelper.Update(diary.getId(),editTextTitle.getText().toString(),textViewDate.getText().toString(),editTextContentMain.getText().toString(),editTextContent.getText().toString());
            if(done){
                Toast.makeText(this,"Update thanh cong",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
            else{
                Toast.makeText(this,"That bai",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void DeletePressed(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Are You Sure ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                diaryHelper.Delete(diary.getId());
                onBackPressed();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private void anhxa() {
        toolbar = findViewById(R.id.diary_view_toolbar);

        editTextTitle = findViewById(R.id.diary_detail_title_EditText);
        textViewDate = findViewById(R.id.diary_detail_date_TV);
        editTextContentMain = findViewById(R.id.diary_detail_mainContent_EditText);
        editTextContent = findViewById(R.id.diary_detail_content_EditText);
        imageView = findViewById(R.id.diary_detail_image);

        btnUpdate = findViewById(R.id.diary_detail_edit);
        btnDelete = findViewById(R.id.diary_detail_delete);
    }
}