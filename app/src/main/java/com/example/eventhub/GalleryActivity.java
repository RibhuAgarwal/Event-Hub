package com.example.eventhub;


import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ArrayList<ImageItem> imageList = new ArrayList<>();

    ImagesAdapter imagesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> openFileChooser());

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
                Intent i = new Intent(getApplicationContext(),eatdrink.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_Access) {
                Intent i = new Intent(getApplicationContext(),AccessActivity.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_gallery) {
                Toast.makeText(getApplicationContext(), "You are already at gallery", Toast.LENGTH_SHORT).show();

            }
            else if (item.getItemId() == R.id.nav_tickets) {
                Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(i);

            }
            else if (item.getItemId() == R.id.nav_createevent) {

                Intent i = new Intent(getApplicationContext(),createevent.class);
                startActivity(i);



            }



            drawer.closeDrawer(GravityCompat.START);
            return true;
        });


        recyclerView = findViewById(R.id.recycler_view);
        imageList = new ArrayList<>();
        imagesAdapter = new ImagesAdapter(this, imageList);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(imagesAdapter);

        loadImagesFromFirebase();




    }

    private void loadImagesFromFirebase() {


        DatabaseReference dbImages = FirebaseDatabase.getInstance().getReference("uploads");
        dbImages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String imageUrl = postSnapshot.getValue(String.class);
                    ImageItem upload = new ImageItem(imageUrl);
                    imageList.add(upload);
                    Log.d("GalleryActivity", "Image URL: " + imageUrl); // Log the URL for debugging
                }
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(GalleryActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("GalleryActivity", "Database Error: " + databaseError.getMessage()); // Log errors
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            uploadImageToFirebaseStorage(imageUri);
        }
    }

    private void uploadImageToFirebaseStorage(Uri imageUri) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference("uploads");
        StorageReference fileReference = storageRef.child(System.currentTimeMillis()
                + "." + getFileExtension(imageUri));

        fileReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    saveImageUrlToRealtimeDatabase(imageUrl);
                }))
                .addOnFailureListener(e -> Toast.makeText(GalleryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private String getFileExtension(Uri uri) {
        // Get content resolver and return the file extension of the image
        return "";
    }

    private void saveImageUrlToRealtimeDatabase(String imageUrl) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        String uploadId = databaseRef.push().getKey();
        if (uploadId != null) {
            databaseRef.child(uploadId).setValue(imageUrl)
                    .addOnSuccessListener(aVoid -> Toast.makeText(GalleryActivity.this, "Upload successful", Toast.LENGTH_LONG).show())
                    .addOnFailureListener(e -> Toast.makeText(GalleryActivity.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_LONG).show());
        } else {
            Toast.makeText(GalleryActivity.this, "Database reference ID was null.", Toast.LENGTH_SHORT).show();
        }
    }

}
