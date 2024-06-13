package com.example.lashazarnadze;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUpActivity extends AppCompatActivity {
    private EditText name,lastName,loginEmailSingUp,loginPasswordSingUp,repeatPassword;
    private Button singUp;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sing_up);
        initialization();
        clickListener();

    }

    private void clickListener() {
        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }


        });
    }
    private void registration() {
        if(!validation())
        {
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(loginEmailSingUp.getText().toString(),loginPasswordSingUp.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser!= null)
                {
                    databaseReference.child("UserList").child(firebaseUser.getUid()).child("FirstName").setValue(name.getText().toString());
                    databaseReference.child("UserList").child(firebaseUser.getUid()).child("LastName").setValue(lastName.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Intent intent= new Intent(SingUpActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    private boolean validation(){
        if(name.getText().toString().isEmpty())
        {
            toast("Please Enter Your Name");
            return  false;

        }
        if(lastName.getText().toString().isEmpty())
        {
            toast("Please Enter Your LatName");
            return false;
        }
        if(loginEmailSingUp.getText().toString().isEmpty())
        {
            toast("Please Enter Your Email");
            return  false;
        }
        if(loginPasswordSingUp.getText().toString().isEmpty())
        {
          toast("Please Enter Your Password");
          return  false;
        } if(loginPasswordSingUp.getText().toString().length()<5)
        {
          toast("Password Should More Then Five Character");
          return  false;
        }
        if(repeatPassword.getText().toString().isEmpty())
        {
            toast("Please Repeat Password");
            return  false;
        }
        if(!repeatPassword.getText().toString().equals(loginPasswordSingUp.getText().toString()))
        {
            toast("Passwords Does Not Match");
            return  false;
        }
        return  true;

    }

    private  void  toast(String message)
    {
        Toast.makeText(SingUpActivity.this,message,Toast.LENGTH_LONG).show();
    }
    private void initialization() {
     name=findViewById(R.id.name);
     lastName=findViewById(R.id.lastName);
     loginEmailSingUp=findViewById(R.id.loginEmailSingUp);
     loginPasswordSingUp=findViewById(R.id.loginPasswordSingUp);
     repeatPassword=findViewById(R.id.repeatPassword);
     singUp=findViewById(R.id.singUpSingUp);
     firebaseAuth = FirebaseAuth.getInstance();
     databaseReference = FirebaseDatabase.getInstance().getReference();

    }





}