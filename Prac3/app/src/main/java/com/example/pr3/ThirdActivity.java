package com.example.pr3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		textView = findViewById(R.id.text_view_third);
		String dataFromSecond = getIntent().getStringExtra("dataFromSecond");
		textView.setText(dataFromSecond);

		Button backButton = findViewById(R.id.back_button);
		backButton.setOnClickListener(v -> finish());
	}
}