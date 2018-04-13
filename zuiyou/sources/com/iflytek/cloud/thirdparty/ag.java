package com.iflytek.cloud.thirdparty;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class ag {
    private String a = "cloud";
    private af b;
    private Queue<String> c = new ConcurrentLinkedQueue();
    private boolean d = false;
    private String e;
    private aj f;
    private ae g;

    public ag(af afVar) {
        this.b = afVar;
        this.g = ae.a();
        this.f = new aj();
    }

    private void a(Message message, boolean z) {
        if (this.b == null) {
            return;
        }
        if (z) {
            this.b.sendMessageAtFrontOfQueue(message);
        } else {
            this.b.sendMessage(message);
        }
    }

    private void a(String str) {
        if (!str.equals(this.e)) {
            this.e = str;
            int size = this.c.size();
            if (size > 5) {
                for (int i = 0; i < size - 3; i++) {
                    this.g.f((String) this.c.poll());
                }
            }
        }
    }

    private void a(String str, String str2, int i, String str3) throws UnsupportedEncodingException {
        this.f.e();
        this.f.a(str, ".txt", true);
        this.f.a(("sid=" + str2 + "\n").getBytes("utf-8"), true);
        this.f.a(("error=" + i + "\n").getBytes("utf-8"), true);
        this.f.a(("des=" + str3).getBytes("utf-8"), true);
        this.f.a("\n\n".getBytes("utf-8"), true);
        this.f.d();
    }

    private void a(String str, String str2, long j, long j2, long j3, JSONObject jSONObject, String str3, JSONObject jSONObject2) throws JSONException, UnsupportedEncodingException {
        this.f.e();
        this.f.a(str, ".txt", true);
        this.f.a(("sid=" + str2 + "\n").getBytes("utf-8"), true);
        String jSONObject3 = jSONObject2.toString();
        if ("nlp".equals(str3)) {
            b(str, str2, j, j2, j3, jSONObject, str3, jSONObject2);
            this.f.a(("bos_nlp=" + j2 + "\n").getBytes("utf-8"), true);
            this.f.a(("eos_nlp=" + j3 + "\n").getBytes("utf-8"), true);
        } else if ("iat".equals(str3)) {
            this.f.a(("bos_iat=" + j2 + "\n").getBytes("utf-8"), true);
            this.f.a(("eos_iat=" + j3 + "\n").getBytes("utf-8"), true);
        } else if ("asr".equals(str3)) {
            this.f.a(("bos_asr=" + j2 + "\n").getBytes("utf-8"), true);
            this.f.a(("eos_asr=" + j3 + "\n").getBytes("utf-8"), true);
        } else if ("tpp".equals(str3)) {
            b(str, str2, j, j2, j3, jSONObject, str3, jSONObject2);
        }
        this.f.a(jSONObject3.getBytes("utf-8"), true);
        this.f.a("\n\n".getBytes("utf-8"), true);
        this.f.d();
        cb.a("ResultScheduler", jSONObject3);
        cb.a("ResultScheduler", "<---------------------------------------------->");
    }

    private void a(String str, String str2, String str3, String str4, Map<String, byte[]> map, long j, long j2, long j3) {
        Bundle a = am.a((Map) map);
        a.putString(SpeechConstant.IST_SESSION_ID, str);
        a.putString("stream_id", str2);
        a.putLong("p_rslt", j);
        a.putLong("bos_rslt", j2);
        a.putLong("eos_rslt", j3);
        if (!TextUtils.isEmpty(str3)) {
            a.putString(AIUIConstant.KEY_TAG, str3);
        }
        ad a2 = this.g.a(str2, "iat");
        if (a2 != null) {
            Long b = this.g.b(str2);
            Long c = this.g.c(str2);
            long d = a2.d();
            Long valueOf = Long.valueOf(b == null ? 0 : b.longValue());
            c = Long.valueOf(c == null ? -1 : c.longValue());
            long longValue = valueOf.longValue();
            long longValue2 = -1 == c.longValue() ? -1 : d - c.longValue();
            a.putLong("p_bos", valueOf.longValue());
            a.putLong("p_eos", c.longValue());
            a.putLong("bos_iat", Long.valueOf(d - longValue).longValue());
            a.putLong("eos_iat", Long.valueOf(longValue2).longValue());
        }
        Message obtain = Message.obtain();
        obtain.what = 5;
        obtain.obj = new AIUIEvent(1, 0, 0, str4, a);
        a(obtain, false);
    }

    private void a(JSONObject jSONObject) {
        int i;
        try {
            i = jSONObject.has(AIUIConstant.WORK_MODE_INTENT) ? jSONObject.getJSONObject(AIUIConstant.WORK_MODE_INTENT).getInt("rc") : 4;
        } catch (JSONException e) {
            e.printStackTrace();
            i = 4;
        }
        if (i == 0 || 3 == i) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            a(obtain, true);
        }
    }

    private void b(String str, String str2, long j, long j2, long j3, JSONObject jSONObject, String str3, JSONObject jSONObject2) {
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("stream_id", str);
            jSONObject3.put(SpeechConstant.IST_SESSION_ID, str2);
            jSONObject3.put("sub", str3);
            jSONObject3.put("confidence", (double) this.g.a(str));
            if ("nlp".equals(str3)) {
                jSONObject3.put("p_nlp_sdk", j);
                jSONObject3.put("bos_nlp", j2);
                jSONObject3.put("eos_nlp", j3);
                ad a = this.g.a(str, "iat");
                if (a != null) {
                    Long b = this.g.b(str);
                    Long c = this.g.c(str);
                    long d = a.d();
                    Long valueOf = Long.valueOf(b == null ? 0 : b.longValue());
                    c = Long.valueOf(c == null ? -1 : c.longValue());
                    long longValue = valueOf.longValue();
                    long longValue2 = -1 == c.longValue() ? -1 : d - c.longValue();
                    jSONObject3.put("p_bos", valueOf);
                    jSONObject3.put("p_eos", c);
                    jSONObject3.put("bos_iat", Long.valueOf(d - longValue));
                    jSONObject3.put("eos_iat", Long.valueOf(longValue2));
                }
            } else if ("tpp".equals(str3)) {
                jSONObject3.put("p_tpp_sdk", j);
                jSONObject3.put("bos_tpp", j2);
                jSONObject3.put("eos_tpp", j3);
            }
            jSONObject3.put(SpeechConstant.PARAMS, jSONObject);
            int i = -1;
            try {
                if (jSONObject2.has(AIUIConstant.WORK_MODE_INTENT)) {
                    JSONObject jSONObject4 = jSONObject2.getJSONObject(AIUIConstant.WORK_MODE_INTENT);
                    String optString = jSONObject4.optString(NotificationCompat.CATEGORY_SERVICE, "");
                    i = jSONObject4.optInt("rc", -1);
                    jSONObject3.put(NotificationCompat.CATEGORY_SERVICE, optString);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONObject3.put("rc", i);
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = new AIUIMessage(12, 1, 0, jSONObject3.toString(), null);
            a(obtain, false);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void a() {
        this.a = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTENT_ENGINE_TYPE, "cloud");
    }

    public void a(String str, String str2, Map<String, byte[]> map, String str3, String str4, long j) throws JSONException, UnsupportedEncodingException {
        if (TextUtils.isEmpty(this.e)) {
            this.e = str4;
        }
        if (!this.c.contains(str4)) {
            this.c.add(str4);
        }
        long j2 = 0;
        long j3 = -1;
        Long b = this.g.b(str4);
        Long c = this.g.c(str4);
        if (b != null) {
            j2 = j - b.longValue();
        }
        if (c != null) {
            j3 = j - c.longValue();
        }
        JSONObject jSONObject = new JSONObject(str2).getJSONArray("data").getJSONObject(0);
        JSONObject jSONObject2 = jSONObject.getJSONObject(SpeechConstant.PARAMS);
        JSONObject jSONObject3 = jSONObject.getJSONArray("content").getJSONObject(0);
        String optString = jSONObject2.optString("sub");
        String d = this.g.d(str4);
        if (this.g.e(str4)) {
            ad adVar = new ad(optString, str, str2, str4, map, j, false);
            if ("nlp".equals(optString)) {
                if (!this.g.c(str4, "asr")) {
                    adVar.a(true);
                    this.g.a(str4, optString, adVar);
                    a(str3, str4, d, str2, (Map) map, j, j2, j3);
                    cb.a("ResultScheduler", "notify " + optString);
                }
            } else {
                adVar.a(true);
                this.g.a(str4, optString, adVar);
                a(str3, str4, d, str2, (Map) map, j, j2, j3);
                cb.a("ResultScheduler", "notify " + optString);
            }
        } else {
            cb.a("ResultScheduler", "no " + str4 + " in statistic list, ignore this " + optString + " result.");
        }
        a(str4);
        cb.a("ResultScheduler", "sid=" + str3);
        cb.a("ResultScheduler", str);
        cb.a("ResultScheduler", str2);
        if (jSONObject3.has("cnt_id")) {
            String str5;
            String str6 = "";
            byte[] bArr = (byte[]) map.get(jSONObject3.getString("cnt_id"));
            if (bArr != null) {
                str5 = new String(bArr, "utf-8");
            } else {
                Log.e("ResultScheduler", "content data is null.");
                str5 = str6;
            }
            Message obtain = Message.obtain();
            obtain.what = 11;
            a(obtain, true);
            JSONObject jSONObject4 = new JSONObject(str5);
            if ("nlp".equals(optString)) {
                a(jSONObject4);
                ao.a(SpeechUtility.TAG_RESOURCE_RESULT, str3, al.b());
            } else if (!"iat".equals(optString) && "asr".equals(optString)) {
                a(jSONObject4);
            }
            a(str4, str3, j, j2, j3, jSONObject2, optString, jSONObject4);
        }
    }

    public void b() {
        this.d = false;
    }

    void errorCb(char[] cArr, int i, byte[] bArr) {
        try {
            String str = new String(bArr, "utf-8");
            Message obtain = Message.obtain();
            obtain.what = 7;
            obtain.obj = new AIUIEvent(2, i, 0, str, null);
            ce ceVar = new ce();
            ceVar.a(str);
            String b = ceVar.b("stmid", "");
            String b2 = ceVar.b(SpeechConstant.IST_SESSION_ID, "");
            if ("mixed".equals(this.a)) {
                if (10120 == i) {
                    List<ad> b3 = this.g.b(b, "asr");
                    if (b3 != null) {
                        for (ad adVar : b3) {
                            if (!adVar.f()) {
                                adVar.a(true);
                                try {
                                    a(adVar.b(), adVar.a(), adVar.c(), "", adVar.e(), adVar.d());
                                    break;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } else {
                    a(obtain, false);
                }
            } else if (10120 != i) {
                a(obtain, false);
            } else if (ac.a("vad", "vad_enable", true)) {
                float a = this.g.a(b);
                if (a > 0.7f) {
                    a(obtain, false);
                } else if (0.0f == a) {
                    a(obtain, false);
                } else {
                    cb.a("ResultScheduler", String.format("%s had low confidence, ignore 10120 error.", new Object[]{b}));
                }
            } else {
                a(obtain, false);
            }
            cb.a("ResultScheduler", "errorCode=" + i + ", " + str);
            cb.a("ResultScheduler", "<---------------------------------------------->");
            if (!TextUtils.isEmpty(b)) {
                if (!this.c.contains(b)) {
                    this.c.add(b);
                }
                a(b, b2, i, str);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }

    void pushCb(char[] cArr, byte[] bArr, byte[] bArr2, Object obj) {
        String str = new String(bArr2);
        Bundle a = am.a((Map) obj);
        cb.a("ResultScheduler", "received push message, bizparams=" + str);
        AIUIEvent aIUIEvent = new AIUIEvent(1001, 0, 0, str, a);
        Message obtain = Message.obtain();
        obtain.what = 10;
        obtain.obj = aIUIEvent;
        a(obtain, false);
    }

    void resultCb(char[] cArr, byte[] bArr, byte[] bArr2, Object obj) {
        long currentTimeMillis = System.currentTimeMillis();
        if (cArr == null || bArr == null || bArr2 == null || obj == null) {
            Log.e("ResultScheduler", "resultCb has null params.");
            return;
        }
        try {
            String str = new String(bArr, "utf-8");
            String str2 = new String(bArr2, "utf-8");
            Map map = (Map) obj;
            ce ceVar = new ce();
            ceVar.b(str.replace("&", ","));
            a(str, str2, map, ceVar.e(SpeechConstant.IST_SESSION_ID), ceVar.e("stmid"), currentTimeMillis);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    void statusCb(char[] cArr, int i, int i2, byte[] bArr, int i3) {
        Message obtain;
        switch (i2) {
            case 10114:
            case 10205:
            case 11802:
                Log.e("ServerConnection", "connect to server failed.");
                if (!this.d) {
                    this.d = true;
                    if (this.b != null) {
                        this.b.sendEmptyMessageDelayed(12, 600000);
                    }
                }
                obtain = Message.obtain();
                obtain.what = 14;
                a(obtain, false);
                return;
            case 11801:
                Log.d("ServerConnection", "connect to server success.");
                if (this.b != null) {
                    this.b.removeMessages(12);
                }
                obtain = Message.obtain();
                obtain.what = 13;
                a(obtain, false);
                this.d = false;
                return;
            default:
                return;
        }
    }

    void syncDataCb(char[] cArr, int i, byte[] bArr, int i2) {
        cb.a("ResultScheduler", "sync data, ret=" + i + ", dataType=" + i2);
        String str = bArr != null ? new String(bArr) : "";
        Bundle bundle = new Bundle();
        bundle.putInt("sync_dtype", i2);
        if (3 == i2) {
            try {
                bundle.putString(SpeechConstant.IST_SESSION_ID, new JSONObject(str).getString(SpeechConstant.IST_SESSION_ID));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Message obtain;
        if (4 == i2) {
            bundle.putString(SpeechUtility.TAG_RESOURCE_RESULT, str);
            obtain = Message.obtain();
            obtain.what = 8;
            if (200 != i) {
                obtain.obj = new AIUIEvent(8, 24, i, "query athena sync status error.", bundle);
            } else {
                obtain.obj = new AIUIEvent(8, 24, 0, "query athena sync status.", bundle);
            }
            a(obtain, false);
            return;
        }
        obtain = Message.obtain();
        obtain.what = 8;
        if (200 != i) {
            obtain.obj = new AIUIEvent(8, 13, i, "sync data error. dataType=" + i2, bundle);
        } else {
            obtain.obj = new AIUIEvent(8, 13, 0, "sync data success. dataType=" + i2, bundle);
        }
        a(obtain, false);
    }
}
