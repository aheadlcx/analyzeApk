package com.sina.weibo.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.sina.weibo.sdk.a.d;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONObject;

public class c {
    private static final String a = c.class.getName();
    private static c b;
    private Context c;
    private com.sina.weibo.sdk.auth.c d;

    private c(Context context) {
        this.c = context.getApplicationContext();
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c(context);
            }
            cVar = b;
        }
        return cVar;
    }

    public synchronized com.sina.weibo.sdk.auth.c a() {
        if (this.d == null) {
            this.d = b(this.c);
        }
        return this.d;
    }

    public static com.sina.weibo.sdk.auth.c b(Context context) {
        com.sina.weibo.sdk.auth.c c = c(context);
        return (c != null ? 1 : null) != null ? c : null;
    }

    private static com.sina.weibo.sdk.auth.c c(Context context) {
        com.sina.weibo.sdk.auth.c cVar = null;
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (!(queryIntentServices == null || queryIntentServices.isEmpty())) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (!(resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.applicationInfo == null || TextUtils.isEmpty(resolveInfo.serviceInfo.packageName))) {
                    com.sina.weibo.sdk.auth.c a = a(context, resolveInfo.serviceInfo.packageName);
                    if (a == null) {
                        a = cVar;
                    }
                    cVar = a;
                }
            }
        }
        return cVar;
    }

    private static com.sina.weibo.sdk.auth.c a(Context context, String str) {
        Exception e;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        InputStream open;
        try {
            byte[] bArr = new byte[4096];
            open = context.createPackageContext(str, 2).getAssets().open("weibo_for_sdk.json");
            try {
                int read;
                com.sina.weibo.sdk.auth.c cVar;
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    read = open.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read));
                }
                JSONObject jSONObject;
                if (TextUtils.isEmpty(stringBuilder.toString()) || !a.a(context, str)) {
                    jSONObject = new JSONObject(stringBuilder.toString());
                    read = jSONObject.optInt("support_api", -1);
                    cVar = new com.sina.weibo.sdk.auth.c();
                    cVar.a(str);
                    cVar.a(read);
                    cVar.b(jSONObject.optString("authActivityName", "com.sina.weibo.SSOActivity"));
                } else {
                    jSONObject = new JSONObject(stringBuilder.toString());
                    read = jSONObject.optInt("support_api", -1);
                    cVar = new com.sina.weibo.sdk.auth.c();
                    cVar.a(str);
                    cVar.a(read);
                    cVar.b(jSONObject.optString("authActivityName", "com.sina.weibo.SSOActivity"));
                }
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e2) {
                    }
                }
                return cVar;
            } catch (Exception e3) {
                e = e3;
                try {
                    d.c(a, e.getMessage());
                    if (open != null) {
                        return null;
                    }
                    try {
                        open.close();
                        return null;
                    } catch (IOException e4) {
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            open = null;
            d.c(a, e.getMessage());
            if (open != null) {
                return null;
            }
            open.close();
            return null;
        } catch (Throwable th3) {
            open = null;
            th = th3;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }
}
