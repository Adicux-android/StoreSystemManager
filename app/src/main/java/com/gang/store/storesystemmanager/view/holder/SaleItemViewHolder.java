package com.gang.store.storesystemmanager.view.holder;

import android.view.View;
import android.widget.TextView;

import com.gang.store.storesystemmanager.R;
import com.gang.store.storesystemmanager.base.BaseViewHolder;
import com.gang.store.storesystemmanager.bean.StockDto;

/**
 * Created by Administrator on 2018/6/3.
 */

public class SaleItemViewHolder extends BaseViewHolder<StockDto> {

    TextView tv_brand, tv_model, tv_qty, tv_price;

    public SaleItemViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public int getType() {
        return R.layout.list_item_card_main;
    }

    @Override
    public void onBindViewHolder(View view, StockDto obj) {
        tv_brand.setText(obj.getBrandCode());
        tv_model.setText(obj.getModelMatCode());
        tv_qty.setText(obj.getFqty().toString());
        tv_price.setText(obj.getFdate());

    }
}
