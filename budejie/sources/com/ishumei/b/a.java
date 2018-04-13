package com.ishumei.b;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.wireless.security.SecExceptionCode;
import com.ishumei.e.b.b;
import com.ishumei.f.c;
import com.ishumei.f.d;
import com.ishumei.f.e;
import com.ishumei.f.f;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONObject;

public class a {
    private b a;
    private String b;
    private String c;
    private Context d = null;
    private b e = new b(this, false, 2) {
        final /* synthetic */ a a;

        public void a(String str) {
            b a = this.a.b(str);
            if (a != null) {
                this.a.a = a;
            }
        }

        public boolean a(String str, int i) {
            super.a(str, i);
            return false;
        }
    };

    public a(String str, String str2) {
        c.b("CloudConfiguration", "CloudConfiguration()");
        this.d = d.a;
        this.b = str;
        if (this.d != null) {
            this.a = c();
        }
        this.c = str2;
    }

    private b a(Context context) {
        int i = 0;
        String b = b(context);
        if (d.d(b)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(b);
        int i2 = jSONObject.getInt("length");
        int i3 = jSONObject.has("enc") ? jSONObject.getInt("enc") : 0;
        if (jSONObject.has("ver")) {
            i = jSONObject.getInt("ver");
        }
        b a = a(jSONObject.getString("data"), i2, i3, i);
        a.a("local");
        return a;
    }

    private b a(String str, int i, int i2, int i3) {
        if (str == null) {
            return null;
        }
        try {
            String str2;
            byte[] g = e.g(str);
            if (i2 == 1) {
                byte[] a = f.a(com.ishumei.f.b.b("zaq1mko0", g, i));
                str2 = new String(a, 0, a.length, "utf-8");
            } else {
                str2 = com.ishumei.f.b.a("zaq1mko0", g, i);
            }
            return i3 == 1 ? b.d(str2) : b.e(str2);
        } catch (Exception e) {
            return null;
        }
    }

    private void a(Context context, String str) {
        Editor edit = context.getSharedPreferences("cloudms.conf", 0).edit();
        edit.putString("conf", str);
        if (!edit.commit()) {
            throw new IOException("editor commit failed");
        }
    }

    private void a(String str, b bVar, com.ishumei.e.a aVar) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("organization", this.b);
            Map hashMap2 = new HashMap();
            hashMap2.put("os", AlibcConstants.PF_ANDROID);
            hashMap2.put("sdkver", "2.3.6");
            hashMap2.put("md5", str);
            hashMap2.put("enc", new Integer(1));
            hashMap.put("data", hashMap2);
            new com.ishumei.e.b().a(aVar).a(e.a(hashMap).toString().getBytes("utf-8"), null, a(), bVar);
        } catch (Exception e) {
        }
    }

    private b b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (SecExceptionCode.SEC_ERROR_OPENSDK != jSONObject.getInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                return null;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(AlibcConstants.DETAIL);
            if (jSONObject2 == null || jSONObject2.getInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) != 0) {
                return null;
            }
            int i = jSONObject2.getInt("length");
            int i2 = jSONObject2.has("enc") ? jSONObject2.getInt("enc") : 0;
            int i3 = jSONObject2.has("ver") ? jSONObject2.getInt("ver") : 0;
            String string = jSONObject2.getString("data");
            b a = a(string, i, i2, i3);
            a.a("cloud");
            Map hashMap = new HashMap();
            hashMap.put("data", string);
            hashMap.put("length", Integer.valueOf(i));
            hashMap.put("enc", Integer.valueOf(i2));
            hashMap.put("ver", Integer.valueOf(i3));
            a(this.d, e.a(hashMap).toString());
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    private String b(Context context) {
        String string = context.getSharedPreferences("cloudms.conf", 0).getString("conf", null);
        d.a(string);
        return string;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.ishumei.b.b c() {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.d;	 Catch:{ Exception -> 0x000b }
        r0 = r4.a(r0);	 Catch:{ Exception -> 0x000b }
        if (r0 == 0) goto L_0x0020;
    L_0x0009:
        monitor-exit(r4);
        return r0;
    L_0x000b:
        r0 = move-exception;
        r1 = "CloudConfiguration";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x002a }
        r3 = "load local cloud conf failed: ";
        r2.<init>(r3);	 Catch:{ all -> 0x002a }
        r0 = r2.append(r0);	 Catch:{ all -> 0x002a }
        r0 = r0.toString();	 Catch:{ all -> 0x002a }
        com.ishumei.f.c.d(r1, r0);	 Catch:{ all -> 0x002a }
    L_0x0020:
        r0 = r4.d();	 Catch:{ Exception -> 0x0027 }
        if (r0 == 0) goto L_0x0009;
    L_0x0026:
        goto L_0x0009;
    L_0x0027:
        r0 = move-exception;
        r0 = 0;
        goto L_0x0009;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.b.a.c():com.ishumei.b.b");
    }

    private b d() {
        int i = 0;
        JSONObject jSONObject = new JSONObject("{\"code\":0,\"data\":\"MJTJI5pLpagmDO0m7YjE9/mNe30YhZ+aB1Ith2DNdfwh4Ks4MYV1VaGHhO5NBT8zR7il+JsZx1WIRcYY1to9hUoI3ivAP3s4nIXtgfSnCmuk42NaN2gN8wP5OroOz0eRoXxfLQnybxKwRpdhZFo7sktFY3pJqXVgEt7FDqzowyQt+IBet66MeNvTsjJD3Y4BFtarsmZhgz7eieidRt8z0SKUIZLlR5/9Gs6cS0WCMAWEuEHlYS1lPDJ/oPLA8h3eIl2MaG0Vv6KPxCSDzz4NQJF/2eihljsM1PUKD2bwPYV0LhM224HDVd/mkJrkSOvWTxXDmZHTyp8O2AwuXhtzucsjzr03xLUcfmkvxWbXjF/+oxxql/52pgfQARD3/mJ94VwXIQJKxG+BzMPWD2uyeR+KOzm0OZNfRmBNDu4j78Z1HJARnfFTz+T6WpS8yy8tSVJDhXPfK8Ki5sHOLRkSnYJD0mMs9a00czI/XUvheHw/LmjN2NC24QHhhMfzZIqfmY1Kiz3Uvg6tNgLCDccLgnvJa+WSHjq31C4e+mto1evwaqcckOAUuXx+N5OWdbp8za63MvtenuOuBTYxuAspoQ5IV4SCdD9Mmtcp3Zv4XXumNvKJNqg2zBwdQFTsstAfbqAqJPlMrpgzwVdapwOZiZtBuGcS7uevrAV0SQ3OkqXjIgkNzkZhtsa5H4//pnFt14aq8A6bEMvxLaILpOmlTopl9CTMbWpU/TShAD2YANd7qPToisPOrXQiLEfd/zmD6GZGZHIDrtJmFTwnNAyQd9oCK64vcsBS6FHFOmWLYZVI1BIH+qSWrsYfJiXLcaluam/ZDf2ZltyfK8cKXd5k69CbemMBSaZa2qgPRe8ksu9yHer+VHvUByK1lHppavBGtppT0GmABbZW/kHdSbN8EBOmqgyHeqX00sHH2Hgt+4vqViu3oTtkQg9PP12iVficyYWFj0LTos/P5A9lLrm8pO0pok/SYGNEFUQ3uKf9Y42Y3PKeCicSV2+jwKHVhVTOHXtlLXPbMl4SseJfeMBm5BycNmFxDkB39BcKmUGtkoKRJtH1NzlTO5INRH4f4TZEQrd+fI6y+9Iczzpxsz9QInoSH3WU6bHtuBHRkNkwkNtiPJIsMdfknx3TA/yVgwrIYr1WtrwAIdDOFd0542DUyLzjrGXxrPG75slX/Bwte1xbHJ7TOfmmrA==\",\"enc\":1,\"length\":901,\"ver\":1}");
        int i2 = jSONObject.getInt("length");
        int i3 = jSONObject.has("enc") ? jSONObject.getInt("enc") : 0;
        if (jSONObject.has("ver")) {
            i = jSONObject.getInt("ver");
        }
        b a = a(jSONObject.getString("data"), i2, i3, i);
        a.a(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
        return a;
    }

    public String a() {
        return this.c;
    }

    public void a(com.ishumei.e.a aVar) {
        try {
            b b = b();
            a(b == null ? "" : b.f(), this.e, aVar);
        } catch (Exception e) {
            c.b("CloudConfiguration", e.getMessage());
        }
    }

    public boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) != 0) {
                return false;
            }
            int i = jSONObject.getInt("length");
            int i2 = jSONObject.has("enc") ? jSONObject.getInt("enc") : 0;
            int i3 = jSONObject.has("ver") ? jSONObject.getInt("ver") : 0;
            String string = jSONObject.getString("data");
            a(string, i, i2, i3).a("cloud");
            Map hashMap = new HashMap();
            hashMap.put("data", string);
            hashMap.put("length", Integer.valueOf(i));
            hashMap.put("enc", Integer.valueOf(i2));
            hashMap.put("ver", Integer.valueOf(i3));
            a(this.d, e.a(hashMap).toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized b b() {
        return b.a(this.a);
    }
}
