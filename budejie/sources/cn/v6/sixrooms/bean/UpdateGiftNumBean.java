package cn.v6.sixrooms.bean;

import java.util.ArrayList;

public class UpdateGiftNumBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private ArrayList<RepertoryBean> giftNumBeans;

    public UpdateGiftNumBean(ArrayList<RepertoryBean> arrayList) {
        this.giftNumBeans = arrayList;
    }

    public ArrayList<RepertoryBean> getGiftNumBeans() {
        return this.giftNumBeans;
    }

    public void setGiftNumBeans(ArrayList<RepertoryBean> arrayList) {
        this.giftNumBeans = arrayList;
    }

    public String toString() {
        return "UpdateGiftNumBean [RepertoryBean=" + this.giftNumBeans + "]";
    }
}
