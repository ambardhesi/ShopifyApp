package com.nefariousdream.shopifyapp.data.source.remote;

import com.nefariousdream.shopifyapp.data.model.Orders;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("admin/orders.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<Orders> getOrders();
}
