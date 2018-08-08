package com.gang.store.storesystemmanager.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/9.
 */

public class OrderListDto implements Serializable {

    private int mNumberId;
    private String mCategory;
    private String mName;
    private int mSize;
    private int mCount;
    private float mPrice;

    public int getmNumberId() {
        return mNumberId;
    }

    public void setmNumberId(int mNumberId) {
        this.mNumberId = mNumberId;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmSize() {
        return mSize;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public float getmPrice() {
        return mPrice;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    @Override
    public String toString() {
        return "OrderListDto{" +
                "mNumberId=" + mNumberId +
                ", mCategory='" + mCategory + '\'' +
                ", mName='" + mName + '\'' +
                ", mSize=" + mSize +
                ", mCount=" + mCount +
                ", mPrice=" + mPrice +
                '}';
    }
}
