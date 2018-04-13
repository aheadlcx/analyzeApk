package qsbk.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.view.View.OnTouchListener;
import cn.shuzilm.core.Main;
import com.ak.android.shell.AKAD;
import com.baidu.mobstat.StatService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import com.flurry.android.FlurryAgent;
import com.qiushibaike.statsdk.StatSDK;
import com.tencent.bugly.Bugly;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.cafe.plugin.JumpPlugin;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.provider.UserInfoProvider;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.NativeJsPluginManager;
import qsbk.app.exception.CrashHandler;
import qsbk.app.im.emotion.EmotionManager;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Article;
import qsbk.app.model.UserInfo;
import qsbk.app.nearby.api.RandomLocationMgr;
import qsbk.app.service.ReportHandler;
import qsbk.app.service.VoteHandler;
import qsbk.app.service.VoteManager;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LiveRecommendManager;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.utils.VersionUtil;
import qsbk.app.utils.VideoLoadConfig;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.widget.QiushiEmotionHandler;

public class QsbkApp extends Application {
    public static final String CONTENT_TXT_SIZE_KEY = "content_txt_size";
    public static final int DEFAULT_MIN_AUTO_DISCONNECT_TIME = 120000;
    public static final int LOAD_IMAGE_CONNECT_TIMEOUT = 12000;
    public static final int LOAD_IMAGE_READ_TIMEOUT = 40000;
    public static final OnTouchListener TouchDark = new c();
    public static final int VIDEO_IMAGE_BLUR = 32;
    private static VoteHandler a;
    public static ArrayList<String> allCollection = new ArrayList();
    private static LruCache<String, String> b = new f(2048);
    public static int brightness = -1;
    private static Random c = new Random();
    public static ArrayList<Object> currentDataSource = null;
    public static int currentSelectItem;
    public static UserInfo currentUser = null;
    private static QsbkApp d;
    public static TimeDelta delta = new TimeDelta();
    private static float e;
    private static boolean f = false;
    public static HandlerThread hThread;
    public static boolean hasVerify = false;
    public static JSONObject indexConfig = null;
    public static boolean isChange = false;
    public static boolean isEnterSingle = false;
    public static boolean isInVideoList = false;
    public static int loading_process = 0;
    public static Context mContext;
    public static Article newArticle = null;
    public static Activity register;
    public static ReportHandler reportHandler;
    public static HandlerThread reportThread;
    public static boolean reportable = false;
    public static boolean sSDcardAvaliable = true;
    public static Integer screenMode = Integer.valueOf(0);
    public static HashMap<String, String> valuesMap = null;
    public List<Activity> activityList = new LinkedList();
    private final Handler g = new Handler(Looper.getMainLooper());
    private boolean h = false;
    private int i = DEFAULT_MIN_AUTO_DISCONNECT_TIME;
    public TimeDelta startTimeDelta = new TimeDelta();
    public Bitmap waitSendBitmap = null;

    public static VoteHandler getVoteHandler() {
        initVoteHandler();
        return a;
    }

    public static void initVoteHandler() {
        if (a == null) {
            hThread = new HandlerThread("voteThread");
            hThread.start();
            a = new VoteHandler(hThread.getLooper());
            a.obtainMessage().sendToTarget();
        }
    }

    public static boolean isInConfigRatio(String str, int i) {
        JSONObject jSONObject = indexConfig;
        if (ConfigManager.getInstance().isTestOnlyChannel()) {
            i = 100;
        }
        return c.nextInt(100) <= jSONObject.optInt(str, i);
    }

    public static void reportAppInfo() {
        reportThread = new HandlerThread("reportThread");
        reportThread.start();
        reportHandler = new ReportHandler(reportThread.getLooper());
    }

    public static QsbkApp getInstance() {
        if (d == null) {
            d = new QsbkApp();
        }
        return d;
    }

