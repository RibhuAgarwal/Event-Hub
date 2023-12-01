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
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity  {

    private EventAdapter adapter;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerView recyclerView2 = findViewById(R.id.recyclerview2);
        RecyclerView recyclerView3 = findViewById(R.id.recyclerview3);

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

                        Toast.makeText(getApplicationContext(), "You are already at home", Toast.LENGTH_SHORT).show();
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






        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventList = new ArrayList<>();
        adapter = new EventAdapter(eventList);
        recyclerView.setAdapter(adapter);



        fetchEvents();



        // recycler view 2
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager);

        List<ItemData> dataList = new ArrayList<>();


        MyAdapter adapter_hori = new MyAdapter(dataList);
        recyclerView2.setAdapter(adapter_hori);


        dataList.add(new ItemData(R.drawable.train, "Central Station", "Kamloops, BC"));
        dataList.add(new ItemData(R.drawable.airport, "National Airport", "Kamloops, BC"));
        dataList.add(new ItemData(R.drawable.transit, "Transit", "Kamloops, BC"));




        ///recycler vview 3
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager2);

        List<EatData> dataList2 = new ArrayList<>();


        eat adapter_hori2 = new eat(dataList2);
        recyclerView3.setAdapter(adapter_hori2);


        dataList2.add(new EatData(R.drawable.mauryas, "Maurya's Rest.Bar.Banquet "));
        dataList2.add(new EatData(R.drawable.tiger, "Tiger Ramen"));
        dataList2.add(new EatData(R.drawable.cocnut, "The Coconut"));
        dataList2.add(new EatData(R.drawable.flavour, "Flavors of India"));
        dataList2.add(new EatData(R.drawable.jcob, "Jacob's Noodle & Cutlet"));
        dataList2.add(new EatData(R.drawable.taka, "Taka Japanese Restaurant"));
        dataList2.add(new EatData(R.drawable.madras, "Madras Masala & Grill"));


    }

    private void fetchEvents() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Events");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Event event = snapshot.getValue(Event.class);
                        eventList.add(event);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("FirebaseData", "No data found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FirebaseData", "Error: " + databaseError.getMessage());
            }
        });
    }

}