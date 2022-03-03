package com.example.mobiletechchallenge.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiletechchallenge.R;

public class RatesViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_code;
    public TextView textView_value;

    public RatesViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView_code = itemView.findViewById(R.id.textView_code);
        this.textView_value = itemView.findViewById(R.id.textView_value);
    }
}
