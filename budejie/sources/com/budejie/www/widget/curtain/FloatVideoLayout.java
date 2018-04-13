package com.budejie.www.widget.curtain;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.CommendDetail;
import com.budejie.www.activity.detail.PostDetailActivity;
import com.budejie.www.activity.detail.c;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.VideoTextureView;
import com.budejie.www.activity.video.barrage.a.f;
import com.budejie.www.activity.video.barrage.ui.widget.DanmakuView;
import com.budejie.www.activity.video.k;
import com.budejie.www.adapter.e.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.busevent.ShareEvent;
import com.budejie.www.busevent.ShareEvent.ShareAction;
import com.budejie.www.util.an;
import com.budejie.www.util.ao;
import com.budejie.www.widget.FavorLayout;
import com.budejie.www.widget.curtain.BarrageStatusManager.BarrageState;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;

public class FloatVideoLayout extends RelativeLayout implements OnClickListener, d {
    private Context A;
    private c B;
    private e C;
    private View D;
    private TextView E;
    private LinearLayout F;
    private TextView G;
    private TextView H;
    private TextView I;
    private VideoADView J;
    private RelativeLayout K;
    private RelativeLayout L;
    private LinearLayout M;
    private AsyncImageView N;
    private TextView O;
    private TextView P;
    private TextView Q;
    private TextView R;
    private ImageView S;
    private ImageView T;
    private ImageView U;
    private ImageView V;
    private View W;
    public CurtainBGLayout a;
    private FloatVideoLayout$a aa = new FloatVideoLayout$a();
    private FloatVideoLayout$a ab = new FloatVideoLayout$a();
    private a ac;
    private int ad;
    private ListItemObject ae;
    private OnClickListener af = new FloatVideoLayout$3(this);
    public CurtainVideoContainerLayout b;
    SharedPreferences c;
    int d;
    int e;
    int f;
    boolean g;
    boolean h;
    f i;
    FavorLayout j;
    b k;
    RelativeLayout l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public int q;
    public boolean r;
    private boolean s = true;
    private boolean t = true;
    private FloatVideoRootLayout u;
    private View v;
    private int w = 0;
    private int x;
    private boolean y;
    private boolean z;

    public static void a(Context context, boolean z) {
        FloatVideoLayout b = k.a(context).b(context);
        if (b != null) {
            b.setIndicatorShowState(z);
            return;
        }
        b = a(context);
        if (b != null) {
            b.s = z;
        }
    }

    public static void b(Context context, boolean z) {
        FloatVideoLayout b = k.a(context).b(context);
        if (b != null) {
            b.setBottomNavigationShowState(z);
            return;
        }
        b = a(context);
        if (b != null) {
            b.t = z;
        }
    }

    public static FloatVideoLayout a(Context context) {
        FloatVideoLayout floatVideoLayout = null;
        if (!(context instanceof Activity)) {
            return null;
        }
        Activity activity = (Activity) context;
        if (activity.getParent() != null) {
            floatVideoLayout = (FloatVideoLayout) activity.getParent().findViewById(R.id.curtain_layout);
        }
        if (floatVideoLayout == null) {
            return (FloatVideoLayout) activity.findViewById(R.id.curtain_layout);
        }
        return floatVideoLayout;
    }

    public boolean c() {
        return this.y;
    }

    public boolean d() {
        return this.z;
    }

    public MutilKeyboardListenerRelativeLayout getParentView() {
        return this.u;
    }

    private void setIndicatorShowState(boolean z) {
        if (z != this.s) {
            this.s = z;
            s();
        }
    }

    private void setBottomNavigationShowState(boolean z) {
        if (z != this.t) {
            this.t = z;
            s();
        }
    }

