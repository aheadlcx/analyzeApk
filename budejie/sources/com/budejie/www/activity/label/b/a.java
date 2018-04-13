package com.budejie.www.activity.label.b;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.activity.label.enumeration.PlatePostEnum;
import com.budejie.www.activity.label.k;
import com.budejie.www.activity.label.l;
import com.budejie.www.activity.view.StaggeredRecyclerView;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.d;
import com.budejie.www.c.g;
import com.budejie.www.c.h;
import com.budejie.www.i.b.c;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.xrecyclerview.XRecyclerView$c;
import java.util.ArrayList;
import java.util.List;

public class a extends com.budejie.www.b.a implements c {
    private View a;
    private XListView b;
    private StaggeredRecyclerView c;
    private String d;
    private String e;
    private boolean f;
    private l g;
    private d h;
    private h i;
    private CommonLabelActivity j;
    private com.budejie.www.i.a.c k;
    private String l = "0";
    private String m;
    private List<ListItemObject> n;
    private g o;
    private Context p;
    private boolean q;
    private TextView r;
    private com.budejie.www.widget.XListView.a s = new a$1(this);
    private XRecyclerView$c t = new a$2(this);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a != null) {
            if (this.a.getParent() != null) {
                ((ViewGroup) this.a.getParent()).removeView(this.a);
            }
            return this.a;
        }
        this.a = layoutInflater.inflate(R.layout.fragment_label_list, viewGroup, false);
        c();
        return this.a;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.p = context;
        this.j = (CommonLabelActivity) context;
        if (this.k == null) {
            this.k = new com.budejie.www.i.a.c();
        }
        if (this.n == null) {
            this.n = new ArrayList();
        }
        this.k.a(this);
    }

    private void b() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.d = arguments.getString("theme_id");
            this.f = arguments.getInt("colum_set") == 2;
            this.e = arguments.getString("post_type_tag");
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.k != null) {
            this.k.a();
            this.k = null;
        }
    }

    private void c() {
        b();
        d();
        this.m = "20";
        this.l = "0";
        f();
        i();
        a();
    }

    public void a() {
        if (this.k != null) {
            this.l = "0";
            if (this.f) {
                if (this.c != null) {
                    this.c.c();
                }
            } else if (this.b != null) {
                this.b.d();
            }
        }
    }

    private void d() {
        if (this.p != null && isAdded()) {
            this.o = new g(this.p);
            this.h = new d(this.p);
            this.i = new h(this.p);
        }
    }

    private void f() {
        int i = 8;
        this.b = (XListView) this.a.findViewById(R.id.list_view);
        ViewCompat.setNestedScrollingEnabled(this.b, true);
        this.c = (StaggeredRecyclerView) this.a.findViewById(R.id.recycler_view);
        ViewCompat.setNestedScrollingEnabled(this.c, true);
        this.b.setVisibility(this.f ? 8 : 0);
        StaggeredRecyclerView staggeredRecyclerView = this.c;
        if (this.f) {
            i = 0;
        }
        staggeredRecyclerView.setVisibility(i);
        this.b.setXListViewListener(this.s);
        this.c.setLoadingListener(this.t);
        this.b.setPullRefreshEnable(true);
        this.b.setPullLoadEnable(false);
        this.c.setLoadingMoreEnabled(false);
        this.r = (TextView) this.a.findViewById(R.id.empty_text_view);
    }

    private void i() {
        this.g = new l(getActivity(), ((CommonLabelActivity) getActivity()).k);
        this.b.setAdapter(this.g);
        if (this.n == null) {
            this.n = new ArrayList();
        }
        this.g.a(this.n);
        this.c.setData(this.n);
        this.c.a();
    }

    private void j() {
        a(false);
    }

    private void a(boolean z) {
        if (z) {
            this.l = "0";
        }
        this.k.a(this.d, this.e, this.l, this.m);
    }

    private void b(boolean z) {
        this.b.a(z);
        this.c.a(z);
    }

    private void k() {
        this.b.c();
        this.c.b();
    }

    public void a(Throwable th) {
        l();
        au.a((int) R.string.load_failed);
    }

    private void l() {
        if (this.q) {
            b(true);
        } else {
            k();
        }
    }

    public void a(k kVar) {
        int i = 1;
        int i2 = 0;
        if (isAdded()) {
            this.q = "0".equals(this.l);
            l();
            if (kVar != null) {
                boolean z;
                if (PlatePostEnum.NEW_TYPE.getValue().equals(this.e) && this.q && kVar.a != null && this.p != null && (this.p instanceof CommonLabelActivity)) {
                    ((CommonLabelActivity) this.p).a(kVar.a);
                }
                List<ListItemObject> list = kVar.b;
                if (list == null) {
                    au.a((int) R.string.labeldetails_no_listdata);
                }
                if (this.q) {
                    this.n.clear();
                }
                if (list != null) {
                    for (ListItemObject listItemObject : list) {
                        listItemObject.setAddtime(listItemObject.getPasstime());
                        listItemObject.setState(2);
                        if (this.j != null) {
                            LabelBean c = this.j.c();
                            if (c != null && "2".equals(c.getTheme_type()) && c.status == 0) {
                                listItemObject.setState(1);
                            }
                        }
                    }
                    if (!com.budejie.www.goddubbing.c.a.a(list)) {
                        an.a((List) list, this.i, this.h, this.o);
                    }
                    this.n.addAll(list);
                    if (this.f) {
                        if (this.q) {
                            this.c.setData(this.n);
                        } else {
                            this.c.a(list);
                        }
                    } else if (this.q) {
                        this.g.b();
                        this.g.a(list);
                    } else {
                        this.g.b(list);
                    }
                }
                long j = kVar.d;
                this.l = kVar.c;
                XListView xListView = this.b;
                if (j != 0) {
                    z = true;
                } else {
                    z = false;
                }
                xListView.setPullLoadEnable(z);
                StaggeredRecyclerView staggeredRecyclerView = this.c;
                if (j != 0) {
                    z = true;
                } else {
                    z = false;
                }
                staggeredRecyclerView.setLoadingMoreEnabled(z);
                if (!(j == 0 && com.budejie.www.goddubbing.c.a.a(this.n))) {
                    i = 0;
                }
                TextView textView = this.r;
                if (i == 0) {
                    i2 = 8;
                }
                textView.setVisibility(i2);
            }
        }
    }

    public void a_() {
    }

    public void g() {
        if (m()) {
            this.j.g();
        }
    }

    private boolean m() {
        if (this.j == null) {
            return false;
        }
        boolean z;
        int f = this.j.f();
        if (f == PlatePostEnum.NEW_TAB_POSITION.getCode() && PlatePostEnum.NEW_TYPE.getValue().equals(this.e)) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if (f == PlatePostEnum.ESSENCE_TAB_POSITION.getCode() && PlatePostEnum.ESSENCE_TYPE.getValue().equals(this.e)) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || r3) {
            return true;
        }
        return false;
    }

    public Context h() {
        return getContext();
    }
}
