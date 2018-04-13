package cn.xiaochuankeji.tieba.ui.homepage;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentBean;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean;
import cn.xiaochuankeji.tieba.background.ad.GDTAdvertisment;
import cn.xiaochuankeji.tieba.background.ad.PostAdExtraInfo;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.post.PostRecommendQueryList;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.utils.a.g;
import cn.xiaochuankeji.tieba.d.a.c;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.base.MainActivity;
import cn.xiaochuankeji.tieba.ui.base.l;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;
import cn.xiaochuankeji.tieba.ui.post.a.c.a;
import cn.xiaochuankeji.tieba.ui.post.postitem.AdsItemHolder;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import com.iflytek.cloud.SpeechConstant;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.util.ArrayList;
import org.greenrobot.eventbus.ThreadMode;

public class d extends l implements b, a {
    public static boolean a = false;
    public String b = "key_all_rec_visible_pos";
    public int c = 1;
    public int d = -1;
    public int e = -1;
    public int f = 1;
    public int g = 0;
    private PostRecommendQueryList h;
    private PostLoadedTipsView i;
    private PostQueryListView j;
    private RelativeLayout k;
    private Handler l = new Handler();
    private boolean m = false;
    private String n;
    private String o;
    private boolean p = true;
    private String q;
    private int r = 0;
    private cn.xiaochuankeji.tieba.d.a.d s;
    private cn.xiaochuankeji.tieba.d.a.b t;
    private Runnable u = new Runnable(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.i != null) {
                this.a.i.setVisibility(8);
            }
        }
    };

    public static d a(NavigatorTag navigatorTag) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("fragment_type", navigatorTag);
        d dVar = new d();
        dVar.setArguments(bundle);
        return dVar;
    }

    private void a(String str, String str2) {
        this.q = str2;
        if (str.equals("video")) {
            this.h = r.b();
            this.b = "key_video_rec_visible_pos";
        } else if (str.equals(SpeechConstant.PLUS_LOCAL_ALL)) {
            this.h = r.a();
            this.b = "key_all_rec_visible_pos";
        } else if (str.equals("imgtxt")) {
            this.h = r.c();
            this.b = "key_image_rec_visible_pos";
        } else {
            this.h = r.a(str2, str);
            this.b = str;
        }
        if (this.h != null) {
            this.h.registerOnQueryFinishListener(this);
        }
    }

    public void e() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            NavigatorTag navigatorTag = (NavigatorTag) arguments.getParcelable("fragment_type");
            if (navigatorTag.action_info.filter == null) {
                navigatorTag.action_info.filter = SpeechConstant.PLUS_LOCAL_ALL;
            }
            this.n = navigatorTag.action_info.filter;
            this.o = navigatorTag.ename;
            a(this.n, this.o);
        }
        this.j.a("此刻啥都没有", c.a.d.a.a.a().d(R.drawable.ic_topic_empty_post), EmptyPaddingStyle.GoldenSection);
        this.t = new c(this.j.m());
        this.j.m().setRefreshOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == 0 && this.a.getUserVisibleHint() && !this.a.m) {
                    if (this.a.s != null) {
                        this.a.s.a(this.a.t);
                    }
                    this.a.j();
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                boolean z;
                this.a.a(i, i2);
                if (i3 <= 7 || absListView.getLastVisiblePosition() + 4 <= i3 || cn.xiaochuankeji.tieba.d.a.d() != cn.xiaochuankeji.tieba.d.a.g) {
                    z = false;
                } else {
                    this.a.j.o();
                    z = true;
                }
                if (i > 4) {
                    f fVar = new f(true);
                    fVar.b = z;
                    org.greenrobot.eventbus.c.a().d(fVar);
                } else {
                    org.greenrobot.eventbus.c.a().d(new f(false));
                }
                if (i3 > 0 && absListView.getChildAt(0) != null) {
                    if (i2 > 2) {
                        i++;
                    } else if (absListView.getChildAt(0).getY() + ((float) absListView.getChildAt(0).getMeasuredHeight()) < ((float) (e.c() / 2))) {
                        i++;
                    }
                    cn.xiaochuankeji.tieba.background.a.p().b().execute(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            cn.xiaochuankeji.tieba.background.a.a().edit().putInt(this.b.a.b, i).apply();
                        }
                    });
                    this.a.f = i;
                }
            }
        });
        this.r = cn.xiaochuankeji.tieba.background.a.a().getInt(this.b, 0);
        this.j.a(this.h);
        this.h.a(new PostRecommendQueryList.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.c = 2;
                if (this.a.a()) {
                    this.a.m();
                }
                if (this.a.k != null) {
                    this.a.k.setVisibility(8);
                }
            }

            public void b() {
                if (this.a.k != null) {
                    this.a.k.setVisibility(8);
                }
                this.a.f = this.a.r;
                if (cn.xiaochuankeji.tieba.d.a.b() == cn.xiaochuankeji.tieba.d.a.b && this.a.r > 1 && !cn.xiaochuankeji.tieba.background.post.b.a().a(this.a.n) && this.a.r < this.a.h.a) {
                    this.a.j.m().setSelection(this.a.r);
                    if (!cn.xiaochuankeji.tieba.background.post.b.a().f()) {
                        this.a.l.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a("上次您看到这里", 0);
                            }
                        }, 500);
                        cn.xiaochuankeji.tieba.background.post.b.a().a(true);
                    }
                }
                this.a.k();
            }
        });
        o();
        n();
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_recommend_post, null);
        this.k = (RelativeLayout) inflate.findViewById(R.id.fragment_loading_tip);
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.rootView);
        this.j = new PostQueryListView(this, getActivity()) {
            final /* synthetic */ d d;

            public void k() {
                super.k();
                MainActivity.a("tab_home_page");
            }

            public void o() {
                super.o();
                MainActivity.a("tab_home_page");
            }
        };
        frameLayout.addView(this.j);
        a(frameLayout);
        return frameLayout;
    }

    public void i() {
        for (int i = this.d > 0 ? this.d - 1 : 0; i < this.j.getLastVisiblePosition(); i++) {
            if (i < this.h.getItems().size()) {
                AbstractPost abstractPost = (AbstractPost) this.h.getItems().get(i);
                if (abstractPost instanceof Post) {
                    Post post = (Post) abstractPost;
                    g.a().a(post._ID, System.currentTimeMillis(), i, this.q, post._postContent);
                }
            }
        }
    }

    private void a(int i, int i2) {
        int i3 = 1;
        boolean z = false;
        try {
            if (getUserVisibleHint() && this.h != null) {
                boolean z2;
                int i4;
                int lastVisiblePosition = this.j.m().getLastVisiblePosition();
                if (this.d != i) {
                    if (this.d > i) {
                        z = true;
                    }
                    this.d = i;
                    z2 = z;
                    i4 = i;
                } else if (this.e != lastVisiblePosition) {
                    if (this.e < lastVisiblePosition) {
                        z = true;
                    }
                    this.e = lastVisiblePosition;
                    if (this.p) {
                        this.p = false;
                        return;
                    }
                    boolean z3 = z;
                    i4 = lastVisiblePosition;
                    z2 = z3;
                } else {
                    z2 = false;
                    i4 = -1;
                }
                if (i4 <= 0) {
                    return;
                }
                if (this.p) {
                    if (i4 != 0) {
                        i3 = i4;
                    }
                    while (i3 < i4 + i2) {
                        a(i3, i2, true);
                        i3++;
                    }
                    return;
                }
                a(i4, i2, z2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(int i, int i2, boolean z) {
        ArrayList items = this.h.getItems();
        if (i < items.size()) {
            int i3 = i - 1;
            if (i3 >= 0) {
                AbstractPost abstractPost = (AbstractPost) items.get(i3);
                if ((abstractPost instanceof Post) && z) {
                    Post post = (Post) abstractPost;
                    g.a().a(post._ID, System.currentTimeMillis(), i, this.q, post._postContent);
                }
                g.a().a(i, i2);
            }
        }
    }

    public void j() {
        if (this.h != null && this.f != this.g && this.f > 0 && this.h.itemCount() > this.f - 1) {
            AbstractPost abstractPost = (AbstractPost) this.h.getItems().get(this.f - 1);
            g.a().c();
            if (abstractPost != null && (abstractPost instanceof Post)) {
                Post post = (Post) abstractPost;
                g.a().a(post._ID, System.currentTimeMillis(), this.q, post._postContent);
            }
            this.g = this.f;
        }
    }

    private void k() {
        l();
        if (this.j == null || !this.j.i()) {
            boolean a = cn.xiaochuankeji.tieba.background.post.b.a().a(this.n);
            if (this.h != null && a && getUserVisibleHint()) {
                this.h.a();
                m();
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        this.m = false;
        if (getUserVisibleHint()) {
            if (g.a().a) {
                onHiddenChanged(true);
            }
            g.a().a = true;
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.h != null) {
            onHiddenChanged(z);
        }
    }

    public void onHiddenChanged(boolean z) {
        if (z) {
            i();
            this.g = -1;
            j();
            cn.xiaochuankeji.tieba.ui.post.a.c.a().a((a) this);
            this.j.m().post(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.s != null) {
                        this.a.s.a(this.a.t);
                    }
                }
            });
            return;
        }
        g.a().b();
        g.a().c();
        g.a().e();
        if (this.s != null) {
            this.s.a();
        }
    }

    private void l() {
        if (this.j != null && this.j.m() != null) {
            if (this.j.m().getFirstVisiblePosition() > 4) {
                org.greenrobot.eventbus.c.a().d(new f(true));
                return;
            }
            org.greenrobot.eventbus.c.a().d(new f(false));
        }
    }

    public void onPause() {
        super.onPause();
        this.m = true;
        if (getUserVisibleHint() && g.a().a) {
            onHiddenChanged(false);
        }
    }

    public void a(Post post) {
        if (this.h != null) {
            this.h.a(post);
            this.j.m().setSelection(0);
        }
    }

    public void a(Moment moment) {
        if (this.h != null) {
            this.h.a(moment);
            this.j.m().setSelection(0);
        }
    }

    private void a(String str, int i) {
        CharSequence replace;
        if (!TextUtils.isEmpty(str)) {
            replace = str.replace("${count}", String.valueOf(i));
        } else if (i > 0) {
            replace = "为你选出" + i + "条好内容";
        } else {
            replace = "暂无推荐，到话题里看看";
        }
        if (this.i != null) {
            this.i.setText(replace);
            this.i.setVisibility(0);
            this.l.removeCallbacks(this.u);
            this.l.postDelayed(this.u, 1500);
        }
    }

    private void m() {
        a(null);
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void navRefresh(cn.xiaochuankeji.tieba.b.a aVar) {
        String str = aVar.a;
        NavigatorTag navigatorTag = aVar.b;
        if (!TextUtils.isEmpty(str) && navigatorTag != null && this.j != null) {
            if (navigatorTag.id != ((NavigatorTag) getArguments().getParcelable("fragment_type")).id) {
                return;
            }
            if (this.j.i()) {
                this.j.m().setSelection(0);
                return;
            }
            this.j.m().setSelection(0);
            this.h.a(str);
            this.j.h();
        }
    }

    public void a(String str) {
        if (this.j == null) {
            return;
        }
        if (this.j.i()) {
            this.j.m().setSelection(0);
            return;
        }
        this.j.m().setSelection(0);
        this.h.a(str);
        this.j.h();
    }

    private void n() {
        if (SpeechConstant.PLUS_LOCAL_ALL.equalsIgnoreCase(this.n) && isVisible() && this.j != null) {
            this.j.m().post(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    View childAt = this.a.j.m().getChildAt(1);
                    if (childAt != null) {
                        if (cn.xiaochuankeji.tieba.background.a.a().getBoolean("kLikeOrDislikeGuide", false) || this.a.c > 0) {
                            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.b());
                        } else {
                            View findViewById = childAt.findViewById(R.id.postItemUpDownView);
                            if (findViewById != null) {
                                View findViewById2 = findViewById.findViewById(R.id.ivUpArrow);
                                findViewById = findViewById.findViewById(R.id.ivDownArrow);
                                if (!(findViewById2 == null || findViewById == null)) {
                                    this.a.a(childAt, findViewById2, findViewById);
                                    cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("kLikeOrDislikeGuide", true).apply();
                                }
                            }
                        }
                        d dVar = this.a;
                        dVar.c--;
                    }
                }
            });
        }
    }

    private void a(View view) {
        if (isAdded() && !getActivity().isFinishing()) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            SDGuideDialog sDGuideDialog = new SDGuideDialog(getActivity());
            sDGuideDialog.a(null, R.drawable.img_block_topic_guide, 53, 0, rect.top, false);
            sDGuideDialog.setOnDismissListener(new SDGuideDialog.c(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void a(SDGuideDialog sDGuideDialog) {
                    this.a.n();
                }
            });
            sDGuideDialog.b();
        }
    }

    private void a(View view, View view2, View view3) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        int dimension = (int) getResources().getDimension(R.dimen.bottom_navbar_height);
        final int max = Math.max(((rect.top + view.getHeight()) - e.c()) + dimension, 0);
        if (!isAdded() || !getActivity().isFinishing()) {
            int c;
            SDGuideDialog sDGuideDialog = new SDGuideDialog(getActivity());
            sDGuideDialog.setBackgroundColor(getResources().getColor(R.color.transparent));
            view2.getGlobalVisibleRect(new Rect());
            if (max == 0) {
                c = e.c() - (rect.bottom - e.a(8.0f));
            } else {
                c = dimension + e.a(8.0f);
            }
            sDGuideDialog.a(null, R.drawable.img_like_guide, 85, PostItemUpDownView.a() - e.a(63.0f), c);
            sDGuideDialog.a(null, R.drawable.img_dislike_guide, 85, 0, c - e.a(55.0f), false);
            sDGuideDialog.setOnShownListener(new cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog.d(this) {
                final /* synthetic */ d b;

                public void a(SDGuideDialog sDGuideDialog) {
                    if (max > 0) {
                        this.b.j.m().scrollTo(0, max);
                    }
                }
            });
            sDGuideDialog.setOnDismissListener(new SDGuideDialog.c(this) {
                final /* synthetic */ d b;

                public void a(SDGuideDialog sDGuideDialog) {
                    if (max > 0) {
                        this.b.j.m().scrollTo(0, 0);
                    }
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.b());
                }
            });
            sDGuideDialog.b();
        }
    }

    public void a(boolean z, boolean z2, String str) {
        if (z && z2) {
            if (this.h == null) {
                this.h = r.a();
            }
            a(this.h.c(), this.h.d());
            n();
            g.a().b();
            this.f = 1;
            this.g = 0;
            j();
            i();
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onEventMainThread(UgcVideoActivity.b bVar) {
        if (isVisible() && bVar.c != null) {
            for (int i = 0; i < this.h.itemCount(); i++) {
                AbstractPost abstractPost = (AbstractPost) this.h.itemAt(i);
                if (abstractPost.classType() == 3 && ((Moment) abstractPost).id == bVar.c.id) {
                    if (bVar.c.ugcVideos.size() == 0 || ((UgcVideoInfoBean) bVar.c.ugcVideos.get(0)).id != bVar.c.id) {
                        this.h.remove(i);
                    } else {
                        this.h.replace(i, bVar.c);
                    }
                    this.j.a();
                    this.h.b();
                    return;
                }
            }
        }
    }

    private void a(FrameLayout frameLayout) {
        this.i = new PostLoadedTipsView(getActivity());
        this.i.setVisibility(8);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = e.a(-10.0f);
        layoutParams.gravity = 1;
        this.i.setLayoutParams(layoutParams);
        frameLayout.addView(this.i);
    }

    public void onDestroy() {
        if (this.h != null) {
            this.h.b();
            this.h.unregisterOnQueryFinishedListener(this);
        }
        if (this.j != null) {
            this.j.c();
        }
        super.onDestroy();
    }

    public void a(NativeMediaADData nativeMediaADData, PostAdExtraInfo postAdExtraInfo, int i) {
        if (this.h != null && getUserVisibleHint()) {
            AbstractPost gDTAdvertisment = new GDTAdvertisment();
            gDTAdvertisment.mAD = nativeMediaADData;
            gDTAdvertisment.extraInfo = postAdExtraInfo;
            this.h.a(gDTAdvertisment, i);
        }
    }

    public void a(NativeMediaADData nativeMediaADData) {
        AdsItemHolder b = b(nativeMediaADData);
        if (b != null) {
            b.a(cn.xiaochuankeji.tieba.ui.post.a.c.a().a(nativeMediaADData));
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onDeleteAd(cn.xiaochuankeji.tieba.ui.post.a.b bVar) {
        for (int i = 0; i < this.h.itemCount(); i++) {
            if (this.h.itemAt(i) instanceof GDTAdvertisment) {
                GDTAdvertisment gDTAdvertisment = (GDTAdvertisment) this.h.itemAt(i);
                if (bVar.a != null && bVar.a.equalsAdData(gDTAdvertisment.mAD)) {
                    this.h.remove(i);
                    this.h.notifyListUpdate();
                }
            } else if (this.h.itemAt(i) instanceof AdvertismentBean) {
                AdvertismentBean advertismentBean = (AdvertismentBean) this.h.itemAt(i);
                if (bVar.b != null && bVar.b.id == advertismentBean.id) {
                    this.h.remove(i);
                    this.h.notifyListUpdate();
                }
            } else if (this.h.itemAt(i) instanceof AdvertismentSoftBean) {
                AdvertismentSoftBean advertismentSoftBean = (AdvertismentSoftBean) this.h.itemAt(i);
                if (bVar.c != null && bVar.c.adid == advertismentSoftBean.advert.adid) {
                    this.h.remove(i);
                    this.h.notifyListUpdate();
                }
            }
        }
    }

    private AdsItemHolder b(NativeMediaADData nativeMediaADData) {
        if (this.j == null || !isAdded()) {
            return null;
        }
        int firstVisiblePosition = this.j.m().getFirstVisiblePosition();
        int lastVisiblePosition = this.j.m().getLastVisiblePosition();
        for (int i = firstVisiblePosition; i <= lastVisiblePosition; i++) {
            View childAt = this.j.m().getChildAt(i - firstVisiblePosition);
            if (childAt.getTag() instanceof AdsItemHolder) {
                AdsItemHolder adsItemHolder = (AdsItemHolder) childAt.getTag();
                if (adsItemHolder != null && nativeMediaADData.equalsAdData(adsItemHolder.b())) {
                    return adsItemHolder;
                }
            }
        }
        return null;
    }

    private void o() {
        this.s = new cn.xiaochuankeji.tieba.d.a.d(this, new cn.xiaochuankeji.tieba.d.a.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i, View view) {
                AbstractPost abstractPost = (AbstractPost) this.a.h.itemAt(i);
                if (abstractPost instanceof GDTAdvertisment) {
                    GDTAdvertisment gDTAdvertisment = (GDTAdvertisment) abstractPost;
                    if (gDTAdvertisment.mAD != null && gDTAdvertisment.mAD.getAdPatternType() == 2) {
                        gDTAdvertisment.mAD.stop();
                    }
                }
            }

            public void b(int i, View view) {
                AbstractPost abstractPost = (AbstractPost) this.a.h.itemAt(i);
                if (abstractPost instanceof GDTAdvertisment) {
                    GDTAdvertisment gDTAdvertisment = (GDTAdvertisment) abstractPost;
                    if (gDTAdvertisment.mAD != null && gDTAdvertisment.mAD.getAdPatternType() == 2) {
                        gDTAdvertisment.mAD.play();
                    }
                }
            }
        }) {
            final /* synthetic */ d a;

            public View a(int i) {
                if (((AbstractPost) this.a.h.itemAt(i)) == null) {
                    return null;
                }
                int firstVisiblePosition = i - this.a.j.m().getFirstVisiblePosition();
                if (firstVisiblePosition < 0 || firstVisiblePosition >= this.a.j.m().getChildCount()) {
                    return null;
                }
                com.izuiyou.a.a.b.a("AutoPlay", "visible item index:" + firstVisiblePosition + ", adapterPosition:" + i + ", itemViewIndex:" + firstVisiblePosition);
                return this.a.j.getChildAt(firstVisiblePosition);
            }
        };
    }
}
