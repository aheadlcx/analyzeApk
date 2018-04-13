package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.nearby.api.ObservableScrollView;
import qsbk.app.nearby.api.PersonalListener;
import qsbk.app.utils.UIHelper;

public abstract class BasePersonalCenterActivity extends Activity implements LocationCallBack {
    public static final int LOGIN_REQUEST_CODE = 2016;
    public static int[] PERSONAL_BG_IMAGE = new int[]{R.drawable.personal_default, R.drawable.personal_desk, R.drawable.personal_sun, R.drawable.personal_greenfield, R.drawable.personal_goldgatebridge};
    protected LinearLayout A;
    protected RelativeLayout B;
    protected TextView C;
    protected LinearLayout D;
    protected TextView E;
    protected TextView F;
    protected ImageView G;
    protected View H;
    protected TextView I;
    protected ImageView J;
    protected TextView K;
    protected TextView L;
    protected ImageView M;
    protected View N;
    protected RelativeLayout O;
    protected TextView P;
    protected TextView Q;
    protected LinearLayout R;
    protected ImageView S;
    protected View T;
    protected RelativeLayout U;
    protected LinearLayout V;
    protected TextView W;
    protected TextView X;
    protected LinearLayout Y;
    protected View Z;
    protected View a;
    protected View aA;
    protected TextView aB;
    protected TextView aC;
    protected ImageView aD;
    protected View aE;
    protected RelativeLayout aF;
    protected TextView aG;
    protected TextView aH;
    protected TextView aI;
    protected ImageView aJ;
    protected View aK;
    protected RelativeLayout aL;
    protected SimpleDraweeView aM;
    protected TextView aN;
    protected TextView aO;
    protected View aP;
    protected ImageView aQ;
    protected RelativeLayout aR;
    protected SimpleDraweeView aS;
    protected TextView aT;
    protected TextView aU;
    protected View aV;
    protected ImageView aW;
    protected RelativeLayout aX;
    protected TextView aY;
    protected TextView aZ;
    protected TextView aa;
    protected RelativeLayout ab;
    protected TextView ac;
    protected LinearLayout ad;
    protected View ae;
    protected TextView af;
    protected RelativeLayout ag;
    protected TextView ah;
    protected LinearLayout ai;
    protected View aj;
    protected RelativeLayout ak;
    protected TextView al;
    protected TextView am;
    protected TextView an;
    protected View ao;
    protected RelativeLayout ap;
    protected RelativeLayout aq;
    protected TextView ar;
    protected ImageView[] as;
    protected ImageView at;
    protected ImageView au;
    protected ImageView av;
    protected RelativeLayout aw;
    protected TextView ax;
    protected TextView ay;
    protected ImageView az;
    protected String b;
    protected ImageView[] ba;
    protected ImageView bb;
    protected View bc;
    protected RelativeLayout bd;
    protected TextView be;
    protected TextView bf;
    protected View bg;
    protected TextView bh;
    protected RelativeLayout bi;
    protected TextView bj;
    protected View bk;
    protected TextView bl;
    protected RelativeLayout bm;
    protected TextView bn;
    protected View bo;
    protected RelativeLayout bp;
    protected TextView bq;
    protected TextView br;
    protected PersonalListener bs;
    protected LocalBroadcastManager bt;
    protected LocationHelper bu = null;
    protected FrameLayout bv;
    protected TextView bw;
    protected int bx = UIHelper.getDefaultImageTileBackground();
    protected ImageView by;
    protected LinearLayout c;
    protected FrameLayout d;
    protected View e;
    protected ProgressBar f;
    protected LinearLayout g;
    protected TextView h;
    protected View i;
    protected TextView j;
    protected ObservableScrollView k;
    protected LinearLayout l;
    protected RelativeLayout m;
    protected TextView n;
    protected ImageView o;
    protected TextView p;
    protected ImageView q;
    protected TextView r;
    protected LinearLayout s;
    protected ImageView t;
    protected TextView u;
    protected TextView v;
    protected TextView w;
    protected TextView x;
    protected TextView y;
    protected TextView z;

    public abstract void onLocateDone();

    public abstract void onLocateFailed();

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public void startLocate() {
        if (this.bu == null) {
            this.bu = new LocationHelper((Context) this);
        }
        LocationHelper locationHelper = this.bu;
        if (LocationHelper.loadCache()) {
            onLocateDone();
            this.bu.startLocate(null);
            return;
        }
        this.bu.startLocate(this);
    }

    protected void onCreate(Bundle bundle) {
        initView();
        super.onCreate(bundle);
    }

