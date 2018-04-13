package cn.xiaochuankeji.tieba.ui.discovery;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.arch.lifecycle.q;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.netlib.NetworkMonitor;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.TopicBanner;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.topic.Category;
import cn.xiaochuankeji.tieba.background.topic.RecommendTopicInitModel;
import cn.xiaochuankeji.tieba.background.topic.RecommendTopicInitModel.CallBack;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.a.b;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.discovery.search.SearchAllActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.FrescoImageLoader;
import cn.xiaochuankeji.tieba.ui.topic.TopicCreateActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.model.TopicBannerModel;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.RefreshLinearLayout;
import cn.xiaochuankeji.tieba.ui.widget.StickyNavLayout;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class TopicActivity extends a implements OnClickListener, cn.xiaochuankeji.tieba.ui.discovery.a.a.a, OnBannerListener {
    private static final org.aspectj.lang.a.a z = null;
    private RecyclerView a;
    private ViewPager b;
    private Banner c;
    private TextView d;
    private ImageView e;
    private StickyNavLayout f;
    private CustomEmptyView g;
    private ArrayList<Category> h;
    private cn.xiaochuankeji.tieba.ui.discovery.a.a i;
    private RecommendTopicInitModel j;
    private ArrayList<TopicBanner> k = new ArrayList();
    private List<String> l = new ArrayList();
    private LinearLayoutManager m;
    private TopicBannerModel n;
    private View o;
    private int p;
    private int q = -1;
    private boolean r;
    private RefreshLinearLayout s;
    private float t;
    private float u;
    private ValueAnimator v;
    private boolean w;
    private boolean x;
    private b y;

    static {
        B();
    }

    private static void B() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("TopicActivity.java", TopicActivity.class);
        z = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.discovery.TopicActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 122);
    }

    static final void a(TopicActivity topicActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        topicActivity.y = cn.xiaochuankeji.tieba.background.utils.a.a.a().a(topicActivity);
        topicActivity.e();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(z, this, this, bundle);
        c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void e() {
        NetworkMonitor.a(new NetworkMonitor.a(this) {
            final /* synthetic */ TopicActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i == 0) {
                    this.a.g.b();
                } else {
                    this.a.g.setVisibility(8);
                }
            }
        });
    }

    public boolean h() {
        return false;
    }

    protected int a() {
        return R.layout.activity_tab_topic;
    }

    protected void i_() {
        int i = 0;
        this.n = (TopicBannerModel) q.a((FragmentActivity) this).a(TopicBannerModel.class);
        this.g = (CustomEmptyView) findViewById(R.id.custom_empty_view);
        this.g.setOnClickListener(this);
        this.g.a();
        this.s = (RefreshLinearLayout) findViewById(R.id.rll);
        this.a = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        this.b = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        this.d = (TextView) findViewById(R.id.tvSearchArea);
        this.c = (Banner) findViewById(R.id.id_stickynavlayout_topview);
        this.c.setOnBannerListener(this);
        this.e = (ImageView) findViewById(R.id.ivAdd);
        this.o = findViewById(R.id.title_container);
        if (!com.android.a.a.c.a()) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.status_bar_height);
            this.o.setPadding(this.o.getPaddingLeft(), dimensionPixelSize, this.o.getPaddingRight(), 0);
            i = dimensionPixelSize;
        }
        this.f = (StickyNavLayout) findViewById(R.id.snl_topic);
        LayoutParams layoutParams = (LayoutParams) this.o.getLayoutParams();
        i += e.a(44.0f);
        layoutParams.height = i;
        this.o.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = this.c.getLayoutParams();
        layoutParams2.height = d.a();
        this.p = layoutParams2.height - i;
        this.c.setLayoutParams(layoutParams2);
        this.f.setMoveOffset(-i);
        y();
        this.c.showLoadingImage();
        z();
        j();
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_TOPIC_REFRESH_FINISH && this.w) {
            this.e.postDelayed(new Runnable(this) {
                final /* synthetic */ TopicActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.k();
                    this.a.w = false;
                    this.a.s.setRefreshing(this.a.w);
                }
            }, 1000);
        }
    }

    private void j() {
        this.t = (float) e.a(70.0f);
        this.v = ValueAnimator.ofFloat(new float[]{0.0f, 360.0f});
        this.v.setRepeatCount(-1);
        this.v.setDuration(1000);
        this.v.setInterpolator(new LinearInterpolator());
        this.v.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ TopicActivity a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.e.setRotation(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.s.setOnScrollListener(new RefreshLinearLayout.a(this) {
            final /* synthetic */ TopicActivity a;

            {
                this.a = r1;
            }

            public void a(float f) {
                ViewGroup.LayoutParams layoutParams = this.a.c.getLayoutParams();
                layoutParams.height = (int) (((float) d.a()) + f);
                this.a.c.setLayoutParams(layoutParams);
                this.a.a(f);
            }

            public void b(float f) {
                ViewGroup.LayoutParams layoutParams = this.a.c.getLayoutParams();
                layoutParams.height = d.a();
                this.a.c.setLayoutParams(layoutParams);
                if (f > this.a.t) {
                    this.a.q();
                    h.a(this.a, "zy_event_topic_tab", "下拉刷新次数");
                    return;
                }
                this.a.p();
            }

            public void a() {
                this.a.p();
            }
        });
    }

    private void k() {
        this.c.startAutoPlay();
        if (this.v != null) {
            this.v.cancel();
        }
        this.e.setScaleX(1.0f);
        this.e.setScaleY(1.0f);
        this.e.setRotation(0.0f);
        r();
    }

    private void p() {
        this.c.startAutoPlay();
        if (this.v != null) {
            this.v.cancel();
        }
        this.e.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_establish));
        this.e.setScaleX(1.0f);
        this.e.setScaleY(1.0f);
        this.e.setRotation(0.0f);
    }

    private void q() {
        this.e.setScaleX(1.0f);
        this.e.setScaleY(1.0f);
        this.v.start();
        org.greenrobot.eventbus.c.a().d(new MessageEvent(MessageEventType.MESSAGE_TOPIC_REFRESH));
        y();
        this.w = true;
        this.s.setRefreshing(this.w);
    }

    private void a(float f) {
        this.c.stopAutoPlay();
        if (f < this.t) {
            this.e.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_refresh));
            this.e.setScaleX(f / this.t);
            this.e.setScaleY(f / this.t);
            this.e.setRotation(-90.0f * (1.0f - (f / this.t)));
        }
    }

    private void r() {
        if (((double) this.u) > 0.8d) {
            c.a.b.a(this.d, R.drawable.topic_search_up, 0, 0, 0);
            c.a.b.a(this.d, (int) R.color.search_hint_up);
            this.e.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_establish_up));
            return;
        }
        c.a.b.a(this.d, R.drawable.topic_search, 0, 0, 0);
        c.a.b.a(this.d, (int) R.color.search_hint);
        this.e.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_establish));
    }

    private void s() {
        if (((double) this.u) > 0.8d) {
            if (!this.r) {
                this.r = true;
                c.a.b.a(this.d, R.drawable.topic_search_up, 0, 0, 0);
                c.a.b.a(this.d, (int) R.color.search_hint_up);
                if (!this.w) {
                    this.e.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_establish_up));
                }
            }
        } else if (((double) this.u) < 0.1d) {
            this.o.setBackgroundResource(R.drawable.bg_search_title);
        } else if (this.r) {
            this.r = false;
            c.a.b.a(this.d, R.drawable.topic_search, 0, 0, 0);
            c.a.b.a(this.d, (int) R.color.search_hint);
            if (!this.w) {
                this.e.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_establish));
            }
        }
    }

    private void t() {
        int a = c.a.d.a.a.a().a((int) R.color.CB);
        final int red = Color.red(a);
        final int green = Color.green(a);
        a = Color.blue(a);
        if (this.q != -1) {
            this.o.setBackgroundColor(Color.argb(this.q, red, green, a));
            r();
        } else {
            this.o.setBackgroundResource(R.drawable.bg_search_title);
        }
        this.f.a(new StickyNavLayout.a(this) {
            final /* synthetic */ TopicActivity d;

            public void a_(int i) {
                this.d.u = ((float) i) / ((float) this.d.p);
                this.d.q = (int) (this.d.u * 255.0f);
                this.d.o.setBackgroundColor(Color.argb(this.d.q, red, green, a));
                this.d.s();
            }
        });
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    private void u() {
        this.c.setImages(this.l).setImageLoader(new FrescoImageLoader()).setBannerStyle(4).setIndicatorGravity(6).setOnBannerListener(this).setDelayTime(5000).setTitleEnable(false).start();
    }

    private void v() {
        w();
        x();
    }

    private void w() {
        this.h = this.j.getTopicCategorys();
        this.m = new LinearLayoutManager(this);
        this.m.setOrientation(0);
        this.a.setLayoutManager(this.m);
        this.a.addItemDecoration(new cn.xiaochuankeji.tieba.ui.discovery.a.c());
        this.i = new cn.xiaochuankeji.tieba.ui.discovery.a.a(this, this.h, -1);
        this.a.setAdapter(this.i);
        this.i.a((cn.xiaochuankeji.tieba.ui.discovery.a.a.a) this);
    }

    private void x() {
        this.b.setAdapter(new cn.xiaochuankeji.tieba.ui.discovery.moretopic.c(getSupportFragmentManager(), this.h));
        this.b.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ TopicActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                this.a.i.a(((Category) this.a.h.get(i)).categoryId);
                this.a.a(i);
                if (i == 0) {
                    h.a(this.a, "zy_event_topic_tab_follow", "页面进入");
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        if (this.j.getShowType() == 1) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ TopicActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b.setCurrentItem(0);
                }
            });
        }
        if (this.j.getShowType() == 2) {
            for (int i = 0; i < this.h.size(); i++) {
                if (((Category) this.h.get(i)).categoryId == this.j.getCurrentCategoryId()) {
                    this.a.post(new Runnable(this) {
                        final /* synthetic */ TopicActivity b;

                        public void run() {
                            this.b.b.setCurrentItem(i);
                        }
                    });
                    break;
                }
            }
        }
        h.a(this, "zy_event_topic_tab_follow", "页面进入");
    }

    private void a(int i) {
        int findFirstVisibleItemPosition = this.m.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.m.findLastVisibleItemPosition();
        if (findFirstVisibleItemPosition != -1) {
            if (i - findFirstVisibleItemPosition > 1) {
                this.a.scrollToPosition(i + 1);
            }
            if (findLastVisibleItemPosition - i > 1) {
                this.a.scrollToPosition(i - 1);
            }
        }
    }

    private void y() {
        this.n.a(new cn.xiaochuankeji.tieba.ui.topic.model.a(this) {
            final /* synthetic */ TopicActivity a;

            {
                this.a = r1;
            }

            public void a(@NonNull List<TopicBanner> list) {
                if (list.isEmpty()) {
                    g.a("banner数据为空哎~");
                    this.a.c.showEmptyImage(true);
                    return;
                }
                this.a.k.clear();
                this.a.k.addAll(list);
                this.a.l.clear();
                Iterator it = this.a.k.iterator();
                while (it.hasNext()) {
                    this.a.l.add(((TopicBanner) it.next()).imageUrl);
                }
                if (this.a.x) {
                    this.a.c.update(this.a.l);
                } else {
                    this.a.u();
                    this.a.x = true;
                }
                this.a.c.showEmptyImage(false);
            }

            public void a(Throwable th) {
                g.a(th == null ? "出错了" : th.getMessage());
                this.a.c.showEmptyImage(true);
            }
        });
    }

    private void z() {
        this.j = RecommendTopicInitModel.getInstance();
        if (this.j.hasData()) {
            v();
        } else {
            this.j.query(new CallBack(this) {
                final /* synthetic */ TopicActivity a;

                {
                    this.a = r1;
                }

                public void queryFinish(boolean z, String str) {
                    Object obj = (!z || this.a.j.getTopicList().size() <= 0) ? null : 1;
                    if (obj != null) {
                        this.a.g.setVisibility(8);
                        this.a.v();
                        return;
                    }
                    this.a.g.b();
                }
            });
        }
    }

    public void onPause() {
        super.onPause();
        this.y.d();
        this.c.stopAutoPlay();
    }

    public void onResume() {
        super.onResume();
        this.y.b();
        t();
        if (this.i != null) {
            this.i.notifyDataSetChanged();
        }
        this.c.postDelayed(new Runnable(this) {
            final /* synthetic */ TopicActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c.startAutoPlay();
            }
        }, 10000);
    }

    public void a(View view, long j) {
        int i = 0;
        while (i < this.h.size()) {
            if (((Category) this.h.get(i)).categoryId == j) {
                break;
            }
            i++;
        }
        i = 0;
        this.b.setCurrentItem(i);
    }

    private void A() {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "topic_tab", 7, 1115)) {
            TopicCreateActivity.a((Context) this, null, "kTopicTab");
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_empty_view:
                y();
                z();
                return;
            case R.id.tvSearchArea:
                h.a(this, "zy_event_topic_tab", "点击搜索");
                SearchAllActivity.a(this, "discovery_act");
                return;
            case R.id.ivAdd:
                h.a(this, "zy_event_topic_tab", "点击创建话题");
                A();
                return;
            default:
                return;
        }
    }

    public void OnBannerClick(int i) {
        TopicBanner topicBanner = (TopicBanner) this.k.get(i);
        HashMap hashMap = new HashMap();
        if (topicBanner.type == 1) {
            Topic topic = new Topic();
            topic._topicID = topicBanner.id;
            hashMap.put("tid", Long.valueOf(topicBanner.id));
            TopicDetailActivity.a((Context) this, topic, "discovery_banner");
            h.a(this, "zy_event_discovery_tab", "Banner点击");
        } else if (topicBanner.type == 3) {
            hashMap.put("url", topicBanner.activityUrl);
            WebActivity.a(this, cn.xiaochuan.jsbridge.b.a(null, topicBanner.activityUrl));
        } else if (topicBanner.type == 9) {
            hashMap.put("pid", Long.valueOf(topicBanner.id));
            PostDetailActivity.a((Context) this, new Post(topicBanner.id));
        }
        cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("click", "banner", hashMap);
    }

    protected void onDestroy() {
        super.onDestroy();
        cn.xiaochuankeji.tieba.background.utils.a.a.a().b();
        Map hashMap = new HashMap();
        hashMap.put("remain_time", Integer.valueOf(this.y.e() / 1000));
        cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("view", "topic", 0, -1, "topicTab", hashMap);
    }
}
