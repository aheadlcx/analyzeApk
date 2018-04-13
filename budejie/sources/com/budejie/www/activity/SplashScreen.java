package com.budejie.www.activity;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.R;
import com.budejie.www.activity.video.AdVideoActivity;
import com.budejie.www.bean.Navigations;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.http.j;
import com.budejie.www.util.aa;
import com.budejie.www.util.ah;
import com.budejie.www.util.ai;
import com.budejie.www.util.t;
import com.budejie.www.util.z;
import com.google.gson.Gson;
import com.tencent.stat.StatService;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.List;
import java.util.Timer;

public class SplashScreen extends QiHooActivity {
    public final String a = "navigation_cache_file.txt";
    b b;
    String d = "sprite";
    Timer e;
    public Navigations f;
    private boolean g = true;
    private int h = 1000;
    private boolean i = false;
    private RelativeLayout j;
    private TextView k;
    private SharedPreferences l;
    private Editor m;
    private Handler n = new Handler();
    private Runnable o = new Runnable(this) {
        final /* synthetic */ SplashScreen a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.i) {
                this.a.d();
            }
        }
    };
    private net.tsz.afinal.a.a<String> p = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ SplashScreen a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            aa.b("SplashScreen", "onRequestCallBack onSuccess");
            if (((Navigations) z.a(str, Navigations.class)) != null) {
                t.a(this.a, "navigation_cache_file.txt", str);
                aa.b("SplashScreen", "writeFile NAVIGATION_CACHE_FILE");
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            aa.b("SplashScreen", "onRequestCallBack onFailure");
        }
    };

    class a implements OnClickListener {
        final /* synthetic */ SplashScreen a;

        a(SplashScreen splashScreen) {
            this.a = splashScreen;
        }

        public void onClick(View view) {
            if (this.a.j != null) {
                this.a.j.setVisibility(8);
            }
            if (this.a.k != null) {
                this.a.k.setVisibility(8);
            }
            this.a.d();
        }
    }

    class b {
        public int a;
        public boolean b;
        final /* synthetic */ SplashScreen c;

        b(SplashScreen splashScreen) {
            this.c = splashScreen;
        }
    }

    public void onCreate(Bundle bundle) {
        aa.a("HomeGroup", "Splash.onCreate()");
        setTheme(16973841);
        super.onCreate(bundle);
        this.l = getSharedPreferences("budejie", 0);
        this.m = this.l.edit();
        this.c = true;
        setContentView(R.layout.splashscreen);
        this.j = (RelativeLayout) findViewById(R.id.ad_layout);
        this.k = (TextView) findViewById(R.id.clickToJumpView);
        this.k.setOnClickListener(new a(this));
        a();
        if (f()) {
            this.m.putString("startVideoVer", "3");
            this.m.commit();
            e();
        }
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "ad_splash_count_down");
        if ("".equals(configParams)) {
            this.b = b();
        } else {
            try {
                this.b = (b) new Gson().fromJson(configParams, b.class);
                if (this.b == null) {
                    this.b = b();
                }
            } catch (Exception e) {
                this.b = b();
            }
        }
        if (this.b.a <= 0 || this.b.b) {
            this.d = OnlineConfigAgent.getInstance().getConfigParams(this, "ad_splash_config");
        } else {
            this.d = OnlineConfigAgent.getInstance().getConfigParams(this, "ad_splash_config");
        }
        if (TextUtils.isEmpty(ai.q(this))) {
            c();
        } else {
            d();
        }
    }

    private void a() {
        h a = new h("http://s.budejie.com/public/list-appbar").b().a("/");
        this.f = (Navigations) z.a(t.a(this, "navigation_cache_file.txt"), Navigations.class);
        ah.a(getApplicationContext(), this.f);
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "navigation_update_now");
        if (this.f == null || Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
            BudejieApplication.a.a(RequstMethod.GET, a.toString(), new j(this), this.p);
        } else {
            BudejieApplication.a.a(RequstMethod.GET, a.toString(), new j(this), this.p);
        }
    }

    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
        MobclickAgent.onPause(this);
    }

    protected void onRestart() {
        super.onRestart();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 4) {
            this.g = false;
            finish();
        }
        return true;
    }

    private b b() {
        b bVar = new b(this);
        bVar.b = true;
        bVar.a = 5;
        return bVar;
    }

    private void c() {
        this.n.postDelayed(this.o, (long) this.h);
    }

    private boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks == null || runningTasks.size() <= 0 || !str.equals(((RunningTaskInfo) runningTasks.get(0)).topActivity.getClassName())) {
            return false;
        }
        return true;
    }

    private void d() {
        aa.a("HomeGroup", "jump()");
        if (this.e != null) {
            this.e.cancel();
        }
        if (this.g && a(this, getClass().getName())) {
            boolean booleanExtra = getIntent().getBooleanExtra("flag", false);
            Intent intent = new Intent(this, HomeGroup.class);
            intent.putExtra("flag", booleanExtra);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String string = extras.getString("jumpUrl");
                this.m.putString("h5JumpUrl", string);
                this.m.commit();
                aa.a("jump", "SplashScreen  HomeGroup:" + string);
                extras.putString("jumpUrl", string);
                intent.putExtras(extras);
            }
            startActivity(intent);
            overridePendingTransition(0, 0);
            try {
                finish();
                return;
            } catch (Exception e) {
                return;
            }
        }
        try {
            finish();
        } catch (Exception e2) {
        }
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        return super.onCreatePanelMenu(i, menu);
    }

    private void e() {
        if (this.e != null) {
            this.e.cancel();
        }
        MobclickAgent.onEvent((Context) this, "E01-A06", "视频出现次数");
        Intent intent = new Intent(this, AdVideoActivity.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String string = extras.getString("jumpUrl");
            this.m.putString("h5JumpUrl", string);
            this.m.commit();
            aa.a("jump", "SplashScreen  AdVideoActivity:" + string);
            extras.putString("jumpUrl", string);
            intent.putExtras(extras);
        }
        startActivity(intent);
        try {
            finish();
        } catch (Exception e) {
        }
    }

    private boolean f() {
        int parseInt;
        try {
            parseInt = Integer.parseInt(this.l.getString("startVideoVer", "0"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            parseInt = 0;
        }
        if (parseInt < 3) {
            return true;
        }
        return false;
    }
}
