package com.tencent.weibo.sdk.android.api.util;

import com.tencent.weibo.sdk.android.model.BaseVO;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {
    public static BaseVO jsonToObject(Class<? extends BaseVO> cls, JSONObject jSONObject) throws Exception {
        Map hashMap = new HashMap();
        for (Method method : cls.getMethods()) {
            if (method.getName().startsWith("set")) {
                hashMap.put(method.getName().toLowerCase(), method);
            }
        }
        BaseVO baseVO = (BaseVO) cls.newInstance();
        for (Field field : cls.getDeclaredFields()) {
            String name = field.getType().getName();
            String name2 = field.getName();
            try {
                if (name.equals("boolean")) {
                    ((Method) hashMap.get("set" + name2.toLowerCase())).invoke(baseVO, new Object[]{Boolean.valueOf(jSONObject.getBoolean(name2))});
                } else if (name.equals(Boolean.class.getName())) {
                    ((Method) hashMap.get("set" + name2.toLowerCase())).invoke(baseVO, new Object[]{Boolean.valueOf(jSONObject.getBoolean(name2))});
                } else if (name.equals("int") || name.equals("byte")) {
                    ((Method) hashMap.get("set" + name2.toLowerCase())).invoke(baseVO, new Object[]{Integer.valueOf(jSONObject.getInt(name2))});
                } else if (name.equals(Integer.class.getName()) || name.equals(Byte.class.getName())) {
                    ((Method) hashMap.get("set" + name2.toLowerCase())).invoke(baseVO, new Object[]{Integer.valueOf(jSONObject.getInt(name2))});
                } else if (name.equals(String.class.getName())) {
                    ((Method) hashMap.get("set" + name2.toLowerCase())).invoke(baseVO, new Object[]{jSONObject.getString(name2)});
                } else if (name.equals("double") || name.equals("float")) {
                    ((Method) hashMap.get("set" + name2.toLowerCase())).invoke(baseVO, new Object[]{Double.valueOf(jSONObject.getDouble(name2))});
                } else if (name.equals(Double.class.getName()) || name.equals(Float.class.getName())) {
                    ((Method) hashMap.get("set" + name2.toLowerCase())).invoke(baseVO, new Object[]{Double.valueOf(jSONObject.getDouble(name2))});
                } else if (name.equals("long")) {
                    ((Method) hashMap.get("set" + name2.toLowerCase())).invoke(baseVO, new Object[]{Long.valueOf(jSONObject.getLong(name2))});
                } else if (name.equals(Long.class.getName())) {
                    ((Method) hashMap.get("set" + name2.toLowerCase())).invoke(baseVO, new Object[]{Long.valueOf(jSONObject.getLong(name2))});
                }
            } catch (Exception e) {
            }
        }
        return baseVO;
    }

    public static List<BaseVO> jsonToList(Class<? extends BaseVO> cls, JSONArray jSONArray) throws Exception {
        List<BaseVO> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jsonToObject(cls, jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }
}
