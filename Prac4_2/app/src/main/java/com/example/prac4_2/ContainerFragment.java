package com.example.prac4_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ContainerFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_container, container, false);
		TextView textView = view.findViewById(R.id.containerTextView);
		textView.setText("Это фрагмент с ContainerView");

		Button button = view.findViewById(R.id.navigateToDynamicContainer);
		button.setOnClickListener(v -> {
			// Навигация к динамическому фрагменту
			getActivity().getSupportFragmentManager().beginTransaction()
						 .replace(R.id.fragment_container_view, new DynamicFragment())
						 .addToBackStack(null)
						 .commit();
		});

		return view;
	}
}