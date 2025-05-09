package com.example.prac11;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity {
	Button buttonPlay, buttonStop;
	MediaPlayer mediaPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		buttonPlay = findViewById(R.id.button_play);
		final String URL = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(URL);
			mediaPlayer.prepareAsync();
			mediaPlayer.setOnPreparedListener(mp -> {
				buttonPlay.setEnabled(true);
			});
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(this, "Ошибка загрузки", Toast.LENGTH_SHORT).show();
		}
		buttonPlay = findViewById(R.id.button_play);
		buttonStop = findViewById(R.id.button_stop);
		buttonPlay.setOnClickListener(v -> {
			mediaPlayer.start();
		});
		buttonStop.setOnClickListener(v -> {
			mediaPlayer.stop();
		});
	}
	@Override protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
}