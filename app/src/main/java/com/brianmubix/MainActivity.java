package com.brianmubix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //retrieve Logins
        SharedPreferences logins = getApplicationContext().getSharedPreferences("UserLogins", 0); // 0 - for private mode
        String username = logins.getString("username",null);
        if (username == null) {
            Toast.makeText(getBaseContext(),"Please login first",Toast.LENGTH_LONG).show();

            startActivity(new Intent(getBaseContext(), Login.class));
        }else {
            setTitle(username);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SharedPreferences logins = getApplicationContext().getSharedPreferences("UserLogins", 0); // 0 - for private mode
            SharedPreferences.Editor editor = logins.edit();
            editor.clear();
            editor.commit();

            startActivity(new Intent(getBaseContext(), Login.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            setTitle("Dashboard");
        } else if (id == R.id.nav_myrooms) {
            setTitle("My Rooms");

        } else if (id == R.id.nav_vacant) {
            setTitle("Vacant Rooms");

        } else if (id == R.id.nav_map) {
            setTitle("Rooms Near You");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MapFragment()).commit();

        } else if (id == R.id.nav_faqs) {
            setTitle("FAQs");

        }else if (id == R.id.nav_aboutus) {
            setTitle("About us");

        }else if (id == R.id.nav_change_password) {
            setTitle("Change Password");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new changePasswordFragment()).commit();


        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "This application will help you get a room easily with help of google maps. \nClick the link below to download\n https://www.play.google.com";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "MY HOME APP");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:0712345678"));
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
