package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class ActivityBean extends MessageBean implements Serializable {
    private ContentEntity content;
    private int fid;
    private String frid;
    private String from;
    private String tid;
    private String to;
    private String trid;

    public static class ContentEntity {
        private int eid;
        private String ename;
        private int num;

        public int getNum() {
            return this.num;
        }

        public void setNum(int i) {
            this.num = i;
        }

        public int getEid() {
            return this.eid;
        }

        public void setEid(int i) {
            this.eid = i;
        }

        public String getEname() {
            return this.ename;
        }

        public void setEname(String str) {
            this.ename = str;
        }

        public String toString() {
            return "ContentEntity{num=" + this.num + ", eid=" + this.eid + ", ename='" + this.ename + '\'' + '}';
        }
    }

    public int getFid() {
        return this.fid;
    }

    public void setFid(int i) {
        this.fid = i;
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

    public String getTo() {
        return this.to;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String str) {
        this.tid = str;
    }

    public String getTrid() {
        return this.trid;
    }

    public void setTrid(String str) {
        this.trid = str;
    }

    public ContentEntity getContent() {
        return this.content;
    }

    public void setContent(ContentEntity contentEntity) {
        this.content = contentEntity;
    }

    public String toString() {
        return "ActivityBean{fid=" + this.fid + ", frid='" + this.frid + '\'' + ", from='" + this.from + '\'' + ", to='" + this.to + '\'' + ", tid='" + this.tid + '\'' + ", trid='" + this.trid + '\'' + ", content=" + this.content + '}';
    }
}
