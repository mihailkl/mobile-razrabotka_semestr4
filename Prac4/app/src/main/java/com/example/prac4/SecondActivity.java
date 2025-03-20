package com.example.prac4;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		TextView textViewName = findViewById(R.id.textViewName);
		TextView textViewSurname = findViewById(R.id.textViewSurname);
		EditText editTextSubject = findViewById(R.id.editTextSubject);
		Button buttonEnterInfo = findViewById(R.id.buttonEnterInfo);
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String surname = intent.getStringExtra("surname");
		textViewName.setText(name);
		textViewSurname.setText(surname);
		buttonEnterInfo.setOnClickListener(v -> {
			String subject = editTextSubject.getText().toString();
			Intent intent3 = new Intent(this, ThirdActivity.class);
			intent3.putExtra("subject", subject);
			startActivityForResult(intent3, 1);
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK) {
			String time = data.getStringExtra("time");
			Toast.makeText(this, "Время  занятия: " + time, Toast.LENGTH_SHORT).show();
		}
	}
}