package com.example.prac10;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubdActivity extends AppCompatActivity {
	private DatabaseHelper dbHelper;
	private List<Task> tasks;
	private TaskAdapter adapter;
	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subd);

		dbHelper = new DatabaseHelper(this);
		recyclerView = findViewById(R.id.tasks_recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		Button addButton = findViewById(R.id.add_button);
		Button updateButton = findViewById(R.id.update_button);
		Button deleteButton = findViewById(R.id.delete_button);

		refreshTasksList();

		addButton.setOnClickListener(v -> {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			View view = getLayoutInflater().inflate(R.layout.dialog_task, null);
			builder.setView(view);
			builder.setTitle("Добавить задачу");

			EditText titleInput = view.findViewById(R.id.title_input);
			EditText descriptionInput = view.findViewById(R.id.description_input);
			EditText dueDateInput = view.findViewById(R.id.due_date_input);
			Spinner prioritySpinner = view.findViewById(R.id.priority_spinner);
			CheckBox completedCheckbox = view.findViewById(R.id.completed_checkbox);

			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
					R.array.priority_levels, android.R.layout.simple_spinner_item
			);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			prioritySpinner.setAdapter(adapter);

			builder.setPositiveButton("Добавить", (dialog, which) -> {
				String title = titleInput.getText().toString();
				String description = descriptionInput.getText().toString();
				String dueDate = dueDateInput.getText().toString();
				String priority = prioritySpinner.getSelectedItem().toString();
				boolean isCompleted = completedCheckbox.isChecked();

				Task task = new Task(0, title, description, dueDate, priority, isCompleted);
				if (dbHelper.addTask(task)) {
					refreshTasksList();
					Toast.makeText(this, "Задача добавлена", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "Ошибка при добавлении", Toast.LENGTH_SHORT).show();
				}
			});

			builder.setNegativeButton("Отмена", null);
			builder.show();
		});
		updateButton.setOnClickListener(v -> {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			View view = getLayoutInflater().inflate(R.layout.dialog_task, null);
			builder.setView(view);
			builder.setTitle("Обновить задачу");

			EditText titleInput = view.findViewById(R.id.title_input);
			EditText descriptionInput = view.findViewById(R.id.description_input);
			EditText dueDateInput = view.findViewById(R.id.due_date_input);
			Spinner prioritySpinner = view.findViewById(R.id.priority_spinner);
			CheckBox completedCheckbox = view.findViewById(R.id.completed_checkbox);

			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
					R.array.priority_levels, android.R.layout.simple_spinner_item
			);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			prioritySpinner.setAdapter(adapter);
			// Заполняем данными первой задачи (для примера)
			Task firstTask = tasks.get(0);
			titleInput.setText(firstTask.getTitle());
			descriptionInput.setText(firstTask.getDescription());
			dueDateInput.setText(firstTask.getDueDate());

			int priorityPosition = adapter.getPosition(firstTask.getPriority());
			prioritySpinner.setSelection(priorityPosition);

			completedCheckbox.setChecked(firstTask.getCompleted());

			builder.setPositiveButton("Обновить", (dialog, which) -> {
				String title = titleInput.getText().toString();
				String description = descriptionInput.getText().toString();
				String dueDate = dueDateInput.getText().toString();
				String priority = prioritySpinner.getSelectedItem().toString();
				boolean isCompleted = completedCheckbox.isChecked();

				Task updatedTask = new Task(firstTask.getId(), title, description, dueDate, priority, isCompleted);
				if (dbHelper.updateTask(updatedTask)) {
					refreshTasksList();
					Toast.makeText(this, "Задача обновлена", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "Ошибка при обновлении", Toast.LENGTH_SHORT).show();
				}
			});

			builder.setNegativeButton("Отмена", null);
			builder.show();
		});
		deleteButton.setOnClickListener(v -> {
			if (tasks.isEmpty()) {
				Toast.makeText(this, "Нет задач для удаления", Toast.LENGTH_SHORT).show();
				return;
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Удалить задачу");
			builder.setMessage("Вы уверены, что хотите удалить первую задачу?");

			builder.setPositiveButton("Удалить", (dialog, which) -> {
				if (dbHelper.deleteTask(tasks.get(0).getId())) {
					refreshTasksList();
					Toast.makeText(this, "Задача удалена", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "Ошибка при удалении", Toast.LENGTH_SHORT).show();
				}
			});

			builder.setNegativeButton("Отмена", null);
			builder.show();
		});
	}

	private void refreshTasksList() {
		tasks = dbHelper.getAllTasks();
		adapter = new TaskAdapter(tasks);
		recyclerView.setAdapter(adapter);
	}

}