package com.example.prac12client;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonGet = findViewById(R.id.button_get);
		TextView textGet = findViewById(R.id.text_get);
		buttonGet.setOnClickListener(v -> {
			Uri contentUri = Uri.parse("content://com.example.prac12/books");
			ContentResolver resolver = getContentResolver();
			Cursor cursor = resolver.query(contentUri, new String[]{"_id", "title", "author"},
					null, null, "title ASC"
			);
			StringBuilder booksInfo = new StringBuilder();
			if (cursor != null) {
				try {
					int idColumn = cursor.getColumnIndex("_id");
					int titleColumn = cursor.getColumnIndex("title");
					int authorColumn = cursor.getColumnIndex("author");

					while (cursor.moveToNext()) {
						int id = cursor.getInt(idColumn);
						String title = cursor.getString(titleColumn);
						String author = cursor.getString(authorColumn);

						booksInfo.append("ID: ").append(id).append("\n");
						booksInfo.append("Название: ").append(title).append("\n");
						booksInfo.append("Автор: ").append(author).append("\n\n");
					}
				} finally {
					cursor.close();
				}
			}
			textGet.setText(booksInfo.toString());
		});
	}
}