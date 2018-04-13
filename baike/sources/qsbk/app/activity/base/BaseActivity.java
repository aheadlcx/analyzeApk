package qsbk.app.activity.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import com.baidu.mobstat.StatService;
import com.flurry.android.FlurryAgent;
import qsbk.app.AppStat;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.push.PushMessageProcessor;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;

public abstract class BaseActivity extends AppCompatActivity {
    private Handler a = new Handler(Looper.getMainLooper());
    protected View i;

    protected abstract int a();

    protected abstract void a(Bundle bundle);

    public void statPushLabel() {
        Object stringExtra = getIntent().getStringExtra(PushMessageProcessor.PUSH_LABEL);
        if (!TextUtils.isEmpty(stringExtra)) {
            StatService.onEvent(this, "open_push", stringExtra);
        }
    }

    @TargetApi(14)
    protected void onCreate(Bundle bundle) {
        if (QsbkApp.getInstance().isMeizuVersion() && VERSION.SDK_INT >= 14) {
            getWindow().setUiOptions(1);
        }
        requestWindowFeature(5);
        super.onCreate(bundle);
        AppStat.reportAppStart("activity");
        this.i = LayoutInflater.from(this).inflate(a(), null);
        setContentView(this.i);
        if (c() || QsbkApp.getInstance().isMeizuVersion()) {
            UIHelper.setIndeterminateProgressBarPadding(this, 0, 0, getResources().getDimensionPixelSize(R.dimen.padding_medium), 0);
        }
        a(bundle);
    }

    public Handler getMainUIHandler() {
        return this.a;
    }

    protected String b() {
        return null;
    }

    protected void onResume() {
        if (TextUtils.isEmpty(b())) {
            StatService.onResume(this);
        } else {
            StatService.onPageStart(this, b());
        }
        FlurryAgent.onStartSession(this, "LLLGV7Y72RGDIMUHII8Z");
        super.onResume();
    }

    protected void onPause() {
        FlurryAgent.onEndSession(this);
        if (TextUtils.isEmpty(b())) {
            StatService.onPause(this);
        } else {
            StatService.onPageEnd(this, b());
        }
        super.onPause();
    }

    protected boolean c() {
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
}
