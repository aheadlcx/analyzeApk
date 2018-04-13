package qsbk.app.http;

import org.json.JSONObject;

public interface SimpleCallBack {
    public static final int LOCAL = 9999;

    void onFailure(int i, String str);

    void onSuccess(JSONObject jSONObject);
}
