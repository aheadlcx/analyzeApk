package cn.v6.sixrooms.room.bean;

import cn.v6.sixrooms.bean.MessageBean;
import java.util.List;

public class OnHeadlineBeans extends MessageBean {
    private String countdown;
    private List<OnHeadlineBean> top8;

    public String getCountdown() {
        return this.countdown;
    }

    public void setCountdown(String str) {
        this.countdown = str;
    }

    public List<OnHeadlineBean> getTop8() {
        return this.top8;
    }

    public void setTop8(List<OnHeadlineBean> list) {
        this.top8 = list;
    }
}
