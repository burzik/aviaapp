package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
 */


public class RecordsViewHolder extends ArrayAdapter<StepEngineData> {
    private ArrayList<StepEngineData> objects;
    //private DatabaseReference mDatabase;

    RecordsViewHolder(Context context, int textViewResourceId, ArrayList<StepEngineData> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent){

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            v = inflater.inflate(R.layout.activity_main, null);

            StepEngineData i = objects.get(position);

            if (i != null) {
                TextView tt = (TextView) v.findViewById(R.id.activityMainId);
                TextView tt2 = (TextView) v.findViewById(R.id.LastName);

                if (tt != null){
                    tt.setText("Имя: " + i.getEngineId());
                }
                if (tt != null){
                    tt2.setText("Фамилия: " + i.getLaunchDate());
                }

            }
        }

        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(activity, DetailedUserInfo.class);
                //intent.putExtra("position", position);
                //activity.startActivity(intent);
                //mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineLaunches");

                Intent intent = new Intent(getContext(), DetailedRecordInformation.class);
                intent.putExtra("position", position);
                StepEngineData i = objects.get(position);
                //intent.putExtra("firstname", i.getFirstName());
                intent.putExtra("obj", objects);

                Bundle extra = new Bundle();
                extra.putSerializable("objects", objects);

                //Intent intent = new Intent(getBaseContext(), ShowSpread.class);
                intent.putExtra("extra", extra);

                //intent.putExtra("qwe",objects);
                getContext().startActivity(intent);
            }
        });

        return v;
    }
}
