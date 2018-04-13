package com.umeng.fb.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;
import u.fb.b;
import u.fb.g;

public class UserInfo {
    private static final String e = UserInfo.class.getName();
    int a;
    String b;
    Map<String, String> c;
    Map<String, String> d;

    public UserInfo() {
        this.a = -1;
        this.b = "";
        this.c = new HashMap();
        this.d = new HashMap();
    }

    UserInfo(JSONObject jSONObject) throws JSONException {
        Iterator keys;
        this.a = -1;
        this.b = "";
        this.a = jSONObject.optInt("age_group", -1);
        this.b = jSONObject.optString("gender", "");
        this.c = new HashMap();
        this.d = new HashMap();
        JSONObject optJSONObject = jSONObject.optJSONObject("contact");
        if (optJSONObject != null) {
            keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                this.c.put(str, optJSONObject.getString(str));
            }
        }
        optJSONObject = jSONObject.optJSONObject("remark");
        b.c(e, optJSONObject);
        if (optJSONObject != null) {
            keys = optJSONObject.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                this.d.put(str, optJSONObject.getString(str));
            }
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2;
            if (this.a > -1) {
                jSONObject.put("age_group", this.a);
            }
            if (!g.b(this.b)) {
                jSONObject.put("gender", this.b);
            }
            if (this.c != null && this.c.size() > 0) {
                jSONObject2 = new JSONObject();
                for (Entry entry : this.c.entrySet()) {
                    jSONObject2.put((String) entry.getKey(), entry.getValue());
                }
                jSONObject.put("contact", jSONObject2);
            }
            if (this.d != null && this.d.size() > 0) {
                jSONObject2 = new JSONObject();
                for (Entry entry2 : this.d.entrySet()) {
                    jSONObject2.put((String) entry2.getKey(), entry2.getValue());
                }
                jSONObject.put("remark", jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public int getAgeGroup() {
        return this.a;
    }

    public void setAgeGroup(int i) {
        this.a = i;
    }

    public String getGender() {
        return this.b;
    }

    public void setGender(String str) {
        this.b = str;
    }

    public Map<String, String> getContact() {
        return this.c;
    }

    public void setContact(Map<String, String> map) {
        this.c = map;
    }

    public Map<String, String> getRemark() {
        return this.d;
    }

    public void setRemark(Map<String, String> map) {
        this.d = map;
    }
}
