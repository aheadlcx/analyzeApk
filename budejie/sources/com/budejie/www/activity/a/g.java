package com.budejie.www.activity.a;

import android.app.Activity;
import android.text.TextUtils;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.http.j;
import com.budejie.www.widget.ExpertXListView;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.a;
import net.tsz.afinal.a.b;

public class g extends d {
    public List<ListItemObject> a = new ArrayList();
    private PersonalProfileActivity b;
    private e c;
    private String d;
    private String e = "1";
    private long f;
    private ExpertXListView g;
    private b h;
    private String i = SocialConstants.PARAM_APP_DESC;
    private a<String> j = new g$1(this);
    private a<String> k = new g$2(this);

    public g(Activity activity) {
        if (activity instanceof PersonalProfileActivity) {
            this.b = (PersonalProfileActivity) activity;
            this.g = this.b.d();
            this.d = this.b.c();
            this.h = new b();
        }
    }

    public void b(String str) {
        this.d = str;
    }

    public void a(e eVar) {
        this.c = eVar;
    }

    public void c(String str) {
        this.i = str;
        a();
    }

    public void a() {
        this.h.a(1);
        b jVar = new j(this.b);
        if (this.b.f() || this.c.a()) {
            jVar.d("show_top", "1");
        }
        BudejieApplication.a.a(RequstMethod.GET, a(this.d, 0, this.e, this.i), jVar, this.j);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.e;
        }
        this.e = str;
        a();
    }

    public void a(Object... objArr) {
        this.h.a(3);
        BudejieApplication.a.a(RequstMethod.GET, a(this.d, this.f, this.e, this.i), new j(this.b), this.k);
    }

    public b b() {
        return this.h;
    }

    private String a(String str, long j, String str2, String str3) {
        h hVar = new h();
        if (this.b.f() || this.c.a()) {
            hVar.a("http://d.api.budejie.com/topic/my-topic").a(j).a();
        } else {
            hVar.a("http://s.budejie.com/topic/user-topic").d(str).d(str2).d(str3).a(j).a();
        }
        return hVar.toString();
    }
}
