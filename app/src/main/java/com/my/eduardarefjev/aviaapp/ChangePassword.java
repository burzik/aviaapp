package com.my.eduardarefjev.aviaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	28.01.2018		Eduard Arefjev 		Created empty class
 * 	19.02.2018      Eduard Arefjev      Created update password method
 */


public class ChangePassword extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_change_password);

        changePassword();
    }

    public void changePassword() {
        Button update = findViewById(R.id.RelativeButtonUpdate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final EditText oldPassword = findViewById(R.id.RelativeInpOldPassword);
                final EditText newPass = findViewById(R.id.RelativeInpNewPassword);
                final EditText newPasswordRepeat = findViewById(R.id.RelativeInpNewPasswordRepeat);

                if(oldPassword.getText().toString().equals(""))
                    badPasswordMsg(oldPassword);
                else {
                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword.getText().toString());

                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                if (newPasswordRepeat.getText().toString().equals(newPass.getText().toString()) && !newPasswordRepeat.getText().toString().equals("")) {
                                    user.updatePassword(newPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(ChangePassword.this, R.string.toast_password_updated, Toast.LENGTH_LONG).show();
                                            } else {
                                                try {
                                                    throw task.getException();
                                                } catch (FirebaseAuthWeakPasswordException e) {
                                                    badPasswordMsg(newPass);
                                                } catch (Exception e) {
                                                    String ex = task.getException().toString();
                                                    Toast.makeText(ChangePassword.this, R.string.label_registration_failed + ex, Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(ChangePassword.this, R.string.toast_password_does_not_match, Toast.LENGTH_LONG).show();
                                    newPass.requestFocus();
                                }
                            } else {
                                badPasswordMsg(oldPassword);
                            }
                        }
                    });
                }
            }
        });
    }

    public void badPasswordMsg(EditText password){
        password.setError(getString(R.string.label_bad_password));
        password.requestFocus();
    }
}
