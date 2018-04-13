package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class RoomEventFloatBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String ebground;
    private String eid;
    private String elogo;
    private String ename;
    private String identity;
    private String ruleurl;
    private String schtitle;
    private String signurl;
    private String spic;
    private String status;

    public String getEbground() {
        return this.ebground;
    }

    public void setEbground(String str) {
        this.ebground = str;
    }

    public static long getSerialVersionUID() {
        return 1;
    }

    public String getEid() {
        return this.eid;
    }

    public void setEid(String str) {
        this.eid = str;
    }

    public String getElogo() {
        return this.elogo;
    }

    public void setElogo(String str) {
        this.elogo = str;
    }

    public String getEname() {
        return this.ename;
    }

    public void setEname(String str) {
        this.ename = str;
    }

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String str) {
        this.identity = str;
    }

    public String getRuleurl() {
        return this.ruleurl;
    }

    public void setRuleurl(String str) {
        this.ruleurl = str;
    }

    public String getSchtitle() {
        return this.schtitle;
    }

    public void setSchtitle(String str) {
        this.schtitle = str;
    }

    public String getSignurl() {
        return this.signurl;
    }

    public void setSignurl(String str) {
        this.signurl = str;
    }

    public String getSpic() {
        return this.spic;
    }

    public void setSpic(String str) {
        this.spic = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
