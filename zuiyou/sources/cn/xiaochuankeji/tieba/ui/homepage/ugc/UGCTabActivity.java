package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.UgcEventSummaryJson.EventIcon;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.base.MainActivity;
import cn.xiaochuankeji.tieba.ui.base.g;
import cn.xiaochuankeji.tieba.ui.discovery.search.SearchAllActivity;
import cn.xiaochuankeji.tieba.ui.homepage.banner.UgcEventListActivity;
import cn.xiaochuankeji.tieba.ui.homepage.banner.b;
import cn.xiaochuankeji.tieba.ui.homepage.ugc.UgcCrumbManger.CrumbType;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.videomaker.VideoRecordActivity;
import cn.xiaochuankeji.tieba.ui.widget.TBViewPager;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;
import cn.xiaochuankeji.tieba.ui.widget.indicator.i;
import cn.xiaochuankeji.tieba.ui.widget.indicator.o;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.android.a.a.c;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.j;

public class UGCTabActivity extends cn.xiaochuankeji.tieba.ui.base.a implements OnPageChangeListener {
    private static final String[] a = new String[]{"关注", "推荐"};
    private b b;
    private o c;
    private a d;
    private EventIcon e = null;
    @BindView
    View header;
    @BindView
    MagicIndicator indicator;
    @BindView
    RelativeLayout rlRootView;
    @BindView
    TBViewPager viewPager;
    @BindView
    WebImageView wivIcon;

    class a extends g {
        final /* synthetic */ UGCTabActivity a;

        public a(UGCTabActivity uGCTabActivity, FragmentManager fragmentManager) {
            this.a = uGCTabActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            if (i == 0) {
                return h.c();
            }
            if (1 == i) {
                return i.c();
            }
            return null;
        }

        public int getCount() {
            return UGCTabActivity.a.length;
        }
    }

    public boolean h() {
        return false;
    }

