package com.example.lashazarnadze;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
   private EditText loginEmail,loginPassword;
   private TextView singUp,forgetPassword;
   private Button  LoginIn;
   private  FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initialization();
        clickListener();
    }
    private void initialization() {


        loginEmail=findViewById(R.id.loginEmail);
        loginPassword=findViewById(R.id.loginPassword);
        LoginIn=findViewById(R.id.LoginIn);
        firebaseAuth = FirebaseAuth.getInstance();
        singUp=findViewById(R.id.singUp);
        forgetPassword=findViewById(R.id.forgetPassword);

    }
     private  void  clickListener()
     {
         LoginIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            if(loginEmail.getText().toString().isEmpty())
            {
                return;
            }
            if(loginPassword.getText().toString().isEmpty())
            {
                return;
            }
            firebaseAuth.signInWithEmailAndPassword(loginEmail.getText().toString(),loginPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                  Intent intent = new Intent( MainActivity.this, Welcome.class);
                  startActivity(intent);
                }
            });
           }

       });

         singUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this,SingUpActivity.class);
                 startActivity(intent);
             }
         });
         forgetPassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
             }
         });
     }
}