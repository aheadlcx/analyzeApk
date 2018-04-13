package com.budejie.www.activity;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.app.AlertDialog.Builder;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sdk.sixrooms.coop.NotifyAppLoginCallBack;
import cn.v6.sdk.sixrooms.coop.NotifyAppLogoutCallBack;
import cn.v6.sdk.sixrooms.coop.SyncLoginCallBack;
import cn.v6.sdk.sixrooms.coop.SyncLogoutCallBack;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.androidex.util.CacheManager;
import com.androidex.util.EnvironmentUtil;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.BudejieApplication.b;
import com.budejie.www.activity.htmlpage.HtmlFeatureActivity;
import com.budejie.www.activity.plate.PlateListActivity;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.activity.recommend.SuggestedFollowsActivity;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.BadgeView;
import com.budejie.www.activity.view.BottomRelativeLayout;
import com.budejie.www.ad.AdConfig;
import com.budejie.www.ad.AdManager;
import com.budejie.www.bean.Navigation;
import com.budejie.www.bean.Navigations;
import com.budejie.www.bean.OperationItem;
import com.budejie.www.bean.SpiderResult;
import com.budejie.www.bean.TopNavigation;
import com.budejie.www.bean.UserItem;
import com.budejie.www.busevent.LoginAction;
import com.budejie.www.c.f;
import com.budejie.www.c.i;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.d;
import com.budejie.www.http.g;
import com.budejie.www.http.h;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.push.IxinPushReceiver;
import com.budejie.www.type.UpdateUserInfo;
import com.budejie.www.util.aa;
import com.budejie.www.util.ah;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.av;
import com.budejie.www.util.p;
import com.budejie.www.util.t;
import com.budejie.www.util.w;
import com.budejie.www.util.z;
import com.elves.update.c;
import com.google.gson.Gson;
import com.jlzx.comment.CommentSingleton;
import com.sprite.ads.SpriteAD;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.splash.SplashADListener;
import com.sprite.ads.splash.SplashAd;
import com.tencent.smtt.utils.TbsLog;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.x;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.update.UpdateConfig;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;

