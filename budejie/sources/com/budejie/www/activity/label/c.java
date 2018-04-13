package com.budejie.www.activity.label;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.activity.video.k;
import com.budejie.www.c.b;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.util.p;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.XListView.a;
import com.nostra13.universalimageloader.core.d;
import com.nostra13.universalimageloader.core.d.e;
import com.umeng.analytics.MobclickAgent;
import java.util.HashMap;
import java.util.Map;

public class c extends Fragment {
    private TextView A;
    private ImageButton B;
    private boolean C = false;
    private PostsActivity D;
    private Dialog E;
    private b F;
    private String G;
    private j H;
    private boolean I = true;
    private String J;
    private OnClickListener K = new c$2(this);
    private OnClickListener L = new c$4(this);
    private OnClickListener M = new c$5(this);
    private a N = new c$6(this);
    private OnScrollListener O = new c$7(this);
    private net.tsz.afinal.a.a<String> P = new c$8(this);
    public g a;
    View b;
    boolean c = false;
    net.tsz.afinal.a.a<String> d = new c$3(this);
    private String e;
    private XListView f;
    private l g;
    private AsyncImageView h;
    private LabelBean i;
    private String j;
    private String k;
    private String l;
    private Toast m;
    private String n;
    private SharedPreferences o;
    private n p;
    private m q;
    private HashMap<String, String> r;
    private com.elves.update.a s;
    private d t;
    private d u;
    private LinearLayout v;
    private ImageView w;
    private View x;
    private RelativeLayout y;
    private TextView z;

