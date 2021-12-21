package com.example.nhom13.activity.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.nhom13.R;
import com.example.nhom13.adapter.DiaryAdapter;
import com.example.nhom13.database.DBHelper;
import com.example.nhom13.database.DiaryHelper;
import com.example.nhom13.model.Diary;

import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button openAdd;
    GridView gridView;

    ArrayList<Diary> arrayDiary;
    DiaryAdapter diaryAdapter;

    DiaryHelper diaryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        anhxa();
        toolbar.setTitle("Danh sách nhật ký");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        arrayDiary = new ArrayList<>();
        diaryAdapter = new DiaryAdapter(arrayDiary,this);
        gridView.setAdapter(diaryAdapter);

        diaryHelper = new DiaryHelper(this);
        diaryHelper.CreatTable();

        openAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(DiaryActivity.this,AddDiaryActivity.class);
                startActivityForResult(add,010701);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
        registerForContextMenu(gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DiaryActivity.this,DiaryDetail.class);
                Diary temp = arrayDiary.get(arrayDiary.size()-position-1);
                intent.putExtra("id",temp.getId());
                intent.putExtra("title",temp.getTitle());
                intent.putExtra("date",temp.getDate());
                intent.putExtra("contentMain",temp.getContentMain());
                intent.putExtra("content",temp.getContent());
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }

    private void loadData() {
        Cursor diaries = diaryHelper.getAll();

        arrayDiary.clear();
        while(diaries.moveToNext()){
            arrayDiary.add(new Diary(diaries.getInt(0),
                                     diaries.getString(1),
                                     diaries.getString(2),
                                     diaries.getString(3),
                                     diaries.getString(4)));
        }

        diaryAdapter.notifyDataSetChanged();
    }

    private void anhxa() {
        toolbar = findViewById(R.id.diary_toolbar);
        openAdd = findViewById(R.id.btn_diary_add);
        gridView = findViewById(R.id.diary_gridView);
    }
}