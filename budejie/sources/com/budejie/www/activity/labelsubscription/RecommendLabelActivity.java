package com.budejie.www.activity.labelsubscription;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.c.b;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView.a;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecommendLabelActivity extends BaseTitleActivity implements OnClickListener, a {
    private int A;
    private int B;
    private int C;
    private String D;
    private boolean E;
    private OnScrollListener F = new OnScrollListener(this) {
        final /* synthetic */ RecommendLabelActivity a;

        {
            this.a = r1;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            int i2 = 0;
            if (i == 0) {
                this.a.z = absListView.getFirstVisiblePosition();
            }
            if (this.a.y != null) {
                View childAt = absListView.getChildAt(0);
                RecommendLabelActivity recommendLabelActivity = this.a;
                if (childAt != null) {
                    i2 = childAt.getTop();
                }
                recommendLabelActivity.A = i2;
            }
        }
    };
    private OnScrollListener G = new OnScrollListener(this) {
        final /* synthetic */ RecommendLabelActivity a;

        {
            this.a = r1;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            int i2 = 0;
            if (i == 0) {
                this.a.B = absListView.getFirstVisiblePosition();
            }
            if (this.a.v != null) {
                View childAt = absListView.getChildAt(0);
                RecommendLabelActivity recommendLabelActivity = this.a;
                if (childAt != null) {
                    i2 = childAt.getTop();
                }
                recommendLabelActivity.C = i2;
            }
        }
    };
    private net.tsz.afinal.a.a H = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ RecommendLabelActivity a;

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
            this.a.v = z.h(obj.toString());
            if (this.a.v == null || this.a.v.size() == 0) {
                Toast.makeText(this.a.b, "抱歉，没有该标签！", 0).show();
            } else if (this.a.u != null) {
                this.a.r.setAdapter(this.a.u);
                this.a.x = "";
                this.a.u.a(this.a.v, this.a.x);
            }
        }
    };
    private TextWatcher I = new TextWatcher(this) {
        final /* synthetic */ RecommendLabelActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(this.a.o.getText().toString())) {
                this.a.q.setVisibility(8);
                return;
            }
            this.a.q.setVisibility(0);
            this.a.x = this.a.o.getText().toString();
            this.a.v = this.a.t.e(this.a.x);
            if (this.a.u != null) {
                this.a.r.setAdapter(this.a.u);
                this.a.u.a(this.a.v, this.a.x);
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(this.a.o.getText().toString())) {
                this.a.r.setVisibility(8);
                if (!this.a.E) {
                    this.a.p.setVisibility(0);
                    return;
                }
                return;
            }
            this.a.r.setVisibility(0);
            if (!this.a.E) {
                this.a.p.setVisibility(8);
            }
        }
    };
    private OnItemClickListener J = new OnItemClickListener(this) {
        final /* synthetic */ RecommendLabelActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                RecommendSubscription recommendSubscription = (RecommendSubscription) this.a.d.getItemAtPosition(i);
                if ("#".equals(this.a.D)) {
                    this.a.f(recommendSubscription.getTheme_name());
                    return;
                }
                Intent intent = new Intent(this.a.b, CommonLabelActivity.class);
                intent.putExtra("theme_name", recommendSubscription.getTheme_name());
                intent.putExtra("theme_id", recommendSubscription.getTheme_id());
                this.a.b.startActivityForResult(intent, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private OnItemClickListener K = new OnItemClickListener(this) {
        final /* synthetic */ RecommendLabelActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                if (!(this.a.r.getItemAtPosition(i) instanceof Integer)) {
                    RecommendSubscription recommendSubscription = (RecommendSubscription) this.a.r.getItemAtPosition(i);
                    if ("#".equals(this.a.D)) {
                        this.a.f(recommendSubscription.getTheme_name());
                        return;
                    }
                    Intent intent = new Intent(this.a.b, CommonLabelActivity.class);
                    intent.putExtra("theme_name", recommendSubscription.getTheme_name());
                    intent.putExtra("theme_id", Integer.parseInt(recommendSubscription.getTheme_id()));
                    this.a.b.startActivityForResult(intent, 0);
                } else if (TextUtils.isEmpty(this.a.o.getText().toString())) {
                    Toast.makeText(this.a.b, "请输入要搜索的标签", 0).show();
                } else {
                    this.a.e();
                    this.a.d(this.a.o.getText().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private net.tsz.afinal.a.a L = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ RecommendLabelActivity a;

        {
            this.a = r1;
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.f();
            this.a.a = true;
            Toast.makeText(this.a.b, "请求数据失败，请重试", 0).show();
        }

        public void onSuccess(Object obj) {
            this.a.f();
            this.a.a = false;
            if (obj == null) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            this.a.e = z.h(obj.toString());
            if (!this.a.a) {
                if (this.a.e == null || this.a.e.size() <= 0) {
                    Toast.makeText(this.a.b, "无数据", 0).show();
                    return;
                }
                this.a.d.setAdapter(this.a.c);
                this.a.y = this.a.e;
                this.a.c.a(this.a.e);
            }
        }
    };
    private boolean a;
    private Activity b;
    private d c;
    private ListView d;
    private ArrayList<RecommendSubscription> e;
    private LinearLayout i;
    private float j;
    private int k;
    private LinearLayout l;
    private List<RecommendSubscription> m;
    private LinearLayout n;
    private EditText o;
    private FrameLayout p;
    private ImageView q;
    private ListView r;
    private InputMethodManager s;
    private b t;
    private e u;
    private List<RecommendSubscription> v;
    private TextView w;
    private String x;
    private List<RecommendSubscription> y;
    private int z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.recommend_label);
        this.b = this;
        this.t = new b(this);
        this.y = new ArrayList();
        this.e = new ArrayList();
        this.D = getIntent().getStringExtra("source");
        if ("#".equals(this.D)) {
            this.c = new d(this.b, this.D);
            a((int) R.string.add_topic);
            b((int) R.string.cancel);
            this.h.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            this.c = new d(this.b);
            a("推荐标签");
        }
        this.v = new ArrayList();
        this.u = new e(this.b);
        this.m = new ArrayList();
        i();
        Intent intent = getIntent();
        this.E = intent.getBooleanExtra("only_seach", false);
        intent.getStringExtra("only_seach");
        if (this.E) {
            c();
            return;
        }
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(this.b, "推荐订阅-刷新间隔");
        long longValue = ((BudejieApplication) getApplication()).g().p.a().longValue();
        long time = new Date().getTime();
        long j = (long) (((double) ((time - longValue) / com.umeng.analytics.a.i)) + 0.5d);
        longValue = (time - longValue) / com.umeng.analytics.a.j;
        e();
        if (!ai.b(this.b).equals(((BudejieApplication) getApplication()).g().aj.a())) {
            e();
            j();
        } else if (j > 1 || longValue > ((long) Integer.valueOf(configParams).intValue())) {
            e();
            j();
        } else {
            g();
        }
    }

    private void c() {
        c(true);
        a(true);
        this.n.setVisibility(0);
        this.o.setFocusable(true);
        this.o.setFocusableInTouchMode(true);
        this.o.requestFocus();
        this.s = (InputMethodManager) this.o.getContext().getSystemService("input_method");
        this.s.showSoftInput(this.o, 0);
    }

    private void g() {
        if (h()) {
            this.d.setAdapter(this.c);
            this.y = this.m;
            this.c.a(this.m);
            f();
        } else if (an.a((Context) this)) {
            j();
        } else {
            f();
            Toast.makeText(this, getString(R.string.nonet), 0).show();
        }
    }

    private boolean h() {
        this.m = this.t.c();
        if (this.m == null || this.m.size() <= 0) {
            return false;
        }
        return true;
    }

    public void labelSearch$click(View view) {
        if (!this.E) {
            this.j = (float) this.i.getTop();
            this.k = this.i.getHeight();
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (-this.j) + ((float) an.a(getResources(), 5)));
            translateAnimation.setDuration(100);
            translateAnimation.setFillAfter(true);
            translateAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ RecommendLabelActivity a;

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
                    this.a.n.setVisibility(0);
                    this.a.o.setFocusable(true);
                    this.a.o.setFocusableInTouchMode(true);
                    this.a.o.requestFocus();
                    this.a.s = (InputMethodManager) this.a.o.getContext().getSystemService("input_method");
                    this.a.s.showSoftInput(this.a.o, 0);
                    this.a.overridePendingTransition(R.anim.animation_2, R.anim.animation_1);
                }
            });
            this.l.startAnimation(translateAnimation);
        }
    }

    private void i() {
        this.i = (LinearLayout) findViewById(R.id.label_search);
        this.w = (TextView) findViewById(R.id.cancel_btn);
        this.w.setOnClickListener(this);
        this.l = (LinearLayout) findViewById(R.id.search_label);
        this.n = (LinearLayout) findViewById(R.id.label_mengban);
        this.p = (FrameLayout) findViewById(R.id.fl_bg);
        this.r = (ListView) findViewById(R.id.listview);
        this.p.setOnClickListener(this);
        this.o = (EditText) findViewById(R.id.search_keywords_text);
        this.o.addTextChangedListener(this.I);
        this.o.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ RecommendLabelActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 66 && keyEvent.getAction() == 0) {
                    ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.b.getCurrentFocus().getWindowToken(), 2);
                    if (TextUtils.isEmpty(this.a.o.getText().toString())) {
                        Toast.makeText(this.a.b, "请输入要搜索的标签", 0).show();
                    } else {
                        this.a.e();
                        this.a.d(this.a.o.getText().toString());
                    }
                }
                return false;
            }
        });
        this.q = (ImageView) findViewById(R.id.del_keywords_btn);
        this.q.setOnClickListener(this);
        this.d = (ListView) findViewById(R.id.label_listview);
        this.d.setOnItemClickListener(this.J);
        this.d.setOnScrollListener(this.F);
        this.r.setOnItemClickListener(this.K);
        this.r.setOnScrollListener(this.G);
    }

    private synchronized void d(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", e(str), this.H);
    }

    private net.tsz.afinal.a.b e(String str) {
        return new j().m(this.b, str);
    }

    private void f(String str) {
        setResult(-1, new Intent().putExtra(getString(R.string.RESPONE_RESULT_TOPIC_NAME), str));
        finish();
    }

    private synchronized void j() {
        if (!this.E) {
            if (an.a((Context) this)) {
                BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", k(), this.L);
            } else {
                f();
                Toast.makeText(this, getString(R.string.nonet), 0).show();
            }
        }
    }

    private net.tsz.afinal.a.b k() {
        return new j().b(this.b);
    }

    public void a() {
    }

    public void b() {
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.cancel_btn:
                l();
                return;
            case R.id.del_keywords_btn:
                this.o.requestFocus();
                this.o.getText().clear();
                return;
            case R.id.fl_bg:
                l();
                return;
            default:
                return;
        }
    }

    private void l() {
        if (this.E) {
            finish();
            return;
        }
        this.n.setVisibility(8);
        this.s.hideSoftInputFromWindow(this.o.getWindowToken(), 0);
        c(false);
        a(false);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (-this.j) + ((float) an.a(getResources(), 5)), 0.0f);
        translateAnimation.setDuration(100);
        translateAnimation.setFillAfter(true);
        this.l.startAnimation(translateAnimation);
        this.o.setText("");
        if (!TextUtils.isEmpty(ai.b(this.b))) {
            e();
            g();
        }
    }

    protected void onResume() {
        super.onResume();
        if (!this.E) {
            String b = ai.b(this.b);
            if (!b.equals(((BudejieApplication) getApplication()).g().aj.a())) {
                e();
                j();
                ((BudejieApplication) getApplication()).g().aj.a(b);
            } else if (this.a) {
                e();
                g();
            }
        }
    }

    protected void onStop() {
        super.onStop();
        this.a = true;
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
            b = ai.b(this.b);
            if (b.equals(((BudejieApplication) getApplication()).g().aj.a())) {
                labelBean = (LabelBean) intent.getSerializableExtra("labelObj");
                if (labelBean != null) {
                    for (i3 = 0; i3 < this.v.size(); i3++) {
                        if (((RecommendSubscription) this.v.get(i3)).getTheme_id().equals(labelBean.getTheme_id() + "")) {
                            ((RecommendSubscription) this.v.get(i3)).setIs_sub(labelBean.getIs_sub());
                            ((RecommendSubscription) this.v.get(i3)).setSub_number(labelBean.getSub_number());
                        }
                    }
                }
                this.u.a(this.v, this.x);
                this.r.setSelectionFromTop(this.B, this.C);
            } else if (this.E) {
                labelBean = (LabelBean) intent.getSerializableExtra("labelObj");
                if (labelBean != null) {
                    for (i3 = 0; i3 < this.v.size(); i3++) {
                        if (((RecommendSubscription) this.v.get(i3)).getTheme_id().equals(labelBean.getTheme_id() + "")) {
                            ((RecommendSubscription) this.v.get(i3)).setIs_sub(labelBean.getIs_sub());
                            ((RecommendSubscription) this.v.get(i3)).setSub_number(labelBean.getSub_number());
                        }
                    }
                }
                this.u.a(this.v, this.x);
                this.r.setSelectionFromTop(this.B, this.C);
            } else {
                this.o.setText("");
                j();
                ((BudejieApplication) getApplication()).g().aj.a(b);
            }
            this.a = false;
        } else if (i == 1) {
            b = ai.b(this.b);
            if (b.equals(((BudejieApplication) getApplication()).g().aj.a())) {
                labelBean = (LabelBean) intent.getSerializableExtra("labelObj");
                if (labelBean != null) {
                    for (i3 = 0; i3 < this.y.size(); i3++) {
                        if (((RecommendSubscription) this.y.get(i3)).getTheme_id().equals(labelBean.getTheme_id() + "")) {
                            ((RecommendSubscription) this.y.get(i3)).setIs_sub(labelBean.getIs_sub());
                            ((RecommendSubscription) this.y.get(i3)).setSub_number(labelBean.getSub_number());
                        }
                    }
                }
                this.c.a(this.y);
                this.d.setSelectionFromTop(this.z, this.A);
            } else {
                e();
                j();
                ((BudejieApplication) getApplication()).g().aj.a(b);
            }
            this.a = false;
        }
    }
}
