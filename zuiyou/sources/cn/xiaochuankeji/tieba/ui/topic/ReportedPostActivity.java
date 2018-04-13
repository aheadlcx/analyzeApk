package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.topic.ReportedPostList;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import org.aspectj.a.b.b;
import org.json.JSONObject;

public class ReportedPostActivity extends h {
    private static final org.aspectj.lang.a.a i = null;
    private ReportedPostQueryListView d;
    private ReportedPostList e;
    private TextView f;
    private TextView g;
    private View h;

    public interface a {
        void a();
    }

    static {
        v();
    }

    private static void v() {
        b bVar = new b("ReportedPostActivity.java", ReportedPostActivity.class);
        i = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.topic.ReportedPostActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 41);
    }

    public static void a(Context context, long j) {
        Intent intent = new Intent(context, ReportedPostActivity.class);
        intent.putExtra("topic_id", j);
        context.startActivity(intent);
    }

    static final void a(ReportedPostActivity reportedPostActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(i, this, this, bundle);
        c.c().a(new c(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected int a() {
        return R.layout.activity_reported_post;
    }

    protected void i_() {
        this.h = findViewById(R.id.empty_tip);
        this.d = (ReportedPostQueryListView) findViewById(R.id.list);
        this.d.f();
        this.d.g();
        this.d.setUpdateListener(new a(this) {
            final /* synthetic */ ReportedPostActivity a;

            {
                this.a = r1;
            }

            public void a() {
                ReportedPostList a = this.a.e;
                a.post_report_count--;
                a = this.a.e;
                a.proc_count++;
                this.a.j();
                this.a.k();
            }
        });
        View inflate = LayoutInflater.from(this).inflate(R.layout.header_reported_post, null);
        this.f = (TextView) inflate.findViewById(R.id.post_report_count);
        this.g = (TextView) inflate.findViewById(R.id.proc_count);
        this.d.m().addHeaderView(inflate);
        this.e = new ReportedPostList(this, getIntent().getLongExtra("topic_id", 0)) {
            final /* synthetic */ ReportedPostActivity a;

            protected void handleQuerySuccResult(JSONObject jSONObject) {
                super.handleQuerySuccResult(jSONObject);
                this.a.j();
            }
        };
        this.e.setRequestType(0);
        this.e.registerOnQueryFinishListener(new cn.htjyb.b.a.b.b(this) {
            final /* synthetic */ ReportedPostActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, boolean z2, String str) {
                if (!z) {
                    g.a(str);
                    this.a.h.setVisibility(8);
                } else if (this.a.e.itemCount() <= 0) {
                    this.a.h.setVisibility(0);
                } else {
                    this.a.h.setVisibility(8);
                }
            }
        });
        this.d.a(this.e);
        k();
    }

    public void j() {
        int i = 9999;
        TopicDetailActivity.d = this.e.post_report_count;
        TextView textView = this.f;
        StringBuilder append = new StringBuilder().append("");
        if (this.e.post_report_count <= 9999) {
            i = this.e.post_report_count;
        }
        textView.setText(append.append(i).toString());
        this.g.setText("" + this.e.proc_count);
    }

    public void k() {
        if (this.e.itemCount() <= 0) {
            this.e.refresh();
        }
    }
}
