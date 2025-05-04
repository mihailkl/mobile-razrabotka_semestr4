package com.example.prac9_1;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
	private static final int REQUEST_CODE = 777;
	private EditText inputFile;
	private Button buttonRead;
	private TextView textContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inputFile = findViewById(R.id.input_file);
		buttonRead = findViewById(R.id.button_read);
		textContent = findViewById(R.id.text_content);
		buttonRead.setOnClickListener(v -> {
			String fileName = inputFile.getText().toString();
			File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
			File file = new File(dir, fileName);
			if (file.exists()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line).append('\n');
					}
					br.close();

					textContent.setText(sb.toString());
				} catch (IOException e) {
					e.printStackTrace();
					Toast.makeText(this, "ошибка", Toast.LENGTH_SHORT).show();
				}
			} else {
				textContent.setText("файл не найден");
			}
		});
		if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {
			requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
					REQUEST_CODE
			);
		}
	}
	@Override public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResult) {
		super.onRequestPermissionsResult(requestCode, permission, grantResult);
		if (requestCode == REQUEST_CODE) {
			if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
				Toast.makeText(this, "разрешение получено", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "разрешение отклонено", Toast.LENGTH_SHORT).show();
			}
		}
	}
}