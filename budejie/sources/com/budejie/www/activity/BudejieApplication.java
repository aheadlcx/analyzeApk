package com.budejie.www.activity;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.android.TtgSource;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.ManifestUtil;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.multidex.LoadDexActivity;
import com.budejie.www.push.f;
import com.budejie.www.util.an;
import com.budejie.www.util.o;
import com.nostra13.universalimageloader.a.a.b.c;
import com.nostra13.universalimageloader.core.d;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sprite.ads.SpriteAD;
import com.sprite.ads.SpriteInitiator;
import com.sprite.ads.SpriteInitiator.Param;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.QbSdk.PreInitCallback;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.UMAnalyticsConfig;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import org.json.JSONObject;

public class BudejieApplication extends Application {
    public static NetWorkUtil a;
    public static String c = "";
    public static String d = "";
    public static String e = "";
    public static String f = "";
    public static Context g;
    public static int h;
    public static int i;
    private static BudejieApplication o;
    public String[] b;
    private a j = new a(this);
    private com.budejie.www.service.MediaPlayerServer.a k;
    private com.budejie.www.activity.base.a l;
    private HashMap<String, PostsActivity> m = new HashMap();
    private b n;
    private ListItemObject p;
    private Status q;
    private boolean r;
    private String s;
    private SharedPreferences t;

    public enum Status {
        start,
        playing,
        stop,
        end
    }

    class a implements ServiceConnection {
        final /* synthetic */ BudejieApplication a;

