package com.example.mobiletechchallenge;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiletechchallenge.Adapters.RatesAdapter;
import com.example.mobiletechchallenge.Models.APIResponse;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textView_base;
    RecyclerView recycler_rates;
    RatesAdapter ratesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_base = findViewById(R.id.textView_base);
        recycler_rates = findViewById(R.id.recycler_rates);

        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getExchangeRates(listener);
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            if(apiResponse == null) {
                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showData(APIResponse apiResponse) {
        textView_base.setText(apiResponse.getBase());
        recycler_rates.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_rates.setLayoutManager(manager);
        ratesAdapter = new RatesAdapter(this, apiResponse.getRates());
        recycler_rates.setAdapter(ratesAdapter);
    }
}