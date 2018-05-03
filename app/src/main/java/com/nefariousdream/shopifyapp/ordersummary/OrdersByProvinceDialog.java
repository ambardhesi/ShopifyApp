package com.nefariousdream.shopifyapp.ordersummary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nefariousdream.shopifyapp.Injection;
import com.nefariousdream.shopifyapp.R;
import com.nefariousdream.shopifyapp.data.model.Order;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class OrdersByProvinceDialog extends DialogFragment {
    public static String ORDERS_BY_PROVINCE = "orders_by_province";
    private Context mContext;

    private HashMap<String, List<Order>> mOrdersByProvince = new HashMap<>();
    private RecyclerView mOrdersByProvinceRecyclerView;
    private OrdersByProvinceAdapter mOrdersByProvinceAdapter;

    public static OrdersByProvinceDialog newInstance(HashMap<String, List<Order>> ordersByProvince) {
        Bundle args = new Bundle();
        OrdersByProvinceDialog fragment = new OrdersByProvinceDialog();
        args.putSerializable(ORDERS_BY_PROVINCE, ordersByProvince);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_orders_by_province, container);

        mOrdersByProvince = (HashMap<String, List<Order>>)getArguments().getSerializable(ORDERS_BY_PROVINCE);

        setUpRecyclerView(view);

        return view;
    }

    private void setUpRecyclerView(View view) {
        mOrdersByProvinceRecyclerView = view.findViewById(R.id.province_recycler);
        mOrdersByProvinceAdapter = new OrdersByProvinceAdapter(mContext, new TreeMap<>(mOrdersByProvince));
        mOrdersByProvinceRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mOrdersByProvinceRecyclerView.setAdapter(mOrdersByProvinceAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
