package com.budejie.www.http;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.WebView;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.sdk.util.h;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.f.a;
import com.budejie.www.util.aa;
import com.budejie.www.util.af;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.budejie.www.util.v;
import com.tencent.open.SocialConstants;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.security.KeyStore;
import java.util.List;
import net.tsz.afinal.b;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

public class NetWorkUtil {
    public static CookieStore a;
    private b b;
    private long c;
    private Context d;

    public enum RequstMethod {
        POST,
        GET
    }

    public NetWorkUtil(Context context) {
        this.d = context;
        this.b = new b(context, new v(context));
        this.b.a(30000);
        this.b.b(0);
        this.b.a("utf-8");
        this.b.a("User-Agent", new WebView(context).getSettings().getUserAgentString() + a());
        this.b.a(a(context));
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            this.b.a(new k(instance));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2) {
        this.b.a(str, str2);
    }

    public static CookieStore a(Context context) {
        if (a == null) {
            a = new af(context);
        }
        return a;
    }

    public static String a(Context context, String str) {
        List cookies = a(context).getCookies();
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = (Cookie) cookies.get(i);
            if (str.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }

    public static String b(Context context) {
        List cookies = a(context).getCookies();
        if (cookies == null || cookies.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = (Cookie) cookies.get(i);
            String name = cookie.getName();
            String value = cookie.getValue();
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(value) && an.h(name) && an.h(value)) {
                stringBuilder.append(name).append(LoginConstants.EQUAL);
                stringBuilder.append(value).append(h.b);
            }
        }
        return stringBuilder.toString();
    }

    public void a(Context context, String str, String str2, net.tsz.afinal.a.b bVar, a aVar, Handler handler, int i) {
        a(context, str, str2, bVar, aVar, handler, i, null);
    }

    public void a(Context context, String str, String str2, net.tsz.afinal.a.b bVar, a aVar, Handler handler, int i, Bundle bundle) {
        if (an.a(context)) {
            b(context, str, str2, bVar, aVar, handler, i, bundle);
        }
    }

    public static String b(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (str.contains("?")) {
            stringBuilder.append("&");
        } else {
            stringBuilder.append("?");
        }
        stringBuilder.append("ver").append(LoginConstants.EQUAL).append("6.9.2").append("&");
        stringBuilder.append("client").append(LoginConstants.EQUAL).append(AlibcConstants.PF_ANDROID).append("&");
        stringBuilder.append("market").append(LoginConstants.EQUAL).append("xiaomi").append("&");
        stringBuilder.append("udid").append(LoginConstants.EQUAL).append(an.e(context)).append("&");
        stringBuilder.append("appname").append(LoginConstants.EQUAL).append("baisibudejie").append("&");
        stringBuilder.append("mac").append(LoginConstants.EQUAL).append(an.h(context)).append("&");
        stringBuilder.append("runtimecan").append(LoginConstants.EQUAL).append("1");
        Object b = ai.b(context);
        if (!TextUtils.isEmpty(b)) {
            stringBuilder.append("&").append(HistoryOpenHelper.COLUMN_UID).append(LoginConstants.EQUAL).append(b);
        }
        return stringBuilder.toString();
    }

    public static String a() {
        return " budejie/6.9.2 (" + Build.MODEL + ") android/" + VERSION.RELEASE;
    }

    public void b(Context context, String str, String str2, net.tsz.afinal.a.b bVar, a aVar, Handler handler, int i, Bundle bundle) {
        try {
            if (an.a(context)) {
                String a = com.lt.a.a(context).a(str);
                if ("get".equals(str2)) {
                    this.b.a(a, bVar, new NetWorkUtil$a(this, i, aVar, handler, bundle, a, context, null));
                } else if ("post".equals(str2)) {
                    this.b.b(a, bVar, new NetWorkUtil$a(this, i, aVar, handler, bundle, a, context, null));
                }
            }
        } catch (OutOfMemoryError e) {
            aa.e("connet", "OutOfMemoryError: " + e.getMessage());
        } catch (Exception e2) {
        }
    }

    public void a(Context context, String str, a aVar, int i) {
        if (!an.i(UpdateConfig.f)) {
            p.a(context);
        } else if (!TextUtils.isEmpty(str)) {
            String str2 = Environment.getExternalStorageDirectory().toString() + "/budejie/" + str.substring(7).replace("/", "-");
            if (!new File(str2).exists()) {
                this.b.a(com.lt.a.a(this.d).a(str), str2, true, new NetWorkUtil$2(this, str2, aVar, i));
            } else if (aVar != null) {
                aVar.a(i, Constants.SERVICE_SCOPE_FLAG_VALUE);
            }
        } else if (aVar != null) {
            aVar.a(i);
        }
    }

    public void a(Context context, String str, net.tsz.afinal.a.b bVar, a aVar, int i) {
        try {
            String a = com.lt.a.a(context).a(str);
            if (an.a(context)) {
                this.b.a(a, bVar, new NetWorkUtil$a(this, i, aVar, null, null, a, context, null));
            }
            aa.a("LY", "已经置null了");
        } catch (OutOfMemoryError e) {
            aa.e("connet", "OutOfMemoryError: " + e.getMessage());
        } catch (Exception e2) {
        }
    }

    public void a(Context context, String str, Handler handler, int i, Bundle bundle) {
        try {
            if (an.a(context)) {
                String a = com.lt.a.a(context).a(str);
                aa.a("NetWorkUtiluploadAdInfo", "请求：uploadAdInfo");
                this.b.a(a, new NetWorkUtil$a(this, i, null, handler, bundle, a, context, null));
            }
        } catch (OutOfMemoryError e) {
            aa.e("connet", "OutOfMemoryError: " + e.getMessage());
        } catch (Exception e2) {
            aa.e("connet", "exception: " + e2.getMessage());
        }
    }

    public void a(long j, String str) {
        net.tsz.afinal.a.b bVar = new net.tsz.afinal.a.b();
        bVar.d(SocialConstants.PARAM_APP_DESC, "time:" + j + " url:" + com.lt.a.a(this.d).a(str));
        bVar.d("nt", an.d(this.d));
        this.b.b("http://log.spriteapp.com/report/stats/", bVar, new NetWorkUtil$a(this, -1, null, null, null, null, null, null));
    }

    public void a(RequstMethod requstMethod, String str, net.tsz.afinal.a.b bVar, net.tsz.afinal.a.a<?> aVar) {
        if (!com.lt.a.b()) {
            str = com.lt.a.a(this.d).a(str);
        }
        switch (NetWorkUtil$4.a[requstMethod.ordinal()]) {
            case 1:
                this.b.b(str, bVar, aVar);
                return;
            case 2:
                this.b.a(str, bVar, aVar);
                return;
            default:
                return;
        }
    }

    public static void a(Context context, String str, net.tsz.afinal.a.a<?> aVar) {
        BudejieApplication.a.a(RequstMethod.POST, j.e(str), new j(context), (net.tsz.afinal.a.a) aVar);
    }

    public static void c(Context context, String str) {
        a(context, str, new NetWorkUtil$3(context));
    }
}
