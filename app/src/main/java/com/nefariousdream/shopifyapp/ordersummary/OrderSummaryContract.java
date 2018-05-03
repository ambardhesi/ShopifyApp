package com.nefariousdream.shopifyapp.ordersummary;

import com.nefariousdream.shopifyapp.BasePresenter;
import com.nefariousdream.shopifyapp.BaseView;
import com.nefariousdream.shopifyapp.data.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderSummaryContract {
    public interface Presenter extends BasePresenter {
        public void loadOrders();
    }

    public interface View extends BaseView<Presenter> {
        public void showFetchingDataView();
        public void hideFetchingDataView();
        public void showOrders(Data listData);
    }

    public static class Data {
        private HashMap<String, List<Order>> ordersByProvince;
        private List<Order> ordersCreatedIn2017;

        public Data(HashMap<String, List<Order>> ordersByProvince, List<Order> ordersCreatedIn2017) {
            this.ordersByProvince = ordersByProvince;
            this.ordersCreatedIn2017 = ordersCreatedIn2017;
        }
    }
}
