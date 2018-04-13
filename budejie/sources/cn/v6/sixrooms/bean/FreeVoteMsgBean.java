package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class FreeVoteMsgBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private FreeVoteMsgContentBean content;
    private String fid;
    private String frid;
    private String from;

    public class FreeVoteMsgContentBean implements Serializable {
        private static final long serialVersionUID = 1;
        private int num;

        public int getNum() {
            return this.num;
        }

        public void setNum(int i) {
            this.num = i;
        }

        public String toString() {
            return "FreeVoteMsgContentBean [num=" + this.num + "]";
        }
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public FreeVoteMsgContentBean getContent() {
        return this.content;
    }

    public void setContent(FreeVoteMsgContentBean freeVoteMsgContentBean) {
        this.content = freeVoteMsgContentBean;
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
        return "FreeVoteMsgBean [fid=" + this.fid + ", content=" + this.content + ", frid=" + this.frid + ", from=" + this.from + "]";
    }
}
