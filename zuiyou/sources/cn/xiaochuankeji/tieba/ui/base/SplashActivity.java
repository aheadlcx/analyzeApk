package cn.xiaochuankeji.tieba.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.a.e;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.h.d;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.android.a.a.c;
import com.cesards.cropimageview.CropImageView;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import org.aspectj.a.b.b;
import rx.d$a;
import rx.j;

public class SplashActivity extends AppCompatActivity {
    private static final org.aspectj.lang.a.a h = null;
    private Bitmap a;
    private Handler b = new a(this);
    private cn.xiaochuankeji.tieba.background.splash.a c = new cn.xiaochuankeji.tieba.background.splash.a();
    private int d;
    private boolean e = true;
    private TextView f;
    private CropImageView g;

    static class a extends Handler {
        WeakReference<SplashActivity> a;

        a(SplashActivity splashActivity) {
            this.a = new WeakReference(splashActivity);
        }

        public void handleMessage(Message message) {
            SplashActivity splashActivity = (SplashActivity) this.a.get();
            if (splashActivity != null) {
                switch (message.what) {
                    case 26:
                        AppController.instance().preloadBeforeEnterApp();
                        return;
                    case 27:
                        splashActivity.e();
                        return;
                    case 28:
                        splashActivity.b();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    static {
        h();
    }

    private static void h() {
        b bVar = new b("SplashActivity.java", SplashActivity.class);
        h = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.base.SplashActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 63);
    }

    static final void a(SplashActivity splashActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        c.a.b.a((Activity) splashActivity, (int) R.color.CB);
        c.a(splashActivity.getWindow(), true);
        super.onCreate(bundle);
        Intent intent = splashActivity.getIntent();
        if ((intent.getFlags() & 4194304) == 0 || intent.getAction() == null || !intent.getAction().equals("android.intent.action.MAIN")) {
            splashActivity.setContentView((int) R.layout.activity_splash);
            splashActivity.g = (CropImageView) splashActivity.findViewById(R.id.ivSplashAd);
            splashActivity.g.setOnClickListener(new OnClickListener(splashActivity) {
                final /* synthetic */ SplashActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.c();
                }
            });
            splashActivity.f = (TextView) splashActivity.findViewById(R.id.btn_skip);
            splashActivity.f.setOnClickListener(new OnClickListener(splashActivity) {
                final /* synthetic */ SplashActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.d();
                }
            });
            splashActivity.a();
            splashActivity.f();
            d.a().a(1);
            return;
        }
        splashActivity.finish();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(h, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new p(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void a() {
        cn.xiaochuankeji.tieba.background.utils.c.a.c().j();
    }

    private void b() {
        if (this.d > 0) {
            this.f.setText("跳过" + this.d + NotifyType.SOUND);
        }
        this.d = Math.max(0, this.d - 1);
        this.b.sendEmptyMessageDelayed(28, 1000);
    }

    private void c() {
        if (!this.e) {
            this.b.removeMessages(27);
            HashMap hashMap = new HashMap();
            switch (this.c.c) {
                case 2:
                    hashMap.put("tid", Long.valueOf(this.c.h));
                    TopicDetailActivity.a((Activity) this, this.c.h, false, "splash", 0);
                    break;
                case 3:
                    hashMap.put("url", this.c.i);
                    WebActivity.a(this, cn.xiaochuan.jsbridge.b.a(null, this.c.i), 0);
                    break;
                default:
                    d();
                    break;
            }
            if (!hashMap.isEmpty()) {
                cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("click", "splash", hashMap);
            }
        }
    }

    private void d() {
        if (!isFinishing()) {
            Uri data = getIntent().getData();
            if (data == null || !data.getScheme().equals("zuiyou")) {
                MainActivity.a((Context) this);
            } else {
                MainActivity.a((Context) this, data.toString());
            }
            finish();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        d();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.recycle();
            this.a = null;
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.b.sendEmptyMessage(26);
        }
    }

    private void e() {
        d();
    }

    private void f() {
        final long currentTimeMillis = System.currentTimeMillis();
        rx.d.a(new d$a<e>(this) {
            final /* synthetic */ SplashActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super e> jVar) {
                e a = this.a.a(this.a.c.b());
                if (a == null || !cn.htjyb.c.a.b.c(a.f)) {
                    jVar.onError(new Throwable("empty splash"));
                    return;
                }
                this.a.c.a(a);
                jVar.onNext(a);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<e>(this) {
            final /* synthetic */ SplashActivity b;

            public /* synthetic */ void onNext(Object obj) {
                a((e) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.b.g();
            }

            public void a(e eVar) {
                String str;
                if (TextUtils.isEmpty(eVar.f)) {
                    str = eVar.e;
                } else {
                    str = "file://" + eVar.f;
                }
                this.b.g.setImageURI(Uri.parse(str));
                long currentTimeMillis = 1000 - (System.currentTimeMillis() - currentTimeMillis);
                if (currentTimeMillis < 0) {
                    currentTimeMillis = 0;
                }
                this.b.g.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.g.setVisibility(0);
                        this.a.b.g();
                    }
                }, currentTimeMillis);
            }
        });
    }

    private void g() {
        boolean z;
        if (!this.c.c() || this.c.g < System.currentTimeMillis() / 1000) {
            z = false;
        } else {
            this.f.setVisibility(0);
            z = true;
        }
        this.e = false;
        int max = Math.max(1200, this.c.f * 1000);
        if (max > 15000) {
            max = z ? 3000 : 1200;
        }
        this.b.sendEmptyMessageDelayed(27, (long) max);
        if (z) {
            this.d = max / 1000;
            b();
        }
    }

    private e a(List<e> list) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (list == null || list.size() == 0) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            e eVar = (e) list.get(i);
            if (currentTimeMillis > eVar.a && currentTimeMillis < eVar.b) {
                for (int i2 : cn.xiaochuankeji.tieba.background.splash.a.a) {
                    if (eVar.c == i2) {
                        return eVar;
                    }
                }
                continue;
            }
        }
        return null;
    }
}
