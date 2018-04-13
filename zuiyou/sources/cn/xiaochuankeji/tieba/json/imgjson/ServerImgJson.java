package cn.xiaochuankeji.tieba.json.imgjson;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class ServerImgJson implements Serializable {
    @JSONField(name = "h")
    public int height;
    @JSONField(name = "id")
    public int id;
    @JSONField(name = "w")
    public int width;

    public String toJSON() {
        return JSONObject.toJSONString(this);
    }

    public static ServerImgJson fromJSONString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (ServerImgJson) JSONObject.parseObject(str, ServerImgJson.class);
    }
}
