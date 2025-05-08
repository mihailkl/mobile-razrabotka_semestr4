package com.example.prac12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "books.db";
	private static final int DATABASE_VERSION = 1;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE books (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"title TEXT NOT NULL, " +
				"author TEXT NOT NULL);");

		// Добавляем тестовые данные
		db.execSQL("INSERT INTO books (title, author) VALUES ('Мастер и Маргарита', 'М. Булгаков');");
		db.execSQL("INSERT INTO books (title, author) VALUES ('Тень над Иннсмутом', 'Г.Ф Лавкрафт');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS books");
		onCreate(db);
	}
}