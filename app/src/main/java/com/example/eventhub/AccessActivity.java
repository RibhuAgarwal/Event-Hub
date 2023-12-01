package com.example.eventhub;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class AccessActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AccessAdapter adapter;
    List<Access> accessList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        recyclerView = findViewById(R.id.recyclerview7);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                Toast.makeText(getApplicationContext(), "You are already at access", Toast.LENGTH_SHORT).show();

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


        // Sample data
        accessList = new ArrayList<>();
        // Replace with your drawable IDs
        accessList.add(new Access(R.drawable.train, "Central Station", "Kamloops, BC"));
        accessList.add(new Access(R.drawable.airport, "National Airport", "Kamloops, BC"));
        accessList.add(new Access(R.drawable.transit, "Transit Bus Station", "Kamloops, BC"));
        // ... Add other items

        // Setting adapter
        adapter = new AccessAdapter(this, accessList);
        recyclerView.setAdapter(adapter);
    }
}