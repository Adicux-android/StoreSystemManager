package com.gang.store.storesystemmanager.ui.stock;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.gang.store.storesystemmanager.R;
import com.gang.store.storesystemmanager.adapter.FragmentAdapter;
import com.gang.store.storesystemmanager.base.BaseActivity;
import com.gang.store.storesystemmanager.base.BaseFragment;
import com.gang.store.storesystemmanager.ui.addorder.AddOrderActivity;
import com.gang.store.storesystemmanager.utils.DialogUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import rx.Observable;

import static com.gang.store.storesystemmanager.R.id.date_end;
import static com.gang.store.storesystemmanager.R.id.date_start;
import static com.gang.store.storesystemmanager.R.id.fab;
import static com.gang.store.storesystemmanager.R.id.viewPager;

/**
 * Created by wanggang on 2018/6/3.
 */

public class StockActivity extends BaseActivity<StockPresenter, StockModel> implements StockContract.View,View.OnClickListener{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.tabs)
    TabLayout mTabs;

    @BindView(viewPager)
    ViewPager mViewPager;

    @BindView(fab)
    FloatingActionButton mFab;

    private Button mDateStart;
    private Button mDateEnd;
    private View mView;
    private int mYear,mMonth,mDay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mFab.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        getDate();
    }

    //获取当前日期
    private void getDate() {
        Calendar cal= Calendar.getInstance();
        mYear=cal.get(Calendar.YEAR);       //获取年月日时分秒
        mMonth=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        mDay=cal.get(Calendar.DAY_OF_MONTH);
        Log.d("wang","mYear=====" + mYear + ",mMonth==" + mMonth + ",mDay===" + mDay);
    }

    @Override
    public void showTabList(List<String> tabList) {
        List<Fragment> fragments = new ArrayList<>();

        Observable.from(tabList).subscribe(tab -> fragments.add(BaseFragment.newInstance(tab)));
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments, tabList));
        mTabs.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) //得到被点击的item的itemId
        {
            case  R.id.action_search :
                mView = LayoutInflater.from(this).inflate(R.layout.dialog_content,null);
                mDateStart = (Button) mView.findViewById(R.id.date_start);
                mDateEnd = (Button) mView.findViewById(R.id.date_end);
                mDateStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDateDialog(mDateStart);
                    }
                });
                mDateEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDateDialog(mDateEnd);
                    }
                });
                DialogUtils.getInstance().showDialog(this, getString(R.string.filter_date), mView, true, new DialogUtils.DialogCallBack() {
                    @Override
                    public void excuteEvent() {
                        Log.d("wang","====" + mDateStart.getText().toString() + ",==" + mDateEnd.getText().toString());
                        if (isDateOneBigger((String) mDateStart.getText(), (String) mDateEnd.getText())) {
                            Toast.makeText(StockActivity.this,"请选择合适的时间",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.action_order:
                Intent intent = new Intent(this, AddOrderActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case date_start:
                break;
            case date_end:
                break;
            default:
                break;
        }
    }

    private void showDateDialog(Button button) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                button.setText(year+"-"+(++month)+"-"+dayOfMonth);
            }
        },mYear,mMonth,mDay);
        datePickerDialog.show();
    }

    public static boolean isDateOneBigger(String str1, String str2) {
        boolean isBigger = false;
        String[] strings1 = str1.split("-");
        String[] strings2 = str2.split("-");
        Log.d("wang","str1===" + strings1.length + "," + strings1.toString());
        Log.d("wang","str2===" + strings2.length + "," + strings2.toString());
        for (String s:strings1) {
            Log.d("wanggang","s====" + s.toString());
        }
        for (String s:strings2) {
            Log.d("wanggang","s2====" + s.toString());
        }
        if (Integer.valueOf(strings1[0]) > Integer.valueOf(strings2[0])) {
            Log.d("gang","=============000");
            isBigger = true;
        }
        else{
            if ((Integer.valueOf(strings1[1]) > Integer.valueOf(strings2[1]))) {
                Log.d("gang","=============111");
                isBigger = true;
            }
            else {
                if ((Integer.valueOf(strings1[2]) > Integer.valueOf(strings2[2]))) {
                    Log.d("gang","=============222");
                    isBigger = true;
                } else {
                    Log.d("gang","=============333");
                    isBigger = false;
                }
            }
        }
        return isBigger;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
