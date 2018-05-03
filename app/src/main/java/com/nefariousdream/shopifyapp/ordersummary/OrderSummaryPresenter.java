package com.nefariousdream.shopifyapp.ordersummary;

import com.nefariousdream.shopifyapp.data.model.Order;
import com.nefariousdream.shopifyapp.data.source.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class OrderSummaryPresenter implements OrderSummaryContract.Presenter {
    private Repository mRepository;
    private OrderSummaryContract.View mOrderSummaryView;

    public OrderSummaryPresenter(Repository repository, OrderSummaryContract.View view) {
        mRepository = repository;
        mOrderSummaryView = view;
    }

    @Override
    public void start() {
        mOrderSummaryView.showFetchingDataView();
        loadOrders();
    }

    @Override
    public void loadOrders() {
        SortedMap<String, List<Order>> ordersByProvince = new TreeMap<>();
        List<Order> ordersCreatedIn2017 = new ArrayList<>();

        mRepository.getOrders(orders -> {
            mOrderSummaryView.hideFetchingDataView();

            for (Order order : orders) {
                if (order.shippingAddress != null) {
                    String orderProvince = order.shippingAddress.province;
                    if (ordersByProvince.get(orderProvince) == null) {
                        ordersByProvince.put(orderProvince, new ArrayList<>());
                    }
                    ordersByProvince.get(orderProvince).add(order);
                }

                if (order.createdAt != null && !order.createdAt.isEmpty()) {
                    if (order.createdAt.substring(0, 4).equals("2017")) {
                        ordersCreatedIn2017.add(order);
                    }
                }
            }
            processOrders(ordersByProvince, ordersCreatedIn2017);
        });
    }

    @Override
    public void ordersByProvinceTitleClicked() {
        mOrderSummaryView.showOrdersByProvinceDialog();
    }

    private void processOrders(SortedMap<String, List<Order>> ordersByProvince, List<Order> ordersCreatedIn2017) {
        OrderSummaryContract.Data data = new OrderSummaryContract.Data(ordersByProvince, ordersCreatedIn2017);
        mOrderSummaryView.showOrders(data);
    }
}
