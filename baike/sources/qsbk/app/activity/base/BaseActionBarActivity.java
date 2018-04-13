package qsbk.app.activity.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import com.baidu.mobstat.StatService;
import com.flurry.android.FlurryAgent;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import qsbk.app.AppStat;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.push.PushMessageProcessor;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ResultActivityAdaptor;
import qsbk.app.utils.ResultActivityListener;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UIHelper$Theme;

public abstract class BaseActionBarActivity extends AppCompatActivity {
    protected View L;
    protected ResultActivityAdaptor M = new ResultActivityAdaptor(this);
    SystemBarTintManager N;
    private Handler a = new Handler(Looper.getMainLooper());
    public int statusBarTintColor = -1;

    protected abstract int a();

    protected abstract void a(Bundle bundle);

    protected abstract CharSequence getCustomTitle();

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        try {
            StatService.onEvent(this, "onRestoreInstanceState", getClass().getName());
        } catch (Exception e) {
        }
    }

    public void statPushLabel() {
        Object stringExtra = getIntent().getStringExtra(PushMessageProcessor.PUSH_LABEL);
        if (!TextUtils.isEmpty(stringExtra)) {
            StatService.onEvent(this, "open_push", stringExtra);
        }
    }

    protected void c_() {
        int e_ = e_();
        if (e_ != 0) {
            setTheme(e_);
        }
    }

    public boolean needShowActionBar() {
        return true;
    }

    protected int e_() {
        return UIHelper.isNightTheme() ? R.style.Night : R.style.Day;
    }

    @TargetApi(14)
    protected void onCreate(Bundle bundle) {
        c_();
        if (QsbkApp.getInstance().isMeizuVersion() && VERSION.SDK_INT >= 14) {
            getWindow().setUiOptions(1);
        }
        requestWindowFeature(1);
        super.onCreate(bundle);
        AppStat.reportAppStart("activity");
        this.L = LayoutInflater.from(this).inflate(a(), null);
        setContentView(this.L);
        if (d() || QsbkApp.getInstance().isMeizuVersion()) {
            UIHelper.setIndeterminateProgressBarPadding(this, 0, 0, getResources().getDimensionPixelSize(R.dimen.padding_medium), 0);
        }
        f();
        if (f_()) {
            h();
        }
        a(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (needShowActionBar()) {
            if (!(TextUtils.isEmpty(getCustomTitle()) || supportActionBar == null)) {
                supportActionBar.show();
                supportActionBar.setDisplayShowTitleEnabled(true);
                supportActionBar.setTitle(getCustomTitle());
            }
        } else if (supportActionBar != null) {
            supportActionBar.hide();
        }
        if (supportActionBar != null) {
            supportActionBar.setElevation(0.0f);
        }
    }

    private void f() {
        try {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
            Field declaredField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                declaredField.setBoolean(viewConfiguration, false);
            }
        } catch (Exception e) {
        }
    }

    public Handler getMainUIHandler() {
        return this.a;
    }

    public void setTitle(CharSequence charSequence) {
        getSupportActionBar().setTitle(charSequence);
    }

    protected String o() {
        return null;
    }

    protected void onResume() {
        if (TextUtils.isEmpty(o())) {
            StatService.onResume((Context) this);
        } else {
            StatService.onPageStart(this, o());
        }
        FlurryAgent.onStartSession(this, "LLLGV7Y72RGDIMUHII8Z");
        super.onResume();
    }

    protected void onPause() {
        FlurryAgent.onEndSession(this);
        if (TextUtils.isEmpty(o())) {
            StatService.onPause((Context) this);
        } else {
            StatService.onPageEnd(this, o());
        }
        super.onPause();
    }

    public void showLoading() {
        setProgressBarIndeterminateVisibility(true);
    }

    public void hideLoading() {
        setProgressBarIndeterminateVisibility(false);
    }

    protected boolean d() {
        return false;
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(65536);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                LogUtil.d("on home click");
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected String p() {
        return UIHelper.getTheme().equals(UIHelper$Theme.THEME_NIGHT) ? UIHelper$Theme.THEME_NIGHT : "";
    }

    public void gotoFeedbackActivity() {
        String p = p();
        String str = "android_" + Constants.localVersionName;
        String str2 = (QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId + "|") + DeviceUtils.getAndroidId();
        String str3 = Build.FINGERPRINT;
        str2 = String.format(Constants.FEEDBACK + "?platform=android&source=%s&userid=%s&device=%s&theme=%s&channel=%s", new Object[]{URLEncoder.encode(str), URLEncoder.encode(str2), URLEncoder.encode(str3), URLEncoder.encode(p), URLEncoder.encode(ConfigManager.getInstance().getChannel())});
        LogUtil.d("feedback url:" + str2);
        SimpleWebActivity.launch(this, str2, "意见反馈");
    }

    public void setActionBarHeight(int i) {
        View findViewById;
        if (i < 0) {
            i = UIHelper.dip2px((Context) this, 48.0f);
        }
        int identifier = getResources().getIdentifier("action_bar_container", "id", "android");
        int identifier2 = getResources().getIdentifier("action_bar_container", "id", getPackageName());
        try {
            findViewById = findViewById(identifier);
            if (findViewById == null) {
                findViewById = findViewById(identifier2);
            }
            if (findViewById != null) {
                LayoutParams layoutParams = findViewById.getLayoutParams();
                layoutParams.height = i;
                findViewById.setLayoutParams(layoutParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        identifier = getResources().getIdentifier("action_bar", "id", "android");
        identifier2 = getResources().getIdentifier("action_bar", "id", getPackageName());
        try {
            findViewById = findViewById(identifier);
            if (findViewById == null) {
                findViewById = findViewById(identifier2);
            }
            if (findViewById != null) {
                layoutParams = findViewById.getLayoutParams();
                layoutParams.height = i;
                findViewById.setLayoutParams(layoutParams);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setActionbarBackable() {
        boolean z = true;
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayUseLogoEnabled(false);
            supportActionBar.setHomeButtonEnabled(true);
            View findViewById = findViewById(16908332);
            if (findViewById != null) {
                try {
                    Object parent = findViewById.getParent();
                    while (parent != null) {
                        parent = parent.getParent();
                        if (parent == null) {
                            break;
                        }
                        LogUtil.d("abview:" + parent);
                        if (parent.getClass().getSimpleName().endsWith("ActionBarView")) {
                            break;
                        }
                    }
                    if (parent != null) {
                        boolean z2;
                        Field[] declaredFields = parent.getClass().getDeclaredFields();
                        for (Field name : declaredFields) {
                            if (name.getName().equals("mUpGoerFive")) {
                                z2 = true;
                                break;
                            }
                        }
                        z2 = false;
                        if (!z2) {
                            z = false;
                        }
                        LogUtil.d("found:" + z2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LogUtil.d("home enabled:" + z);
            if ((z || VERSION.SDK_INT >= 18) && VERSION.SDK_INT >= 11) {
                supportActionBar.setIcon((int) R.drawable.icon_actionbar_home);
            } else {
                supportActionBar.setDisplayShowHomeEnabled(false);
            }
        }
    }

    protected int a(Intent intent, ResultActivityListener resultActivityListener) {
        return this.M.startActivityForResult(intent, resultActivityListener);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.d("on activity result:" + i);
        LogUtil.d("on activity result:" + i2);
        if (!this.M.onResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    @TargetApi(19)
    protected void b(boolean z) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
    }

    protected boolean f_() {
        return true;
    }

    @SuppressLint({"NewApi"})
    protected void h() {
        int i = false;
        if (VERSION.SDK_INT >= 19) {
            if (SystemBarTintManager.sPostLollipop) {
                b(false);
            } else {
                b(true);
            }
            if (this.L != null && VERSION.SDK_INT >= 14) {
                this.L.setFitsSystemWindows(true);
            }
            if (this.N == null) {
                this.N = new SystemBarTintManager(this);
                this.N.setStatusBarTintEnabled(true);
                this.N.setStatusBarDarkMode(UIHelper.isNightTheme(), this);
            }
            if (this.statusBarTintColor == -1) {
                try {
                    TypedArray obtainStyledAttributes = obtainStyledAttributes(new int[]{16843470});
                    TypedValue typedValue = new TypedValue();
                    if (obtainStyledAttributes.length() > 0) {
                        obtainStyledAttributes.getValue(0, typedValue);
                        obtainStyledAttributes = obtainStyledAttributes(typedValue.data, new int[]{16842964});
                        i = obtainStyledAttributes.getColor(0, 0);
                    }
                    obtainStyledAttributes.recycle();
                } catch (Exception e) {
                }
                if (!needShowActionBar()) {
                    i = getResources().getColor(R.color.no_actionbar_color);
                } else if (i == 0) {
                    i = getResources().getColor(UIHelper.isNightTheme() ? R.color.actionbar_dark : R.color.actionbar_day);
                }
                this.N.setStatusBarTintColor(i);
                return;
            }
            this.N.setStatusBarTintColor(this.statusBarTintColor);
        }
    }

    public void setStatusColor() {
        this.N.setStatusBarTintColor(this.statusBarTintColor);
    }
}
