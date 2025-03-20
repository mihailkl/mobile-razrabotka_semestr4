package com.example.prac4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		EditText editTextDay = findViewById(R.id.editTextDay);
		EditText editTextTime = findViewById(R.id.editTextTime);
		EditText editTextComment = findViewById(R.id.editTextComment);
		Button buttonOK = findViewById(R.id.buttonOK);

		buttonOK.setOnClickListener(v -> {
			String time = editTextTime.getText().toString();

			Intent resultIntent = new Intent();
			resultIntent.putExtra("time", time);
			setResult(RESULT_OK, resultIntent);
			Toast.makeText(this, "Время занятия успешно передано", Toast.LENGTH_SHORT).show();
			finish();
		});
	}
}