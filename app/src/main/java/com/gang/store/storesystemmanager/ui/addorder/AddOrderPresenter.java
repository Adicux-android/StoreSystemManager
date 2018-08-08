package com.gang.store.storesystemmanager.ui.addorder;


/**
 * Created by Administrator on 2018/7/8.
 */

public class AddOrderPresenter extends AddOrderContract.Presenter {
    @Override
    public void getOrderList() {
        view.showOrderList(model.getOrders());
    }

    @Override
    public void onStart() {
        getOrderList();
    }
}