    private int getMinTopHeight() {
        if (this.n) {
            return 0;
        }
        if (this.m) {
            if ((this.A instanceof CommendDetail) || (this.A instanceof PostDetailActivity)) {
                return 0;
            }
            return an.t(this.A);
        } else if (this.g && this.s) {
            return this.e + this.x;
        } else {
            if (this.s) {
                return this.e;
            }
            return this.d + this.x;
        }
    }

    private int getMinBottomHeight() {
        if (this.n) {
            return 0;
        }
        if (this.m) {
            if ((this.A instanceof CommendDetail) || (this.A instanceof PostDetailActivity)) {
                return 0;
            }
            return -an.t(this.A);
        } else if (!this.h || this.t) {
            return this.f;
        } else {
            return 0;
        }
    }

    public FloatVideoLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A = context;
        r();
    }

    public FloatVideoLayout(Context context) {
        super(context);
        this.A = context;
        r();
    }

    private void r() {
        if (VERSION.SDK_INT < 21 || (this.A instanceof CommendDetail) || (this.A instanceof PostDetailActivity)) {
            this.w = an.t(getContext());
        } else {
            this.w = 0;
        }
        this.x = getContext().getResources().getDimensionPixelSize(R.dimen.toptabpageindicator_height);
        this.c = getContext().getSharedPreferences("weiboprefer", 0);
    }

    public void a(FloatVideoRootLayout floatVideoRootLayout) {
        this.u = floatVideoRootLayout;
        this.a = (CurtainBGLayout) findViewById(R.id.curtain_bg);
        this.a.setOnInterceptTouchListener(this);
        this.b = (CurtainVideoContainerLayout) findViewById(R.id.curtain_video_container);
        this.b.setOnInterceptTouchListener(this);
        this.v = findViewById(R.id.curtain_video_move_limit);
        LayoutParams layoutParams = (LayoutParams) this.v.getLayoutParams();
        layoutParams.topMargin = getMinTopHeight();
        layoutParams.bottomMargin = getMinBottomHeight();
        this.v.setLayoutParams(layoutParams);
        this.l = (RelativeLayout) View.inflate(this.A, R.layout.post_video_complete_include, null);
        this.D = this.l.findViewById(R.id.reply_shared_layout);
        this.F = (LinearLayout) this.l.findViewById(R.id.video_play_end_share_layout);
        this.E = (TextView) this.l.findViewById(R.id.video_play_end_share_title);
        this.G = (TextView) this.l.findViewById(R.id.video_replay);
        this.H = (TextView) this.l.findViewById(R.id.video_friend);
        this.I = (TextView) this.l.findViewById(R.id.video_moments);
        this.J = (VideoADView) this.l.findViewById(R.id.video_ad_view);
        this.l.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.G.setOnClickListener(this);
        this.K = (RelativeLayout) View.inflate(this.A, R.layout.recommend_video_pause_include, null);
        this.L = (RelativeLayout) this.K.findViewById(R.id.recommend_post_item_pause);
        this.M = (LinearLayout) this.K.findViewById(R.id.ll_user_view);
        this.N = (AsyncImageView) this.K.findViewById(R.id.recommend_post_user_header);
        this.O = (TextView) this.K.findViewById(R.id.recommend_post_user_name);
        this.P = (TextView) this.K.findViewById(R.id.recommend_post_play_count);
        this.Q = (TextView) this.K.findViewById(R.id.recommend_post_content);
        this.R = (TextView) this.K.findViewById(R.id.recommend_post_comment);
        this.S = (ImageView) this.K.findViewById(R.id.recommend_post_item_pause_forword);
        this.T = (ImageView) this.K.findViewById(R.id.recommend_post_item_pause_like);
        this.U = (ImageView) this.K.findViewById(R.id.recommend_post_item_pause_shit);
        this.V = (ImageView) this.K.findViewById(R.id.recommend_post_item_pause_comment);
        this.L.setOnClickListener(this.af);
        this.R.setOnClickListener(this.af);
        this.S.setOnClickListener(this.af);
        this.T.setOnClickListener(this.af);
        this.U.setOnClickListener(this.af);
        this.V.setOnClickListener(this.af);
        this.M.setOnClickListener(this.af);
        this.i = (f) findViewById(R.id.barrage_full_screen_container);
        this.j = (FavorLayout) findViewById(R.id.favor_layout);
        setDanmakuViewMargin(false);
        this.k = new b(this.A, this.i, this.j);
        this.B = new c(this.k.a);
        this.B.a((Activity) getContext(), floatVideoRootLayout, this);
        this.C = new e(this.A, this);
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this.A, "wx592fdc48acfbe290", true);
        createWXAPI.registerApp("wx592fdc48acfbe290");
        boolean isWXAppInstalled = createWXAPI.isWXAppInstalled();
        boolean z = createWXAPI.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT;
        if (isWXAppInstalled && z) {
            this.F.setVisibility(0);
            this.E.setVisibility(0);
            return;
        }
        this.E.setVisibility(8);
        this.H.setVisibility(8);
        this.I.setVisibility(8);
    }

    public void setDanmakuViewMargin(boolean z) {
        LayoutParams layoutParams;
        LayoutParams layoutParams2;
        if ((this.A instanceof CommendDetail) && (this.i instanceof DanmakuView)) {
            layoutParams = (LayoutParams) ((DanmakuView) this.i).getLayoutParams();
            layoutParams2 = (LayoutParams) this.j.getLayoutParams();
            if (((CommendDetail) this.A).m || z) {
                layoutParams.topMargin = this.A.getResources().getDimensionPixelOffset(R.dimen.navigation_height);
                layoutParams2.topMargin = this.A.getResources().getDimensionPixelOffset(R.dimen.navigation_height);
            } else {
                layoutParams.topMargin = 0;
                layoutParams2.topMargin = 0;
            }
            ((DanmakuView) this.i).setLayoutParams(layoutParams);
            this.j.setLayoutParams(layoutParams2);
        }
        if ((this.A instanceof PostDetailActivity) && (this.i instanceof DanmakuView)) {
            layoutParams = (LayoutParams) ((DanmakuView) this.i).getLayoutParams();
            layoutParams2 = (LayoutParams) this.j.getLayoutParams();
            c cVar = (c) ((PostDetailActivity) this.A).a();
            if ((cVar == null || !cVar.c) && !z) {
                layoutParams.topMargin = 0;
                layoutParams2.topMargin = 0;
            } else {
                layoutParams.topMargin = this.A.getResources().getDimensionPixelOffset(R.dimen.navigation_height);
                layoutParams2.topMargin = this.A.getResources().getDimensionPixelOffset(R.dimen.navigation_height);
            }
            ((DanmakuView) this.i).setLayoutParams(layoutParams);
            this.j.setLayoutParams(layoutParams2);
        }
    }

    public void e() {
        if (this.b != null && this.q == 1) {
            this.b.setBackgroundColor(getResources().getColor(R.color.barrage_full_screen_bg));
        }
    }

    public void f() {
        this.a.a();
        this.b.a();
    }

    public void a(boolean z) {
        if (!z) {
            f();
            setVisibility(8);
        }
        this.a.setVisibility(8);
        this.B.c();
        this.y = false;
        this.m = false;
        this.n = false;
        this.o = false;
        t();
        this.b.removeAllViews();
        this.b.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.k.d();
        if (this.u.getKeyBoardState()) {
            ao.a((Activity) this.A);
            LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            this.b.setLayoutParams(layoutParams);
        }
    }

    public FloatVideoLayout$TouchPosition getTouchPostion() {
        if (this.b.getTouched()) {
            return FloatVideoLayout$TouchPosition.VIDEO;
        }
        return this.a.getTouched() ? FloatVideoLayout$TouchPosition.BG : null;
    }

    public boolean getCurtainInterceptTouchState() {
        return !this.a.getScrollSloped();
    }

    public void a(ListItemObject listItemObject, int i) {
        this.ae = listItemObject;
        this.p = i > 0;
        this.q = i;
        if (i == 4) {
            a(listItemObject);
        }
        g();
        if (i == 0) {
            this.k.a(listItemObject.getWid());
        }
    }

    public void a(ListItemObject listItemObject) {
        this.ae = listItemObject;
        if (this.ae != null) {
            this.N.setPostAvatarImage(this.ae.getProfile());
            this.O.setText(this.ae.getName());
            String playcount = this.ae.getPlaycount();
            if (!TextUtils.isEmpty(playcount) && playcount.length() >= 5) {
                playcount = playcount.substring(0, playcount.length() - 4) + "万";
            }
            this.P.setText(playcount + "次播放");
            this.Q.setText(listItemObject.getContent());
            this.R.setText("所有" + this.ae.getComment() + "条评论");
            this.T.setSelected("ding".equals(this.ae.getFlag()));
            this.U.setSelected("cai".equals(this.ae.getCai_flag()));
        }
    }

    public void g() {
        h();
        setVisibility(0);
        BarrageState a = BarrageStatusManager.a(this.c);
        if (!this.p) {
            if (!this.a.isShown()) {
                this.a.setVisibility(0);
            }
            this.k.a(a, this.r);
        }
    }

    private void s() {
        LayoutParams layoutParams = (LayoutParams) this.v.getLayoutParams();
        layoutParams.topMargin = getMinTopHeight();
        layoutParams.bottomMargin = getMinBottomHeight();
        this.v.setLayoutParams(layoutParams);
        b(true);
    }

    public void h() {
        if (VERSION.SDK_INT < 18) {
            post(new FloatVideoLayout$1(this));
        } else {
            b(false);
        }
    }

    private void b(boolean z) {
        if (z || !(this.n || this.m)) {
            View f = k.a(getContext()).f();
            if (f != null) {
                int[] iArr = new int[2];
                f.getLocationOnScreen(iArr);
                if (f.isShown() || iArr[1] != 0) {
                    iArr[1] = iArr[1] - this.w;
                    LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
                    int minTopHeight = iArr[1] - getMinTopHeight();
                    if (VERSION.SDK_INT >= 18 || Math.abs(layoutParams.topMargin - minTopHeight) >= 5 || Math.abs(layoutParams.leftMargin - iArr[0]) >= 5) {
                        layoutParams.topMargin = minTopHeight;
                        layoutParams.leftMargin = iArr[0];
                        layoutParams.rightMargin = (com.budejie.www.adapter.b.a.a - layoutParams.leftMargin) - f.getWidth();
                        layoutParams.bottomMargin = ((this.u.getHeight() - iArr[1]) - f.getHeight()) - getMinBottomHeight();
                        this.b.setLayoutParams(layoutParams);
                    }
                }
            }
        }
    }

    public void i() {
        if (!this.n && !this.m) {
            int height = this.ae.getHeight();
            int width = this.ae.getWidth();
            if (height > 0 && width > 0 && k.a(getContext()).f() != null) {
                this.r = true;
                this.k.b();
                LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
                int i = com.budejie.www.adapter.b.a.a / 2;
                if (height > width) {
                    layoutParams.height = i;
                    layoutParams.width = (width * i) / height;
                } else {
                    layoutParams.width = i;
                    layoutParams.height = (height * i) / width;
                }
                layoutParams.leftMargin = (com.budejie.www.adapter.b.a.a + 1) - layoutParams.width;
                layoutParams.topMargin = ((((BudejieApplication.i - this.w) + 4) - getResources().getDimensionPixelSize(R.dimen.navigation_height)) - this.e) - layoutParams.height;
                layoutParams.rightMargin = com.budejie.www.adapter.b.a.a + 1;
                layoutParams.bottomMargin = (((BudejieApplication.i - this.w) + 4) - getResources().getDimensionPixelSize(R.dimen.navigation_height)) - this.e;
                this.b.setLayoutParams(layoutParams);
            }
        }
    }

    public void j() {
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        layoutParams.height = 0;
        layoutParams.width = 0;
        this.b.setLayoutParams(layoutParams);
    }

    public void k() {
        if (!this.n && !this.m && k.a(getContext()).f() != null) {
            VideoTextureView e = k.a(this.A).e();
            if (e != null) {
                LayoutParams layoutParams;
                if (e.getWidth() < com.budejie.www.adapter.b.a.a) {
                    layoutParams = (LayoutParams) e.getLayoutParams();
                    layoutParams.width = -1;
                    layoutParams.height = -1;
                    e.setLayoutParams(layoutParams);
                }
                this.r = false;
                this.k.c();
                layoutParams = (LayoutParams) this.b.getLayoutParams();
                int height = this.ae.getHeight();
                int width = this.ae.getWidth();
                layoutParams.width = com.budejie.www.adapter.b.a.a;
                if (width > 0) {
                    layoutParams.height = (height * com.budejie.www.adapter.b.a.a) / width;
                    if (layoutParams.height > com.budejie.www.adapter.b.a.b - com.budejie.www.adapter.b.a.f) {
                        layoutParams.height = com.budejie.www.adapter.b.a.b - com.budejie.www.adapter.b.a.f;
                    }
                    this.b.setLayoutParams(layoutParams);
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void l() {
        Activity activity = (Activity) this.A;
        c cVar;
        if (activity.getRequestedOrientation() != 0) {
            this.b.setBackgroundColor(getResources().getColor(R.color.barrage_full_screen_bg));
            if (this.q == 0 && !this.C.a()) {
                PlateBean plateBean = this.ae.getPlateBean(0);
                if (!(plateBean == null || TextUtils.isEmpty(plateBean.theme_id))) {
                    this.C.a(plateBean.theme_id);
                }
            }
            if (activity instanceof CommendDetail) {
                ((CommendDetail) activity).a(true);
            }
            if (activity instanceof PostDetailActivity) {
                cVar = (c) ((PostDetailActivity) activity).a();
                if (cVar != null) {
                    cVar.b(true);
                }
            }
            setDanmakuViewMargin(true);
            k.a(this.A).i();
            this.n = true;
            w();
            this.k.a();
            k.a(null).b.setFullScreen(true);
            k.a(null).b.j();
            LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
            this.ab.a = layoutParams.leftMargin;
            this.ab.b = layoutParams.topMargin;
            this.ab.c = layoutParams.bottomMargin;
            activity.getWindow().addFlags(1024);
            activity.setRequestedOrientation(0);
            s();
            layoutParams.bottomMargin = 0;
            layoutParams.topMargin = 0;
            layoutParams.rightMargin = 0;
            layoutParams.leftMargin = 0;
            layoutParams.width = -1;
            layoutParams.height = -1;
            this.b.setLayoutParams(layoutParams);
            VideoTextureView e = k.a(this.A).e();
            LayoutParams layoutParams2 = (LayoutParams) e.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            e.setLayoutParams(layoutParams2);
            return;
        }
        if (activity instanceof CommendDetail) {
            ((CommendDetail) activity).a(false);
        }
        if (activity instanceof PostDetailActivity) {
            cVar = (c) ((PostDetailActivity) activity).a();
            if (cVar != null) {
                cVar.b(true);
            }
        }
        setDanmakuViewMargin(false);
        activity.getWindow().clearFlags(1024);
        k.a(null).b.setFullScreen(false);
        k.a(null).b.j();
        activity.setRequestedOrientation(1);
        this.n = false;
        s();
        layoutParams2 = (LayoutParams) this.b.getLayoutParams();
        int i = this.ab.a;
        layoutParams2.rightMargin = i;
        layoutParams2.leftMargin = i;
        layoutParams2.topMargin = this.ab.b;
        layoutParams2.bottomMargin = this.ab.c;
        this.b.setLayoutParams(layoutParams2);
        w();
        this.k.a();
        if (!k.a(null).b.t()) {
            k.a(null).b.a(false, true);
        }
    }

    public void m() {
        if (this.n) {
            l();
        }
        k.a(this.A).p();
    }

    public void n() {
        this.o = true;
        try {
            if (this.n) {
                this.b.removeView(this.W);
                if (this.C.a()) {
                    this.W = this.C.b();
                    this.C.c();
                    this.b.addView(this.W);
                    return;
                }
                m();
                return;
            }
            this.b.removeView(this.l);
            this.b.addView(this.l, -1, -1);
            this.D.setVisibility(8);
            this.J.setVisibleListener(new FloatVideoLayout$2(this));
            this.J.b();
            if (this.m) {
                ao.a((Activity) this.A);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void t() {
        this.o = false;
        this.b.removeView(this.W);
        this.b.removeView(this.l);
        this.J.a();
    }

    public void o() {
        this.b.removeView(this.L);
        this.b.addView(this.L, -1, -1);
    }

    private void u() {
        this.b.removeView(this.L);
        k.a(this.A).m();
    }

    public boolean a(MotionEvent motionEvent) {
        FloatVideoLayout$TouchPosition touchPostion = getTouchPostion();
        if (touchPostion == FloatVideoLayout$TouchPosition.BG) {
            this.a.onTouchEvent(motionEvent);
        } else if (touchPostion != FloatVideoLayout$TouchPosition.VIDEO) {
            return false;
        } else {
            this.b.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean getSendBarrageEditTextFocusState() {
        return this.u.getFocusState();
    }

    private void v() {
        CurtainBGLayout curtainBGLayout = this.a;
        boolean z = this.m || this.n;
        curtainBGLayout.a(z);
    }

    private void w() {
        this.a.setFullScreenState(this.n);
        v();
    }

    public void a() {
        if (this.u.getKeyBoardState()) {
            this.B.b();
        }
    }

    public void b() {
        if (!this.p) {
        }
    }

    public void onClick(View view) {
        if (!this.z && !this.y) {
            switch (view.getId()) {
                case R.id.video_back:
                    m();
                    return;
                case R.id.video_replay:
                    MobclickAgent.onEvent(this.A, "E06-A06", "视频播放完毕后重播按钮点击次数");
                    t();
                    k.a(this.A).r();
                    return;
                case R.id.video_share:
                    MobclickAgent.onEvent(this.A, "E06-A07", "视频播放完毕后分享点击次数");
                    this.B.a();
                    return;
                case R.id.video_friend:
                    MobclickAgent.onEvent(this.A, "E06-A09", "视频播放完毕后分享好友点击次数");
                    EventBus.getDefault().post(new ShareEvent(ShareAction.WXFRIEND, this.ae));
                    return;
                case R.id.video_moments:
                    MobclickAgent.onEvent(this.A, "E06-A10", "视频播放完毕后分享朋友圈点击次数");
                    EventBus.getDefault().post(new ShareEvent(ShareAction.WXP, this.ae));
                    return;
                default:
                    return;
            }
        }
    }

    public void a(int i) {
        if (this.a.isShown()) {
            this.k.a(i);
        }
    }

    public void p() {
        this.B.d();
    }

    public boolean b(int i) {
        if ((!this.m && !this.n) || i != 4) {
            return false;
        }
        if (this.n) {
            m();
        }
        return true;
    }

    public void a(BarrageState barrageState) {
        this.k.a(barrageState, this.r);
    }

    public void a(a aVar, int i) {
        this.ac = aVar;
        this.ad = i;
    }

    public boolean q() {
        return this.D != null && this.D.isShown();
    }
}
