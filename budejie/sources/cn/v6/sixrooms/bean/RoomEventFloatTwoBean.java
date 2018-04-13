package cn.v6.sixrooms.bean;

import cn.v6.sixrooms.hall.BaseBean;

public class RoomEventFloatTwoBean extends BaseBean {
    private static final long serialVersionUID = 1;
    private String eid;
    private String ename;
    private String logo;
    private String url;

    public String getEid() {
        return this.eid;
    }

    public void setEid(String str) {
        this.eid = str;
    }

    public String getEname() {
        return this.ename;
    }

    public void setEname(String str) {
        this.ename = str;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String str) {
        this.logo = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
