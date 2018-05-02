package com.nefariousdream.shopifyapp.data.source.remote;

import android.util.Log;

import com.nefariousdream.shopifyapp.data.model.Order;
import com.nefariousdream.shopifyapp.data.model.Orders;
import com.nefariousdream.shopifyapp.data.source.LoadDataCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {
    private static RemoteDataSource INSTANCE;

    private RemoteDataSource() {}

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    public void getOrders(LoadDataCallback<Order> callback) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Orders> call = apiService.getOrders();
        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                if(response.isSuccessful()) {
                    Log.d("SUCCESS!", "SUCCESSFULY FETCHED DATA!");
                    Orders orders = response.body();
                    if (orders != null) {
                        callback.onDataLoaded(orders.orders);
                    }
                } else {
                    Log.d("ERROR!", "FAILED TO FETCH DATA!");
                }
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {

            }
        });
    }


}