        a(BudejieApplication budejieApplication) {
            this.a = budejieApplication;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder instanceof com.budejie.www.service.MediaPlayerServer.a) {
                this.a.k = (com.budejie.www.service.MediaPlayerServer.a) iBinder;
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public interface b {
        void a(Status status);
    }

    public Status a() {
        return this.q;
    }

    public void a(Status status) {
        try {
            this.q = status;
            this.n.a(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(b bVar) {
        this.n = bVar;
    }

    public static BudejieApplication b() {
        if (o == null) {
            o = new BudejieApplication();
        }
        return o;
    }

    public BudejieApplication() {
        o = this;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Log.d("start_test", "+++++++BudejieApplication.attachBaseContext");
        if (!c() && VERSION.SDK_INT < 21) {
            if (!"360zhushou".equals("xiaomi") && f(context)) {
                a(context);
            }
            MultiDex.install(this);
            Log.d("start_test", "-----------BudejieApplication.attachBaseContext");
        }
    }

    public boolean c() {
        String d = d(this);
        if (d == null || !d.contains(":mini")) {
            return false;
        }
        Log.d("loadDex", ":mini start!");
        return true;
    }

    public void a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.budejie.www", LoadDexActivity.class.getName()));
        intent.addFlags(268435456);
        context.startActivity(intent);
        long currentTimeMillis = System.currentTimeMillis();
        long j = 10000;
        if (VERSION.SDK_INT < 12) {
            j = 20000;
        }
        while (f(context)) {
            try {
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                Log.d("loadDex", "wait ms :" + currentTimeMillis2);
                if (currentTimeMillis2 < j) {
                    Thread.sleep(200);
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean f(Context context) {
        String g = g(context);
        Log.d("loadDex", "dex2-sha1 " + g);
        return !context.getSharedPreferences(b(context).versionName, 4).getString("dex2-SHA1-Digest", "").equals(g);
    }

    public static PackageInfo b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            return new PackageInfo();
        }
    }

    public void c(Context context) {
        context.getSharedPreferences(b(context).versionName, 4).edit().putString("dex2-SHA1-Digest", g(context)).commit();
    }

    private String g(Context context) {
        try {
            return ((Attributes) new JarFile(context.getApplicationInfo().sourceDir).getManifest().getEntries().get("classes2.dex")).getValue("SHA1-Digest");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onCreate() {
        super.onCreate();
        Log.d("start_test", "+++++++BudejieApplication.onCreate1");
        if (!c()) {
            Log.d("start_test", "+++++++BudejieApplication.onCreate");
            g = getApplicationContext();
            h();
            List arrayList = new ArrayList();
            arrayList.add(new Param(SpriteInitiator.QH_INITIATOR, ""));
            SpriteAD.applicationInit(this, arrayList);
            o.a().a((Context) this);
            j();
            a = new NetWorkUtil(this);
            this.l = new com.budejie.www.activity.base.a(this);
            e(g);
            an.b = false;
            h = an.w(g);
            i = com.budejie.www.activity.video.a.b(g);
            TtgSDK.setIsDebug(false);
            TtgConfig.getInstance().getTitleBar().setTitleCenter(true).setTitle("败家姐精选特惠").setTitleColor(getResources().getColor(R.color.white)).setIconColor(getResources().getColor(R.color.white)).setBackIconShown(true).setBackIconColor(getResources().getColor(R.color.white)).setSearchShown(true).setSearchColor(getResources().getColor(R.color.white)).setBgColor(getResources().getColor(R.color.main_red)).setStatusBarBgColor(getResources().getColor(R.color.main_red)).setHeight(43);
            TtgSDK.init(o, TtgSource.BAISI);
            com.d.a.a(getApplicationContext());
            MobclickAgent.startWithConfigure(new UMAnalyticsConfig(getApplicationContext(), f.a(getApplicationContext(), "UMENG_APPKEY"), f.a(getApplicationContext(), ManifestUtil.CHANNEL)));
            com.microquation.linkedme.android.a.a((Context) this).c();
            com.b.a aVar = new com.b.a();
            aVar.a(getApplicationContext());
            aVar.a();
            com.sina.weibo.sdk.b.a(this, new AuthInfo(this, "4130757738", "http://admin.spriteapp.com/weibo/response.php", CommonStrs.SCOPE));
        }
    }

    public static String d(Context context) {
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public void e(Context context) {
        d.a().a(new com.nostra13.universalimageloader.core.e.a(context).b(5).a(new com.budejie.www.e.a(g)).a(new c()).a(4).a(new com.nostra13.universalimageloader.a.b.a.a.c(AsyncImageView.CACHESIZE)).c(AsyncImageView.CACHESIZE).a(new com.nostra13.universalimageloader.a.a.a.a.b(AsyncImageView.mImageDir, com.nostra13.universalimageloader.core.a.b(), 209715200)).a(new com.budejie.www.e.f(false)).c());
        com.nostra13.universalimageloader.b.d.a(new com.nostra13.universalimageloader.b.a(this) {
            final /* synthetic */ BudejieApplication a;

            {
                this.a = r1;
            }

            public void a(String str, Throwable th) {
                if (th instanceof SocketTimeoutException) {
                    MobclickAgent.onEvent(BudejieApplication.g, "E03-A02", "超时url：" + str + "  超时原因：" + th.getMessage());
                } else {
                    MobclickAgent.onEvent(BudejieApplication.g, "E03-A03", "出错url：" + str + "  出错原因：" + th.getMessage());
                }
            }
        });
    }

    public ListItemObject d() {
        return this.p;
    }

    public void a(ListItemObject listItemObject) {
        this.p = listItemObject;
    }

    private void j() {
        Intent intent = new Intent("com.budejie.www.mediaplayer.Server");
        intent.setClassName(this, "com.budejie.www.service.MediaPlayerServer");
        getApplicationContext().bindService(intent, this.j, 1);
        Log.i("mservice", "绑定服务");
    }

    public void e() {
        getApplicationContext().unbindService(this.j);
        Log.i("mservice", "解除服务");
    }

    public com.budejie.www.service.MediaPlayerServer.a f() {
        return this.k;
    }

    public com.budejie.www.activity.base.a g() {
        return this.l;
    }

    public void h() {
        QbSdk.initX5Environment(getApplicationContext(), new PreInitCallback(this) {
            final /* synthetic */ BudejieApplication a;

            {
                this.a = r1;
            }

            public void onViewInitFinished(boolean z) {
                Log.d("app", " onViewInitFinished is " + z);
            }

            public void onCoreInitFinished() {
                Log.d("app", " onCoreInitFinished is ");
            }
        });
    }

    public String i() {
        try {
            if (TextUtils.isEmpty(this.s) && !this.r) {
                this.t = g.getSharedPreferences("U", 0);
                this.s = this.t.getString("d", null);
                if (TextUtils.isEmpty(this.s) && an.a(g)) {
                    a.a(RequstMethod.GET, "http://d.api.budejie.com/device/activate/", j.d(g), new net.tsz.afinal.a.a<String>(this) {
                        final /* synthetic */ BudejieApplication a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onSuccess(Object obj) {
                            a((String) obj);
                        }

                        public void onStart() {
                            this.a.r = true;
                        }

                        public void a(String str) {
                            if (!TextUtils.isEmpty(str)) {
                                try {
                                    this.a.s = new JSONObject(str).optString("d");
                                    this.a.t.edit().putString("d", this.a.s);
                                    this.a.t = null;
                                    BudejieApplication.a.a("d", this.a.s);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            this.a.r = false;
                        }

                        public void onFailure(Throwable th, int i, String str) {
                            super.onFailure(th, i, str);
                            this.a.r = false;
                        }
                    });
                } else {
                    this.t = null;
                }
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(g, "cacheException", "BudejieApplication getHeaderU:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return this.s;
    }

    public void a(String str, PostsActivity postsActivity) {
        this.m.put(str, postsActivity);
    }

    public void a(String str) {
        this.m.remove(str);
    }

    public PostsActivity b(String str) {
        return (PostsActivity) this.m.get(str);
    }
}
