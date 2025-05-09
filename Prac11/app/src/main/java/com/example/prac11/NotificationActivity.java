package com.example.prac11;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationActivity extends AppCompatActivity {
	protected static final String CHANNEL_ID = "my_channel";
	protected static final int NOTIFICATION_ID = 1;
	protected static final int DELAYED_NOTIFICATION_ID = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);

		// Создаем канал уведомлений
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "My Channel";
			String description = "Channel for my notifications";
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
			channel.setDescription(description);
			NotificationManager notificationManager = getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}

		// Обычное уведомление
		Button buttonNotify = findViewById(R.id.button_notify);
		buttonNotify.setOnClickListener(v -> {
			NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
					.setSmallIcon(R.drawable.ic_launcher_foreground)
					.setContentTitle("Обычное уведомление")
					.setContentText("Это тестовое уведомление")
					.setPriority(NotificationCompat.PRIORITY_DEFAULT);

			NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
			notificationManager.notify(NOTIFICATION_ID, builder.build());
		});

		// Отложенное уведомление
		Button buttonDelayed = findViewById(R.id.button_delayed);
		buttonDelayed.setOnClickListener(v -> {
			Intent intent = new Intent(this, AlarmReciever.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					this,
					0,
					intent,
					PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
			);

			AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
			long triggerTime = System.currentTimeMillis() + 10000;

			if (alarmManager != null) {
				alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
				Toast.makeText(this, "Отложенное уведомление запланировано", Toast.LENGTH_SHORT).show();
			}
		});
	}
}