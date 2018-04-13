package cn.tatagou.sdk.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MyMap implements Serializable {
    private Map<String, String> map = new HashMap();

    public Map<String, String> getMap() {
        return this.map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getMapValues(String str) {
        if (this.map != null) {
            return (String) this.map.get(str);
        }
        return null;
    }
}
