package com.vipul.kami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
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

public class MainActivity extends AppCompatActivity {
    EditText emailId,password;
    Button bsignup;
    TextView tvsignin;

    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

   /* @Override
    protected void onStart() {

        super.onStart();
        FirebaseUser mFirebaseUser=mAuth.getCurrentUser();
        if(mFirebaseUser!=null){
            Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_LONG).show();
            Intent i=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(MainActivity.this,"Please Signup",Toast.LENGTH_LONG).show();

        }
    }*/

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        emailId=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        bsignup=findViewById(R.id.button);
        tvsignin=findViewById(R.id.textView);

        bsignup.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(getApplicationContext(),"Field are Empty",Toast.LENGTH_LONG).show();
                }
                else if(!email.isEmpty()&& !pwd.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"SignUp Unsuccessful,Please try again",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Intent obj =new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(obj);

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error Ocurred!",Toast.LENGTH_LONG).show();
                }


            }
        });
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }


}

