package com.gang.store.storesystemmanager.ui.fragment.orderlist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gang.store.storesystemmanager.R;
import com.gang.store.storesystemmanager.adapter.OrderListAdapter;
import com.gang.store.storesystemmanager.base.BaseFragment;
import com.gang.store.storesystemmanager.bean.OrderListDto;

import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */

public class OrderListFragment extends BaseFragment<OrderListPresenter,OrderListModel> implements OrderListContract.View {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private OrderListAdapter mAdapter;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(getContext());
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.orderlist_content;
    }

    @Override
    public void showOrderList(List<OrderListDto> orders) {
        mAdapter = new OrderListAdapter(getContext(),orders);
        mRecyclerView.setAdapter(mAdapter);
    }
}
