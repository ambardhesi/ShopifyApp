package com.nefariousdream.shopifyapp.data.source;

import android.support.annotation.NonNull;

import com.nefariousdream.shopifyapp.data.model.Order;
import com.nefariousdream.shopifyapp.data.source.remote.RemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class Repository {
    private static Repository INSTANCE = null;

    private RemoteDataSource remoteDataSource;

    private boolean doesDataExist;

    public static Repository getInstance(RemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteDataSource);
        }
        return INSTANCE;
    }

    private Repository(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
    }

    public void getOrders(LoadDataCallback<Order> callback) {
        checkNotNull(callback);
        if (!doesDataExist) {
            getOrdersFromRemoteDataSource(list -> {
                callback.onDataLoaded(list);
                doesDataExist = true;
            });
        }
    }

    private void getOrdersFromRemoteDataSource(LoadDataCallback<Order> callback) {
        remoteDataSource.getOrders(callback);
    }
}
