package cn.xiaochuankeji.tieba.background.beans;

import java.io.Serializable;
import org.json.JSONObject;

public class Epaulet implements Serializable {
    private String click_url;
    private String name;
    private int type;

    public Epaulet(JSONObject jSONObject) {
        this.name = jSONObject.optString("name");
        this.type = jSONObject.optInt("type");
        this.click_url = jSONObject.optString("click_url");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getClick_url() {
        return this.click_url;
    }

    public void setClick_url(String str) {
        this.click_url = str;
    }
}
