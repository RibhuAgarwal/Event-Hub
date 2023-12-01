package com.example.eventhub;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class TransitActivity extends AppCompatActivity implements OnMapReadyCallback {
    TextView textViewEventTitle;
    ImageView imageViewEvent;

    private double latitudeToMark;
    private double longitudeToMark;
    private boolean shouldMarkLocation = false;
    private GoogleMap mMap;
    Button buttonGetDirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transit);

        imageViewEvent = findViewById(R.id.imageViewEvent);
        textViewEventTitle = findViewById(R.id.textViewEventTitle);
        buttonGetDirections= findViewById(R.id.getdirection);
        Intent intent = getIntent();
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


        // Extract the title using the key
        String title = intent.getStringExtra("title");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        if (title.equals("Central Station")) {
            textViewEventTitle.setText("Central Station");
            imageViewEvent.setImageResource(R.drawable.train);
            latitudeToMark = 50.678600;
            longitudeToMark = -120.329700;
            shouldMarkLocation = true;

        }
        if (title.equals("National Airport")) {
            textViewEventTitle.setText("National Airport");
            imageViewEvent.setImageResource(R.drawable.airport);
            latitudeToMark = 50.705100;
            longitudeToMark = -120.441530;
            shouldMarkLocation = true;
        }
        if (title.equals("Transit Bus Station")) {
            textViewEventTitle.setText("Transit Bus Station");
            imageViewEvent.setImageResource(R.drawable.transit);
            latitudeToMark = 50.676680;
            longitudeToMark = -120.329580;
            shouldMarkLocation = true;
        }

        // Example usage
        buttonGetDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMapsForDirections(latitudeToMark, longitudeToMark);
            }
        });



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (shouldMarkLocation) {
            addMarkerAtLocation(latitudeToMark, longitudeToMark);
        }
    }

    private void addMarkerAtLocation(double latitude, double longitude) {
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title("Location Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15)); // 15 is a zoom level
    }
    private void openGoogleMapsForDirections(double destLatitude, double destLongitude) {
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destLatitude + "," + destLongitude);

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Attempt to start an activity that can handle the Intent
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Google Maps is not installed", Toast.LENGTH_LONG).show();
        }
    }


}