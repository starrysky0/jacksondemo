package com.starry.sky.jacksondemo;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */

public class ProductBean {
    public int id;
    public String name;
    public float price;
    public List<ProductPropertyBean> productProperty;

    public ProductBean() {
    }

    public ProductBean(int id, String name, float price, List<ProductPropertyBean> productProperty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productProperty = productProperty;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productProperty=" + productProperty +
                '}';
    }
}

