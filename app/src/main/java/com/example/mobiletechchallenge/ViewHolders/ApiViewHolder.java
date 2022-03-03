package com.example.mobiletechchallenge.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiletechchallenge.R;

public class ApiViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_base;
    public RecyclerView recycler_rates;

    public ApiViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView_base = itemView.findViewById(R.id.textView_base);
        this.recycler_rates = itemView.findViewById(R.id.recycler_rates);
    }
}
