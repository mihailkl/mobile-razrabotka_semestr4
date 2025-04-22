package com.example.prac7;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
	private TextView fragmentText;
	private Button startButton;
	private Button stopButton;
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		startButton = findViewById(R.id.startButton);
		stopButton = findViewById(R.id.stopButton);

		startButton.setOnClickListener(v -> {
			Intent startIntent = new Intent(MainActivity.this, MusicService.class);
			startService(startIntent);
		});

		stopButton.setOnClickListener(v -> {
			Intent stopIntent = new Intent(MainActivity.this, MusicService.class);
			stopService(stopIntent);
		});
		//2 - алерт
		TextView alertText = findViewById(R.id.alert_text);
		Button alertButton = findViewById(R.id.alert_button);
		alertButton.setOnClickListener(v -> {
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("Алерт диалог");
			builder.setMessage("да или нет?!");
			builder.setPositiveButton("Да", (dialog, which) -> alertText.setText("выбрано: да"));
			builder.setNegativeButton("Нет", (dialog, which) -> alertText.setText("выбрано: нет"));
			builder.create().show();
		});
		//3 - date
		TextView dateText = findViewById(R.id.date_text);
		final Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR), month = calendar.get(Calendar.MONTH), day =
				calendar.get(Calendar.DAY_OF_WEEK);
		Button dateButton = findViewById(R.id.date_button);
		dateButton.setOnClickListener(v -> {
			DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
					(view, year1, month1, dayOfMonth) -> dateText.setText(
							dayOfMonth + "/" + (month1 + 1) + "/" + year1),
					year,
					month,
					day
			);
			datePickerDialog.show();
		});
		//4 - time
		TextView timeText = findViewById(R.id.time_text);
		int hour = calendar.get(Calendar.HOUR), minute = calendar.get(Calendar.MINUTE);
		Button timeButton = findViewById(R.id.time_button);
		timeButton.setOnClickListener(v -> {
			TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
					(view, hourOfDay, minute1) -> timeText.setText(hourOfDay + ":" + minute1),
					hour, minute, true
			);
			timePickerDialog.show();
		});
		//5 - custom
		TextView customText = findViewById(R.id.custom_text);
		Button customButton = findViewById(R.id.custom_button);
		customButton.setOnClickListener(v -> {
			final Dialog dialog = new Dialog(MainActivity.this);
			dialog.setContentView(R.layout.custom_dialog);
			dialog.setTitle("кастомный заголовок");
			final EditText input = dialog.findViewById(R.id.custom_input);
			final Button submitButton = dialog.findViewById(R.id.custom_submit);
			submitButton.setOnClickListener(v2 -> {
				customText.setText("введено: " + input.getText().toString());
				dialog.dismiss();
			});
			dialog.show();
		});
		//6 - fragment
		fragmentText = findViewById(R.id.fragment_text);
		Button fragmentButton = findViewById(R.id.fragment_button);
		fragmentButton.setOnClickListener(v -> {
			new MyCustomFragment().show(getSupportFragmentManager(), "dialog");
		});
	}
	public void updateFragmentResult(String text) {
		this.fragmentText.setText("фрагмент: " + text);
	}
}