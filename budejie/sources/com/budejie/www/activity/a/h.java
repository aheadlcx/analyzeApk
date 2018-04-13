package com.budejie.www.activity.a;

import android.app.Activity;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.widget.ExpertXListView;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.a;

public class h extends d {
    public List<ListItemObject> a = new ArrayList();
    private PersonalProfileActivity b;
    private e c;
    private String d;
    private long e;
    private ExpertXListView f;
    private b g;
    private a<String> h = new h$1(this);
    private a<String> i = new h$2(this);

    public h(Activity activity) {
        if (activity instanceof PersonalProfileActivity) {
            this.b = (PersonalProfileActivity) activity;
            this.f = this.b.d();
            this.d = this.b.c();
            this.g = new b();
        }
    }

    public void b(String str) {
        this.d = str;
    }

    public void a(e eVar) {
        this.c = eVar;
    }

    public void a() {
        this.g.a(1);
        BudejieApplication.a.a(RequstMethod.GET, a(this.d, 0), new j(this.b), this.h);
    }

    public void a(String str) {
        a();
    }

    public void a(Object... objArr) {
        this.g.a(3);
        BudejieApplication.a.a(RequstMethod.GET, a(this.d, this.e), new j(this.b), this.i);
    }

    public b b() {
        return this.g;
    }

    private String a(String str, long j) {
        com.budejie.www.http.h hVar = new com.budejie.www.http.h();
        if (this.b.f() || this.c.a()) {
            hVar.a("http://d.api.budejie.com/topic/my-share-topic");
        } else {
            hVar.a("http://s.budejie.com/topic/share-topic").d(str);
        }
        hVar.a(j).a();
        return hVar.toString();
    }
}
