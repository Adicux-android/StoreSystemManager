package com.gang.store.storesystemmanager.ui.addorder;

import com.gang.store.storesystemmanager.base.BaseModel;
import com.gang.store.storesystemmanager.base.BasePresenter;
import com.gang.store.storesystemmanager.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/7/8.
 */

public interface AddOrderContract {
    interface Model extends BaseModel {
        List<String> getOrders();
    }

    interface View extends BaseView {
        void showOrderList(List<String> tabs);
    }

    abstract class Presenter extends BasePresenter<Model,View> {
        public abstract void getOrderList();
    }
}
