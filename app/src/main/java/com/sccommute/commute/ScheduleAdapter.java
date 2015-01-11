package com.sccommute.commute;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by simba on 1/11/15.
 */
public class ScheduleAdapter extends ArrayAdapter<BusSched>{

    private Context context;

    public ScheduleAdapter(Context context, List<BusSched> objects) {
        super(context, R.layout.sched_item, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.sched_item, null, false);
        TextView tv1 = (TextView) rowView.findViewById(R.id.start_id);
        tv1.setText(getItem(position).getStart());
        TextView tv2 = (TextView) rowView.findViewById(R.id.mid_id);
        tv2.setText(getItem(position).getMid());
        TextView tv3 = (TextView) rowView.findViewById(R.id.end_id);
        tv3.setText(getItem(position).getEnd());
        return rowView;
    }
}
