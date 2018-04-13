package com.budejie.www.activity.label;

import android.app.Activity;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.g;
import com.budejie.www.c.h;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.a;

public class d {
    private String a;
    private Activity b;
    private c c;
    private XListView d;
    private l e;
    private List<ListItemObject> f;
    private String g;
    private h h;
    private com.budejie.www.c.d i;
    private g j;
    private Toast k;
    private String l = "0";
    private String m = "20";
    private long n;
    private e o;
    private a<String> p = new a<String>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            this.a.o.a(1);
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.a(false);
            this.a.o.a(2);
            this.a.k = an.a(this.a.b, this.a.b.getString(R.string.labeldetails_no_listdata), -1);
        }

        public void a(String str) {
            super.onSuccess(str);
            this.a.a(true);
            k e = com.budejie.www.j.a.e(this.a.b, str);
            if (e == null) {
                this.a.k = an.a(this.a.b, this.a.b.getString(R.string.labeldetails_no_listdata), -1);
                this.a.k.show();
                if ("new".equals(this.a.a)) {
                    this.a.c.a(true);
                    return;
                }
                return;
            }
            this.a.l = e.c;
            this.a.n = e.d;
            List<ListItemObject> list = e.b;
            if (list == null) {
                this.a.k = an.a(this.a.b, this.a.b.getString(R.string.labeldetails_no_listdata), -1);
                this.a.c.a(true);
                this.a.e.b();
            }
            if (list != null) {
                for (ListItemObject listItemObject : list) {
                    listItemObject.setAddtime(listItemObject.getPasstime());
                    listItemObject.setState(2);
                    if (this.a.c.a() != null && "2".equals(this.a.c.a().getTheme_type()) && this.a.c.a().status == 0) {
                        listItemObject.setState(1);
                    }
                }
                an.a(list, this.a.h, this.a.i, this.a.j);
                this.a.f.clear();
                this.a.f.addAll(list);
                this.a.a((List) list);
                this.a.c.a(false);
            }
            if (this.a.n == 0) {
                this.a.o.a(false);
                if (this.a.o.a() != 3) {
                    this.a.d.setPullLoadEnable(false);
                }
            } else {
                this.a.o.a(true);
                if (this.a.o.a() != 3) {
                    this.a.d.setPullLoadEnable(true);
                }
            }
            this.a.o.a(2);
        }
    };
    private a<String> q = new a<String>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            this.a.o.a(4);
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.a(false);
            this.a.o.a(5);
            this.a.k = an.a(this.a.b, this.a.b.getString(R.string.labeldetails_no_listdata), -1);
        }

        public void a(String str) {
            super.onSuccess(str);
            this.a.a(false);
            k e = com.budejie.www.j.a.e(this.a.b, str);
            if (e == null) {
                this.a.k = an.a(this.a.b, this.a.b.getString(R.string.labeldetails_no_listdata), -1);
                return;
            }
            this.a.l = e.c;
            this.a.n = e.d;
            Object<ListItemObject> obj = e.b;
            if (obj == null) {
                this.a.k = an.a(this.a.b, this.a.b.getString(R.string.labeldetails_no_listdata), -1);
                this.a.k.show();
            }
            if (obj != null) {
                for (ListItemObject listItemObject : obj) {
                    listItemObject.setAddtime(listItemObject.getPasstime());
                    listItemObject.setState(2);
                    if (this.a.c.a() != null && "2".equals(this.a.c.a().getTheme_type()) && this.a.c.a().status == 0) {
                        listItemObject.setState(1);
                    }
                }
                an.a(obj, this.a.h, this.a.i, this.a.j);
                this.a.f.addAll(obj);
                this.a.b((List) obj);
            }
            if (this.a.n == 0) {
                this.a.o.a(false);
                if (this.a.o.a() != 3) {
                    this.a.d.setPullLoadEnable(false);
                }
            } else {
                this.a.o.a(true);
                if (this.a.o.a() != 3) {
                    this.a.d.setPullLoadEnable(true);
                }
            }
            this.a.o.a(5);
        }
    };

    public d(Activity activity, c cVar, String str) {
        this.b = activity;
        this.c = cVar;
        this.a = str;
        a();
    }

    public void a() {
        this.f = new ArrayList();
        this.o = new e();
        this.g = this.c.b();
        this.d = this.c.c();
        this.e = this.c.d();
        this.h = new h(this.b);
        this.i = new com.budejie.www.c.d(this.b);
        this.j = new g(this.b);
    }

    public List<ListItemObject> b() {
        return this.f;
    }

    public e c() {
        return this.o;
    }

    private void a(boolean z) {
        if (this.o.a() != 3) {
            this.d.a(z);
        }
    }

    private void a(List<ListItemObject> list) {
        if (this.o.a() != 3) {
            this.e.b();
            this.e.a(list);
        }
    }

    private void b(List<ListItemObject> list) {
        if (this.o.a() != 3) {
            this.e.b(list);
        }
    }

    public void d() {
        this.l = "0";
        BudejieApplication.a.a(RequstMethod.GET, j.a(this.g, "1", "new", this.l, this.m), new j(this.b), this.p);
    }

    public void e() {
        BudejieApplication.a.a(RequstMethod.GET, j.a(this.g, "1", "new", this.l, this.m), new j(this.b), this.q);
    }
}
