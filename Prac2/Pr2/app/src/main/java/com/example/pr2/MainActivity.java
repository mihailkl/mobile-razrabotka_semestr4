package com.example.pr2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_main);
		Log.d("MainActivity", "onCreate()");
		Button button = findViewById(R.id.button_programmatic_open);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				EditText editTextFullName = findViewById(R.id.editTextName);
				EditText editTextGroupNumber = findViewById(R.id.editTextGroup);
				EditText editTextAge = findViewById(R.id.editTextAge);
				EditText editTextGrade = findViewById(R.id.editTextGrade);
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtra("EXTRA_FULL_NAME", editTextFullName.getText().toString());
				intent.putExtra("EXTRA_GROUP_NUMBER", editTextGroupNumber.getText().toString());
				intent.putExtra("EXTRA_AGE", editTextAge.getText().toString());
				intent.putExtra("EXTRA_GRADE", editTextGrade.getText().toString());
				startActivity(intent);
			}
		});
	}
	public void openSecondActivity(View view) {
		EditText editTextFullName = findViewById(R.id.editTextName);
		EditText editTextGroupNumber = findViewById(R.id.editTextGroup);
		EditText editTextAge = findViewById(R.id.editTextAge);
		EditText editTextGrade = findViewById(R.id.editTextGrade);
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		intent.putExtra("EXTRA_FULL_NAME", editTextFullName.getText().toString());
		intent.putExtra("EXTRA_GROUP_NUMBER", editTextGroupNumber.getText().toString());
		intent.putExtra("EXTRA_AGE", editTextAge.getText().toString());
		intent.putExtra("EXTRA_GRADE", editTextGrade.getText().toString());
		startActivity(intent);
	}
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("MainActivity", "onStart()");
	}
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("MainActivity", "onResume()");
	}
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("MainActivity", "onPause()");
	}
	@Override
	protected void onStop() {
		super.onStop();
		Log.d("MainActivity", "onStop()");
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("MainActivity", "onDestroy()");
	}
}