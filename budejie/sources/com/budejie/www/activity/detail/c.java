package com.budejie.www.activity.detail;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.label.enumeration.UpdateLabelPostStatus;
import com.budejie.www.activity.label.h;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.CustomListView;
import com.budejie.www.ad.AdConfig;
import com.budejie.www.ad.AdManager;
import com.budejie.www.adapter.d.b;
import com.budejie.www.adapter.d.d;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.VoteData;
import com.budejie.www.busevent.UpdateCommentAction;
import com.budejie.www.c.e;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.service.MediaPlayerServer;
import com.budejie.www.util.ae;
import com.budejie.www.util.ai;
import com.budejie.www.util.ak;
import com.budejie.www.util.al;
import com.budejie.www.util.an;
import com.budejie.www.util.ax;
import com.budejie.www.util.v;
import com.budejie.www.widget.NewTitleView;
import com.budejie.www.widget.RecordVoiceView;
import com.budejie.www.widget.curtain.BarrageStatusManager;
import com.budejie.www.widget.curtain.BarrageStatusManager.BarrageState;
import com.budejie.www.widget.curtain.FloatVideoLayout;
import com.budejie.www.widget.curtain.FloatVideoRootLayout;
import com.budejie.www.widget.f;
import com.sprite.ads.banner.BannerAd;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.WebView;
import com.umeng.onlineconfig.OnlineConfigAgent;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.tsz.afinal.a.a;

