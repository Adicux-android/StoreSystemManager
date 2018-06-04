package com.gang.store.storesystemmanager.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gang.store.storesystemmanager.utils.ViewUtil;

/**
 * Created by Administrator on 2018/6/3.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public Context context;

    public BaseViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ViewUtil.autoFind(this, itemView);
    }

    /**
     * ViewHolder的Type，同时也是它的LayoutId
     *
     * @return
     */
    public abstract int getType();

    /**
     * 绑定ViewHolder
     *
     * @return
     */
    public abstract void onBindViewHolder(View view, T obj);
}

