package com.nefariousdream.shopifyapp.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable
{
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("total_price_usd")
    @Expose
    public String totalPriceUsd;
    @SerializedName("order_number")
    @Expose
    public Long orderNumber;
    @SerializedName("contact_email")
    @Expose
    public String contactEmail;
    @SerializedName("order_status_url")
    @Expose
    public String orderStatusUrl;
    @SerializedName("total_price")
    @Expose
    public String totalPrice;
    @SerializedName("shipping_address")
    @Expose
    public ShippingAddress shippingAddress;
    @SerializedName("customer")
    @Expose
    public Customer customer;
}