public class HomeGroup extends ActivityGroup implements OnClickListener, b, com.budejie.www.f.a, c, com.sina.weibo.sdk.share.a {
    public static com.sina.weibo.sdk.share.b A;
    public static int a = 601;
    private static boolean af = true;
    public static int b = 602;
    public static int c = 603;
    public static int d = 1048576;
    public static long e = 104857600;
    public static File f = new File(Environment.getExternalStorageDirectory(), "budejie" + File.separator + "ImageCache");
    public static FrameLayout g;
    public static int h = 0;
    public static int i = 0;
    public static int j = 0;
    public static String k;
    public static int l = 0;
    public static int m = 0;
    public static int n = 0;
    public static int o = 0;
    public static int p = 0;
    public static BottomRelativeLayout w;
    public static Navigations y;
    public static Navigations z;
    Handler B = new Handler(this) {
        final /* synthetic */ HomeGroup a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = 0;
            int i2 = message.what;
            if (i2 == SocketUtil.TYPEID_810) {
                if (this.a.X != null) {
                    this.a.X.cancel();
                }
                try {
                    this.a.ak.a(this.a.D, (String) message.obj, (Handler) this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (i2 == 922) {
                an.a(this.a.D);
            } else if (i2 == R$styleable.Theme_Custom_send_btn_text_color) {
                com.budejie.www.util.b.a(this.a, ai.b(this.a.getApplicationContext()));
            } else if (i2 == SocketUtil.TYPEID_811) {
                try {
                    this.a.D.unregisterReceiver(this.a.aj);
                } catch (Exception e2) {
                }
                this.a.D.finish();
                System.exit(0);
            } else if (i2 == 822) {
                this.a.aw.b();
                if (this.a.ar != null) {
                    this.a.ah.edit().putInt("imgCount_updateRemind", this.a.ar.intValue()).commit();
                }
            } else if (i2 == 823) {
                this.a.aw.a(this.a.t, this.a.t);
            } else if (i2 == 935) {
                this.a.ax.b();
                if (this.a.as != null) {
                    this.a.ah.edit().putInt("dzCount_updateRemind", this.a.as.intValue()).commit();
                }
            } else if (i2 == 936) {
                this.a.ax.a(this.a.t, this.a.t);
            } else if (i2 == 948) {
            } else {
                if (i2 == 949) {
                    if ("1".equals(an.b(this.a.D))) {
                        this.a.ag = true;
                    }
                } else if (i2 == TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR) {
                    this.a.ay.b();
                    if (this.a.at != null) {
                        this.a.ah.edit().putInt("soundCount_updateRemind", this.a.at.intValue()).commit();
                    }
                } else if (i2 == 9991) {
                    this.a.az.b();
                    if (this.a.au != null) {
                        this.a.ah.edit().putInt("videoCount_updateRemind", this.a.au.intValue()).commit();
                    }
                } else if (i2 == 9980) {
                    if (HomeGroup.p > 0) {
                        aa.b("HomeGroup", "sAllUpdateCount=" + HomeGroup.p);
                        if (HomeGroup.p > 99) {
                            this.a.aA.setText("99+");
                        } else {
                            this.a.aA.setText(String.valueOf(HomeGroup.p));
                        }
                        this.a.aA.a();
                    } else if (this.a.ah.getBoolean("shenheUpdate", false)) {
                        this.a.aA.b();
                    } else {
                        this.a.aA.b();
                        if (HomeGroup.o != 1 && HomeGroup.j <= 0) {
                            this.a.aB.b();
                        }
                    }
                } else if (i2 == 9981) {
                    if (HomeGroup.o != 1 && HomeGroup.j <= 0) {
                        this.a.aB.b();
                    }
                    this.a.aA.b();
                    HomeGroup.p = 0;
                } else if (i2 == 9982) {
                    this.a.aB.b();
                } else if (i2 == 9993) {
                    HomeGroup.j = 0;
                } else if (i2 == 950) {
                    r0 = (String) message.obj;
                    aa.a("reportresult", r0);
                    this.a.ah.edit().putString("reportdata", r0).commit();
                } else if (i2 == TbsLog.TBSLOG_CODE_SDK_THIRD_MODE) {
                    r0 = (String) message.obj;
                    if (!TextUtils.isEmpty(r0)) {
                        ai.a(this.a.D, System.currentTimeMillis());
                        ai.b(this.a.D, r0);
                    }
                } else if (i2 == 723) {
                    aa.a("HomeGroup", "更新提醒");
                    this.a.al.a(722, ai.b(this.a.D));
                } else if (i2 == 722) {
                    Map r = z.r((String) message.obj);
                    if (r != null) {
                        Integer num;
                        boolean z = this.a.ah.getBoolean("shenheUpdate", false);
                        Integer valueOf = Integer.valueOf(r.get("msgCenter") == null ? 0 : Integer.parseInt(String.valueOf(r.get("msgCenter"))));
                        Integer valueOf2 = Integer.valueOf(r.get("friendInfoNum") == null ? 0 : Integer.parseInt(String.valueOf(r.get("friendInfoNum"))));
                        Object valueOf3 = r.get("friendInfoHeader") == null ? "" : String.valueOf(r.get("friendInfoHeader"));
                        if (!TextUtils.isEmpty(valueOf3)) {
                            HomeGroup.k = valueOf3;
                        }
                        Integer valueOf4 = Integer.valueOf(r.get("fansAdd") == null ? 0 : Integer.parseInt(String.valueOf(r.get("fansAdd"))));
                        Integer valueOf5 = Integer.valueOf(r.get("friendRecommendSina") == null ? 0 : Integer.parseInt(String.valueOf(r.get("friendRecommendSina"))));
                        Integer valueOf6 = Integer.valueOf(r.get("friendRecommendQQ") == null ? 0 : Integer.parseInt(String.valueOf(r.get("friendRecommendQQ"))));
                        Integer valueOf7 = Integer.valueOf(0);
                        Integer valueOf8 = Integer.valueOf(r.get("friendRecommendSina") == null ? 0 : Integer.parseInt(String.valueOf(r.get("new_redpack"))));
                        if (valueOf8 != null) {
                            HomeGroup.o = valueOf8.intValue();
                            if (HomeGroup.o == 1) {
                                this.a.aB.a(this.a.t, this.a.t);
                                this.a.D.sendBroadcast(new Intent("android.budejie.more.UPDATE_RED_PACKET"));
                            } else {
                                this.a.aB.b();
                            }
                        }
                        if (HomeGroup.j > 0) {
                            this.a.aB.a(this.a.t, this.a.t);
                            this.a.D.sendBroadcast(new Intent("android.budejie.more.MYFRIEND_UPDATE"));
                        } else {
                            this.a.aB.b();
                        }
                        if (valueOf5 == null || valueOf5.intValue() == 0) {
                            num = valueOf7;
                        } else {
                            num = Integer.valueOf(valueOf7.intValue() + valueOf5.intValue());
                            HomeGroup.m = valueOf5.intValue();
                        }
                        if (!(valueOf6 == null || valueOf6.intValue() == 0)) {
                            Integer.valueOf(num.intValue() + valueOf6.intValue());
                            HomeGroup.n = valueOf6.intValue();
                        }
                        if (valueOf2 != null) {
                            HomeGroup.j = valueOf2.intValue();
                        }
                        valueOf7 = Integer.valueOf(0);
                        if ((valueOf != null && valueOf.intValue() != 0) || ((valueOf4 != null && valueOf4.intValue() != 0) || (valueOf7 != null && valueOf7.intValue() != 0))) {
                            i2 = valueOf == null ? 0 : valueOf.intValue();
                            if (valueOf4 != null) {
                                i = valueOf4.intValue();
                            }
                            i2 += i;
                            HomeGroup.p = i2;
                            if (i2 > 99) {
                                this.a.aA.setText("99+");
                            } else {
                                this.a.aA.setText(String.valueOf(i2));
                            }
                            this.a.aA.a();
                        } else if (!z) {
                            HomeGroup.p = 0;
                            if (HomeGroup.o != 1 && HomeGroup.j <= 0) {
                                this.a.aA.b();
                                this.a.aB.b();
                            }
                        }
                        if (valueOf != null) {
                            HomeGroup.h = valueOf.intValue();
                            this.a.D.sendBroadcast(new Intent("android.budejie.more.NEWS_UPDATE"));
                        }
                        if (valueOf4 != null) {
                            if (valueOf4.intValue() >= 0) {
                                if (valueOf4.intValue() > 99) {
                                    HomeGroup.i = 99;
                                } else {
                                    HomeGroup.i = valueOf4.intValue();
                                }
                            }
                            this.a.D.sendBroadcast(new Intent("android.budejie.more.FANS_ADD_UPDATE"));
                        }
                        if (valueOf2 != null) {
                            HomeGroup.j = valueOf2.intValue();
                        }
                        if (valueOf7 != null) {
                            if (valueOf7.intValue() >= 0) {
                                if (valueOf7.intValue() > 99) {
                                    HomeGroup.l = 99;
                                } else {
                                    HomeGroup.l = valueOf7.intValue();
                                }
                            }
                            this.a.D.sendBroadcast(new Intent("android.budejie.more.RECOMMEND_UPDATE"));
                        }
                        if (valueOf != null && valueOf.intValue() != 0) {
                            return;
                        }
                        if (valueOf2 != null && valueOf2.intValue() != 0) {
                            return;
                        }
                        if (valueOf4 != null && valueOf4.intValue() != 0) {
                            return;
                        }
                        if (valueOf7 != null && valueOf7.intValue() != 0) {
                            return;
                        }
                        if (valueOf8 != null && valueOf8.intValue() != 0) {
                            return;
                        }
                        if (z) {
                            this.a.aB.b();
                        } else {
                            this.a.aB.b();
                        }
                    }
                } else if (i2 == HomeGroup.a) {
                    this.a.a(this.a.getText(R.string.no_space), this.a.getText(R.string.prompt_sdcard_no_space));
                } else if (i2 == HomeGroup.b) {
                    this.a.a((CharSequence) "SD卡不可用", (CharSequence) "SD卡已拔出，图片将无法显示。");
                } else if (i2 == HomeGroup.c) {
                    p.a(this.a);
                }
            }
        }
    };
    boolean C = false;
    private HomeGroup D;
    private ImageView E;
    private ImageView F;
    private ImageView G;
    private ImageView H;
    private ImageView I;
    private ImageView J;
    private AnimationDrawable K = null;
    private FrameLayout L;
    private FrameLayout M;
    private TextView N;
    private TextView O;
    private FrameLayout P;
    private FrameLayout Q;
    private FrameLayout R;
    private RelativeLayout S;
    private RelativeLayout T;
    private RelativeLayout U;
    private RelativeLayout V;
    private RelativeLayout W;
    private ProgressDialog X;
    private com.budejie.www.g.b Y;
    private Toast Z;
    private BadgeView aA;
    private BadgeView aB;
    private int aC = -10000;
    private BudejieApplication aD;
    private n aE;
    private long aF = 0;
    private String aG;
    private SharedPreferences aH;
    private UserItem aI;
    private m aJ;
    private int aK;
    private String aL;
    private String aM;
    private String aN = "";
    private NotifyAppLoginCallBack aO;
    private NotifyAppLogoutCallBack aP;
    private SyncLoginCallBack aQ;
    private SyncLogoutCallBack aR;
    private String aS;
    private boolean aT;
    private Boolean aU;
    private Handler aV = new Handler(this) {
        final /* synthetic */ HomeGroup a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (!this.a.aT) {
                this.a.aU = Boolean.valueOf(true);
                this.a.getWindow().clearFlags(1024);
                if (this.a.aW != null) {
                    this.a.aW.setVisibility(8);
                }
                this.a.h();
            }
        }
    };
    private RelativeLayout aW;
    private SharedPreferences aX;
    private net.tsz.afinal.a.a<String> aY = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ HomeGroup a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            aa.b("HomeGroup", "onRequestCallBack onSuccess");
            if (((SpiderResult) z.a(str, SpiderResult.class)) != null) {
                t.a(this.a, "spider_result_cache_file", str);
                aa.b("HomeGroup", "writeFile NAVIGATION_CACHE_FILE");
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            aa.b("HomeGroup", "onRequestCallBack onFailure");
        }
    };
    private long aa = 0;
    private Intent ab;
    private Intent ac;
    private Intent ad;
    private Intent ae;
    private boolean ag;
    private SharedPreferences ah = null;
    private LocalActivityManager ai;
    private a aj;
    private p ak;
    private d al;
    private com.budejie.www.http.m am;
    private g an;
    private IntentFilter ao = new IntentFilter();
    private Timer ap;
    private TimerTask aq;
    private Integer ar;
    private Integer as;
    private Integer at;
    private Integer au;
    private Intent av;
    private BadgeView aw;
    private BadgeView ax;
    private BadgeView ay;
    private BadgeView az;
    public LinearLayout q;
    int r = 3600000;
    int s = 30000;
    int t;
    boolean u;
    boolean v = true;
    public com.budejie.www.c.g x;

