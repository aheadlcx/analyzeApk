package com.sina.weibo.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.WbAppInfo;
import com.sina.weibo.sdk.utils.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONObject;

public class WeiboAppManager {
    private static final String a = WeiboAppManager.class.getName();
    private static final Uri b = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    private static final Uri c = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    private static WeiboAppManager d;
    private Context e;
    private WbAppInfo f;

    private WeiboAppManager(Context context) {
        this.e = context.getApplicationContext();
    }

    public static synchronized WeiboAppManager getInstance(Context context) {
        WeiboAppManager weiboAppManager;
        synchronized (WeiboAppManager.class) {
            if (d == null) {
                d = new WeiboAppManager(context);
            }
            weiboAppManager = d;
        }
        return weiboAppManager;
    }

    public synchronized WbAppInfo getWbAppInfo() {
        if (this.f == null) {
            this.f = a(this.e);
        }
        return this.f;
    }

    private WbAppInfo a(Context context) {
        Object obj = 1;
        WbAppInfo b = b(context);
        WbAppInfo c = c(context);
        Object obj2 = b != null ? 1 : null;
        if (c == null) {
            obj = null;
        }
        if (obj2 == null || obj == null) {
            if (obj2 != null) {
                return b;
            }
            if (obj != null) {
                return c;
            }
            return null;
        } else if (b.getSupportVersion() >= c.getSupportVersion()) {
            return b;
        } else {
            return c;
        }
    }

    private WbAppInfo b(Context context) {
        Exception e;
        Throwable th;
        ContentResolver contentResolver = context.getContentResolver();
        Cursor query;
        try {
            Cursor query2 = contentResolver.query(b, null, null, null, null);
            if (query2 == null) {
                try {
                    query = contentResolver.query(c, null, null, null, null);
                    if (query == null) {
                        if (query != null) {
                            query.close();
                        }
                        return null;
                    }
                } catch (Exception e2) {
                    e = e2;
                    query = query2;
                    try {
                        LogUtil.e(a, e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query = query2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            query = query2;
            try {
                int columnIndex = query.getColumnIndex("support_api");
                int columnIndex2 = query.getColumnIndex("package");
                int columnIndex3 = query.getColumnIndex("sso_activity");
                if (query.moveToFirst()) {
                    int parseInt;
                    Object string;
                    int i = -1;
                    try {
                        parseInt = Integer.parseInt(query.getString(columnIndex));
                    } catch (NumberFormatException e3) {
                        e3.printStackTrace();
                        parseInt = i;
                    }
                    String string2 = query.getString(columnIndex2);
                    if (columnIndex3 > 0) {
                        string = query.getString(columnIndex3);
                    } else {
                        string = null;
                    }
                    if (!TextUtils.isEmpty(string2) && ApiUtils.validateWeiboSign(context, string2)) {
                        WbAppInfo wbAppInfo = new WbAppInfo();
                        wbAppInfo.setPackageName(string2);
                        wbAppInfo.setSupportVersion(parseInt);
                        if (!TextUtils.isEmpty(string)) {
                            wbAppInfo.setAuthActivityName(string);
                        }
                        if (query == null) {
                            return wbAppInfo;
                        }
                        query.close();
                        return wbAppInfo;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e4) {
                e = e4;
                LogUtil.e(a, e.getMessage());
                if (query != null) {
                    query.close();
                }
                return null;
            }
        } catch (Exception e5) {
            e = e5;
            query = null;
            LogUtil.e(a, e.getMessage());
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return null;
    }

    private WbAppInfo c(Context context) {
        WbAppInfo wbAppInfo = null;
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (!(queryIntentServices == null || queryIntentServices.isEmpty())) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (!(resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.applicationInfo == null || TextUtils.isEmpty(resolveInfo.serviceInfo.packageName))) {
                    WbAppInfo parseWbInfoByAsset = parseWbInfoByAsset(resolveInfo.serviceInfo.packageName);
                    if (parseWbInfoByAsset == null) {
                        parseWbInfoByAsset = wbAppInfo;
                    }
                    wbAppInfo = parseWbInfoByAsset;
                }
            }
        }
        return wbAppInfo;
    }

    public WbAppInfo parseWbInfoByAsset(String str) {
        Exception e;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        InputStream open;
        try {
            byte[] bArr = new byte[4096];
            open = this.e.createPackageContext(str, 2).getAssets().open("weibo_for_sdk.json");
            try {
                int read;
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    read = open.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read));
                }
                if (!TextUtils.isEmpty(stringBuilder.toString()) && ApiUtils.validateWeiboSign(this.e, str)) {
                    JSONObject jSONObject = new JSONObject(stringBuilder.toString());
                    read = jSONObject.optInt("support_api", -1);
                    WbAppInfo wbAppInfo = new WbAppInfo();
                    wbAppInfo.setPackageName(str);
                    wbAppInfo.setSupportVersion(read);
                    wbAppInfo.setAuthActivityName(jSONObject.optString("authActivityName", "com.sina.weibo.SSOActivity"));
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e2) {
                        }
                    }
                    return wbAppInfo;
                } else if (open == null) {
                    return null;
                } else {
                    try {
                        open.close();
                        return null;
                    } catch (IOException e3) {
                        return null;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                try {
                    LogUtil.e(a, e.getMessage());
                    if (open != null) {
                        return null;
                    }
                    try {
                        open.close();
                        return null;
                    } catch (IOException e5) {
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e7) {
            e = e7;
            open = null;
            LogUtil.e(a, e.getMessage());
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