    protected void a() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
    }

    public void initView() {
        this.a = findViewById(R.id.layerMask);
        if (UIHelper.isNightTheme()) {
            this.a.setVisibility(0);
        } else {
            this.a.setVisibility(8);
        }
        this.c = (LinearLayout) findViewById(R.id.personal_center_botton_lin);
        this.f = (ProgressBar) findViewById(R.id.personal_fans_progress_bar);
        this.g = (LinearLayout) findViewById(R.id.personal_center_botton_fan_lin);
        this.d = (FrameLayout) findViewById(R.id.personal_center_botton_frame);
        this.e = findViewById(R.id.personal_center_botton_view);
        this.h = (TextView) findViewById(R.id.personal_fans_tv);
        this.i = findViewById(R.id.personal_fans_note_line);
        this.j = (TextView) findViewById(R.id.personal_note);
        this.k = (ObservableScrollView) findViewById(R.id.personal_scrollView);
        this.l = (LinearLayout) findViewById(R.id.personal_info_head_lin);
        this.m = (RelativeLayout) findViewById(R.id.personal_bar_background_rel);
        this.n = (TextView) findViewById(R.id.reback);
        this.o = (ImageView) findViewById(R.id.choice);
        this.p = (TextView) findViewById(R.id.personal_edit);
        this.q = (ImageView) findViewById(R.id.personal_avatar);
        this.r = (TextView) findViewById(R.id.personal_username);
        this.s = (LinearLayout) findViewById(R.id.personal_gender_age);
        this.t = (ImageView) findViewById(R.id.personal_gender);
        this.u = (TextView) findViewById(R.id.personal_age);
        this.v = (TextView) findViewById(R.id.personal_info);
        this.w = (TextView) findViewById(R.id.smile);
        this.x = (TextView) findViewById(R.id.hot_comment);
        this.y = (TextView) findViewById(R.id.highlight);
        this.z = (TextView) findViewById(R.id.check_in);
        this.U = (RelativeLayout) findViewById(R.id.qiushi_topic_rel);
        this.V = (LinearLayout) findViewById(R.id.qiushi_topic_left_lin);
        this.W = (TextView) findViewById(R.id.qiushi_topic_remind);
        this.X = (TextView) findViewById(R.id.qiushi_topic_num);
        this.Y = (LinearLayout) findViewById(R.id.qiushi_topic_right_lin);
        this.Z = findViewById(R.id.qiushi_topic_dynamic_line);
        this.A = (LinearLayout) findViewById(R.id.personal_body_lin);
        this.B = (RelativeLayout) findViewById(R.id.personal_dynamic_lin);
        this.C = (TextView) findViewById(R.id.personal_my_other_dynamic);
        this.I = (TextView) findViewById(R.id.personal_dynamic_num);
        this.J = (ImageView) findViewById(R.id.personal_dynamic_image);
        this.K = (TextView) findViewById(R.id.personal_dynamic_content);
        this.L = (TextView) findViewById(R.id.personal_dynamic_location);
        this.M = (ImageView) findViewById(R.id.personal_dynaic_view);
        this.N = findViewById(R.id.personal_dynamic_group_view);
        this.D = (LinearLayout) findViewById(R.id.personal_circle_level_and_score_lin);
        this.E = (TextView) findViewById(R.id.personal_circle_level);
        this.F = (TextView) findViewById(R.id.personal_circle_score);
        this.G = (ImageView) findViewById(R.id.personal_circle_help);
        this.H = findViewById(R.id.personal_circle_level_and_score_view);
        this.O = (RelativeLayout) findViewById(R.id.personal_qiushi_lin);
        this.P = (TextView) findViewById(R.id.personal_my_other_qiushi);
        this.Q = (TextView) findViewById(R.id.personal_qiushi_num);
        this.R = (LinearLayout) findViewById(R.id.personal_qiushi_content_lin);
        this.S = (ImageView) findViewById(R.id.personal_qiushi_view);
        this.T = findViewById(R.id.personal_dynamic_view);
        this.aa = (TextView) findViewById(R.id.personal_group_remind);
        this.ab = (RelativeLayout) findViewById(R.id.personal_group_rel);
        this.ac = (TextView) findViewById(R.id.personal_group_num);
        this.ad = (LinearLayout) findViewById(R.id.personal_group_right_lin);
        this.af = (TextView) findViewById(R.id.personal_topic_remind);
        this.ag = (RelativeLayout) findViewById(R.id.personal_topic_rel);
        this.ah = (TextView) findViewById(R.id.personal_topic_num);
        this.ai = (LinearLayout) findViewById(R.id.personal_topic_right_lin);
        this.aj = findViewById(R.id.personal_topic_group_line);
        this.ak = (RelativeLayout) findViewById(R.id.presonal_score_rel);
        this.al = (TextView) findViewById(R.id.presonal_score_remind);
        this.am = (TextView) findViewById(R.id.personal_score_next);
        this.an = (TextView) findViewById(R.id.presonal_score);
        this.ao = findViewById(R.id.presonal_score_view);
        this.ae = findViewById(R.id.personal_group_hometown_line);
        this.bd = (RelativeLayout) findViewById(R.id.personal_hometown_rel);
        this.be = (TextView) findViewById(R.id.personal_hometown_remind);
        this.bf = (TextView) findViewById(R.id.presonal_hometowm);
        this.bg = findViewById(R.id.personal_hometown_view);
        this.bh = (TextView) findViewById(R.id.personal_qb_age_remind);
        this.bi = (RelativeLayout) findViewById(R.id.personal_qb_age_rel);
        this.bj = (TextView) findViewById(R.id.personal_qb_age);
        this.bk = findViewById(R.id.personal_qb_age_view);
        this.bl = (TextView) findViewById(R.id.personal_job_remind);
        this.bm = (RelativeLayout) findViewById(R.id.personal_job_rel);
        this.bn = (TextView) findViewById(R.id.personal_job);
        this.bo = findViewById(R.id.personal_job_view);
        this.bp = (RelativeLayout) findViewById(R.id.personal_origin_rel);
        this.bq = (TextView) findViewById(R.id.personal_origin);
        this.br = (TextView) findViewById(R.id.personal_origin_remind);
        this.bv = (FrameLayout) findViewById(R.id.personal_head_frame);
        this.bw = (TextView) findViewById(R.id.personal_remark);
        this.ap = (RelativeLayout) findViewById(R.id.personal_live_lin);
        this.aq = (RelativeLayout) findViewById(R.id.personal_live_receive_present_lin);
        this.ar = (TextView) findViewById(R.id.personal_live_label);
        this.as = new ImageView[3];
        this.as[0] = (ImageView) findViewById(R.id.personal_live_send_present_avatar1);
        this.as[1] = (ImageView) findViewById(R.id.personal_live_send_present_avatar2);
        this.as[2] = (ImageView) findViewById(R.id.personal_live_send_present_avatar3);
        this.at = (ImageView) findViewById(R.id.personal_live_present_gold);
        this.au = (ImageView) findViewById(R.id.personal_live_present_silver);
        this.av = (ImageView) findViewById(R.id.personal_live_present_copper);
        this.aw = (RelativeLayout) findViewById(R.id.personal_live_receive_present_layout);
        this.ax = (TextView) findViewById(R.id.personal_live_receive_present_total_tip);
        this.ay = (TextView) findViewById(R.id.personal_live_receive_present_total);
        this.az = (ImageView) findViewById(R.id.personal_live_present_imageview);
        this.aA = findViewById(R.id.personal_live_receive_present_divider);
        this.aB = (TextView) findViewById(R.id.personal_live_level);
        this.aC = (TextView) findViewById(R.id.personal_live_send_present_total);
        this.aD = (ImageView) findViewById(R.id.personal_live_level_imageview);
        this.aE = findViewById(R.id.personal_live_level_divider);
        this.aF = (RelativeLayout) findViewById(R.id.personal_live_no_receive_lin);
        this.aG = (TextView) findViewById(R.id.personal_live_no_receive_label);
        this.aH = (TextView) findViewById(R.id.personal_live_no_receive_level);
        this.aI = (TextView) findViewById(R.id.personal_live_no_receive_send_present_total);
        this.aJ = (ImageView) findViewById(R.id.personal_live_no_receive_level_imageview);
        this.aK = findViewById(R.id.personal_live_no_receive_level_divider);
        this.aL = (RelativeLayout) findViewById(R.id.personal_no_live_family_rel);
        this.aM = (SimpleDraweeView) findViewById(R.id.personal_no_live_family_avator);
        this.aN = (TextView) findViewById(R.id.personal_no_live_family_name);
        this.aO = (TextView) findViewById(R.id.personal_no_live_family_intro);
        this.aP = findViewById(R.id.personal_live_no_family_divider);
        this.aQ = (ImageView) findViewById(R.id.personal_no_live_family_next);
        this.aR = (RelativeLayout) findViewById(R.id.personal_family_rel);
        this.aS = (SimpleDraweeView) findViewById(R.id.personal_family_avator);
        this.aT = (TextView) findViewById(R.id.personal_family_name);
        this.aU = (TextView) findViewById(R.id.personal_family_intro);
        this.aV = findViewById(R.id.personal_family_divider);
        this.aW = (ImageView) findViewById(R.id.personal_family_next);
        this.aX = (RelativeLayout) findViewById(R.id.personal_medal_lin);
        this.aY = (TextView) findViewById(R.id.personal_medal_sign);
        this.aZ = (TextView) findViewById(R.id.personal_medal_num);
        this.ba = new ImageView[3];
        this.ba[0] = (ImageView) findViewById(R.id.personal_medal_first);
        this.ba[1] = (ImageView) findViewById(R.id.personal_medal_second);
        this.ba[2] = (ImageView) findViewById(R.id.personal_medal_third);
        this.bb = (ImageView) findViewById(R.id.personal_medal_more);
        this.bc = findViewById(R.id.personal_medal_divider);
        this.by = (ImageView) findViewById(R.id.certification);
    }

    protected void a(String str, String str2, ImageView imageView) {
        FrescoImageloader.displayAvatar(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
    }

    protected void onDestroy() {
        if (this.bu != null) {
            this.bu = null;
        }
        super.onDestroy();
    }

    protected boolean b() {
        if (VERSION.SDK_INT >= 19) {
            return true;
        }
        return false;
    }

    public float getDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public int getViewHeight(View view) {
        return view.getMeasuredHeight();
    }
}
