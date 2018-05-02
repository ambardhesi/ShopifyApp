package com.nefariousdream.shopifyapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShippingAddress implements Serializable
{
    @SerializedName("province")
    @Expose
    public String province;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("country_code")
    @Expose
    public String countryCode;
    @SerializedName("province_code")
    @Expose
    public String provinceCode;
}