//package com.example.eventhub;
//
//import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import java.sql.Time;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
//public class createevent extends AppCompatActivity {
//
//    EditText event_name,event_location,event_desc, event_strttime,event_endtime,event_date ;
//    int hours,mins,hours2,mins2;
//    Button upload_img, event_create;
//    String strdate;
//
//
//    private static final int PICK_IMAGE_REQUEST = 1;
//    private static final int PERMISSION_REQUEST_CODE = 2;
//
//    private DatabaseReference databaseReference;
//    Uri selectedImageUri;
//
//    ImageView imageView;
//
//    String[] arraySpinner = new String[] {"am","pm"};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_createevent);
//
//
//        imageView = findViewById(R.id.upload_image);
//
//        event_name= findViewById(R.id.event_name);
//        event_location=findViewById(R.id.event_location);
//        event_desc=findViewById(R.id.event_description);
//        event_strttime=findViewById(R.id.event_start_time);
//        event_endtime=findViewById(R.id.event_ending_time);
//        event_date=findViewById(R.id.event_date);
//
//        upload_img= findViewById(R.id.upload_image_btn);
//        event_create=findViewById(R.id.create_btn);
//
//
//
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//
//        NavigationView navigationView = findViewById(R.id.nav_view);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//
//
//        navigationView.setNavigationItemSelectedListener( item -> {
//            if (item.getItemId() == R.id.nav_home) {
//
//
//                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
//                startActivity(i);
//            }
////            else if (item.getItemId() == R.id.nav_programs) {
////                Intent i = new Intent(getApplicationContext(),ProgramActivity.class);
////                startActivity(i);
////            }
//            else if (item.getItemId() == R.id.nav_live) {
//
//            }
//            else if (item.getItemId() == R.id.nav_eatdrink) {
//
//            }
//            else if (item.getItemId() == R.id.nav_Access) {
//                Intent i = new Intent(getApplicationContext(),AccessActivity.class);
//                startActivity(i);
//
//            }
//            else if (item.getItemId() == R.id.nav_gallery) {
//
//            }
//            else if (item.getItemId() == R.id.nav_tickets) {
//
//            }
//            else if (item.getItemId() == R.id.nav_createevent) {
//
//                Toast.makeText(getApplicationContext(), "You are already at create event", Toast.LENGTH_SHORT).show();
//
//
//
//            }
//
//
//
//            drawer.closeDrawer(GravityCompat.START);
//            return true;
//        });
//
//
//
//        FirebaseApp.initializeApp(this);
//        databaseReference = FirebaseDatabase.getInstance().getReference("create_event");
//
//
//        upload_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGallery();
//            }
//        });
//
//        event_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // on below line we are getting
//                // the instance of our calendar.
//                final Calendar c = Calendar.getInstance();
//
//                // on below line we are getting
//                // our day, month and year.
//                int year = c.get(Calendar.YEAR);
//                int month = c.get(Calendar.MONTH);
//                int day = c.get(Calendar.DAY_OF_MONTH);
//
//                // on below line we are creating a variable for date picker dialog.
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        // on below line we are passing context.
//                        createevent.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//                                // on below line we are setting date to our edit text.
//                                strdate=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
//                                event_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//
//                            }
//                        },
//                        // on below line we are passing year,
//                        // month and day for selected date in our date picker.
//                        year, month, day);
//                // at last we are calling show to
//                // display our date picker dialog.
//                datePickerDialog.show();
//            }
//        });
//
//        event_strttime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//
//                // on below line we are getting our hour, minute.
//                int hour = c.get(Calendar.HOUR_OF_DAY);
//                int minute = c.get(Calendar.MINUTE);
//
//                // on below line we are initializing our Time Picker Dialog
//                TimePickerDialog timePickerDialog = new TimePickerDialog(createevent.this,
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view,int hourOfDay,
//                                                  int minute) {
//                                // on below line we are setting selected time
//                                // in our text view.
//                                event_strttime.setText(hourOfDay+":"+minute);
//                                hours2= hourOfDay;
//                                mins2=minute;
//                            }
//                        }, hour, minute, false);
//                // at last we are calling show to
//                // display our time picker dialog.
//                timePickerDialog.show();
//
//            }
//        });
//
//
//        event_endtime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//
//                // on below line we are getting our hour, minute.
//                int hour = c.get(Calendar.HOUR_OF_DAY);
//                int minute = c.get(Calendar.MINUTE);
//
//                // on below line we are initializing our Time Picker Dialog
//                TimePickerDialog timePickerDialog = new TimePickerDialog(createevent.this,
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view,int hourOfDay,
//                                                  int minute) {
//                                // on below line we are setting selected time
//                                // in our text view.
//                                event_endtime.setText(hourOfDay+":"+minute);
//                                hours= hourOfDay;
//                                mins=minute;
//                            }
//                        }, hour, minute, false);
//                // at last we are calling show to
//                // display our time picker dialog.
//                timePickerDialog.show();
//
//            }
//        });
//
//        event_create.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                String eventName = event_name.getText().toString();
//                String eventLocation = event_location.getText().toString();
//                String eventDescription = event_desc.getText().toString();
//                String endTiming= hours+" "+mins;
//                String startTiming= hours2+" "+mins2;
//                String eventDate = strdate;
//
//                // Create a unique key for the event
//                String eventId = databaseReference.push().getKey();
//
//                // Create a map to store event data
//                Map<String, String> eventData = new HashMap<>();
//                eventData.put("name", eventName);
//                eventData.put("location", eventLocation);
//                eventData.put("description", eventDescription);
//                eventData.put("date", eventDate);
//                eventData.put("startTime", startTiming );
//                eventData.put("endTime", endTiming);
//                eventData.put("image", String.valueOf(selectedImageUri));
//
//
//                // Store the event data under the unique key
//                databaseReference.child(eventId).setValue(eventData);
//            }
//        });
//
//
//
//
//
//
//
//
//    }
//
//
//
//    private void openGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            // Get the selected image URI
//             selectedImageUri = data.getData();
//
//            // Now you can use this URI to display the selected image or perform further actions.
//            // For example, you can load the image into an ImageView:
//
//            imageView.setImageURI(selectedImageUri);
//
//        }
//    }
//
//    // Check for permission and request it if not granted
//    private void checkPermission() {
//        if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission is not granted, request it
//            ActivityCompat.requestPermissions(this,
//                    new String[]{READ_EXTERNAL_STORAGE},
//                    PERMISSION_REQUEST_CODE);
//        }
//    }
//
//
//
//    // Handle the result of the permission request
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, you can now open the gallery
//                openGallery();
//            } else {
//                // Permission denied, handle accordingly (e.g., show a message to the user)
//            }
//        }
//    }
//    private void uploadImageToFirebaseStorage(Uri imageUri) {
//        StorageReference storageRef = FirebaseStorage.getInstance().getReference("create_event");
//        StorageReference fileReference = storageRef.child(System.currentTimeMillis()
//                + "." + getFileExtension(imageUri));
//
//        fileReference.putFile(imageUri)
//                .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
//                    String imageUrl = uri.toString();
//                    saveImageUrlToRealtimeDatabase(imageUrl);
//                }))
//                .addOnFailureListener(e -> Toast.makeText(createevent.this, e.getMessage(), Toast.LENGTH_SHORT).show());
//    }
//    private String getFileExtension(Uri uri) {
//        // Get content resolver and return the file extension of the image
//        return "";
//    }
//
//    private void saveImageUrlToRealtimeDatabase(String imageUrl) {
//        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("create_event");
//        String uploadId = databaseRef.push().getKey();
//        if (uploadId != null) {
//            databaseRef.child(uploadId).setValue(imageUrl)
//                    .addOnSuccessListener(aVoid -> Toast.makeText(createevent.this, "Upload successful", Toast.LENGTH_LONG).show())
//                    .addOnFailureListener(e -> Toast.makeText(createevent.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_LONG).show());
//        } else {
//            Toast.makeText(createevent.this, "Database reference ID was null.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//}

