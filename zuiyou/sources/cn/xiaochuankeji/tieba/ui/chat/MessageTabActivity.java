package cn.xiaochuankeji.tieba.ui.chat;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.h.d;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.ui.widget.TBViewPager;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;
import cn.xiaochuankeji.tieba.ui.widget.indicator.o;
import org.aspectj.a.b.b;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class MessageTabActivity extends cn.xiaochuankeji.tieba.ui.base.a implements OnPageChangeListener {
    private static final String[] a = new String[]{"提醒", "私信"};
    private static final org.aspectj.lang.a.a c = null;
    private o b;
    @BindView
    View header;
    @BindView
    MagicIndicator magicIndicator;
    @BindView
    View tips;
    @BindView
    TBViewPager viewpager;

    private static class a extends FragmentStatePagerAdapter {
        a(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public int getCount() {
            return MessageTabActivity.a.length;
        }

        public Fragment getItem(int i) {
            if (i == 0) {
                return NotificationFragment.b();
            }
            return MessageFragment.b();
        }

        public Parcelable saveState() {
            return null;
        }
    }

    private static void q() {
        b bVar = new b("MessageTabActivity.java", MessageTabActivity.class);
        c = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.chat.MessageTabActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 61);
    }

    static {
        q();
    }

    static final void a(MessageTabActivity messageTabActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        messageTabActivity.p();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(c, this, this, bundle);
        c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    public boolean h() {
        return false;
    }

    protected void onDestroy() {
        cn.xiaochuan.push.c.a().a(new Runnable(this) {
            final /* synthetic */ MessageTabActivity a;

            {
                this.a = r1;
            }

            public void run() {
                cn.xiaochuankeji.tieba.push.b.b.c(cn.xiaochuankeji.tieba.background.a.g().c());
                cn.xiaochuankeji.tieba.push.b.c.a();
            }
        });
        super.onDestroy();
    }

    protected int a() {
        return R.layout.activity_tab_message;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        PagerAdapter aVar = new a(getSupportFragmentManager());
        if (!com.android.a.a.c.a()) {
            this.header.setPadding(this.header.getPaddingLeft(), getResources().getDimensionPixelOffset(R.dimen.status_bar_height), this.header.getPaddingRight(), 0);
        }
        this.viewpager.setAdapter(aVar);
        this.viewpager.setOffscreenPageLimit(2);
        Object bVar = new cn.xiaochuankeji.tieba.ui.widget.indicator.b(this);
        bVar.setAdjustMode(true);
        this.b = new o(a);
        bVar.setAdapter(this.b);
        this.magicIndicator.setNavigator(bVar);
        int j = j();
        this.viewpager.setCurrentItem(j);
        bVar.a(j);
        if (j == 0) {
            h.a(this, "zy_event_message_tab_notice", "页面进入");
        }
    }

    private int j() {
        int i;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            i = extras.getInt("DEFAULT_SEGMENT_IDX", 0);
        } else {
            i = cn.xiaochuankeji.tieba.background.a.a().getInt("kMessageActivityLastTab", 0);
        }
        cn.xiaochuankeji.tieba.background.a.a().edit().putInt("kMessageActivityLastTab", i).apply();
        return i;
    }

    protected void onResume() {
        super.onResume();
        if (this.viewpager != null) {
            this.viewpager.addOnPageChangeListener(this);
        }
        if (this.b != null) {
            this.b.a(this.viewpager);
        }
        k();
    }

    protected void onPause() {
        super.onPause();
        if (this.viewpager != null) {
            this.viewpager.removeOnPageChangeListener(this);
        }
        if (this.b != null) {
            this.b.c();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putInt("key_index", this.viewpager.getCurrentItem());
        }
        if (this.viewpager != null) {
            this.viewpager.onSaveInstanceState();
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        com.izuiyou.a.a.b.e(bundle);
        if (bundle != null) {
            int i = bundle.getInt("key_index", -1);
            if (i > 0) {
                bundle.remove("key_index");
                if (this.magicIndicator != null) {
                    this.magicIndicator.a(i);
                }
                if (this.viewpager != null) {
                    this.viewpager.setCurrentItem(i);
                }
            }
        }
    }

    private void k() {
        int i = 8;
        Context appContext = BaseApplication.getAppContext();
        if (cn.xiaochuan.push.b.b.a(appContext)) {
            this.tips.setVisibility(8);
            return;
        }
        boolean z = cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_n_p_close", false);
        View view = this.tips;
        if (!z) {
            i = 0;
        }
        view.setVisibility(i);
        h.a(appContext, "zy_event_notfication_open_remind", "tag1");
    }

    @OnClick
    public void tipsClose() {
        this.tips.setVisibility(8);
        cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("key_n_p_close", true).apply();
        h.a(this, "zy_event_notfication_open_remind", "tag3");
    }

    @OnClick
    public void goSetting() {
        cn.xiaochuan.push.b.b.a();
        h.a(this, "zy_event_notfication_open_remind", "tag2");
    }

    protected void a(boolean z) {
        super.a(z);
        if (!(this.viewpager == null || this.viewpager.getAdapter() == null)) {
            this.viewpager.getAdapter().notifyDataSetChanged();
        }
        if (this.b != null) {
            this.b.b();
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.magicIndicator != null) {
            this.magicIndicator.a(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
        edit.putInt("kMessageActivityLastTab", i);
        edit.apply();
        if (i == 0) {
            h.a(this, "zy_event_message_tab_notice", "页面进入");
        } else if (i == 1) {
            h.a(this, "zy_event_message_tab_letter", "页面进入");
        }
        if (this.magicIndicator != null) {
            this.magicIndicator.a(i);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.magicIndicator != null) {
            this.magicIndicator.b(i);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void message(cn.xiaochuankeji.tieba.push.c.c cVar) {
        p();
    }

    private void p() {
        int i = -1;
        int g = d.a().g();
        int f = d.a().f();
        if (this.b != null) {
            o oVar = this.b;
            String str = a[0];
            if (g <= 0) {
                g = -1;
            }
            oVar.a(str, g);
            o oVar2 = this.b;
            String str2 = a[1];
            if (f > 0) {
                i = f;
            }
            oVar2.a(str2, i);
        }
    }
}
