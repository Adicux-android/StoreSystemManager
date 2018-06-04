package com.gang.store.storesystemmanager.view.holder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gang.store.storesystemmanager.R;
import com.gang.store.storesystemmanager.base.BaseViewHolder;

/**
 * Created by Administrator on 2018/6/3.
 */

public class CommonFooterViewHolder extends BaseViewHolder<Object> {
    public ProgressBar progressBar;
    public TextView tv_state;
    public static final int LAYOUT_TYPE = R.layout.list_footer_view;

    public CommonFooterViewHolder(View view) {
        super(view);
    }

    @Override
    public int getType() {
        return LAYOUT_TYPE;
    }

    @Override
    public void onBindViewHolder(View view, Object o) {
        boolean isHasMore = (null == o ? false : true);
        progressBar.setVisibility(isHasMore ? View.VISIBLE : View.GONE);
        tv_state.setText(isHasMore ? "正在加载..." : "已经到底");
    }
}
