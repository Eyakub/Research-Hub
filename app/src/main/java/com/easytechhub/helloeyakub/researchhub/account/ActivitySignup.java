package com.easytechhub.helloeyakub.researchhub.account;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.easytechhub.helloeyakub.researchhub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivitySignup extends AppCompatActivity {


    private EditText inputEmail, inputPassword, confirmPassword;
    private TextInputLayout inputEmailWrapper, inputPasswordWrapper, confirmPasswordWrapper;
    private Button btnSignUp, btnAlreadyAcc;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputEmail = findViewById(R.id.input_email);
        inputEmailWrapper = (TextInputLayout) findViewById(R.id.input_email_wrapper);
        inputPassword = findViewById(R.id.input_password);
        inputPasswordWrapper = (TextInputLayout) findViewById(R.id.input_password_wrapper);
        confirmPassword = findViewById(R.id.confirm_password);
        confirmPasswordWrapper = (TextInputLayout) findViewById(R.id.confirm_password_wrapper);

        final LinearLayout linearLayout = findViewById(R.id.signupLL);

        btnAlreadyAcc = findViewById(R.id.already_login_here);
        btnSignUp = findViewById(R.id.btn_signUp);

        firebaseAuth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideKeyboard();
                signup();
            }
        });

        btnAlreadyAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivitySignup.this, ActivityLogin.class);
                startActivity(i);
            }
        });
    }

    private void signup() {

        String regEmail = inputEmail.getText().toString();
        String regPass = inputPassword.getText().toString();
        String confPass = confirmPassword.getText().toString();
        /*if(!validate()){
            Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(),
                    "Enter valid information",
                    Snackbar.LENGTH_SHORT);
            return;
        }*/

        //else{
            /*final ProgressDialog progressDialog = ProgressDialog.show(ActivitySignup.this,
                    "Please wait...",
                    "Processing...",
                    true);*/
        if(TextUtils.isEmpty(regEmail)){
            inputEmail.setError("Enter an Email");
        }
        if(TextUtils.isEmpty(regPass)){
            inputPassword.setError("Enter a password");
        }
        if(TextUtils.isEmpty(confPass)){
            confirmPassword.setError("Enter confirm password");
        }
        else{
            final ProgressDialog progressDialog = new ProgressDialog(ActivitySignup.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();

            (firebaseAuth.createUserWithEmailAndPassword(
                    regEmail,
                    regPass)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if(task.isSuccessful()){
                        Toast.makeText(ActivitySignup.this,
                                "Registration successful",
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ActivitySignup.this, ActivityLogin.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(ActivitySignup.this,
                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public boolean validate(){
        boolean valid = true;

        String regEmail = inputEmail.getText().toString();
        String regPass = inputPassword.getText().toString();
        String confPass = confirmPassword.getText().toString();

        if(regEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(regEmail).matches()){
            inputEmail.setError("Enter valid Email address");
            valid = false;
        }else{
            inputEmail.setError(null);
        }

        if(regPass.isEmpty() || confPass.isEmpty() || regPass.length() > 5){
            inputPassword.setError("Password should be at least 5 digits");
            valid = false;
        }else{
            if(isPasswordMatch(regPass, confPass)){
                valid = true;
                inputPassword.setError(null);
            }else{
                confirmPassword.setError("Confirm password doesn't match");
                valid = false;
            }
        }

        return valid;
    }

    /*
    method is used for checking valid email id format
     */
    public static boolean isEmailValid(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /*
    method is used for checking Password and Confirm password is correct or not
     */
    public boolean isPasswordMatch(String pass, String confirmPass){
        return pass.matches(confirmPass);
    }

    /*
    hide keyboard
     */
    private void hideKeyboard(){
        View view = getCurrentFocus();
        if(view != null){
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
