package com.gang.store.storesystemmanager.ui.fragment.orderlist;

import android.content.Context;

import com.gang.store.storesystemmanager.base.BaseModel;
import com.gang.store.storesystemmanager.base.BasePresenter;
import com.gang.store.storesystemmanager.base.BaseView;
import com.gang.store.storesystemmanager.bean.OrderListDto;

import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */

public interface OrderListContract {
    interface Model extends BaseModel {
        List<OrderListDto> getOrderList(Context context);
    }

    interface View extends BaseView {
        void showOrderList(List<OrderListDto> orders);
    }

    abstract class Presenter extends BasePresenter<Model,View> {
        public abstract void getOrdersNeed();
    }
}
