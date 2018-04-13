package com.budejie.www.activity.recommend;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.view.BadgeView;
import com.budejie.www.adapter.a.k;
import com.budejie.www.adapter.c.c$a;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.c.g;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.i;
import com.budejie.www.widget.XListView;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.b;

public class a extends Fragment {
    private net.tsz.afinal.a.a<String> A = new a$4(this);
    private net.tsz.afinal.a.a<String> B = new a$5(this);
    private c$a C = new a$6(this);
    private final int a = 1;
    private int b = ErrorCode.SERVER_SESSIONSTATUS;
    private XListView c;
    private k d;
    private Toast e;
    private List<SuggestedFollowsListItem> f;
    private List<SuggestedFollowsListItem> g;
    private List<SuggestedFollowsListItem> h;
    private List<SuggestedFollowsListItem> i;
    private LinearLayout j;
    private LinearLayout k;
    private ImageView l;
    private ImageView m;
    private String n = "0";
    private SuggestedFollowsActivity o;
    private n p;
    private com.budejie.www.util.as.a q;
    private Handler r;
    private boolean s = false;
    private BadgeView t;
    private BadgeView u;
    private LinearLayout v;
    private ImageView w;
    private g x;
    private OnClickListener y = new a$2(this);
    private com.budejie.www.widget.XListView.a z = new a$3(this);

    public List<SuggestedFollowsListItem> a() {
        return this.i;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.o = (SuggestedFollowsActivity) activity;
        this.d = new k(activity, this.C);
        this.p = new n(getActivity());
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.r = new Handler();
        this.x = new g(activity);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.suggested_follows_fragment_layout, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.c = (XListView) view.findViewById(R.id.listview);
        this.c.setPullLoadEnable(false);
        this.c.setPullRefreshEnable(false);
        this.c.setXListViewListener(this.z);
        d();
        this.c.setAdapter(this.d);
        if (this.d != null && this.d.getCount() > 0) {
            return;
        }
        if (an.a(this.o.a) && this.s) {
            this.r.postDelayed(new a$1(this), 200);
        } else {
            an.a(this.o, 1, "", "", 1);
        }
    }

    public void onStart() {
        super.onStart();
        e();
        b();
    }

    public void onResume() {
        super.onResume();
        if (this.d.getCount() > 0) {
            as.b().a(this.d);
            this.d.notifyDataSetChanged();
        }
    }

    public void b() {
        if (this.i.size() > 0) {
            this.o.a("邀请(" + this.i.size() + ")");
        } else {
            this.o.a("no_invite_data");
        }
    }

    public void c() {
        this.i.clear();
        this.c.d();
    }

    private void d() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.sf_friends_top_shareitem_layout, null);
        this.j = (LinearLayout) inflate.findViewById(R.id.sfFriend_sina);
        this.k = (LinearLayout) inflate.findViewById(R.id.sfFriend_tencent);
        this.l = (ImageView) inflate.findViewById(R.id.sfFriend_sinaicon);
        this.m = (ImageView) inflate.findViewById(R.id.sfFriend_tencenticon);
        this.v = (LinearLayout) inflate.findViewById(R.id.sfFriend_contacts);
        this.w = (ImageView) inflate.findViewById(R.id.sfFriend_phone);
        this.j.setOnClickListener(this.y);
        this.k.setOnClickListener(this.y);
        this.v.setOnClickListener(this.y);
        e();
        this.c.addHeaderView(inflate);
        int b = (int) (10.0f * i.a().b(getActivity()));
        this.t = new BadgeView(this.o, this.l);
        this.t.setBadgePosition(2);
        this.t.setBackgroundResource(R.drawable.notice_bg);
        this.t.b(30, 20);
        if (HomeGroup.m > 0) {
            this.t.a(b, b);
        } else {
            this.t.b();
        }
        this.u = new BadgeView(this.o, this.m);
        this.u.setBadgePosition(2);
        this.u.setBackgroundResource(R.drawable.notice_bg);
        this.u.b(30, 20);
        if (HomeGroup.n > 0) {
            this.u.a(b, b);
        } else {
            this.u.b();
        }
    }

    private void e() {
        if (this.p.a(this.o)) {
            this.j.setTag(Boolean.valueOf(true));
            this.n = "0";
            this.l.setSelected(true);
            this.s = true;
        } else {
            this.j.setTag(Boolean.valueOf(false));
            this.l.setSelected(false);
        }
        if (this.p.b(this.o)) {
            this.k.setTag(Boolean.valueOf(true));
            this.n = "1";
            this.m.setSelected(true);
            this.s = true;
        } else {
            this.k.setTag(Boolean.valueOf(false));
            this.m.setSelected(false);
        }
        if (this.p.d(this.o)) {
            this.v.setTag(Boolean.valueOf(true));
            this.n = "3";
            this.w.setSelected(true);
            this.s = true;
            return;
        }
        this.v.setTag(Boolean.valueOf(false));
        this.w.setSelected(false);
    }

    private void a(String str) {
        this.q = as.b().c();
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", c(str), this.A);
    }

    private void b(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", c(str), this.B);
    }

    private b c(String str) {
        return new j().a(this.o, str, this.b + "", this.q);
    }
}
