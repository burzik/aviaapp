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

    public static void checkValue(final EditText value, final double left, final double right){

        value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if (value.getText().toString().length() != 0 ) {
                    double dValue = Double.parseDouble(value.getText().toString());
                    if (dValue < left) {
                        value.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.DARKEN);
                        //if (left == Double.MIN_VALUE){}
                        if (right == Double.MAX_VALUE){
                            value.setError("критичное значение (" + left + "- ∞)");
                        } else {
                        value.setError("критичное значение (" + left + "-" + right + ")");
                        }
                    }
                    else if (dValue > right) {
                        value.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.DARKEN);
                            if (right == Double.MAX_VALUE){
                            value.setError("критичное значение (" + left + "- ∞)");
                            } else {
                            value.setError("критичное значение (" + left + "-" + right + ")");
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
        double result = 0;
        result = nkvd*Math.sqrt((273+temp)/288);
        return result;
    }
}
