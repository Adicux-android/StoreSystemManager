package com.gang.store.storesystemmanager.ui.fragment.orderlist;

import android.content.Context;
import android.database.Cursor;

import com.gang.store.storesystemmanager.bean.OrderListDto;
import com.gang.store.storesystemmanager.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */

public class OrderListModel implements OrderListContract.Model {
    @Override
    public List<OrderListDto> getOrderList(Context context) {
        List<OrderListDto> orderListDtos = new ArrayList<>();
        DBUtils dbUtils = DBUtils.getInstance(context);
        Cursor cursor = dbUtils.selectData(null,null,null,null,null,null);
        while (cursor.moveToNext()) {
            OrderListDto dto = new OrderListDto();
            int mOrderNumberId = cursor.getInt(cursor.getColumnIndex("order_id"));
            String mOrderCategory = cursor.getString(cursor.getColumnIndex("category"));
            String mOrderName = cursor.getString(cursor.getColumnIndex("name"));
            int mOrderSize = cursor.getInt(cursor.getColumnIndex("size"));
            int mOrderCount = cursor.getInt(cursor.getColumnIndex("order_count"));
            float mOrderPrice = cursor.getFloat(cursor.getColumnIndex("order_price"));
            dto.setmNumberId(mOrderNumberId);
            dto.setmName(mOrderName);
            dto.setmPrice(mOrderPrice);
            dto.setmCategory(mOrderCategory);
            dto.setmSize(mOrderSize);
            dto.setmCount(mOrderCount);

            orderListDtos.add(dto);
        }
        cursor.close();
        dbUtils.close();
        return orderListDtos;
    }
}
