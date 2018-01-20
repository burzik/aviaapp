package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	30.12.2017		Eduard Arefjev 		Created "RecordsViewHolder" screen
 * 	07.01.2017      Eduard Arefjev      Refactored ViewHolder to recycle view holder
 */


public class RecordsViewHolder extends ArrayAdapter<StepEngineData> {
    private ArrayList<StepEngineData> objects;
    private Activity activity;

    class ViewHolder {
        TextView date;
        TextView engineId;
    }

    RecordsViewHolder(Activity activity, int textViewResourceId,ArrayList<StepEngineData> objects) {
        super(activity, textViewResourceId, objects);
        this.activity = activity;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent){
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = activity.getLayoutInflater();
            final ViewGroup nullParent = null;
            convertView = inflater.inflate(R.layout.listview_records, nullParent);

            viewHolder.date = (TextView) convertView.findViewById(R.id.firstListItem);
            viewHolder.engineId = (TextView) convertView.findViewById(R.id.secondListItem);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        StepEngineData engineData = objects.get(position);

        viewHolder.date.setText(DateFormat.format("dd.MM.yyyy", engineData.getLaunchDate()).toString());
        viewHolder.engineId.setText(String.format(activity.getResources().getConfiguration().locale,"%d",engineData.getEngineId()));

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailedRecordInfo.class);
                Bundle extra = new Bundle();
                extra.putSerializable("objects", objects);
                intent.putExtra("position", position);
                intent.putExtra("extra", extra);
                activity.startActivity(intent);
            }
        });

        return convertView;
    }
}
