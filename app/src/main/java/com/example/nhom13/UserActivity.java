package com.example.nhom13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhom13.activity.event.EventActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView btnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        navigation();
    }

    private void navigation(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.user);

        btnv = findViewById(R.id.btnv);
        btnv.setSelectedItemId(R.id.user);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home:{
                        Intent main = new Intent(UserActivity.this, MainActivity.class);
                        startActivity(main);
                        break;
                    }
                    case R.id.dashboard:{
                        Intent dashboard = new Intent(UserActivity.this, DashBoardActivity.class);
                        startActivity(dashboard);
                        break;
                    }
                    case R.id.user: {
                        break;
                    }
                }
                return false;
            }
        });
    }
}