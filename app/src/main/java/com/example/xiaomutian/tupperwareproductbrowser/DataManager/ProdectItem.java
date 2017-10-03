package com.example.xiaomutian.tupperwareproductbrowser.DataManager;

import org.litepal.crud.DataSupport;

/**
 * Created by Xiaomutian on 2017/10/3.
 */

public class ProdectItem extends DataSupport {
    private String m_productName;
    private double m_price;
    private String m_desp;

    public String getProductName() {
        return m_productName;
    }

    public void setProductName(String m_productName) {
        this.m_productName = m_productName;
    }

    public double getPrice() {
        return m_price;
    }

    public void setPrice(double m_price) {
        this.m_price = m_price;
    }

    public String getDesp() {
        return m_desp;
    }

    public void setDesp(String m_desp) {
        this.m_desp = m_desp;
    }
}
