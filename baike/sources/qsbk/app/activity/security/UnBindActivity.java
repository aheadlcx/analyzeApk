package qsbk.app.activity.security;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class UnBindActivity extends BaseActionBarActivity {
    public static final int ACTION_QQ = 1;
    public static final int ACTION_SINA = 2;
    public static final int ACTION_WX = 3;
    public static final String KEY_ACTION = "action";
    public static final String KEY_TIP = "tips";
    private static final String a = UnBindActivity.class.getSimpleName();
    private TextView b;
    private EditText c;
    private View d;
    private int e;
    private String f;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static Intent makeIntent(Context context, int i, String str) {
        Intent intent = new Intent(context, UnBindActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TIP, str);
        bundle.putInt("action", i);
        intent.putExtras(bundle);
        return intent;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Login_Night);
        } else {
            setTheme(R.style.Login);
        }
    }

    protected String f() {
        return "帐号绑定";
    }

    protected int a() {
        return R.layout.unbind;
    }

    private void g() {
        if (i()) {
            UIHelper.hideKeyboard(this);
            Map hashMap = new HashMap();
            hashMap.put("password", this.c.getText().toString().trim());
            hashMap.put("type", Integer.valueOf(this.e));
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.UNBIND_THIRD_PARTY, new s(this));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private boolean i() {
        if (!TextUtils.isEmpty(this.c.getText().toString().trim())) {
            return true;
        }
        this.c.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请输入正确密码", Integer.valueOf(0)).show();
        return false;
    }

    protected void a(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        }
        this.e = extras.getInt("action");
        this.f = extras.getString(KEY_TIP);
        setActionbarBackable();
        this.b = (TextView) findViewById(R.id.tips);
        this.c = (EditText) findViewById(R.id.password);
        this.d = findViewById(R.id.finish);
        if (TextUtils.isEmpty(this.f)) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
            this.b.setText(this.f);
        }
        this.d.setOnClickListener(new t(this));
    }
}
