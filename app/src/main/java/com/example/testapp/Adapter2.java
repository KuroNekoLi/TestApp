package com.example.testapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    private final OnItemClickListener listener;

    public Adapter2(OnItemClickListener listener) {
        this.listener = listener;
    }

    private List<Integer> data = new ArrayList<>();

    public void updateData(int start, int end) {
        data.clear();
        for (int i = start; i <= end; i++) {
            data.add(i);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_index, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_number_text);
        }

        public void bind(int num,final OnItemClickListener listener) {
            textView.setText(String.valueOf(num));
            textView.setOnClickListener( new View.OnClickListener()
            {
                @Override
                public void onClick( View view )
                {
                    listener.onItemClick( num-1,0 );
                }
            } );
        }
    }
}

