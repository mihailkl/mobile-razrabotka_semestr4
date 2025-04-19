package com.example.prac6;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
	private DrawerLayout drawer;
	private ActionBarDrawerToggle toggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, R.string.drawer_open, R.string.drawer_close
		);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		drawer = findViewById(R.id.drawer_layout);
		toggle = new ActionBarDrawerToggle(
				this, drawer, R.string.drawer_open, R.string.drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		NavigationView navigationView = findViewById(R.id.nav_view);
		DrawerLayout finalDrawer = drawer;
		navigationView.setNavigationItemSelectedListener(item -> {
			Fragment fragment = null;
			int id = item.getItemId();

			if (id == R.id.nav_cpu) {
				fragment = new ProcessorFragment();
			} else if (id == R.id.nav_gpu) {
				fragment = new VideoCardFragment();
			} else if (id == R.id.nav_ram) {
				fragment = new RamFragment();
			} else if (id == R.id.nav_second_activity) {
				startActivity(new Intent(this, SecondActivity.class));
				finalDrawer.closeDrawer(GravityCompat.START);
				return true;
			}

			if (fragment != null) {
				getSupportFragmentManager().beginTransaction()
										   .replace(R.id.content_frame, fragment)
										   .commit();
				finalDrawer.closeDrawer(GravityCompat.START);
			}
			return true;
		});
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
									   .replace(R.id.content_frame, new ProcessorFragment())
									   .commit();
			navigationView.setCheckedItem(R.id.nav_cpu);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (toggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}