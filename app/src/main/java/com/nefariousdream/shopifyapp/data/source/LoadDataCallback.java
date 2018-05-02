package com.nefariousdream.shopifyapp.data.source;

import java.util.List;

public interface LoadDataCallback <T> {
    void onDataLoaded(List<T> list);
}
