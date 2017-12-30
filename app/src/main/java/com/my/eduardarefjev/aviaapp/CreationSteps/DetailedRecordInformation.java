package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.CreationSteps.StepEngineData;
import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	30.12.2017		Eduard Arefjev 		Created "DetailedRecordInformation" screen
 */
public class DetailedRecordInformation extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_record_detailed_info);

        this.setTitle("Detailed info");

        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", -1);
        Bundle extra = getIntent().getBundleExtra("extra");
        ArrayList<StepEngineData> objects = (ArrayList<StepEngineData>) extra.getSerializable("objects");
        assert objects != null;
        final StepEngineData user = objects.get(position);

        EditText qwe = (EditText) findViewById(R.id.LinearInpEngineNumber222);
            int id = user.getEngineId();
            qwe.setText(Integer.toString(id));
        //qwe.setText(user.getEngineId());
    }
}
