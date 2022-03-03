package com.example.mobiletechchallenge.Adapters;

import android.content.Context;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiletechchallenge.Models.Rates;
import com.example.mobiletechchallenge.R;
import com.example.mobiletechchallenge.ViewHolders.RatesViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class RatesAdapter extends RecyclerView.Adapter<RatesViewHolder> {
    private Context context;
    private Rates rates;
    private List<Pair<String, Double>> ratesAsList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RatesAdapter(Context context, Rates rates) {
        this.context = context;
        this.rates = rates;
        this.ratesAsList = new ArrayList<>(rates.getCurrencies().size());
        rates.getCurrencies().forEach((code, val) -> ratesAsList.add(new Pair<>(code, val)));
    }

    @NonNull
    @Override
    public RatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatesViewHolder holder, int position) {
        holder.textView_code.setText(ratesAsList.get(position).first.toUpperCase(Locale.ROOT));
        holder.textView_value.setText(ratesAsList.get(position).second.intValue());
    }

    @Override
    public int getItemCount() {
        return ratesAsList.size();
    }
}