package com.example.eventhub;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class createevent extends AppCompatActivity {

    // UI Components
    EditText event_name, event_location, event_desc, event_strttime, event_endtime, event_date;
    Button upload_img, event_create;
    ImageView imageView;

    // Variables for date and time
    int hours, mins, hours2, mins2;
    String strdate;

    // Request codes for permissions and picking an image
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 2;

    // Firebase instances
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createevent);

        // Initialize UI components
        imageView = findViewById(R.id.upload_image);
        event_name = findViewById(R.id.event_name);
        event_location = findViewById(R.id.event_location);
        event_desc = findViewById(R.id.event_description);
        event_strttime = findViewById(R.id.event_start_time);
        event_endtime = findViewById(R.id.event_ending_time);
        event_date = findViewById(R.id.event_date);
        upload_img = findViewById(R.id.upload_image_btn);
        event_create = findViewById(R.id.create_btn);

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
                Intent i = new Intent(getApplicationContext(), eatdrink.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_Access) {
                Intent i = new Intent(getApplicationContext(),AccessActivity.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_gallery) {
                Intent i = new Intent(getApplicationContext(), GalleryActivity.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_tickets) {
                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(i);
            }
            else if (item.getItemId() == R.id.nav_createevent) {

                Toast.makeText(getApplicationContext(), "You are already at create event", Toast.LENGTH_SHORT).show();



            }



            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        // Initialize Firebase components
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Events");
        storageReference = FirebaseStorage.getInstance().getReference("EventImages");

        // Set onClickListener for the upload button

        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        createevent.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                strdate=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                event_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        event_strttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(createevent.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view,int hourOfDay,
                                                  int minute) {
                                Calendar chosenTime = Calendar.getInstance();
                                chosenTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                chosenTime.set(Calendar.MINUTE, minute);

                                // Compare chosenTime with current time

                                // on below line we are setting selected time
                                // in our text view.
                                event_strttime.setText(hourOfDay+":"+minute);
                                hours2= hourOfDay;
                                mins2=minute;
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();

            }
        });



        event_endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(createevent.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view,int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                if (hourOfDay < hours2 || (hourOfDay == hours2 && minute <= mins2)) {
                                    // Inform the user that the end time must be after the start time
                                    Toast.makeText(createevent.this, "End time must be after start time", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    event_endtime.setText(hourOfDay+":"+minute);
                                    hours= hourOfDay;
                                    mins=minute;
                            }}
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();

            }
        });

        // Set onClickListener for the create event button
        event_create.setOnClickListener(v -> {
            if (selectedImageUri != null) {
                uploadImageToFirebaseStorage(selectedImageUri);
            } else {
                Toast.makeText(createevent.this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ... [Other existing methods]

    // Method to open gallery for image selection
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Method to upload image to Firebase Storage
    private void uploadImageToFirebaseStorage(Uri imageUri) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                + "." + getFileExtension(imageUri));

        fileReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Dismiss dialog when success
                        progressDialog.dismiss();

                        // Display success toast msg
                        Toast.makeText(getApplicationContext(), "Image Uploaded!!", Toast.LENGTH_SHORT).show();

                        // Get the download URL
                        fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                // Call method to save all data including image URL to the database
                                saveEventToDatabase(uri.toString());
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Dismiss dialog when error
                        progressDialog.dismiss();
                        // Display error toast msg
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        // Show upload progress
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    }
                });
    }

    // Method to save event details to the Realtime Database
    private void saveEventToDatabase(String imageUrl) {
        String eventName = event_name.getText().toString().trim();
        String eventLocation = event_location.getText().toString().trim();
        String eventDescription = event_desc.getText().toString().trim();
        String eventDate = event_date.getText().toString().trim();
        String startTiming = event_strttime.getText().toString().trim();
        String endTiming = event_endtime.getText().toString().trim();

        // Create a unique ID for the event
        String eventId = databaseReference.push().getKey();

        // Create a map to store event data
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("name", eventName);
        eventData.put("location", eventLocation);
        eventData.put("description", eventDescription);
        eventData.put("date", eventDate);
        eventData.put("startTime", startTiming);
        eventData.put("endTime", endTiming);
        eventData.put("imageUrl", imageUrl);

        // Save event data to the database
        if (eventId != null) {
            databaseReference.child(eventId).setValue(eventData)
                    .addOnSuccessListener(aVoid -> Toast.makeText(createevent.this, "Event created successfully", Toast.LENGTH_LONG).show())
                    .addOnFailureListener(e -> Toast.makeText(createevent.this, "Failed to create event: " + e.getMessage(), Toast.LENGTH_LONG).show());
        } else {
            Toast.makeText(createevent.this, "Failed to generate a unique ID for the event.", Toast.LENGTH_SHORT).show();
        }
    }

    // Utility method to get the file extension from a Uri
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    // ... [Methods to handle activity results and permission results]

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            imageView.setImageURI(selectedImageUri);
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        }
    }

}
