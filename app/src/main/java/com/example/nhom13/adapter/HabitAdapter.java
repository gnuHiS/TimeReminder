package com.example.nhom13.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nhom13.R;
import com.example.nhom13.model.Habit;

import java.util.List;

public class HabitAdapter extends BaseAdapter {
    Context context;
    List<Habit> list;

    public HabitAdapter(Context context, List<Habit> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if(view == null){
            v = View.inflate(viewGroup.getContext(), R.layout.habit_item, null);
        }else{
            v = view;
        }

        Habit habit = (Habit) getItem(i);
        ((TextView) v.findViewById(R.id.habit_title)).setText(habit.getName());
        ((TextView) v.findViewById(R.id.habit_time)).setText(habit.getTime());
//        ((TextView) v.findViewById(R.id.event_date_time)).setText(event.getDate() + ", " + event.getTime());
        return  v;
    }
}
