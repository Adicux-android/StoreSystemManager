package com.gang.store.storesystemmanager.ui.fragment.orderlist;

/**
 * Created by Administrator on 2018/7/9.
 */

public class OrderListPresenter extends OrderListContract.Presenter {
    @Override
    public void getOrdersNeed() {
        view.showOrderList(model.getOrderList(context));
    }

    @Override
    public void onStart() {
        getOrdersNeed();
    }
}
