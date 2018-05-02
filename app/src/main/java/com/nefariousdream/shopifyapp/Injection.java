package com.nefariousdream.shopifyapp;

import android.content.Context;
import android.support.annotation.NonNull;


import com.nefariousdream.shopifyapp.data.source.Repository;
import com.nefariousdream.shopifyapp.data.source.remote.RemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static Repository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        return Repository.getInstance(RemoteDataSource.getInstance());
    }
}
