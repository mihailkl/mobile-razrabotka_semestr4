package com.example.prac4_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DynamicFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
		TextView textView = view.findViewById(R.id.dynamicTextView);
		textView.setText("Это динамический фрагмент");

		Button button = view.findViewById(R.id.navigateToStatic);
		button.setOnClickListener(v -> {
			// Навигация обратно к статическому фрагменту
			getActivity().getSupportFragmentManager().beginTransaction()
						 .replace(R.id.fragment_container_view, new StaticFragment())
						 .addToBackStack(null)
						 .commit();
		});

		return view;
	}
}