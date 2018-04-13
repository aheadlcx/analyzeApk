package qsbk.app.activity.security;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;

public class EmailBindActivity extends BaseActionBarActivity {
    public static final String ACTION_BIND = "action_bind";
    public static final String ACTION_BINDED = "action_binded";
    public static final String ACTION_UNBIND = "action_unbind";
    public static final String KEY_ACTION = "action";
    public static final String KEY_EMAIL = "email";
    private static final String b = EmailBindActivity.class.getSimpleName();
    protected JSONObject a = null;
    private EditText c;
    private EditText d;
    private View e;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void makeAction(Bundle bundle, String str, String str2) {
        if (bundle != null) {
            bundle.putString("action", str);
            bundle.putString("email", str2);
        }
    }

    public static boolean isEmail(String str) {
        if (str.trim().equals("")) {
            return false;
        }
        return Pattern.compile("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$").matcher(str).matches();
    }

    private String i() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getString("action");
    }

    private boolean j() {
        return ACTION_UNBIND.equalsIgnoreCase(i());
    }

    private boolean k() {
        return ACTION_BINDED.equalsIgnoreCase(i());
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Login_Night);
        } else {
            setTheme(R.style.Login);
        }
    }

    protected String f() {
        if (j()) {
            return "更改绑定邮箱";
        }
        return "绑定邮箱";
    }

    protected int a() {
        return R.layout.activity_emailbind;
    }

    protected void a(Bundle bundle) {
        l();
        m();
        setActionbarBackable();
    }

    private void l() {
        this.e = findViewById(R.id.finish);
        this.c = (EditText) findViewById(R.id.email);
        this.d = (EditText) findViewById(R.id.password);
        if (j()) {
            this.c.setHint("输入你的新邮箱");
            this.d.setHint("你当前登录的糗事百科密码");
        } else {
            this.c.setHint("输入你要绑定的邮箱");
            this.d.setHint(QsbkApp.currentUser.hasPwd() ? "你当前登录的糗事百科密码" : "设置你的糗事百科密码");
        }
        if (k()) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String string = extras.getString("email");
                if (!TextUtils.isEmpty(string)) {
                    a(string);
                }
            }
        }
    }

    private void m() {
        this.e.setOnClickListener(new n(this));
    }

    private void a(String str) {
        UIHelper.hideKeyboard(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainContent);
        linearLayout.removeAllViews();
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.activity_emailbind_sent, linearLayout, false);
        String str2 = UIHelper.isNightTheme() ? "#bd7c1c" : "#fdb932";
        textView.setText(Html.fromHtml(String.format("验证邮件已发至<br/><font color=\"%s\">%s</font><br/>请在48小时内前往邮箱激活，只有激活后才可使用邮箱登录糗百帐号", new Object[]{str2, str})));
        linearLayout.addView(textView);
    }

    private boolean n() {
        String trim = this.c.getText().toString().trim();
        if (trim.equals("") || !isEmail(trim)) {
            this.c.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "邮箱格式不合法！", Integer.valueOf(0)).show();
            return false;
        }
        Object trim2 = this.d.getText().toString().trim();
        Object obj = null;
        if (TextUtils.isEmpty(trim2)) {
            obj = "请输入正确密码";
        } else if (!(j() || Util.isValidPwd(trim2))) {
            obj = getString(R.string.enter_new_pwd);
        }
        if (TextUtils.isEmpty(obj)) {
            return true;
        }
        this.d.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, obj, Integer.valueOf(0)).show();
        return false;
    }

    public void submit() {
        g();
        showLoading();
        Map hashMap = new HashMap();
        hashMap.put("email", this.c.getText().toString().trim());
        hashMap.put("password", this.d.getText().toString().trim());
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.BINDEMAIL, new o(this));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void g() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
    }

    public void finish() {
        super.finish();
    }

    protected boolean d() {
        return true;
    }
}
