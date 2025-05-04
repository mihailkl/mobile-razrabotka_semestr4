package com.example.prac9;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
	EditText inputFile;
	EditText inputText;
	Button buttonSave;
	Button buttonRead;
	Button buttonDelete;
	Button buttonAdd;
	TextView textContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inputFile = findViewById(R.id.input_file);
		inputText = findViewById(R.id.input_text);
		buttonSave = findViewById(R.id.button_save);
		buttonRead = findViewById(R.id.button_read);
		buttonDelete = findViewById(R.id.button_delete);
		buttonAdd = findViewById(R.id.button_add);
		textContent = findViewById(R.id.text_content);

		buttonSave.setOnClickListener(v -> {
			if (!isExtStorageWritable()) {
				Toast.makeText(this, "хранилище недоступно", Toast.LENGTH_SHORT).show();
				return;
			}
			String fileName = inputFile.getText().toString();
			String content = inputText.getText().toString();
			File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(dir, fileName);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter writer = new FileWriter(file);
				writer.write(content);
				writer.flush();
				writer.close();
				Toast.makeText(this, "файл создан", Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(this, "ошибка", Toast.LENGTH_SHORT).show();
			}
		});

		buttonRead.setOnClickListener(v -> {
			if (!isExtStorageReadable()) {
				Toast.makeText(this, "Внешнее хранилище недоступно", Toast.LENGTH_SHORT).show();
				return;
			}

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
					Toast.makeText(this, "ошибка чтения", Toast.LENGTH_SHORT).show();
				}
			} else {
				textContent.setText("файл не найден");
			}
		});

		buttonDelete.setOnClickListener(v -> {
			File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
			String fileName = inputFile.getText().toString();
			File file = new File(dir, fileName);

			if (file.exists()) {
				if (file.delete()) {
					textContent.setText("файл удален");
				} else {
					Toast.makeText(this, "ошибка с файлом", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(this, "файл не найден", Toast.LENGTH_SHORT).show();
			}
		});

		buttonAdd.setOnClickListener(v -> {
			if (!isExtStorageWritable()) {
				Toast.makeText(this, "хранилище недоступно", Toast.LENGTH_SHORT).show();
				return;
			}
			String fileName = inputFile.getText().toString();
			String content = inputText.getText().toString();
			File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(dir, fileName);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter writer = new FileWriter(file, true);
				writer.append('\n').append(content);
				writer.flush();
				writer.close();
				Toast.makeText(this, "файл создан", Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(this, "ошибка", Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("file", inputFile.getText().toString());
		outState.putString("content", inputText.getText().toString());
		outState.putString("textContent", textContent.getText().toString());
	}

	@Override protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);
		inputFile.setText(inState.getString("file"));
		inputText.setText(inState.getString("content"));
		textContent.setText(inState.getString("textContent"));
	}
	private boolean isExtStorageWritable() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	private boolean isExtStorageReadable() {
		String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
	}
}