public class c extends Fragment {
    public static List<c> e;
    private boolean A = false;
    private String B;
    private final String C = "20";
    private String D;
    private int E = -1;
    private String F;
    private String G;
    private boolean H = false;
    private boolean I;
    private int J;
    private ArrayList<CommentItem> K = null;
    private a L;
    private CommentItem M;
    private b N;
    private int O = 5;
    private File P;
    private int Q;
    private NewTitleView R;
    private CustomListView S;
    private d T;
    private b U;
    private com.budejie.www.adapter.d.c V;
    private View W;
    private View X;
    private View Y;
    private LinearLayout Z;
    public boolean a = false;
    private String aA;
    private Toast aB;
    private int aC;
    private boolean aD = true;
    private BudejieApplication aE;
    private float aF;
    private c$a aG = c$a.a;
    private boolean aH = false;
    private boolean aI;
    private boolean aJ;
    private a<String> aK = new c$18(this);
    private CustomListView.b aL = new c$24(this);
    private OnScrollListener aM = new c$2(this);
    private NewTitleView.a aN = new c$3(this);
    private Handler aO = new c$4(this);
    private Handler aP = new c$5(this);
    private a.a aQ = new c$7(this);
    private a<String> aR = new c$10(this);
    private OnClickListener aS = new c$11(this);
    private Handler aT = new c$13(this);
    private a<String> aU = new c$15(this);
    private View aa;
    private View ab;
    private View ac;
    private RelativeLayout ad;
    private ImageView ae;
    private TextView af;
    private RecordVoiceView ag;
    private View ah;
    private View ai;
    private RelativeLayout aj;
    private TextView ak;
    private boolean al;
    private RelativeLayout am;
    private RelativeLayout an;
    private TextView ao;
    private TextView ap;
    private ScrollView aq;
    private LinearLayout ar;
    private TextView as;
    private View at;
    private FloatVideoRootLayout au;
    private Dialog av;
    private Dialog aw;
    private f ax;
    private int ay;
    private int az = 0;
    al b;
    public boolean c;
    public boolean d;
    OnClickListener f = new c$20(this);
    a<String> g = new c$8(this);
    a<String> h = new c$9(this);
    private String i = "PostDetailFragment";
    private PostDetailActivity j;
    private e k;
    private com.budejie.www.c.b l;
    private m m;
    private n n;
    private HashMap<String, String> o;
    private com.budejie.www.http.c p;
    private com.elves.update.a q;
    private SharedPreferences r;
    private j s;
    private IWXAPI t;
    private com.budejie.www.g.b u;
    private com.budejie.www.http.b v;
    private String w = "add";
    private ProgressDialog x;
    private ListItemObject y;
    private String z;

    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
        a(context);
        p();
        j();
        k();
    }

    private void j() {
        if (this.L != null) {
            this.L.a(this.a);
        }
    }

    private void k() {
        if (e == null) {
            e = new ArrayList();
        }
        e.add(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.at = layoutInflater.inflate(R.layout.fragment_post_detail, viewGroup, false);
        return this.at;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        q();
    }

    public void onDetach() {
        super.onDetach();
        this.c = false;
        this.aI = false;
        this.aJ = false;
        EventBus.getDefault().unregister(this);
        l();
    }

    private void l() {
        if (!com.budejie.www.goddubbing.c.a.a(e)) {
            e.remove(this);
        }
    }

    public void onEventMainThread(UpdateLabelPostStatus updateLabelPostStatus) {
        if (this.y != null && updateLabelPostStatus != null) {
            if (UpdateLabelPostStatus.ESSENCE_OPERATOR == updateLabelPostStatus) {
                h.a(this.y);
            } else if (UpdateLabelPostStatus.TO_TOP_OPERATOR == updateLabelPostStatus) {
                h.b(this.y);
            }
        }
    }

    public void onEventMainThread(UpdateCommentAction updateCommentAction) {
        if (this.d) {
            this.aJ = true;
            c(true);
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.d && !z) {
            a(true);
        }
        this.d = z;
    }

    public void onResume() {
        super.onResume();
        this.aD = true;
    }

    public void onPause() {
        super.onPause();
        FloatVideoLayout floatVideoLayout = k.a(this.j).f;
        if (!(floatVideoLayout == null || floatVideoLayout.q() || !this.d)) {
            k.a(this.j).o();
        }
        this.aD = false;
        if (this.d) {
            a(false);
        }
    }

    public void a(boolean z) {
        b();
        if (this.b != null) {
            this.b.a(true);
        }
        if (this.ag != null) {
            this.ag.a(z);
        }
        this.aG = c$a.a;
        c();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.T != null) {
            this.T.f();
        }
    }

    private void a(Context context) {
        this.j = (PostDetailActivity) context;
        this.ay = this.j.getWindowManager().getDefaultDisplay().getHeight();
        this.K = new ArrayList();
        this.q = new com.elves.update.a(this.j);
        this.s = new j(this.j);
        this.aA = ai.b(this.j);
        this.r = this.j.getSharedPreferences("weiboprefer", 0);
        this.n = new n(this.j);
        this.k = new e(this.j);
        this.l = new com.budejie.www.c.b(this.j);
        this.m = new m(this.j);
        this.o = this.n.a(this.aA);
        this.p = new com.budejie.www.http.c(this.j, null);
        this.u = new com.budejie.www.g.b(this.j, this.j.mSsoHandler, this.j.mTencent, this.j);
        this.v = com.budejie.www.http.b.a(this.j, null);
        this.N = new b(this.j, this.u, this.aO, this.aP);
        this.L = new a(this.j, this.u, this.p, this.k);
        m();
        n();
        this.D = OnlineConfigAgent.getInstance().getConfigParams(this.j, "评论列表-请求条数");
        if (TextUtils.isEmpty(this.D)) {
            this.D = "20";
        }
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this.j, "send_comment_level");
        if (!TextUtils.isEmpty(configParams)) {
            this.O = Integer.parseInt(configParams);
        }
        o();
    }

    private void m() {
        this.av = new Dialog(this.j, R.style.dialogTheme);
        this.av.setContentView(R.layout.loaddialog);
        this.av.setCanceledOnTouchOutside(true);
        this.av.setCancelable(true);
        this.aw = new Dialog(this.j, R.style.dialogTheme);
        this.aw.setContentView(R.layout.loaddialog);
        this.aw.setCanceledOnTouchOutside(true);
        this.ax = new f(this.j, R.style.dialogTheme);
    }

    private void n() {
        this.t = WXAPIFactory.createWXAPI(this.j, "wx592fdc48acfbe290", true);
        this.t.registerApp("wx592fdc48acfbe290");
    }

    private void o() {
        if (!AdManager.isAdClosed()) {
            this.Z = (LinearLayout) this.j.getLayoutInflater().inflate(R.layout.ad_banner_layout, null);
            BannerAd bannerAd = new BannerAd(AdConfig.banner, this.j, (RelativeLayout) this.Z.findViewById(R.id.ad_banner_container), new c$1(this));
        }
    }

    private void p() {
        Bundle arguments = getArguments();
        this.a = arguments.getBoolean("is_from_commonlabel", false);
        this.y = (ListItemObject) arguments.getSerializable("listitem_object");
        this.B = arguments.getString("request_detail_url");
        if (this.y != null) {
            this.A = arguments.getBoolean("to_comment", false);
            VoteData voteData = this.y.getVoteData();
            if (!(voteData == null || voteData.isVoted())) {
                ax.a(this.y, this.j);
            }
            Log.i("CommendDetail", "帖子评论数:" + this.y.getComment());
            if ("0".equals(this.y.getComment())) {
                k.a(this.j).h();
            }
            this.z = this.y.getWid();
            i.a(this.j.getString(R.string.track_event_see_comment), j.a(this.y), j.b(this.j, this.y));
            return;
        }
        this.z = arguments.getString("msg_wid");
    }

    private void q() {
        if (this.y != null) {
            F();
            J();
            E();
            D();
            C();
            B();
            this.L.a(this.aQ);
            this.L.a(this.y);
            this.L.a(this.Z);
            this.S.setAdapter(this.L);
            this.S.getFootView().setVisibility(8);
            c();
            c(false);
            return;
        }
        r();
    }

    private void r() {
        this.p.a(this.z, this.B, new c$12(this));
    }

    private void c(boolean z) {
        this.E = 0;
        this.F = "0";
        this.G = "0";
        this.I = true;
        this.H = false;
        if (z) {
            this.p.b(this.j, this.z, "0", "0", this.aK);
        } else {
            this.p.a(this.j, this.z, "0", "0", this.aK);
        }
        t();
    }

    private void s() {
        if (this.aJ) {
            this.aJ = false;
            if (k.a(this.j).b()) {
                k.a(this.j).k();
            }
        }
    }

    private void t() {
        A();
        w();
    }

    private void u() {
        if (this.aa == null) {
            this.aa = this.j.getLayoutInflater().inflate(R.layout.comment_item_footer_none_data, null);
        }
        try {
            if (this.S.getFooterViewsCount() > 0) {
                this.S.removeFooterView(this.aa);
            }
            this.S.addFooterView(this.aa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void v() {
        if (this.aa == null) {
            this.aa = this.j.getLayoutInflater().inflate(R.layout.comment_item_footer_none_data, null);
        }
        try {
            if (this.S.getFooterViewsCount() > 0) {
                this.S.removeFooterView(this.aa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void w() {
        if (this.ab == null) {
            this.ab = this.j.getLayoutInflater().inflate(R.layout.comment_item_footer_view, null);
        }
        try {
            if (this.S.getFooterViewsCount() > 0) {
                this.S.removeFooterView(this.ab);
            }
            this.S.addFooterView(this.ab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void x() {
        if (this.ab == null) {
            this.ab = this.j.getLayoutInflater().inflate(R.layout.comment_item_footer_view, null);
        }
        try {
            if (this.S.getFooterViewsCount() > 0) {
                this.S.removeFooterView(this.ab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void y() {
        if (this.ac == null) {
            this.ac = this.j.getLayoutInflater().inflate(R.layout.comment_item_footer_view_failed, null);
        }
        this.ac.setOnClickListener(new c$19(this));
        try {
            if (this.S.getFooterViewsCount() > 0) {
                this.S.removeFooterView(this.ac);
            }
            this.S.addFooterView(this.ac);
            this.aI = true;
        } catch (Exception e) {
        }
    }

    private void z() {
        if (this.ac == null) {
            this.ac = this.j.getLayoutInflater().inflate(R.layout.comment_item_footer_view_failed, null);
        }
        try {
            if (this.S.getFooterViewsCount() > 0) {
                this.aI = !this.S.removeFooterView(this.ac);
            }
        } catch (Exception e) {
            this.aI = true;
        }
    }

    private void A() {
        if (this.S.getFootView() != null) {
            try {
                if (this.S.getFooterViewsCount() > 0) {
                    this.S.removeFooterView(this.S.getFootView());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void B() {
        this.am = (RelativeLayout) this.at.findViewById(R.id.VoiceToWordsLayout);
        this.an = (RelativeLayout) this.at.findViewById(R.id.VoiceToWordsLoadingLayout);
        this.ao = (TextView) this.at.findViewById(R.id.VoiceToWordsTextView);
        this.ap = (TextView) this.at.findViewById(R.id.VoiceToWordsCancelTextView);
        this.aq = (ScrollView) this.at.findViewById(R.id.VoiceToWordsScrollView);
        this.ar = (LinearLayout) this.at.findViewById(R.id.VoiceToWordsFailsLayout);
        this.as = (TextView) this.at.findViewById(R.id.VoiceToWordsFailsTextView);
        this.am.setVisibility(8);
        this.am.setOnClickListener(this.f);
        this.aq.setOnClickListener(this.f);
        this.ao.setOnClickListener(this.f);
        this.ap.setOnClickListener(this.f);
    }

    private void C() {
        this.aj = (RelativeLayout) this.at.findViewById(R.id.screen_shot_mask);
        this.ak = (TextView) this.at.findViewById(R.id.screen_shot_stop);
        this.aj.setOnTouchListener(new c$21(this));
        this.ak.setOnClickListener(new c$22(this));
    }

    private void D() {
        this.ad = (RelativeLayout) this.at.findViewById(R.id.recordBottomFrame);
        this.ae = (ImageView) this.at.findViewById(R.id.recordOrKeyboard);
        this.ae.setOnClickListener(this.aS);
        this.af = (TextView) this.at.findViewById(R.id.write_comment_tv);
        this.af.setOnClickListener(this.aS);
        this.ag = (RecordVoiceView) this.at.findViewById(R.id.record_voice_view);
        this.ah = this.at.findViewById(R.id.record_voice_mask);
        this.ai = this.at.findViewById(R.id.record_send_voice_mask);
        this.ag.a(this.ah, this.ai);
        this.ag.setRecordVoiceHandler(this.aT);
        this.ai.setOnClickListener(new c$23(this));
    }

    private void E() {
        this.T = new d(this.j, this.N, this.y, 0, false);
        this.W = this.T.b();
        this.T.a((com.budejie.www.adapter.b) this.W.getTag());
        this.U = new b(this.j, this.N, this.y, 0);
        this.X = this.U.b();
        this.U.a((com.budejie.www.adapter.b) this.X.getTag());
        this.S.addHeaderView(this.W);
        this.S.addHeaderView(this.X);
        if (!this.a) {
            this.V = new com.budejie.www.adapter.d.c(this.j, this.N, this.y, 0);
            this.Y = this.V.b();
            this.V.a((com.budejie.www.adapter.b) this.Y.getTag());
            this.S.addHeaderView(this.Y);
        }
    }

    private void F() {
        this.au = (FloatVideoRootLayout) this.at.findViewById(R.id.curtain_root_layout);
        this.S = (CustomListView) this.at.findViewById(R.id.listview);
        this.S.setmEnablePullToRefresh(false);
        this.S.setonLoadListener(this.aL);
        this.S.setOnScrollListener(new com.nostra13.universalimageloader.core.d.e(com.nostra13.universalimageloader.core.d.a(), false, true, this.aM));
    }

    private void G() {
        if (!this.I && !this.H) {
            if (an.a(this.j)) {
                this.I = true;
                if (this.aI) {
                    z();
                }
                if (TextUtils.isEmpty(this.G) || this.G.equals("0")) {
                    c(false);
                    return;
                }
                t();
                H();
                return;
            }
            this.aB = an.a(this.j, this.j.getString(R.string.nonet), -1);
            this.aB.show();
        }
    }

    private void H() {
        this.E = 2;
        this.p.a(this.j, this.z, this.G, "2", this.aK);
    }

    private void a(AbsListView absListView, int i) {
        if (this.aG != c$a.c && isAdded()) {
            if (absListView.getLastVisiblePosition() >= 3) {
                if (!this.ad.isShown()) {
                    this.ad.setVisibility(0);
                }
                I();
                float t = (float) ((this.az - an.t(this.j)) + 2);
                float f = this.aF;
                if (absListView.getLastVisiblePosition() == 3) {
                    int[] iArr = new int[2];
                    absListView.getChildAt(i - 1).getLocationOnScreen(iArr);
                    f = (float) ((iArr[1] - an.t(this.j)) + 2);
                    if (f < t) {
                        if (f <= this.aF) {
                            t = this.aF;
                        }
                    }
                    if (t != this.ad.getY()) {
                        this.ad.setY(t);
                    }
                }
                t = f;
                if (t != this.ad.getY()) {
                    this.ad.setY(t);
                }
            } else if (this.ad.isShown()) {
                this.ad.setVisibility(8);
            }
        }
    }

    private void I() {
        if (this.az == 0) {
            if (this.at != null) {
                int[] iArr = new int[2];
                this.at.getLocationOnScreen(iArr);
                this.az = iArr[1] + this.at.getHeight();
                BudejieApplication.i = this.az;
            } else {
                this.az = this.ay;
            }
        }
        this.aF = (float) (((this.az - getResources().getDimensionPixelSize(R.dimen.navigation_height)) - an.t(this.j)) + 2);
    }

    private void J() {
        this.R = (NewTitleView) this.at.findViewById(R.id.new_title_view);
        if (this.y != null) {
            this.R.setmTitleLayoutClick(this.aN);
            this.R.setmListItemObject(this.y);
            if (this.y.getWidth() > 0 && ak.a(this.j, this.y)) {
                K();
                if (this.R != null) {
                    this.R.a(true);
                }
            }
            this.R.a(BarrageStatusManager.a(this.r));
        }
        this.N.a(this.R);
    }

    private void K() {
        if (!this.c) {
            LinearLayout linearLayout = (LinearLayout) this.at.findViewById(R.id.listview_container);
            LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            linearLayout.setLayoutParams(layoutParams);
            this.au.setmMinTopHeight(0);
            this.c = true;
        }
    }

    private void L() {
        if (!com.budejie.www.goddubbing.c.a.a(e) && this.r != null) {
            BarrageState a = BarrageStatusManager.a(this.r);
            if (a != null) {
                for (c cVar : e) {
                    if (!(cVar == this || cVar.R == null)) {
                        cVar.R.a(a);
                    }
                }
            }
        }
    }

    private void a(View view) {
        this.aG = c$a.a;
        if (this.y != null) {
            view.setTag(this.y);
            Bundle bundle = new Bundle();
            bundle.putInt("position", 0);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this.j));
            bundle.putSerializable("weiboMap", this.o);
            bundle.putSerializable("data", this.y);
            bundle.putBoolean("has_screen_shoot", true);
            this.u.a(5, bundle, this.aO, this.t, this.m, this.n, this.q, this.r, this.aP).onClick(view);
        }
    }

    private void d(boolean z) {
        new c$6(this, z).start();
    }

    private void a(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "https://api.budejie.com/api/api_open.php", new j().p(this.j, str), this.g);
    }

    private void a(String str, String str2) {
        BudejieApplication.a.a(RequstMethod.GET, "https://api.budejie.com/api/api_open.php", new j().k(this.j, str2, str), this.h);
    }

    private void b(String str) {
        BudejieApplication.a.a(RequstMethod.GET, j.j(str), this.s, this.aR);
    }

    private void M() {
        this.aA = this.r.getString("id", "");
        if (TextUtils.isEmpty(this.aA)) {
            an.a(this.j, 0, null, null, 0);
        } else {
            com.budejie.www.util.a.a(this.j, this.y, this.M);
        }
    }

    public void a() {
        if (isAdded()) {
            if (this.aE == null) {
                BudejieApplication budejieApplication = (BudejieApplication) getActivity().getApplication();
                this.aE = budejieApplication;
                if (budejieApplication == null) {
                    return;
                }
            }
            ae.a(this.aE, this.j);
        }
    }

    public void b() {
        if (isAdded()) {
            a();
            if (this.aE == null) {
                BudejieApplication budejieApplication = (BudejieApplication) getActivity().getApplication();
                this.aE = budejieApplication;
                if (budejieApplication == null) {
                    return;
                }
            }
            MediaPlayerServer.a f = this.aE.f();
            if (f == null) {
                return;
            }
            if (f.a() || f.n()) {
                f.d();
                this.aE.a(Status.end);
            }
        }
    }

    private void a(List<CommentItem> list) {
        List a = this.k.a();
        if (a.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                CommentItem commentItem = (CommentItem) list.get(i);
                if (!(commentItem == null || commentItem.getId() == null)) {
                    for (int i2 = 0; i2 < a.size(); i2++) {
                        String str = (String) a.get(i2);
                        if (!TextUtils.isEmpty(str)) {
                            String[] split = str.split("#");
                            String str2 = split[0];
                            str = split[1];
                            if (str2 != null && str2.equals(commentItem.getId())) {
                                commentItem.setDingOrCai(str);
                            }
                        }
                    }
                }
                if (!(commentItem == null || commentItem.getReplyList() == null || commentItem.getReplyList().size() <= 0)) {
                    for (int i3 = 0; i3 < commentItem.getReplyList().size(); i3++) {
                        CommentItem commentItem2 = (CommentItem) commentItem.getReplyList().get(i3);
                        for (int i4 = 0; i4 < a.size(); i4++) {
                            String str3 = (String) a.get(i4);
                            if (!TextUtils.isEmpty(str3)) {
                                String[] split2 = str3.split("#");
                                String str4 = split2[0];
                                str3 = split2[1];
                                if (str4 != null && str4.equals(commentItem2.getId())) {
                                    commentItem2.setDingOrCai(str3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(CommentItem commentItem) {
        List a = this.k.a();
        if (commentItem != null && commentItem.getReplyList() != null && commentItem.getReplyList().size() > 0) {
            for (int i = 0; i < commentItem.getReplyList().size(); i++) {
                CommentItem commentItem2 = (CommentItem) commentItem.getReplyList().get(i);
                for (int i2 = 0; i2 < a.size(); i2++) {
                    String str = (String) a.get(i2);
                    if (!TextUtils.isEmpty(str)) {
                        String[] split = str.split("#");
                        String str2 = split[0];
                        str = split[1];
                        if (str2 != null && str2.equals(commentItem2.getId())) {
                            commentItem2.setDingOrCai(str);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void N() {
        View footView = this.S.getFootView();
        if (footView != null) {
            ((ProgressBar) footView.findViewById(R.id.message_list_more_progressbar)).setVisibility(8);
            TextView textView = (TextView) footView.findViewById(R.id.message_list_more_tv);
            textView.setLayoutParams(new LinearLayout.LayoutParams(-1, an.a(this.j, 80)));
            textView.setVisibility(0);
            textView.setText("");
            footView.setVisibility(0);
            this.H = true;
        }
    }

    private void O() {
        String str = "31";
        if (this.ax != null) {
            this.ax.show();
        }
        String str2 = "";
        if (this.M != null) {
            str2 = this.M.getId();
        }
        a(this.s.a(this.j, this.y.getWid(), this.aA, str2, "", str, this.Q, null, this.P, null, "", 0));
        this.aG = c$a.a;
        c();
    }

    public void a(net.tsz.afinal.a.b bVar) {
        net.tsz.afinal.b bVar2 = new net.tsz.afinal.b(this.j.getApplicationContext(), new v(this.j));
        bVar2.a("User-Agent", new WebView(this.j).getSettings().getUserAgentString() + NetWorkUtil.a());
        bVar2.a("cookie", NetWorkUtil.b(this.j));
        bVar2.a(NetWorkUtil.a(this.j.getApplicationContext()));
        StringBuilder append = new StringBuilder().append("http://d.api.budejie.com");
        j jVar = this.s;
        bVar2.b(com.lt.a.a(this.j).a(append.append(j.d(this.y.getWid())).toString()), bVar, new c$14(this));
    }

    public void c() {
        switch (c$17.a[this.aG.ordinal()]) {
            case 1:
                if (this.ag != null) {
                    this.ag.setVisibility(8);
                }
                if (this.ai != null) {
                    this.ai.setVisibility(8);
                }
                if (this.ah != null) {
                    this.ah.setVisibility(8);
                }
                this.M = null;
                if (this.af != null) {
                    this.af.setText("写评论...");
                    return;
                }
                return;
            case 2:
                if (!this.aH) {
                    k.a(this.j).h();
                    b();
                    if (this.ad != null) {
                        I();
                        if (!this.ad.isShown()) {
                            this.ad.setVisibility(0);
                        }
                        this.ad.setY(this.aF);
                    }
                    if (this.ag != null) {
                        this.ag.setVisibility(0);
                    }
                    if (this.ai != null) {
                        this.ai.setVisibility(0);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void b(boolean z) {
        if (this.R != null) {
            this.R.a(z);
        }
    }

    public void d() {
        if (this.b == null) {
            this.aP.sendEmptyMessageDelayed(19, 500);
        }
    }

    public void e() {
        if (this.T != null && !this.A) {
            this.T.e();
        }
    }

    public String f() {
        return this.z;
    }

    public void g() {
        this.b = new al(this.j, this.at.findViewById(R.id.listview_container), an.y(this.j) * 4);
        this.b.a((int) R.drawable.screen_shoot_bottom);
        this.b.a(this.al, new c$16(this));
    }

    public void h() {
        if (this.S != null) {
            this.S.setSelection(1);
        }
    }

    public void i() {
        if (this.ag != null) {
            this.ag.c();
        }
    }
}
