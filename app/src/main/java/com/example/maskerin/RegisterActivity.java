package com.example.maskerin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText email_input;
    EditText password_input;
    EditText nama_input;
    EditText nik_input;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void LoginOnClick(View v){
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void RegisterOnClick (View view) {
        email_input = findViewById(R.id.et_email);
        password_input = findViewById(R.id.et_password);
        nama_input = findViewById(R.id.et_nama);
        nik_input = findViewById(R.id.et_nik);

        final String email = email_input.getText().toString().trim();
        String password = password_input.getText().toString().trim();
        final String nama = nama_input.getText().toString().trim();
        final String nik = nik_input.getText().toString().trim();

        // cek user
        if (false) {

        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
        } else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Pengguna pengguna = new Pengguna(
                                        email,
                                        nama,
                                        nik
                                );

                                FirebaseDatabase.getInstance().getReference("Pengguna")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(pengguna).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Log.d("daftar", "createUserWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            updateUI(user);

                                        } else {
                                            Log.w("daftar", "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            updateUI(null);
                                        }
                                    }

                                    public void updateUI(FirebaseUser user) {
                                        if (user != null) {
                                            Toast.makeText(getApplicationContext(), "Sukses daftar", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            //finish();

                                        } else {
                                            Toast.makeText(getApplicationContext(), "gagal daftar", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }


    }
}