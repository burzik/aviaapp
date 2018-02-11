package com.my.eduardarefjev.aviaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	06.02.2017		Eduard Arefjev 		Created
 */

public class CreateEngine extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_insert_engine);
        
        createEngineNumber();
    }

    public void createEngineNumber(){
        Button bInsertEngine = findViewById(R.id.buttonCreateEngineNumber);

        bInsertEngine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eEngineNumber = findViewById(R.id.LinearLabelInpEngineNumber);
                mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineNumber").child(String.valueOf(eEngineNumber.getText().toString()));
                EngineInformation engineInformation = new EngineInformation();
                engineInformation.setEngineNumber(Integer.valueOf(eEngineNumber.getText().toString()));
                mDatabase.setValue(engineInformation);
            }
        });
    }
}
