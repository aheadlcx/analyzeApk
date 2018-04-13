package qsbk.app.open.auth;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.reflect.Field;
import qsbk.app.ConfigManager;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.Md5;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class SSOAuthActivity extends BaseActionBarActivity {
    public static final int BTN_STATE_AUTH = 1;
    public static final int BTN_STATE_AUTO_LOGIN = 2;
    public static final int BTN_STATE_RETRY = 3;
    public static final int REQUEST_CODE_LOGIN = 1;
    AuthApi a = new AuthApi();
    private ImageView b;
    private ImageView c;
    private Button d;
    private TextView e;
    private TextView f;
    private int g = 1;
    private boolean h = false;
    private boolean i = false;
    private RequestInfo j;
    private AppInfo k;
    private TextView l = null;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "";
    }

    protected int a() {
        return R.layout.layout_sso;
    }

    private void i() {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.sso_actionbar, null);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowCustomEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayUseLogoEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setCustomView(relativeLayout, new LayoutParams(-1, -1, 3));
        supportActionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_info_top_button));
        View customView = supportActionBar.getCustomView();
        ((LinearLayout) customView.findViewById(R.id.cancel)).setOnClickListener(new a(this));
        ViewParent parent = customView.getParent();
        if (parent != null && (parent instanceof Toolbar)) {
            ((Toolbar) parent).setContentInsetsAbsolute(0, 0);
        }
        ((TextView) customView.findViewById(R.id.change_account)).setOnClickListener(new b(this));
    }

    private void a(int i) {
        this.g = i;
        if (this.g == 1) {
            this.d.setText("授权并登录");
            this.d.setEnabled(true);
        } else if (this.g == 2) {
            this.d.setEnabled(false);
            this.d.setText("自动登录中..");
        } else {
            this.d.setEnabled(true);
            this.d.setText("点击重试");
        }
    }

    private void j() {
        this.h = true;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Login_Night);
        } else {
            setTheme(R.style.Login);
        }
    }

    protected void a(Bundle bundle) {
        i();
        this.b = (ImageView) findViewById(R.id.app_icon);
        this.e = (TextView) findViewById(R.id.app_name);
        this.c = (ImageView) findViewById(R.id.user_icon);
        this.f = (TextView) findViewById(R.id.user_name);
        this.d = (Button) findViewById(R.id.auth_btn);
        this.l = (TextView) findViewById(R.id.id_loading_tips);
        this.d.setOnClickListener(new d(this));
        this.j = new RequestInfo(getIntent(), getCallingAppSignature(), getCallingAppPackageName());
        g();
    }

    protected void g() {
        this.h = false;
        String clientId = this.j.getClientId();
        LogUtil.d("appid:" + clientId);
        initAndCheckApp(clientId);
    }

    public void checkAndInitAuthedUser(String str) {
        checkLoginUser();
    }

    public void checkLoginUser() {
        if (QsbkApp.currentUser == null) {
            LogUtil.d("login user is null, start login activity");
            a(new Intent(this, ActionBarLoginActivity.class), new e(this));
            return;
        }
        LogUtil.d("user loged in and get auth code");
        String str = null;
        if (!TextUtils.isEmpty(QsbkApp.currentUser.userIcon)) {
            str = QsbkApp.absoluteUrlOfMediumUserIcon(QsbkApp.currentUser.userIcon, QsbkApp.currentUser.userId);
        }
        showAuthInfo(QsbkApp.currentUser.userName, str);
        if (this.i) {
            LogUtil.d("retry clicked and get authcode");
            this.i = false;
            a(2);
            runGetAuthCodeProcess();
            return;
        }
        a(1);
    }

    public void showAuthInfo(String str, String str2) {
        LogUtil.d("icon");
        a(str, str2);
    }

    public void initAndCheckApp(String str) {
        AppInfo appInfoFromLocal = AppInfo.getAppInfoFromLocal(str);
        LogUtil.d("app info in local:" + str);
        if (appInfoFromLocal == null) {
            CommHttpAsyncTask fVar = new f(this, str);
            showLoadingInfo("拉取应用信息中..");
            fVar.run();
            return;
        }
        LogUtil.d("app info in local");
        checkAppInfo(appInfoFromLocal, str);
    }

    public void checkAppInfo(AppInfo appInfo, String str) {
        if (initAppInfo(appInfo)) {
            checkAndInitAuthedUser(str);
        } else {
            showAuthFailAndRetry("应用签名验证出错，请确定应用签名正确!");
        }
    }

    public void showAuthFailAndRetry(String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        a(3);
    }

    private void a(String str, String str2) {
        this.f.setText(str);
        if (TextUtils.isEmpty(str2)) {
            this.c.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(this.c, str2);
        }
    }

    public boolean initAppInfo(AppInfo appInfo) {
        this.k = appInfo;
        this.e.setText(appInfo.getName());
        if (TextUtils.isEmpty(appInfo.getIcon())) {
            this.b.setImageResource(R.drawable.app_default);
        } else {
            FrescoImageloader.displayImage(this.b, appInfo.getIcon(), R.drawable.app_default);
        }
        if (!ConfigManager.getInstance().isTestOnlyChannel()) {
            LogUtil.d("appsignature,md5:" + this.j.getSignatureMD5());
            LogUtil.d("appinfo sig:" + appInfo.getSignatureMD5());
            if (!appInfo.getSignatureMD5().equals(this.j.getSignatureMD5())) {
                return false;
            }
        }
        return true;
    }

    public String getCallingAppSignature() {
        return a(getCallingAppPackageName());
    }

    public String getCallingAppPackageName() {
        String packageName = getCallingActivity().getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return k();
        }
        return packageName;
    }

    private String k() {
        if (VERSION.SDK_INT < 22) {
            return null;
        }
        try {
            Field declaredField = Class.forName("android.app.Activity").getDeclaredField("mReferrer");
            declaredField.setAccessible(true);
            return (String) declaredField.get(this);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private String a(String str) {
        PackageInfo pacageInfo = getPacageInfo(this, str);
        if (pacageInfo == null || pacageInfo.signatures == null || pacageInfo.signatures.length <= 0) {
            return null;
        }
        return pacageInfo.signatures[0].toCharsString();
    }

    public PackageInfo getPacageInfo(Activity activity, String str) {
        try {
            return activity.getPackageManager().getPackageInfo(str, 64);
        } catch (NameNotFoundException e) {
            LogUtil.d("obtain package info exception:" + e.getMessage());
            return null;
        }
    }

    public String getNativeClientSecret(RequestInfo requestInfo, String str) {
        String signatureMD5 = requestInfo.getSignatureMD5();
        if (ConfigManager.getInstance().isTestOnlyChannel()) {
            signatureMD5 = this.k.getSignatureMD5();
        }
        return Md5.MD5((requestInfo.getClientId() + requestInfo.getPackageName() + signatureMD5) + "qbsecret" + str).toLowerCase();
    }

    public void runGetAccessTokenProcess(String str) {
        CommHttpAsyncTask gVar = new g(this, str);
        showLoadingInfo("拉取授权信息..");
        gVar.run();
    }

    public void runGetAuthCodeProcess() {
        CommHttpAsyncTask hVar = new h(this, QsbkApp.currentUser.token, QsbkApp.currentUser.userId);
        showLoadingInfo("用户认证中..");
        hVar.run();
    }

    public void showLoadingInfo(String str) {
        this.l.setVisibility(0);
        this.l.setText(str);
    }

    public void hideLoadingInfo() {
        this.l.setVisibility(4);
    }
}
