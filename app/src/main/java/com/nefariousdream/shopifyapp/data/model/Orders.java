package com.nefariousdream.shopifyapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Orders implements Serializable
{
    @SerializedName("orders")
    @Expose
    public List<Order> orders = null;
}