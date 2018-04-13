package cn.xiaochuankeji.tieba.ui.discovery.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.h.a;
import org.aspectj.a.b.b;

public class SearchAllActivity extends h implements OnClickListener, a {
    private static final org.aspectj.lang.a.a q = null;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private View g;
    private View h;
    private View i;
    private cn.xiaochuankeji.tieba.ui.widget.h j;
    private TextView k;
    private TextView l;
    private TextView m;
    private b n;
    private LinearLayout o;
    private int p = 0;

    static {
        v();
    }

    private static void v() {
        b bVar = new b("SearchAllActivity.java", SearchAllActivity.class);
        q = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.discovery.search.SearchAllActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 47);
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, SearchAllActivity.class);
        intent.putExtra("enter_type", str);
        context.startActivity(intent);
    }

    static final void a(SearchAllActivity searchAllActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        Object string = searchAllActivity.getIntent().getExtras().getString("enter_type");
        if (!TextUtils.isEmpty(string)) {
            searchAllActivity.b(string);
        }
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(q, this, this, bundle);
        c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void b(String str) {
        cn.xiaochuankeji.tieba.background.utils.h.a(this, "zy_event_search_page", "页面进入");
        if (str.equals("homepage_act")) {
            cn.xiaochuankeji.tieba.background.utils.h.a(this, "zy_event_search_page", "页面进入_最右Tab");
        } else if (str.equals("follow_act")) {
            cn.xiaochuankeji.tieba.background.utils.h.a(this, "zy_event_search_page", "页面进入_关注Tab");
        } else if (str.equals("discovery_act")) {
            cn.xiaochuankeji.tieba.background.utils.h.a(this, "zy_event_search_page", "页面进入_发现Tab");
        }
    }

    protected int a() {
        return R.layout.activity_search_all;
    }

    protected void c() {
        super.c();
        this.d = (LinearLayout) findViewById(R.id.llTapTopic);
        this.e = (LinearLayout) findViewById(R.id.llTapPost);
        this.f = (LinearLayout) findViewById(R.id.llTapMember);
        this.g = findViewById(R.id.vTopicBottomLine);
        this.h = findViewById(R.id.vPostBottomLine);
        this.i = findViewById(R.id.vMemberBottomLine);
        this.j = new cn.xiaochuankeji.tieba.ui.widget.h(this);
        this.o = (LinearLayout) findViewById(R.id.rootView);
        this.o.addView(this.j.f_(), 0);
        this.k = (TextView) findViewById(R.id.tv_topic);
        this.l = (TextView) findViewById(R.id.tv_post);
        this.m = (TextView) findViewById(R.id.tv_user);
    }

    protected void i_() {
        this.n = b.b();
        a(this.n);
        this.j.a("搜索话题 / 帖子 / 用户", this);
    }

    protected void j_() {
        super.j_();
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llTapTopic:
                if (this.p != 0) {
                    this.p = 0;
                    k();
                    this.n.a(b.a);
                    return;
                }
                return;
            case R.id.llTapPost:
                if (this.p != 1) {
                    this.p = 1;
                    k();
                    this.n.a(b.b);
                    return;
                }
                return;
            case R.id.llTapMember:
                if (this.p != 2) {
                    this.p = 2;
                    k();
                    this.n.a(b.c);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void k() {
        if (this.p == 0) {
            this.g.setVisibility(0);
            this.h.setVisibility(4);
            this.i.setVisibility(4);
            this.k.setTextColor(c.a.d.a.a.a().a((int) R.color.CM));
            this.l.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
            this.m.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
        } else if (1 == this.p) {
            this.g.setVisibility(4);
            this.h.setVisibility(0);
            this.i.setVisibility(4);
            this.k.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
            this.l.setTextColor(c.a.d.a.a.a().a((int) R.color.CM));
            this.m.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
        } else if (2 == this.p) {
            this.g.setVisibility(4);
            this.h.setVisibility(4);
            this.i.setVisibility(0);
            this.k.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
            this.l.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_2));
            this.m.setTextColor(c.a.d.a.a.a().a((int) R.color.CM));
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.n.c();
        } else {
            this.n.a(str);
        }
    }

    public void j() {
        finish();
    }
}
