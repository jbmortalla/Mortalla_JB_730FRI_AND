package com.eldroid.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder> {

    private final List<Headline> headlines;
    private final OnHeadlineClickListener listener;

    public HeadlineAdapter(List<Headline> headlines, OnHeadlineClickListener listener) {
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_headline, parent, false);
        return new HeadlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlineViewHolder holder, int position) {
        Headline headline = headlines.get(position);
        holder.titleTextView.setText(headline.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onHeadlineClick(headline));
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }

    public static class HeadlineViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTextView;

        public HeadlineViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.headlineTitle);
        }
    }

    public interface OnHeadlineClickListener {
        void onHeadlineClick(Headline headline);
    }
}
