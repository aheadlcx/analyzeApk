package com.budejie.www.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.NewCommentItem;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.elves.update.a;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class b {
    static Handler a = new b$3();
    private static HashMap<String, SoftReference<Drawable>> b = new HashMap();
    private static a c;
    private static Map<String, NewCommentItem> d = new HashMap();

    public static synchronized void a(Context context, net.tsz.afinal.a.b bVar, String str, int i, String str2) {
        synchronized (b.class) {
            aa.e("wuzhenlin", bVar.toString());
            c = new a(context);
            c.a(i, context.getString(R.string.repint_sending));
            net.tsz.afinal.b bVar2 = new net.tsz.afinal.b(context.getApplicationContext(), new v(context));
            bVar2.a(300000);
            bVar2.a("User-Agent", new WebView(context).getSettings().getUserAgentString() + NetWorkUtil.a());
            bVar2.a("cookie", NetWorkUtil.b(context));
            bVar2.b("http://api.budejie.com/api/api_open.php?c=topic&a=createugc", bVar, new b$1(i, context, str, context, str2));
        }
    }

    public static synchronized void a(Context context, net.tsz.afinal.a.b bVar, String str, int i) {
        synchronized (b.class) {
            aa.e("wuzhenlin", bVar.toString());
            c = new a(context);
            c.a(i, context.getString(R.string.tougao_sending));
            net.tsz.afinal.b bVar2 = new net.tsz.afinal.b(context.getApplicationContext(), new v(context));
            bVar2.a(300000);
            bVar2.a(NetWorkUtil.a(context));
            bVar2.a("cookie", NetWorkUtil.b(context));
            bVar2.b(com.lt.a.a(context).a("http://api.budejie.com/api/api_open.php?c=topic&a=createugc"), bVar, new b$2(i, context, str, context));
        }
    }

    public static void a(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    public static void a(Context context, String str, net.tsz.afinal.a.a aVar) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().d(context, str), aVar);
    }

    public static void b(Context context, String str, net.tsz.afinal.a.a aVar) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().e(context, str), aVar);
    }

    public static void a(Activity activity, String str) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().g(activity, str), new b$4(activity));
    }

    public static void a(Activity activity) {
        BudejieApplication.a.a(RequstMethod.GET, j.q(), new j(activity), new b$5(activity));
        BudejieApplication.a.a(RequstMethod.GET, j.r(), new j(activity), new b$6(activity));
    }
}
