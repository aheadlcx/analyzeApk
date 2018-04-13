package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.util.Calendar;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;
import qsbk.app.im.TimeUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

public class WalletActivity extends BaseActionBarActivity implements OnClickListener {
    private String a;
    private TextView b;
    private View c;
    private View d;
    private View e;
    private HttpTask f;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, WalletActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected String f() {
        return "糗百钱袋";
    }

    protected int a() {
        return R.layout.activity_wallet;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.e = findViewById(R.id.desc);
        this.c = findViewById(R.id.send_laisee);
        this.c.setOnClickListener(this);
        this.d = findViewById(R.id.recharge_diamond);
        this.d.setOnClickListener(this);
        this.b = (TextView) findViewById(R.id.balance);
        this.b.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.b.setText("加载中...");
        g();
    }

    private void g() {
        long sharePreferencesLongValue = SharePreferenceUtils.getSharePreferencesLongValue(QsbkApp.currentUser.userId + "wallet_check_phone");
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(sharePreferencesLongValue);
        if (!TimeUtils.isSameDay(instance, instance2)) {
            if (!QsbkApp.currentUser.hasPaypass()) {
                new Builder(this).setTitle("为了保证您的钱袋安全，请先设置支付密码").setPositiveButton(17039370, new afx(this)).setNegativeButton(17039360, null).show();
            }
            SharePreferenceUtils.setSharePreferencesValue(QsbkApp.currentUser.userId + "wallet_check_phone", System.currentTimeMillis());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wallet, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.pay_detail:
                WalletTradeListActivity.launch(this);
                return true;
            case R.id.pay_pwd:
                if (!QsbkApp.currentUser.hasPhone()) {
                    new Builder(this).setTitle("账号安全系数低，请先绑定手机").setPositiveButton("绑定手机", new afz(this)).setNegativeButton("取消", null).show();
                    return true;
                } else if (QsbkApp.currentUser.hasPaypass()) {
                    PayPasswordModifyActivity.launch(this);
                    return true;
                } else {
                    PayPasswordSetActivity.launch(this);
                    return true;
                }
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void getBalance() {
        this.f = new EncryptHttpTask(null, Constants.WALLET_BALANCE, new aga(this));
        this.f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.desc:
            case R.id.balance:
                WalletBalanceActivity.launch(this, this.a);
                return;
            case R.id.send_laisee:
            case R.id.recharge_diamond:
                ToastAndDialog.makeText(this, "攻城狮吊瓶开发中...").show();
                return;
            default:
                return;
        }
    }

    protected void onResume() {
        super.onResume();
        getBalance();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f != null && !this.f.isCancelled()) {
            this.f.cancel(true);
        }
    }
}
