package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.SettingItem;

public class AboutSettingActivity extends BaseActionBarActivity {
    private SettingItem a;
    private SettingItem b;
    private SettingItem c;
    private View d;
    private ImageView e;
    private TextView f;
    private a g;

    private static class a extends Handler {
        WeakReference<AboutSettingActivity> a;

        a(AboutSettingActivity aboutSettingActivity) {
            this.a = new WeakReference(aboutSettingActivity);
        }

        public void handleMessage(Message message) {
            AboutSettingActivity aboutSettingActivity = (AboutSettingActivity) this.a.get();
            if (aboutSettingActivity != null && !aboutSettingActivity.isFinishing()) {
                if (((Boolean) message.obj).booleanValue()) {
                    aboutSettingActivity.g().show();
                } else {
                    ToastAndDialog.makeNeutralToast(aboutSettingActivity, "没有检测到新版本", Integer.valueOf(1)).show();
                }
            }
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return b();
    }

    protected int a() {
        return R.layout.activity_about_setting;
    }

    protected String b() {
        return "关于糗百";
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        initWidget();
        initListener();
    }

    public void initWidget() {
        this.a = (SettingItem) findViewById(R.id.about_qiubai_settingitem);
        this.b = (SettingItem) findViewById(R.id.new_version_settingitem);
        this.c = (SettingItem) findViewById(R.id.give_good_settingItem);
        this.d = findViewById(R.id.new_version_settingitem_line_view);
        this.e = (ImageView) findViewById(R.id.about_header_image);
        this.f = (TextView) findViewById(R.id.qiubai_version_text);
        this.g = new a(this);
        if (UIHelper.isNightTheme()) {
            this.f.setTextColor(-12106156);
            this.e.setImageResource(R.drawable.q01_night);
            return;
        }
        this.f.setTextColor(-5329234);
        this.e.setImageResource(R.drawable.q01_new);
    }

    public void initListener() {
        Object config = ConfigManager.getInstance().getConfig(ConfigManager.KEY_BUILD_VERSION, "");
        String str = Constants.localVersionName;
        String channel = ConfigManager.getInstance().getChannel();
        if (TextUtils.isEmpty(config)) {
            this.f.setText("version " + str + "\n  build " + channel);
        } else {
            this.f.setText("version " + str + "\n  build " + config + "_" + channel);
        }
        this.a.setOnClickListener(new d(this));
        if (ConfigManager.getInstance().isGoolePlayChannel()) {
            this.b.setVisibility(8);
            this.d.setVisibility(8);
        } else {
            this.b.setOnClickListener(new e(this));
        }
        this.c.setOnClickListener(new g(this));
    }

    private void f() {
        new h(this, "qbk-UserSetN2").start();
    }

    private Builder g() {
        Builder builder = new Builder(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.update_dialog_message, null);
        ((TextView) inflate.findViewById(R.id.updateMessage)).setText(Constants.change);
        builder.setView(inflate);
        builder.setTitle("软件版本更新");
        builder.setPositiveButton("以后再说", new j(this)).setNegativeButton("立即下载", new i(this));
        return builder;
    }

    private void a(Context context) throws ActivityNotFoundException {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=qsbk.app")));
    }

    protected void onDestroy() {
        if (this.g != null) {
            this.g.removeCallbacksAndMessages(null);
            this.g = null;
        }
        super.onDestroy();
    }
}
