package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    private EditText login_UserName;
    private EditText login_Password;
    private Button btn_Go;
    private Button btn_Sign;
    private FirebaseAuth mAuth;
    private int backButtonCount;
    private boolean isclick;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mAuth = FirebaseAuth.getInstance(); //Init' the firebase instance
        login_UserName = findViewById(R.id.etU);
        login_Password = findViewById(R.id.etP);
        btn_Go = findViewById(R.id.btnGo);
        btn_Sign = findViewById(R.id.btnSign);
        backButtonCount = 0;
        isclick = false;


        btn_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent = new Intent(Main2Activity.this, RegisterActivity.class);
                startActivity(register_intent);
            }
        });

        // check if there is existing user in firebase and start another activity pickactivity
        btn_Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = login_UserName.getText().toString();
                String passwordUser = login_Password.getText().toString();

                if (user.isEmpty()) {
                    login_UserName.setError("The item Username cannot be empty ");
                    return;
                }

                if (passwordUser.isEmpty()) {
                    login_Password.setError("The item Username cannot be empty ");
                    return;
                }


                btn_Go.setTextColor(Color.RED);
                btn_Go.setEnabled(false);

                mAuth.signInWithEmailAndPassword(user, passwordUser)
                        .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Main2Activity.this, "Logged in with user " + user.getEmail(),
                                            Toast.LENGTH_SHORT).show();
                                    Intent intet = new Intent(Main2Activity.this, PickActivity.class);
                                    btn_Go.setEnabled(true);
                                    btn_Go.setTextColor(Color.WHITE);
                                    startActivity(intet);
                                } else {
                                    Toast.makeText(Main2Activity.this, "User Authentication failed ",
                                            Toast.LENGTH_SHORT).show();
                                    btn_Go.setEnabled(true);
                                    btn_Go.setTextColor(Color.WHITE);
                                }
                            }
                        });
            }
        });
    }
}