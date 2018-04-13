package cn.v6.sixrooms.bean;

public class GuardStausBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String alias;
    private String propid;

    public String getPropid() {
        return this.propid;
    }

    public void setPropid(String str) {
        this.propid = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String toString() {
        return "GuardStausBean [propid=" + this.propid + ", alias=" + this.alias + "]";
    }
}
