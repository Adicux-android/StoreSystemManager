package com.gang.store.storesystemmanager.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gang.store.storesystemmanager.constant.SystemConstant;
import com.gang.store.storesystemmanager.utils.TUtil;
import com.gang.store.storesystemmanager.view.TRecyclerView;

/**
 * Created by wanggang on 2018/6/3.
 */

public class BaseListFragment extends Fragment {
    private TRecyclerView tRecyclerView;

    /**
     * @param viewHolder 传入ViewHolder的类名
     * @return
     */
    public static BaseListFragment newInstance(Class<? extends BaseViewHolder> viewHolder, String type) {
        Bundle arguments = new Bundle();
        arguments.putString(SystemConstant.VH_CLASS, viewHolder.getCanonicalName());
        arguments.putString("type", type);
        BaseListFragment fragment = new BaseListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tRecyclerView = new TRecyclerView(getContext()).setParam("type", getArguments().getString("type"))
                .setView(TUtil.forName(getArguments().getString(SystemConstant.VH_CLASS)));
        return tRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (tRecyclerView != null) tRecyclerView.fetch();
    }
}
