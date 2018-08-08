package com.gang.store.storesystemmanager.ui.addorder;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gang.store.storesystemmanager.R;
import com.gang.store.storesystemmanager.base.BaseActivity;
import com.gang.store.storesystemmanager.utils.DBUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/8.
 */

public class AddOrderActivity extends BaseActivity<AddOrderPresenter,AddOrderModel> implements AddOrderContract.View,View.OnClickListener{
    @BindView(R.id.btn_cancer)
    Button mBtnCancer;
    @BindView(R.id.btn_store)
    Button mBtnStore;
    @BindView(R.id.number)
    EditText mOrderNumberId;
    @BindView(R.id.category)
    EditText mOrderCategory;
    @BindView(R.id.order_name)
    EditText mOrderName;
    @BindView(R.id.order_size)
    EditText mOrderSize;
    @BindView(R.id.order_number)
    EditText mOrderCount;
    @BindView(R.id.order_price)
    EditText mOrderPrice;


    private int mNumberId;
    private String mCategory;
    private String mName;
    private int mSize;
    private int mCount;
    private float mPrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mNumberId = savedInstanceState.getInt("order_id", mNumberId);
            mName = savedInstanceState.getString("name", mName);
            mCategory = savedInstanceState.getString("category",mCategory);
            mSize = savedInstanceState.getInt("size", mSize);
            mCount = savedInstanceState.getInt("order_count", mCount);
            mPrice = savedInstanceState.getFloat("order_price", mPrice);

            mOrderNumberId.setText(mNumberId);
            mOrderCategory.setText(mCategory);
            mOrderName.setText(mName);
            mOrderSize.setText(mSize);
            mOrderCount.setText(mCount);
            mOrderPrice.setText((int) mPrice);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_addorder_content;
    }

    @Override
    public void initView() {
        mBtnCancer.setOnClickListener(this);
        mBtnStore.setOnClickListener(this);
    }

    @Override
    public void showOrderList(List<String> tabs) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancer:
                finish();
                break;
            case R.id.btn_store:
                getOrderInformations();
                executeInsert();
                finish();
                break;
            default:
                break;
        }
    }

    private void getOrderInformations() {
        if (mOrderNumberId.getText() == null || mOrderCount.getText() == null) {
            Toast.makeText(this,"订单号或订单数量不能为空",Toast.LENGTH_SHORT);
            return;
        }
        if (mOrderSize.getText() == null || mOrderPrice.getText() == null) {
            Toast.makeText(this,"种类大小或单价不能为空",Toast.LENGTH_SHORT);
            return;
        }
        mNumberId = Integer.parseInt(mOrderNumberId.getText().toString());
        mCategory = mOrderCategory.getText().toString();
        mName = mOrderName.getText().toString();
        mSize = Integer.parseInt(mOrderSize.getText().toString());
        mCount = Integer.parseInt(mOrderCount.getText().toString());
        mPrice = Float.parseFloat(mOrderPrice.getText().toString());
    }

    private void executeInsert() {
        DBUtils dbUtils = DBUtils.getInstance(this);
        ContentValues values = new ContentValues();
        values.put("order_id", mNumberId);
        values.put("name", mName);
        values.put("category",mCategory);
        values.put("size", mSize);
        values.put("order_count", mCount);
        values.put("order_price", mPrice);
        dbUtils.insertData(values);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle bundle = new Bundle();
        bundle.putInt("order_id", mNumberId);
        bundle.putString("name", mName);
        bundle.putString("category",mCategory);
        bundle.putInt("size", mSize);
        bundle.putInt("order_count", mCount);
        bundle.putFloat("order_price", mPrice);
        outState.putAll(bundle);
    }
}
