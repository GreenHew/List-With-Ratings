package com.example.matthew.mgreen_listwithratings_s18;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<ListItem> data;

    private OnItemClicked onItemClicked;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, rating, comment;
        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            rating = view.findViewById(R.id.rating);
            comment = view.findViewById(R.id.comment);
        }
    }

    public MyAdapter(ArrayList<ListItem> dataList) {
        data = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bug_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ListItem item = data.get(position);
        holder.title.setText(item.getTitle());

        float rating = item.getRating();
        StringBuilder stars = new StringBuilder();
        do {
            if (rating == 0.5f)
                stars.append(Character.toString((char) 0x00BD));
            else if(rating > 0)
                stars.append(Character.toString((char) 0x2605));
            rating--;
        }while (rating > 0f);
        holder.rating.setText(stars.toString());

        if (item.getComment() != null)
            holder.comment.setText(item.getComment());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClicked(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }
}
