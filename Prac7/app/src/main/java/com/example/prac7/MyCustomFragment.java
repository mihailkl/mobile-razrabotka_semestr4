package com.example.prac7;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class MyCustomFragment extends DialogFragment {
	private EditText inputText;

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.fragment_my_custom, null);

		inputText = view.findViewById(R.id.input_fragment);

		builder.setView(view)
			   .setTitle("фрагмент")
			   .setPositiveButton("окок", (dialog, id) -> {
				   String text = inputText.getText().toString();
				   ((MainActivity) getActivity()).updateFragmentResult(text);
			   })
			   .setNegativeButton("отмена", (dialog, id) -> MyCustomFragment.this.getDialog().cancel());
		return builder.create();
	}
}