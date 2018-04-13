package cn.v6.sixrooms.bean;

import java.util.List;

public class BadgeConfig {
    private List<Badge> props;
    private String ver;

    public String getVer() {
        return this.ver;
    }

    public void setVer(String str) {
        this.ver = str;
    }

    public List<Badge> getProps() {
        return this.props;
    }

    public void setProps(List<Badge> list) {
        this.props = list;
    }
}
