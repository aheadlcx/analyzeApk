package com.lt;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.budejie.www.widget.e;
import com.lt.a.b.b;
import com.lt.bean.ProxyResult;
import com.lt.bean.ProxyResult.DataBean.ProxyBean;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a {
    private static a f;
    private static a h;
    private Context a;
    private String b;
    private boolean c;
    private String d;
    private Map<String, String> e;
    private boolean g;

    public interface a {
        void a(boolean z);
    }

    private a(Context context) {
        aa.b("test", "LTManager() ");
        this.a = context.getApplicationContext();
        if (1 != b.d(this.a)) {
            this.b = an.m(this.a);
            if (!TextUtils.isEmpty(this.b)) {
                this.c = true;
                this.d = an.e(this.a, this.b);
                String h = an.h(this.a, this.b);
                if (!TextUtils.isEmpty(h)) {
                    d(h);
                }
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (!simpleDateFormat.format(new Date()).equals(an.l(this.a))) {
                an.g(this.a, simpleDateFormat.format(new Date()));
                c();
            }
        }
    }

    public static a a(Context context) {
        if (f == null) {
            aa.b("test", " new LTManager(mContext) " + context);
            f = new a(context);
        }
        return f;
    }

    public static a a() {
        return f;
    }

    public static void a(a aVar) {
        h = aVar;
    }

    public static boolean b() {
        return f == null;
    }

    public void a(Activity activity) {
        aa.b("test", "startAppCheck()");
        if (this.c && !e()) {
            a(activity, false);
        }
    }

    public void a(Activity activity, boolean z) {
        aa.b("test", "showFreeFlowDialog() flag=" + z);
        if (1 != b.d(this.a)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-");
            Date date = new Date();
            try {
                if ((date.before(simpleDateFormat.parse(simpleDateFormat2.format(date) + "08")) || z) && !an.d(this.a, simpleDateFormat.format(date))) {
                    new e(activity, R.style.dialog, this.b).show();
                    an.c(this.a, simpleDateFormat.format(date));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(boolean z) {
        aa.b("test", "getPhoneNumber(boolean isSubscribeComplete)");
        this.g = z;
        c();
    }

    public void c() {
        aa.b("test", "getPhoneNumber()");
        BudejieApplication.a.a(RequstMethod.GET, com.lt.a.a.a.a(this.a), null, new a$1(this));
    }

    private void c(String str) {
        aa.b("test", "requestPhoneNumUrl()");
        BudejieApplication.a.a(RequstMethod.GET, str, null, new a$2(this));
    }

    public void d() {
        aa.b("test", "getFreeFlowState()");
        BudejieApplication.a.a(RequstMethod.GET, com.lt.a.a.a.b(this.a, this.b), null, new a$3(this));
    }

    private void i() {
        BudejieApplication.a.a(RequstMethod.GET, com.lt.a.a.a.a(this.a, this.b), null, new a$4(this));
    }

    private void d(String str) {
        aa.b("test", "parseProxyMap");
        ProxyResult proxyResult = (ProxyResult) z.a(str, ProxyResult.class);
        if (proxyResult != null && proxyResult.getData() != null) {
            List proxy;
            if ("1".equals(this.d) || "2".equals(this.d) || "3".equals(this.d)) {
                proxy = proxyResult.getData().getProxy();
            } else {
                proxy = null;
            }
            if (r0 != null && r0.size() > 0) {
                this.e = new HashMap();
                for (ProxyBean proxyBean : r0) {
                    this.e.put(proxyBean.getAppDomainName(), proxyBean.getDomainName());
                }
            }
        }
    }

    public String a(String str) {
        aa.b("test", "replaceProxyUrl() url=" + str);
        if (str == null) {
            return "";
        }
        if (!str.startsWith("https:") && this.e != null && e() && f()) {
            try {
                CharSequence host = new URL(str).getHost();
                if (this.e.containsKey(host) && !TextUtils.isEmpty((CharSequence) this.e.get(host))) {
                    str = str.replace(host, (CharSequence) this.e.get(host));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        aa.b("test", "result=" + str);
        return str;
    }

    public String b(String str) {
        aa.b("test", "replaceOriginUrl() url=" + str);
        if (str == null) {
            return "";
        }
        if (!str.startsWith("https:") && this.e != null && e() && f()) {
            try {
                CharSequence host = new URL(str).getHost();
                if (this.e.containsValue(host)) {
                    for (String str2 : this.e.keySet()) {
                        if (host.equals(this.e.get(str2))) {
                            str = str.replace(host, str2);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        aa.b("test", "replaceOriginUrl result=" + str);
        return str;
    }

    public boolean e() {
        aa.b("test", "isFree() state=" + this.d);
        return "1".equals(this.d) || "2".equals(this.d) || "3".equals(this.d);
    }

    public boolean f() {
        return 1 != b.d(this.a);
    }

    public String g() {
        return this.b;
    }
}
