package com.my.eduardarefjev.aviaapp.UserInformation;

import android.app.Activity;
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
 * 	22.12.2017		Eduard Arefjev 		Created viewholder for "ListOfUsers" screen
 * 	20.01.2017      Eduard Arefjev      Refactored ViewHolder to recycle view holde
 */


public class ListOfUsersViewHolder extends ArrayAdapter<User> {

    private ArrayList<User> objects;
    private Activity activity;

    private class ViewHolder{
        TextView firstName;
        TextView lastName;
    }

    ListOfUsersViewHolder(Activity activity, int textViewResourceId, ArrayList<User> objects) {
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
            convertView = inflater.inflate(R.layout.activity_main, nullParent);

            viewHolder.firstName = (TextView) convertView.findViewById(R.id.activityMainId);
            viewHolder.lastName = (TextView) convertView.findViewById(R.id.LastName);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        User user = objects.get(position);

        viewHolder.firstName.setText(user.getFirstName());
        viewHolder.lastName.setText(user.getLastName());

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailedUserInfo.class);
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
