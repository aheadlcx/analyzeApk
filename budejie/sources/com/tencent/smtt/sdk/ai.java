package com.tencent.smtt.sdk;

import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.tencent.smtt.export.external.TbsCoreSettings;
import java.util.HashMap;
import java.util.Map;

class ai {
    public boolean a;
    public boolean b;
    private Map<String, String> c;

    public ai() {
        this.a = false;
        this.b = false;
        this.c = null;
        this.c = new HashMap();
    }

    public synchronized void a(int i, String str) {
        bi b = bi.b();
        if (b.c() && str != null) {
            if (str.startsWith(UriUtil.HTTP_SCHEME) && ((this.b && this.a) || System.currentTimeMillis() % 10 == 0)) {
                this.c.put("is_first_init_tbs", String.valueOf(this.b));
                this.c.put("is_first_init_x5", String.valueOf(this.a));
                this.c.put("x5_webview_id", Integer.toString(i));
                this.c.put("current_url", str);
                if (QbSdk.k != null && QbSdk.k.containsKey(TbsCoreSettings.TBS_SETTINGS_APP_SCENE_ID)) {
                    this.c.put(TbsCoreSettings.TBS_SETTINGS_APP_SCENE_ID, "" + QbSdk.k.get(TbsCoreSettings.TBS_SETTINGS_APP_SCENE_ID));
                }
                b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "setTbsInitPerformanceData", new Class[]{Integer.TYPE, Map.class}, Integer.valueOf(i), this.c);
            }
        }
    }

    public synchronized void a(String str, byte b) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = "";
            if (b == (byte) 1) {
                str2 = "_begin";
            } else if (b == (byte) 2) {
                str2 = "_end";
            }
            this.c.put(str + str2, String.valueOf(System.currentTimeMillis()));
        }
    }
}
