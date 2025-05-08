package com.example.prac10;

public class Task {
	private int id;
	private String title, description, dueDate, priority;
	private boolean isCompeted;
	public Task(int id, String title, String description, String dueDate, String priority, boolean isCompleted) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
		this.isCompeted = isCompleted;
	}
	public int getId() {return this.id;}
	public void setId(int id) {this.id = id;}
	public String getTitle() {return this.title;}
	public void setTitle(String title) {this.title = title;}
	public String getDescription() {return this.description;}
	public void setDescription(String description) {this.description = description;}
	public String getDueDate() {return this.dueDate;}
	public void setDueDate(String dueData) {this.dueDate = dueData;}
	public String getPriority() {return this.priority;}
	public void setPriority(String priority) {this.priority = priority;}
	public boolean getCompleted() {return this.isCompeted;}
	public void setCompleted(boolean isCompeted) {this.isCompeted = isCompeted;}
}
