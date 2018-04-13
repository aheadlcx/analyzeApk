package com.budejie.www.activity.labelsubscription;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.c.b;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.ao;
import com.budejie.www.widget.KeyboardListenerRelativeLayout;
import com.budejie.www.widget.XListView.a;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class c extends Fragment implements OnClickListener, a {
    private OnItemClickListener A = new c$11(this);
    private net.tsz.afinal.a.a B = new c$12(this);
    private TextWatcher C = new c$2(this);
    private net.tsz.afinal.a.a D = new c$3(this);
    Handler a = new c$4(this);
    private boolean b;
    private Activity c;
    private f d;
    private ListView e;
    private List<RecommendSubscription> f;
    private LinearLayout g;
    private LinearLayout h;
    private EditText i;
    private FrameLayout j;
    private ImageView k;
    private ListView l;
    private List<RecommendSubscription> m;
    private List<RecommendSubscription> n;
    private b o;
    private e p;
    private String q;
    private List<RecommendSubscription> r;
    private List<RecommendSubscription> s;
    private List<RecommendSubscription> t;
    private int u;
    private int v;
    private ProgressBar w;
    private OnScrollListener x = new c$8(this);
    private net.tsz.afinal.a.a y = new c$9(this);
    private OnItemClickListener z = new c$10(this);

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.c = activity;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.label_subscribe_fragment, viewGroup, false);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.o = new b(this.c);
        this.r = new ArrayList();
        this.f = new ArrayList();
        this.p = new e(this.c);
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.s = new ArrayList();
        this.t = new ArrayList();
        h();
        ((BudejieApplication) this.c.getApplication()).g().ai.a(ai.b(this.c));
        if (an.a(this.c)) {
            c();
            j();
            return;
        }
        e();
    }

    public void onResume() {
        super.onResume();
        String b = ai.b(this.c);
        if (!b.equals(((BudejieApplication) this.c.getApplication()).g().ai.a())) {
            c();
            j();
            ((BudejieApplication) this.c.getApplication()).g().ai.a(b);
        } else if (this.b) {
            c();
            e();
            this.b = false;
        }
    }

    public void onPause() {
        super.onPause();
        d();
    }

    public void onStop() {
        super.onStop();
        this.b = true;
    }

    private void e() {
        new c$1(this).execute(new Void[0]);
    }

    private void f() {
        this.e.setAdapter(this.d);
        this.r.clear();
        this.r.addAll(this.n);
        this.r.addAll(this.m);
        Collection a = this.d.a(this.r);
        this.r.clear();
        this.r.addAll(a);
        an.a(this.e);
        i();
    }

    private boolean g() {
        this.m.clear();
        this.m = this.o.a();
        this.n.clear();
        this.n = this.o.c();
        if ((this.m == null || this.m.size() <= 0) && (this.n == null || this.n.size() <= 0)) {
            return false;
        }
        return true;
    }

    private void h() {
        getView().findViewById(R.id.cancel_btn).setOnClickListener(this);
        KeyboardListenerRelativeLayout keyboardListenerRelativeLayout = (KeyboardListenerRelativeLayout) getView().findViewById(R.id.lable_subscribe_root_layout);
        keyboardListenerRelativeLayout.setOnKeyboardChangeListener(new c$5(this));
        keyboardListenerRelativeLayout.setOnClickListener(this);
        this.w = (ProgressBar) getView().findViewById(R.id.progress);
        this.h = (LinearLayout) getView().findViewById(R.id.label_mengban);
        this.g = (LinearLayout) getView().findViewById(R.id.label_search);
        this.g.setOnClickListener(this);
        this.j = (FrameLayout) getView().findViewById(R.id.fl_bg);
        this.l = (ListView) getView().findViewById(R.id.listview);
        this.j.setOnClickListener(this);
        this.i = (EditText) getView().findViewById(R.id.search_keywords_text);
        this.i.setOnKeyListener(new c$6(this));
        this.i.addTextChangedListener(this.C);
        this.i.setOnFocusChangeListener(new c$7(this));
        this.k = (ImageView) getView().findViewById(R.id.del_keywords_btn);
        this.k.setOnClickListener(this);
        this.d = new f(this.c, this.a);
        this.e = (ListView) getView().findViewById(R.id.label_listview);
        this.e.setOnItemClickListener(this.A);
        this.l.setOnItemClickListener(this.z);
        this.l.setOnScrollListener(this.x);
    }

    protected void c() {
    }

    private void i() {
    }

    private synchronized void a(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", b(str), this.y);
    }

    private net.tsz.afinal.a.b b(String str) {
        return new j().m(this.c, str);
    }

    private void j() {
        BudejieApplication.a.a(RequstMethod.GET, new h("http://d.api.budejie.com/tag/subscribe").b().a().toString(), new j(), this.B);
    }

    private void k() {
        this.o.a("subscribe_Label");
        this.t.clear();
        this.t.addAll(this.s);
        Collections.reverse(this.t);
        this.o.a(this.t);
        this.o.b("recommend_Label");
        this.o.b(this.n);
    }

    protected List<RecommendSubscription> a(List<RecommendSubscription> list) {
        int i;
        int i2 = 0;
        List<RecommendSubscription> arrayList = new ArrayList();
        Collection arrayList2 = new ArrayList();
        Collection arrayList3 = new ArrayList();
        for (i = 0; i < list.size(); i++) {
            if ("1".equals(((RecommendSubscription) list.get(i)).getIs_default())) {
                arrayList2.add(list.get(i));
            } else {
                arrayList3.add(list.get(i));
            }
        }
        arrayList.addAll(arrayList3);
        arrayList.addAll(arrayList2);
        while (i2 < arrayList.size() - 1) {
            for (i = arrayList.size() - 1; i > i2; i--) {
                if (((RecommendSubscription) arrayList.get(i)).getTheme_id().equals(((RecommendSubscription) arrayList.get(i2)).getTheme_id())) {
                    arrayList.remove(i);
                }
            }
            i2++;
        }
        return arrayList;
    }

    public void labelSearch$click(View view) {
        MobclickAgent.onEvent(this.c, "标签订阅", "订阅页面点击搜索框次数");
        MobclickAgent.onEvent(this.c, "E02-A02", "点击搜索框");
        this.h.setVisibility(0);
        this.i.setFocusable(true);
        this.i.setFocusableInTouchMode(true);
        this.i.requestFocus();
        ao.a(this.c, this.i);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_btn:
                d();
                return;
            case R.id.label_search:
                labelSearch$click(view);
                return;
            case R.id.del_keywords_btn:
                this.i.requestFocus();
                this.i.getText().clear();
                return;
            case R.id.fl_bg:
                d();
                return;
            default:
                return;
        }
    }

    public void d() {
        if (this.h.isShown() || !isVisible()) {
            this.h.setVisibility(8);
            ao.a(this.c);
            this.i.setText("");
            if (!TextUtils.isEmpty(ai.b(this.c))) {
                c();
                e();
            }
        }
    }

    public void a() {
    }

    public void b() {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != 22) {
            return;
        }
        String b;
        LabelBean labelBean;
        if (i == 0) {
            b = ai.b(this.c);
            if (b.equals(((BudejieApplication) this.c.getApplication()).g().ai.a())) {
                labelBean = (LabelBean) intent.getSerializableExtra("labelObj");
                if (labelBean != null) {
                    for (int i3 = 0; i3 < this.f.size(); i3++) {
                        if ((labelBean.getTheme_id() + "").equals(((RecommendSubscription) this.f.get(i3)).getTheme_id())) {
                            ((RecommendSubscription) this.f.get(i3)).setIs_sub(labelBean.getIs_sub());
                            ((RecommendSubscription) this.f.get(i3)).setSub_number(labelBean.getSub_number());
                        }
                    }
                }
                this.p.a(this.f, this.q);
                this.l.setSelectionFromTop(this.u, this.v);
            } else {
                this.i.setText("");
                j();
                l();
                ((BudejieApplication) this.c.getApplication()).g().ai.a(b);
            }
            this.b = false;
        } else if (i == 1) {
            b = ai.b(this.c);
            if (b.equals(((BudejieApplication) this.c.getApplication()).g().ai.a())) {
                labelBean = (LabelBean) intent.getSerializableExtra("labelObj");
                Collection a = this.d.a(this.r);
                this.r.clear();
                this.r.addAll(a);
            } else {
                c();
                j();
                ((BudejieApplication) this.c.getApplication()).g().ai.a(b);
            }
            this.b = false;
        }
    }

    private synchronized void l() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", m(), this.D);
    }

    private net.tsz.afinal.a.b m() {
        return new j().b(this.c);
    }
}
