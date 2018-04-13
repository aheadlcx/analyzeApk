package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import java.math.BigDecimal;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.utils.Util;
import qsbk.app.widget.LoadingLayout;
import qsbk.app.widget.MarqueeTextView;

public class WalletBalanceActivity extends BaseActionBarActivity {
    public static final String ACTION_WALLET_RECORD = "wallet_records";
    public static final String ACTION_WITHDRAW_BIND = "withdraw_bind";
    TextView a;
    Button b;
    MarqueeTextView c;
    LoadingLayout d;
    Button e;
    private EncryptHttpTask f;
    private BigDecimal g;
    private BigDecimal h;
    private boolean i;
    private String j;
    private boolean k;
    private String l;
    private String m;
    private String n;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, String str) {
        Intent intent = new Intent(context, WalletBalanceActivity.class);
        intent.putExtra(PayPWDUniversalActivity.MONEY, str);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected String f() {
        return getString(R.string.wallet_balance);
    }

    protected int a() {
        return R.layout.activity_wallet_balance;
    }

    protected void a(Bundle bundle) {
        String str;
        setActionbarBackable();
        CharSequence stringExtra = getIntent().getStringExtra(PayPWDUniversalActivity.MONEY);
        if (TextUtils.isEmpty(stringExtra)) {
            str = "0";
        } else {
            CharSequence charSequence = stringExtra;
        }
        this.g = new BigDecimal(str);
        this.a = (TextView) findViewById(R.id.balance);
        this.b = (Button) findViewById(R.id.btn_withdraw);
        this.e = (Button) findViewById(R.id.btn_charge);
        this.e.setOnClickListener(new agb(this));
        this.c = (MarqueeTextView) findViewById(R.id.tip);
        this.c.setSelected(true);
        this.c.setSingleLine();
        this.c.setMarqueeEnable(true);
        this.d = (LoadingLayout) findViewById(R.id.loading);
        this.d.setOnLoadingClickListener(new agc(this));
        if (TextUtils.isEmpty(stringExtra)) {
            this.a.setText("加载中...");
        } else {
            this.a.setText("￥" + Util.formatMoney(this.g.doubleValue()));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_balance, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.pay_detail:
                WalletTradeListActivity.launch(this);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onResume() {
        super.onResume();
        g();
    }

    void g() {
        this.d.onLoading();
        this.f = new EncryptHttpTask(null, Constants.WALLET_BALANCE_DETAIL, new agd(this));
        this.f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void i() {
        this.b.setEnabled(this.k);
        this.b.setText(this.j);
        this.b.setOnClickListener(new agh(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 3 && i2 == -1) {
            if (intent != null) {
                Object stringExtra = intent.getStringExtra(WithdrawSetupActivity.ALIPAY_NAME);
                if (!TextUtils.isEmpty(stringExtra)) {
                    this.n = stringExtra;
                }
            }
            WithdrawActivity.launch(this, this.n, this.g.doubleValue(), this.h.doubleValue());
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f != null) {
            this.f.cancel(true);
        }
    }
}
