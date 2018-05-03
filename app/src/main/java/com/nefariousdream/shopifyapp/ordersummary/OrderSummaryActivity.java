package com.nefariousdream.shopifyapp.ordersummary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.nefariousdream.shopifyapp.BaseView;
import com.nefariousdream.shopifyapp.Injection;
import com.nefariousdream.shopifyapp.R;

public class OrderSummaryActivity extends AppCompatActivity implements OrderSummaryContract.View {

    private OrderSummaryContract.Presenter mOrderSummaryPresenter;

    private ShimmerFrameLayout mShimmerLayout;
    private View mFetchingDataView;
    private View mContentScrollView;

    private RecyclerView mOrdersByProvinceRecyclerView;
    private RecyclerView mOrdersCreatedIn2017RecyclerView;

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
        mOrdersByProvinceRecyclerView = findViewById(R.id.orders_by_province_recycler);
        mOrdersCreatedIn2017RecyclerView = findViewById(R.id.orders_created_in_2017_recycler);

    }

    private void setUpRecyclerViews() {

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
    public void showOrders(OrderSummaryContract.Data listData) {
        // TO DO implement
    }
}
