package cn.v6.sixrooms.bean;

import java.util.List;
import java.util.Map;

public class ExchangeRulesBean {
    private String desc;
    private List<Map<String, String>> list;

    public List<Map<String, String>> getList() {
        return this.list;
    }

    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }
}
