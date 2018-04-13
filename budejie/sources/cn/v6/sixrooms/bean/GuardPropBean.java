package cn.v6.sixrooms.bean;

import java.util.List;

public class GuardPropBean {
    private String desc;
    private List<GuardPropDetailBean> details;
    private String propId;

    public GuardPropBean(String str, List<GuardPropDetailBean> list, String str2) {
        this.propId = str;
        this.details = list;
        this.desc = str2;
    }

    public String getPropId() {
        return this.propId;
    }

    public void setPropId(String str) {
        this.propId = str;
    }

    public List<GuardPropDetailBean> getDetails() {
        return this.details;
    }

    public void setDetails(List<GuardPropDetailBean> list) {
        this.details = list;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String toString() {
        return "GuardPropBean [propId=" + this.propId + ", details=" + this.details + ", desc=" + this.desc + "]";
    }
}
