package com.my.eduardarefjev.aviaapp.UserInformation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	22.12.2017		Eduard Arefjev 		Created viewholder for "ListOfUsers" screen
 */


public class ListOfUsersViewHolder extends ArrayAdapter<User> {

    private ArrayList<User> objects;
    private Activity activity;
    private DatabaseReference mDatabase;

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
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

                Intent intent = new Intent(getContext(), DetailedUserInfo.class);
                intent.putExtra("position", position);
                User i = objects.get(position);
                intent.putExtra("firstname", i.getFirstName());
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
