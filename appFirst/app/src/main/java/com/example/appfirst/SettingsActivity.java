package com.example.appfirst;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfirst.databinding.ActivitySettingsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

public class SettingsActivity extends AppCompatActivity {


    public void logOut(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), login.class));
        finish();
    }
    TextView eUserName,eUserAbout,resetPassword,verifyEmail;
    Button resendCode;
    ImageView profileImage,addProfileImage;
    FirebaseAuth fAuth;
    FirebaseUser user;
    FirebaseStorage fStore;
    FirebaseDatabase database;

    final int[] c = {0};
    @NonNull ActivitySettingsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding= ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        resetPassword=findViewById(R.id.resetPassword);
        eUserName=findViewById(R.id.eUserName);
        eUserAbout=findViewById(R.id.eUserAbout);
        fAuth=FirebaseAuth.getInstance();
        fStore = FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        user=fAuth.getCurrentUser();
        profileImage=findViewById(R.id.myProfile);
        addProfileImage=findViewById(R.id.addProfile);
        verifyEmail=findViewById(R.id.verifyEmail);

        getSupportActionBar().hide();



        if(!user.isEmailVerified() && c[0]==0){
            verifyEmail.setVisibility(View.VISIBLE);

            verifyEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //send verification Email to the user
                    FirebaseUser fUser = fAuth.getCurrentUser();
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(v.getContext(), "Verification Email Send", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {

                            Log.d("tag", "onFailure: Email Not Send" + e.getMessage());

                        }
                    });
                    c[0]++;
                    if(c[0]==1) {
                        verifyEmail.setVisibility(View.GONE);
                    }
                }
            });

        }

        if(c[0]==0) {
            verifyEmail.setVisibility(View.GONE);
        }

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingsActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        fStore.getReference().child("users").child(FirebaseAuth.getInstance().getUid());

        binding.addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent();
                in.setAction(Intent.ACTION_GET_CONTENT);
                in.setType("image/*");
                startActivityForResult(in,33);
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final EditText resetPassword=new EditText(v.getContext());
               final AlertDialog.Builder passwordResetDialog=new AlertDialog.Builder(v.getContext());
               passwordResetDialog.setTitle("Reset Password");
               passwordResetDialog.setMessage("Enter your new password");
               passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Extract the mail and sent the link
                        String newPassword=resetPassword.getText().toString();
                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SettingsActivity.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(SettingsActivity.this, "Password Reset Failed", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){

            Uri sFile= data.getData();
            binding.myProfile.setImageURI(sFile);
            Toast.makeText(this, "profile pic updated", Toast.LENGTH_SHORT).show();

            final StorageReference reference=fStore.getReference().child("Profile Pictures")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                         fStore.getReference().child("users").child(FirebaseAuth.getInstance().getUid())
                         .child("profilePic");
                         Toast.makeText(SettingsActivity.this, "Profile Pic Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

}