package com.example.prac5_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
	private List<Item> itemList;

	public static class ItemViewHolder extends RecyclerView.ViewHolder {
		public ImageView imageView;
		public TextView titleTextView;
		public TextView descTextView;

		public ItemViewHolder(View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.itemImage);
			titleTextView = itemView.findViewById(R.id.itemTitle);
			descTextView = itemView.findViewById(R.id.itemDescription);
		}
	}

	public ItemAdapter(List<Item> items) {
		this.itemList = items;
	}

	@Override
	@NonNull
	public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
								  .inflate(R.layout.item_layout, parent, false);
		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ItemViewHolder holder, int position) {
		Item currentItem = itemList.get(position);
		holder.imageView.setImageResource(currentItem.getImageResId());
		holder.titleTextView.setText(currentItem.getTitle());
		holder.descTextView.setText(currentItem.getDescription());
	}

	@Override
	public int getItemCount() {
		return itemList.size();
	}
}