package com.example.anuraggupta.firebase;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity implements TextView.OnClickListener{

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),profileActivity.class));
        }

        progressDialog=new ProgressDialog(this);

        editTextEmail=(EditText)findViewById(R.id.EditTextEmail);
        editTextPassword=(EditText)findViewById(R.id.EditTextPassword);
        buttonSignIn=(Button)findViewById(R.id.buttonSignin);
        textViewSignUp=(TextView)findViewById(R.id.textViewSignup);

        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);


    }

    private void userLogin(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"please enter an email",Toast.LENGTH_SHORT).show();
            //stop the execution further
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"please enter an password",Toast.LENGTH_SHORT).show();
            //stop execution
            return;
        }

        //if the credentials are entered

        progressDialog.setMessage("Registering User");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),profileActivity.class));
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {

        if(v==buttonSignIn){
            userLogin();
        }
        if(v==textViewSignUp){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

    }
}
