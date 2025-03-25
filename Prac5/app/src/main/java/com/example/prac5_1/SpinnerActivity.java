package com.example.prac5_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpinnerActivity extends AppCompatActivity {
	private Spinner spinner;
	private TextView selectedItemText;
	private String[] items = {"Элемент 1", "Элемент 2", "Элемент 3", "Элемент 4", "Элемент 5"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);

		spinner = findViewById(R.id.mySpinner);
		selectedItemText = findViewById(R.id.selectedItemText);

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_item, items
		);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				selectedItemText.setText("Выбрано: " + items[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				selectedItemText.setText("Выбрано: ничего");
			}
		});
	}
}