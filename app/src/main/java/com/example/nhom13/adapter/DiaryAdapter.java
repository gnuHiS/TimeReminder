package com.example.nhom13.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nhom13.R;
import com.example.nhom13.model.Diary;

import java.util.List;

public class DiaryAdapter extends BaseAdapter {

    List<Diary> list;
    Context context;

    public DiaryAdapter(List<Diary> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView == null){
            v = View.inflate(parent.getContext(), R.layout.diary_item,null);
        }
        else{
            v = convertView;
        }

        Diary diary = (Diary) getItem(this.getCount()-position-1);
        ((TextView) v.findViewById(R.id.diary_title)).setText(diary.getTitle());

        ((TextView) v.findViewById(R.id.diary_date)).setText(diary.getDate());
        ((TextView) v.findViewById(R.id.diary_mainContent)).setText(diary.getContentMain());
        ((TextView) v.findViewById(R.id.diary_content)).setText(diary.getContent());
        return v;
    }
}
