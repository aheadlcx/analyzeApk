package cn.xiaochuankeji.tieba.ui.homepage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.abmgr.data.RequireStyle;
import cn.xiaochuankeji.tieba.abmgr.data.Requirement;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.ui.discovery.search.SearchAllActivity;
import cn.xiaochuankeji.tieba.ui.hollow.edit.CreateHollowActivity;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.TreeHoleFragment;
import cn.xiaochuankeji.tieba.ui.homepage.feed.newfeed.FeedMainFragment;
import cn.xiaochuankeji.tieba.ui.publish.PublishPostActivity;
import cn.xiaochuankeji.tieba.ui.tag.NavigatorTagActivity;
import cn.xiaochuankeji.tieba.ui.tale.TaleCreateActivity;
import cn.xiaochuankeji.tieba.ui.videomaker.VideoRecordActivity;
import cn.xiaochuankeji.tieba.ui.voice.VoiceCreateActivity;
import cn.xiaochuankeji.tieba.ui.voice.b.d;
import cn.xiaochuankeji.tieba.ui.widget.SDTopSheet;
import cn.xiaochuankeji.tieba.ui.widget.TBViewPager;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;
import cn.xiaochuankeji.tieba.ui.widget.indicator.b;
import cn.xiaochuankeji.tieba.ui.widget.indicator.f;
import com.sina.weibo.sdk.api.CmdObject;
import java.util.ArrayList;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class HomePageActivity extends cn.xiaochuankeji.tieba.ui.base.a implements OnPageChangeListener, OnClickListener, cn.xiaochuankeji.tieba.abmgr.a.a {
    public static int a;
    private static final org.aspectj.lang.a.a n = null;
    private TBViewPager b;
    private a c;
    private View d;
    private ImageView e;
    private MagicIndicator f;
    private f g = new f();
    private b h;
    private View i;
    private View j;
    private ImageView k;
    @SuppressLint({"HandlerLeak"})
    private Handler l = new Handler(this) {
        final /* synthetic */ HomePageActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.b(true);
        }
    };
    private boolean m = true;

    private class a extends FragmentStatePagerAdapter {
        ArrayList<NavigatorTag> a;
        boolean b = false;
        final /* synthetic */ HomePageActivity c;

        a(HomePageActivity homePageActivity, FragmentManager fragmentManager) {
            this.c = homePageActivity;
            super(fragmentManager);
            a();
        }

        public void a() {
            this.a = cn.xiaochuankeji.tieba.background.a.q().a();
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.a.size();
        }

        public Fragment getItem(int i) {
            return a((NavigatorTag) this.a.get(i));
        }

        public NavigatorTag a(int i) {
            if (this.a == null || i < 0 || i > this.a.size()) {
                return null;
            }
            return (NavigatorTag) this.a.get(i);
        }

        public int getItemPosition(Object obj) {
            if (this.b) {
                return -2;
            }
            return super.getItemPosition(obj);
        }

        public Fragment a(NavigatorTag navigatorTag) {
            if (cn.xiaochuankeji.tieba.background.a.q().a(navigatorTag)) {
                return cn.xiaochuankeji.tieba.ui.tale.a.a(navigatorTag);
            }
            if (cn.xiaochuankeji.tieba.background.a.q().d(navigatorTag)) {
                return TreeHoleFragment.a(navigatorTag);
            }
            if (cn.xiaochuankeji.tieba.background.a.q().b(navigatorTag)) {
                return FeedMainFragment.a(navigatorTag);
            }
            if (cn.xiaochuankeji.tieba.background.a.q().c(navigatorTag)) {
                return cn.xiaochuankeji.tieba.ui.voice.b.a(navigatorTag);
            }
            return d.a(navigatorTag);
        }

        public Parcelable saveState() {
            return super.saveState();
        }

        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
            try {
                super.restoreState(parcelable, getClass().getClassLoader());
            } catch (Exception e) {
            }
        }
    }

    static {
        r();
    }

    private static void r() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("HomePageActivity.java", HomePageActivity.class);
        n = bVar.a("method-execution", bVar.a("1", "onCreate", "cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity", "android.os.Bundle", "savedInstance", "", "void"), 104);
    }

    static final void a(HomePageActivity homePageActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        cn.xiaochuankeji.aop.permission.a.a((Context) homePageActivity).a(new PermissionItem("android.permission.READ_PHONE_STATE", "android.permission.READ_EXTERNAL_STORAGE").deniedMessage("开启以下权限才能正常浏览图片和视频").needGotoSetting(true).rationalButton("确定").rationalMessage("正常使用权限，不会对您的手机造成伤害和泄露隐私").runIgnorePermission(true).settingText("前往"), new e(homePageActivity) {
            final /* synthetic */ HomePageActivity a;

            {
                this.a = r1;
            }

            public void permissionGranted() {
            }

            public void permissionDenied() {
            }
        });
    }

    public void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(n, this, this, bundle);
        c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    public boolean h() {
        return false;
    }

    protected int a() {
        return R.layout.activity_tab_home;
    }

    protected void c() {
        super.c();
        View findViewById = findViewById(R.id.header);
        if (!com.android.a.a.c.a()) {
            findViewById.setPadding(findViewById.getPaddingLeft(), getResources().getDimensionPixelOffset(R.dimen.status_bar_height), findViewById.getPaddingRight(), 0);
        }
        this.e = (ImageView) findViewById(R.id.ivPublishPost);
        this.d = findViewById(R.id.ivSearch);
        this.f = (MagicIndicator) findViewById(R.id.indicator);
        this.i = findViewById(R.id.ic_recommend_more);
        this.j = findViewById(R.id.tag_crumb);
        j();
    }

    private void j() {
        cn.xiaochuankeji.tieba.abmgr.a.b.a().a((cn.xiaochuankeji.tieba.abmgr.a.a) this);
        this.k = (ImageView) findViewById(R.id.home_refresh_view);
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HomePageActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!(this.a.c == null || this.a.b == null)) {
                    NavigatorTag a = this.a.c.a(this.a.b.getCurrentItem());
                    if (a != null) {
                        org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.a("refreshbutton", a));
                    }
                }
                h.a(this.a, "zy_event_homepage_refresh", "点击首页刷新按钮");
            }
        });
        cn.xiaochuankeji.tieba.abmgr.b.a.a(this.k);
    }

    protected void i_() {
        this.b = (TBViewPager) findViewById(R.id.viewpager);
        this.c = new a(this, getSupportFragmentManager());
        this.b.setAdapter(this.c);
        this.b.setOffscreenPageLimit(1);
        this.h = new b(this);
        k();
        this.h.setSpace(cn.xiaochuankeji.tieba.ui.utils.e.a(9.0f));
        this.h.setIsNeedMargin(false);
        this.h.setAdapter(this.g);
        this.h.setLeftPadding(cn.xiaochuankeji.tieba.ui.utils.e.a(40.0f));
        this.f.setNavigator(this.h);
        this.h.setScrollListener(new cn.xiaochuankeji.tieba.ui.widget.indicator.b.a(this) {
            final /* synthetic */ HomePageActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.b(false);
            }

            public void a() {
                this.a.l.sendMessageDelayed(this.a.l.obtainMessage(0), 2000);
            }

            public void b() {
                this.a.l.removeMessages(0);
            }
        });
        this.g.a(cn.xiaochuankeji.tieba.background.a.q().a());
        int d = cn.xiaochuankeji.tieba.background.a.q().d();
        if (d >= 0) {
            this.b.setCurrentItem(d, false);
            this.f.a(d);
            a = d;
            r.a(((NavigatorTag) cn.xiaochuankeji.tieba.background.a.q().a().get(d)).ename);
        }
        if (VERSION.SDK_INT >= 18) {
            getWindow().setFlags(16777216, 16777216);
        }
        if (this.c != null) {
            this.c.registerDataSetObserver(new DataSetObserver(this) {
                final /* synthetic */ HomePageActivity a;

                {
                    this.a = r1;
                }

                public void onChanged() {
                    super.onChanged();
                    this.a.c.b = false;
                }
            });
        }
        this.b.setTouchListener(new cn.xiaochuankeji.tieba.ui.widget.TBViewPager.a(this) {
            final /* synthetic */ HomePageActivity a;

            {
                this.a = r1;
            }

            public void a(MotionEvent motionEvent) {
                this.a.b(true);
            }
        });
        cn.xiaochuankeji.tieba.background.a.q().m();
    }

    private void k() {
        boolean z;
        int size = cn.xiaochuankeji.tieba.background.a.q().a().size();
        if (size < 6) {
            this.h.setLeftPadding(cn.xiaochuankeji.tieba.ui.utils.e.a(20.0f));
            this.h.setRightPadding(cn.xiaochuankeji.tieba.ui.utils.e.a(40.0f));
            this.i.setVisibility(8);
            this.j.setVisibility(8);
            this.e.setImageResource(R.drawable.navbar_icon_create_t);
        } else {
            if (this.d.getVisibility() == 0) {
                this.h.setLeftPadding(cn.xiaochuankeji.tieba.ui.utils.e.a(40.0f));
            } else {
                this.h.setLeftPadding(cn.xiaochuankeji.tieba.ui.utils.e.a(3.0f));
            }
            this.h.setRightPadding(cn.xiaochuankeji.tieba.ui.utils.e.a(40.0f));
            this.i.setVisibility(0);
            if (cn.xiaochuankeji.tieba.background.a.q().l()) {
                this.j.setVisibility(0);
            } else {
                this.j.setVisibility(8);
            }
            this.e.setImageResource(R.drawable.navbar_icon_create);
        }
        b bVar = this.h;
        if (size < 6) {
            z = true;
        } else {
            z = false;
        }
        bVar.setAdjustMode(z);
        this.h.setSmoothScroll(false);
    }

    protected void j_() {
        super.j_();
        this.e.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.d.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ HomePageActivity a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                return false;
            }
        });
        this.e.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ HomePageActivity a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                return false;
            }
        });
        this.i.setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
        this.b.addOnPageChangeListener(this);
        if (this.g != null) {
            this.g.a(this.b);
        }
        if (a >= 0 && a < cn.xiaochuankeji.tieba.background.a.q().a().size()) {
            if (this.b.getCurrentItem() != a) {
                a = this.b.getCurrentItem();
            }
            this.f.a(a);
        }
    }

    protected void onPause() {
        super.onPause();
        this.b.removeOnPageChangeListener(this);
        if (this.g != null) {
            this.g.c();
        }
        if (d.a().h() && d.a().i()) {
            d.a().f();
            cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(d.a().b().a, d.a().b().f);
        }
        cn.xiaochuankeji.tieba.ui.post.a.a.a().b();
    }

    protected void onDestroy() {
        super.onDestroy();
        cn.xiaochuankeji.tieba.abmgr.a.b.a().b(this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Fragment fragment;
        if (-1 == i2 && ((2 == i || 3 == i) && this.c.getCount() > 0)) {
            fragment = (Fragment) this.c.instantiateItem(this.b, a);
            if (fragment instanceof cn.xiaochuankeji.tieba.ui.tale.a) {
                int h = cn.xiaochuankeji.tieba.background.a.q().h();
                if (h >= 0 && this.b.getCurrentItem() != h) {
                    this.b.setCurrentItem(h, true);
                    fragment = (Fragment) this.c.instantiateItem(this.b, 0);
                }
            }
            if (fragment instanceof d) {
                d dVar = (d) fragment;
                if (2 == i) {
                    dVar.a((Post) intent.getSerializableExtra("publishedPost"));
                } else if (3 == i) {
                    dVar.a((Moment) intent.getSerializableExtra("key_published_main_video"));
                }
                if (cn.xiaochuankeji.tieba.push.e.a.a().a(this, 3)) {
                    g.a("发帖成功");
                }
            }
        } else if (-1 == i2 && 5 == i) {
            boolean z;
            if (intent == null || !intent.getBooleanExtra("key_nav_tag_chage", false)) {
                z = false;
            } else {
                z = true;
            }
            a(z, cn.xiaochuankeji.tieba.background.a.q().b());
        } else if (-1 == i2 && 4 == i) {
            int i3 = cn.xiaochuankeji.tieba.background.a.q().i();
            if (i3 != -1 && i3 < this.c.getCount()) {
                this.b.setCurrentItem(i3, true);
                fragment = (Fragment) this.c.instantiateItem(this.b, i3);
                if (fragment instanceof cn.xiaochuankeji.tieba.ui.tale.a) {
                    fragment.onActivityResult(i, i2, intent);
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.f != null) {
            this.f.a(i, f, i2);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.f != null) {
            this.f.b(i);
        }
    }

    public void onPageSelected(int i) {
        if (this.f != null) {
            this.f.a(i);
        }
        a = i;
        if (a >= 0 && a < cn.xiaochuankeji.tieba.background.a.q().a().size()) {
            NavigatorTag navigatorTag = (NavigatorTag) cn.xiaochuankeji.tieba.background.a.q().a().get(a);
            r.a(navigatorTag.ename);
            if (!navigatorTag.type.equals("voice") && d.a().h()) {
                d.a().e();
            }
            cn.xiaochuankeji.tieba.abmgr.b.a.a(this.k, p());
        }
        a(i);
    }

    private boolean p() {
        if (a < 0 || a >= cn.xiaochuankeji.tieba.background.a.q().a().size()) {
            return false;
        }
        NavigatorTag navigatorTag = (NavigatorTag) cn.xiaochuankeji.tieba.background.a.q().a().get(a);
        if (cn.xiaochuankeji.tieba.background.a.q().c(navigatorTag) || cn.xiaochuankeji.tieba.background.a.q().d(navigatorTag)) {
            return true;
        }
        return false;
    }

    private void a(int i) {
        if (i == 0) {
            h.a(this, "zy_event_homepage_tab_recommend", "页面进入");
        } else if (1 == i) {
            h.a(this, "zy_event_homepage_tab_video", "页面进入");
        } else if (2 == i) {
            h.a(this, "zy_event_homepage_tab_imgtxt", "页面进入");
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSearch:
                SearchAllActivity.a(this, "homepage_act");
                return;
            case R.id.ic_recommend_more:
                ArrayList a = cn.xiaochuankeji.tieba.background.a.q().a();
                if (a.size() > this.b.getCurrentItem()) {
                    cn.xiaochuankeji.tieba.background.a.q().a(((NavigatorTag) a.get(this.b.getCurrentItem())).id);
                }
                this.j.setVisibility(8);
                NavigatorTagActivity.a(this, 5);
                return;
            case R.id.ivPublishPost:
                q();
                return;
            default:
                return;
        }
    }

    private void q() {
        SDTopSheet sDTopSheet = new SDTopSheet(this, new SDTopSheet.b(this) {
            final /* synthetic */ HomePageActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i == 1) {
                    if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "home_tab", 1, 1)) {
                        PublishPostActivity.a(this.a, null, 2);
                    }
                } else if (i == 2) {
                    if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "home_tab", 3, 1)) {
                        VideoRecordActivity.a(this.a, 3, 0, "index");
                    }
                } else if (i == 3) {
                    if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "home_tab", 9)) {
                        TaleCreateActivity.a(this.a, CmdObject.CMD_HOME, null, 4);
                    }
                } else if (i == 4) {
                    if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "home_tab", 41)) {
                        CreateHollowActivity.a(this.a);
                    }
                } else if (i == 5 && cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "home_tab", 43)) {
                    cn.xiaochuankeji.aop.permission.a.a(this.a).a(new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO").rationalMessage("发声音帖需要录音和写文件权限").deniedMessage("发声音帖需要录音和写文件权限").needGotoSetting(true).runIgnorePermission(false), new e(this) {
                        final /* synthetic */ AnonymousClass11 a;

                        {
                            this.a = r1;
                        }

                        public void permissionGranted() {
                            VoiceCreateActivity.a(this.a.a);
                        }

                        public void permissionDenied() {
                            g.a("拒绝权限后无法录声音");
                        }
                    });
                }
            }
        });
        boolean e = cn.xiaochuankeji.tieba.background.a.q().e();
        boolean f = cn.xiaochuankeji.tieba.background.a.q().f();
        boolean g = cn.xiaochuankeji.tieba.background.a.q().g();
        sDTopSheet.a("发帖子", R.drawable.icon_publish_post, 1);
        sDTopSheet.a("发跟拍", R.drawable.icon_publish_ugcvideo, 2);
        if (g) {
            sDTopSheet.a("发声音", R.drawable.icon_publish_voice, 5);
        }
        if (f) {
            sDTopSheet.a("发树洞", R.drawable.icon_publish_hollow, 4);
        }
        if (e) {
            sDTopSheet.a("发跟帖", R.drawable.ic_follow_post, 3);
        }
        sDTopSheet.b();
    }

    public void a(Requirement requirement, RequireStyle requireStyle) {
        if (requirement.equals(Requirement.HOME_REFRESH)) {
            cn.xiaochuankeji.tieba.abmgr.b.a.a(this.k, p());
        }
    }

    protected void a(boolean z) {
        super.a(z);
        if (!(this.g == null || isFinishing())) {
            this.g.b();
        }
        if (this.c != null && !isFinishing()) {
            this.c.notifyDataSetChanged();
        }
    }

    @l(a = ThreadMode.MAIN)
    public void refreshByEvent(h hVar) {
        if (this.c != null && this.b != null) {
            NavigatorTag a = this.c.a(this.b.getCurrentItem());
            if (a != null) {
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.a("homebutton", a));
            }
        }
    }

    @l(a = ThreadMode.MAIN)
    public void refreshNavByEvent(a aVar) {
        if (this.c != null) {
            int h = cn.xiaochuankeji.tieba.background.a.q().h();
            if (h >= 0) {
                a(true, h);
            }
        }
    }

    @l(a = ThreadMode.MAIN)
    public void onPublishHollow(final cn.xiaochuankeji.tieba.ui.hollow.recommend.f fVar) {
        int j = cn.xiaochuankeji.tieba.background.a.q().j();
        if (j >= 0) {
            if (j == a) {
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.hollow.recommend.a(fVar.a));
                return;
            }
            this.b.setCurrentItem(j, true);
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ HomePageActivity b;

                public void run() {
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.hollow.recommend.a(fVar.a));
                }
            }, 400);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void onPublishVoice(final cn.xiaochuankeji.tieba.ui.voice.a.c cVar) {
        int k = cn.xiaochuankeji.tieba.background.a.q().k();
        if (k >= 0) {
            this.b.setCurrentItem(k, true);
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ HomePageActivity b;

                public void run() {
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.voice.a.b(cVar.a));
                }
            }, 400);
        }
    }

    public void a(boolean z, int i) {
        if (z) {
            this.c.b = true;
            this.c.a();
            k();
            this.g.a(cn.xiaochuankeji.tieba.background.a.q().a());
        }
        this.b.setCurrentItem(i, true);
        this.f.a(i);
    }

    public static boolean e() {
        ArrayList a = cn.xiaochuankeji.tieba.background.a.q().a();
        if (a != null && a >= 0 && a < a.size()) {
            NavigatorTag navigatorTag = (NavigatorTag) a.get(a);
            if (cn.xiaochuankeji.tieba.background.a.q().e(navigatorTag) && "index".equalsIgnoreCase(navigatorTag.ename)) {
                return true;
            }
        }
        return false;
    }

    private void b(final boolean z) {
        float f = 1.0f;
        if (z != this.m) {
            float f2;
            this.m = z;
            int width = this.d.getWidth();
            View view = this.d;
            String str = "translationX";
            float[] fArr = new float[2];
            fArr[0] = z ? (float) (-width) : 0.0f;
            fArr[1] = z ? 0.0f : (float) (-width);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, str, fArr);
            ImageView imageView = this.e;
            str = "alpha";
            fArr = new float[2];
            if (z) {
                f2 = 0.0f;
            } else {
                f2 = 1.0f;
            }
            fArr[0] = f2;
            if (!z) {
                f = 0.0f;
            }
            fArr[1] = f;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, str, fArr);
            if (z) {
                this.e.setVisibility(0);
            }
            ofFloat2.addListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ HomePageActivity b;

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    this.b.e.setVisibility(z ? 0 : 8);
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setInterpolator(new LinearInterpolator());
            animatorSet.setDuration(300);
            animatorSet.start();
        }
    }
}
