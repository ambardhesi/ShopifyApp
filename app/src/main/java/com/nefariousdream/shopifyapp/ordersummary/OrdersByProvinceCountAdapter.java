package com.nefariousdream.shopifyapp.ordersummary;

import android.content.res.Resources;
import android.support.annotation.NonNull;
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

public class OrdersByProvinceCountAdapter extends RecyclerView.Adapter<OrdersByProvinceCountAdapter.OrdersByProvinceCountViewHolder> {

    private Resources mResources;
    private SortedMap<String, List<Order>> mOrdersByProvince;

    public OrdersByProvinceCountAdapter(Resources resources, SortedMap<String, List<Order>> ordersByProvince) {
        mResources = checkNotNull(resources);
        mOrdersByProvince = checkNotNull(ordersByProvince);
    }

    @NonNull
    @Override
    public OrdersByProvinceCountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_by_province_count_row, parent, false);
        return new OrdersByProvinceCountViewHolder(orderView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersByProvinceCountViewHolder holder, int position) {
        String province = (String) mOrdersByProvince.keySet().toArray()[position];
        List<Order> ordersForProvince = mOrdersByProvince.get(province);
        holder.mProvinceNameTextView.setText(province);
        holder.mOrdersCountTextView.setText(mResources.getString(R.string.orders_count_by_province, ordersForProvince.size()));
    }

    @Override
    public int getItemCount() {
        return mOrdersByProvince.size();
    }

    public class OrdersByProvinceCountViewHolder extends RecyclerView.ViewHolder {
        public TextView mProvinceNameTextView;
        public TextView mOrdersCountTextView;

        public OrdersByProvinceCountViewHolder(View itemView) {
            super(itemView);
            mProvinceNameTextView = itemView.findViewById(R.id.province_name_text_view);
            mOrdersCountTextView = itemView.findViewById(R.id.orders_count_text_view);
        }
    }
}