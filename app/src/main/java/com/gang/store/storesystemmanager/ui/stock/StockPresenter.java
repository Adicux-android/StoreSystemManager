package com.gang.store.storesystemmanager.ui.stock;

/**
 * Created by wanggang on 2018/6/3.
 */

public class StockPresenter extends StockContract.Presenter{
    @Override
    public void getTabList() {
        view.showTabList(model.getTabs());
    }

    @Override
    public void onStart() {
        getTabList();
    }
}
