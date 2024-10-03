package com.example.vs_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private EditText mEtEmail, mEtPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("apple");

        mEtEmail = findViewById(R.id.et_email2);
        mEtPwd =  findViewById(R.id.et_pwd);


        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 요청
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class   );
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_SHORT).show();

                        }


                    }
                });
            }
        });






        Button btn_Register = findViewById(R.id.btn_Register);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입화면이동

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}