package com.example.nhom13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView btnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation();
    }

    private void navigation(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.calender);

        btnv = findViewById(R.id.btnv);
        btnv.setSelectedItemId(R.id.home);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home:{
                        break;
                    }
                    case R.id.dashboard:{
                        Intent dashboard = new Intent(MainActivity.this, DashBoardActivity.class);
                        startActivity(dashboard);
                        break;
                    }
                    case R.id.user: {
                        Intent user = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(user);
                        break;
                    }
                }
                return false;
            }
        });
    }
}