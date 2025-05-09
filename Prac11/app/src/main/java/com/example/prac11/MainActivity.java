package com.example.prac11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonWebView = findViewById(R.id.button_webview);
		buttonWebView.setOnClickListener(v -> {
			startActivity(new Intent(this, WebViewActivity.class));
		});
		Button buttonPlayer = findViewById(R.id.button_player);
		buttonPlayer.setOnClickListener(v -> {
			startActivity(new Intent(this, PlayerActivity.class));
		});
		Button buttonAnim = findViewById(R.id.button_animation);
		buttonAnim.setOnClickListener(v -> {
			startActivity(new Intent(this, AnimationActivity.class));
		});
		Button buttonNotif = findViewById(R.id.button_notif);
		buttonNotif.setOnClickListener(v -> {
			startActivity(new Intent(this, NotificationActivity.class));
		});
	}
}