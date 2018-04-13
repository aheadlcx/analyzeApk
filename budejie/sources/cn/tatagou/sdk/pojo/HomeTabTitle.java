package cn.tatagou.sdk.pojo;

import java.io.Serializable;

public class HomeTabTitle implements Serializable {
    private TabTitle cart;
    private TabTitle home;
    private TabTitle mine;

    public TabTitle getHome() {
        return this.home;
    }

    public void setHome(TabTitle tabTitle) {
        this.home = tabTitle;
    }

    public TabTitle getMine() {
        return this.mine;
    }

    public void setMine(TabTitle tabTitle) {
        this.mine = tabTitle;
    }

    public TabTitle getCart() {
        return this.cart;
    }

    public void setCart(TabTitle tabTitle) {
        this.cart = tabTitle;
    }
}
