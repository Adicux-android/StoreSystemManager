package com.gang.store.storesystemmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gang.store.storesystemmanager.R;
import com.gang.store.storesystemmanager.bean.OrderListDto;

import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<OrderListDto> mOrders;

    public OrderListAdapter(Context context,List<OrderListDto> orders) {
        this.mInflater=LayoutInflater.from(context);
        this.mOrders = orders;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_orderlist,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OrderListDto orderListDto = mOrders.get(position);
        holder.mOrderNumberId.setText(orderListDto.getmNumberId() + "");
        holder.mOrderCategory.setText(orderListDto.getmCategory());
        holder.mOrderName.setText(orderListDto.getmName());
        holder.mOrderSize.setText(orderListDto.getmSize() + "");
        holder.mOrderCount.setText(orderListDto.getmCount() + "");
        holder.mOrderPrice.setText((int) orderListDto.getmPrice() + "");
    }

    @Override
    public int getItemCount() {
        if (mOrders.size() == 0 || mOrders == null) {
            return 0;
        }
        return mOrders.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mOrderNumberId,mOrderCategory,mOrderName,mOrderSize,mOrderCount,mOrderPrice;
        public MyViewHolder(View view) {
            super(view);
            mOrderNumberId = (TextView)view.findViewById(R.id.orderNumberId);
            mOrderCategory = (TextView)view.findViewById(R.id.orderCategory);
            mOrderName = (TextView)view.findViewById(R.id.orderName);
            mOrderSize = (TextView)view.findViewById(R.id.orderSize);
            mOrderCount = (TextView)view.findViewById(R.id.orderCount);
            mOrderPrice = (TextView)view.findViewById(R.id.orderPrice);
        }
    }
}
