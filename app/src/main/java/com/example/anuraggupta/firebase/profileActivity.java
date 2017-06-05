package com.example.anuraggupta.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textViewuserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,loginActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();

        textViewuserEmail=(TextView)findViewById(R.id.textView2);

        textViewuserEmail.setText("welcome  "+user.getEmail());
        buttonLogout=(Button)findViewById(R.id.button2);

        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,loginActivity.class));
        }
    }
}