    class a extends BroadcastReceiver {
        final /* synthetic */ HomeGroup a;

        a(HomeGroup homeGroup) {
            this.a = homeGroup;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.android.intent.sisiter.host.refresh")) {
                HomeGroup.g.removeAllViews();
                this.a.ab.putExtra("flag", true);
                this.a.ab.addFlags(67108864);
                this.a.ab.putExtra("post_type", ((TopNavigation) ((Navigation) HomeGroup.z.menus.get(0)).submenus.get(0)).getKey());
                HomeGroup.g.addView(this.a.ai.startActivity("essence_type", this.a.ab).getDecorView());
            } else if (intent.getAction().equals("android.hide.sister.NOTIFYTIPS")) {
                this.a.B.sendEmptyMessage(822);
            } else if (intent.getAction().equals("android.hide.sister.duanzi.NOTIFYTIPS")) {
                this.a.B.sendEmptyMessage(935);
            } else if (intent.getAction().equals("android.hide.sister.apply.NOTIFYTIPS")) {
                this.a.B.sendEmptyMessage(948);
            } else if (intent.getAction().equals("android.hide.sister.sound.NOTIFYTIPS")) {
                this.a.B.sendEmptyMessage(TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR);
            } else if (intent.getAction().equals("android.hide.sister.news.NOTIFYTIPS")) {
                this.a.B.sendEmptyMessage(9980);
            } else if (intent.getAction().equals("android.hide.sister.my.NOTIFYTIPS")) {
                this.a.B.sendEmptyMessage(9981);
            } else if (intent.getAction().equals("android.hide.sister.voido.NOTIFYTIPS")) {
                this.a.B.sendEmptyMessage(9991);
            } else if (intent.getAction().equals("android.hide.sister.friend.NOTIFYTIPS")) {
                this.a.B.sendEmptyMessage(9993);
            } else if (intent.getAction().equals("android.hide.sister.my.HANDLER_HIDE_MY_REDPACKET_TIPS")) {
                this.a.B.sendEmptyMessage(9982);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        aa.a("HomeGroup", "onCreate");
        aa.b("start_test", "+++++HomeGroup.onCreate");
        this.D = this;
        an.f((Activity) this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.aN = extras.getString("jumpUrl");
            aa.a("jump", "HomeGroup onCreate:" + this.aN);
        } else {
            this.aN = "";
        }
        this.aH = getSharedPreferences("weiboprefer", 0);
        this.aG = this.aH.getString("id", "");
        SpriteAD.init(getApplicationContext());
        SpriteAD.setAppKey(AdConfig.APP_KEY);
        SpriteAD.setMarket("xiaomi");
        SpriteAD.setUid(this.aG);
        if (AdManager.isVIP(this)) {
            AdManager.closeAd();
        } else {
            AdManager.openAd();
        }
        d();
        UserItem g = an.g(this.D);
        com.d.a.a(this.D, g);
        com.b.a.a(g);
        boolean booleanExtra = getIntent().getBooleanExtra("noSplashAd", false);
        if (!booleanExtra) {
            getWindow().setFlags(1024, 1024);
        }
        this.aD = (BudejieApplication) getApplication();
        this.aD.g().Q.a(Long.valueOf(System.currentTimeMillis()));
        setTheme(com.budejie.www.h.c.a().a(ai.a(this)));
        setContentView(R.layout.layout);
        com.budejie.www.widget.a.a((Activity) this);
        q();
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "ad_splash_config");
        g();
        aa.a("HomeGroup", "onCreate startFromSwithchTheme=" + booleanExtra);
        aa.a("HomeGroup", "onCreate adSplashConfig=" + configParams);
        EventBus.getDefault().register(this, 1);
        c();
        com.lt.a.a(new com.lt.a.a(this) {
            final /* synthetic */ HomeGroup a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                com.lt.a.a(this.a.D).a(this.a.D, z);
            }
        });
        com.lt.a.a(this.D);
        A = new com.sina.weibo.sdk.share.b(this);
        A.a();
    }

    private void c() {
        if (this.aN != null && !this.aN.equals("")) {
            Intent intent = new Intent();
            intent.setClass(this.D, HtmlFeatureActivity.class);
            intent.setData(Uri.parse(this.aN));
            this.D.startActivity(intent);
        }
    }

    private void d() {
        this.aO = new NotifyAppLoginCallBack(this) {
            final /* synthetic */ HomeGroup a;

            {
                this.a = r1;
            }

            public void appNeedLogin() {
                UserItem g = an.g(this.a.D);
                if (g != null) {
                    com.d.a.a(this.a.D, g);
                } else {
                    an.a(this.a.D, 0, null, null, 0);
                }
            }
        };
        this.aP = new NotifyAppLogoutCallBack(this) {
            final /* synthetic */ HomeGroup a;

            {
                this.a = r1;
            }

            public void appNeedLogout() {
            }
        };
        this.aQ = new SyncLoginCallBack(this) {
            final /* synthetic */ HomeGroup a;

            {
                this.a = r1;
            }

            public void syncLoginSuccess() {
                aa.b("HomeGroup", "syncLoginCallBack syncLoginSuccess()");
            }

            public void syncLoginFailed(String str, String str2) {
                aa.b("HomeGroup", "syncLoginCallBack syncLoginFailed() flag =" + str + ",content=" + str2);
            }
        };
        this.aR = new SyncLogoutCallBack(this) {
            final /* synthetic */ HomeGroup a;

            {
                this.a = r1;
            }

            public void syncLogoutSuccess() {
                aa.b("HomeGroup", "syncLogoutCallBack syncLogoutSuccess()");
            }

            public void syncLogoutFailed(String str, String str2) {
                aa.b("HomeGroup", "syncLogoutCallBack syncLogoutFailed() flag =" + str + ",content=" + str2);
            }
        };
        com.d.a.a(this.aQ, this.aR, this.aO, this.aP);
    }

    public void onEventMainThread(LoginAction loginAction) {
        if (loginAction != LoginAction.LOGIN) {
            a(false);
        }
        this.ab.putExtra("navigation_key", (Serializable) z.menus.get(0));
        this.ac.putExtra("navigation_key", (Serializable) z.menus.get(1));
        aa.a("tangjian", "homeGroup执行了");
    }

    private void e() {
        aa.b("HomeGroup", "init.....");
        m();
        o();
        v();
        x();
        an.c((Activity) this);
        n();
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this.D, "sync_follow_list_frequency");
        if (!TextUtils.isEmpty(configParams)) {
            this.aF = (Long.parseLong(configParams) * 60) * 1000;
        }
        try {
            com.budejie.www.h.a.a().a(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        an.u(this);
        this.x = new com.budejie.www.c.g(this);
        b();
        l();
        final String b = ai.b(getApplicationContext());
        k();
        this.B.postDelayed(new Runnable(this) {
            final /* synthetic */ HomeGroup b;

            public void run() {
                HomeGroup.a(this.b.D, b);
                ai.g(this.b.getApplicationContext(), b);
                this.b.w();
                if (an.a(this.b.aH)) {
                    NetWorkUtil.c(this.b.D, b);
                    this.b.y();
                }
            }
        }, 10000);
        j();
    }

    private void f() {
        com.lt.a.a(this.D).a(this.D);
    }

    private void g() {
        boolean z = true;
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.splashADView);
        boolean booleanExtra = getIntent().getBooleanExtra("noSplashAd", false);
        if (this.ah.getBoolean("isFirstIn", true)) {
            this.ah.edit().putBoolean("isFirstIn", false).commit();
        } else {
            z = booleanExtra;
        }
        aa.a("HomeGroup", "onCreate initSplashAD startFromSwithchTheme: " + z);
        if (z) {
            relativeLayout.setVisibility(8);
            aa.a("HomeGroup", "onCreate initSplashAD 没广告 直接初始化");
            getWindow().clearFlags(1024);
            e();
            return;
        }
        relativeLayout.setVisibility(0);
        aa.a("HomeGroup", "onCreate initSplashAD loadAD");
        a(relativeLayout);
    }

    private void a(RelativeLayout relativeLayout) {
        if (AdManager.isAdClosed()) {
            getWindow().clearFlags(1024);
            relativeLayout.setVisibility(8);
            h();
            return;
        }
        this.aW = relativeLayout;
        this.aU = Boolean.valueOf(false);
        this.aT = false;
        this.aV.sendEmptyMessageDelayed(0, 5000);
        SplashAd splashAd = new SplashAd(AdConfig.splash, this, relativeLayout, new SplashADListener(this) {
            final /* synthetic */ HomeGroup a;

            {
                this.a = r1;
            }

            public void onNoAD(int i) {
                aa.a("HomeGroup", "onCreate loadAD onNoAD：" + i);
                this.a.aT = true;
                if (!this.a.aU.booleanValue()) {
                    onADDismissed();
                }
            }

            public void onADPresent(AdItem adItem) {
                aa.a("HomeGroup", "onCreate loadAD init");
                this.a.aT = true;
                if (!this.a.aU.booleanValue()) {
                    this.a.e();
                    this.a.C = true;
                }
            }

            public void onADSkip(AdItem adItem) {
                onADDismissed();
                w.a(this.a.D, false).a(adItem.getUrl());
            }

            public void onADDismissed() {
                aa.a("HomeGroup", "onCreate loadAD onADDismissed");
                this.a.aT = true;
                if (!this.a.aU.booleanValue()) {
                    this.a.getWindow().clearFlags(1024);
                    this.a.aW.setVisibility(8);
                    this.a.h();
                }
            }
        });
    }

    private void h() {
        aa.a("HomeGroup", "onCreate  hideSplashAD initHome：" + this.C);
        if (!this.C) {
            e();
        }
        f();
    }

    private void a(boolean z) {
        z = (Navigations) z.a(an.c((Activity) this, "navigations.json"), Navigations.class);
        aa.b("HomeGroup", "readFile NAVIGATION_CACHE_FILE");
        y = (Navigations) z.a(t.a(this, "navigation_cache_file.txt"), Navigations.class);
        ah.a(this.D, y);
        if (y != null && y.menus.size() >= 2) {
            z.menus.set(0, y.menus.get(0));
            z.menus.set(1, y.menus.get(1));
            z.default_menu = y.default_menu;
        }
    }

    private void i() {
        try {
            if (z != null && z.menus != null) {
                this.N.setText(((Navigation) z.menus.get(0)).name);
                this.O.setText(((Navigation) z.menus.get(1)).name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void j() {
        String q = ai.q(this);
        if (!TextUtils.isEmpty(q)) {
            ai.f(this, "");
            try {
                aa.a("HomeGroup", q);
                com.budejie.www.push.c a = com.budejie.www.push.a.a(q);
                aa.a("HomeGroup", "msg-->" + a);
                if (a == null) {
                    IxinPushReceiver.a(this);
                } else if (a instanceof com.budejie.www.push.b) {
                    IxinPushReceiver.a((Context) this, (com.budejie.www.push.b) a);
                } else if (a instanceof com.budejie.www.push.d) {
                    IxinPushReceiver.a((Context) this, (com.budejie.www.push.d) a);
                } else {
                    IxinPushReceiver.a(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void k() {
        if (an.a(this.aH)) {
            this.B.sendEmptyMessage(R$styleable.Theme_Custom_send_btn_text_color);
        }
    }

    private void l() {
        try {
            new Thread(new Runnable(this) {
                final /* synthetic */ HomeGroup a;

                {
                    this.a = r1;
                }

                public void run() {
                    f fVar = new f(this.a);
                }
            }).run();
        } catch (InternalError e) {
            MobclickAgent.onEvent((Context) this, "E07_A02", "InternalError HomeGroup updateMyTopicSendingToFail()");
        }
    }

    private void m() {
        new Thread(new Runnable(this) {
            final /* synthetic */ HomeGroup a;

            {
                this.a = r1;
            }

            public void run() {
                int memoryClass = ((ActivityManager) this.a.getSystemService("activity")).getMemoryClass();
                aa.e("wuzhenlin", "cacheSize = " + ((1048576 * memoryClass) / 8) + ", memClass = " + memoryClass);
                if (!an.i(UpdateConfig.f)) {
                    this.a.B.sendEmptyMessage(HomeGroup.c);
                } else if (!EnvironmentUtil.sdcardIsEnable()) {
                    this.a.B.sendEmptyMessage(HomeGroup.b);
                } else if (CacheManager.checkDirSpace(HomeGroup.f, HomeGroup.e, 6291456)) {
                    this.a.B.sendEmptyMessage(HomeGroup.a);
                }
            }
        }).start();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1111131 && an.a(this.ah)) {
            startActivity(new Intent(this, SuggestedFollowsActivity.class));
        }
    }

    protected void onNewIntent(Intent intent) {
        if (A != null) {
            A.a(intent, (com.sina.weibo.sdk.share.a) this);
        }
        Bundle extras = intent.getExtras();
        if (extras == null || !extras.containsKey("_weibo_resp_errcode")) {
            try {
                aa.b("HomeGroup", "onNewIntent(");
                setIntent(intent);
                this.av = intent;
                b(intent);
                j();
                this.aX = getSharedPreferences("budejie", 0);
                this.aN = this.aX.getString("h5JumpUrl", "");
                if (!this.aN.equals("")) {
                    this.aX.edit().putString("h5JumpUrl", "").commit();
                }
                aa.a("jump", "HomeGroup  onNewIntent:" + this.aN);
                c();
            } catch (Exception e) {
                MobclickAgent.onEvent(this.D, "cacheException", "HomeGroup onNewIntent:" + e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }

    private void a(CharSequence charSequence, CharSequence charSequence2) {
        try {
            Builder builder = new Builder(this);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ HomeGroup a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setTitle(charSequence);
            builder.setMessage(charSequence2);
            builder.setIcon(17301543);
            builder.create().show();
        } catch (Exception e) {
            MobclickAgent.onEvent(this.D, "cacheException", "HomeGroup showCacheNoSpaceDialog:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private void n() {
        if (af) {
            af = false;
            CommentSingleton.getInstance().Start(this, ErrorCode.SERVER_SESSIONSTATUS);
        }
    }

    private void o() {
        this.am.a((Activity) this, (com.budejie.www.f.a) this, 1111124);
    }

    private DisplayMetrics p() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private void q() {
        aa.a("HomeGroup", "initMainViews");
        a(true);
        this.aK = an.F(this);
        b(this.aK);
        this.Y = new com.budejie.www.g.b(this);
        com.budejie.www.h.b.a().a(this.aG, this.D);
        com.budejie.www.h.b.a().a(this.D);
        this.aJ = new m(this);
        this.aE = new n(this.D);
        this.aH = getSharedPreferences("weiboprefer", 0);
        this.aG = this.aH.getString("id", "");
        this.av = getIntent();
        w = (BottomRelativeLayout) findViewById(R.id.bottom_layout);
        w.setDisplayMetrics(p());
        this.ai = getLocalActivityManager();
        Intent intent = getIntent();
        this.ab = new Intent(this, PostsActivity.class);
        this.ab.putExtra("post_type", this.av.getStringExtra("post_type"));
        this.ab.putExtra("tag_all", "tag_essence");
        this.ab.putExtra("navigation_key", (Serializable) z.menus.get(0));
        this.ac = new Intent(this, PostsActivity.class);
        this.ac.putExtra("post_type", this.av.getStringExtra("post_type"));
        this.ac.putExtra("tag_all", "tag_new");
        this.ac.putExtra("navigation_key", (Serializable) z.menus.get(1));
        this.ad = new Intent(this, PlateListActivity.class);
        this.ae = new Intent(this, MyInfoActivity.class).putExtra("bundle", intent.getBundleExtra("bundle")).putExtra("jump_type", intent.getStringExtra("jump_type"));
        this.q = (LinearLayout) findViewById(R.id.bottom_frame);
        g = (FrameLayout) findViewById(R.id.container);
        this.E = (ImageView) findViewById(R.id.new_background);
        this.F = (ImageView) findViewById(R.id.duanzi_background);
        this.I = (ImageView) findViewById(R.id.sound_background);
        this.G = (ImageView) findViewById(R.id.set_background);
        this.H = (ImageView) findViewById(R.id.video_background);
        this.L = (FrameLayout) findViewById(R.id.new_layout);
        this.M = (FrameLayout) findViewById(R.id.duanzi_layout);
        this.P = (FrameLayout) findViewById(R.id.sound_layout);
        this.Q = (FrameLayout) findViewById(R.id.video_layout);
        this.R = (FrameLayout) findViewById(R.id.set_layout);
        this.N = (TextView) findViewById(R.id.tv_cate_1);
        this.O = (TextView) findViewById(R.id.tv_cate_2);
        i();
        com.budejie.www.activity.video.p.a(this, this.Q);
        this.S = (RelativeLayout) findViewById(R.id.image_layout_rl);
        this.T = (RelativeLayout) findViewById(R.id.text_layout_rl);
        this.V = (RelativeLayout) findViewById(R.id.sound_layout_rl);
        this.W = (RelativeLayout) findViewById(R.id.goods_layout_rl);
        this.U = (RelativeLayout) findViewById(R.id.myinfo_layout_rl);
        this.J = (ImageView) findViewById(R.id.melodyview);
        s();
        this.L.setOnClickListener(this);
        this.M.setOnClickListener(this);
        this.P.setOnClickListener(this);
        this.R.setOnClickListener(this);
        this.Q.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.ah = getSharedPreferences("weiboprefer", 0);
        this.al = new d(this, this);
        this.am = new com.budejie.www.http.m();
        this.ak = new p();
        this.an = new g(this.D);
        this.aj = new a(this);
        this.ao.addAction("com.android.intent.sisiter.host.refresh");
        this.ao.addAction("android.hide.sister.NOTIFYTIPS");
        this.ao.addAction("android.hide.sister.duanzi.NOTIFYTIPS");
        this.ao.addAction("android.hide.sister.apply.NOTIFYTIPS");
        this.ao.addAction("android.hide.sister.news.NOTIFYTIPS");
        this.ao.addAction("android.hide.sister.my.NOTIFYTIPS");
        this.ao.addAction("android.hide.sister.sound.NOTIFYTIPS");
        this.ao.addAction("android.hide.sister.voido.NOTIFYTIPS");
        this.ao.addAction("android.hide.sister.friend.NOTIFYTIPS");
        this.ao.addAction("android.hide.sister.my.HANDLER_HIDE_MY_REDPACKET_TIPS");
        r();
    }

    public static void a(Context context, int i) {
    }

    private void r() {
        aa.b("HomeGroup", "toWhichTag()");
        Object obj = "";
        try {
            if (this.av != null) {
                obj = this.av.getStringExtra("tag_all");
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.D, "cacheException", "toWhichTag tagTypeKey:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        try {
            if (this.av != null && "friend_type".equals(r0)) {
                aa.b("HomeGroup", "currIntent.getStringExtra(PostsActivity.TAG_TYPE_KEY) == HomeGroup.FRIEND_ID");
                a("friend_type", null);
                a(this.av);
            } else if (this.av == null || !"myinfo_type".equals(r0)) {
                String a = this.aD.g().af.a();
                aa.a("HomeGroup", "上次阅读标签：" + a);
                String key = ((TopNavigation) ((Navigation) z.menus.get(0)).submenus.get(0)).getKey();
                if (!TextUtils.isEmpty(this.aL)) {
                    a(this.aL, this.aM);
                } else if (TextUtils.isEmpty(a)) {
                    this.ab.putExtra("post_type", key);
                    a("essence_type", null);
                } else {
                    a(a, null);
                }
            } else {
                a("com.budejie.www.activity.MyInfoActivity", null);
            }
        } catch (Exception e2) {
            MobclickAgent.onEvent(this.D, "cacheException", "toWhichTag:" + e2.getLocalizedMessage());
            e2.printStackTrace();
        }
    }

    public void b(int i) {
        String str;
        String str2 = "0";
        str2 = "0";
        switch (i) {
            case 0:
                try {
                    str = z.default_menu.initial.split("_")[0];
                    str2 = z.default_menu.initial.split("_")[1];
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 3:
                str = z.default_menu.offline_day_3.split("_")[0];
                str2 = z.default_menu.offline_day_3.split("_")[1];
                break;
            case 7:
                str = z.default_menu.offline_day_7.split("_")[0];
                str2 = z.default_menu.offline_day_7.split("_")[1];
                break;
            default:
                return;
        }
        int parseInt = Integer.parseInt(str);
        int parseInt2 = Integer.parseInt(str2);
        if (parseInt == 0) {
            this.aL = "essence_type";
        } else if (1 == parseInt) {
            this.aL = "new_type";
        }
        this.aM = ((TopNavigation) ((Navigation) z.menus.get(parseInt)).submenus.get(parseInt2)).getKey();
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra("msg_wid");
        if (TextUtils.isEmpty(stringExtra)) {
            aa.e("tangjian", "BDJ_Pop_Friend :: 帖子id不存在");
        } else {
            com.budejie.www.util.a.a((Activity) this, null, stringExtra, false);
        }
    }

    private void s() {
        this.t = an.A(this);
        this.aw = an.a(this, this.S, false, 16, 4, 2, 10.0f);
        this.ax = an.a(this, this.T, false, 16, 4, 2, 10.0f);
        this.ay = an.a(this, this.V, false, 16, 4, 2, 10.0f);
        this.az = an.a(this, this.W, false, 16, 4, 2, 10.0f);
        this.aA = an.a(this, this.U, true, 16, 4, 2, 10.0f);
        this.aB = an.a(this, this.U, false, 16, 4, 2, 10.0f);
    }

    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.video_layout:
                    aa.a("HomeGroup", "点击了底部导航-动态");
                    if (this.Q.isSelected()) {
                        aa.a("HomeGroup", "video_layout.isSelected()");
                        ((com.budejie.www.f.c) this.ai.getActivity("friend_type")).a("");
                    } else {
                        a("friend_type", null);
                    }
                    MobclickAgent.onEvent((Context) this, "底部导航", "动态");
                    MobclickAgent.onEvent((Context) this, "E01-A03", "关注");
                    aa.a("HomeGroup", "点击了底部导航-动态");
                    return;
                case R.id.melodyview:
                    view.setTag(this.aD.d());
                    this.Y.a(3, null).onClick(view);
                    return;
                case R.id.new_layout:
                    MobclickAgent.onEvent((Context) this, "底部导航", "精华");
                    MobclickAgent.onEvent((Context) this, "E01-A03", "精华");
                    aa.a("HomeGroup", "点击了底部导航-精华");
                    if (this.L.isSelected()) {
                        ((com.budejie.www.f.c) this.ai.getActivity("essence_type")).a("tag_essence");
                        this.u = false;
                        return;
                    }
                    a("essence_type", null);
                    return;
                case R.id.duanzi_layout:
                    MobclickAgent.onEvent((Context) this, "底部导航", "最新");
                    MobclickAgent.onEvent((Context) this, "E01-A03", "最新");
                    aa.a("HomeGroup", "点击了底部导航-最新");
                    if (this.M.isSelected()) {
                        ((com.budejie.www.f.c) this.ai.getActivity("new_type")).a("tag_new");
                        this.u = false;
                        return;
                    }
                    a("new_type", null);
                    return;
                case R.id.sound_layout:
                    this.aI = this.aJ.e(this.aG);
                    try {
                        BudejieApplication.a.a(RequstMethod.GET, new h("http://app.spriteapp.com/setting/").toString(), new j(this).a((Context) this), this.aY);
                        av.a().a(this.ai.getCurrentActivity(), this.aI);
                    } catch (Exception e) {
                        MobclickAgent.onEvent(this.D, "cacheException", "HomeGroup onClick TougaoDialogTools:" + e.getLocalizedMessage());
                        e.printStackTrace();
                    }
                    MobclickAgent.onEvent((Context) this, "底部导航", "发帖");
                    MobclickAgent.onEvent((Context) this, "E01-A03", "发帖");
                    aa.a("HomeGroup", "点击了底部导航-发帖");
                    k.a(getApplicationContext()).k();
                    return;
                case R.id.set_layout:
                    if (an.a(this.aH)) {
                        this.B.sendEmptyMessage(723);
                    }
                    a("com.budejie.www.activity.MyInfoActivity", null);
                    MobclickAgent.onEvent((Context) this, "底部导航", "我的");
                    MobclickAgent.onEvent((Context) this, "E01-A03", "我的");
                    aa.a("HomeGroup", "点击了底部导航-我的");
                    return;
                default:
                    return;
            }
        } catch (Exception e2) {
            MobclickAgent.onEvent(this.D, "cacheException", "HomeGroup onClick:" + e2.getLocalizedMessage());
            e2.printStackTrace();
        }
        MobclickAgent.onEvent(this.D, "cacheException", "HomeGroup onClick:" + e2.getLocalizedMessage());
        e2.printStackTrace();
    }

    private void a(String str, String str2) {
        aa.b("HomeGroup", "changeTab() tabID = " + str);
        this.aS = str;
        a();
        k.a((Context) this).p();
        g.removeAllViews();
        a(this.aD.a());
        if (this.aD.a() == null || this.aD.a() == Status.end) {
            this.J.setVisibility(8);
        } else if (this.aD.a() != Status.stop || an.b) {
            this.J.setVisibility(0);
        } else {
            this.J.setVisibility(8);
        }
        if ("essence_type".equals(str) || "new_type".equals(str)) {
            PostsActivity.a = true;
        }
        if ("essence_type".equals(str)) {
            if (TextUtils.isEmpty(str2)) {
                this.ab.putExtra("post_type", this.aD.g().ag.a());
            } else {
                this.ab.putExtra("post_type", str2);
            }
            this.L.setSelected(true);
            g.addView(this.ai.startActivity("essence_type", this.ab).getDecorView());
            this.aD.g().af.a("essence_type");
        } else if ("new_type".equals(str)) {
            if (TextUtils.isEmpty(str2)) {
                this.ac.putExtra("post_type", this.aD.g().ah.a());
            } else {
                this.ac.putExtra("post_type", str2);
            }
            this.M.setSelected(true);
            g.addView(this.ai.startActivity("new_type", this.ac).getDecorView());
            this.aD.g().af.a("new_type");
        } else if ("friend_type".equals(str)) {
            this.Q.setSelected(true);
            g.addView(this.ai.startActivity("friend_type", this.ad).getDecorView());
            this.aD.g().af.a("essence_type");
        } else if ("com.budejie.www.activity.MyInfoActivity".equals(str)) {
            this.R.setSelected(true);
            g.addView(this.ai.startActivity(StatisticCodeTable.MORE, this.ae).getDecorView());
        } else {
            this.L.setSelected(true);
            g.addView(this.ai.startActivity("essence_type", this.ab).getDecorView());
            this.aD.g().af.a("essence_type");
        }
    }

    private void b(Intent intent) {
        a();
        String stringExtra = intent.getStringExtra("tag_all");
        g.removeAllViews();
        if ("tag_essence".equals(stringExtra)) {
            this.L.setSelected(true);
            this.ab.putExtra("post_type", intent.getStringExtra("post_type"));
            a(intent.putExtra("post_type", intent.getStringExtra("post_type")), "essence_type");
            g.addView(this.ai.startActivity("essence_type", this.ab).getDecorView());
            this.aD.g().af.a("essence_type");
        } else if ("tag_new".equals(stringExtra)) {
            this.M.setSelected(true);
            this.ac.putExtra("post_type", intent.getStringExtra("post_type"));
            a(intent.putExtra("post_type", intent.getStringExtra("post_type")), "new_type");
            g.addView(this.ai.startActivity("new_type", this.ac).getDecorView());
            this.aD.g().af.a("new_type");
        } else if ("myinfo_type".equals(stringExtra)) {
            this.R.setSelected(true);
            this.ae.putExtra("bundle", intent.getBundleExtra("bundle")).putExtra("jump_type", intent.getStringExtra("jump_type"));
            g.addView(this.ai.startActivity(StatisticCodeTable.MORE, this.ae).getDecorView());
        } else if ("friend_type".equals(stringExtra)) {
            this.Q.setSelected(true);
            g.addView(this.ai.startActivity("friend_type", this.ad).getDecorView());
            this.aD.g().af.a("essence_type");
            a(intent);
        } else {
            stringExtra = this.aD.g().af.a();
            if (TextUtils.isEmpty(stringExtra)) {
                this.L.setSelected(true);
                this.ab.putExtra("post_type", intent.getStringExtra("post_type"));
                a(intent.putExtra("post_type", intent.getStringExtra("post_type")), "essence_type");
                g.addView(this.ai.startActivity("essence_type", this.ab).getDecorView());
                this.aD.g().af.a("essence_type");
                return;
            }
            a(stringExtra, null);
        }
    }

    private void a(Intent intent, String str) {
        try {
            ((com.budejie.www.activity.htmlpage.b) this.ai.getActivity(str)).a(intent, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z = true;
        if (!(keyEvent.getAction() == 1 && keyEvent.getKeyCode() == 4 && k.a((Context) this).e == this && k.a((Context) this).f != null && k.a((Context) this).f.b(4))) {
            if (keyEvent.getAction() == 1 && keyEvent.getKeyCode() == 4 && this.ah.getBoolean("barrage_status", true) && k.a(this.D).j()) {
                try {
                    k.a(this.D).n();
                } catch (Exception e) {
                }
            } else {
                try {
                    int keyCode = keyEvent.getKeyCode();
                    int action = keyEvent.getAction();
                    aa.a("HomeGroup", "dispatchKeyEvent:" + keyCode + "," + action);
                    aa.a("HomeGroup", "keyboardAction:" + this.aC);
                    if (keyCode == 4 && action == 0) {
                        this.aC = action;
                    }
                    if (keyCode == 4 && action == 1 && this.aC == 0) {
                        t();
                    } else {
                        z = super.dispatchKeyEvent(keyEvent);
                    }
                } catch (IllegalStateException e2) {
                } catch (Exception e3) {
                }
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void t() {
        /*
        r4 = this;
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r2 = r4.aa;	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r0 = r0 - r2;
        r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x0021;
    L_0x000d:
        r0 = r4.D;	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r1 = 2131231248; // 0x7f080210 float:1.8078572E38 double:1.052968143E-314;
        r2 = 0;
        r0 = android.widget.Toast.makeText(r0, r1, r2);	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r0.show();	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r4.aa = r0;	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
    L_0x0020:
        return;
    L_0x0021:
        r0 = 0;
        r4.v = r0;	 Catch:{ Exception -> 0x005b, InflateException -> 0x0052, OutOfMemoryError -> 0x0059 }
        r0 = r4.D;	 Catch:{ Exception -> 0x005b, InflateException -> 0x0052, OutOfMemoryError -> 0x0059 }
        r1 = r4.aj;	 Catch:{ Exception -> 0x005b, InflateException -> 0x0052, OutOfMemoryError -> 0x0059 }
        r0.unregisterReceiver(r1);	 Catch:{ Exception -> 0x005b, InflateException -> 0x0052, OutOfMemoryError -> 0x0059 }
        r0 = r4.aD;	 Catch:{ Exception -> 0x005b, InflateException -> 0x0052, OutOfMemoryError -> 0x0059 }
        r0.e();	 Catch:{ Exception -> 0x005b, InflateException -> 0x0052, OutOfMemoryError -> 0x0059 }
    L_0x0030:
        r0 = new com.budejie.www.activity.base.a$g;	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r1 = new com.budejie.www.activity.base.a;	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r1.<init>(r4);	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r1.getClass();	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r2 = "banner_response_cache";
        r3 = 0;
        r0.<init>(r1, r2, r3);	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r1 = "";
        r0.a(r1);	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r0 = r4.D;	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r0.finish();	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        r0 = android.os.Process.myPid();	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        android.os.Process.killProcess(r0);	 Catch:{ InflateException -> 0x0052, OutOfMemoryError -> 0x0059, Exception -> 0x0054 }
        goto L_0x0020;
    L_0x0052:
        r0 = move-exception;
        goto L_0x0020;
    L_0x0054:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0020;
    L_0x0059:
        r0 = move-exception;
        goto L_0x0020;
    L_0x005b:
        r0 = move-exception;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.HomeGroup.t():void");
    }

    protected void onResume() {
        super.onResume();
        try {
            if (this.J != null) {
                this.K = (AnimationDrawable) this.J.getBackground();
            }
            this.aD.a((b) this);
            a(this.aD.a());
            registerReceiver(this.aj, this.ao);
            u();
            aa.b("HomeGroup", "resume");
            this.Y = new com.budejie.www.g.b(this);
        } catch (Exception e) {
            MobclickAgent.onEvent(this.D, "cacheException", "HomeGroup onResume:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private void u() {
        if (ai.a(this.D) == 0) {
            String p = an.p(this.D);
            if (p.equals("deep_colour")) {
                this.q.setBackgroundResource(com.budejie.www.util.j.aB);
                this.L.setBackgroundResource(R.drawable.bottom_tab_bg_deepstyle);
                this.M.setBackgroundResource(R.drawable.bottom_tab_bg_deepstyle);
                this.P.setBackgroundResource(R.drawable.bottom_tab_bg_deepstyle);
                this.Q.setBackgroundResource(R.drawable.bottom_tab_bg_deepstyle);
                this.R.setBackgroundResource(R.drawable.bottom_tab_bg_deepstyle);
            } else if (p.equals("light_colour")) {
                this.q.setBackgroundResource(com.budejie.www.util.j.aB);
                this.L.setBackgroundDrawable(null);
                this.M.setBackgroundDrawable(null);
                this.P.setBackgroundDrawable(null);
                this.Q.setBackgroundDrawable(null);
                this.R.setBackgroundDrawable(null);
            }
            this.q.setBackgroundResource(com.budejie.www.util.j.aB);
        } else {
            this.q.setBackgroundResource(com.budejie.www.util.j.aA);
            this.L.setBackgroundDrawable(null);
            this.M.setBackgroundDrawable(null);
            this.P.setBackgroundDrawable(null);
            this.Q.setBackgroundDrawable(null);
            this.R.setBackgroundDrawable(null);
        }
        this.q.requestLayout();
    }

    protected void onPause() {
        super.onPause();
        if (this.Z != null) {
            this.Z.cancel();
        }
        this.aC = -10000;
        aa.a("HomeGroup", "重新赋值keyboardAction：" + this.aC);
    }

    public void a(String str) {
        aa.b("HomeGroup", "updateSuccess result=" + str);
        aa.b("HomeGroup", "result=" + str);
        this.B.sendMessage(this.B.obtainMessage(SocketUtil.TYPEID_810, str));
    }

    private void v() {
        this.al.a(729);
    }

    public void a() {
        this.E.setVisibility(8);
        this.F.setVisibility(8);
        this.I.setVisibility(8);
        this.G.setVisibility(8);
        this.H.setVisibility(8);
        this.L.setSelected(false);
        this.M.setSelected(false);
        this.P.setSelected(false);
        this.R.setSelected(false);
        this.Q.setSelected(false);
    }

    public void onWbShareSuccess() {
        if (this.ai != null && !TextUtils.isEmpty(this.aS)) {
            Activity activity = null;
            if (this.aS.equals("essence_type")) {
                activity = this.ai.getActivity("essence_type");
            } else if (this.aS.equals("new_type")) {
                activity = this.ai.getActivity("new_type");
            }
            if (activity != null && (activity instanceof PostsActivity)) {
                ((PostsActivity) activity).onWbShareSuccess();
            }
        }
    }

    public void onWbShareCancel() {
    }

    public void onWbShareFail() {
        au.a((int) R.string.share_failed);
    }

    public void b() {
        try {
            Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "sister_request_hz_new");
            if (!TextUtils.isEmpty(configParams)) {
                this.s = (int) ((Double.parseDouble(configParams) * 60.0d) * 1000.0d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(int i, String str) {
        if (i == 1111124) {
            this.B.sendMessage(this.B.obtainMessage(950, str));
        } else if (i == 721) {
            this.B.sendMessage(this.B.obtainMessage(TbsLog.TBSLOG_CODE_SDK_THIRD_MODE, str));
        } else if (i == 722) {
            this.B.sendMessage(this.B.obtainMessage(722, str));
        } else if (i == 729) {
            com.budejie.www.activity.base.a aVar = new com.budejie.www.activity.base.a(this);
            aVar.getClass();
            new com.budejie.www.activity.base.a.g(aVar, "banner_response_cache", null).a(str);
            Activity activity = this.ai.getActivity("essence_type");
            if (activity != null) {
                ((com.budejie.www.f.c) activity).c();
            }
            activity = this.ai.getActivity("new_type");
            if (activity != null) {
                ((com.budejie.www.f.c) activity).c();
            }
        }
    }

    public void a(int i) {
        if (i == 722) {
            this.B.sendMessage(this.B.obtainMessage(722, ""));
        } else if (i != 729) {
        }
    }

    private void w() {
        try {
            if (an.a((Context) this) && an.s(this.D)) {
                an.a((Context) this, (c) this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void x() {
        if (an.a((Context) this)) {
            sendBroadcast(new Intent("com.elves.budejie.check.unsend.adrequest"));
        }
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    private void y() {
        if (this.ap == null) {
            this.ap = new Timer(true);
            if (this.aq == null) {
                this.aq = new TimerTask(this) {
                    final /* synthetic */ HomeGroup a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (an.a(this.a.D)) {
                            this.a.B.sendEmptyMessage(723);
                            long currentTimeMillis = System.currentTimeMillis();
                            long s = ai.s(this.a.D);
                            aa.b("HomeGroup", "currentTime=" + currentTimeMillis + ", syncTime=" + s + ", syncFollowFrequency=" + this.a.aF);
                            if (currentTimeMillis - s > this.a.aF) {
                                this.a.k();
                            }
                        }
                    }
                };
            }
            if (this.ap != null && this.aq != null) {
                this.ap.schedule(this.aq, 0, (long) this.s);
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        com.budejie.www.activity.base.a aVar = new com.budejie.www.activity.base.a(this);
        aVar.getClass();
        new com.budejie.www.activity.base.a.g(aVar, "banner_response_cache", null).a("");
        EventBus.getDefault().unregister(this);
    }

    public void a(Status status) {
        int i = 0;
        if (status != null) {
            switch (status) {
                case start:
                    if (this.K != null) {
                        this.K.stop();
                        this.K.start();
                        this.J.setVisibility(0);
                        return;
                    }
                    return;
                case stop:
                    if (this.K != null) {
                        this.K.stop();
                    }
                    ImageView imageView = this.J;
                    if (!an.b) {
                        i = 8;
                    }
                    imageView.setVisibility(i);
                    return;
                case end:
                    if (this.K != null) {
                        this.K.stop();
                    }
                    this.J.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    }

    public static void a(final Context context) {
        final String b = ai.b(context);
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().j(context, b), new net.tsz.afinal.a.a<String>() {
            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                super.onSuccess(str);
                try {
                    UpdateUserInfo updateUserInfo = (UpdateUserInfo) new Gson().fromJson(str, UpdateUserInfo.class);
                    ai.h(context, updateUserInfo.getIs_black_device());
                    if (!TextUtils.isEmpty(b)) {
                        new m(context).a(updateUserInfo, b);
                    }
                } catch (Exception e) {
                    aa.e("", "ljj-->" + e.toString());
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                super.onFailure(th, i, str);
                aa.e("", "ljj-->fetchDeviseStatus: " + th.toString() + "--" + str);
            }
        });
    }

    public static void a(Context context, String str) {
        if (str == null || str.length() == 0) {
            str = "0";
        }
        final i iVar = new i(context);
        h hVar = new h("http://s.budejie.com/op/adplace2");
        hVar.b().c().a("/" + str).c("0", GiftConfigUtil.SUPER_GIRL_GIFT_TAG).a();
        BudejieApplication.a.a(RequstMethod.GET, hVar.toString(), new j(context), new net.tsz.afinal.a.a<String>() {
            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("ads");
                    List arrayList = new ArrayList();
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        OperationItem operationItem = new OperationItem();
                        operationItem.order_id = com.bdj.picture.edit.util.b.a(jSONObject, "id");
                        operationItem.is_show = com.bdj.picture.edit.util.b.a(jSONObject, "is_show");
                        operationItem.show_num = com.bdj.picture.edit.util.b.a(jSONObject, "show_num");
                        operationItem.font_color = com.bdj.picture.edit.util.b.a(jSONObject, "font_color");
                        operationItem.title = com.bdj.picture.edit.util.b.a(jSONObject, "title");
                        operationItem.words = com.bdj.picture.edit.util.b.a(jSONObject, "words");
                        operationItem.buttons = com.bdj.picture.edit.util.b.a(jSONObject, "buttons");
                        operationItem.end_time = com.bdj.picture.edit.util.b.a(jSONObject, x.X);
                        operationItem.backgroud_pic = com.bdj.picture.edit.util.b.a(jSONObject, "backgroud_pic");
                        arrayList.add(operationItem);
                    }
                    if (arrayList.size() > 0) {
                        iVar.a(null, null);
                        iVar.a(arrayList);
                    }
                } catch (Exception e) {
                    aa.e("", "ljj-->fetchOperationInfo: " + e.toString());
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                super.onFailure(th, i, str);
                aa.e("", "ljj-->fetchOperationInfo: " + th.toString() + "--" + str);
            }
        });
    }
}
