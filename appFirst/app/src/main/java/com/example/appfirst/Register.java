package com.example.appfirst;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appfirst.databinding.ActivityRegisterBinding;
import com.example.appfirst.modals.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    FirebaseDatabase database;
    ActivityRegisterBinding binding;

    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.loginButton);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        database = FirebaseDatabase.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String email = mEmail.getText().toString().trim();
                                                String password = mPassword.getText().toString().trim();
                                                String fullName = mFullName.getText().toString();
                                                String phone = mPhone.getText().toString();

                                                if (TextUtils.isEmpty(email)) {
                                                    mEmail.setError("Email is required");
                                                    return;
                                                }
                                                if (TextUtils.isEmpty(password)) {
                                                    mPassword.setError("Password is required");
                                                    return;
                                                }
                                                if (password.length() < 6) {
                                                    mPassword.setError("Password must be >=6 characters");
                                                    return;
                                                }
                                                progressBar.setVisibility(View.VISIBLE);
                                                //register the user in firebase
                                                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                                                            userId = fAuth.getCurrentUser().getUid();
                                                            DocumentReference documentReference = fStore.collection("users").document(userId);
                                                            Map<String, Object> user = new HashMap<>();//storing data
                                                            user.put("fname", fullName);
                                                            user.put("email", email);
                                                            user.put("phone", phone);
                                                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                public void onSuccess(Void aVoid) {
                                                                    Log.d(TAG, "onSuccess : user Profile is create for " + userId);
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Log.d(TAG, "onFailure: " + e.toString());
                                                                }
                                                            });
                                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                            finish();

                                                        } else {
                                                            Toast.makeText(Register.this, "Error !!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                                            progressBar.setVisibility(View.GONE);
                                                        }
                                                    }
                                                });
                                            }
                                        });

                mLoginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), login.class));
                        finish();
                    }
                });
            }
        }