    public static float getPrefContentTextSize() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(CONTENT_TXT_SIZE_KEY);
        float f = 0.0f;
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            try {
                f = Float.parseFloat(sharePreferencesValue);
            } catch (NumberFormatException e) {
            }
        }
        return f;
    }

    public static String absoluteUrlOfMediumUserIcon(String str, String str2) {
        if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str.trim()) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String str3 = (String) b.get(str);
        if (str3 != null && str3.length() > 0) {
            return str3;
        }
        if (str.length() > 4 && str.endsWith(".jpg")) {
            str = str.substring(0, str.length() - 4) + ".webp";
        }
        str3 = String.format(Constants.ARATAR_URL, new Object[]{Integer.valueOf(Integer.valueOf(str2).intValue() / 10000), str2, "thumb", str});
        b.put(str, str3);
        return str3;
    }

    public static String absoluteUrlOfLargeUserIcon(String str, String str2) {
        if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str.trim()) || TextUtils.isEmpty(str2)) {
            return null;
        }
        if (str.length() > 4 && str.endsWith(".jpg")) {
            str = str.substring(0, str.length() - 4) + ".webp";
        }
        return String.format(Constants.ARATAR_URL, new Object[]{Integer.valueOf(Integer.valueOf(str2).intValue() / 10000), str2, "medium", str});
    }

    public static String absoluteUrlOfMediumContentImage(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || "null".equalsIgnoreCase(str2.trim())) {
            return null;
        }
        if (str2.endsWith(".jpg")) {
            str2 = str2.substring(0, str2.length() - 4) + ".webp";
        }
        return String.format(Constants.CONTENT_IMAGE_URL, new Object[]{Integer.valueOf(Integer.valueOf(str).intValue() / 10000), str, "medium", str2});
    }

    public static String ManageqiushiAbsoluteUrlOfMediumContentImage(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || "null".equalsIgnoreCase(str2.trim())) {
            return null;
        }
        return String.format(Constants.CONTENT_IMAGE_URL, new Object[]{Integer.valueOf(Integer.valueOf(str).intValue() / 10000), str, "medium", str2});
    }

    public static String absoluteUrlOfSmallContentImage(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || "null".equalsIgnoreCase(str2.trim())) {
            return null;
        }
        if (str2.endsWith(".jpg")) {
            str2 = str2.substring(0, str2.length() - 4) + ".webp";
        }
        return String.format(Constants.CONTENT_IMAGE_URL, new Object[]{Integer.valueOf(Integer.valueOf(str).intValue() / 10000), str, "small", str2});
    }

    public static String absoluteUrlOfCircleWebpImage(String str, int i) {
        if (str == null) {
            return str;
        }
        if (i != 0 && System.currentTimeMillis() <= ((long) (i + 300)) * 1000) {
            return str;
        }
        int indexOf = str.indexOf("?");
        if (indexOf != -1) {
            return str.substring(0, indexOf) + ".webp" + str.substring(indexOf, str.length());
        }
        return str + ".webp";
    }

    public static String absoluteUrlOfGroupIcon(String str) {
        if (str == null || str.endsWith("/fromat/webp")) {
            return str;
        }
        return str + "/fromat/webp";
    }

    public static final boolean isHttpsEnable() {
        return TextUtils.isEmpty(SharePreferenceUtils.getSharePreferencesValue("https_disable"));
    }

    public static final void setHttpsEnable(boolean z) {
        String str = "https_disable";
        if (z) {
            SharePreferenceUtils.remove(str);
        } else {
            SharePreferenceUtils.setSharePreferencesValue(str, "1");
        }
    }

    public static final boolean checkLogin(Context context, boolean z, boolean z2) {
        boolean z3 = currentUser != null;
        if (!(z3 || !z || context == null)) {
            context.startActivity(new Intent(context, ActionBarLoginActivity.class));
        }
        return z3;
    }

    private void a() {
        this.i = SharePreferenceUtils.getSharePreferencesIntValue("auto_disconnect_time");
        this.i = Math.max(DEFAULT_MIN_AUTO_DISCONNECT_TIME, this.i);
    }

    public int getAutoDisconnectTime() {
        return Math.max(DEFAULT_MIN_AUTO_DISCONNECT_TIME, this.i);
    }

    public void setAutoDisconnectTime(int i) {
        if (i > DEFAULT_MIN_AUTO_DISCONNECT_TIME) {
            this.i = Math.max(i, this.i);
            SharePreferenceUtils.setSharePreferencesValue("auto_disconnect_time", this.i);
        }
    }

    public void loadUserInfoFromLocalPreference() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("loginUser");
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            currentUser = new UserInfo(sharePreferencesValue);
        }
    }

    public void updateCurrentUserInfo(JSONObject jSONObject) {
        currentUser = UserInfo.updateServerJson(currentUser, jSONObject);
        SharePreferenceUtils.setSharePreferencesValue("loginUser", currentUser.toString());
    }

    public void setCurrentUserToLocal() {
        if (currentUser != null) {
            String userInfo = currentUser.toString();
            LogUtil.e("保存的curretnUser:" + userInfo);
            SharePreferenceUtils.setSharePreferencesValue("loginUser", userInfo);
        }
    }

    private void b() {
        int i = 1;
        LogUtil.d("init config manager");
        ConfigManager instance = ConfigManager.getInstance();
        if ("true".equals(instance.getConfig(ConfigManager.KEY_DEBUG, Bugly.SDK_IS_DEV))) {
            DebugUtil.DEBUG = true;
            StatService.setDebugOn(true);
        } else {
            try {
                getPackageManager().getApplicationIcon("com.qsbk.crashreporter");
                DebugUtil.DEBUG = true;
                StatService.setDebugOn(true);
            } catch (NameNotFoundException e) {
            }
        }
        Logger.getInstance().setDebug(DebugUtil.DEBUG);
        String channel = instance.getChannel();
        if (!TextUtils.isEmpty(channel)) {
            StatService.setAppChannel(mContext, channel, true);
            LogUtil.d("set channel:" + channel);
        }
        FlurryAgent.setReportLocation(false);
        VersionUtil.initLocalVersion(mContext);
        StatSDK.init("qb001", this);
        StatSDK.setDebug(DebugUtil.DEBUG);
        StatSDK.setAppInfo(Constants.localVersionName, channel, DeviceUtils.getAndroidId());
        AppUtils.init(this, 1, "20d33e4b144342b2a3553139c82a0539", channel);
        AppUtils.getInstance().setUserInfoProvider(new g(this));
        AppUtils.getInstance().setImageProvider(new j(this));
        AppUtils.getInstance().loadConfigInfo();
        UserInfoProvider userInfoProvider = AppUtils.getInstance().getUserInfoProvider();
        if (!UIHelper.isNightTheme()) {
            i = 0;
        }
        Object taburl = userInfoProvider.getTaburl(i);
        if (!TextUtils.isEmpty(taburl)) {
            Fresco.getImagePipeline().prefetchToBitmapCache(ImageRequest.fromUri(Uri.parse(taburl)), null);
        }
        NativeJsPluginManager.getInstance().registerPlugin("jump", JumpPlugin.class);
    }

    public void onCreate() {
        try {
            Class.forName("qsbk.app.core.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("qsbk.app.utils.HttpClient");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        c();
        super.onCreate();
        d = this;
        mContext = getApplicationContext();
        if (g()) {
            FrescoImageloader.init(this);
            if (UIHelper.isNightTheme()) {
                setTheme(R.style.Night);
            } else {
                setTheme(R.style.Day);
            }
            StatService.start(this);
            EmotionManager.getInstance().init(this);
            QiushiEmotionHandler.getInstance().init(this);
            RandomLocationMgr.getInstance();
            b();
        }
        Main.go(getApplicationContext(), ConfigManager.getInstance().getChannel(), null);
        f();
        initDelayed();
        AppContext.setAppContext(this);
        this.h = l();
        AppStat.setAppStartTime();
    }

    private void c() {
        if (g()) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new k(this));
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        try {
            FrescoImageloader.clearAllMemoryCaches();
        } catch (Throwable th) {
        }
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        try {
            FrescoImageloader.onTrimMemory(i);
        } catch (Throwable th) {
        }
    }

    public void onTerminate() {
        super.onTerminate();
    }

    private void d() {
        WXAPIFactory.createWXAPI(this, Constants.APP_ID, false).registerApp(Constants.APP_ID);
    }

    private void e() {
        this.g.postDelayed(new l(this), 15000);
    }

    public void initDelayed() {
        if (g()) {
            int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                Util.statusBarHeight = getResources().getDimensionPixelSize(identifier);
            } else {
                Util.statusBarHeight = 23;
            }
            Util.density = getResources().getDisplayMetrics().density;
            Util.checkDisplaySize(this);
            a(true);
            j();
            loadUserInfoFromLocalPreference();
            e();
            k();
            a();
            d();
            initConfig();
            h();
            initRemarkManager();
            initLiveManager();
        }
    }

    private void f() {
        AKAD.initSdk(this, false, false);
    }

    public void initLiveManager() {
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.init();
        }
    }

    public void initRemarkManager() {
        RemarkManager remarkManager = RemarkManager.getRemarkManager();
        if (remarkManager != null) {
            remarkManager.init();
        }
    }

    private boolean g() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
        String packageName = getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && packageName.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public void initConfig() {
        if (indexConfig == null) {
            Object readStream;
            try {
                readStream = HttpClient.readStream(getAssets().open("default_cfg"));
                if (!TextUtils.isEmpty(readStream)) {
                    indexConfig = new JSONObject(readStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            readStream = SharePreferenceUtils.getSharePreferencesValue("config");
            if (!TextUtils.isEmpty(readStream)) {
                if (indexConfig == null) {
                    indexConfig = new JSONObject();
                }
                try {
                    JSONObject jSONObject = new JSONObject(readStream);
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        indexConfig.put(str, jSONObject.opt(str));
                    }
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private void h() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new m(this));
    }

    public int getLaunchedCount() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("launchedCount");
        if (TextUtils.isEmpty(sharePreferencesValue) || !TextUtils.isDigitsOnly(sharePreferencesValue)) {
            return 0;
        }
        int parseInt = Integer.parseInt(sharePreferencesValue);
        if (parseInt < 0) {
            return 0;
        }
        return parseInt;
    }

    private void i() {
        SharePreferenceUtils.setSharePreferencesValue("launchedCount", (getLaunchedCount() + 1) + "");
    }

    private void a(boolean z) {
        CrashHandler.getInstance().init(getApplicationContext());
    }

    private void j() {
        TimeDelta timeDelta = new TimeDelta();
        VoteManager.getInstance().setInterceptor(new n(this));
        LogUtil.d("get vote td:" + timeDelta.getDelta());
        LogUtil.d("本地待发送投票数：" + VoteManager.getInstance().getWaiteSendVotes().size());
    }

    public void addActivity(Activity activity) {
        if (!this.activityList.contains(activity)) {
            this.activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (!this.activityList.contains(activity)) {
            this.activityList.remove(activity);
        }
    }

    public boolean isStart(Activity activity) {
        return this.activityList.contains(activity);
    }

    public void exit() {
        for (Activity finish : this.activityList) {
            finish.finish();
        }
        this.activityList.clear();
        System.exit(0);
    }

    public Bitmap getWaitSendBitmap() {
        return this.waitSendBitmap;
    }

    public void setWaitSendBitmap(Bitmap bitmap) {
        this.waitSendBitmap = bitmap;
    }

    private void k() {
        float prefContentTextSize = getPrefContentTextSize();
        if (prefContentTextSize == 0.0f) {
            try {
                prefContentTextSize = getResources().getDimension(R.dimen.content) / getResources().getDisplayMetrics().scaledDensity;
            } catch (NullPointerException e) {
            }
        }
        e = prefContentTextSize;
    }

    public float getCurrentContentTextSize() {
        return e;
    }

    public boolean setContentTextSize(float f) {
        if (f != getCurrentContentTextSize()) {
            SharePreferenceUtils.setSharePreferencesValue(CONTENT_TXT_SIZE_KEY, f + "");
            e = f;
            f = true;
        }
        return f;
    }

    public boolean hasContentTextSizeChange() {
        boolean z = f;
        f = false;
        return z;
    }

    public void gotoMarket(Context context) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=qsbk.app")));
            SharePreferenceUtils.setSharePreferencesValue("isRated", "true");
        } catch (ActivityNotFoundException e) {
            ToastAndDialog.makeNeutralToast(context, "感谢您的支持, 我们会更加努力.", Integer.valueOf(0)).show();
        }
    }

    public boolean isMeizuVersion() {
        return this.h;
    }

    private boolean l() {
        try {
            return ((Boolean) Class.forName("android.os.Build").getMethod("hasSmartBar", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e) {
            if (Build.DEVICE.equals("mx2")) {
                return true;
            }
            if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) {
                return false;
            }
            return false;
        }
    }

    public boolean isAutoPlayVideo() {
        return isAutoPlayConfiged() && MainActivity.mInstance != null && MainActivity.mInstance.isInVideoTab();
    }

    public boolean isAutoPlayConfiged() {
        return VideoLoadConfig.isAutoPlay(mContext);
    }

    public void checkAndGotoMarketIfNecessary(BaseActionBarActivity baseActionBarActivity, boolean z) throws ActivityNotFoundException {
        if (baseActionBarActivity != null && !baseActionBarActivity.isFinishing()) {
            if (z) {
                gotoMarket(baseActionBarActivity);
                return;
            }
            if ((!TextUtils.isEmpty(SharePreferenceUtils.getSharePreferencesValue("isRated")) ? 1 : null) == null) {
                int launchedCount = getInstance().getLaunchedCount();
                if (launchedCount == 8 || launchedCount == 18 || launchedCount == 33) {
                    new Builder(baseActionBarActivity).setTitle("请支持我们").setMessage("您的支持是我们的动力，糗友们求给个好评鼓励下！").setNegativeButton("果断给好评", new e(this, baseActionBarActivity)).setPositiveButton("有意见要说", new d(this, baseActionBarActivity)).setNeutralButton("稍后", new o(this)).show();
                }
            }
        }
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
