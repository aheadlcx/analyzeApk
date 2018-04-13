package qsbk.app.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.AppStat;
import qsbk.app.AppStat.OnFpsResultListener;
import qsbk.app.BuildConfig;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.dialog.FirstInRemindDailog;
import qsbk.app.activity.publish.PublishActivity;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.cafe.plugin.JumpPlugin;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.NotificationUtils;
import qsbk.app.fragments.FoundFragment;
import qsbk.app.fragments.IArticleList;
import qsbk.app.fragments.LiveTabsFragment;
import qsbk.app.fragments.MyProfileFragment;
import qsbk.app.fragments.QiushiListFragment;
import qsbk.app.fragments.QiushiListFragment$OnViewed;
import qsbk.app.fragments.QiushiListFragment$QiushiCallback;
import qsbk.app.fragments.QiuyouCircleFragment;
import qsbk.app.guide.dialog.LoginGuideDialog;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.IMMessageListFragment;
import qsbk.app.im.IMPreConnector;
import qsbk.app.im.MessageCountManager;
import qsbk.app.im.MessageCountManager$UnreadCountListener;
import qsbk.app.im.QiuyouquanNotificationCountManager;
import qsbk.app.im.RelationshipCacheMgr;
import qsbk.app.im.TimeUtils;
import qsbk.app.im.UserChatManager;
import qsbk.app.im.datastore.ChatMsgStore;
import qsbk.app.im.datastore.ChatMsgStoreProxy;
import qsbk.app.im.datastore.Util;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.image.OkHttpDnsUtil;
import qsbk.app.live.ui.family.FamilyMessageActivity;
import qsbk.app.model.EventWindow;
import qsbk.app.model.FoundFragementItem.Duobao;
import qsbk.app.model.FoundFragementItem.FoundChicken;
import qsbk.app.model.FoundFragementItem.FoundGame;
import qsbk.app.model.Laisee;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.model.WelcomeCard;
import qsbk.app.pay.ui.PayActivity;
import qsbk.app.service.ConfigService;
import qsbk.app.service.VerifyUserInfoService;
import qsbk.app.service.VersionCheckService;
import qsbk.app.slide.SlideActivity;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpDNSManager;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.NetworkStateBroadcastReceiver;
import qsbk.app.utils.ReadCircle;
import qsbk.app.utils.ReadQiushi;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ReportCommon;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SpringFestivalUtil;
import qsbk.app.utils.TipsManager;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.utils.timer.TimerHelper;
import qsbk.app.video.VideoLoopStatistics;
import qsbk.app.widget.OnNavigationListener;
import qsbk.app.widget.QBImageView;
import qsbk.app.widget.TabPanel;
import qsbk.app.widget.TabPanel.TabBarItem;

