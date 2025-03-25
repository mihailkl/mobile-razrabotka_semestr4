package com.example.prac5_1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
	private RecyclerView recyclerView;
	private ItemAdapter adapter;
	private List<Item> itemList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler);

		recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		itemList = new ArrayList<>();
		// Добавляем тестовые данные
		itemList.add(new Item("Элемент 1", "Описание первого элемента", R.mipmap.ic_launcher));
		itemList.add(new Item("Элемент 2", "Описание второго элемента", R.mipmap.ic_launcher));
		itemList.add(new Item("Элемент 3", "Описание третьего элемента", R.mipmap.ic_launcher));
		itemList.add(new Item("Элемент 4", "Описание четвертого элемента", R.mipmap.ic_launcher));
		itemList.add(new Item("Элемент 5", "Описание пятого элемента", R.mipmap.ic_launcher));

		adapter = new ItemAdapter(itemList);
		recyclerView.setAdapter(adapter);
	}
}