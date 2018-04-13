package qsbk.app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class AppInfo {
    public String appName = "";
    public String packageName = "";
    public String versionName = "";

    public AppInfo(String str, String str2, String str3) {
        this.appName = str;
        this.packageName = str2;
        this.versionName = str3;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", this.appName);
            jSONObject.put("p", this.packageName);
            jSONObject.put("vn", this.versionName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
