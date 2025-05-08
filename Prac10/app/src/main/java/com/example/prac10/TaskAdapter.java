package com.example.prac10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// TaskAdapter.java
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
	private List<Task> tasks;

	public static class TaskViewHolder extends RecyclerView.ViewHolder {
		public TextView titleTextView;
		public TextView descriptionTextView;
		public TextView dueDateTextView;
		public TextView priorityTextView;
		public CheckBox completedCheckbox;

		public TaskViewHolder(View itemView) {
			super(itemView);
			titleTextView = itemView.findViewById(R.id.task_title);
			descriptionTextView = itemView.findViewById(R.id.task_description);
			dueDateTextView = itemView.findViewById(R.id.task_due_date);
			priorityTextView = itemView.findViewById(R.id.task_priority);
			completedCheckbox = itemView.findViewById(R.id.task_completed);
		}

		public void bind(Task task) {
			titleTextView.setText(task.getTitle());
			descriptionTextView.setText(task.getDescription());
			dueDateTextView.setText(task.getDueDate());
			priorityTextView.setText(task.getPriority());
			completedCheckbox.setChecked(task.getCompleted());
		}
	}

	public TaskAdapter(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override @NonNull
	public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
								  .inflate(R.layout.item_task, parent, false);
		return new TaskViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TaskViewHolder holder, int position) {
		holder.bind(tasks.get(position));
	}

	@Override
	public int getItemCount() {
		return tasks.size();
	}
}