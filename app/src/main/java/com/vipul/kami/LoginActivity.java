package com.vipul.kami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailId,password;
    Button bsignin;
    TextView tvsignup;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        emailId=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        bsignin=findViewById(R.id.button);
        tvsignup=findViewById(R.id.textView);
        mAuthStateListener=new FirebaseAuth.AuthStateListener(){


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mAuth.getCurrentUser();
                if(mFirebaseUser!=null){
                    Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_LONG).show();

                }
            }
        };
        bsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailId.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter Email id");
                    emailId.requestFocus();

                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    emailId.requestFocus();

                }
                else if(email.isEmpty()&& pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Field are Empty",Toast.LENGTH_LONG).show();
                }
                else if(!email.isEmpty()&& !pwd.isEmpty()){
                    mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Error!,Please Try Again",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Intent home=new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(home);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error Ocurred!",Toast.LENGTH_LONG).show();
                }

            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }


}
