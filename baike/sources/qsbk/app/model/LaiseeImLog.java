package qsbk.app.model;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.EncryptDecryptUtil;

public class LaiseeImLog {
    public String laiseeId;
    public String receiveId;
    public String receiveName;
    public String secret;
    public String sendId;
    public String sendName;

    public void parseEncryptJson(String str) {
        try {
            parse(EncryptDecryptUtil.processDecrypt(new JSONObject(str).optString("data")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.laiseeId = jSONObject.optString("laisee_id");
            this.secret = jSONObject.optString("secret");
            JSONObject optJSONObject = jSONObject.optJSONObject("send_user");
            if (optJSONObject != null) {
                this.sendId = optJSONObject.optString("id");
                this.sendName = optJSONObject.optString(QsbkDatabase.LOGIN);
            }
            jSONObject = jSONObject.optJSONObject("recv_user");
            if (jSONObject != null) {
                this.receiveId = jSONObject.optString("id");
                this.receiveName = jSONObject.optString(QsbkDatabase.LOGIN);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isAboutUser(String str) {
        return isSend(str) || isReceive(str);
    }

    public boolean isSend(String str) {
        return TextUtils.equals(str, this.sendId);
    }

    public boolean isReceive(String str) {
        return TextUtils.equals(str, this.receiveId);
    }

    public String getDesc() {
        if (isSend(QsbkApp.currentUser.userId)) {
            return String.format("%s领取了%s的 红包", new Object[]{this.receiveName, "你"});
        } else if (isReceive(QsbkApp.currentUser.userId)) {
            return String.format("%s领取了%s的 红包", new Object[]{"你", this.sendName});
        } else {
            return String.format("%s领取了%s的 红包", new Object[]{this.receiveName, this.sendName});
        }
    }
}
