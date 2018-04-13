package com.tencent.weibo.sdk.android.model;

import com.qq.e.comm.constants.Constants.KEYS;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseVO implements Serializable {
    public static final int TYPE_BEAN = 0;
    public static final int TYPE_BEAN_LIST = 3;
    public static final int TYPE_JSON = 4;
    public static final int TYPE_LIST = 1;
    public static final int TYPE_OBJECT = 2;
    private static final long serialVersionUID = 8175948521471886407L;

    public Map<String, Object> analyseHead(JSONObject jSONObject) throws JSONException {
        Map<String, Object> hashMap = new HashMap();
        JSONArray jSONArray = jSONObject.getJSONArray("result_list");
        int i = jSONObject.getInt("total");
        int i2 = jSONObject.getInt("p");
        int i3 = jSONObject.getInt(KEYS.PLACEMENTS);
        boolean z = jSONObject.getBoolean("is_last_list");
        hashMap.put("array", jSONArray);
        hashMap.put("total", Integer.valueOf(i));
        hashMap.put("p", Integer.valueOf(i2));
        hashMap.put(KEYS.PLACEMENTS, Integer.valueOf(i3));
        hashMap.put("isLastPage", Boolean.valueOf(z));
        return hashMap;
    }

    public Object analyseBody(JSONObject jSONObject) throws JSONException {
        return null;
    }

    public Object analyseBody(JSONArray jSONArray) throws JSONException {
        return null;
    }
}
