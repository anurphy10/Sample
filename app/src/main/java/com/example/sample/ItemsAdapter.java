package com.example.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {


    public interface onLongClickListener {
        void onItemLongClicked(int position);
    }
    List<String> items;
    onLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, onLongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup parent, int viewType)
    {

       View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, , attachtoRoot: false);
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position)
    {
        String item = items.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
        public void bind(String item){
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnClickListener()) {
                @Override
                public void onClick(View v) {
                }
            };
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            }
        }
    }
}

