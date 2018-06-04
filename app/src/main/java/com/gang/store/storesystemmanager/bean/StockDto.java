package com.gang.store.storesystemmanager.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/6/3.
 */

public class StockDto implements Serializable{
    private String brandCode;

    private String modelMatCode;

    private BigDecimal fqty;

    private String fdate;

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getModelMatCode() {
        return modelMatCode;
    }

    public void setModelMatCode(String modelMatCode) {
        this.modelMatCode = modelMatCode;
    }

    public BigDecimal getFqty() {
        return fqty;
    }

    public void setFqty(BigDecimal fqty) {
        this.fqty = fqty;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    @Override
    public String toString() {
        return "StockDto{" +
                "brandCode='" + brandCode + '\'' +
                ", modelMatCode='" + modelMatCode + '\'' +
                ", fqty=" + fqty +
                ", fdate='" + fdate + '\'' +
                '}';
    }
}
