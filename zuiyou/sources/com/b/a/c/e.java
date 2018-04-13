package com.b.a.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.b.a.b.a.a;
import com.b.a.b.a.c;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.meizu.cloud.pushsdk.pushtracer.storage.EventStoreHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public final class e implements Runnable {
    private Context a;
    private int b;
    private long c;

    public e(Context context, int i, long j) {
        this.a = context;
        this.b = i;
        this.c = j;
    }

    private void a(SharedPreferences sharedPreferences) {
        Editor edit = sharedPreferences.edit();
        edit.putLong("last_millis", this.c);
        edit.commit();
    }

    private static void a(SharedPreferences sharedPreferences, long j) {
        Editor edit = sharedPreferences.edit();
        String valueOf = String.valueOf(j);
        edit.remove(SpeechEvent.KEY_EVENT_SESSION_ID);
        edit.remove("refer_id");
        edit.putString(SpeechEvent.KEY_EVENT_SESSION_ID, valueOf);
        edit.putString("refer_id", "");
        edit.putLong("end_millis", j);
        edit.commit();
    }

    private void b(SharedPreferences sharedPreferences) {
        JSONObject jSONObject = new JSONObject();
        Context context = this.a;
        StringBuffer stringBuffer = new StringBuffer("");
        SharedPreferences a = c.a(context, "sessioncontext");
        String string = a.getString(SpeechEvent.KEY_EVENT_SESSION_ID, "");
        if ("".equals(string)) {
            long currentTimeMillis = System.currentTimeMillis();
            string = String.valueOf(currentTimeMillis);
            Editor edit = a.edit();
            edit.putString(SpeechEvent.KEY_EVENT_SESSION_ID, string);
            edit.putLong("end_millis", currentTimeMillis);
            edit.commit();
        }
        String str = string;
        String string2 = a.getString("refer_id", "");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            a.h();
            Object obj = null;
        } else {
            stringBuffer.append(a.c(context)[0]).append(",").append(telephonyManager.getNetworkOperatorName().replace(',', '&')).append(",").append(str).append(",").append(string2);
            str = stringBuffer.toString();
        }
        if (obj != null) {
            try {
                String[] split;
                JSONArray jSONArray;
                boolean z;
                if (sharedPreferences.getString("activities", "").trim().length() > 0) {
                    split = sharedPreferences.getString("activities", "").split(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    jSONArray = new JSONArray();
                    for (Object put : split) {
                        jSONArray.put(put);
                    }
                    jSONObject.put("b", jSONArray);
                    z = false;
                } else {
                    z = true;
                }
                if (sharedPreferences.getString(EventStoreHelper.TABLE_EVENTS, "").trim().length() > 0) {
                    split = sharedPreferences.getString(EventStoreHelper.TABLE_EVENTS, "").split(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    jSONArray = new JSONArray();
                    for (Object put2 : split) {
                        jSONArray.put(put2);
                    }
                    jSONObject.put(Parameters.EVENT, jSONArray);
                    z = false;
                }
                jSONObject.put("h", obj);
                jSONObject.put("type", "termination");
                Handler f = a.f();
                if (f != null) {
                    f.post(new d(this.a, jSONObject, z));
                }
                a.h();
            } catch (Throwable e) {
                Log.e("HiAnalytics", "onTerminate: JSONException.", e);
                e.printStackTrace();
            }
        }
        Editor edit2 = sharedPreferences.edit();
        edit2.putString("activities", "");
        edit2.remove(EventStoreHelper.TABLE_EVENTS);
        edit2.commit();
    }

    private boolean c(SharedPreferences sharedPreferences) {
        return this.c - sharedPreferences.getLong("last_millis", -1) > a.a().longValue() * 1000;
    }

    public final void run() {
        try {
            Context context = this.a;
            long j = this.c;
            SharedPreferences a = c.a(context, "sessioncontext");
            if ("".equals(a.getString(SpeechEvent.KEY_EVENT_SESSION_ID, ""))) {
                a(a, j);
            } else if (j - a.getLong("end_millis", 0) > a.c().longValue() * 1000) {
                a(a, j);
            } else {
                Editor edit = a.edit();
                edit.putLong("end_millis", j);
                edit.commit();
            }
            if (this.b == 0) {
                Context context2 = this.a;
                if (this.a != context2) {
                    a.h();
                    return;
                }
                this.a = context2;
                SharedPreferences a2 = c.a(context2, "state");
                if (a2 != null) {
                    long j2 = a2.getLong("last_millis", -1);
                    if (j2 == -1) {
                        a.h();
                    } else {
                        long j3 = this.c - j2;
                        long j4 = a2.getLong("duration", 0);
                        Editor edit2 = a2.edit();
                        Object string = a2.getString("activities", "");
                        String name = context2.getClass().getName();
                        if (!"".equals(string)) {
                            string = new StringBuilder(String.valueOf(string)).append(VoiceWakeuperAidl.PARAMS_SEPARATE).toString();
                        }
                        String stringBuilder = new StringBuilder(String.valueOf(string)).append(name).append(",").append(new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date(j2))).append(",").append(j3 / 1000).toString();
                        edit2.remove("activities");
                        edit2.putString("activities", stringBuilder);
                        edit2.putLong("duration", j4 + j3);
                        edit2.commit();
                    }
                    if (c(a2)) {
                        b(a2);
                        a(a2);
                    } else if (a.d(context2)) {
                        b(a2);
                        a(a2);
                    }
                }
            } else if (this.b == 1) {
                context = this.a;
                this.a = context;
                a = c.a(context, "state");
                if (a != null && c(a)) {
                    b(a);
                    a(a);
                }
            } else if (this.b == 2) {
                a = c.a(this.a, "state");
                if (a != null) {
                    b(a);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            a.h();
            e.printStackTrace();
        }
    }
}
