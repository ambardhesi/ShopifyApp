package com.nefariousdream.shopifyapp.ordersummary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.nefariousdream.shopifyapp.Injection;
import com.nefariousdream.shopifyapp.R;
import com.nefariousdream.shopifyapp.data.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class OrderSummaryActivity extends AppCompatActivity implements OrderSummaryContract.View {

    private OrderSummaryContract.Presenter mOrderSummaryPresenter;

    private ShimmerFrameLayout mShimmerLayout;
    private View mFetchingDataView;
    private View mContentScrollView;

    private View mOrdersByProvinceTextView;
    private RecyclerView mOrdersByProvinceRecyclerView;
    private RecyclerView mOrdersCreatedIn2017RecyclerView;

    private OrdersByProvinceCountAdapter mOrdersByProvinceCountAdapter;
    private OrdersAdapter mOrdersCreatedIn2017Adapter;
    private TextView mOrdersCreatedIn2017CountTextView;

    private SortedMap<String, List<Order>> mOrdersByProvince = new TreeMap<>();
    private List<Order> mOrdersCreatedIn2017 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        mOrderSummaryPresenter = new OrderSummaryPresenter(Injection.provideTasksRepository(getApplicationContext()), this);

        setUpViews();
        setUpRecyclerViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mOrderSummaryPresenter != null) {
            mOrderSummaryPresenter.start();
        }
    }

    private void setUpViews() {
        mShimmerLayout = findViewById(R.id.shimmer_layout);
        mFetchingDataView = findViewById(R.id.loading_data_layout);
        mContentScrollView = findViewById(R.id.content_scroll_view);
        mOrdersByProvinceTextView = findViewById(R.id.orders_by_province_text);
        mOrdersByProvinceRecyclerView = findViewById(R.id.orders_by_province_recycler);
        mOrdersCreatedIn2017RecyclerView = findViewById(R.id.orders_created_in_2017_recycler);
        mOrdersCreatedIn2017CountTextView = findViewById(R.id.orders_created_in_2017_count_text);

        mOrdersByProvinceTextView.setOnClickListener(view -> {
            mOrderSummaryPresenter.ordersByProvinceTitleClicked();
        });
    }

    private void setUpRecyclerViews() {
        mOrdersByProvinceCountAdapter = new OrdersByProvinceCountAdapter(getResources(), mOrdersByProvince);
        mOrdersByProvinceRecyclerView.setAdapter(mOrdersByProvinceCountAdapter);
        mOrdersByProvinceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOrdersByProvinceRecyclerView.setNestedScrollingEnabled(false);

        mOrdersCreatedIn2017Adapter = new OrdersAdapter(getResources(), mOrdersCreatedIn2017);
        mOrdersCreatedIn2017RecyclerView.setAdapter(mOrdersCreatedIn2017Adapter);
        mOrdersCreatedIn2017RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOrdersCreatedIn2017RecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void setPresenter(OrderSummaryContract.Presenter presenter) {
        mOrderSummaryPresenter = presenter;
    }


    @Override
    public void showFetchingDataView() {
        mShimmerLayout.startShimmerAnimation();
    }

    @Override
    public void hideFetchingDataView() {
        mShimmerLayout.stopShimmerAnimation();
        mFetchingDataView.setVisibility(View.GONE);
        mContentScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showOrdersByProvinceDialog() {
        HashMap<String, List<Order>> ordersByProvinceHashMap = new HashMap<>(mOrdersByProvince);
        OrdersByProvinceDialog ordersByProvinceDialog = OrdersByProvinceDialog.newInstance(ordersByProvinceHashMap);
        ordersByProvinceDialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void showOrders(OrderSummaryContract.Data listData) {
        mOrdersByProvince.clear();
        mOrdersByProvince.putAll(listData.ordersByProvince);
        mOrdersByProvinceCountAdapter.notifyDataSetChanged();

        mOrdersCreatedIn2017.clear();
        mOrdersCreatedIn2017.addAll(listData.ordersCreatedIn2017);
        mOrdersCreatedIn2017Adapter.notifyDataSetChanged();

        mOrdersCreatedIn2017CountTextView.setText(getString(R.string.orders_count, listData.ordersCreatedIn2017.size()));
    }
}
