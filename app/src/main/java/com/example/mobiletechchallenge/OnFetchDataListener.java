package com.example.mobiletechchallenge;

import com.example.mobiletechchallenge.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);

    void onError(String message);
}
