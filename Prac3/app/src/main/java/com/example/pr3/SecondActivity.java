package com.example.pr3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		textView = findViewById(R.id.text_view_second);
		String dataFromMain = getIntent().getStringExtra("dataFromMain");
		textView.setText(dataFromMain);

		Button buttonToThird = findViewById(R.id.button_to_third);
		buttonToThird.setOnClickListener(v -> {
			Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
			intent.putExtra("dataFromSecond", dataFromMain);
			startActivity(intent);
		});
	}
}