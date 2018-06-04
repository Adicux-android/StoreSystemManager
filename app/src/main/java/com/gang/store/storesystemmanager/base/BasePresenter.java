package com.gang.store.storesystemmanager.base;

import android.content.Context;

/**
 * Created by Administrator on 2018/6/3.
 */

public abstract class BasePresenter<M, V> {

    public Context context;
    public M model;
    public V view;
//    public RxManager rxManager = new RxManager();

    public void initViewModel(V v, M m) {
        this.view = v;
        this.model = m;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy() {
//        rxManager.clear();
    }
}
