package com.gang.store.storesystemmanager.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gang.store.storesystemmanager.ui.fragment.NewsFragment;
import com.gang.store.storesystemmanager.ui.fragment.PictureFragment;

/**
 * Created by Administrator on 2018/6/21.
 */

public abstract class BaseFragment extends Fragment {

    protected boolean bIsViewCreated;
    protected boolean bIsDataLoaded;

    public static Fragment newInstance(String type) {
        Fragment fragment;
        Bundle arguments = new Bundle();
        arguments.putString("type", type);
        if (type.contains("列表")) {
            fragment = new NewsFragment();
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

    protected abstract void loadData();

    protected abstract void initView(View view);

    protected abstract int getLayoutResId();
}
