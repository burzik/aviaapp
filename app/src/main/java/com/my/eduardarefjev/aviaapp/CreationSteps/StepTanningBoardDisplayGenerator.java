package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	22.12.2017		Eduard Arefjev 		Created "StepRunoutOfRotors" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 */

public class StepTanningBoardDisplayGenerator extends AppCompatActivity {

    private StepEngineData engineData;
    private String id;
    private boolean showValues;
    private boolean editableValues;
    private ArrayList<Class> classArrayList = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //EA Inflate the menu
        if(!editableValues)
            getMenuInflater().inflate(R.menu.menu_creation_steps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean itemSelected = super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.UpdateRecord: {
                Intent intent = new Intent(this, UpdateRecordMenu.class);
                intent.putExtra("recordId", id);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);
                break;
            }
            default:
                return itemSelected;
        }
        return true;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_tanning_board_display_generator);
        this.setTitle(R.string.label_board_generator);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");
        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", false);
        classArrayList = (ArrayList<Class>)intent.getSerializableExtra("classMap");

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextTanningBoardDisplayGenerator);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent;
                if (classArrayList.size() <= 0)
                    intent = new Intent(StepTanningBoardDisplayGenerator.this, StepFinish.class);
                else {
                    intent = new Intent(StepTanningBoardDisplayGenerator.this, classArrayList.get(0));
                    classArrayList.remove(0);
                }
                intent.putExtra("recordId", id);
                intent.putExtra("showValues", showValues);
                intent.putExtra("editableValues", editableValues);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                intent.putExtra("classMap", classArrayList);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        EditText eColdEngine = findViewById(R.id.LinearLabelInpColdEngine);
        if (!eColdEngine.getText().toString().isEmpty())
            engineData.setModeGeneratorTablo(Integer.valueOf(eColdEngine.getText().toString()));
    }

    public void updateUI(){
        if(showValues){
            EditText eColdEngine = findViewById(R.id.LinearLabelInpColdEngine);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eColdEngine);
            }

            eColdEngine.setText(Integer.toString(engineData.getModeGeneratorTablo()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}