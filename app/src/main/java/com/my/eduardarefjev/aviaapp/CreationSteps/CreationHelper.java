package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.eduardarefjev.aviaapp.UserInformation.User;
import com.my.eduardarefjev.aviaapp.UserInformation.UserCreation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.11.2017		Eduard Arefjev 		Created to store base methods for creation
 * 	30.12.2017      Eduard Arefjev      Implemented new methods "createRecord" and "updateRecord"
 */

public class CreationHelper {

    private static DatabaseReference mDatabase;

    public static void checkValue(final EditText value, final double left, final double right){
        value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if (value.getText().toString().length() != 0 ) {
                    double dValue = Double.parseDouble(value.getText().toString());
                    double leftVal = left;

                    if (dValue < left) {
                        value.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.DARKEN);
                        if (left == -Double.MIN_VALUE){
                            leftVal = 0;
                        }
                        if (right == Double.MAX_VALUE){
                            value.setError("критичное значение (" + leftVal + "- ∞)");
                        } else {
                        value.setError("критичное значение (" + leftVal + "-" + right + ")");
                        }
                    }
                    else if (dValue > right) {
                        if (left == -Double.MIN_VALUE){
                            leftVal = 0;
                        }
                        value.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.DARKEN);
                            if (right == Double.MAX_VALUE){
                            value.setError("критичное значение (" + leftVal + "- ∞)");
                            } else {
                            value.setError("критичное значение (" + leftVal + "-" + right + ")");
                            }
                        //value.setError("критичное значение (" + left + "-" + right + ")");
                    } else {
                        value.getBackground().setColorFilter(Color.parseColor("#9b9b9b"), PorterDuff.Mode.SRC_ATOP);
                    }
                }
            }
        });
    }

    public static double calcBaseSetup(double temp, double presure, int mode){
        double result;
        switch (mode) {
            case 1:
                result = 1;
                break;
            case 2:
                result = 2;
                break;
            case 3:
                result = 3;
                break;
            default: result = 4;
        }
        return result;
    }

    public static double calcControl(double temp, double nkvd){
        double result;
        result = nkvd*Math.sqrt((273+temp)/288);
        return result;
    }

    static String createRecord(StepEngineData stepEngineData){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineLaunches");
        DatabaseReference newPostRef = mDatabase.push();
        newPostRef.setValue(stepEngineData);
        return newPostRef.getKey();
    }

    static void updateRecord(final String id, final StepEngineData user){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineLaunches").child(id);
        mDatabase.setValue(user);
    }
}
