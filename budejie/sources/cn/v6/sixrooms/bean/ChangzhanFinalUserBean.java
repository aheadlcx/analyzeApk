package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class ChangzhanFinalUserBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String alias;
    private String num;
    private String out;
    private String pic;
    private String rid;
    private String uid;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getOut() {
        return this.out;
    }

    public void setOut(String str) {
        this.out = str;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String str) {
        this.pic = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }
}
