package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.PasswordAuthentication;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText userName;//private off
    private EditText password;
    private EditText rePassword;
    private Button btnReg;
    private FirebaseAuth mAuth;
    private boolean isClick = false; // TODO:
    private String lengh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.userNameRegis);
        password = findViewById(R.id.passWordregiRegis);
        rePassword = findViewById(R.id.passWordRegiCheckNameRegis);
        btnReg = findViewById(R.id.ButtRrgi);
        mAuth = FirebaseAuth.getInstance();
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        btnReg.setOnClickListener(new View.OnClickListener() {

            String userReg = userName.getText().toString();
            String passwordReg = password.getText().toString();
            String passwordRegReEnter = rePassword.getText().toString();

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(userReg)) {
                    userName.setError("The item UserName cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(passwordReg)) {
                    password.setError("The item UserName cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(passwordRegReEnter)) {
                    rePassword.setError("The item UserName cannot be empty");
                    return;
                }
                if (passwordReg.length() < 6) {
                    password.setError("Password should be at least 6 characters");
                    return;
                }
                if (!(passwordReg.equals(passwordRegReEnter))) {
                    rePassword.setError("The item password and password re-Enter not equal");
                    return;
                }

                isClick = true;
                {
                    btnReg.setTextColor(Color.RED);
                    btnReg.setEnabled(false);
                    isClick = false;
                }
                mAuth.createUserWithEmailAndPassword(userReg, passwordReg)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(RegisterActivity.this, "Created user" + user.getEmail(), Toast.LENGTH_LONG).show();
                                    btnReg.setTextColor(Color.WHITE);
                                    btnReg.setEnabled(false);
                                    RegisterActivity.this.onBackPressed();
                                } else {
                                    if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                        Toast.makeText(RegisterActivity.this, "Created user failed", Toast.LENGTH_SHORT).show();
                                        userName.setError("The email address is already in use by another account.");
                                        return;
                                    }
                                    // If sign in fails, display a message to the user.
                                    Log.i("createUser: failure", task.getException().getMessage());
                                    Toast.makeText(RegisterActivity.this, "Created user failed" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}





