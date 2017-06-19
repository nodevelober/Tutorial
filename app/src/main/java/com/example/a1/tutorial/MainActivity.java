package com.example.a1.tutorial;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a1.tutorial.fragments.FragmentResult;
import com.example.a1.tutorial.fragments.FragmentTeory;
import com.example.a1.tutorial.fragments.FragmentTest;
import com.example.a1.tutorial.fragments.FragmentUser;

import java.net.URI;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTeory fteory;
    FragmentResult fresult;
    FragmentTest ftest;
    FragmentUser fuser;
    ImageView imageView;
    String name;
    TextView textView;
    public DrawerLayout drawer;

    private static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        drawer.openDrawer(GravityCompat.START);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);


        fteory = new FragmentTeory();
        ftest = new FragmentTest();
        fresult = new FragmentResult();
        fuser = new FragmentUser();
     //   NavigationView nv = new NavigationView(this);
     //   View header = nv.getHeaderView(0);
        //textView = (TextView) header.findViewById(R.id.textUser);
        //textV iew.setText("user");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        //else {
       //     super.onBackPressed();
       // }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                name = data.getStringExtra("login");


            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_teory) {
          //  getFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);

            //Fragment hidenFrag = getFragmentManager().findFragmentByTag("theory");
            ftrans.replace(R.id.content_main, fteory, "ftheory");
            setTitle("Теория");

            // ftrans.addToBackStack("ftheory");

        } else if (id == R.id.nav_test) {
           // getFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);

            Bundle bundle = new Bundle();
            ftest.setArguments(bundle);
            ftrans.replace(R.id.content_main, ftest);
            setTitle("Тест");

            //ftrans.addToBackStack("ftest");
           // drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else if (id == R.id.nav_result) {
           // getFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);

            ftrans.replace(R.id.content_main, fresult);
            setTitle("Результаты");

           // ftrans.addToBackStack("fresult");
        } else if (id == R.id.nav_change_user) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {
            finish();
        } ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
