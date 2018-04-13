package cn.xiaochuankeji.tieba.ui.debug;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.h.d;
import cn.xiaochuankeji.tieba.push.b.e;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.ui.widget.g;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import cn.xiaochuankeji.tieba.widget.AchievementView;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import org.aspectj.a.b.b;

public class DebugOptionsActivity extends a {
    private static final org.aspectj.lang.a.a a = null;
    @BindView
    AchievementView achievementView;
    @BindView
    Switch debug_show_layout;
    @BindView
    Switch enable_leak_canary;
    @BindView
    Switch https_switch;
    @BindView
    NavigationBar navBar;
    @BindView
    TextView status;

    static {
        q();
    }

    private static void q() {
        b bVar = new b("DebugOptionsActivity.java", DebugOptionsActivity.class);
        a = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.debug.DebugOptionsActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 64);
    }

    public static void a(Context context) {
        context.startActivity(new Intent(context, DebugOptionsActivity.class));
    }

    static final void a(DebugOptionsActivity debugOptionsActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(a, this, this, bundle);
        c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_debug_options;
    }

    protected void c() {
        super.c();
        ButterKnife.a((Activity) this);
    }

    protected void i_() {
        try {
            Field field = ViewGroup.class.getField("DEBUG_DRAW");
            field.setAccessible(true);
            this.debug_show_layout.setChecked(field.getBoolean(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        e();
        this.https_switch.setChecked(cn.xiaochuankeji.tieba.background.utils.c.a.c().F());
        this.enable_leak_canary.setChecked(b.a().e());
        this.debug_show_layout.setTag(Boolean.valueOf(true));
        this.https_switch.setTag(Boolean.valueOf(true));
        this.enable_leak_canary.setTag(Boolean.valueOf(true));
        this.navBar.setListener(new NavigationBar.a(this) {
            final /* synthetic */ DebugOptionsActivity a;

            {
                this.a = r1;
            }

            public void r() {
                this.a.finish();
            }

            public void s() {
            }

            public void t() {
            }

            public void u() {
            }
        });
    }

    private void e() {
        if (VERSION.SDK_INT >= 21) {
            CharSequence spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append("当前 API Host 是:").append(cn.xiaochuankeji.tieba.background.utils.d.a.e(), new ClickableSpan(this) {
                final /* synthetic */ DebugOptionsActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b(cn.xiaochuankeji.tieba.background.utils.d.a.e());
                }
            }, 33).append("\n").append("Use Https:").append(cn.xiaochuankeji.tieba.background.utils.c.a.c().F() ? "Y" : "N").append("\n").append("Client Id:").append(String.valueOf(cn.xiaochuankeji.tieba.push.a.a()), new ClickableSpan(this) {
                final /* synthetic */ DebugOptionsActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b(cn.xiaochuankeji.tieba.push.a.a());
                }
            }, 33).append("\n").append("Device Id:").append(AppController.instance().deviceID(), new ClickableSpan(this) {
                final /* synthetic */ DebugOptionsActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b(AppController.instance().deviceID());
                }
            }, 33).append("\n").append("当前渠道:").append(AppController.instance().packageChannel()).append("\n");
            this.status.setText(spannableStringBuilder);
            this.status.setMovementMethod(ScrollingMovementMethod.getInstance());
            return;
        }
        spannableStringBuilder = new StringBuilder();
        spannableStringBuilder.append("当前 API Host 是:").append(cn.xiaochuankeji.tieba.background.utils.d.a.e()).append("\n").append("Use Https:").append(cn.xiaochuankeji.tieba.background.utils.c.a.c().F() ? "Y" : "N").append("\n").append("Client Id:").append(cn.xiaochuankeji.tieba.push.a.a()).append("\n").append("Device Id:").append(AppController.instance().deviceID()).append("\n").append("当前渠道:").append(AppController.instance().packageChannel()).append("\n");
        this.status.setText(spannableStringBuilder);
    }

    private void b(String str) {
        try {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("test", str));
            }
        } catch (Throwable e) {
            cn.xiaochuankeji.tieba.analyse.a.a(e);
        }
    }

    @OnClick
    public void event(View view) {
        switch (view.getId()) {
            case R.id.release_api:
                b(false);
                return;
            case R.id.debug_api:
                b(true);
                return;
            case R.id.js_bridge:
                WebActivity.a(this, new cn.xiaochuan.jsbridge.b("jsbridge 功能展示", "https://www.izuiyou.com/help/js_test"));
                return;
            case R.id.setting:
                p();
                return;
            case R.id.dev_setting:
                k();
                return;
            case R.id.net_setting:
                j();
                return;
            case R.id.update_did:
                AppController.instance().deviceIDUpdated();
                a("已更新 did");
                return;
            case R.id.clear_message_db:
                e.f();
                a("已重建消息数据库");
                return;
            case R.id.clear_history_cache:
                Object r = cn.xiaochuankeji.tieba.background.a.e().r();
                if (TextUtils.isEmpty(r)) {
                    File file = new File(r);
                    if (file.isDirectory()) {
                        try {
                            org.apache.commons.io.b.b(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            org.apache.commons.io.b.d(file);
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                a("已清空缓存");
                return;
            default:
                return;
        }
    }

    private void b(boolean z) {
        g.a((Activity) this, "退出登录中...");
        cn.xiaochuankeji.tieba.push.a.b(cn.xiaochuankeji.tieba.push.a.a());
        if (z) {
            b.a().d();
            a("已切换到测试环境");
        } else {
            b.a().c();
            a("已切换到正式环境");
        }
        cn.xiaochuankeji.tieba.background.a.h().a(new cn.xiaochuankeji.tieba.background.modules.a.g.a(this) {
            final /* synthetic */ DebugOptionsActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str) {
                if (g.b(this.a)) {
                    g.c(this.a);
                }
                if (z) {
                    cn.xiaochuankeji.tieba.background.a.g().e();
                    d.a().d();
                    this.a.setResult(-1);
                    this.a.finish();
                    return;
                }
                cn.xiaochuankeji.tieba.background.utils.g.a(str);
            }
        });
    }

    private void j() {
        startActivity(new Intent("android.settings.WIFI_SETTINGS"));
    }

    private void k() {
        startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
    }

    private void p() {
        try {
            Context appContext = BaseApplication.getAppContext();
            String packageName = appContext.getPackageName();
            Intent intent = new Intent();
            if (VERSION.SDK_INT < 19) {
                intent.setAction("android.settings.SETTINGS");
            } else if (VERSION.SDK_INT >= 19) {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + packageName));
            }
            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            appContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                BaseApplication.getAppContext().startActivity(new Intent("android.settings.SETTINGS"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @OnCheckedChanged
    public void enable_leak_canary(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getTag() != null) {
            b.a().a(z);
            a("重启后生效");
        }
    }

    @OnCheckedChanged
    public void https(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getTag() != null) {
            if (z) {
                cn.xiaochuankeji.tieba.background.utils.c.a.c().E();
            } else {
                cn.xiaochuankeji.tieba.background.utils.c.a.c().D();
            }
            b.a().b(z);
            a(z ? "启用 Https" : "关闭 Https");
        }
    }

    @OnCheckedChanged
    public void showLayout(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getTag() != null) {
            try {
                Field field = ViewGroup.class.getField("DEBUG_DRAW");
                field.setAccessible(true);
                if (field.getBoolean(null) != z) {
                    field.set(null, Boolean.valueOf(z));
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(String str) {
        cn.xiaochuankeji.tieba.background.utils.g.a(str);
        e();
    }
}
