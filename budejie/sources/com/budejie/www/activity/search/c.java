package com.budejie.www.activity.search;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.adapter.a.l;
import com.budejie.www.adapter.a.n;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.SearchHotItem;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.widget.XListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.tsz.afinal.a.a;

public class c extends a {
    private List<String> h;
    private SharedPreferences i;
    private Editor j;
    private View k;
    private RelativeLayout l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private List<ListItemObject> q;
    private n r;
    private j s;
    private List<SearchHotItem> t;
    private l u;
    private a<String> v = new c$3(this);
    private OnClickListener w = new c$4(this);
    private XListView.a x = new c$5(this);
    private int y;
    private long z;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        f();
        g();
    }

    public void d() {
        if (this.e != null) {
            this.e.setSelection(0);
            this.r.notifyDataSetChanged();
        }
    }

    private void f() {
        this.k = LayoutInflater.from(getActivity()).inflate(R.layout.search_histroy_top, null);
        this.m = (TextView) getView().findViewById(R.id.HotTipTextView);
        this.n = (TextView) this.k.findViewById(R.id.HisTextView1);
        this.o = (TextView) this.k.findViewById(R.id.HisTextView2);
        this.p = (TextView) this.k.findViewById(R.id.HisTextView3);
        this.l = (RelativeLayout) this.k.findViewById(R.id.SearchDelHisLayout);
    }

    private void g() {
        this.a = 0;
        this.q = new ArrayList();
        this.r = new n(getActivity());
        this.c.setAdapter(this.r);
        this.s = new j();
        this.c.setPullRefreshEnable(false);
        this.c.setPullLoadEnable(false);
        this.c.setXListViewListener(this.x);
        j();
        this.m.setVisibility(8);
        this.e.setOnItemClickListener(new c$1(this));
        this.n.setOnClickListener(this.w);
        this.o.setOnClickListener(this.w);
        this.p.setOnClickListener(this.w);
        this.l.setOnClickListener(this.w);
        i();
        this.e.setOnTouchListener(new c$2(this));
        h();
    }

    private void h() {
        this.t = new ArrayList();
        this.u = new l(getActivity(), this.t);
        this.e.setAdapter(this.u);
        BudejieApplication.a.a(RequstMethod.GET, j.g("0"), null, this.v);
    }

    private void i() {
        this.i = getActivity().getSharedPreferences("budejie", 0);
        this.h = new ArrayList();
        String string = this.i.getString("searchHisText", "");
        if (string.equals("")) {
            this.k.setVisibility(8);
            this.e.removeHeaderView(this.k);
            this.m.setVisibility(0);
            return;
        }
        Collections.addAll(this.h, string.split(" "));
        a(false, "");
    }

    public void a(boolean z, String str) {
        if (z) {
            Object obj;
            if (str.equals("")) {
                this.h.clear();
            } else if (this.h != null) {
                if (this.h.contains(str)) {
                    this.h.remove(str);
                    this.h.add(0, str);
                } else {
                    if (this.h.size() >= 3) {
                        this.h.remove(2);
                    }
                    this.h.add(0, str);
                }
            }
            String str2 = "";
            if (this.h != null) {
                obj = str2;
                for (String str22 : this.h) {
                    String str3 = obj + str22 + " ";
                }
            } else {
                obj = str22;
            }
            if (this.j == null && isAdded()) {
                if (this.i == null) {
                    this.i = getContext().getSharedPreferences("budejie", 0);
                }
                this.j = this.i.edit();
            }
            if (this.j != null) {
                if (!TextUtils.isEmpty(obj)) {
                    this.j.putString("searchHisText", obj);
                    this.j.apply();
                }
            } else {
                return;
            }
        }
        e();
    }

    public void e() {
        if (this.h != null) {
            int size = this.h.size();
            this.k.setVisibility(0);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.p.setVisibility(8);
            switch (size) {
                case 0:
                    this.k.setVisibility(8);
                    this.m.setVisibility(0);
                    this.e.removeHeaderView(this.k);
                    return;
                case 1:
                    j();
                    this.m.setVisibility(8);
                    this.n.setVisibility(0);
                    this.n.setText((CharSequence) this.h.get(0));
                    return;
                case 2:
                    this.n.setVisibility(0);
                    this.n.setText((CharSequence) this.h.get(0));
                    this.o.setVisibility(0);
                    this.o.setText((CharSequence) this.h.get(1));
                    return;
                default:
                    this.n.setVisibility(0);
                    this.n.setText((CharSequence) this.h.get(0));
                    this.o.setVisibility(0);
                    this.o.setText((CharSequence) this.h.get(1));
                    this.p.setVisibility(0);
                    this.p.setText((CharSequence) this.h.get(2));
                    return;
            }
        }
    }

    private void j() {
        try {
            if (this.e.getHeaderViewsCount() == 0) {
                this.e.addHeaderView(this.k);
            }
        } catch (Exception e) {
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.f)) {
            this.f = str;
            c(str);
            if (this.g != null) {
                this.g.a(1, str, false);
            }
        }
    }

    private synchronized void c(String str) {
        b();
        if (this.r != null) {
            this.r.a();
        }
        NetWorkUtil netWorkUtil = BudejieApplication.a;
        RequstMethod requstMethod = RequstMethod.GET;
        String f = j.f("0");
        j jVar = this.s;
        netWorkUtil.a(requstMethod, f, j.r(getActivity(), str), new c$a(this, 1, null));
    }
}
