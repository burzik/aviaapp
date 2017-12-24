package com.my.eduardarefjev.aviaapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by EduardArefjev on 22/12/2017.
 */

public class ListOfUsersViewHolder extends ArrayAdapter<User> {

    private ArrayList<User> objects;
    private Activity activity;

    private class ViewHolder{
        TextView firstName;
        TextView lastName;
    }

    //ListOfUsersViewHolder(Activity activity){
        //super(activity, R.layout.activity_main, ListOfUsers.values());
        //this.activity = activity;
    //}
    public ListOfUsersViewHolder(Context context, int textViewResourceId, ArrayList<User> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    /*ListOfUsersViewHolder(Activity activity) {
        super(activity, R.layout.activity_main, ListOfUsers.values());
        this.activity = activity;
    }*/

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_main, null);

            User i = objects.get(position);

            if (i != null) {
                TextView tt = (TextView) v.findViewById(R.id.activityMainId);
                TextView tt2 = (TextView) v.findViewById(R.id.LastName);

                if (tt != null){
                    tt.setText("Имя: " + i.getFirstName());
                }
                if (tt != null){
                    tt2.setText("Фамилия: " + i.getLastName());
                }

            }
        }

        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(activity, DetailedUserInfo.class);
                //intent.putExtra("position", position);
                //activity.startActivity(intent);
                Intent intent = new Intent(getContext(), DetailedUserInfo.class);
                intent.putExtra("position", position);
                User i = objects.get(position);
                intent.putExtra("firstname", i.getFirstName());
                getContext().startActivity(intent);
            }
        });

        return v;
    }
}
