package com.zd.hdshop.entity;

import com.zd.hdshop.entity.base.Category;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class HomeCategory extends Category {
    private int imgBig;
    private int imgSmallTop;
    private int imgSmallBottom;
    public HomeCategory(String name,int imgBig,int imgSmallBottom,int imgSmallTop){
        super(name);
        this.imgBig = imgBig;
        this.imgSmallTop = imgSmallTop;
        this.imgSmallBottom = imgSmallBottom;
    }
    public int getImgBig() {
        return imgBig;
    }

    public void setImgBig(int imgBig) {
        this.imgBig = imgBig;
    }

    public int getImgSmallBottom() {
        return imgSmallBottom;
    }

    public void setImgSmallBottom(int imgSmallBottom) {
        this.imgSmallBottom = imgSmallBottom;
    }

    public int getImgSmallTop() {
        return imgSmallTop;
    }

    public void setImgSmallTop(int imgSmallTop) {
        this.imgSmallTop = imgSmallTop;
    }
}
