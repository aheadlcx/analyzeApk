package cn.v6.sixrooms.bean;

import cn.v6.sixrooms.hall.BaseBean;

public class SuperGMemBean extends BaseBean {
    private MemoBean memo;
    private String starlight;
    private String uid;

    public String getStarlight() {
        return this.starlight;
    }

    public void setStarlight(String str) {
        this.starlight = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public MemoBean getMemo() {
        return this.memo;
    }

    public void setMemo(MemoBean memoBean) {
        this.memo = memoBean;
    }
}
