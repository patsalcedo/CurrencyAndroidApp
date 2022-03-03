package com.example.mobiletechchallenge;

import android.content.Context;
import android.widget.Toast;

import com.example.mobiletechchallenge.Models.APIResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    String accessKey = BuildConfig.ACCESS_KEY;

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request original = chain.request();
                HttpUrl httpUrl = original.url();

                HttpUrl newHttpUrl = httpUrl.newBuilder().addQueryParameter("access_key", accessKey).build();

                Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.exchangeratesapi.io/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getExchangeRates(OnFetchDataListener listener){
        CallAPI callAPI = retrofit.create(CallAPI.class);
        Call<List<APIResponse>> call = callAPI.callApi();

        try{
            call.enqueue(new Callback<List<APIResponse>>() {
                @Override
                public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<APIResponse>> call, Throwable t) {
                    listener.onError("Request Failed");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "An Error Occurred!", Toast.LENGTH_SHORT).show();
        }
    }

    public interface CallAPI {
        @GET("latest")
        Call<List<APIResponse>> callApi();
    }
}
