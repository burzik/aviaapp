package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.11.2017		Eduard Arefjev 		Created to store base methods for creation
 * 	30.12.2017      Eduard Arefjev      Implemented new methods "createRecord" and "updateRecord"
 * 	31.12.2017      Eduard Arefjev      Added new functions
 * 	01.02.2018      Eduard Arefjev      Added new functions makeEditTextReadOnly/makeSpinnerReadOnly
 * 	16.02.2018      Eduard Arefjev      updated calcControl
 */

public class CreationHelper {

    private static DatabaseReference mDatabase;
    private static int counter = 0;
    private static String idd;

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

    static double calcControl(int temp, double nkvd){
        return nkvd*Math.sqrt((273.0d+temp)/288.0d);
    }

    static String createRecord(StepEngineData stepEngineData){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineLaunches");
        DatabaseReference newPostRef = mDatabase.push();
        stepEngineData.setRowId(newPostRef.getKey());
        newPostRef.setValue(stepEngineData);
        return newPostRef.getKey();
    }

    static void updateRecord(final String id, final StepEngineData engineData){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineLaunches").child(id);
        mDatabase.setValue(engineData);
    }

    static void updateEngine(final String id, Object object, String root){
        mDatabase = FirebaseDatabase.getInstance().getReference().child(root).child(id);
        mDatabase.setValue(object);
    }

    public static String updateData(final String node, final int position, final StepEngineData user) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child(node);
        counter = 0;

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (position == counter) {
                    idd = dataSnapshot.getKey();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child(node).child(idd);
                    Map<String, Object> userNicknameUpdates = new HashMap<>();
                    mDatabase.updateChildren(userNicknameUpdates);
                }
                counter++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    return idd;
    }

    public static void makeEditTextReadOnly(EditText editText){
        editText.setCursorVisible(false);
        editText.setLongClickable(false);
        editText.setClickable(false);
        editText.setFocusable(false);
        editText.setSelected(false);
        editText.setKeyListener(null);
        editText.setBackgroundResource(android.R.color.transparent);
    }

    public static void makeSpinnerReadOnly(Spinner spinner){
        spinner.setLongClickable(false);
        spinner.setClickable(false);
        spinner.setFocusable(false);
        spinner.setSelected(false);
        spinner.setBackgroundResource(android.R.color.transparent);
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //view.performClick();
                return true;
            }
        });
    }
}
