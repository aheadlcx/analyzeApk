package cn.tatagou.sdk.pojo;

import java.util.Map;

public class LvCate {
    private String base;
    private Map<String, String> hitCates;
    private String totalCateNum;
    private String totalHits;
    private String weight;

    public String getBase() {
        return this.base;
    }

    public void setBase(String str) {
        this.base = str;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String str) {
        this.weight = str;
    }

    public String getTotalCateNum() {
        return this.totalCateNum;
    }

    public void setTotalCateNum(String str) {
        this.totalCateNum = str;
    }

    public String getTotalHits() {
        return this.totalHits;
    }

    public void setTotalHits(String str) {
        this.totalHits = str;
    }

    public Map<String, String> getHitCates() {
        return this.hitCates;
    }

    public void setHitCates(Map<String, String> map) {
        this.hitCates = map;
    }
}
