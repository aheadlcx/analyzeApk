package com.b.a.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.b.a.b.a.a;
import com.b.a.b.a.c;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.meizu.cloud.pushsdk.pushtracer.storage.EventStoreHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class b implements Runnable {
    private Context a;
    private String b;
    private String c;
    private long d;

    public b(Context context, String str, String str2, long j) {
        this.a = context;
        this.b = str.replace(",", "^");
        this.c = str2.replace(",", "^");
        this.d = j;
    }

    public final void run() {
        try {
            SharedPreferences a = c.a(this.a, "state");
            if (a == null) {
                a.h();
                return;
            }
            Object string = a.getString(EventStoreHelper.TABLE_EVENTS, "");
            if (!"".equals(string)) {
                string = new StringBuilder(String.valueOf(string)).append(VoiceWakeuperAidl.PARAMS_SEPARATE).toString();
            }
            String stringBuilder = new StringBuilder(String.valueOf(string)).append(this.b).append(",").append(this.c).append(",").append(new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date(this.d))).toString();
            if (stringBuilder.split(VoiceWakeuperAidl.PARAMS_SEPARATE).length <= a.d()) {
                Editor edit = a.edit();
                edit.remove(EventStoreHelper.TABLE_EVENTS);
                edit.putString(EventStoreHelper.TABLE_EVENTS, stringBuilder);
                edit.commit();
                a.h();
            }
            if (!a.d(this.a)) {
                return;
            }
            if (a.e()) {
                a.h();
                a.a(this.a);
                return;
            }
            a.edit().remove(EventStoreHelper.TABLE_EVENTS).commit();
        } catch (Exception e) {
            e.getMessage();
            a.h();
            e.printStackTrace();
        }
    }
}
