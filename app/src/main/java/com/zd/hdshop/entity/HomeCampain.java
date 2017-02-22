package com.zd.hdshop.entity;

import com.zd.hdshop.entity.base.BaseBean;
import com.zd.hdshop.entity.base.Campaign;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class HomeCampain extends BaseBean{
    private String title;
    private Campaign cpOne;
    private Campaign cpTwo;
    private Campaign cpThree;
    public Campaign getCpThree() {
        return cpThree;
    }

    public void setCpThree(Campaign cpThree) {
        this.cpThree = cpThree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Campaign getCpTwo() {
        return cpTwo;
    }

    public void setCpTwo(Campaign cpTwo) {
        this.cpTwo = cpTwo;
    }

    public Campaign getCpOne() {
        return cpOne;
    }

    public void setCpOne(Campaign cpOne) {
        this.cpOne = cpOne;
    }


}
