package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.utils.UMUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class a {
    private static Context a = null;
    private String b;
    private String c;

    private static class a {
        private static final a a = new a();
    }

    private a() {
        this.b = null;
        this.c = null;
    }

    public static a a(Context context) {
        if (a == null && context != null) {
            a = context.getApplicationContext();
        }
        return a.a;
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("a");
    }

    public void b(String str) {
        String substring = str.substring(0, str.indexOf(95));
        d(substring);
        c(substring);
    }

    private void c(String str) {
        try {
            String replaceAll = str.replaceAll("&=", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).replaceAll("&&", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).replaceAll("==", MqttTopic.TOPIC_LEVEL_SEPARATOR);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(replaceAll).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append("Android").append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(Build.DISPLAY).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(Build.MODEL).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(VERSION.RELEASE).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(HelperUtils.getUmengMD5(UMUtils.getAppkey(a)));
            this.b = stringBuilder.toString();
        } catch (Throwable th) {
            b.a(a, th);
        }
    }

    private void d(String str) {
        try {
            Object obj = str.split("&&")[0];
            if (!TextUtils.isEmpty(obj)) {
                String[] split = obj.split("&=");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(g.av);
                for (Object obj2 : split) {
                    if (!TextUtils.isEmpty(obj2)) {
                        String substring = obj2.substring(0, 2);
                        if (substring.endsWith("=")) {
                            substring = substring.replace("=", "");
                        }
                        stringBuilder.append(substring);
                    }
                }
                this.c = stringBuilder.toString();
            }
        } catch (Throwable th) {
            b.a(a, th);
        }
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }
}
