package com.ak.android.engine.navbase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdSpace {
    private String mAdSpaceId = "";
    private int mNum = -1;
    private JSONArray mWH;

    public AdSpace(String str) {
        this.mAdSpaceId = str;
    }

    public AdSpace setAdNum(int i) {
        this.mNum = i;
        return this;
    }

    public AdSpace addAdSize(int i, int i2) {
        if (this.mWH == null) {
            this.mWH = new JSONArray();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("w", i);
            jSONObject.put("h", i2);
            this.mWH.put(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Object[] getArgs() {
        return new Object[]{this.mAdSpaceId, Integer.valueOf(this.mNum), this.mWH};
    }
}
