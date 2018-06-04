package com.gang.store.storesystemmanager.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gang.store.storesystemmanager.R;
import com.gang.store.storesystemmanager.utils.TUtil;
import com.gang.store.storesystemmanager.view.SwipeBackLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wanggang on 2018/6/3.
 */

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {
    public boolean isNight;
    public P presenter;
    public M model;
    public Context context;

    private SwipeBackLayout swipeBackLayout;
    private ImageView ivShadow;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());
        unbinder = ButterKnife.bind(this);
        context = this;
        presenter = TUtil.getT(this, 0);
        model = TUtil.getT(this, 1);
        this.initView();
        if (this instanceof BaseView) presenter.initViewModel(this, model);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
        unbinder.unbind();

        // 关闭缓存。
        /*//TODO for test crash log
        DiskLruCacheHelper.closeCache();*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 同步日志。
//        DiskLruCacheHelper.syncJournal();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (isNight != SharedPreferencesUtil.isNight()) reload();
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID == R.layout.activity_main || layoutResID == R.layout.activity_flash) {
            super.setContentView(layoutResID);
        } else {
            super.setContentView(getContainer());
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            swipeBackLayout.addView(view);
        }
    }

    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        swipeBackLayout.setOnSwipeBackListener((fa, fs) -> ivShadow.setAlpha(1 - fs));
        return container;
    }


    public abstract int getLayoutId();

    public abstract void initView();
}
