package com.oceanmtech.authenticationdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.oceanmtech.authenticationdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MainActivity mContext = MainActivity.this;
    ActivityMainBinding mBinding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(mContext, R.layout.activity_main);
    }

    public void signUpHere(View view) {

        String email = mBinding.etEmail.getText().toString();
        String password = mBinding.etPassword.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            mBinding.etEmail.setText("");
                            mBinding.etPassword.setText("");
                            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            mBinding.etEmail.setText("");
                            mBinding.etPassword.setText("");
                            Toast.makeText(getApplicationContext(),"Process Error",Toast.LENGTH_LONG).show();
                            Log.d("Shiv", task.toString());
                        }
                    }
                });

    }
}