package com.my.eduardarefjev.aviaapp.UserInformation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.eduardarefjev.aviaapp.R;

import java.util.HashMap;
import java.util.List;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.10.2017		Eduard Arefjev 		Created "UserCreation" screen
 * 	23.01.2018      Eduard Arefjev      Updated validation and error messages
 * 	27.01.2018      Eduard Arefjev      Alert message to empty name & surname / Small refactoring
 */

public class UserCreation extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseUser mUser2;
    private FirebaseAuth mAuth2;

    private EditText eFirstName;
    private EditText eLastName;
    private EditText eEmail;
    private EditText ePassword;
    private Spinner spinnerPrivileges;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.linear_create_user);

        //EA Bind variables
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        eFirstName = findViewById(R.id.LinearLabelInpFirstName);
        eLastName = findViewById(R.id.LinearLabelInpLastName);
        eEmail = findViewById(R.id.LinearLabelInpEmail);
        ePassword = findViewById(R.id.LinearLabelInpPassword);
        spinnerPrivileges = findViewById(R.id.LinearLabelInpSpinnerPrivileges);

        eEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b && !isValidEmail(eEmail.getText().toString().trim()))
                    eEmail.setError(getResources().getString(R.string.label_bad_email));
            }
        });

        //EA Create second copy of db
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://aviaapp-c9281.firebaseio.com/")
                .setApiKey("AIzaSyCJPedkRhA5bdzTnTJLEq4UQnr3KY3iQEU")
                .setApplicationId("aviaapp-c9281").build();

        boolean hasBeenInitialized = false;
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps(this);
        FirebaseApp finestayApp;

        for(FirebaseApp app : firebaseApps){
            if(app.getName().equals("AnyAppName")){
                hasBeenInitialized = true;
                finestayApp = app;
                mAuth2 = FirebaseAuth.getInstance(finestayApp);
            }
        }

        if(!hasBeenInitialized) {
            FirebaseApp myApp = FirebaseApp.initializeApp(getApplicationContext(),firebaseOptions, "AnyAppName");
            mAuth2 = FirebaseAuth.getInstance(myApp);
        }

        //EA Create DropDown List
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(UserCreation.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.privileges));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrivileges.setAdapter(myAdapter);

        createUserButton();
        clearFieldsButton();
    }

    public void clearFieldsButton(){
        Button bClear = findViewById(R.id.LinearButtonClear);
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eFirstName.setText("");
                eLastName.setText("");
                eEmail.setText("");
                ePassword.setText("");
            }
        });
    }

    public void createUserButton(){
        Button bCreate = findViewById(R.id.LinearButtonCreateUser);
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EA Get values from inputs
                final String sFirstName = eFirstName.getText().toString().trim();
                final String sLastName = eLastName.getText().toString().trim();
                final String sEmail = eEmail.getText().toString().trim();
                final String sPassword = ePassword.getText().toString().trim();

                if (isCorrectData(sEmail, sPassword)) {
                    if (sFirstName.length() == 0 || sLastName.length() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserCreation.this);
                        builder.setTitle(R.string.error_missing_name_surname);
                        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //EA Do nothing
                            }
                        });
                        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //EA Sign up user
                                createUser(sEmail, sPassword, fillUserHashMap());
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        //EA Sign up user
                        createUser(sEmail, sPassword, fillUserHashMap());
                    }
                } else {
                    if (!isValidEmail(sEmail)) {
                        badEmailMsg();
                    } else {
                        badPasswordMsg();
                    }
                }
            }
        });
    }

    public void badEmailMsg(){
        eEmail.setError(getString(R.string.label_bad_email));
        eEmail.requestFocus();
    }

    public void badPasswordMsg(){
        ePassword.setError(getString(R.string.label_bad_password));
        ePassword.requestFocus();
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isCorrectData(String sEmail, String sPassword) {
        return !(!isValidEmail(sEmail) || sPassword.equals(""));
    }

    private HashMap<String, String> fillUserHashMap(){
        //EA Get values from inputs
        final String sFirstName = eFirstName.getText().toString().trim();
        final String sLastName = eLastName.getText().toString().trim();
        final String sEmail = eEmail.getText().toString().trim();
        final String sPrivileges = spinnerPrivileges.getSelectedItem().toString();

        //EA fill datamap
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("firstName", sFirstName);
        dataMap.put("lastName", sLastName);
        dataMap.put("email", sEmail);
        dataMap.put("privileges", sPrivileges);

        return dataMap;
    }

    private void createUser(String email, String password, final HashMap<String, String> dataMap) {
        mAuth2.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthWeakPasswordException e) {
                        badPasswordMsg();
                    } catch(FirebaseAuthInvalidCredentialsException e) {
                        badEmailMsg();
                    } catch(FirebaseAuthUserCollisionException e) {
                        eEmail.setError(getString(R.string.error_user_exists));
                        eEmail.requestFocus();
                    } catch(Exception e) {
                        String ex = task.getException().toString();
                        Toast.makeText(UserCreation.this, R.string.label_registration_failed + ex, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(UserCreation.this, R.string.label_registration_successful, Toast.LENGTH_SHORT).show();
                    mUser2 = mAuth2.getCurrentUser();
                    assert mUser2 != null;
                    String sUID = mUser2.getUid();
                    mAuth2.signOut();

                    dataMap.put("uid", sUID);
                    mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(UserCreation.this, R.string.label_registration_successful, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(UserCreation.this, R.string.label_registration_failed, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
