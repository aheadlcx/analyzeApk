package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class e {
    public static final String a = "browseImages";
    public JSONObject b;

    public e(String str) {
        try {
            this.b = JSON.parseObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
