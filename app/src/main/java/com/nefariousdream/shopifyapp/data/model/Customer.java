package com.nefariousdream.shopifyapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable
{
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
}