    public static c a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            throw new RuntimeException("label id or tag is empty");
        }
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putString("themeId", str);
        bundle.putString("key", str2);
        bundle.putString(AppLinkConstants.TAG, str3);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.D = (PostsActivity) activity;
        this.j = getArguments().getString("themeId");
        this.l = getArguments().getString("key");
        this.J = getArguments().getString(AppLinkConstants.TAG);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.b != null) {
            if (this.b.getParent() != null) {
                ((ViewGroup) this.b.getParent()).removeView(this.b);
            }
            this.c = true;
            return this.b;
        }
        this.b = layoutInflater.inflate(R.layout.fragment_label_details, viewGroup, false);
        this.c = false;
        return this.b;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (!this.c) {
            f();
            e();
            m();
        }
    }

    public LabelBean a() {
        return this.i;
    }

    public String b() {
        return this.j;
    }

    public XListView c() {
        return this.f;
    }

    public l d() {
        return this.g;
    }

    public d a(String str) {
        if ("new".equals(str)) {
            return this.t;
        }
        if (StatisticCodeTable.HOT.equals(str)) {
            return this.u;
        }
        return this.t;
    }

    private void e() {
        this.o = this.D.getSharedPreferences("weiboprefer", 0);
        this.H = new j();
        this.n = this.o.getString("id", "");
        this.F = new b(this.D);
        this.p = new n(this.D);
        this.q = new m(this.D);
        this.r = this.p.a(this.n);
        this.s = new com.elves.update.a(this.D);
    }

    private void f() {
        this.f = (XListView) getView().findViewById(R.id.labelDetailsListview);
        this.f.setPullRefreshEnable(false);
        this.f.setPullLoadEnable(false);
        this.f.setXListViewListener(this.N);
        this.f.setOnScrollListener(new e(d.a(), false, true, this.O));
        this.f.a();
        this.g = new l(this.D, this.D);
        this.v = (LinearLayout) getView().findViewById(R.id.labelDetailsEmptyHint);
        this.w = (ImageView) getView().findViewById(R.id.labelDetailsEmptyButton);
        this.w.setOnClickListener(this.M);
        g();
        this.E = new Dialog(this.D, R.style.dialogTheme);
        this.E.setCanceledOnTouchOutside(true);
        this.E.setContentView(R.layout.loaddialog);
    }

    private void g() {
        this.x = View.inflate(this.D, R.layout.label_details_head, null);
        this.h = (AsyncImageView) this.x.findViewById(R.id.labelDetailsImg);
        this.h.setLayoutParams(i.a().c(this.D));
        this.f.addHeaderView(this.x);
        this.y = (RelativeLayout) this.x.findViewById(R.id.normalLable_bottom_ll);
        this.A = (TextView) this.x.findViewById(R.id.tvPostNumber);
        this.z = (TextView) this.x.findViewById(R.id.tvSubNumber);
        this.B = (ImageButton) this.x.findViewById(R.id.lebel_add_btn);
    }

    private void h() {
        if (this.C) {
            this.B.setVisibility(8);
            return;
        }
        this.B.setVisibility(0);
        this.B.setOnClickListener(this.K);
    }

    private void i() {
        if ("1".equals(this.i.is_sub)) {
            this.C = true;
        }
        if (this.i.getTheme_name() != null) {
            String theme_name = this.i.getTheme_name();
            if (theme_name.length() > 5) {
                theme_name.substring(0, 5) + "...";
            }
        }
        this.h.setAsyncCacheImage(this.i.getImage_detail(), R.drawable.lable_default_bg);
        if ("1".equals(this.i.getTheme_type())) {
            aa.a("CommonLabelActivity", "普通标签");
            this.y.setVisibility(0);
            if ("0".equals(this.i.getSub_number())) {
                this.G = "喜欢“" + this.i.getTheme_name() + "”的小伙伴都在玩百思不得姐";
            } else {
                this.G = this.i.getSub_number() + "个喜欢“" + this.i.getTheme_name() + "”的小伙伴在玩百思不得姐";
            }
            this.A.setText(this.i.getPost_number());
            this.z.setText(this.i.getSub_number());
        }
        h();
        j();
    }

    private void j() {
        this.t = new d(this.D, this, "new");
        this.u = new d(this.D, this, StatisticCodeTable.HOT);
        this.f.setPullRefreshEnable(true);
        if (this.I) {
            b(this.J);
        } else {
            View textView = new TextView(this.D);
            textView.setLayoutParams(new LayoutParams(-1, com.budejie.www.adapter.b.a.g));
            textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.personal_not_have_user_text_prompt));
            textView.setTextSize(18.0f);
            textView.setGravity(1);
            textView.setPadding(0, an.a(this.D, 100), 0, 0);
            textView.setText(R.string.label_not_have_label_text_prompt);
            try {
                this.f.addHeaderView(textView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.f.setAdapter(this.g);
    }

    private void k() {
        if (!an.e(this.D)) {
            MobclickAgent.onEvent(this.D, "标签详情", "底部发帖按钮点击次数");
            Object content_type = this.i.getContent_type();
            if (TextUtils.isEmpty(content_type) || ",".equals(content_type)) {
                an.a(this.D, "暂时没有发帖的类型!", -1).show();
                return;
            }
            String[] split = content_type.split(",");
            Map hashMap = new HashMap();
            hashMap.put("theme_id", Integer.valueOf(this.i.getTheme_id()));
            hashMap.put("theme_name", this.i.getTheme_name());
            p.a(this.D, split, hashMap);
        }
    }

    private void b(String str) {
        if (!(str == null || str.equals(this.e))) {
            k.a(this.D).p();
        }
        try {
            this.e = str;
            if ("new".equals(str)) {
                a(this.u.c());
                if (this.t.b().size() <= 0) {
                    l();
                    return;
                }
                this.g.b();
                this.g.a(this.t.b());
                this.f.setPullLoadEnable(this.t.c().c());
                a(false);
            } else if (StatisticCodeTable.HOT.equals(str)) {
                a(this.t.c());
                if (this.u.b().size() <= 0) {
                    l();
                    return;
                }
                this.g.b();
                this.g.a(this.u.b());
                this.f.setPullLoadEnable(this.u.c().c());
                a(false);
            }
        } catch (Exception e) {
        }
    }

    private void l() {
        this.f.postDelayed(new c$1(this), 100);
    }

    private void a(e eVar) {
        if (eVar.a() == 1) {
            eVar.a(3);
        }
        if (eVar.b() == 4) {
            eVar.b(6);
        }
    }

    public void a(boolean z) {
        if ("2".equals(this.i.getTheme_type()) && this.i.getStatus() != 1) {
            z = false;
        }
        if (z) {
            this.v.setVisibility(0);
            this.w.setVisibility(0);
            return;
        }
        this.v.setVisibility(8);
        this.w.setVisibility(8);
    }

    private void m() {
        BudejieApplication.a.a(RequstMethod.POST, "http://api.budejie.com/api/api_open.php", this.H.g(this.D, this.j, this.k), this.P);
    }
}
