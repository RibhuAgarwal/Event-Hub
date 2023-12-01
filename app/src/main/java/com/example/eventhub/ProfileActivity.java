package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextFirstName, editTextLastName, editTextPhone;
    private Button buttonSaveProfile,logout;

    DatabaseReference userRef;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSaveProfile = findViewById(R.id.buttonSaveProfile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener( item -> {
            if (item.getItemId() == R.id.nav_home) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                //Toast.makeText(getApplicationContext(), "You are already at home", Toast.LENGTH_SHORT).show();
            }
            else if (item.getItemId() == R.id.nav_programs) {
                Intent i = new Intent(getApplicationContext(),ProgramActivity.class);
                startActivity(i);
            }
            else if (item.getItemId() == R.id.nav_live) {
                Intent i = new Intent(getApplicationContext(), MainActivities2.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_eatdrink) {
                Intent i = new Intent(getApplicationContext(),eatdrink.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_Access) {
                Intent i = new Intent(getApplicationContext(),AccessActivity.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_gallery) {
                Intent i = new Intent(getApplicationContext(),GalleryActivity.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_tickets) {
                Toast.makeText(getApplicationContext(), "You are already at Profile", Toast.LENGTH_SHORT).show();


            }
            else if (item.getItemId() == R.id.nav_createevent) {

                Intent i = new Intent(getApplicationContext(),createevent.class);
                startActivity(i);



            }



            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        logout= findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Log Out", Toast.LENGTH_SHORT).show();
            }
        });

        // Assuming the user is authenticated with Firebase Auth
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
            fetchUserData(userRef);
        } else {
            Toast.makeText(this, "No authenticated user found.", Toast.LENGTH_SHORT).show();
        }
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });
    }

    private void fetchUserData(DatabaseReference userRef) {
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                // Populate the EditTexts with the fetched data
                if (user != null) {
                    editTextEmail.setText(user.getEmail());
                    editTextFirstName.setText(user.getFirstName());
                    editTextLastName.setText(user.getLastName());
                    editTextPhone.setText(user.getPhone());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "Failed to load user data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveUserProfile() {
        String email = editTextEmail.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        // Input validation (optional)
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        // Update Firebase user record with new data
        Map<String, Object> userProfileUpdates = new HashMap<>();
        userProfileUpdates.put("email", email);
        userProfileUpdates.put("firstName", firstName);
        userProfileUpdates.put("lastName", lastName);
        userProfileUpdates.put("phone", phone);

        userRef.updateChildren(userProfileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ProfileActivity.this, "Profile updated successfully!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Failed to update profile. Please try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
