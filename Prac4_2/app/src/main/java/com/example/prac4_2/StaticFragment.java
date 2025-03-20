package com.example.prac4_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StaticFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {
		View view = inflater.inflate(R.layout.fragment_static, container, false);
		TextView textView = view.findViewById(R.id.staticTextView);
		textView.setText("Это статический фрагмент");
		return view;
	}
}