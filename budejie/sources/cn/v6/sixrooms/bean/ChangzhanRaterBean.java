package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class ChangzhanRaterBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String alias;
    private String ok;
    private String uid;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getOk() {
        return this.ok;
    }

    public void setOk(String str) {
        this.ok = str;
    }

    public String toString() {
        return "ChangzhanRaterBean [uid=" + this.uid + ", alias=" + this.alias + ", ok=" + this.ok + "]";
    }
}
