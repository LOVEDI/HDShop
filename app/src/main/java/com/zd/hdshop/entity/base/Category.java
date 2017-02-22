package com.zd.hdshop.entity.base;

import com.zd.hdshop.entity.base.BaseBean;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class Category extends BaseBean {
    private String name;


    public Category() {
    }
    public Category(long id) {
        this.id = id ;
    }
    public Category(String name) {
        this.name = name;
    }
    public Category(String name,long id) {
        this.name = name;
        this.id = id ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
