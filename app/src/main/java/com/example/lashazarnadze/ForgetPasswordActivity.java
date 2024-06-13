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
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText loginEmailForgetPassword;
    private Button  submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);
        inizialation();
        clickListener();
    }

    private void clickListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if( loginEmailForgetPassword.getText().toString().isEmpty())
               {
                   return;
               }
               firebaseAuth.sendPasswordResetEmail(loginEmailForgetPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       Intent intent=new Intent(ForgetPasswordActivity.this,MainActivity.class);
                       startActivity(intent);
                   }
               });
            }
        });
    }

    private void inizialation() {
        loginEmailForgetPassword= findViewById(R.id.loginEmailForgetPassword);
        submit= findViewById(R.id.submit);
        firebaseAuth=FirebaseAuth.getInstance();

    }


}