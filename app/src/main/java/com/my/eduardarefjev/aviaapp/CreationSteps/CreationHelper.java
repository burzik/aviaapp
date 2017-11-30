package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;

/**
 * Created by EduardArefjev on 29/11/2017.
 */

public class CreationHelper {

    public static void checkValue(final EditText value, final float left, final float right){
        //TODO calculate range
        float range = 0;
        double leftRange = 0;
        double rightRange = 0;
        double c = 0;
        range = right - left;

        if(left == 0){

        }
        if(right == 0){
            range = Float.intBitsToFloat(0x7f7fffff);
        }

        if (range != Float.intBitsToFloat(0x7f7fffff)){
            c = range * 0.05;
            leftRange = left+c;
            rightRange = right-c;
        } else {
            leftRange = left;
            rightRange = Float.intBitsToFloat(0x7f7fffff);
        }

        //if( value.getText().toString().length() != 0 ) {
            //final double dValue = Double.parseDouble(value.getText().toString());
        //}

        final double finalLeftRange = leftRange;
        final double finalRightRange = rightRange;
        value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if( value.getText().toString().length() != 0 ) {

                    double dValue = Double.parseDouble(value.getText().toString());

                    if (dValue <= finalLeftRange)
                        if (dValue < left) {
                            value.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.DARKEN);
                            value.setError("критичное значение");
                        } else {
                            value.getBackground().setColorFilter(Color.parseColor("#ffff00"), PorterDuff.Mode.DARKEN);
                            value.setError("почти критично");
                        }
                    else if (dValue >= finalRightRange) {
                        if (dValue > right) {
                            value.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.DARKEN);
                            value.setError("критичное значение");
                        } else {
                            value.getBackground().setColorFilter(Color.parseColor("#ffff00"), PorterDuff.Mode.DARKEN);
                            value.setError("почти критично");
                        }
                    } else {
                        value.getBackground().setColorFilter(Color.parseColor("#9b9b9b"), PorterDuff.Mode.SRC_ATOP);
                    }
                /*
                    if (Integer.parseInt(value.getText().toString()) > 10) {
                        value.getBackground().setColorFilter(Color.parseColor("#ffff00"), PorterDuff.Mode.DARKEN);
                        value.setError("почти критично");
                    } else if (Integer.parseInt((value.getText().toString())) < 1) {
                        value.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.DARKEN);
                        value.setError("критичное значение");
                    } else
                        value.getBackground().setColorFilter(Color.parseColor("#9b9b9b"), PorterDuff.Mode.SRC_ATOP);
                        */
                }
            }
        });
    }
}
