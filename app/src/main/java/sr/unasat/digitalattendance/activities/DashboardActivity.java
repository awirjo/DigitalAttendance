package sr.unasat.digitalattendance.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import sr.unasat.digitalattendance.R;
import sr.unasat.digitalattendance.database.DtbHelper;
import sr.unasat.digitalattendance.fragmentsDashboard.FragmentMain;
import sr.unasat.digitalattendance.fragmentsDashboard.FragmentSecond;
import sr.unasat.digitalattendance.notificationService.NotificationActivity;
import sr.unasat.digitalattendance.restApi.RestCall;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DtbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout =(DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        db = new DtbHelper(this);

        // default fragment loaded here
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new FragmentMain());
        fragmentTransaction.commit();
    }
    //avoid current navigation drawer close when pressing the "back" option
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { // boolean is created when implementing "NavigationView.OnNavigationItemSelectedListener"
        switch (item.getItemId()) {
            case R.id.home: //when pressed load to main fragment activity
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new FragmentMain());
                fragmentTransaction.commit();
                break;
            case  R.id.studentProfile:
                Intent intent1 = new Intent(DashboardActivity.this, StudentActivity.class);
                startActivity(intent1);
                Toast.makeText(DashboardActivity.this, "*Remember:  YOU CAN'T UPDATE FIRSTNAME, INSTEAD JUST DELETE*", Toast.LENGTH_LONG).show();
                break;
            case R.id.attendance:
                Intent intent2 = new Intent(DashboardActivity.this, AttendanceActivity.class);
                startActivity(intent2);
                break;
            case R.id.onlineRegistration:
                Intent intent3 = new Intent(DashboardActivity.this, RestCall.class);
                startActivity(intent3);
                break;
            case R.id.setReminder:
                Intent intent4 = new Intent(DashboardActivity.this, NotificationActivity.class);
                startActivity(intent4);
                break;
            case R.id.aboutUs:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new FragmentSecond()); //replace fragment because its already added
                fragmentTransaction.commit();
                break;
            case R.id.logout:
                Intent intent5 =new Intent(DashboardActivity.this,LoginActivity.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent5);
                Toast.makeText(getApplicationContext(), "Logging Out", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);// when selecting an item, closes the drawer
        return true;
    }
}