public class MainActivity extends BaseActionBarActivity implements OnSharedPreferenceChangeListener, OnFpsResultListener, QiushiListFragment$OnViewed, QiushiListFragment$QiushiCallback, MessageCountManager$UnreadCountListener {
    public static final String ACTION_FOUND_HIDE_TIPS = "action_found_hied_tips";
    public static final String ACTION_FOUND_STATUS_CHANGE = "action_found_status_change";
    public static final String ACTION_NEW_FANS = "action_new_fans";
    public static final String ACTION_NEW_INTENT = "action_new_intent";
    public static final String ACTION_QB_LOGIN = "action_q_login";
    public static final String ACTION_QB_LOGOUT = "action_qb_logtou";
    public static final String ACTION_RECEIVE_IM_MSG = "action_receive_im_messages";
    public static final String ACTION_RECEIVE_MEDAL_MSG = "action_receive_medal_messages";
    public static final String ACTION_UPDATE = "action_update";
    public static final String ACTION_VERIFY = "action_verify";
    public static final String ACT_ACTION = "act_action";
    public static final String CLEAR_FAMILY_MESSAGE = "clear_family_message";
    public static final String CLEAR_MESSAGE_ID = "clear_message_id";
    public static final String DUOBAO_HAVE_REFRESH = "duobao_have_refresh";
    public static final String FOUND_HAVE_REFRESH = "found_have_refresh";
    public static final String FROM_SPLASH = "from_splash";
    public static final String GOTO_NEWS = "goto_news";
    public static final String GO_FAMILY_MESSAGE = "go_family_message";
    public static final long INTER_TIME_FOR_LIVE = 259200000;
    public static final String LAST_TIME_CLICK_LIVE = "_last_time_click_live";
    public static final String NEED_SHOW_REMIX_OR_CHECKEN = "_need_show_remix_or_checken";
    public static final String PUSH_CONFIG_CHECK_TIME = "push_config_check_time";
    public static final int REQUEST_CODE_LOGIN = 88;
    public static final String SECEND_LAYER_TAB_INDEX = "second_layer_tab_index";
    public static final String SELECTED_PAGE_ID = "selected_page";
    public static final String SELECTED_TAB_ID = "selected_tab";
    public static final String SHOWED_LIVE_TIP = "_has_showed_live_tip";
    public static final String SHOW_QSJX_ARTICLE = "qsjx_article_need_show";
    public static final String SHOW_QSJX_ARTICLES = "qsjx_articles_need_show";
    public static final String SHOW_QSJX_ARTICLE_FIRST_OPEN = "qsjx_article_need_show_first_open";
    public static final String TAB_LIVE_ID = "tab_live";
    public static final String TAB_MESSAGE_ID = "tab_message";
    public static final String TAB_MIME_ID = "tab_mime";
    public static final String TAB_NEARBY_ID = "tab_nearby";
    public static final String TAB_QIUSHI_ID = "tab_qiushi";
    public static final String TAB_QIUYOUCIRCLE_ID = "tab_qiuyoucircle";
    public static boolean hasShowMarket = false;
    private static final String[] j = new String[]{TAB_QIUSHI_ID, TAB_QIUYOUCIRCLE_ID, TAB_LIVE_ID, TAB_MESSAGE_ID, TAB_MIME_ID};
    private static final Class[] k = new Class[]{QiushiListFragment.class, QiuyouCircleFragment.class, LiveTabsFragment.class, IMMessageListFragment.class, MyProfileFragment.class};
    private static final String l = MainActivity.class.getSimpleName();
    private static Boolean m = Boolean.valueOf(false);
    public static MainActivity mInstance = null;
    private static Boolean n = Boolean.valueOf(false);
    public static boolean needShowRemixOrChecken;
    public static long oldFoundChickenTimestamp;
    public static long oldFoundGameTimestamp;
    private MainActivity$a A;
    private MainActivity$c B;
    private long C;
    private boolean D;
    private final BroadcastReceiver E = new sy(this);
    private final BroadcastReceiver F = new rc(this);
    private boolean G;
    private TimerHelper H;
    private String I;
    private Fragment J;
    private int K;
    private List<MainActivity$onKeyDownListener> O = new ArrayList();
    private MainActivity$ShowTipsRunnable P = new MainActivity$ShowTipsRunnable(this);
    private final BroadcastReceiver Q = new rd(this);
    private final BroadcastReceiver R = new re(this);
    private final BroadcastReceiver S = new rf(this);
    private LocalBroadcastManager T;
    private final BroadcastReceiver U = new rh(this);
    private FragmentManager V;
    protected TabPanel a;
    boolean b = false;
    boolean c = false;
    boolean d = false;
    public boolean duobaoHavaRefresh;
    String e;
    Laisee f;
    public boolean foundHaveRefresh;
    public long found_interval;
    public long found_timestamp;
    Handler g = new Handler();
    Runnable h = new sv(this);
    public boolean hasClickMyProfileFragment = false;
    BroadcastReceiver i = new sw(this);
    private final NetworkStateBroadcastReceiver o = new NetworkStateBroadcastReceiver(this, null);
    private final BroadcastReceiver p = new rb(this);
    private final BroadcastReceiver q = new rn(this);
    private QBImageView r;
    private final BroadcastReceiver s = new ry(this);
    private final BroadcastReceiver t = new sj(this);
    private final BroadcastReceiver u = new su(this);
    private MessageCountManager v = null;
    private final BroadcastReceiver w = new sx(this);
    private IArticleList x;
    private boolean y = true;
    private MainActivity$b z;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected void c_() {
        setTheme(UIHelper.isNightTheme() ? R.style.Night.MainActivity : R.style.Day.MainActivity);
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AppStat.main_on_create_start_delta = QsbkApp.delta.getDelta();
        if (QsbkApp.isInConfigRatio("main_fps_ratio", 0)) {
            AppStat.startChoreographerCallback(12, this);
        }
        if (bundle == null && getIntent().getBooleanExtra(FROM_SPLASH, false)) {
            this.y = true;
        }
        if (bundle != null) {
            bundle.remove("android:support:fragments");
        }
        v();
        m();
        initDelayed();
        mInstance = this;
        this.T = LocalBroadcastManager.getInstance(this);
        this.T.registerReceiver(this.p, new IntentFilter(ACTION_UPDATE));
        this.T.registerReceiver(this.R, new IntentFilter(ACTION_RECEIVE_IM_MSG));
        this.T.registerReceiver(this.Q, new IntentFilter(ACTION_RECEIVE_MEDAL_MSG));
        this.T.registerReceiver(this.s, new IntentFilter(ACTION_FOUND_STATUS_CHANGE));
        this.T.registerReceiver(this.w, new IntentFilter(ACTION_QB_LOGOUT));
        this.T.registerReceiver(this.q, new IntentFilter(ACTION_VERIFY));
        this.T.registerReceiver(this.u, new IntentFilter(TipsManager.SHOW_SECURITY_BIND));
        this.T.registerReceiver(this.U, new IntentFilter(ACTION_QB_LOGIN));
        this.T.registerReceiver(this.S, new IntentFilter(ACTION_NEW_FANS));
        this.T.registerReceiver(this.t, new IntentFilter(ACTION_FOUND_HIDE_TIPS));
        this.T.registerReceiver(this.E, new IntentFilter(Constants.ACTION_SPRING_FESTIVAL_REMIND));
        this.T.registerReceiver(this.F, new IntentFilter(Constants.ACTION_POP_LAISEE_WINDOW));
        this.z = new MainActivity$b(this, null);
        this.A = new MainActivity$a(this, null);
        this.T.registerReceiver(this.z, new IntentFilter("_KEY_PUBLISH_ARTICLE_SUCC_"));
        this.T.registerReceiver(this.A, new IntentFilter("_KEY_PUBLISH_ARTICLE_FAILED_"));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.i, new IntentFilter(Constants.ACTION_LIVE_BEGIN));
        new IMPreConnector().preConnect("onMainActivity");
        HttpDNSManager.instance().onCreate(this);
        AppStat.main_on_create_end_delta = QsbkApp.delta.getDelta();
        this.hasClickMyProfileFragment = SharePreferenceUtils.getSharePreferencesBoolValue("has_click_my_profile_fragment");
        this.foundHaveRefresh = SharePreferenceUtils.getSharePreferencesBoolValue(FOUND_HAVE_REFRESH);
        this.duobaoHavaRefresh = SharePreferenceUtils.getSharePreferencesBoolValue(DUOBAO_HAVE_REFRESH);
        needShowRemixOrChecken = SharePreferenceUtils.getSharePreferencesBoolValue(NEED_SHOW_REMIX_OR_CHECKEN);
        this.C = SharePreferenceUtils.getSharePreferencesLongValue(LAST_TIME_CLICK_LIVE);
        long time = new Date().getTime();
        if (!SharePreferenceUtils.getSharePreferencesBoolValue(SHOWED_LIVE_TIP) || time - this.C >= 259200000) {
            getMainUIHandler().post(new ri(this));
        }
        SharePreferenceUtils.setSharePreferencesValue(NEED_SHOW_REMIX_OR_CHECKEN, true);
        i();
        a(getIntent());
        g();
        SharePreferenceUtils.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        t();
    }

    public void checkLiveBeginUnread() {
        if (QsbkApp.currentUser != null && ChatMsgStore.getChatMsgStore(QsbkApp.currentUser.userId).getTotalLiveBeginUnreadCount() > 0) {
            setSmallTips(TAB_LIVE_ID);
        }
        long sharePreferencesLongValue = SharePreferenceUtils.getSharePreferencesLongValue(LiveTabsFragment.SP_LAST_LIVE_CLICK);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(sharePreferencesLongValue);
        Calendar instance2 = Calendar.getInstance();
        Object obj = 1;
        if (TimeUtils.isSameYear(instance, instance2) && instance2.get(6) - 7 < instance.get(6)) {
            obj = null;
        }
        if (obj != null) {
            setSmallTips(TAB_LIVE_ID);
        }
    }

    private void g() {
        EventWindow.fetchEventConfig();
    }

    private boolean a(Intent intent) {
        boolean z;
        this.c = intent.getBooleanExtra(GO_FAMILY_MESSAGE, false);
        this.d = intent.getBooleanExtra(CLEAR_FAMILY_MESSAGE, false);
        if (this.c) {
            z = true;
        } else {
            z = false;
        }
        if (this.d) {
            this.e = intent.getStringExtra(CLEAR_MESSAGE_ID);
            if (!TextUtils.isEmpty(this.e)) {
                a(this.e);
            }
        }
        if (this.c) {
            startActivity(new Intent(this, FamilyMessageActivity.class));
            this.c = false;
        }
        return z;
    }

    private void a(String str) {
        if (QsbkApp.currentUser != null) {
            Util.imStorageQueue.postRunnable(new rj(this, ChatMsgStoreProxy.newInstance(QsbkApp.currentUser.userId, 0), str));
        }
    }

    private void i() {
        int i = 1;
        if (QsbkApp.currentUser != null && !QsbkApp.currentUser.hasPhone() && !QsbkApp.currentUser.isBindSocail() && !QsbkApp.currentUser.isBindAndVerifyEmail()) {
            long sharePreferencesLongValue = SharePreferenceUtils.getSharePreferencesLongValue("security_check_time" + QsbkApp.currentUser.userId);
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(sharePreferencesLongValue);
            Calendar instance2 = Calendar.getInstance();
            if (!(instance.get(1) == instance2.get(1) && instance.get(6) == instance2.get(6))) {
                i = 0;
            }
            if (i == 0) {
                new Builder(this).setMessage("您的账号安全系数低，请绑定手机").setPositiveButton("立即绑定", new rk(this)).setNegativeButton("取消", null).create().show();
                SharePreferenceUtils.setSharePreferencesValue("security_check_time" + QsbkApp.currentUser.userId, System.currentTimeMillis());
            }
        }
    }

    private MessageCountManager j() {
        if (this.v == null && QsbkApp.currentUser != null) {
            this.v = MessageCountManager.getMessageCountManager(QsbkApp.currentUser.userId);
        }
        return this.v;
    }

    public void initDelayed() {
        long j;
        getMainUIHandler().postDelayed(new rl(this), 3000);
        getMainUIHandler().postDelayed(new rm(this), 6000);
        getMainUIHandler().postDelayed(new ro(this), 8000);
        if (!NotificationUtils.isNotificationAllow(this)) {
            long j2 = getSharedPreferences("qiushibaike", 0).getLong(PUSH_CONFIG_CHECK_TIME, 0);
            long currentTimeMillis = System.currentTimeMillis();
            j = 259200000;
            JSONObject optJSONObject = QsbkApp.indexConfig.optJSONObject("AppPushConfig");
            if (optJSONObject != null) {
                j = optJSONObject.optLong("checkDuration", 259200000);
            }
            if (currentTimeMillis - j2 > j) {
                this.B = new MainActivity$c(this);
                getMainUIHandler().postDelayed(this.B, 60000);
            }
        }
        if (!hasShowMarket) {
            getMainUIHandler().postDelayed(new rp(this), Config.BPLUS_DELAY_TIME);
        }
        new rq(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        getMainUIHandler().postDelayed(new rr(this), 2000);
        getMainUIHandler().postDelayed(new rs(this), 1000);
        if (QsbkApp.currentUser != null) {
            QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getLastNotification();
        }
        getMainUIHandler().postDelayed(new rt(this), 2000);
        this.found_timestamp = SharePreferenceUtils.getSharePreferencesLongValue("found_timestamp");
        this.found_interval = SharePreferenceUtils.getSharePreferencesLongValue("found_interval");
        j = new Date().getTime();
        if (this.found_timestamp == 0 || (this.found_timestamp > 0 && j - this.found_timestamp > this.found_interval * 1000)) {
            startGetFoundInfo(j);
        } else {
            getFoundInfoFromLocal();
        }
        if (QsbkApp.currentUser != null) {
            getMainUIHandler().postDelayed(new ru(this), 2000);
        }
        this.H = new TimerHelper(new rv(this), ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL, ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL);
        this.H.startTimer();
    }

    private void k() {
        if (QsbkApp.currentUser != null) {
            new SimpleHttpTask(String.format(Constants.LIVE_PERSONAL_INFO, new Object[]{QsbkApp.currentUser.userId}), new rw(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void startGetFoundInfo(long j) {
        SharePreferenceUtils.setSharePreferencesValue("found_timestamp", j);
        getFoundInfoFromLocal();
        getFoundInfoFromServer();
    }

    public void getFoundInfoFromLocal() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("found_chicken_and_game");
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            try {
                JSONObject jSONObject = new JSONObject(sharePreferencesValue);
                FoundGame foundGame = new FoundGame(jSONObject.optJSONObject(EventWindow.JUMP_GAME));
                FoundChicken foundChicken = new FoundChicken(jSONObject.optJSONObject("video"));
                Duobao duobao = new Duobao(jSONObject.optJSONObject(EventWindow.JUMP_DUOBAO));
                if (foundGame.show) {
                    oldFoundGameTimestamp = foundGame.timestamp;
                }
                if (foundChicken.show) {
                    oldFoundChickenTimestamp = foundChicken.timestamp;
                }
                FoundFragment.foundStaticGame = foundGame;
                FoundFragment.foundStaticChicken = foundChicken;
                MyProfileFragment.duobao = duobao;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void getFoundInfoFromServer() {
        new SimpleHttpTask(Constants.FOUND_GET_GAME_AND_CHICKEN, new rx(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void startMyService() {
        LogUtil.d("start my service");
        new rz(this, "qbk-TopAct").start();
    }

    public void startVerifyService() {
        try {
            startService(new Intent(this, VerifyUserInfoService.class));
            DebugUtil.debug("启动用户验证服务");
        } catch (SecurityException e) {
        }
    }

    public void startVersionService() {
        try {
            startService(new Intent(this, VersionCheckService.class));
        } catch (SecurityException e) {
        }
    }

    private void l() {
        try {
            startService(new Intent(this, ConfigService.class));
        } catch (SecurityException e) {
        }
    }

    public void gotoTab(String str, int i) {
        if (selecteTab(str)) {
            Fragment fragment = this.J;
            if (fragment != null && (fragment instanceof OnNavigationListener)) {
                ((OnNavigationListener) fragment).navigateTo(i);
            }
        }
    }

    public boolean selecteTab(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(this.I, str)) {
            return false;
        }
        this.I = str;
        this.a.setSelectedTab(str);
        return true;
    }

    public Fragment getCurrentFragment() {
        return this.J;
    }

    public void refreshCurrentTab() {
        if (this.a != null) {
            this.a.setSelectedTab(this.I);
        }
    }

    public void goTab(String str) {
        if (this.a != null) {
            this.a.setSelectedTab(str);
        }
    }

    private void a(String str, int i) {
        a(str, b(str), i);
    }

    private void a(String str, Class cls, int i) {
        if (cls != null && !TextUtils.isEmpty(str)) {
            if (this.V == null) {
                this.V = getSupportFragmentManager();
            }
            Fragment findFragmentByTag = this.V.findFragmentByTag(str);
            if (findFragmentByTag == null) {
                findFragmentByTag = Fragment.instantiate(this, cls.getName(), a(cls));
                this.V.beginTransaction().add(i, findFragmentByTag, str).commitAllowingStateLoss();
            }
            FragmentTransaction beginTransaction = this.V.beginTransaction();
            for (String str2 : j) {
                if (!str2.equals(str)) {
                    Fragment findFragmentByTag2 = this.V.findFragmentByTag(str2);
                    if (findFragmentByTag2 != null) {
                        findFragmentByTag2.setUserVisibleHint(false);
                        beginTransaction.hide(findFragmentByTag2);
                    }
                }
            }
            findFragmentByTag.setUserVisibleHint(true);
            beginTransaction.show(findFragmentByTag).commitAllowingStateLoss();
            this.V.executePendingTransactions();
        }
    }

    private Class b(String str) {
        int indexOf = ArrayUtils.indexOf(str, j);
        if (indexOf < 0 || indexOf >= k.length) {
            return null;
        }
        return k[indexOf];
    }

    private Bundle a(Class cls) {
        if (QiushiListFragment.class.equals(cls)) {
            return q();
        }
        return null;
    }

    private void m() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("image-reportable");
        if (!TextUtils.isEmpty(sharePreferencesValue) && sharePreferencesValue.equals("1")) {
            QsbkApp.reportable = true;
        }
    }

    @SuppressLint({"NewApi"})
    protected void onRestart() {
        super.onRestart();
        if (VERSION.SDK_INT >= 11) {
            getWindow().invalidatePanelMenu(0);
        }
    }

    protected void onResume() {
        super.onResume();
        this.D = true;
        if (AppStat.canSend() && this.y) {
            AppStat.main_on_resume_delta = QsbkApp.delta.getDelta();
            AppStat.sendStat();
        }
        if (QsbkApp.currentUser != null && j() != null) {
            getMainUIHandler().postDelayed(new sa(this), 400);
        } else if (QsbkApp.currentUser == null) {
            hideTips(TAB_MESSAGE_ID);
        }
        getMainUIHandler().postDelayed(new sb(this), 4000);
        if (QsbkApp.currentUser != null) {
        }
        if (this.foundHaveRefresh || !this.duobaoHavaRefresh) {
            getMainUIHandler().post(new sc(this));
        }
        this.b = true;
        if (getIntent().getBooleanExtra("fromSignUp", false)) {
            getMainUIHandler().post(new sd(this));
        }
        SpringFestivalUtil.checkRemind();
        int sharePreferencesIntValue = SharePreferenceUtils.getSharePreferencesIntValue("last_version_code");
        if (QsbkApp.currentUser == null || sharePreferencesIntValue >= 149) {
            this.a.hideSmallTips(TAB_MIME_ID);
        } else {
            this.a.setSmallTips(TAB_MIME_ID);
        }
        s();
        checkLiveBeginUnread();
        if (this.G) {
            EventWindow.checkWindowShow(this);
            this.G = false;
        }
    }

    protected void onPause() {
        super.onPause();
        this.D = false;
    }

    protected void onDestroy() {
        SharePreferenceUtils.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        this.o.unregister(this);
        try {
            super.onDestroy();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        ReportCommon.destroy();
        ReportArticle.destroy();
        ReadQiushi.destroy();
        ReadCircle.destroy();
        VideoLoopStatistics.getInstance().onDestroy();
        if (j() != null) {
            j().removeUnreadCountListener(this);
        }
        if (mInstance == this) {
            mInstance = null;
        }
        this.T.unregisterReceiver(this.p);
        this.T.unregisterReceiver(this.R);
        this.T.unregisterReceiver(this.Q);
        this.T.unregisterReceiver(this.s);
        this.T.unregisterReceiver(this.q);
        this.T.unregisterReceiver(this.u);
        this.T.unregisterReceiver(this.U);
        this.T.unregisterReceiver(this.S);
        this.T.unregisterReceiver(this.t);
        this.T.unregisterReceiver(this.z);
        this.T.unregisterReceiver(this.A);
        this.T.unregisterReceiver(this.E);
        this.T.unregisterReceiver(this.F);
        this.T.unregisterReceiver(this.i);
        if (QsbkApp.currentUser != null) {
            HttpDNSManager.instance().onDestroy(this);
            TipsManager.onDestroy();
        } else {
            HttpDNSManager.instance().onDestroy(this);
            TipsManager.onDestroy();
        }
        if (QsbkApp.currentUser != null) {
            RelationshipCacheMgr.getInstance(QsbkApp.currentUser.userId).onDestroy();
        }
        if (this.B != null) {
            getMainUIHandler().removeCallbacks(this.B);
        }
        OkHttpDnsUtil.getInstance().onDestroy();
        if (this.H != null) {
            this.H.stopTimer();
        }
    }

    protected String f() {
        return null;
    }

    protected int a() {
        return R.layout.main_activity;
    }

    public boolean isInVideoTab() {
        LogUtil.d("mSelectedTabId:" + this.I);
        if (TAB_QIUSHI_ID.equals(this.I)) {
            Fragment fragment = this.J;
            if (fragment != null && (fragment instanceof QiushiListFragment)) {
                LogUtil.d("isQIushiListFragment:");
                return ((QiushiListFragment) fragment).isOnVideoTab();
            }
        }
        return false;
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent, true);
    }

    public void setHighlightedTab(String str, boolean z) {
        if (this.a != null) {
            this.a.setHighlightedTab(str, z);
        }
    }

    private void a(Intent intent, boolean z) {
        if (intent != null && !a(intent)) {
            Object stringExtra = intent.getStringExtra(SELECTED_TAB_ID);
            if (intent.getBooleanExtra(GOTO_NEWS, false)) {
                selecteTab(TAB_QIUSHI_ID);
                getMainUIHandler().post(new se(this, this.J));
            } else if (intent.getBooleanExtra("fromSignUp", false)) {
                getMainUIHandler().post(new sf(this));
            } else {
                SharePreferenceUtils.setSharePreferencesValue(SHOW_QSJX_ARTICLE, intent.getBooleanExtra(SHOW_QSJX_ARTICLE, false));
                SharePreferenceUtils.setSharePreferencesValue(SHOW_QSJX_ARTICLE_FIRST_OPEN, !z);
                SharePreferenceUtils.setSharePreferencesValue(SHOW_QSJX_ARTICLES, intent.getBooleanExtra(SHOW_QSJX_ARTICLES, false));
                if (intent.getBooleanExtra(SHOW_QSJX_ARTICLE, false) || intent.getBooleanExtra(SHOW_QSJX_ARTICLES, false)) {
                    selecteTab(TAB_QIUSHI_ID);
                    getMainUIHandler().post(new sg(this, this.J));
                } else if (TextUtils.equals(intent.getStringExtra(SELECTED_TAB_ID), TAB_MESSAGE_ID)) {
                    getMainUIHandler().post(new sh(this));
                } else {
                    if (z && !TextUtils.isEmpty(stringExtra)) {
                        selecteTab(stringExtra);
                        CharSequence stringExtra2 = intent.getStringExtra(ACT_ACTION);
                        if (TextUtils.equals(JumpPlugin.TO_PUBLISH, stringExtra2)) {
                            if (QsbkApp.currentUser != null) {
                                startActivity(new Intent(this, PublishActivity.class));
                            } else {
                                startActivity(new Intent(this, ActionBarLoginActivity.class));
                            }
                        } else if (TextUtils.equals(JumpPlugin.TO_LIVE_CHARGE, stringExtra2)) {
                            Intent intent2 = new Intent();
                            intent2.setClass(this, PayActivity.class);
                            startActivityForResult(intent2, -1);
                        }
                    }
                    int intExtra = intent.getIntExtra(SECEND_LAYER_TAB_INDEX, -1);
                    if (intExtra >= 0 && !TextUtils.isEmpty(stringExtra)) {
                        gotoTab(stringExtra, intExtra);
                    }
                    Uri data = intent.getData();
                    if (data != null) {
                        RedirectActivity.navigateToActivity(this, data.toString());
                    }
                }
            }
        }
    }

    private void n() {
        this.a.addTab(new TabBarItem(TAB_QIUSHI_ID, UIHelper.getBottomTabBackgroundSelector(), UIHelper.getQiushiTabSelector(), "糗事"));
        this.a.addTab(new TabBarItem(TAB_QIUYOUCIRCLE_ID, UIHelper.getBottomTabBackgroundSelector(), UIHelper.getQiushiCircleTabSelector(), "糗友圈"));
        this.a.addTab(new TabBarItem(TAB_LIVE_ID, UIHelper.getBottomTabBackgroundSelector(), UIHelper.getLiveTabSelector(), "直播"));
        this.a.addTab(new TabBarItem(TAB_MESSAGE_ID, UIHelper.getBottomTabBackgroundSelector(), UIHelper.getMessageTabSelector(), "小纸条"));
        this.a.addTab(new TabBarItem(TAB_MIME_ID, UIHelper.getBottomTabBackgroundSelector(), UIHelper.getMineTabSelector(), "我"));
        if (getIntent() != null) {
            Object stringExtra = getIntent().getStringExtra(SELECTED_TAB_ID);
            if (!TextUtils.isEmpty(stringExtra)) {
                this.I = stringExtra;
            }
        }
        a(getIntent(), false);
        this.a.setOnTabClickListener(new si(this));
        if (TextUtils.isEmpty(this.I)) {
            this.I = TAB_QIUSHI_ID;
        }
        a(this.I, (int) R.id.container);
        this.a.setSelectedTab(this.I);
    }

    private void a(String str, Fragment fragment) {
        if (fragment != null) {
            if (TAB_QIUSHI_ID.equals(str) || TAB_QIUYOUCIRCLE_ID.equals(str) || !TAB_LIVE_ID.equals(str)) {
            }
            if (!(TAB_QIUSHI_ID.equals(str) || TAB_QIUYOUCIRCLE_ID.equals(str))) {
                QsbkApp.isInVideoList = false;
            }
            if (VERSION.SDK_INT >= 11) {
                getWindow().invalidatePanelMenu(0);
            }
            if (TAB_QIUSHI_ID.equals(str)) {
                if (TAB_QIUSHI_ID.equals(this.I)) {
                    hideSmallTips(TAB_QIUSHI_ID);
                    if (this.x != null) {
                        this.x.refresh();
                    }
                }
            } else if (TAB_QIUYOUCIRCLE_ID.equals(str)) {
                boolean z;
                if (TAB_QIUYOUCIRCLE_ID.equals(this.I)) {
                    hideSmallTips(TAB_QIUYOUCIRCLE_ID);
                    ((QiuyouCircleFragment) fragment).refresh();
                }
                if (SharePreferenceUtils.getSharePreferencesBoolValue("circle_first_in")) {
                    z = false;
                } else {
                    z = true;
                }
                SharePreferenceUtils.setSharePreferencesValue("circle_first_in", true);
                if (z) {
                    hideSmallTips(TAB_QIUYOUCIRCLE_ID);
                }
            } else if (TAB_LIVE_ID.equals(str)) {
                SharePreferenceUtils.setSharePreferencesValue(SHOWED_LIVE_TIP, true);
                SharePreferenceUtils.setSharePreferencesValue(LAST_TIME_CLICK_LIVE, new Date().getTime());
                hideSmallTips(TAB_LIVE_ID);
                if (TAB_LIVE_ID.equals(this.I)) {
                    ((LiveTabsFragment) fragment).refresh();
                }
            } else if (TAB_MESSAGE_ID.equals(str) && TAB_MESSAGE_ID.equals(this.I)) {
                ((IMMessageListFragment) fragment).scrollToNextUnreadPosition();
            }
            if (TAB_MIME_ID.equals(str)) {
                this.foundHaveRefresh = false;
                this.duobaoHavaRefresh = true;
                SharePreferenceUtils.setSharePreferencesValue(FOUND_HAVE_REFRESH, this.foundHaveRefresh);
                SharePreferenceUtils.setSharePreferencesValue(DUOBAO_HAVE_REFRESH, this.duobaoHavaRefresh);
                this.a.hideSmallTips(TAB_MIME_ID);
                if (QsbkApp.currentUser != null) {
                    SharePreferenceUtils.setSharePreferencesValue("last_version_code", (int) BuildConfig.VERSION_CODE);
                }
                this.hasClickMyProfileFragment = true;
                SharePreferenceUtils.setSharePreferencesValue("has_click_my_profile_fragment", this.hasClickMyProfileFragment);
            }
            this.I = str;
            this.J = fragment;
        }
    }

    private Bundle q() {
        Bundle bundle = new Bundle();
        if (this.K == 0) {
            this.K = getIntent().getIntExtra(SELECTED_PAGE_ID, 0);
        }
        bundle.putInt(QiushiListFragment.KEY_SELECTED_ITEM, this.K);
        return bundle;
    }

    private void r() {
        if (this.a != null) {
            this.a.setWillAutoCancelTipsWhenSelected(false);
        }
    }

    @SuppressLint({"NewApi"})
    protected void a(Bundle bundle) {
        int i;
        if (bundle != null) {
            this.I = bundle.getString("seletedId");
        }
        this.a = (TabPanel) findViewById(R.id.tabPanel);
        this.r = (QBImageView) findViewById(R.id.mid_icon);
        String taburl = AppUtils.getInstance().getUserInfoProvider().getTaburl(UIHelper.isNightTheme() ? 1 : 0);
        QBImageView qBImageView = this.r;
        if (TextUtils.isEmpty(taburl)) {
            i = 8;
        } else {
            i = 0;
        }
        qBImageView.setVisibility(i);
        if (!TextUtils.isEmpty(taburl)) {
            FrescoImageloader.displayImage(this.r, taburl, 0, 0);
        }
        r();
        n();
        this.o.register(this);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("seletedId", this.I);
    }

    public void showFirstInRemindDailog() {
        Intent intent = new Intent();
        intent.setClass(this, FirstInRemindDailog.class);
        startActivityForResult(intent, 1776);
    }

    public void setProgressBarVisible(boolean z) {
        setSupportProgressBarIndeterminateVisibility(z);
    }

    public void setTips(String str, String str2) {
        if (!TextUtils.isEmpty(str) && this.a != null) {
            this.a.setTips(str, str2);
        }
    }

    public void hideTips(String str) {
        if (!TextUtils.isEmpty(str) && this.a != null) {
            this.a.hideTips(str);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1776 && i2 == -1) {
            if (this.x != null) {
                this.x.refresh();
            }
        } else if (i == com.tencent.connect.common.Constants.REQUEST_LOGIN && i2 == -1) {
            boolean handleQQloginRequest = UserLoginGuideCard.handleQQloginRequest(i, i2, intent);
            if (!handleQQloginRequest) {
                handleQQloginRequest = LoginGuideDialog.handleQQloginRequest(i, i2, intent);
            }
            if (!handleQQloginRequest) {
                WelcomeCard.handleQQloginRequest(i, i2, intent);
            }
        }
        if (i2 == SlideActivity.MORE_CIRCLE_VIDEO) {
            this.a.setSelectedTab(TAB_QIUYOUCIRCLE_ID);
            getMainUIHandler().post(new sk(this, this.J));
        } else if (i2 == SlideActivity.MORE_NEWS) {
            this.a.setSelectedTab(TAB_QIUSHI_ID);
            getMainUIHandler().post(new sl(this, this.J));
        }
    }

    public void reload() {
        Intent intent = getIntent();
        if (!TextUtils.isEmpty(this.I)) {
            intent.putExtra(SELECTED_TAB_ID, this.I);
            intent.putExtra(SELECTED_PAGE_ID, this.K);
        }
        overridePendingTransition(0, 0);
        intent.addFlags(65536);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public void setSelected(int i) {
        if (i != this.K) {
            hideSmallTips(TAB_QIUSHI_ID);
            supportInvalidateOptionsMenu();
        }
        this.K = i;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean onKeyDown;
        for (MainActivity$onKeyDownListener mainActivity$onKeyDownListener : this.O) {
            if (!TAB_QIUSHI_ID.equalsIgnoreCase(this.I) || !(mainActivity$onKeyDownListener instanceof QiushiListFragment)) {
                if (!TAB_NEARBY_ID.equalsIgnoreCase(this.I) || !(mainActivity$onKeyDownListener instanceof FoundFragment)) {
                    if (TAB_MESSAGE_ID.equalsIgnoreCase(this.I) && (mainActivity$onKeyDownListener instanceof IMMessageListFragment)) {
                        onKeyDown = mainActivity$onKeyDownListener.onKeyDown(i, keyEvent);
                        break;
                    }
                } else {
                    onKeyDown = mainActivity$onKeyDownListener.onKeyDown(i, keyEvent);
                    break;
                }
            }
            onKeyDown = mainActivity$onKeyDownListener.onKeyDown(i, keyEvent);
            break;
        }
        onKeyDown = false;
        if (onKeyDown) {
            return onKeyDown;
        }
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (m.booleanValue()) {
            FeedsAd.getInstance().exit();
            FeedsAd.getQiuyouCircleInstance().exit();
            if (QsbkApp.currentUser != null) {
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).destroy(false);
            }
            finish();
            this.g.postDelayed(new sm(this), 800);
        } else {
            m = Boolean.valueOf(true);
            ToastAndDialog.makeNeutralToast(this, "再按一次返回键退出", Integer.valueOf(0)).show();
            if (!n.booleanValue()) {
                n = Boolean.valueOf(true);
                this.g.postDelayed(this.h, 3000);
            }
        }
        return true;
    }

    public void addOnKeyDownListener(MainActivity$onKeyDownListener mainActivity$onKeyDownListener) {
        this.O.add(mainActivity$onKeyDownListener);
    }

    public void removeOnKeyDownListener(MainActivity$onKeyDownListener mainActivity$onKeyDownListener) {
        this.O.remove(mainActivity$onKeyDownListener);
    }

    public void onFpsResult(int[] iArr) {
        if (iArr != null) {
            int i = 0;
            for (int i2 : iArr) {
                i += i2;
            }
            Arrays.sort(iArr);
            LogUtil.d("avg:" + (i / iArr.length));
            LogUtil.d("fps min 0:" + iArr[0]);
            LogUtil.d("fps min 1:" + iArr[1]);
            StatSDK.onEventDuration(QsbkApp.mContext, "stat", "main_fps_avg", (long) (i / iArr.length));
            StatSDK.onEventDuration(QsbkApp.mContext, "stat", "main_fps_min0", (long) iArr[0]);
            StatSDK.onEventDuration(QsbkApp.mContext, "stat", "main_fps_min1", (long) iArr[1]);
            StatSDK.onEventDuration(QsbkApp.mContext, "stat", "main_fps_min2", (long) iArr[2]);
            StatService.onEventDuration(QsbkApp.mContext, "stat", "main_fps_avg", (long) ((i * 1000) / iArr.length));
            StatService.onEventDuration(QsbkApp.mContext, "stat", "main_fps_min0", (long) (iArr[0] * 1000));
            StatService.onEventDuration(QsbkApp.mContext, "stat", "main_fps_min1", (long) (iArr[1] * 1000));
            StatService.onEventDuration(QsbkApp.mContext, "stat", "main_fps_min2", (long) (iArr[2] * 1000));
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (!EventWindow.SP_KEY.equals(str)) {
            return;
        }
        if (this.D) {
            EventWindow.checkWindowShow(this);
        } else {
            this.G = true;
        }
    }

    public void unread(int i, int i2) {
        Log.e(l, String.format("count :%s, unregardedCount : %s", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        this.P.setCount(i, i2);
        this.P.fire();
    }

    private void s() {
        if (this.f != null && this.f.pop) {
            LaiseeGetActivity.launch(this, this.f);
            this.f.pop = false;
            SharePreferenceUtils.setSharePreferencesValue(Laisee.SP_POP_LAISEE, this.f.toJson());
            this.f = null;
        }
    }

    private void t() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(Laisee.SP_POP_LAISEE);
        this.f = new Laisee();
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            this.f.parse(sharePreferencesValue);
        }
    }

    private void u() {
        Builder builder = new Builder(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.update_dialog_message, null);
        ((TextView) inflate.findViewById(R.id.updateMessage)).setText(Constants.change);
        builder.setView(inflate);
        builder.setTitle("新版本：" + Constants.serviceVersionName);
        builder.setNegativeButton("跳过此版本", new sp(this)).setPositiveButton("立即下载", new so(this)).setNeutralButton("稍后提醒", new sn(this));
        AlertDialog create = builder.create();
        create.setOnCancelListener(new sq(this));
        create.setCanceledOnTouchOutside(true);
        create.show();
    }

    protected boolean d() {
        return true;
    }

    private void b(Intent intent) {
        Builder builder = new Builder(this);
        builder.setTitle("温馨提示").setMessage("糟糕, 好像似乎你的登录态已经失效了,赶紧重新登录吧!").setPositiveButton("确定", new sr(this));
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        create.show();
    }

    public void onLogout() {
        if (j() != null) {
            j().removeUnreadCountListener(this);
            this.v = null;
        }
        setTips(TAB_MESSAGE_ID, "0");
        hideTips(TAB_MESSAGE_ID);
        setTips(TAB_QIUYOUCIRCLE_ID, "0");
        hideTips(TAB_QIUYOUCIRCLE_ID);
        setTips(TAB_QIUSHI_ID, "0");
        hideTips(TAB_QIUSHI_ID);
        if (this.a != null) {
            hideSmallTips(TAB_MIME_ID);
            hideTips(TAB_MIME_ID);
        }
    }

    private void v() {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_DIR");
        intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
        sendBroadcast(intent);
    }

    protected void onStop() {
        super.onStop();
        ReportArticle.save2Local();
        ReadQiushi.save2Local();
        StatSDK.forceReport(this);
        if (QsbkApp.currentUser != null) {
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).onStop();
        }
        this.b = false;
    }

    public void onNewQiushi(IArticleList iArticleList) {
        setHighlightedTab(TAB_QIUSHI_ID, true);
    }

    public void onResume(IArticleList iArticleList) {
        this.x = iArticleList;
    }

    public void setSmallTips(String str) {
        if (this.a != null) {
            this.a.setSmallTips(str);
        }
    }

    public void hideSmallTips(String str) {
        if (this.a != null) {
            this.a.hideSmallTips(str);
        }
    }

    public void requestHideSmallTips(IArticleList iArticleList) {
        if (this.x == iArticleList) {
            hideSmallTips(TAB_QIUSHI_ID);
        }
    }

    protected boolean f_() {
        return true;
    }

    public void setHasClickMyProfileFragment() {
        this.hasClickMyProfileFragment = true;
        SharePreferenceUtils.setSharePreferencesValue("has_click_my_profile_fragment", this.hasClickMyProfileFragment);
    }

    public void clearIMMessageTips() {
        hideSmallTips(TAB_MESSAGE_ID);
        hideTips(TAB_MESSAGE_ID);
    }

    public void updateIMMessageTips() {
        if (QsbkApp.currentUser != null && j() != null) {
            getMainUIHandler().postDelayed(new ss(this), 400);
        } else if (QsbkApp.currentUser == null) {
            hideSmallTips(TAB_MESSAGE_ID);
            hideTips(TAB_MESSAGE_ID);
        }
    }

    private void w() {
        Context applicationContext = getApplicationContext();
        if (!qsbk.app.utils.Util.checkSelfSign(applicationContext)) {
            StatSDK.onEvent(applicationContext, "apk_signature", "0");
            StatService.onEvent(applicationContext, "apk_signature", "0");
        }
    }

    public void onViewed() {
        if (TextUtils.equals(TAB_QIUSHI_ID, this.I)) {
            getMainUIHandler().post(new st(this));
        }
    }
}
