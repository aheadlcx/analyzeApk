package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.R;
import com.budejie.www.bean.Navigations;
import com.budejie.www.c.k;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.http.l;
import com.budejie.www.util.aa;
import com.budejie.www.util.ah;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.t;
import com.budejie.www.util.z;
import com.facebook.imagepipeline.memory.DefaultFlexByteArrayPoolParams;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.x;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.Calendar;
import net.tsz.afinal.a.a;

public class LaucherActivity extends Activity {
    public Navigations a;
    OnClickListener b = new OnClickListener(this) {
        final /* synthetic */ LaucherActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.f.setBackgroundResource(R.drawable.ad_button_pressed);
            MobclickAgent.onEvent(this.a, "E01-A06", "跳过次数");
            this.a.b();
        }
    };
    private SharedPreferences c;
    private Editor d;
    private RelativeLayout e;
    private Button f;
    private SurfaceView g;
    private MediaPlayer h;
    private RelativeLayout i;
    private boolean j = false;
    private a<String> k = new a<String>(this) {
        final /* synthetic */ LaucherActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            if (((Navigations) z.a(str, Navigations.class)) != null) {
                t.a(this.a, "navigation_cache_file.txt", str);
            }
        }

        public void onFailure(Throwable th, int i, String str) {
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        aa.b("start_test", "+++++++LaucherActivity.onCreate");
        setContentView(R.layout.activity_launcher);
        Bundle extras = getIntent().getExtras();
        if ((getIntent().getFlags() & DefaultFlexByteArrayPoolParams.DEFAULT_MAX_BYTE_ARRAY_SIZE) == 0 || extras != null) {
            if (!TextUtils.isEmpty(NetWorkUtil.b((Context) this))) {
                BudejieApplication.a.a("cookie", NetWorkUtil.b((Context) this));
            }
            String i = ((BudejieApplication) getApplication()).i();
            if (!TextUtils.isEmpty(i)) {
                BudejieApplication.a.a("d", i);
            }
            OnlineConfigAgent.getInstance().updateOnlineConfig(this);
            l.a(getApplicationContext());
            ((BudejieApplication) getApplication()).g().f.a(Boolean.valueOf(false));
            ((BudejieApplication) getApplication()).g().d.a(Boolean.valueOf(false));
            a();
            ai.a((Context) this, false);
            this.c = getSharedPreferences("budejie", 0);
            this.d = this.c.edit();
            this.e = (RelativeLayout) findViewById(R.id.splash_layout);
            if (c()) {
                this.d.putString("startVideoVer", "3");
                this.d.commit();
                d();
                e();
            } else {
                this.e.setVisibility(0);
                b();
            }
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            SharedPreferences sharedPreferences = getSharedPreferences("tip", 0);
            Editor edit = sharedPreferences.edit();
            long j = sharedPreferences.getLong(x.W, timeInMillis);
            long currentTimeMillis = System.currentTimeMillis();
            j = (currentTimeMillis - j) / com.umeng.analytics.a.i;
            Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "refresh_cache");
            k kVar = new k(getApplicationContext());
            if (sharedPreferences.getBoolean("refresh", true) || kVar.a() == 0) {
                TipPopUp.a(this);
                edit.putLong(x.W, timeInMillis);
                edit.putBoolean("refresh", false);
                edit.commit();
            }
            if (TextUtils.isEmpty(configParams)) {
                i = "3";
            } else if (j >= ((long) Integer.parseInt(configParams))) {
                TipPopUp.a(this);
                edit.putLong(x.W, currentTimeMillis);
                edit.commit();
            }
            SharedPreferences sharedPreferences2 = getSharedPreferences("weiboprefer", 0);
            boolean z = sharedPreferences2.getBoolean("barrage_status", true);
            boolean z2 = sharedPreferences2.getBoolean("barrage_multiple", false);
            i = an.a((Context) this) ? "1".equals(an.b((Context) this)) ? "WiFi" : "手机流量" : "网络不可用";
            String str = TextUtils.isEmpty(an.f((Context) this)) ? "未登陆" : ai.c(this) ? "VIP会员" : "免费会员";
            StringBuilder append = new StringBuilder().append("弹幕");
            String str2 = z ? z2 ? "多行" : "单行" : "关";
            i.a(getString(R.string.track_event_state), getString(R.string.track_event_action_state), "|" + str + "|" + append.append(str2).toString() + "|" + an.o(this));
            str = "E06_A13";
            if (z) {
                i = "开|" + (z2 ? "多行" : "单行");
            } else {
                i = "关";
            }
            MobclickAgent.onEvent((Context) this, str, i);
            aa.b("start_test", "------------LaucherActivity.onCreate");
            return;
        }
        finish();
    }

    protected void onResume() {
        try {
            if (!this.j) {
                f();
            }
        } catch (Exception e) {
        }
        this.j = false;
        super.onResume();
    }

    protected void onPause() {
        if (this.h != null && this.h.isPlaying()) {
            this.h.stop();
        }
        super.onPause();
    }

    protected void onStop() {
        this.j = true;
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            this.h.release();
            this.h = null;
        }
        setContentView(R.layout.view_null);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
            return false;
        }
        MobclickAgent.onEvent((Context) this, "E01-A06", "返回键跳过次数");
        b();
        return true;
    }

    private void a() {
        h a = new h("http://s.budejie.com/public/list-appbar").b().a("/");
        this.a = (Navigations) z.a(t.a(this, "navigation_cache_file.txt"), Navigations.class);
        ah.a(getApplicationContext(), this.a);
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "navigation_update_now");
        if (this.a == null || Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
            BudejieApplication.a.a(RequstMethod.GET, a.toString(), new j(this), this.k);
        } else {
            BudejieApplication.a.a(RequstMethod.GET, a.toString(), new j(this), this.k);
        }
    }

    private void b() {
        startActivity(new Intent(this, HomeGroup.class));
        finish();
    }

    private boolean c() {
        int parseInt;
        try {
            parseInt = Integer.parseInt(this.c.getString("startVideoVer", "0"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            parseInt = 0;
        }
        if (parseInt < 3) {
            return true;
        }
        return false;
    }

    private void d() {
        this.f = (Button) findViewById(R.id.JumpButton);
        this.g = (SurfaceView) findViewById(R.id.AdSurfaceView);
        this.i = (RelativeLayout) findViewById(R.id.advideo_layout);
        this.h = new MediaPlayer();
    }

    private void e() {
        this.f.setOnClickListener(this.b);
        this.i.setOnClickListener(this.b);
        this.g.getHolder().setType(3);
        this.g.getHolder().addCallback(new Callback(this) {
            final /* synthetic */ LaucherActivity a;

            {
                this.a = r1;
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    this.a.f();
                    this.a.e.setVisibility(8);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            }
        });
        this.h.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ LaucherActivity a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                MobclickAgent.onEvent(this.a, "E01-A06", "视频播放完成次数");
                this.a.b();
            }
        });
    }

    private void f() {
        try {
            AssetFileDescriptor openFd = getAssets().openFd("advideo.mp4");
            this.h.reset();
            this.h.setAudioStreamType(3);
            this.h.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            this.h.setDisplay(this.g.getHolder());
            this.h.prepare();
            this.h.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
