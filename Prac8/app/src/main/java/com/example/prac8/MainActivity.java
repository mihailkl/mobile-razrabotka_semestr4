package com.example.prac8;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.bumptech.glide.Glide;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
	private ImageView dogImage;
	private Button loadButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(MyWorker.class)
				.setInputData(new Data.Builder().putString("task", "задача 1")
												.build())
				.build();

		OneTimeWorkRequest workRequest2 = new OneTimeWorkRequest.Builder(MyWorker.class)
				.setInputData(new Data.Builder().putString("task", "задача 2")
												.build())
				.build();

		OneTimeWorkRequest workRequest3 = new OneTimeWorkRequest.Builder(MyWorker.class)
				.setInputData(new Data.Builder().putString("task", "задача 3")
												.build())
				.build();
		WorkManager.getInstance(this)
				   .beginWith(workRequest1)
				   .then(workRequest2)
				   .then(workRequest3)
				   .enqueue();
		OneTimeWorkRequest parallelWork1 = new OneTimeWorkRequest.Builder(MyWorker.class)
				.setInputData(new Data.Builder().putString("task", "Parallel Task 1")
												.build())
				.build();

		OneTimeWorkRequest parallelWork2 = new OneTimeWorkRequest.Builder(MyWorker.class)
				.setInputData(new Data.Builder().putString("task", "Parallel Task 2")
												.build())
				.build();

		WorkManager.getInstance(this)
				   .enqueue(Arrays.asList(parallelWork1, parallelWork2));
		//2 часть
		dogImage = findViewById(R.id.dog);
		loadButton = findViewById(R.id.button);
		loadButton.setOnClickListener(v -> {
			DogApi api = ApiClient.getApi();
			Call<DogImage> call = api.getDog();
			call.enqueue(new Callback<DogImage>() {
				@Override public void onResponse(Call<DogImage> call, Response<DogImage> response) {
					if (response.isSuccessful() && response.body() != null) {
						Glide.with(MainActivity.this)
							 .load(response.body()
										   .getUrl())
							 .into(dogImage);
					} else {
						Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT)
							 .show();
					}
				}
				@Override public void onFailure(Call<DogImage> call, Throwable t) {
					Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT)
						 .show();
				}
			});
		});
	}
}