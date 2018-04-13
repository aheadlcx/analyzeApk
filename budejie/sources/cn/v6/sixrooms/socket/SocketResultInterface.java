package cn.v6.sixrooms.socket;

import org.json.JSONObject;

public interface SocketResultInterface {
    void onReceiveFail(JSONObject jSONObject, String str);

    void onReceiveSuccess(JSONObject jSONObject, String str);

    void onReceiveSuccessOnMain(JSONObject jSONObject, String str);
}
