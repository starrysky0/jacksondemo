package com.starry.sky.jacksondemo;

/**
 * Created by Administrator on 2016/12/25.
 */

public class ProductPropertyBean {
    /**
     * id : 1
     * k : 颜色
     * v : 红色
     */
    public int id;
    public String k;
    public String v;

    public ProductPropertyBean() {
    }

    public ProductPropertyBean(int id, String k, String v) {
        this.id = id;
        this.k = k;
        this.v = v;
    }

    @Override
    public String toString() {
        return "ProductPropertyBean{" +
                "id=" + id +
                ", k='" + k + '\'' +
                ", v='" + v + '\'' +
                '}';
    }
}
