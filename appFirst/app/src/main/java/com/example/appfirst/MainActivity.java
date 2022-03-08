package com.example.appfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfirst.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        FirebaseUser user=fAuth.getCurrentUser();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.createGrp:
                Toast.makeText(this, "Creating Group", Toast.LENGTH_SHORT).show();
                break;

            case R.id.joinMeet:
                Toast.makeText(this, "Joining Meeting", Toast.LENGTH_SHORT).show();
                break;

            case R.id.newMeet:
                Toast.makeText(this, "Starting new Meeting", Toast.LENGTH_SHORT).show();
                break;

            case R.id.settings:
                Intent i=new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(i);
                break;

        }
        return true;
    }
}