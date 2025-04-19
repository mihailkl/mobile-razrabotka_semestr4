package com.example.prac6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class VideoCardFragment extends Fragment {
	@Override
	public View onCreateView(
			LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {
		View view = inflater.inflate(R.layout.fragment_image, container, false);
		ImageView imageView = view.findViewById(R.id.image_view);
		imageView.setImageResource(R.drawable.video);
		return view;
	}
}