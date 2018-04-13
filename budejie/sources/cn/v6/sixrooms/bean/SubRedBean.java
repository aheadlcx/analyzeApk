package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class SubRedBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private SubRedContentBean content;
    private String fid;
    private String frid;
    private String from;

    public class SubRedContentBean implements Serializable {
        private static final long serialVersionUID = 1;
        private int num;

        public int getNum() {
            return this.num;
        }

        public void setNum(int i) {
            this.num = i;
        }

        public String toString() {
            return "SubRedContent [num=" + this.num + "]";
        }
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public String getFrid() {
        return this.frid;
    }

    public void setFrid(String str) {
        this.frid = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String toString() {
        return "SubRedBean [fid=" + this.fid + ", frid=" + this.frid + ", from=" + this.from + ", content=" + this.content + "]";
    }

    public SubRedContentBean getContent() {
        return this.content;
    }

    public void setContent(SubRedContentBean subRedContentBean) {
        this.content = subRedContentBean;
    }
}
