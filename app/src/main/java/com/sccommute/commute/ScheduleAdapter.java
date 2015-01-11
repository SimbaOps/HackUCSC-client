package com.sccommute.commute;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by simba on 1/11/15.
 */
public class ScheduleAdapter extends ArrayAdapter<String>{

    private Context context;

    public ScheduleAdapter(Context context, List<String> objects) {
        super(context, R.layout.sched_item, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.sched_item, null, false);
        return rowView;
    }
}
