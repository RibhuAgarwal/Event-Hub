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

public class RestaurantActivity extends AppCompatActivity implements OnMapReadyCallback{
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
        setContentView(R.layout.activity_restaurant);

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
        String title = intent.getStringExtra("EAT_FOOD_NAME");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        if (title.equals("Maurya's Rest.Bar.Banquet")) {
            textViewEventTitle.setText("Maurya's Rest.Bar.Banquet");
            imageViewEvent.setImageResource(R.drawable.mauryas);
            latitudeToMark = 50.675780;
            longitudeToMark = -120.337240;
            shouldMarkLocation = true;

        }
        if (title.equals("Tiger Ramen")) {
            textViewEventTitle.setText("Tiger Ramen");
            imageViewEvent.setImageResource(R.drawable.tiger);
            latitudeToMark = 50.6651319 ;
            longitudeToMark = -120.3517268;
            shouldMarkLocation = true;
        }
        if (title.equals("The Coconut")) {
            textViewEventTitle.setText("The Coconut");
            imageViewEvent.setImageResource(R.drawable.cocnut);
            latitudeToMark = 50.6747387;
            longitudeToMark = -120.3278421;
            shouldMarkLocation = true;
        }
        if (title.equals("Flavors of India")) {
            textViewEventTitle.setText("Flavors of India");
            imageViewEvent.setImageResource(R.drawable.flavour);
            latitudeToMark = 50.672809;
            longitudeToMark = -120.3534965;
            shouldMarkLocation = true;
        }
        if (title.equals("Jacob's Noodle & Cutlet")) {
            textViewEventTitle.setText("Jacob's Noodle & Cutlet");
            imageViewEvent.setImageResource(R.drawable.jcob);
            latitudeToMark = 50.6753104;
            longitudeToMark = -120.3322493;
            shouldMarkLocation = true;
        }
        if (title.equals("Taka Japanese Restaurant")) {
            textViewEventTitle.setText("Taka Japanese Restaurant");
            imageViewEvent.setImageResource(R.drawable.transit);
            latitudeToMark = 50.6651319;
            longitudeToMark = -120.3517268;
            shouldMarkLocation = true;
        }
        if (title.equals("Madras Masala & Grill")) {
            textViewEventTitle.setText("Madras Masala & Grill");
            imageViewEvent.setImageResource(R.drawable.transit);
            latitudeToMark = 50.6764401;
            longitudeToMark = -120.3346697;
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
