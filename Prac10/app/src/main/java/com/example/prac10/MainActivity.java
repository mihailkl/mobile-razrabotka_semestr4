package com.example.prac10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	private EditText inputUser;
	private TextView textUser;
	private Button buttonSave;
	private Button buttonGet;
	private Button buttonUpdate;
	private Button buttonDelete;
	private Button buttonSubd;
	private SharedPreferences sharedPreferences;
	private static final String PREFS_NAME = "MyPrefs";
	private static final String KEY_USERNAME = "username";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inputUser = findViewById(R.id.input_user);
		textUser = findViewById(R.id.text_user);
		buttonSave = findViewById(R.id.button_save);
		buttonGet = findViewById(R.id.button_get);
		buttonUpdate = findViewById(R.id.button_update);
		buttonDelete = findViewById(R.id.button_delete);
		buttonSubd = findViewById(R.id.button_subd);
		sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		buttonSave.setOnClickListener(v -> {
			String userName = inputUser.getText().toString().trim();
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString(KEY_USERNAME, userName);
			editor.apply();
			inputUser.setText("");
		});
		buttonGet.setOnClickListener(v -> {
			textUser.setText(sharedPreferences.getString(KEY_USERNAME, "None"));
		});
		buttonUpdate.setOnClickListener(v -> {
			String newUserName = inputUser.getText().toString().trim();
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString(KEY_USERNAME, newUserName);
			editor.apply();
			inputUser.setText("");
			textUser.setText(sharedPreferences.getString(KEY_USERNAME, "None"));
		});
		buttonDelete.setOnClickListener(v -> {
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.remove(KEY_USERNAME);
			editor.apply();
			textUser.setText("Юзер удален");
		});
		buttonSubd.setOnClickListener(v -> {
			startActivity(new Intent(this, SubdActivity.class));
		});
	}
}