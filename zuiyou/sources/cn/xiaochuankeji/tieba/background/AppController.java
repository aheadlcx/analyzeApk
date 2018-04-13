package cn.xiaochuankeji.tieba.background;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import cn.htjyb.netlib.NetworkMonitor;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.aop.permission.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.a.c;
import cn.xiaochuankeji.tieba.background.modules.a.a.a;
import cn.xiaochuankeji.tieba.network.custom.b;
import cn.xiaochuankeji.tieba.network.f;
import cn.xiaochuankeji.tieba.network.filedownload.e;
import cn.xiaochuankeji.tieba.push.service.DaemonService;
import cn.xiaochuankeji.tieba.ui.videomaker.j;
import com.iflytek.cloud.SpeechUtility;
import com.tencent.bugly.beta.Beta;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.UMAnalyticsConfig;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.w;
import tv.danmaku.ijk.media.player.IjkLibLoader;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class AppController extends BaseApplication implements a {
    private static final String KEY_UPDATED_DEVICE_ID = "updated_device_id_v2";
    private static final String kCommonPreference = "common";
    public static final String kDataCacheCharset = "GBK";
    public static final Charset kDataCacheCharsetUTF8 = org.apache.commons.io.a.f;
    private static boolean mAllowCellular;
    private static AppController sInstance;
    private String _packageChannel;
    private boolean _preloaded;
    private SharedPreferences commonPreference;
    private String mDeviceID;
    private com.d.a.a mRefWatcher;

    public AppController(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
    }

    public void watch(Object obj) {
        if (this.mRefWatcher != null) {
            this.mRefWatcher.a(obj);
        }
    }

    public void onCreate() {
        getApplication().setTheme(R.style.WelcomeStyle);
        super.onCreate();
        sInstance = this;
        com.izuiyou.a.a.a.a(BaseApplication.getAppContext(), a.e().L(), "izuiyou", false);
        cn.xiaochuankeji.tieba.analyse.a.a(getApplication().getApplicationContext());
        d.a(getApplication());
        enableDebugMode();
        boolean a = cn.htjyb.c.a.a(getApplication());
        if (a) {
            a.o().a();
            cn.xiaochuankeji.tieba.common.c.a.a(getApplication(), buildHttpClient(true));
            c.b();
            cn.xiaochuankeji.tieba.push.b.a.a("cn.xiaochuankeji.tieba");
        }
        e.a(getApplication());
        cn.xiaochuan.daemon.a.a(BaseApplication.getAppContext(), DaemonService.class, Integer.valueOf(300000));
        DaemonService.b = false;
        cn.xiaochuan.daemon.a.a(DaemonService.class);
        cn.xiaochuankeji.tieba.network.c.a("http://" + cn.xiaochuankeji.tieba.background.utils.d.a.e(), buildHttpClient(false), new b(), new cn.xiaochuankeji.tieba.network.custom.c());
        cn.xiaochuankeji.tieba.background.upload.e.a("http://" + cn.xiaochuankeji.tieba.background.utils.d.a.e(), buildHttpClient(false), new b(), new cn.xiaochuankeji.tieba.network.custom.c());
        if (a) {
            c.a.c.a(getApplication()).a(new c.a.e.a.a()).a(new c.a.c.a.a()).a(new c.a.a.b()).a(false).b(false).l();
            c.a.i.b.d.a((int) R.drawable.text_cursor_drawable);
            a.g().a(this);
            com.tencent.open.utils.d.a(BaseApplication.getAppContext());
            com.tencent.open.utils.d.a(getApplication().getApplicationContext());
            IjkMediaPlayer.loadLibrariesOnce(new IjkLibLoader(this) {
                final /* synthetic */ AppController a;

                {
                    this.a = r1;
                }

                public void loadLibrary(String str) throws UnsatisfiedLinkError, SecurityException {
                    com.getkeepsafe.relinker.b.a(this.a.getApplication().getApplicationContext(), str);
                }
            });
            a.a(getApplication());
            cn.htjyb.netlib.b.a().a(cn.xiaochuankeji.tieba.network.e.a());
            initUMeng();
            cn.xiaochuankeji.tieba.ui.utils.e.a(getApplication());
            initBackground();
            NetworkMonitor.a(getApplication());
        }
    }

    @TargetApi(14)
    public void onBaseContextAttached(Context context) {
        super.onBaseContextAttached(context);
        MultiDex.install(context);
        Beta.installTinker(this);
    }

    private void initBackground() {
        a.p().d().execute(new Runnable(this) {
            final /* synthetic */ AppController a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    cn.xiaochuankeji.tieba.background.utils.c.a.c().i();
                    this.a.startDnsCheckAsyTask();
                    j.a();
                    SpeechUtility.createUtility(this.a.getApplication(), "appid=56975797");
                    cn.xiaochuankeji.tieba.background.utils.e.a(this.a.getApplication());
                    cn.xiaochuankeji.tieba.background.assessor.b.a().a(null);
                    a.b(this.a.getApplication()).a();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initUMeng() {
        MobclickAgent.startWithConfigure(new UMAnalyticsConfig(BaseApplication.getAppContext(), "54694fa0fd98c51476006477", packageChannel()));
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    public static AppController instance() {
        return sInstance;
    }

    public w buildHttpClient(boolean z) {
        w.a aVar = new w.a();
        aVar.a(new cn.xiaochuankeji.tieba.network.custom.a.c());
        aVar.a(cn.xiaochuankeji.tieba.network.custom.a.a.a());
        if (z) {
            aVar.a(new cn.xiaochuankeji.tieba.network.custom.a.b());
        } else {
            aVar.a(new cn.xiaochuankeji.tieba.network.custom.a.d());
        }
        SSLSocketFactory fVar = new f();
        aVar.a(new cn.xiaochuankeji.tieba.network.custom.a());
        try {
            aVar.a(fVar, f.a());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        aVar.a(10, TimeUnit.SECONDS).b(15, TimeUnit.SECONDS).c(15, TimeUnit.SECONDS);
        return aVar.a();
    }

    public String deviceID() {
        if (!cn.htjyb.c.d.d(this.mDeviceID)) {
            return this.mDeviceID;
        }
        this.mDeviceID = a.a().getString(KEY_UPDATED_DEVICE_ID, null);
        if (!cn.htjyb.c.d.d(this.mDeviceID)) {
            return this.mDeviceID;
        }
        this.mDeviceID = cn.xiaochuankeji.tieba.common.e.c.b(getApplication());
        return this.mDeviceID;
    }

    public boolean deviceIDUpdated() {
        return !TextUtils.isEmpty(a.a().getString(KEY_UPDATED_DEVICE_ID, null));
    }

    public void updateDeviceID(String str) {
        if (str.equals("did")) {
            this.mDeviceID = cn.xiaochuankeji.tieba.common.e.c.b(getApplication());
        } else if (str.equals("uuid")) {
            this.mDeviceID = cn.xiaochuankeji.tieba.common.e.c.a(getApplication());
        } else if (str.equals("remove")) {
            this.mDeviceID = null;
        }
        Editor edit = a.a().edit();
        if (TextUtils.isEmpty(this.mDeviceID)) {
            edit.remove(KEY_UPDATED_DEVICE_ID);
            this.mDeviceID = cn.xiaochuankeji.tieba.common.e.c.b(getApplication());
        } else {
            edit.putString(KEY_UPDATED_DEVICE_ID, this.mDeviceID);
        }
        edit.apply();
    }

    public void updateDeviceId() {
        this.mDeviceID = cn.xiaochuankeji.tieba.common.e.a.b();
    }

    public String packageChannel() {
        if (this._packageChannel != null) {
            return this._packageChannel;
        }
        try {
            this._packageChannel = com.meituan.android.walle.f.a(BaseApplication.getAppContext());
        } catch (Exception e) {
            com.izuiyou.a.a.b.e(e.toString());
        }
        if (this._packageChannel == null) {
            this._packageChannel = "none";
        }
        com.izuiyou.a.a.b.c("_packageChannel: " + this._packageChannel);
        return this._packageChannel;
    }

    private void doInit() {
        a.g().f();
        cn.htjyb.netlib.b.a().a(a.g().c());
        cn.xiaochuankeji.tieba.background.utils.d.a.d();
    }

    private void startDnsCheckAsyTask() {
        cn.xiaochuankeji.tieba.network.b.a().a(getApplication(), cn.xiaochuankeji.tieba.background.utils.c.a.c().l());
    }

    @TargetApi(16)
    private void enableDebugMode() {
        com.izuiyou.a.a.b.e("You should not call enableDebugMode() on a non debug build");
    }

    public void preloadBeforeEnterApp() {
        try {
            if (!this._preloaded) {
                doInit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this._preloaded = true;
    }

    public void initWhenEnterApp() {
        try {
            if (!this._preloaded) {
                doInit();
            }
            cn.xiaochuankeji.tieba.background.utils.c.a.c().d();
            cn.xiaochuankeji.tieba.push.d.a().c();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this._preloaded = false;
    }

    public void clearWhenExitApp() {
        cn.xiaochuankeji.tieba.d.a.c = null;
        a.r();
    }

    public SharedPreferences getCommonPreference() {
        if (this.commonPreference == null) {
            this.commonPreference = getApplication().getSharedPreferences("common", 0);
        }
        return this.commonPreference;
    }

    public boolean allowCellular() {
        return mAllowCellular;
    }

    public void setAllowCellular(boolean z) {
        mAllowCellular = z;
    }

    public void onTokenChanged() {
        cn.xiaochuankeji.tieba.push.d.a().b();
    }
}
