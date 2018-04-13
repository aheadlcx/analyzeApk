package cn.xiaochuankeji.tieba.ui.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.h.c;
import cn.xiaochuankeji.tieba.background.modules.a.g;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.my.block.BlockUserActivity;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import java.util.Locale;
import org.aspectj.a.b.b;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.d;
import rx.e;

public class SettingActivity extends a implements OnSharedPreferenceChangeListener, OnClickListener, g.a {
    private static final org.aspectj.lang.a.a p = null;
    private final int a = 20;
    private TextView b;
    @BindView
    ImageView betaSwitcher;
    private cn.xiaochuankeji.tieba.background.modules.a.a c;
    private RelativeLayout d;
    private TextView e;
    private TextView f;
    @BindView
    View flmemberBlock;
    private TextView g;
    private TextView h;
    private TextView i;
    @BindView
    ImageView ivNew;
    private cn.htjyb.c.a.a j;
    private Boolean k = Boolean.valueOf(false);
    private SharedPreferences l;
    private TextView m;
    private TextView n;
    private ImageView o;

    static {
        k();
    }

    private static void k() {
        b bVar = new b("SettingActivity.java", SettingActivity.class);
        p = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.my.SettingActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 90);
    }

    public static void a(Context context, int i) {
        Intent intent = new Intent(context, SettingActivity.class);
        if (i >= 0) {
            ((Activity) context).startActivityForResult(intent, i);
        } else {
            context.startActivity(intent);
        }
    }

    static final void a(SettingActivity settingActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        Resources resources = settingActivity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = Locale.SIMPLIFIED_CHINESE;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        c.a().g();
        settingActivity.l = cn.xiaochuankeji.tieba.background.a.a();
        d.a(Boolean.valueOf(true)).d(new rx.b.g<Boolean, Long>(settingActivity) {
            final /* synthetic */ SettingActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public Long a(Boolean bool) {
                return Long.valueOf(cn.xiaochuankeji.tieba.background.h.a.c());
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<Long>(settingActivity) {
            final /* synthetic */ SettingActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Long) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(Long l) {
                this.a.f.setText(l.longValue() > 0 ? Formatter.formatFileSize(this.a, l.longValue()) : "");
                this.a.k = Boolean.valueOf(true);
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(p, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new c(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_setting;
    }

    protected boolean a(Bundle bundle) {
        this.c = cn.xiaochuankeji.tieba.background.a.g();
        return true;
    }

    protected void c() {
        int i;
        ButterKnife.a((Activity) this);
        this.b = (TextView) findViewById(R.id.tvLogout);
        this.d = (RelativeLayout) findViewById(R.id.relaCheckUpdate);
        this.e = (TextView) findViewById(R.id.tvVersion);
        this.g = (TextView) findViewById(R.id.tvClearCache);
        this.f = (TextView) findViewById(R.id.tvCache);
        this.h = (TextView) findViewById(R.id.tvReport);
        this.i = (TextView) findViewById(R.id.tvBlockCount);
        this.m = (TextView) findViewById(R.id.tvMemberBlockCount);
        this.n = (TextView) findViewById(R.id.tvAccountSetting);
        if (this.c.d()) {
            this.n.setVisibility(8);
            this.flmemberBlock.setVisibility(8);
        }
        if (!cn.xiaochuankeji.tieba.background.utils.c.a.c().p()) {
            findViewById(R.id.codec_selection).setVisibility(8);
        }
        this.o = (ImageView) findViewById(R.id.sw_codec);
        if (cn.xiaochuankeji.tieba.background.a.a().getBoolean("codec_switch", false)) {
            this.o.setSelected(true);
        } else {
            this.o.setSelected(false);
        }
        this.betaSwitcher.setSelected(c.a().b());
        ImageView imageView = this.ivNew;
        if (c.a().e()) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
    }

    @OnClick
    public void back() {
        onBackPressed();
    }

    @OnClick
    public void memberBlock() {
        BlockUserActivity.a((Context) this);
    }

    @OnClick
    public void flBlock() {
        if (cn.xiaochuankeji.tieba.background.a.g() != null) {
            MyBlockTopicActivity.a(this, cn.xiaochuankeji.tieba.background.a.g().n());
        }
    }

    @OnClick
    public void tvPushSetting() {
        SettingPushActivity.a((Context) this);
    }

    @OnClick
    public void tvPgc() {
        WebActivity.a(this, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/kol/about")));
    }

    protected void i_() {
        if (this.c.d()) {
            this.b.setVisibility(8);
            this.i.setText("");
        } else {
            this.b.setVisibility(0);
            this.i.setText(String.valueOf(this.c.n()));
        }
        this.e.setText("4.1.8.9");
        this.m.setText(String.valueOf(this.c.p()));
    }

    protected void j_() {
        this.b.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.g.setOnClickListener(this);
        findViewById(R.id.tvAbout).setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SettingActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                boolean z = true;
                Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
                boolean isSelected = view.isSelected();
                if (!isSelected) {
                    f a = f.a(null, "已开启视频录制软编码,使用软编码有更好兼容性,但视频录制质量会下降,不建议开启", this.a, new f.a(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                        }
                    }, true, true);
                    a.setConfirmTip("知道了");
                    a.b();
                }
                if (isSelected) {
                    z = false;
                }
                view.setSelected(z);
                edit.putBoolean("codec_switch", view.isSelected());
                edit.apply();
            }
        });
        this.betaSwitcher.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SettingActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (view.isSelected()) {
                    c.a().a(false);
                    view.setSelected(false);
                } else {
                    f a = f.a(null, "已开启新版本优先体验功能，欢迎加入新版本优先体验QQ群：563658875，参与交流", this.a, new f.a(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                        }
                    }, true, true);
                    a.setConfirmTip("知道了");
                    a.b();
                    view.setSelected(true);
                    c.a().a(true);
                }
                c.a().g();
            }
        });
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.j != null) {
            this.j.a();
        }
    }

    @l(a = ThreadMode.MAIN)
    public void updateVersion(cn.xiaochuankeji.tieba.b.g gVar) {
        this.ivNew.setVisibility(c.a().e() ? 0 : 8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAccountSetting:
                AccountSafeActivity.a((Context) this);
                return;
            case R.id.relaCheckUpdate:
                final c.a b = c.a().b(true);
                if (b != null) {
                    f.a("新版" + b.e, b.b, this, new f.a(this) {
                        final /* synthetic */ SettingActivity b;

                        public void a(boolean z) {
                            if (z) {
                                cn.xiaochuankeji.tieba.network.filedownload.e.b(b.c);
                                cn.xiaochuankeji.tieba.background.utils.g.a("开始下载...");
                            }
                        }
                    }, true).setConfirmTip("下载");
                    return;
                } else {
                    cn.xiaochuankeji.tieba.background.utils.g.a("当前已经是最新版本");
                    return;
                }
            case R.id.tvReport:
                WebActivity.a(this, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/report/appeal")));
                return;
            case R.id.tvClearCache:
                if (this.k.booleanValue()) {
                    e();
                    return;
                }
                return;
            case R.id.tvAbout:
                SettingAboutActivity.a(this);
                return;
            case R.id.tvLogout:
                j();
                return;
            default:
                return;
        }
    }

    private void e() {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
        this.j = new cn.htjyb.c.a.a(cn.xiaochuankeji.tieba.background.h.a.a(), 0);
        this.j.a(new cn.htjyb.c.a.a.a(this) {
            final /* synthetic */ SettingActivity a;

            {
                this.a = r1;
            }

            public void a() {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                cn.xiaochuankeji.tieba.background.utils.g.a("缓存清除成功");
                this.a.f.setText("");
                cn.xiaochuankeji.tieba.common.c.a.a(BaseApplication.getAppContext(), AppController.instance().buildHttpClient(true));
            }
        });
        this.j.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void j() {
        f.a("提示", "确定退出" + getResources().getString(R.string.app_name) + "？", this, new f.a(this) {
            final /* synthetic */ SettingActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    cn.xiaochuankeji.tieba.ui.widget.g.a(this.a, "退出登录中...");
                    cn.xiaochuankeji.tieba.push.a.b(cn.xiaochuankeji.tieba.push.a.a());
                    cn.xiaochuankeji.tieba.background.a.h().a(this.a);
                }
            }
        });
    }

    private void a(long j) {
        this.l.edit().putLong("key_last_user_id", j).apply();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void a(boolean z, String str) {
        if (cn.xiaochuankeji.tieba.ui.widget.g.b(this)) {
            cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        }
        if (z) {
            a(cn.xiaochuankeji.tieba.background.a.g().c());
            cn.xiaochuankeji.tieba.background.a.g().e();
            cn.xiaochuankeji.tieba.background.h.d.a().d();
            setResult(-1);
            finish();
            return;
        }
        cn.xiaochuankeji.tieba.background.utils.g.a(str);
    }
}
