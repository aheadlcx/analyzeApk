package qsbk.app.http;

import org.json.JSONObject;

public interface HttpCallBack {
    void onFailure(String str, String str2);

    void onSuccess(String str, JSONObject jSONObject);
}
