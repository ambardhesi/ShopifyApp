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

import static com.google.common.base.Preconditions.checkNotNull;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private Resources mResources;
    private List<Order> mOrders;

    public OrdersAdapter(Resources resources, List<Order> ordersByYear) {
        mResources = checkNotNull(resources);
        mOrders = checkNotNull(ordersByYear);
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row, parent, false);
        return new OrdersViewHolder(orderView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        Order order = mOrders.get(position);
        holder.mOrderNumber.setText(mResources.getString(R.string.order_number, order.orderNumber));
        holder.mTotalPriceUSD.setText(mResources.getString(R.string.currency, order.totalPriceUsd));
        if (order.customer != null) {
            holder.mCustomerName.setText(mResources.getString(R.string.customer_name, order.customer.firstName, order.customer.lastName));
        } else {
            holder.mCustomerName.setVisibility(View.GONE);
        }
        holder.mOrderStatusUrl.setText(order.orderStatusUrl);
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {
        public TextView mOrderNumber;
        public TextView mTotalPriceUSD;
        public TextView mCustomerName;
        public TextView mOrderStatusUrl;

        public OrdersViewHolder(View itemView) {
            super(itemView);
            mOrderNumber = itemView.findViewById(R.id.order_number_text);
            mTotalPriceUSD = itemView.findViewById(R.id.total_price_text);
            mCustomerName = itemView.findViewById(R.id.customer_name_text);
            mOrderStatusUrl = itemView.findViewById(R.id.order_status_url_text);
        }
    }
}