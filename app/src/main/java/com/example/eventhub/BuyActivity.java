package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BuyActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ImageView imageViewEvent;
    private DatabaseReference databaseReference;

    TextView name_tv, strt_time, end_time, date_tv, detail_tv;
    private Button btnGetDirections;

    private GoogleMap mMap;
    String address;
    String name;


    String eventName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        name_tv = findViewById(R.id.textViewEventTitle);
        strt_time = findViewById(R.id.strattime);
        end_time = findViewById(R.id.endtime);
        date_tv = findViewById(R.id.date);
        detail_tv = findViewById(R.id.textViewEventDetails);


        imageViewEvent = findViewById(R.id.imageViewEvent);
        btnGetDirections = findViewById(R.id.buttondirection);

        // Get the event name from the intent
        eventName = getIntent().getStringExtra("EVENT_ID");

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








        btnGetDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMapsForDirections();
            }
        });
        Button buyTicketButton = findViewById(R.id.buttonBuyTicket); // Replace with your button ID
        buyTicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBuyTicketDialog();
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        if (eventName != null) {
            fetchEventDetails(eventName);
            fetchEventAddress(eventName);
        }


    }


    private void fetchEventDetails(String eventId) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Events").child(eventId);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Fetch and set the event details
                    name = dataSnapshot.child("name").getValue(String.class);
                    String startTime = dataSnapshot.child("startTime").getValue(String.class);
                    String endTime = dataSnapshot.child("endTime").getValue(String.class);
                    String date = dataSnapshot.child("date").getValue(String.class);
                    String description = dataSnapshot.child("description").getValue(String.class);
                    String imageUrl = dataSnapshot.child("imageUrl").getValue(String.class);


                    // Now set the TextViews with the fetched data
                    name_tv.setText(name);
                    strt_time.setText(startTime);
                    end_time.setText(endTime);
                    date_tv.setText(date);
                    detail_tv.setText(description);


                    // If you have an ImageView for the image, load it as well
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        loadImageIntoView(imageUrl);
                    }

                } else {
                    Toast.makeText(BuyActivity.this, "Event does not exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BuyActivity.this, "Database error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadImageIntoView(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .into(imageViewEvent); // imageViewEvent is your ImageView
    }

    private void fetchEventAddress(String eventId) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Events").child(eventId);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    address = dataSnapshot.child("location").getValue(String.class);
                    if (address != null && !address.isEmpty()) {
                        geocodeAddress(address);
                    } else {
                        Toast.makeText(BuyActivity.this, "Address is empty", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(BuyActivity.this, "Event does not exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BuyActivity.this, "Database error", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void geocodeAddress(String address) {
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addresses = geocoder.getFromLocationName(address, 1);
            if (!addresses.isEmpty()) {
                Address location = addresses.get(0);
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                updateMap(latLng);
                Toast.makeText(this, "Address found", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Geocoder IO Exception", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateMap(LatLng location) {
        if (mMap != null) {
            mMap.addMarker(new MarkerOptions().position(location).title("Event Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        }
    }

    private void openGoogleMapsForDirections() {
        if (address != null && !address.isEmpty()) {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Uri.encode(address));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");

            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(this, "Google Maps is not installed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void saveTicketInfoToFirebase(String email, String numberOfTickets) {
        // Get Firebase database reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tickets");

        // Generate a unique ID for the ticket
        String ticketId = databaseReference.push().getKey();

        // Create a ticket object or a HashMap
        HashMap<String, Object> ticketData = new HashMap<>();
        ticketData.put("email", email);
        ticketData.put("numberOfTickets", numberOfTickets);
        ticketData.put("eventName", name); // Saving the event name

        // Save to Firebase
        if (ticketId != null) {
            databaseReference.child(ticketId).setValue(ticketData).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(BuyActivity.this, "Ticket information saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BuyActivity.this, "Failed to save ticket information", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void showBuyTicketDialog() {
        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_buy_ticket, null);

        // Create the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        final EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);
        final EditText editTextNumberOfTickets = dialogView.findViewById(R.id.editTextNumberOfTickets);
        Button buttonSubmit = dialogView.findViewById(R.id.buttonSubmit);

        // Create and show the dialog
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        // Set click listener for the submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String numberOfTickets = editTextNumberOfTickets.getText().toString().trim();

                // Validate email
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Invalid email address");
                    editTextEmail.requestFocus();
                    return;
                }


                // Save to Firebase...
                saveTicketInfoToFirebase(email, numberOfTickets);

                dialog.dismiss();
            }
        });
    }
}
