package com.example.prac11;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReciever extends BroadcastReceiver {
	private static final String CHANNEL_ID = "my_channel";

	@Override
	public void onReceive(Context context, Intent intent) {
		createNotificationChannel(context); // Создаем канал если нужно

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
				.setSmallIcon(R.drawable.ic_launcher_foreground)
				.setContentTitle("Отложенное уведомление")
				.setContentText("Это уведомление было запланировано")
				.setPriority(NotificationCompat.PRIORITY_DEFAULT);

		NotificationManager notificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(2, builder.build());
	}

	private void createNotificationChannel(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "My Channel";
			String description = "Channel for my notifications";
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
			channel.setDescription(description);

			NotificationManager notificationManager =
					context.getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}
	}
}