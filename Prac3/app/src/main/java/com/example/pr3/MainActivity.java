package com.example.pr3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = findViewById(R.id.edit_text);
		Button buttonToSecond = findViewById(R.id.button_to_second);

		buttonToSecond.setOnClickListener(v -> {
			String inputText = editText.getText().toString();
			Intent intent = new Intent(MainActivity.this, SecondActivity.class);
			intent.putExtra("dataFromMain", inputText);
			startActivity(intent);
		});
	}
}