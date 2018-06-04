package com.gang.store.storesystemmanager.ui.stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanggang on 2018/6/3.
 */

public class StockModel implements StockContract.Model{
    @Override
    public List<String> getTabs() {
        List<String> tabs = new ArrayList<>();

        tabs.add("列表");
        tabs.add("图表");

        return tabs;
    }
}
