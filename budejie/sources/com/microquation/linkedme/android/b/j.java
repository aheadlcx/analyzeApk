package com.microquation.linkedme.android.b;

import android.content.Context;
import android.util.Base64;
import com.ali.auth.third.login.LoginConstants;
import com.microquation.linkedme.android.c.b;
import com.microquation.linkedme.android.f.a;
import com.microquation.linkedme.android.f.c;
import com.microquation.linkedme.android.util.c.f;
import java.util.Collection;
import org.json.JSONObject;

class j extends h implements c {
    private c f;
    private boolean g = true;
    private b h;

    public j(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    private String a(String str) {
        String str2;
        String str3 = str + "?";
        Collection<String> a = this.f.a();
        if (a != null) {
            str2 = str3;
            for (String str32 : a) {
                str32 = (str32 == null || str32.length() <= 0) ? str2 : str2 + f.Tags + LoginConstants.EQUAL + str32 + "&";
                str2 = str32;
            }
        } else {
            str2 = str32;
        }
        str32 = this.f.b();
        if (str32 != null && str32.length() > 0) {
            str2 = str2 + f.Alias + LoginConstants.EQUAL + str32 + "&";
        }
        str32 = this.f.e();
        if (str32 != null && str32.length() > 0) {
            str2 = str2 + f.Channel + LoginConstants.EQUAL + str32 + "&";
        }
        str32 = this.f.f();
        if (str32 != null && str32.length() > 0) {
            str2 = str2 + f.Feature + LoginConstants.EQUAL + str32 + "&";
        }
        str32 = this.f.g();
        if (str32 != null && str32.length() > 0) {
            str2 = str2 + f.Stage + LoginConstants.EQUAL + str32 + "&";
        }
        str32 = (str2 + f.Type + LoginConstants.EQUAL + ((long) this.f.c()) + "&") + f.Duration + LoginConstants.EQUAL + ((long) this.f.d()) + "&";
        str2 = this.f.h();
        if (str2 == null || str2.length() <= 0) {
            return str32;
        }
        return str32 + "source=Android&data=" + Base64.encodeToString(str2.getBytes(), 2);
    }

    private boolean p() {
        return !this.b.l().equals("lkme_no_value");
    }

    public c a() {
        return this.f;
    }

    public void a(int i, String str) {
        if (this.h != null) {
            this.h.a(o(), new a("创建深度链接失败！" + str, i));
        }
    }

    public void a(u uVar, com.microquation.linkedme.android.a aVar) {
        try {
            String string = uVar.b().getString("url");
            if (this.h != null) {
                this.h.a(string, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return (this.g || p()) ? false : true;
        } else {
            if (this.h == null) {
                return true;
            }
            this.h.a(null, new a("创建深度链接失败！", -102));
            return true;
        }
    }

    public void b() {
        if (this.h != null) {
            this.h.a(null, new a("创建深度链接失败！", -105));
        }
    }

    public boolean c() {
        return false;
    }

    public void d() {
        this.h = null;
    }

    public String o() {
        return !this.b.r().equals("lkme_no_value") ? a(this.b.r()) : a("http://lkme.cc/i/" + this.b.h());
    }
}
