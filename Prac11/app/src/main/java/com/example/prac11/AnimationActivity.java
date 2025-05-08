package com.example.prac11;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);

		// 1. Анимация вращения
		ImageView rotateImageView = findViewById(R.id.image);
		ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(rotateImageView, "rotation", 0f, 360f);
		rotateAnim.setDuration(2000);
		rotateAnim.setRepeatCount(ObjectAnimator.INFINITE);
		rotateAnim.start();

		// 2. Анимация перемещения
		Button moveButton = findViewById(R.id.move_button);
		moveButton.setOnClickListener(v -> {
			ObjectAnimator moveAnim = ObjectAnimator.ofFloat(moveButton, "translationX", 0f, 300f, -300f, 0f);
			moveAnim.setDuration(1500);
			moveAnim.start();
		});

		// 3. Анимация изменения размера
		TextView scaleTextView = findViewById(R.id.scale_text);
		scaleTextView.setOnClickListener(v -> {
			ObjectAnimator scaleX = ObjectAnimator.ofFloat(scaleTextView, "scaleX", 1f, 1.5f, 1f);
			ObjectAnimator scaleY = ObjectAnimator.ofFloat(scaleTextView, "scaleY", 1f, 1.5f, 1f);
			scaleX.setDuration(1000);
			scaleY.setDuration(1000);
			scaleX.start();
			scaleY.start();
		});

		// 4. Анимация прозрачности
		View fadeView = findViewById(R.id.fade);
		ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(fadeView, "alpha", 1f, 0.2f, 1f);
		fadeAnim.setDuration(2000);
		fadeAnim.setRepeatCount(ObjectAnimator.INFINITE);
		fadeAnim.start();
	}
}