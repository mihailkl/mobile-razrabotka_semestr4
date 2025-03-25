package com.example.prac5_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
	private ListView itemsListView;
	private EditText newItemEditText;
	private Button addButton, deleteButton;
	private ArrayList<String> items = new ArrayList<>();
	private ArrayAdapter<String> adapter;
	private String category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		category = getIntent().getStringExtra("category");
		TextView categoryTitle = findViewById(R.id.categoryTitle);
		categoryTitle.setText(category);
		itemsListView = findViewById(R.id.itemsListView);
		newItemEditText = findViewById(R.id.newItemEditText);
		addButton = findViewById(R.id.addButton);
		deleteButton = findViewById(R.id.deleteButton);
		switch (category) {
			case "Яблоки":
				items.add("Голден");
				items.add("Гала");
				items.add("Фуджи");
				break;
			case "Груши":
				items.add("Перун");
				items.add("Дюшес");
				break;
		}

		adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, items
		);
		itemsListView.setAdapter(adapter);

		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String newItem = newItemEditText.getText().toString();
				if (!newItem.isEmpty()) {
					items.add(newItem);
					adapter.notifyDataSetChanged();
					newItemEditText.setText("");
				}
			}
		});

		deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int selectedPosition = itemsListView.getCheckedItemPosition();
				if (selectedPosition != ListView.INVALID_POSITION) {
					items.remove(selectedPosition);
					adapter.notifyDataSetChanged();
					itemsListView.clearChoices();
				}
			}
		});
	}
}