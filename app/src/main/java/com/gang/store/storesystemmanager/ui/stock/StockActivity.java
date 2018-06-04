package com.gang.store.storesystemmanager.ui.stock;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.gang.store.storesystemmanager.R;
import com.gang.store.storesystemmanager.adapter.FragmentAdapter;
import com.gang.store.storesystemmanager.base.BaseActivity;
import com.gang.store.storesystemmanager.base.BaseListFragment;
import com.gang.store.storesystemmanager.view.holder.SaleItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;

/**
 * Created by wanggang on 2018/6/3.
 */

public class StockActivity extends BaseActivity<StockPresenter, StockModel> implements StockContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showTabList(List<String> tabList) {
        List<Fragment> fragments = new ArrayList<>();
        Observable.from(tabList).subscribe(tab -> fragments.add(BaseListFragment.newInstance(SaleItemViewHolder.class, tab)));
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments, tabList));
        tabs.setupWithViewPager(viewPager);
    }
}
