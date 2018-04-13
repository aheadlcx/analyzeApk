package cn.v6.sixrooms.room.bean;

import java.io.Serializable;

public class OnHeadlineBean implements Serializable {
    private String alias;
    private String num;
    private String pic;
    private String rid;
    private String uid;
    private String wealthrank;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public String getWealthrank() {
        return this.wealthrank;
    }

    public void setWealthrank(String str) {
        this.wealthrank = str;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String str) {
        this.pic = str;
    }
}
