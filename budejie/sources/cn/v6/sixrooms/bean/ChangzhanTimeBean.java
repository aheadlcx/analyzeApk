package cn.v6.sixrooms.bean;

import java.io.Serializable;
import java.util.List;

public class ChangzhanTimeBean extends MessageBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String alias;
    private List<ChangzhanRaterBean> content;
    private long dtm;
    private long stm;
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

    public long getDtm() {
        return this.dtm;
    }

    public void setDtm(long j) {
        this.dtm = j;
    }

    public long getStm() {
        return this.stm;
    }

    public void setStm(long j) {
        this.stm = j;
    }

    public List<ChangzhanRaterBean> getContent() {
        return this.content;
    }

    public void setContent(List<ChangzhanRaterBean> list) {
        this.content = list;
    }

    public String toString() {
        return "ChangzhanTimeBean [uid=" + this.uid + ", alias=" + this.alias + ", dtm=" + this.dtm + ", stm=" + this.stm + ", content=" + this.content + "]";
    }
}
