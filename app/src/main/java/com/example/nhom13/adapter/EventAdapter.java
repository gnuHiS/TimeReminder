package com.example.nhom13.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nhom13.R;
import com.example.nhom13.model.Event;

import java.util.List;

public class EventAdapter extends BaseAdapter {
    List<Event> list;
    Context context;

    public EventAdapter(List<Event> list, Context context) {
        this.list = list;
        this.context = context;
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
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if(view == null){
            v = View.inflate(viewGroup.getContext(), R.layout.event_item, null);
        }else{
            v = view;
        }

        Event event = (Event) getItem(i);
        ((TextView) v.findViewById(R.id.event_title)).setText(event.getName());
        ((TextView) v.findViewById(R.id.event_repeat)).setText("Hàng năm");
        ((TextView) v.findViewById(R.id.event_date_time)).setText(event.getDate() + ", " + event.getTime());
        return  v;
    }
}
