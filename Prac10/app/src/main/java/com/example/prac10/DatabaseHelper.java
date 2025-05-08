package com.example.prac10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "tasks.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_TASKS = "tasks";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_TITLE = "title";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_DUE_DATE = "due_date";
	private static final String COLUMN_PRIORITY = "priority";
	private static final String COLUMN_COMPLETED = "completed";
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override public void onCreate(SQLiteDatabase db) {
		String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_TITLE + " TEXT,"
				+ COLUMN_DESCRIPTION + " TEXT,"
				+ COLUMN_DUE_DATE + " TEXT,"
				+ COLUMN_PRIORITY + " TEXT,"
				+ COLUMN_COMPLETED + " INTEGER)";
		db.execSQL(CREATE_TASKS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
		onCreate(db);
	}
	// Добавление задачи
	public boolean addTask(Task task) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_TITLE, task.getTitle());
		values.put(COLUMN_DESCRIPTION, task.getDescription());
		values.put(COLUMN_DUE_DATE, task.getDueDate());
		values.put(COLUMN_PRIORITY, task.getPriority());
		values.put(COLUMN_COMPLETED, task.getCompleted() ? 1 : 0);

		long result = db.insert(TABLE_TASKS, null, values);
		db.close();
		return result != -1;
	}
	// Получение всех задач
	public List<Task> getAllTasks() {
		List<Task> taskList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TASKS, null);

		if (cursor.moveToFirst()) {
			do {
				Task task = new Task(
						cursor.getInt(0),
						cursor.getString(1),
						cursor.getString(2),
						cursor.getString(3),
						cursor.getString(4),
						cursor.getInt(5) == 1
				);
				taskList.add(task);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return taskList;
	}
	// Поиск задачи по ID
	public Task findTaskById(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_TASKS,
				new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_DUE_DATE, COLUMN_PRIORITY, COLUMN_COMPLETED},
				COLUMN_ID + " = ?",
				new String[]{String.valueOf(id)}, null, null, null
		);

		if (cursor != null && cursor.moveToFirst()) {
			Task task = new Task(
					cursor.getInt(0),
					cursor.getString(1),
					cursor.getString(2),
					cursor.getString(3),
					cursor.getString(4),
					cursor.getInt(5) == 1
			);
			cursor.close();
			db.close();
			return task;
		}
		if (cursor != null) {
			cursor.close();
		}
		db.close();
		return null;
	}
	// Обновление задачи
	public boolean updateTask(Task task) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_TITLE, task.getTitle());
		values.put(COLUMN_DESCRIPTION, task.getDescription());
		values.put(COLUMN_DUE_DATE, task.getDueDate());
		values.put(COLUMN_PRIORITY, task.getPriority());
		values.put(COLUMN_COMPLETED, task.getCompleted() ? 1 : 0);

		int result = db.update(TABLE_TASKS, values, COLUMN_ID + " = ?",
				new String[]{String.valueOf(task.getId())}
		);
		db.close();
		return result > 0;
	}

	// Удаление задачи
	public boolean deleteTask(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		int result = db.delete(TABLE_TASKS, COLUMN_ID + " = ?",
				new String[]{String.valueOf(id)}
		);
		db.close();
		return result > 0;
	}
}
