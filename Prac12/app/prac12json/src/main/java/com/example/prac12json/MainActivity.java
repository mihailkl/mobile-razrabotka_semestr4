package com.example.prac12json;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private TextView textOutput;
	private static final String FILE_NAME = "users.json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textOutput = findViewById(R.id.text_output);
		Button buttonCreate = findViewById(R.id.button_create);
		Button buttonRead = findViewById(R.id.button_read);
		Button buttonCreateArray = findViewById(R.id.button_create_array);
		Button buttonReadArray = findViewById(R.id.button_read_array);

		buttonCreate.setOnClickListener(v -> {
			User user = new User("Alice", 25, "alice@example.com");
			Gson gson = new Gson();
			String json = gson.toJson(user);

			saveToFile(json);
			textOutput.setText("Создан JSON объект:\n" + json);
		});
		buttonRead.setOnClickListener(v -> {
			String json = readFromFile();
			if (json != null && !json.isEmpty()) {
				Gson gson = new Gson();
				User user = gson.fromJson(json, User.class);

				String output = "Прочитан JSON объект:\n" +
						"Имя: " + user.name + "\n" +
						"Возраст: " + user.age + "\n" +
						"Email: " + user.email;
				textOutput.setText(output);
			}
		});
		buttonCreateArray.setOnClickListener(v -> {
			List<User> users = new ArrayList<>();
			users.add(new User("John", 30, "john@example.com"));
			users.add(new User("Alice", 25, "alice@example.com"));

			Gson gson = new Gson();
			String jsonArray = gson.toJson(users);

			saveToFile(jsonArray);
			textOutput.setText("Создан JSON массив:\n" + jsonArray);
		});
		buttonReadArray.setOnClickListener(v -> {
			String jsonArray = readFromFile();
			if (jsonArray != null && !jsonArray.isEmpty()) {
				Gson gson = new Gson();
				Type userListType = new TypeToken<List<User>>() {}.getType();
				List<User> users = gson.fromJson(jsonArray, userListType);

				StringBuilder output = new StringBuilder("Прочитан JSON массив:\n");
				for (User user : users) {
					output.append("\nИмя: ").append(user.name)
						  .append("\nВозраст: ").append(user.age)
						  .append("\nEmail: ").append(user.email).append("\n");
				}
				textOutput.setText(output.toString());
			}
		});
	}

	private void saveToFile(String content) {
		try {
			File file = new File(getFilesDir(), FILE_NAME);
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readFromFile() {
		try {
			File file = new File(getFilesDir(), FILE_NAME);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			reader.close();
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}