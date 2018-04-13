package cn.tatagou.sdk.pojo;

import java.util.Map;

public class Sex {
    private String currentSex;
    private Map<String, Map<String, Object>> valMatrix;
    private String weight;

    public Map<String, Map<String, Object>> getValMatrix() {
        return this.valMatrix;
    }

    public void setValMatrix(Map<String, Map<String, Object>> map) {
        this.valMatrix = map;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String str) {
        this.weight = str;
    }

    public String getCurrentSex() {
        return this.currentSex;
    }

    public void setCurrentSex(String str) {
        this.currentSex = str;
    }
}
