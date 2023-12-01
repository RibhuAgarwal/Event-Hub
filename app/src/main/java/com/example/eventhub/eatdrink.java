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

public class eatdrink extends AppCompatActivity {
    RecyclerView recyclerView;
    EatFoodAdapter adapter;
    List<EatFood> eatFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatdrink);


        recyclerView = findViewById(R.id.recyclerview6);
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
                Toast.makeText(getApplicationContext(), "You are already at Eat &amp; Drink", Toast.LENGTH_SHORT).show();

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


        // Sample data

//        ataList2.add(new EatData(R.drawable.mauryas, "Maurya's Rest.Bar.Banquet "));
//        dataList2.add(new EatData(R.drawable.tiger, "Tiger Ramen"));
//        dataList2.add(new EatData(R.drawable.cocnut, "The Coconut"));
//        dataList2.add(new EatData(R.drawable.flavour, "Flavors of India"));
//        dataList2.add(new EatData(R.drawable.jcob, "Jacob's Noodle & Cutlet"));
//        dataList2.add(new EatData(R.drawable.taka, "Taka Japanese Restaurant"));
//        dataList2.add(new EatData(R.drawable.madras, "Madras Masala & Grill"));


        eatFoodList = new ArrayList<>();
        eatFoodList.add(new EatFood(R.drawable.mauryas, "Maurya's Rest.Bar.Banquet"));
        eatFoodList.add(new EatFood(R.drawable.tiger, "Tiger Ramen"));
        eatFoodList.add(new EatFood(R.drawable.cocnut, "The Coconut"));
        eatFoodList.add(new EatFood(R.drawable.flavour, "Flavors of India"));
        eatFoodList.add(new EatFood(R.drawable.jcob, "Jacob's Noodle & Cutlet"));
        eatFoodList.add(new EatFood(R.drawable.taka, "Taka Japanese Restaurant"));
        eatFoodList.add(new EatFood(R.drawable.madras, "Madras Masala & Grill"));

        // Setting adapter
        adapter = new EatFoodAdapter(this, eatFoodList);
        recyclerView.setAdapter(adapter);
    }
}