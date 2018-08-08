package com.gang.store.storesystemmanager.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gang.store.storesystemmanager.ui.fragment.PictureFragment;
import com.gang.store.storesystemmanager.ui.fragment.orderlist.OrderListFragment;
import com.gang.store.storesystemmanager.utils.TUtil;

/**
 * Created by Administrator on 2018/6/21.
 */

public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment {

    public P presenter;
    public M model;
    protected boolean bIsViewCreated;
    protected boolean bIsDataLoaded;

    public static Fragment newInstance(String type) {
        Fragment fragment;
        Bundle arguments = new Bundle();
        arguments.putString("type", type);
        if (type.contains("列表")) {
            fragment = new OrderListFragment();
        } else {
            fragment = new PictureFragment();
        }
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        initView(view);
        presenter = TUtil.getT(this, 0);
        model = TUtil.getT(this, 1);
        if (this instanceof BaseView) presenter.initViewModel(this, model,getContext());

        bIsViewCreated = true;
        if (getUserVisibleHint() && !bIsDataLoaded) {
            loadData();
            bIsDataLoaded = true;
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bIsViewCreated = false;
        bIsDataLoaded = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && bIsViewCreated && !bIsDataLoaded) {
            loadData();
            bIsDataLoaded = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }

    protected abstract void loadData();

    protected abstract void initView(View view);

    protected abstract int getLayoutResId();
}
