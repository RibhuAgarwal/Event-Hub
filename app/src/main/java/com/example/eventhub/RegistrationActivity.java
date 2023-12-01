package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private Button registerButton;


    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize Firebase Auth and Database Reference
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();




        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Bind views
        firstNameEditText = findViewById(R.id.et_reg_Fname);
        lastNameEditText = findViewById(R.id.et_reg_Lname);
        passwordEditText = findViewById(R.id.et_reg_password);
        confirmPasswordEditText = findViewById(R.id.et_reg_repassword);
        emailEditText = findViewById(R.id.et_reg_email);
        phoneEditText = findViewById(R.id.et_reg_phone);
        registerButton = findViewById(R.id.reg_btn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate inputs and passwords match
                if (validateForm()) {
                    // Create a new user
                    createAccount(emailEditText.getText().toString(), passwordEditText.getText().toString());
                }
            }
        });
    }






    private boolean validateForm() {
        boolean valid = true;

        String firstName = firstNameEditText.getText().toString();
        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError("Required.");
            valid = false;
        } else {
            firstNameEditText.setError(null);
        }

        String lastName = lastNameEditText.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError("Required.");
            valid = false;
        } else {
            lastNameEditText.setError(null);
        }

        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordEditText.setError("Required and must be at least 6 characters.");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        String confirmPassword = confirmPasswordEditText.getText().toString();
        if (TextUtils.isEmpty(confirmPassword) || !confirmPassword.equals(password)) {
            confirmPasswordEditText.setError("Passwords do not match.");
            valid = false;
        } else {
            confirmPasswordEditText.setError(null);
        }

        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Required and must be a valid email address.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        String phone = phoneEditText.getText().toString();
        if (TextUtils.isEmpty(phone) || !Patterns.PHONE.matcher(phone).matches() || phone.length()<10) {
            phoneEditText.setError("Required and must be a valid phone number.");
            valid = false;
        } else {
            phoneEditText.setError(null);
        }

        return valid;
    }





    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Save additional user details
                            saveUserDetails(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            if (!task.isSuccessful()) {
                                Exception exception = task.getException();
                                if (exception != null) {
                                    Log.e("RegistrationActivity", exception.getMessage());
                                    Toast.makeText(RegistrationActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void saveUserDetails(FirebaseUser firebaseUser) {
        String userId = firebaseUser.getUid();
        Map<String, Object> userValues = new HashMap<>();
        userValues.put("firstName", firstNameEditText.getText().toString());
        userValues.put("lastName", lastNameEditText.getText().toString());
        userValues.put("phone", phoneEditText.getText().toString());
        userValues.put("email", emailEditText.getText().toString());
        userValues.put("password", passwordEditText.getText().toString());


        mDatabase.child("users").child(userId).setValue(userValues);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Go to next activity or show user logged in
            Toast.makeText(RegistrationActivity.this, "Registration successful.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}