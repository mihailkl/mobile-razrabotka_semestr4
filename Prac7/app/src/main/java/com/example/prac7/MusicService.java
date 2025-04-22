package com.example.prac7;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MusicService extends Service {
	private MediaPlayer mediaPlayer;

	@Override
	public void onCreate() {
		super.onCreate();
		mediaPlayer = MediaPlayer.create(this, R.raw.music);
		mediaPlayer.setLooping(true);
		Toast.makeText(this, "Сервис создан", Toast.LENGTH_SHORT).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (!mediaPlayer.isPlaying()) {
			mediaPlayer.start();
			Toast.makeText(this, "Музыка начала играть", Toast.LENGTH_SHORT).show();
		}
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.release();
			Toast.makeText(this, "Сервис остановлен", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}