package cn.xiaochuankeji.tieba.ui.my.assessor;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.assessor.ReportAssessResultJson;
import cn.xiaochuankeji.tieba.background.assessor.c;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.post.postitem.e;
import cn.xiaochuankeji.tieba.ui.post.postitem.f;
import cn.xiaochuankeji.tieba.ui.post.postitem.h;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog;
import cn.xiaochuankeji.tieba.ui.widget.SDPopupMenu;
import cn.xiaochuankeji.tieba.ui.widget.g;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.aspectj.a.b.b;
import rx.j;

public class UserAssessActivity extends a implements OnClickListener, c.a, MonitorLeftFlingFrameLayout.a, SDCheckSheet.a {
    private static final org.aspectj.lang.a.a t = null;
    private c a;
    private HashMap<Long, Boolean> b;
    private int c = 0;
    private MonitorLeftFlingFrameLayout d;
    private LinearLayout e;
    private ScrollPostAreaLinearLayout f;
    private ImageView g;
    private RejectPostButton h;
    private PassPostButton i;
    private Button j;
    private cn.xiaochuankeji.tieba.ui.widget.c k;
    private FrameLayout l;
    private PostPercentBar m;
    private TextView n;
    private TextView o;
    private View p;
    private ProgressBar q;
    private cn.xiaochuankeji.tieba.api.post.c r;
    private Runnable s = new Runnable(this) {
        final /* synthetic */ UserAssessActivity a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.a.b()) {
                this.a.r();
            } else {
                this.a.f.setVisibility(8);
                this.a.e.setVisibility(0);
            }
            this.a.c = this.a.c + 1;
            this.a.p();
        }
    };

    static {
        v();
    }

    private static void v() {
        b bVar = new b("UserAssessActivity.java", UserAssessActivity.class);
        t = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.my.assessor.UserAssessActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 86);
    }

    public static void a(Context context) {
        Intent intent = new Intent(context, UserAssessActivity.class);
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    static final void a(UserAssessActivity userAssessActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        userAssessActivity.j();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(t, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void j() {
        if (!cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_show_assess_guide", false) && !isFinishing()) {
            cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("key_show_assess_guide", true).apply();
            SDGuideDialog sDGuideDialog = new SDGuideDialog(this);
            sDGuideDialog.a(R.drawable.img_assess_guide, 53);
            sDGuideDialog.b();
        }
    }

    protected boolean a(Bundle bundle) {
        this.b = new HashMap();
        this.a = c.a();
        this.r = new cn.xiaochuankeji.tieba.api.post.c();
        return true;
    }

    protected int a() {
        return R.layout.activity_user_assess;
    }

    protected void c() {
        this.f = (ScrollPostAreaLinearLayout) findViewById(R.id.llPostArea);
        this.e = (LinearLayout) findViewById(R.id.llAssessOver);
        this.h = (RejectPostButton) findViewById(R.id.tvReject);
        this.i = (PassPostButton) findViewById(R.id.tvPass);
        this.g = (ImageView) findViewById(R.id.ivReport);
        this.j = (Button) findViewById(R.id.bnRequestMore);
        this.d = (MonitorLeftFlingFrameLayout) findViewById(R.id.rootView);
        this.l = (FrameLayout) findViewById(R.id.post_item_container);
        this.k = new cn.xiaochuankeji.tieba.ui.widget.c(this);
        this.k.a(R.layout.view_assess_goal_completed, R.id.ivBalloon, R.id.ivCancel);
        this.m = (PostPercentBar) findViewById(R.id.post_percent_bar);
        this.n = (TextView) findViewById(R.id.label_reject_percent);
        this.o = (TextView) findViewById(R.id.label_pass_percent);
        this.q = (ProgressBar) findViewById(R.id.loading_progress);
        this.p = findViewById(R.id.exchange_tips);
        this.p.setVisibility(8);
        findViewById(R.id.exchange).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserAssessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                WebActivity.a(this.a, cn.xiaochuan.jsbridge.b.a("审帖员交流区", cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/bbs/reviewer")));
            }
        });
        findViewById(R.id.back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserAssessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        findViewById(R.id.more).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserAssessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                SDPopupMenu sDPopupMenu = new SDPopupMenu(this.a, view, SDPopupMenu.a(view), new SDPopupMenu.b(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void b(int i) {
                        if (i == 0) {
                            WebActivity.a(this.a.a, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/assessor/know2")));
                        } else if (i == 1) {
                            WebActivity.a(this.a.a, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/assessor/selecttags2?viewAssess=1")));
                        }
                    }
                });
                sDPopupMenu.a("查看审帖须知", 0);
                sDPopupMenu.a("更改类别", 1);
                sDPopupMenu.b();
            }
        });
    }

    protected void i_() {
        if (this.a.b()) {
            s();
            this.f.setVisibility(0);
            return;
        }
        g.a((Activity) this);
        this.a.a((c.a) this);
    }

    protected void j_() {
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.d.a(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivReport:
                k();
                return;
            case R.id.tvReject:
                if (this.f.b() && !this.h.isSelected() && !this.i.isSelected()) {
                    this.h.a(true, true);
                    a("fail", -1, false);
                    return;
                }
                return;
            case R.id.tvPass:
                if (this.f.b() && !this.i.isSelected() && !this.h.isSelected()) {
                    this.i.a(true, true);
                    a("pass", -1, true);
                    return;
                }
                return;
            case R.id.bnRequestMore:
                this.a.a((c.a) this);
                return;
            default:
                return;
        }
    }

    private void k() {
        LinkedHashMap m = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
        if (m.size() == 0) {
            a("report", 0, true);
            return;
        }
        SDCheckSheet sDCheckSheet = new SDCheckSheet(this, this);
        int i = 0;
        for (Entry entry : m.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            int parseInt = Integer.parseInt(str);
            int i2 = i + 1;
            if (i2 == m.size()) {
                sDCheckSheet.a(str2, parseInt, true);
            } else {
                sDCheckSheet.a(str2, parseInt, false);
            }
            i = i2;
        }
        sDCheckSheet.b();
    }

    public void a(int i) {
        a("report", i, true);
    }

    public void e() {
        if (!this.i.isSelected() && !this.h.isSelected()) {
            a("unknown", -1, true);
        }
    }

    public void a(boolean z, String str) {
        g.c(this);
        if (z) {
            this.f.setVisibility(0);
            this.e.setVisibility(8);
            s();
            return;
        }
        cn.xiaochuankeji.tieba.background.utils.g.a(str);
    }

    private void a(final String str, int i, boolean z) {
        if (this.f.b() && this.r != null) {
            final Post c = this.a.c();
            if (c != null) {
                this.q.setVisibility(0);
                this.r.a(c._ID, str, i).a(rx.a.b.a.a()).b(new j<ReportAssessResultJson>(this) {
                    final /* synthetic */ UserAssessActivity c;

                    public /* synthetic */ void onNext(Object obj) {
                        a((ReportAssessResultJson) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        cn.xiaochuankeji.tieba.background.utils.g.a("网络不好，请重试");
                        if (this.c.i != null) {
                            this.c.i.a(false, false);
                        }
                        this.c.q.setVisibility(8);
                    }

                    public void a(ReportAssessResultJson reportAssessResultJson) {
                        this.c.a.a(c);
                        this.c.a(str, reportAssessResultJson.recper, reportAssessResultJson.correct, reportAssessResultJson.reason);
                        this.c.q.setVisibility(8);
                    }
                });
            }
        }
    }

    private void p() {
        int x = cn.xiaochuankeji.tieba.background.utils.c.a.c().x();
        int h = cn.xiaochuankeji.tieba.background.a.g().h() + this.c;
        com.izuiyou.a.a.b.e("goalCount:" + x + "   todayCount:" + h);
        if (x != 0 && h % x == 0) {
            this.k.a();
        }
    }

    private void a(String str, int i, int i2, String str2) {
        if (i2 == 1) {
            if (str.equals("report")) {
                cn.xiaochuankeji.tieba.background.utils.g.a("举报成功");
            }
            u();
            return;
        }
        a(str2);
    }

    private void a(String str) {
        if (this != null && !isFinishing()) {
            final com.flyco.dialog.c.a aVar = new com.flyco.dialog.c.a(this);
            ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) aVar.b(str)).c(1)).b(false)).b(1)).a(new String[]{"朕知道了，退下吧~"})).show();
            aVar.setCanceledOnTouchOutside(false);
            aVar.setOnKeyListener(new OnKeyListener(this) {
                final /* synthetic */ UserAssessActivity a;

                {
                    this.a = r1;
                }

                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4) {
                        this.a.u();
                    }
                    return false;
                }
            });
            aVar.a(new com.flyco.dialog.a.a[]{new com.flyco.dialog.a.a(this) {
                final /* synthetic */ UserAssessActivity b;

                public void a() {
                    aVar.dismiss();
                    this.b.u();
                }
            }});
        }
    }

    private void q() {
        this.m.setVisibility(4);
        this.n.setVisibility(4);
        this.o.setVisibility(4);
    }

    private void r() {
        s();
        this.f.a();
    }

    private void s() {
        e gVar;
        this.h.setSelected(false);
        this.i.setSelected(false);
        q();
        Post c = this.a.c();
        int size = c._imgList.size();
        if (size == 0) {
            gVar = new cn.xiaochuankeji.tieba.ui.post.postitem.g(this);
        } else if (1 == size) {
            gVar = new h(this);
        } else {
            gVar = new f(this, size);
        }
        gVar.a(c, this.b);
        if (this.l.getChildCount() == 2) {
            this.l.removeViewAt(0);
        }
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = cn.xiaochuankeji.tieba.ui.utils.e.a(20.0f);
        layoutParams.topMargin = cn.xiaochuankeji.tieba.ui.utils.e.a(20.0f);
        layoutParams.leftMargin = cn.xiaochuankeji.tieba.ui.utils.e.a(11.0f);
        layoutParams.rightMargin = cn.xiaochuankeji.tieba.ui.utils.e.a(11.0f);
        this.l.addView(gVar.i(), 0, layoutParams);
    }

    private void t() {
        this.f.removeCallbacks(this.s);
    }

    private void u() {
        t();
        this.f.postDelayed(this.s, 500);
    }

    public void onBackPressed() {
        if (!this.k.b()) {
            super.onBackPressed();
        }
    }
}
