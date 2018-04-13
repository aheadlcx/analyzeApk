package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.cloud.SpeechConstant;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class s extends r {
    public s(Context context, af afVar) {
        super(context, afVar);
    }

    private void a(int i, String str) {
        if (this.a != null) {
            this.a.a(i, str);
        }
    }

    private void a(byte[] bArr, byte[] bArr2, int i, int i2) throws n {
        if (this.a != null) {
            ah c = this.a.c();
            if (c != null) {
                c.a(bArr, bArr2, i, i2);
            }
        }
    }

    public void a(AIUIMessage aIUIMessage) {
        try {
            JSONObject jSONObject = new JSONObject(aIUIMessage.params);
            if (3 != aIUIMessage.arg1) {
                return;
            }
            if (jSONObject.has(SpeechConstant.IST_SESSION_ID)) {
                jSONObject.put("query_str", "csid=" + jSONObject.getString(SpeechConstant.IST_SESSION_ID));
                cb.a("SyncDataModule", jSONObject.toString());
                a(jSONObject.toString().getBytes(), new byte[1], 1, 4);
                return;
            }
            cb.c("SyncDataModule", "query params does not have sid.");
        } catch (JSONException e) {
            e.printStackTrace();
            a(10106, "query sync: params invalid json format.");
        } catch (n e2) {
            e2.printStackTrace();
            a(e2.b(), e2.a());
        }
    }

    public void b(AIUIMessage aIUIMessage) {
        String str = "";
        try {
            if (!TextUtils.isEmpty(aIUIMessage.params)) {
                str = new JSONObject(aIUIMessage.params).toString();
            }
            try {
                byte[] bArr;
                int i;
                byte[] bytes = str.getBytes("utf-8");
                byte[] bArr2 = aIUIMessage.data;
                JSONObject jSONObject;
                String optString;
                if (3 == aIUIMessage.arg1) {
                    jSONObject = new JSONObject(new String(bArr2));
                    JSONObject jSONObject2 = jSONObject.getJSONObject("param");
                    if (jSONObject2.has("appid")) {
                        optString = jSONObject2.optString("appid");
                    } else {
                        CharSequence d = this.a == null ? "" : this.a.d();
                        if (TextUtils.isEmpty(d)) {
                            CharSequence charSequence = d;
                        } else {
                            jSONObject2.put("appid", d);
                            optString = d;
                        }
                    }
                    if (jSONObject2.has("uid")) {
                        str = jSONObject2.optString("uid");
                    } else {
                        str = ac.b();
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject2.put("uid", str);
                        }
                    }
                    if (jSONObject2.has("id_name")) {
                        String string = jSONObject2.getString("id_name");
                        CharSequence optString2 = jSONObject2.optString("id_value");
                        if ("appid".equals(string)) {
                            if (TextUtils.isEmpty(optString2)) {
                                jSONObject2.put("id_value", optString);
                            }
                        } else if ("uid".equals(string) && TextUtils.isEmpty(optString2)) {
                            jSONObject2.put("id_value", str);
                        }
                    }
                    jSONObject.put("csid", al.a(optString, "atn", str));
                    optString = jSONObject.toString();
                    bArr2 = optString.getBytes();
                    cb.a("SyncDataModule", "schema data:" + optString);
                } else if (5 == aIUIMessage.arg1) {
                    JSONObject jSONObject3 = new JSONObject(new String(bArr2));
                    if (jSONObject3.has(AIUIConstant.KEY_IAT_USER_DATA)) {
                        ac.b("audioparams", "rec_user_data", jSONObject3.getJSONObject(AIUIConstant.KEY_IAT_USER_DATA).toString());
                    }
                    if (jSONObject3.has(AIUIConstant.KEY_NLP_USER_DATA)) {
                        jSONObject = jSONObject3.getJSONObject(AIUIConstant.KEY_NLP_USER_DATA);
                        if (jSONObject.has("appid")) {
                            str = jSONObject.optString("appid");
                        } else {
                            str = this.a == null ? "" : this.a.d();
                            if (!TextUtils.isEmpty(str)) {
                                jSONObject.put("appid", str);
                            }
                        }
                        if (jSONObject.has("uid")) {
                            optString = jSONObject.getString("uid");
                            if (TextUtils.isEmpty(optString)) {
                                optString = ac.b();
                                jSONObject.put("uid", optString);
                            }
                        } else {
                            optString = ac.b();
                            jSONObject.put("uid", optString);
                        }
                        jSONObject.put("csid", al.a(str, "atn", optString));
                        bArr2 = jSONObject.toString().getBytes();
                        cb.a("SyncDataModule", "nlp_user_data:" + jSONObject.toString());
                        bArr = bArr2;
                        i = 0;
                        if (bArr != null) {
                            i = bArr.length;
                        }
                        a(bytes, bArr, i, aIUIMessage.arg1);
                    }
                    return;
                }
                bArr = bArr2;
                i = 0;
                if (bArr != null) {
                    i = bArr.length;
                }
                a(bytes, bArr, i, aIUIMessage.arg1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (n e2) {
                e2.printStackTrace();
                a(e2.b(), e2.a());
            } catch (JSONException e3) {
                e3.printStackTrace();
                a(10107, "sync data error, invalid data json.");
            }
        } catch (JSONException e32) {
            e32.printStackTrace();
            a(10106, "sync data: params invalid json format.");
        }
    }
}
