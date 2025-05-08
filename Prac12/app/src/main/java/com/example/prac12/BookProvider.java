package com.example.prac12;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;

public class BookProvider extends ContentProvider {
	private static final String AUTHORITY = "com.example.prac12";
	private static final String BASE_PATH = "books";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

	private SQLiteDatabase db;

	@Override
	public boolean onCreate() {
		DBHelper dbHelper = new DBHelper(getContext());
		db = dbHelper.getWritableDatabase();
		return (db != null);
	}

	@Override
	public Cursor query(
			@NonNull Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder
	) {
		return db.query("books", projection, selection, selectionArgs,
				null, null, sortOrder
		);
	}

	@Override
	public Uri insert(@NonNull Uri uri, ContentValues values) {
		long rowID = db.insert("books", "", values);
		Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
		getContext().getContentResolver().notifyChange(_uri, null);
		return _uri;
	}

	@Override
	public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
		int count = db.delete("books", selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(
			@NonNull Uri uri, ContentValues values, String selection,
			String[] selectionArgs
	) {
		int count = db.update("books", values, selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(@NonNull Uri uri) {
		return "vnd.android.cursor.dir/vnd.example.books";
	}
}