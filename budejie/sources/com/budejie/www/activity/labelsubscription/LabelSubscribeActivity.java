package com.budejie.www.activity.labelsubscription;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.BudejieApplication.b;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView.a;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LabelSubscribeActivity extends BaseTitleActivity implements OnClickListener, b, a {
    private String A;
    private List<RecommendSubscription> B;
    private List<RecommendSubscription> C;
    private List<RecommendSubscription> D;
    private int E;
    private int F;
    private int G;
    private int H;
    private ImageView I;
    private AnimationDrawable J = null;
    private BudejieApplication K;
    private OnScrollListener L = new OnScrollListener(this) {
        final /* synthetic */ LabelSubscribeActivity a;

        {
            this.a = r1;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    };
    private OnScrollListener M = new OnScrollListener(this) {
        final /* synthetic */ LabelSubscribeActivity a;

        {
            this.a = r1;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            int i2 = 0;
            if (i == 0) {
                this.a.G = absListView.getFirstVisiblePosition();
            }
            if (this.a.j != null) {
                View childAt = absListView.getChildAt(0);
                LabelSubscribeActivity labelSubscribeActivity = this.a;
                if (childAt != null) {
                    i2 = childAt.getTop();
                }
                labelSubscribeActivity.H = i2;
            }
        }
    };
    private net.tsz.afinal.a.a N = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ LabelSubscribeActivity a;

        {
            this.a = r1;
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.f();
        }

        public void onSuccess(Object obj) {
            this.a.f();
            if (obj == null) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            String obj2 = obj.toString();
            this.a.j.clear();
            this.a.j = z.h(obj2);
            if (this.a.j == null || this.a.j.size() == 0) {
                Toast.makeText(this.a.d, "抱歉，没有该标签！", 0).show();
            } else if (this.a.j != null && this.a.j.size() > 0 && this.a.y != null) {
                this.a.s.setAdapter(this.a.y);
                this.a.A = "";
                this.a.y.a(this.a.j, this.a.A);
            }
        }
    };
    private OnItemClickListener O = new OnItemClickListener(this) {
        final /* synthetic */ LabelSubscribeActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                if (!(this.a.s.getItemAtPosition(i) instanceof Integer)) {
                    RecommendSubscription recommendSubscription = (RecommendSubscription) this.a.s.getItemAtPosition(i);
                    Intent intent = new Intent(this.a.d, CommonLabelActivity.class);
                    intent.putExtra("theme_name", recommendSubscription.getTheme_name());
                    String theme_id = recommendSubscription.getTheme_id();
                    if (theme_id.matches("[0-9]+")) {
                        intent.putExtra("theme_id", Integer.parseInt(theme_id));
                        this.a.d.startActivityForResult(intent, 0);
                    }
                } else if (TextUtils.isEmpty(this.a.p.getText().toString())) {
                    Toast.makeText(this.a.d, "请输入要搜索的标签", 0).show();
                } else {
                    this.a.e();
                    this.a.d(this.a.p.getText().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private OnItemClickListener P = new OnItemClickListener(this) {
        final /* synthetic */ LabelSubscribeActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                RecommendSubscription recommendSubscription = (RecommendSubscription) this.a.i.getItemAtPosition(i);
                Intent intent = new Intent(this.a.d, CommonLabelActivity.class);
                intent.putExtra("theme_name", recommendSubscription.getTheme_name());
                String theme_id = recommendSubscription.getTheme_id();
                if (theme_id.matches("[0-9]+")) {
                    intent.putExtra("theme_id", Integer.parseInt(theme_id));
                    this.a.d.startActivityForResult(intent, 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private net.tsz.afinal.a.a Q = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ LabelSubscribeActivity a;

        {
            this.a = r1;
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.f();
            this.a.c = true;
            this.a.g();
        }

        public void onSuccess(Object obj) {
            this.a.f();
            this.a.c = false;
            if (obj == null) {
                Toast.makeText(this.a, "请求数据为空", 0).show();
                return;
            }
            String obj2 = obj.toString();
            this.a.B.clear();
            this.a.B = z.g(obj2);
            if (!this.a.c) {
                if (this.a.B == null || this.a.B.size() <= 0) {
                    Toast.makeText(this.a.d, "无数据", 0).show();
                    return;
                }
                this.a.w.clear();
                this.a.v.clear();
                for (int i = 0; i < this.a.B.size(); i++) {
                    if ("r".equals(((RecommendSubscription) this.a.B.get(i)).getType())) {
                        this.a.w.add(this.a.B.get(i));
                    } else {
                        this.a.v.add(this.a.B.get(i));
                    }
                }
                this.a.C.clear();
                this.a.C = this.a.a(this.a.v);
                this.a.B.clear();
                if (this.a.w != null && this.a.w.size() > 0) {
                    int size = this.a.w.size();
                    Collection arrayList = new ArrayList();
                    arrayList.addAll(this.a.w);
                    if (size > 5) {
                        arrayList.clear();
                        arrayList.addAll(this.a.w.subList(0, 5));
                    }
                    this.a.B.addAll(arrayList);
                }
                if (this.a.C != null && this.a.C.size() > 0) {
                    this.a.B.addAll(this.a.C);
                }
                this.a.i.setAdapter(this.a.e);
                Collection a = this.a.e.a(this.a.B);
                this.a.B.clear();
                this.a.B.addAll(a);
                new Thread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.m();
                    }
                }).start();
            }
        }
    };
    private TextWatcher R = new TextWatcher(this) {
        final /* synthetic */ LabelSubscribeActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(this.a.p.getText().toString())) {
                this.a.r.setVisibility(8);
                return;
            }
            this.a.r.setVisibility(0);
            this.a.A = this.a.p.getText().toString();
            this.a.j = this.a.x.e(this.a.A);
            if (this.a.y != null) {
                this.a.s.setAdapter(this.a.y);
                this.a.y.a(this.a.j, this.a.A);
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(this.a.p.getText().toString())) {
                this.a.s.setVisibility(8);
                this.a.q.setVisibility(0);
                return;
            }
            this.a.s.setVisibility(0);
            this.a.q.setVisibility(8);
        }
    };
    private net.tsz.afinal.a.a S = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ LabelSubscribeActivity a;

        {
            this.a = r1;
        }

        public void onFailure(Throwable th, int i, String str) {
        }

        public void onSuccess(final Object obj) {
            new Thread(new Runnable(this) {
                final /* synthetic */ AnonymousClass2 b;

                public void run() {
                    List h = z.h(obj.toString());
                    if (h != null && h.size() > 0) {
                        this.b.a.x.b("recommend_Label");
                        this.b.a.x.b(h);
                    }
                }
            }).start();
        }
    };
    public com.budejie.www.g.b a;
    Handler b = new Handler(this) {
        final /* synthetic */ LabelSubscribeActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 434534545 || i == 9976575) {
                ArrayList arrayList = (ArrayList) message.obj;
                this.a.B.clear();
                this.a.B.addAll(arrayList);
            }
        }
    };
    private boolean c;
    private Activity d;
    private f e;
    private ListView i;
    private List<RecommendSubscription> j;
    private LinearLayout k;
    private float l;
    private int m;
    private LinearLayout n;
    private LinearLayout o;
    private EditText p;
    private FrameLayout q;
    private ImageView r;
    private ListView s;
    private InputMethodManager t;
    private TextView u;
    private List<RecommendSubscription> v;
    private List<RecommendSubscription> w;
    private com.budejie.www.c.b x;
    private e y;
    private LayoutInflater z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.label_subscribe);
        this.d = this;
        this.K = BudejieApplication.b();
        this.a = new com.budejie.www.g.b(this.d);
        a("标签订阅");
        d(j.bf);
        this.z = (LayoutInflater) getSystemService("layout_inflater");
        this.x = new com.budejie.www.c.b(this);
        this.B = new ArrayList();
        this.j = new ArrayList();
        this.y = new e(this.d);
        this.v = new ArrayList();
        this.w = new ArrayList();
        this.C = new ArrayList();
        this.D = new ArrayList();
        k();
        ((BudejieApplication) getApplication()).g().ai.a(ai.b(this.d));
        if (an.a((Context) this)) {
            e();
            l();
        } else {
            g();
        }
        j();
    }

    private void g() {
        if (i()) {
            h();
        } else if (an.a((Context) this)) {
            l();
        } else {
            f();
            Toast.makeText(this, getString(R.string.nonet), 0).show();
        }
    }

    private void h() {
        this.i.setAdapter(this.e);
        this.B.clear();
        Collection arrayList = new ArrayList();
        if (this.w != null && this.w.size() > 5) {
            arrayList.clear();
            arrayList.addAll(this.w.subList(0, 5));
        }
        this.B.addAll(arrayList);
        this.B.addAll(this.v);
        arrayList = this.e.a(this.B);
        this.B.clear();
        this.B.addAll(arrayList);
        f();
    }

    private boolean i() {
        this.v.clear();
        this.v = this.x.a();
        this.w.clear();
        this.w = this.x.c();
        if ((this.v == null || this.v.size() <= 0) && (this.w == null || this.w.size() <= 0)) {
            return false;
        }
        return true;
    }

    private void j() {
        this.I = (ImageView) findViewById(R.id.melodyview);
        this.J = (AnimationDrawable) this.I.getBackground();
        this.I.setOnClickListener(this);
    }

    private void k() {
        this.u = (TextView) findViewById(R.id.cancel_btn);
        this.u.setOnClickListener(this);
        this.n = (LinearLayout) findViewById(R.id.search_label);
        this.o = (LinearLayout) findViewById(R.id.label_mengban);
        this.k = (LinearLayout) findViewById(R.id.label_search);
        this.q = (FrameLayout) findViewById(R.id.fl_bg);
        this.s = (ListView) findViewById(R.id.listview);
        this.q.setOnClickListener(this);
        this.p = (EditText) findViewById(R.id.search_keywords_text);
        this.p.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ LabelSubscribeActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 66 && keyEvent.getAction() == 0) {
                    ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.d.getCurrentFocus().getWindowToken(), 2);
                    if (TextUtils.isEmpty(this.a.p.getText().toString())) {
                        Toast.makeText(this.a.d, "请输入要搜索的标签", 0).show();
                    } else {
                        this.a.e();
                        this.a.d(this.a.p.getText().toString());
                    }
                }
                return false;
            }
        });
        this.p.addTextChangedListener(this.R);
        this.r = (ImageView) findViewById(R.id.del_keywords_btn);
        this.r.setOnClickListener(this);
        this.e = new f(this, this.b);
        this.i = (ListView) findViewById(R.id.label_listview);
        this.i.setOnItemClickListener(this.P);
        this.i.setOnScrollListener(this.L);
        this.s.setOnItemClickListener(this.O);
        this.s.setOnScrollListener(this.M);
    }

    private synchronized void d(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", e(str), this.N);
    }

    private net.tsz.afinal.a.b e(String str) {
        return new com.budejie.www.http.j().m(this, str);
    }

    private void l() {
        BudejieApplication.a.a(RequstMethod.GET, new h("http://d.api.budejie.com/tag/subscribe").b().a().toString(), new com.budejie.www.http.j(), this.Q);
    }

    private void m() {
        this.x.a("subscribe_Label");
        this.D.clear();
        this.D.addAll(this.C);
        Collections.reverse(this.D);
        this.x.a(this.D);
        this.x.b("recommend_Label");
        this.x.b(this.w);
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
            } else if ("0".equals(((RecommendSubscription) list.get(i)).getIs_default())) {
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

    public void recommendLabel$click(View view) {
        MobclickAgent.onEvent(this.d, "标签订阅", "推荐标签标题行点击次数");
        MobclickAgent.onEvent(this.d, "E02-A02", "跳到推荐标签");
        startActivity(new Intent(this, RecommendLabelActivity.class));
    }

    public void labelSearch$click(View view) {
        MobclickAgent.onEvent(this.d, "标签订阅", "订阅页面点击搜索框次数");
        MobclickAgent.onEvent(this.d, "E02-A02", "点击搜索框");
        this.l = (float) this.k.getTop();
        this.m = this.k.getHeight();
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (-this.l) + ((float) an.a(getResources(), 5)));
        translateAnimation.setDuration(100);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ LabelSubscribeActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.c(true);
                this.a.a(true);
                this.a.b(true);
                this.a.o.setVisibility(0);
                this.a.p.setFocusable(true);
                this.a.p.setFocusableInTouchMode(true);
                this.a.p.requestFocus();
                this.a.t = (InputMethodManager) this.a.p.getContext().getSystemService("input_method");
                this.a.t.showSoftInput(this.a.p, 0);
                this.a.overridePendingTransition(R.anim.animation_2, R.anim.animation_1);
            }
        });
        this.n.startAnimation(translateAnimation);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.melodyview:
                view.setTag(this.K.d());
                this.a.a(3, null).onClick(view);
                return;
            case R.id.cancel_btn:
                c();
                return;
            case R.id.del_keywords_btn:
                this.p.requestFocus();
                this.p.getText().clear();
                return;
            case R.id.fl_bg:
                c();
                return;
            default:
                return;
        }
    }

    public void c() {
        this.o.setVisibility(8);
        this.t.hideSoftInputFromWindow(this.p.getWindowToken(), 0);
        c(false);
        a(false);
        b(false);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (-this.l) + ((float) an.a(getResources(), 5)), 0.0f);
        translateAnimation.setDuration(100);
        translateAnimation.setFillAfter(true);
        this.n.startAnimation(translateAnimation);
        this.p.setText("");
        if (!TextUtils.isEmpty(ai.b(this.d))) {
            e();
            g();
        }
    }

    public void a() {
    }

    protected void onResume() {
        super.onResume();
        String b = ai.b(this.d);
        if (!b.equals(((BudejieApplication) getApplication()).g().ai.a())) {
            e();
            l();
            ((BudejieApplication) getApplication()).g().ai.a(b);
        } else if (this.c) {
            e();
            g();
        }
        this.K.a((b) this);
        Status a = this.K.a();
        if (a != null) {
            a(a);
        }
        this.a = new com.budejie.www.g.b(this.d);
    }

    protected void onStop() {
        super.onStop();
        this.c = true;
    }

    public void b() {
    }

    public void onLeftClick(View view) {
        super.onLeftClick(view);
    }

    public void onRightClick(View view) {
        super.onRightClick(view);
        MobclickAgent.onEvent(this.d, "标签订阅", "右上角加号点击次数");
        MobclickAgent.onEvent(this.d, "E02-A02", "右上角加号点击次数");
        recommendLabel$click(null);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != 22) {
            return;
        }
        String b;
        LabelBean labelBean;
        int i3;
        if (i == 0) {
            b = ai.b(this.d);
            if (b.equals(((BudejieApplication) getApplication()).g().ai.a())) {
                labelBean = (LabelBean) intent.getSerializableExtra("labelObj");
                if (labelBean != null) {
                    for (i3 = 0; i3 < this.j.size(); i3++) {
                        if ((labelBean.getTheme_id() + "").equals(((RecommendSubscription) this.j.get(i3)).getTheme_id())) {
                            ((RecommendSubscription) this.j.get(i3)).setIs_sub(labelBean.getIs_sub());
                            ((RecommendSubscription) this.j.get(i3)).setSub_number(labelBean.getSub_number());
                        }
                    }
                }
                this.y.a(this.j, this.A);
                this.s.setSelectionFromTop(this.G, this.H);
            } else {
                this.p.setText("");
                l();
                n();
                ((BudejieApplication) getApplication()).g().ai.a(b);
            }
            this.c = false;
        } else if (i == 1) {
            b = ai.b(this.d);
            if (b.equals(((BudejieApplication) getApplication()).g().ai.a())) {
                labelBean = (LabelBean) intent.getSerializableExtra("labelObj");
                if (labelBean != null) {
                    i3 = 0;
                    while (i3 < this.B.size()) {
                        if ((labelBean.getTheme_id() + "").equals(((RecommendSubscription) this.B.get(i3)).getTheme_id()) && !labelBean.getIs_sub().equals(((RecommendSubscription) this.B.get(i3)).getIs_sub())) {
                            if (!"0".equals(labelBean.getIs_sub())) {
                                if ("1".equals(labelBean.getIs_sub()) && "r".equals(((RecommendSubscription) this.B.get(i3)).getType())) {
                                    RecommendSubscription recommendSubscription;
                                    ((RecommendSubscription) this.B.get(i3)).setIs_sub("1");
                                    try {
                                        recommendSubscription = (RecommendSubscription) ((RecommendSubscription) this.B.get(i3)).clone();
                                    } catch (CloneNotSupportedException e) {
                                        e.printStackTrace();
                                        recommendSubscription = null;
                                    }
                                    for (i3 = 0; i3 < this.B.size(); i3++) {
                                        if ("sub_tv".equals(((RecommendSubscription) this.B.get(i3)).getType()) && recommendSubscription != null) {
                                            recommendSubscription.setType("s");
                                            this.B.add(i3 + 1, recommendSubscription);
                                            break;
                                        }
                                    }
                                }
                            } else if ("r".equals(((RecommendSubscription) this.B.get(i3)).getType())) {
                                ((RecommendSubscription) this.B.get(i3)).setIs_sub("0");
                            } else if ("s".equals(((RecommendSubscription) this.B.get(i3)).getType())) {
                                this.B.remove(i3);
                            }
                        }
                        i3++;
                    }
                }
                Collection a = this.e.a(this.B);
                this.B.clear();
                this.B.addAll(a);
                this.i.setSelectionFromTop(this.E, this.F);
            } else {
                e();
                l();
                ((BudejieApplication) getApplication()).g().ai.a(b);
            }
            this.c = false;
        }
    }

    private synchronized void n() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", o(), this.S);
    }

    private net.tsz.afinal.a.b o() {
        return new com.budejie.www.http.j().b(this.d);
    }

    public void a(Status status) {
        int i = 0;
        switch (status) {
            case start:
                this.J.stop();
                this.J.start();
                this.I.setVisibility(0);
                return;
            case stop:
                this.J.stop();
                ImageView imageView = this.I;
                if (!an.b) {
                    i = 8;
                }
                imageView.setVisibility(i);
                return;
            case end:
                this.J.stop();
                this.I.setVisibility(8);
                return;
            default:
                return;
        }
    }
}
