package com.easytechhub.helloeyakub.researchhub.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.easytechhub.helloeyakub.researchhub.MainActivity;
import com.easytechhub.helloeyakub.researchhub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {

    private Button btnLogin, btnCreateOne, btnForgetPass;
    private EditText userEmail, userPassword;
    private FirebaseAuth firebaseAuth;
    public static String userEmailStore;
    private static String userPasswordStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.mailET);
        userPassword = findViewById(R.id.passET);

        btnCreateOne = findViewById(R.id.btnCreateOne);
        btnForgetPass = findViewById(R.id.btnForgetPass);
        btnLogin = findViewById(R.id.btnLogin);

        firebaseAuth =  FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userEmailStore = userEmail.getText().toString();
                userPasswordStore = userPassword.getText().toString();

                if(TextUtils.isEmpty(userEmailStore)){
                    userEmail.setError("Email Can't be Empty");
                }
                if(TextUtils.isEmpty(userPasswordStore)){
                    userPassword.setError("Password Can't be Empty");
                }

                else{

                    final ProgressDialog progressDialog = ProgressDialog.show(
                            ActivityLogin.this,
                            "Please wait...",
                            "Processing...",
                            true);

                    (firebaseAuth.signInWithEmailAndPassword(userEmailStore,
                            userPasswordStore)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if(task.isSuccessful()){
                                Toast.makeText(ActivityLogin.this, "Login Successfull",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ActivityLogin.this, MainActivity.class);
                                //i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                startActivity(i);
                            }
                            else{
                                Log.e("Error", task.getException().toString());
                                Toast.makeText(ActivityLogin.this,
                                        task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });


        btnCreateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityLogin.this, ActivitySignup.class);
                startActivity(i);
            }
        });

        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this, ActivityForgetPassword.class));
                finish();
            }
        });

    }
}
