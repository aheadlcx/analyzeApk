package com.tencent.open.yyb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.ali.auth.third.login.LoginConstants;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import java.io.IOException;
import java.io.InputStream;

public class a {

    public static class a {
        public String a;
        public String b;
        public long c;
    }

    private static class b extends AsyncTask<Bundle, Void, Void> {
        private b() {
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Bundle[]) objArr);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.lang.Void a(android.os.Bundle... r6) {
            /*
            r5 = this;
            r4 = 0;
            if (r6 != 0) goto L_0x0004;
        L_0x0003:
            return r4;
        L_0x0004:
            r1 = "http://analy.qq.com/cgi-bin/mapp_apptrace";
            r0 = r6.length;
            r2 = 2;
            if (r0 != r2) goto L_0x0054;
        L_0x000a:
            r0 = 1;
            r0 = r6[r0];
            r2 = "uri";
            r0 = r0.getString(r2);
            r2 = android.text.TextUtils.isEmpty(r0);
            if (r2 != 0) goto L_0x0054;
        L_0x0019:
            r1 = 0;
            r2 = "GET";
            r3 = 0;
            r3 = r6[r3];	 Catch:{ Exception -> 0x0048 }
            r0 = com.tencent.open.utils.HttpUtils.openUrl2(r1, r0, r2, r3);	 Catch:{ Exception -> 0x0048 }
            r0 = r0.response;	 Catch:{ Exception -> 0x0048 }
            r0 = com.tencent.open.utils.Util.parseJson(r0);	 Catch:{ Exception -> 0x0048 }
            r1 = "ret";
            r0 = r0.getInt(r1);	 Catch:{ Exception -> 0x0048 }
            r1 = "openSDK_LOG.AppbarUtil";
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0048 }
            r2.<init>();	 Catch:{ Exception -> 0x0048 }
            r3 = "-->(ViaAsyncTask)doInBackground : ret = ";
            r2 = r2.append(r3);	 Catch:{ Exception -> 0x0048 }
            r0 = r2.append(r0);	 Catch:{ Exception -> 0x0048 }
            r0 = r0.toString();	 Catch:{ Exception -> 0x0048 }
            com.tencent.open.a.f.b(r1, r0);	 Catch:{ Exception -> 0x0048 }
            goto L_0x0003;
        L_0x0048:
            r0 = move-exception;
            r1 = "openSDK_LOG.AppbarUtil";
            r2 = "-->(ViaAsyncTask)doInBackground : Exception = ";
            com.tencent.open.a.f.b(r1, r2, r0);
            r0.printStackTrace();
            goto L_0x0003;
        L_0x0054:
            r0 = r1;
            goto L_0x0019;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.yyb.a.b.a(android.os.Bundle[]):java.lang.Void");
        }
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str)) {
            CookieSyncManager.createInstance(context);
            CookieManager instance = CookieManager.getInstance();
            instance.setAcceptCookie(true);
            String str5 = null;
            if (Uri.parse(str).getHost().toLowerCase().endsWith(".qq.com")) {
                str5 = ".qq.com";
            }
            instance.setCookie(str, b("logintype", "MOBILEQ", str5));
            instance.setCookie(str, b("qopenid", str2, str5));
            instance.setCookie(str, b("qaccesstoken", str3, str5));
            instance.setCookie(str, b("openappid", str4, str5));
            CookieSyncManager.getInstance().sync();
        }
    }

    private static String b(String str, String str2, String str3) {
        String str4 = str + LoginConstants.EQUAL + str2;
        if (str3 == null) {
            return str4;
        }
        return (str4 + "; path=/") + "; domain=" + str3;
    }

    public static Drawable a(String str, Context context) {
        return a(str, context, new Rect(0, 0, 0, 0));
    }

    public static Drawable a(String str, Context context, Rect rect) {
        InputStream open;
        Drawable ninePatchDrawable;
        Throwable e;
        InputStream inputStream;
        Context applicationContext = context.getApplicationContext();
        try {
            open = applicationContext.getAssets().open(str);
            if (open != null) {
                try {
                    if (str.endsWith(".9.png")) {
                        Bitmap decodeStream = BitmapFactory.decodeStream(open);
                        if (decodeStream != null) {
                            ninePatchDrawable = new NinePatchDrawable(applicationContext.getResources(), decodeStream, decodeStream.getNinePatchChunk(), rect, null);
                        } else if (open == null) {
                            return null;
                        } else {
                            try {
                                open.close();
                                return null;
                            } catch (Throwable e2) {
                                e2.printStackTrace();
                                f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : IOException", e2);
                                return null;
                            }
                        }
                    }
                    ninePatchDrawable = Drawable.createFromStream(open, str);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable e3) {
                            e3.printStackTrace();
                            f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : IOException", e3);
                        }
                    }
                } catch (OutOfMemoryError e4) {
                    e2 = e4;
                    inputStream = open;
                    try {
                        e2.printStackTrace();
                        f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : OutOfMemoryError", e2);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                                ninePatchDrawable = null;
                            } catch (Throwable e22) {
                                e22.printStackTrace();
                                f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : IOException", e22);
                                ninePatchDrawable = null;
                            }
                            return ninePatchDrawable;
                        }
                        ninePatchDrawable = null;
                        return ninePatchDrawable;
                    } catch (Throwable th) {
                        e22 = th;
                        open = inputStream;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (Throwable e32) {
                                e32.printStackTrace();
                                f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : IOException", e32);
                            }
                        }
                        throw e22;
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    try {
                        e22.printStackTrace();
                        f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : IOException", e22);
                        if (open != null) {
                            try {
                                open.close();
                                ninePatchDrawable = null;
                            } catch (Throwable e222) {
                                e222.printStackTrace();
                                f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : IOException", e222);
                                ninePatchDrawable = null;
                            }
                            return ninePatchDrawable;
                        }
                        ninePatchDrawable = null;
                        return ninePatchDrawable;
                    } catch (Throwable th2) {
                        e222 = th2;
                        if (open != null) {
                            open.close();
                        }
                        throw e222;
                    }
                }
                return ninePatchDrawable;
            } else if (open == null) {
                return null;
            } else {
                try {
                    open.close();
                    return null;
                } catch (Throwable e2222) {
                    e2222.printStackTrace();
                    f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : IOException", e2222);
                    return null;
                }
            }
        } catch (OutOfMemoryError e6) {
            e2222 = e6;
            inputStream = null;
            e2222.printStackTrace();
            f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : OutOfMemoryError", e2222);
            if (inputStream != null) {
                inputStream.close();
                ninePatchDrawable = null;
                return ninePatchDrawable;
            }
            ninePatchDrawable = null;
            return ninePatchDrawable;
        } catch (IOException e7) {
            e2222 = e7;
            open = null;
            e2222.printStackTrace();
            f.b("openSDK_LOG.AppbarUtil", "-->(AppbarUtil)getDrawable : IOException", e2222);
            if (open != null) {
                open.close();
                ninePatchDrawable = null;
                return ninePatchDrawable;
            }
            ninePatchDrawable = null;
            return ninePatchDrawable;
        } catch (Throwable th3) {
            e2222 = th3;
            open = null;
            if (open != null) {
                open.close();
            }
            throw e2222;
        }
    }

    public static void a(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("uin", Constants.DEFAULT_UIN);
        bundle.putString("action", str2);
        bundle.putString("appid", str);
        bundle.putString("via", str3);
        new b().execute(new Bundle[]{bundle});
    }
}
