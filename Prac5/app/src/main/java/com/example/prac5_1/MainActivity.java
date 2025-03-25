package com.example.prac5_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	private ListView categoriesListView;
	private String[] categories = {"Яблоки", "Груши"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		categoriesListView = findViewById(R.id.categoriesListView);
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, categories
		);
		categoriesListView.setAdapter(adapter);

		categoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtra("category", categories[position]);
				startActivity(intent);
			}
		});
		Button goToRecyclerButton = findViewById(R.id.goToRecyclerButton);
		goToRecyclerButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,
				RecyclerActivity.class
		)));
		Button goToScrollButton = findViewById(R.id.goToScrollButton);
		goToScrollButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ActivityScroll.class));
			}
		});
		Button goToSpinnerButton = findViewById(R.id.goToSpinnerButton);
		goToSpinnerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, SpinnerActivity.class));
			}
		});
	}
}