package com.budejie.www.activity.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.widget.ImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.QiHooActivity;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.activity.video.k;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.busevent.DetailAction;
import com.budejie.www.busevent.LoadMoreEvent;
import com.budejie.www.busevent.LoadMoreEvent.LoadMoreAction;
import com.budejie.www.busevent.SimpleVideoClickAction;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.aq;
import com.budejie.www.widget.FixedViewPager;
import com.nostra13.universalimageloader.core.d;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PostDetailActivity extends QiHooActivity {
    public boolean a = false;
    private FixedViewPager b;
    private a d;
    private PostDetailActivity e;
    private boolean f = true;
    private int g = -1;
    private String h;
    private boolean i = false;
    private String j;
    private ListItemObject k;
    private String l;
    private ArrayList<ListItemObject> m = new ArrayList();
    private ArrayList<ListItemObject> n = new ArrayList();
    private ImageView o;
    private boolean p = false;
    private boolean q = true;
    private boolean r = false;
    private boolean s;
    private Handler t = new Handler(this) {
        final /* synthetic */ PostDetailActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (!an.g(this.a)) {
                switch (message.what) {
                    case 0:
                        if (this.a.d != null) {
                            this.a.m.addAll(this.a.n);
                            this.a.d.notifyDataSetChanged();
                            return;
                        }
                        return;
                    case 1:
                        k.a(this.a.e).d(false);
                        return;
                    default:
                        return;
                }
            }
        }
    };

    public class a extends FragmentStatePagerAdapter {
        final /* synthetic */ PostDetailActivity a;

        public a(PostDetailActivity postDetailActivity, FragmentManager fragmentManager) {
            this.a = postDetailActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            Fragment cVar = new c();
            Bundle bundle = new Bundle();
            if (this.a.m != null && this.a.m.size() > i) {
                bundle.putSerializable("listitem_object", (Serializable) this.a.m.get(i));
                bundle.putBoolean("is_from_commonlabel", this.a.a);
                bundle.putString("request_detail_url", this.a.j);
                bundle.putString("msg_wid", this.a.l);
                if (i == 0) {
                    bundle.putBoolean("to_comment", this.a.i);
                }
            }
            cVar.setArguments(bundle);
            return cVar;
        }

        public int getCount() {
            return this.a.m == null ? 0 : this.a.m.size();
        }

        public Parcelable saveState() {
            return null;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.e = (PostDetailActivity) new WeakReference(this).get();
        getWindow().setFlags(ViewCompat.MEASURED_STATE_TOO_SMALL, ViewCompat.MEASURED_STATE_TOO_SMALL);
        setContentView(R.layout.activity_post_detail);
        aq.a(this, R.color.black);
        f();
        g();
        EventBus.getDefault().register(this, 0);
    }

    protected void onResume() {
        super.onResume();
        this.f = true;
    }

    protected void onPause() {
        super.onPause();
        this.f = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        k.a((Context) this).p();
        c cVar = (c) a();
        if (cVar != null) {
            cVar.i();
            if (d()) {
                String f = cVar.f();
                PostsActivity b = ((BudejieApplication) getApplication()).b(this.h);
                if (!(b == null || TextUtils.isEmpty(f))) {
                    b.c(f);
                }
            }
        }
        e();
    }

    private void e() {
        if (this.m != null) {
            this.m.clear();
            this.m = null;
        }
        if (this.n != null) {
            this.n.clear();
            this.n = null;
        }
        if (c.e != null) {
            c.e.clear();
            c.e = null;
        }
        this.k = null;
        d.a().c();
        d.a().d();
        setContentView(R.layout.view_null);
    }

    private void f() {
        Intent intent = getIntent();
        this.h = intent.getStringExtra("origin_type");
        this.a = intent.getBooleanExtra("is_from_commonlabel", false);
        this.k = (ListItemObject) intent.getSerializableExtra("listitem_object");
        this.i = intent.getBooleanExtra("to_comment", false);
        this.l = intent.getStringExtra("msg_wid");
        this.j = intent.getStringExtra("request_detail_url");
        this.m.clear();
        if (d()) {
            a(true);
            this.m.addAll(this.n);
            return;
        }
        this.m.add(this.k);
    }

    private void g() {
        this.b = (FixedViewPager) findViewById(R.id.viewpager);
        this.b.setOffscreenPageLimit(2);
        this.d = new a(this, getSupportFragmentManager());
        this.b.setAdapter(this.d);
        this.b.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ PostDetailActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
                aa.b("PostDetailActivity", "onPageScrolled position=" + i);
                if (this.a.d()) {
                    if (i == 0 && this.a.p && this.a.q && i2 == 0) {
                        this.a.q = false;
                        this.a.e.finish();
                    }
                    if (!this.a.q || f != 0.0f || i2 != 0) {
                        return;
                    }
                    if (this.a.g < 0) {
                        this.a.c();
                        this.a.g = i;
                    } else if (this.a.g == i) {
                        this.a.t.sendEmptyMessageDelayed(1, 100);
                    }
                } else if (!this.a.s) {
                    this.a.s = true;
                    this.a.c();
                }
            }

            public void onPageSelected(int i) {
                aa.b("PostDetailActivity", "onPageSelected position=" + i);
                aa.b("PostDetailActivity", "onPageSelected currentPosition=" + this.a.g);
                k.a(this.a.e).p();
                if (i == this.a.b.getAdapter().getCount() - 5 || i == this.a.b.getAdapter().getCount() - 1) {
                    EventBus.getDefault().post(new LoadMoreEvent(LoadMoreAction.LOAD_START, this.a.h));
                }
                if (i == this.a.g + 1) {
                    MobclickAgent.onEvent(this.a.e, "E02_A15", "详情页滑动到下一页");
                }
                this.a.g = i;
            }

            public void onPageScrollStateChanged(int i) {
                this.a.p = i == 1;
                if (1 == i && this.a.o.isShown()) {
                    this.a.o.setVisibility(8);
                }
            }
        });
        this.o = (ImageView) findViewById(R.id.horizontal_scrollable_tips);
        if (!d() || ai.x(this.e)) {
            this.o.setVisibility(8);
            return;
        }
        this.o.setVisibility(0);
        ai.y(this.e);
    }

    public Fragment a() {
        return (Fragment) this.d.instantiateItem(this.b, this.b.getCurrentItem());
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            b();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.r = configuration.orientation == 2;
        if (this.b != null) {
            this.b.setNoScroll(this.r);
        }
        if (!this.r) {
            b();
        } else if (this.o.isShown()) {
            this.o.setVisibility(8);
        }
    }

    public void b() {
        c cVar = (c) a();
        if (cVar != null) {
            cVar.d();
        }
    }

    public void c() {
        c cVar = (c) a();
        if (cVar != null) {
            cVar.e();
        }
    }

    public void onEventBackgroundThread(LoadMoreEvent loadMoreEvent) {
        if (!an.g((Activity) this) && LoadMoreAction.LOAD_FINISH == loadMoreEvent.a() && d()) {
            a(false);
            this.t.sendEmptyMessage(0);
        }
    }

    public void a(boolean z) {
        this.n.clear();
        PostsActivity b = ((BudejieApplication) getApplication()).b(this.h);
        if (b != null) {
            List a = b.a(z);
            if (!com.budejie.www.goddubbing.c.a.a(a)) {
                List subList;
                if (z && this.k != null) {
                    int i = 0;
                    while (i < a.size()) {
                        if (this.k.getWid().equals(((ListItemObject) a.get(i)).getWid())) {
                            break;
                        }
                        i++;
                    }
                    i = 0;
                    if (i != 0) {
                        subList = a.subList(i, a.size());
                        for (ListItemObject listItemObject : r0) {
                            if (!TextUtils.isEmpty(listItemObject.getWid())) {
                                this.n.add(listItemObject);
                            }
                        }
                    }
                }
                subList = a;
                for (ListItemObject listItemObject2 : r0) {
                    if (!TextUtils.isEmpty(listItemObject2.getWid())) {
                        this.n.add(listItemObject2);
                    }
                }
            }
        }
    }

    public boolean d() {
        return VERSION.SDK_INT >= 21 && (("tag_essence".equals(this.h) || "tag_new".equals(this.h)) && this.k != null);
    }

    public void onEventMainThread(DetailAction detailAction) {
        if (!this.f) {
            return;
        }
        if (DetailAction.SCREEN_SHOT == detailAction) {
            c cVar = (c) a();
            if (cVar != null) {
                cVar.g();
            }
        } else if (DetailAction.FINISH_PAGE == detailAction) {
            finish();
        }
    }

    public void onEventMainThread(SimpleVideoClickAction simpleVideoClickAction) {
        if (SimpleVideoClickAction.TO_NORMAL == simpleVideoClickAction) {
            c cVar = (c) a();
            if (cVar != null) {
                cVar.h();
            }
        }
    }
}
