package qsbk.app.model;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.cafe.plugin.OthersPlugin;
import qsbk.app.http.EncryptDecryptUtil;

public class LaiseeImInfo {
    private String a;
    public String content;
    public String laiseeId;
    public int pop;
    public String secret;

    public LaiseeImInfo(String str, String str2, String str3) {
        this.laiseeId = str;
        this.secret = str2;
        this.content = str3;
    }

    public LaiseeImInfo(Laisee laisee) {
        this.laiseeId = laisee.id;
        this.secret = laisee.secret;
        this.content = laisee.content;
        this.a = laisee.subtype;
    }

    public boolean isPop() {
        return this.pop == 1;
    }

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
            this.content = jSONObject.optString("content");
            this.pop = jSONObject.optInt(OthersPlugin.ACTION_POP);
            this.a = jSONObject.optString("sub_type");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(OthersPlugin.ACTION_POP, this.pop);
            jSONObject2.put("secret", this.secret);
            jSONObject2.put("laisee_id", this.laiseeId);
            jSONObject2.put("content", this.content);
            jSONObject2.put("sub_type", this.a);
            jSONObject.put("data", EncryptDecryptUtil.processEncrypt(jSONObject2.toString()));
            jSONObject.put("id", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public Laisee getLaisee() {
        if (TextUtils.equals(this.a, Laisee.SUB_TYPE_VOICE)) {
            return new LaiseeVoice(this);
        }
        return new Laisee(this);
    }
}
