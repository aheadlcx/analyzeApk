package com.iflytek.cloud.thirdparty;

import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.Version;
import com.iflytek.cloud.SpeechConstant;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.umeng.analytics.b.g;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class q {
    private af a;
    private String b = "";
    private int c = 1;
    private int d = -1;
    private String e = "";
    private int f = 1;

    public q(af afVar) {
        this.a = afVar;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObject2 = new JSONObject();
                String optString = jSONObject.optString(SpeechConstant.IST_SESSION_ID, "");
                jSONObject.put("mic_type", this.d);
                jSONObject.put("sdk_ver", Version.getVersion());
                jSONObject.put("ver_type", Version.getVersionType());
                jSONObject.put("dev_lang", "java");
                jSONObject.put(g.p, "android");
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(SpeechConstant.IST_SESSION_ID, optString);
                jSONObject2.put("sess", jSONObject3);
                if (this.a != null) {
                    jSONObject.put(AIUIConstant.KEY_INTERACT_MODE, this.a.g());
                }
                if (i == 0) {
                    if (this.b.equals(optString)) {
                        this.c++;
                    } else {
                        this.b = optString;
                        this.c = 1;
                    }
                    jSONObject2.put(PushConstants.EXTRA_APPLICATION_PENDING_INTENT, jSONObject);
                    jSONObject2.put("tran_id", "app#" + this.c);
                } else if (1 == i) {
                    if (this.e.equals(optString)) {
                        this.f++;
                    } else {
                        this.e = optString;
                        this.f = 1;
                    }
                    jSONObject2.put("sdk", jSONObject);
                    jSONObject2.put("tran_id", "sdk#" + this.f);
                }
                String str2 = "sid=" + jSONObject.getString(SpeechConstant.IST_SESSION_ID);
                try {
                    byte[] bytes = jSONObject2.toString().getBytes("utf-8");
                    if (this.a != null) {
                        ah c = this.a.c();
                        if (c != null) {
                            c.a(str2, bytes, bytes.length);
                        }
                    }
                } catch (n e) {
                    if (this.a != null) {
                        this.a.a(e.b(), e.a());
                    }
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }
}
