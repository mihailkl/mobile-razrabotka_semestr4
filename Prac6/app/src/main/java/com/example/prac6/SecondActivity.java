package com.example.prac6;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setTitle("Материнские платы");
		}
		BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
		bottomNav.setOnNavigationItemSelectedListener(item -> {
			Fragment fragment = null;
			String title = "";
			int id = item.getItemId();
			if (id == R.id.bottom_motherboard) {
				fragment = new MotherboardFragment();
				title = "Материнские платы";
			} else if (id == R.id.bottom_storage) {
				fragment = new SSDFragment();
				title = "Накопители";
			} else if (id == R.id.bottom_psu) {
				fragment = new PSFragment();
				title = "Блоки питания";
			}
			if (fragment != null) {
				getSupportFragmentManager().beginTransaction()
										   .replace(R.id.content_frame, fragment)
										   .commit();

				if (actionBar != null) {
					actionBar.setTitle(title);
				}
				return true;
			}
			return false;
		});
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
									   .replace(R.id.content_frame, new MotherboardFragment())
									   .commit();
			bottomNav.setSelectedItemId(R.id.bottom_motherboard);
		}
	}
}