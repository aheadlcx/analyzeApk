package cn.xiaochuankeji.tieba.ui.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import c.a.a.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.b.f;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.g.e;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.GuideJson;
import cn.xiaochuankeji.tieba.push.c.g;
import cn.xiaochuankeji.tieba.ui.chat.MessageTabActivity;
import cn.xiaochuankeji.tieba.ui.discovery.TopicActivity;
import cn.xiaochuankeji.tieba.ui.homepage.AnimationTabImageView;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.homepage.ugc.UGCTabActivity;
import cn.xiaochuankeji.tieba.ui.homepage.ugc.UgcCrumbManger;
import cn.xiaochuankeji.tieba.ui.homepage.ugc.UgcCrumbManger.CrumbType;
import cn.xiaochuankeji.tieba.ui.my.MeActivity;
import cn.xiaochuankeji.tieba.ui.utils.OpenActivityUtils;
import cn.xiaochuankeji.tieba.ui.videomaker.j;
import cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog;
import cn.xiaochuankeji.tieba.ui.widget.text.BadgeTextView;
import com.tencent.bugly.crashreport.CrashReport;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class MainActivity extends d implements OnTabChangeListener, e {
    private static final String[] c = new String[]{"tab_home_page", "tab_video_page", "tab_topic", "tab_message", "tab_my"};
    private static final org.aspectj.lang.a.a n = null;
    Animation a;
    private TabHost b;
    private long d = 0;
    private Handler e = new b();
    private cn.xiaochuankeji.tieba.background.splash.b f = new cn.xiaochuankeji.tieba.background.splash.b();
    private c[] g = new c[5];
    private int h;
    private String i = "tab_home_page";
    private a j = new a(this);
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;

    final class a implements Runnable {
        final /* synthetic */ MainActivity a;

        a(MainActivity mainActivity) {
            this.a = mainActivity;
        }

        public void run() {
            long b = this.a.b();
            if (b > 0 && System.currentTimeMillis() - b > com.umeng.analytics.a.j) {
                MainActivity.a("tab_home_page", 1);
            }
            this.a.e.removeCallbacks(this.a.j);
            this.a.e.postDelayed(this.a.j, com.umeng.analytics.a.j);
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class b extends Handler {
        final /* synthetic */ MainActivity a;

        private b(MainActivity mainActivity) {
            this.a = mainActivity;
        }

        public void handleMessage(Message message) {
            if (message.what == 100) {
                this.a.f.a(this.a);
            }
        }
    }

    private static class c {
        View a;
        AnimationTabImageView b;
        TextView c;
        BadgeTextView d;

        private c() {
        }
    }

    private static void m() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("MainActivity.java", MainActivity.class);
        n = bVar.a("method-execution", bVar.a("1", "onCreate", "cn.xiaochuankeji.tieba.ui.base.MainActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 152);
    }

    static {
        m();
    }

    public static void a(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(335544320);
        context.startActivity(intent);
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(335544320);
        intent.putExtra("key_uri_string", str);
        context.startActivity(intent);
        cn.xiaochuankeji.tieba.background.h.d.a().a(5);
    }

    static final void a(MainActivity mainActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        int i = 0;
        if (bundle != null) {
            mainActivity.m = true;
        }
        mainActivity.h = mainActivity.getResources().getDisplayMetrics().widthPixels / 5;
        try {
            mainActivity.a = AnimationUtils.loadAnimation(mainActivity, R.anim.anim_refresh_rotate);
            mainActivity.a.setInterpolator(new AccelerateDecelerateInterpolator());
        } catch (Exception e) {
        }
        super.onCreate(bundle);
        org.greenrobot.eventbus.c.a().a(mainActivity);
        mainActivity.h();
        mainActivity.setContentView(R.layout.activity_main);
        com.android.a.a.c.a(mainActivity.getWindow(), c.a.c.e().d());
        AppController.instance().initWhenEnterApp();
        CrashReport.setUserId(cn.xiaochuankeji.tieba.background.a.g().c() + "");
        cn.xiaochuankeji.tieba.background.h.c.a().g();
        mainActivity.b = mainActivity.getTabHost();
        mainActivity.b.setOnTabChangedListener(mainActivity);
        mainActivity.b.setup(mainActivity.getLocalActivityManager());
        TabSpec a = mainActivity.a(0, "tab_home_page", HomePageActivity.class, "最右");
        TabSpec a2 = mainActivity.a(1, "tab_video_page", UGCTabActivity.class, "跟拍");
        TabSpec a3 = mainActivity.a(2, "tab_topic", TopicActivity.class, "话题");
        TabSpec a4 = mainActivity.a(3, "tab_message", MessageTabActivity.class, "消息");
        TabSpec a5 = mainActivity.a(4, "tab_my", MeActivity.class, "我的");
        mainActivity.b.addTab(a);
        mainActivity.b.addTab(a2);
        mainActivity.b.addTab(a3);
        mainActivity.b.addTab(a4);
        mainActivity.b.addTab(a5);
        View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.view_divider, null);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, mainActivity.getResources().getDimensionPixelOffset(R.dimen.divide_space_1px));
        layoutParams.gravity = 80;
        layoutParams.bottomMargin = mainActivity.getResources().getDimensionPixelOffset(R.dimen.navbar_height);
        mainActivity.b.addView(inflate, layoutParams);
        mainActivity.b.setCurrentTab(mainActivity.getIntent().getIntExtra("DEFAULT_TAB_INDEX", 0));
        a("tab_message", 0);
        a("tab_my", 0);
        if (bundle == null) {
            mainActivity.a(mainActivity.getIntent());
        }
        h.a(mainActivity, "zy_event_homepage_tab", "页面进入");
        mainActivity.runOnUiThread(new Runnable(mainActivity) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void run() {
                j.a(this.a);
            }
        });
        if (UgcCrumbManger.a().a(CrumbType.UGCTAB)) {
            mainActivity.g[1].d.a();
            mainActivity.g[1].d.setVisibility(0);
        }
        while (i < 5) {
            inflate = mainActivity.b.getTabWidget().getChildTabViewAt(i);
            if (inflate != null) {
                inflate.setOnClickListener(new OnClickListener(mainActivity) {
                    final /* synthetic */ MainActivity b;

                    public void onClick(View view) {
                        if (this.b.b.getCurrentTab() != i) {
                            this.b.b.setCurrentTab(i);
                        } else {
                            this.b.onTabChanged(MainActivity.c[i]);
                        }
                    }
                });
            }
            i++;
        }
        mainActivity.l();
        cn.xiaochuankeji.tieba.push.e.a.a().a(mainActivity, 1);
        cn.xiaochuankeji.tieba.background.k.c.a((Context) mainActivity, mainActivity.e);
    }

    public void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(n, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new m(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void a(Intent intent) {
        if (!OpenActivityUtils.a(intent)) {
            Object stringExtra = getIntent().getStringExtra("key_uri_string");
            if (!TextUtils.isEmpty(stringExtra)) {
                getIntent().removeExtra("key_uri_string");
                OpenActivityUtils.a((Context) this, Uri.parse(stringExtra));
            }
        }
    }

    private void h() {
        cn.xiaochuankeji.tieba.background.g.c.a((e) this);
        cn.xiaochuankeji.tieba.background.g.c.a((Activity) this);
    }

    public void a(cn.xiaochuankeji.tieba.background.g.a aVar) {
        com.izuiyou.a.a.b.c("LocationUpdate");
        if (aVar.a() != 0.0d || aVar.b() != 0.0d) {
            cn.xiaochuankeji.tieba.background.utils.d.a().a("" + aVar.b(), "" + aVar.a());
        }
    }

    protected void onResume() {
        super.onResume();
        this.e.sendEmptyMessageDelayed(100, 3000);
        this.e.post(this.j);
        cn.xiaochuankeji.tieba.background.h.c.a().a((Activity) this);
    }

    protected void onPause() {
        super.onPause();
        this.e.removeMessages(100);
        this.e.removeCallbacks(this.j);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent);
    }

    private TabSpec a(int i, final String str, Class<?> cls, String str2) {
        final c cVar = new c();
        View inflate = LayoutInflater.from(this).inflate(R.layout.view_main_tab, null);
        inflate.setLayoutParams(new LayoutParams(this.h, -1));
        cVar.a = inflate;
        cVar.b = (AnimationTabImageView) inflate.findViewById(R.id.iconTabItem);
        cVar.c = (TextView) inflate.findViewById(R.id.textTabItem);
        cVar.d = (BadgeTextView) inflate.findViewById(R.id.crumb);
        cVar.c.setText(str2);
        Intent intent = new Intent(this, cls);
        if (str.equals("tab_message")) {
            intent.putExtra("DEFAULT_SEGMENT_IDX", getIntent().getIntExtra("DEFAULT_SEGMENT_IDX", 0));
        }
        cVar.b.setAnimationListener(new cn.xiaochuankeji.tieba.ui.homepage.AnimationTabImageView.a(this) {
            final /* synthetic */ MainActivity c;

            public void a() {
                if (!this.c.i.equals(str)) {
                    cVar.b.setSelected(false);
                }
            }
        });
        this.g[i] = cVar;
        return this.b.newTabSpec(str).setIndicator(inflate).setContent(intent);
    }

    protected void onDestroy() {
        super.onDestroy();
        org.greenrobot.eventbus.c.a().c(this);
        AppController.instance().clearWhenExitApp();
        cn.xiaochuankeji.tieba.background.utils.a.d.a().c();
        cn.xiaochuankeji.tieba.background.g.c.b((e) this);
        HomePageActivity.a = 0;
        cn.xiaochuankeji.tieba.background.post.b.a().a(false);
    }

    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.d < 3000) {
            finish();
            return;
        }
        cn.htjyb.ui.widget.a.a((Context) this, (CharSequence) "再按一次返回键，退出程序", 0).show();
        this.d = currentTimeMillis;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (cn.xiaochuankeji.tieba.background.a.a != null) {
            cn.xiaochuankeji.tieba.background.a.a.a(i, i2, intent);
        }
        if (cn.xiaochuankeji.tieba.background.a.b != null) {
            cn.xiaochuankeji.tieba.background.a.b.a(i, i2, intent);
        }
    }

    public void a(c.a.g.a aVar, Object obj) {
        super.a(aVar, obj);
        i();
        onTabChanged(this.i);
        com.android.a.a.c.a(getWindow(), c.a.c.e().d());
    }

    @l(a = ThreadMode.MAIN)
    public void ugcCrumb(cn.xiaochuankeji.tieba.ui.homepage.ugc.UgcCrumbManger.a aVar) {
        if (!aVar.a && !aVar.b) {
            return;
        }
        if (this.b.getCurrentTab() != 1) {
            this.g[1].d.a();
            this.g[1].d.setVisibility(0);
            UgcCrumbManger.a().a(CrumbType.UGCTAB, true);
            return;
        }
        UgcCrumbManger.a().a(CrumbType.UGCTAB, false);
    }

    public static void a() {
        Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
        edit.putLong("last_refresh_timestamp", System.currentTimeMillis());
        edit.apply();
    }

    public long b() {
        return cn.xiaochuankeji.tieba.background.a.a().getLong("last_refresh_timestamp", -1);
    }

    @l(a = ThreadMode.MAIN)
    public void updateTabBadge(f fVar) {
        boolean z = true;
        if ("tab_home_page".equals(fVar.a)) {
            if (fVar.b > 0) {
                this.g[0].d.a();
                this.g[0].d.setVisibility(0);
                return;
            }
            this.g[0].d.setVisibility(8);
        } else if ("tab_message".equals(fVar.a)) {
            int h = cn.xiaochuankeji.tieba.background.h.d.a().h();
            if (h <= 0) {
                this.g[3].d.setVisibility(8);
                return;
            }
            this.g[3].d.setBadgeCount(h);
            this.g[3].d.setVisibility(0);
        } else if (!"tab_my".equals(fVar.a)) {
        } else {
            if (fVar.b < 0) {
                this.g[4].d.setVisibility(8);
                return;
            }
            boolean b = cn.xiaochuankeji.tieba.background.assessor.b.a().b();
            boolean z2 = cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_first_assessor_remind", true);
            boolean z3 = cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_can_update", true);
            if (!(b && z2)) {
                z = false;
            }
            if (fVar.b > 0 || r0 || z3) {
                this.g[4].d.a();
                this.g[4].d.setVisibility(0);
                return;
            }
            this.g[4].d.setVisibility(8);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void updateTabIndicator(cn.xiaochuankeji.tieba.ui.homepage.f fVar) {
        if (this.g[0] != null && this.i.equalsIgnoreCase("tab_home_page")) {
            this.g[0].b.setImageResource(fVar.a ? R.drawable.ic_tab_home_refresh : R.drawable.ic_tab_home_selected);
            this.g[0].c.setText(fVar.a ? "刷新" : "最右");
            if (!fVar.a || this.a == null) {
                if (this.a != null) {
                    this.g[0].b.clearAnimation();
                }
            } else if (!cn.xiaochuankeji.tieba.background.a.a().getBoolean("tip_refresh", false)) {
                k();
            } else if (fVar.b) {
                this.g[0].b.startAnimation(this.a);
            }
            this.l = fVar.a;
        }
    }

    @l(a = ThreadMode.MAIN)
    public void showTopicGuide(cn.xiaochuankeji.tieba.b.b bVar) {
        j();
    }

    public void a(boolean z) {
        if (this.k != z) {
            this.k = z;
            this.g[1].b.setImageResource(this.k ? R.drawable.ic_tab_home_refresh : R.drawable.ic_tab_genpai_selected);
            this.g[1].c.setText(this.k ? "刷新" : "跟拍");
        }
    }

    public void onTabChanged(String str) {
        boolean equals = String.valueOf(str).equals(this.i);
        i();
        if ("tab_home_page".equals(str)) {
            b(equals);
        } else if ("tab_video_page".equals(str)) {
            c(equals);
        } else if ("tab_topic".equals(str)) {
            d(equals);
        } else if ("tab_message".equals(str)) {
            e(equals);
        } else if ("tab_my".equals(str)) {
            f(equals);
        }
        this.i = str;
    }

    private void b(boolean z) {
        if (z) {
            if (this.m) {
                this.m = false;
                return;
            }
            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.homepage.h());
            a("tab_home_page");
        }
        a();
        this.g[0].b.a();
        this.g[0].c.setText("最右");
        this.g[0].c.setSelected(true);
        if (this.l) {
            this.g[0].b.setImageResource(R.drawable.ic_tab_home_refresh);
        }
    }

    private void c(boolean z) {
        h.a(this, "zy_event_follow_tab", "页面进入");
        int i = cn.xiaochuankeji.tieba.background.a.a().getInt("key_init_position", 0);
        if (i == 0) {
            h.a(this, "zy_event_follow_tab_topic", "页面进入");
        } else if (1 == i) {
            h.a(this, "zy_event_follow_tab_friend_moment", "页面进入");
        }
        if (this.k) {
            this.g[1].b.setImageResource(R.drawable.ic_tab_home_refresh);
            this.g[1].c.setText("刷新");
        } else {
            this.g[1].b.setImageResource(c.a.d.a.a.a().d(R.drawable.tab_selector_genpai));
            this.g[1].c.setText("跟拍");
            this.g[1].b.a();
        }
        this.g[1].c.setSelected(true);
        this.g[1].d.setVisibility(8);
        UgcCrumbManger.a().a(CrumbType.UGCTAB, false);
        if (!z) {
            return;
        }
        if (this.m) {
            this.m = false;
            return;
        }
        org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.homepage.ugc.a());
    }

    private void d(boolean z) {
        h.a(this, "zy_event_topic_tab", "页面进入");
        this.g[2].b.a();
        this.g[2].c.setText("话题");
        this.g[2].c.setSelected(true);
    }

    private void e(boolean z) {
        if (z) {
            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.push.c.e());
            org.greenrobot.eventbus.c.a().d(new g());
        }
        h.a(this, "zy_event_message_tab", "页面进入");
        int i = cn.xiaochuankeji.tieba.background.a.a().getInt("kMessageActivityLastTab", 0);
        if (i == 0) {
            h.a(this, "zy_event_message_tab_notice", "页面进入");
        } else if (1 == i) {
            h.a(this, "zy_event_message_tab_letter", "页面进入");
        }
        this.g[3].b.a();
        this.g[3].c.setText("消息");
        this.g[3].c.setSelected(true);
    }

    private void f(boolean z) {
        Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
        edit.putBoolean("key_first_assessor_remind", false);
        edit.putBoolean("key_can_update", false);
        edit.apply();
        h.a(this, "zy_event_my_tab", "页面进入");
        this.g[4].d.setVisibility(8);
        this.g[4].b.a();
        this.g[4].c.setText("我的");
        this.g[4].c.setSelected(true);
    }

    private void i() {
        this.g[0].b.setImageResource(c.a.d.a.a.a().d(R.drawable.tab_selector_home));
        this.g[0].c.setText("最右");
        this.g[1].b.setImageResource(c.a.d.a.a.a().d(R.drawable.tab_selector_genpai));
        this.g[1].c.setText("跟拍");
        this.g[2].b.setImageResource(c.a.d.a.a.a().d(R.drawable.tab_selector_topic));
        this.g[3].b.setImageResource(c.a.d.a.a.a().d(R.drawable.tab_selector_message));
        this.g[4].b.setImageResource(c.a.d.a.a.a().d(R.drawable.tab_selector_me));
        this.g[0].b.setSelected(false);
        this.g[0].c.setSelected(false);
        this.g[1].b.setSelected(false);
        this.g[1].c.setSelected(false);
        this.g[2].b.setSelected(false);
        this.g[2].c.setSelected(false);
        this.g[3].b.setSelected(false);
        this.g[3].c.setSelected(false);
        this.g[4].b.setSelected(false);
        this.g[4].c.setSelected(false);
    }

    public static void a(String str, int i) {
        org.greenrobot.eventbus.c.a().d(new f(str, i));
    }

    public static void a(String str) {
        org.greenrobot.eventbus.c.a().d(new f(str, -1));
        if ("tab_home_page".equalsIgnoreCase(str)) {
            a();
        }
    }

    private void j() {
        if (!cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_show_topic_guide", false)) {
            new cn.xiaochuankeji.tieba.api.config.a().b().a(rx.a.b.a.a()).b(new rx.j<GuideJson>(this) {
                final /* synthetic */ MainActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((GuideJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(GuideJson guideJson) {
                    Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
                    edit.putBoolean("key_show_topic_guide", true);
                    edit.apply();
                    if (guideJson != null && guideJson.guide == 1) {
                        View imageView = new ImageView(this.a);
                        imageView.setScaleType(ScaleType.CENTER);
                        imageView.setImageResource(R.drawable.tips_topic_guide);
                        final View frameLayout = new FrameLayout(this.a);
                        frameLayout.addView(imageView, new FrameLayout.LayoutParams(-2, -2, 81));
                        frameLayout.setBackgroundResource(R.color.black_a40);
                        frameLayout.setFitsSystemWindows(true);
                        final ViewGroup viewGroup = (ViewGroup) this.a.findViewById(16908290);
                        viewGroup.addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
                        frameLayout.setOnTouchListener(new OnTouchListener(this) {
                            final /* synthetic */ AnonymousClass4 c;

                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                if (motionEvent.getAction() != 0) {
                                    return false;
                                }
                                viewGroup.removeView(frameLayout);
                                return true;
                            }
                        });
                    }
                }
            });
        }
    }

    @l(a = ThreadMode.MAIN)
    public void message(cn.xiaochuankeji.tieba.push.c.c cVar) {
        a("tab_message", 0);
    }

    private void k() {
        SDGuideDialog sDGuideDialog = new SDGuideDialog(this);
        sDGuideDialog.a(null, R.drawable.tips_refresh, 83, 0, 0, false);
        sDGuideDialog.setOnDismissListener(new cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog.c(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void a(SDGuideDialog sDGuideDialog) {
                sDGuideDialog.c();
                Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
                edit.putBoolean("tip_refresh", true);
                edit.apply();
            }
        });
        sDGuideDialog.b();
    }

    private void l() {
        cn.xiaochuankeji.tieba.background.a.p().d().execute(new Runnable(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (cn.xiaochuankeji.tieba.background.h.a.c() > 209715200) {
                    cn.xiaochuankeji.tieba.background.h.a.b();
                }
            }
        });
    }
}