    protected int a() {
        return R.layout.activity_tab_ugc;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        if (!c.a()) {
            this.header.setPadding(this.header.getPaddingLeft(), getResources().getDimensionPixelOffset(R.dimen.status_bar_height), this.header.getPaddingRight(), 0);
        }
        this.d = new a(this, getSupportFragmentManager());
        this.viewPager.setAdapter(this.d);
        this.viewPager.setOffscreenPageLimit(a.length);
        i bVar = new cn.xiaochuankeji.tieba.ui.widget.indicator.b(this);
        bVar.setAdjustMode(true);
        this.c = new o(a);
        bVar.setAdapter(this.c);
        this.indicator.setNavigator(bVar);
        this.viewPager.setCurrentItem(1);
        this.indicator.a(1);
        this.indicator.a(1);
        boolean a = UgcCrumbManger.a().a(CrumbType.ATTRI);
        UgcCrumbManger.a().a(CrumbType.RECOMM);
        if (a) {
            this.c.a("关注", 0);
        }
        this.wivIcon.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UGCTabActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e == null) {
                    return;
                }
                if (this.a.e.type.equals("h5")) {
                    WebActivity.a(this.a, new cn.xiaochuan.jsbridge.b("跟拍活动", this.a.e.url));
                } else if (this.a.e.type.equals("list")) {
                    UgcEventListActivity.a(this.a);
                }
            }
        });
        this.b = new b(this);
    }

    protected void onResume() {
        super.onResume();
        this.viewPager.addOnPageChangeListener(this);
        if (this.c != null) {
            this.c.a(this.viewPager);
        }
        h.a("zy_event_ugcvideo_tab", "页面进入");
    }

    protected void onPause() {
        super.onPause();
        this.viewPager.removeOnPageChangeListener(this);
        if (this.c != null) {
            this.c.c();
        }
    }

    protected void a(boolean z) {
        super.a(z);
        if (this.d != null) {
            this.d.notifyDataSetChanged();
            this.c.b();
        }
    }

    @OnClick
    public void search() {
        SearchAllActivity.a(this, "follow_act");
        h.a("zy_event_ugcvideo_tab", "搜索入口点击");
    }

    @OnClick
    public void post() {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "ugc_tab", 3)) {
            VideoRecordActivity.a((Activity) this, 2, 0, "ugcvideo-index");
            h.a("zy_event_ugcvideo_tab", "拍摄入口点击");
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (2 == i && -1 == i2) {
            UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) ((Moment) intent.getSerializableExtra("key_published_main_video")).ugcVideos.get(0);
            if (this.viewPager.getCurrentItem() != 0) {
                this.viewPager.setCurrentItem(0);
                onPageSelected(0);
            }
            h hVar = (h) this.d.a(0);
            if (hVar != null) {
                hVar.a(ugcVideoInfoBean);
            }
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.indicator != null) {
            this.indicator.a(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.indicator != null) {
            this.indicator.a(i);
            if (i == 0) {
                this.c.a("关注", -1);
            } else if (i == 1) {
                this.c.a("推荐", -1);
            }
            UgcCrumbManger.a().a(i == 0 ? CrumbType.ATTRI : CrumbType.RECOMM, false);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.indicator != null) {
            this.indicator.b(i);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void ugcCrumb(cn.xiaochuankeji.tieba.ui.homepage.ugc.UgcCrumbManger.a aVar) {
        if (this.viewPager.getCurrentItem() == 0 || !aVar.a) {
            UgcCrumbManger.a().a(CrumbType.ATTRI, false);
        } else {
            this.c.a("关注", 0);
            UgcCrumbManger.a().a(CrumbType.ATTRI, true);
        }
        if (this.viewPager.getCurrentItem() == 1 || !aVar.b) {
            UgcCrumbManger.a().a(CrumbType.RECOMM, false);
            return;
        }
        this.c.a("推荐", 0);
        UgcCrumbManger.a().a(CrumbType.RECOMM, true);
    }

    @l(a = ThreadMode.MAIN)
    public void ugcRefresh(a aVar) {
        ((c) this.d.a(this.viewPager.getCurrentItem())).b();
    }

    public void b(boolean z) {
        ((MainActivity) getParent()).a(z);
    }

    public void a(EventIcon eventIcon) {
        this.e = eventIcon;
        if (eventIcon == null) {
            this.wivIcon.setVisibility(8);
            return;
        }
        this.wivIcon.setVisibility(0);
        if (this.e.img != null) {
            this.wivIcon.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a((long) this.e.img.id));
            return;
        }
        this.wivIcon.setImageResource(R.drawable.img_ugc_event_default_icon);
    }

    public void a(long j, float f) {
        if (-1 == this.rlRootView.indexOfChild(this.b)) {
            int a;
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.navbar_height);
            int b = (int) (((float) (e.b(this) - e.a(20.0f))) / 2.55f);
            if (VERSION.SDK_INT >= 21) {
                a = e.a(10.0f);
            } else {
                a = e.a(5.0f);
            }
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, b + (dimensionPixelSize + a));
            if (!c.a()) {
                layoutParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.status_bar_height);
            }
            this.b.setPadding(e.a(10.0f), a + dimensionPixelSize, e.a(10.0f), 0);
            this.rlRootView.addView(this.b, layoutParams);
            View findViewById = findViewById(R.id.id_ugc_recommend_tip_view);
            if (findViewById != null) {
                findViewById.bringToFront();
            }
        }
        this.b.a(j, f);
    }

    public void a(long j) {
        if (-1 != this.rlRootView.indexOfChild(this.b)) {
            this.rlRootView.removeView(this.b);
        }
        if (0 != j) {
            new cn.xiaochuankeji.tieba.api.ugcvideo.a().j(j).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                final /* synthetic */ UGCTabActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(EmptyJson emptyJson) {
                }
            });
        }
    }
}
