package com.nefariousdream.shopifyapp.ordersummary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nefariousdream.shopifyapp.BaseView;
import com.nefariousdream.shopifyapp.R;

public class OrderSummaryActivity extends AppCompatActivity implements OrderSummaryContract.View {

    private OrderSummaryContract.Presenter mOrderSummaryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
    }

    @Override
    public void setPresenter(OrderSummaryContract.Presenter presenter) {
        mOrderSummaryPresenter = presenter;
    }
}
