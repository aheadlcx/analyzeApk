package cn.xiaochuankeji.tieba.background.beans;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import org.json.JSONObject;

public class Medal implements Serializable {
    @JSONField(name = "click_url")
    public String click_url;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "original")
    public int original;

    public Medal(JSONObject jSONObject) {
        this.name = jSONObject.optString("name");
        this.original = jSONObject.optInt("type");
        this.click_url = jSONObject.optString("click_url");
    }
}
