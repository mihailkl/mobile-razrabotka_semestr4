package com.example.prac4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditText editTextName = findViewById(R.id.editTextName);
		EditText editTextSurname = findViewById(R.id.editTextSurname);
		Button buttonNext = findViewById(R.id.buttonNext);
		buttonNext.setOnClickListener(v -> {
			String name = editTextName.getText().toString();
			String surName = editTextSurname.getText().toString();
			Intent intent = new Intent(this, SecondActivity.class);
			intent.putExtra("name", name);
			intent.putExtra("surname", surName);
			startActivity(intent);
		});
	}
}