package com.gang.store.storesystemmanager.ui.stock;

import com.gang.store.storesystemmanager.base.BaseModel;
import com.gang.store.storesystemmanager.base.BasePresenter;
import com.gang.store.storesystemmanager.base.BaseView;

import java.util.List;

/**
 * Created by wanggang on 2018/6/3.
 */

public interface StockContract {
    interface Model extends BaseModel {
        List<String> getTabs();
    }

    interface View extends BaseView {
        void showTabList(List<String> tabs);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getTabList();
    }
}
