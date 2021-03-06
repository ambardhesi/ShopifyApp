package com.nefariousdream.shopifyapp.ordersummary;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nefariousdream.shopifyapp.R;
import com.nefariousdream.shopifyapp.data.model.Order;

import java.util.List;
import java.util.SortedMap;

import static com.google.common.base.Preconditions.checkNotNull;

public class OrdersByProvinceAdapter extends RecyclerView.Adapter<OrdersByProvinceAdapter.OrdersByProvinceViewHolder> {

    private Context mContext;
    private SortedMap<String, List<Order>> mOrdersByProvince;

    public OrdersByProvinceAdapter(Context context, SortedMap<String, List<Order>> ordersByProvince) {
        mContext = checkNotNull(context);
        mOrdersByProvince = checkNotNull(ordersByProvince);
    }

    @NonNull
    @Override
    public OrdersByProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_by_province_row, parent, false);
        return new OrdersByProvinceViewHolder(orderView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersByProvinceViewHolder holder, int position) {
        String province = (String) mOrdersByProvince.keySet().toArray()[position];
        List<Order> ordersForProvince = mOrdersByProvince.get(province);
        holder.mProvinceNameTextView.setText(province);
        OrdersAdapter ordersAdapter = new OrdersAdapter(mContext.getResources(), ordersForProvince);
        holder.mOrdersByProvinceRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.mOrdersByProvinceRecyclerView.setAdapter(ordersAdapter);
    }

    @Override
    public int getItemCount() {
        return mOrdersByProvince.size();
    }

    public class OrdersByProvinceViewHolder extends RecyclerView.ViewHolder {
        public TextView mProvinceNameTextView;
        public RecyclerView mOrdersByProvinceRecyclerView;

        public OrdersByProvinceViewHolder(View itemView) {
            super(itemView);
            mProvinceNameTextView = itemView.findViewById(R.id.province_name_text_view);
            mOrdersByProvinceRecyclerView = itemView.findViewById(R.id.orders_by_province_recycler);
        }
    }
}