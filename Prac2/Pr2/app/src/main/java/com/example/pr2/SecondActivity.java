package com.example.pr2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Intent intent = getIntent();
		String fullName = intent.getStringExtra("EXTRA_FULL_NAME");
		String groupNumber = intent.getStringExtra("EXTRA_GROUP_NUMBER");
		String age = intent.getStringExtra("EXTRA_AGE");
		String grade = intent.getStringExtra("EXTRA_GRADE");
		TextView textView = findViewById(R.id.textViewDisplay);
		textView.setText("ФИО: " + fullName + "\nНомер группы: " + groupNumber +
								 "\nВозраст: " + age + "\nЖелаемая оценка: " + grade);
	}
}