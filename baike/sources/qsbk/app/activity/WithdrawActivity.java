package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;
import qsbk.app.utils.Util;
import qsbk.app.widget.BlackProgressDialog;

public class WithdrawActivity extends BaseActionBarActivity {
    TextView a;
    EditText b;
    View c;
    TextView d;
    TextView e;
    Button f;
    BlackProgressDialog g;
    HttpTask h;
    HttpTask i;
    TextWatcher j = new agp(this);
    private double k;
    private double l;
    private Double m;
    private String n;
    private String o;
    private String p;
    private String q;
    private double r;
    private String s;
    private boolean t;

    public static void launch(Activity activity, String str, double d, double d2) {
        Intent intent = new Intent(activity, WithdrawActivity.class);
        intent.putExtra("alipay", str);
        intent.putExtra(PayPWDUniversalActivity.MONEY, d);
        intent.putExtra("minMoney", d2);
        activity.startActivity(intent);
    }

    protected CharSequence getCustomTitle() {
        return getString(R.string.withdraw);
    }

    protected int a() {
        return R.layout.activity_withdraw;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_withdraw, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.help:
                SimpleWebActivity.launch(this, "https://m2.qiushibaike.com/static/withdraw.html");
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        Intent intent = getIntent();
        this.m = Double.valueOf(intent.getDoubleExtra(PayPWDUniversalActivity.MONEY, 0.0d));
        this.k = intent.getDoubleExtra("minMoney", 0.0d);
        this.n = intent.getStringExtra("alipay");
        f();
        g();
    }

    private void f() {
        this.g = new BlackProgressDialog(this);
        this.a = (TextView) findViewById(R.id.alipay_account);
        this.b = (EditText) findViewById(R.id.editText);
        this.b.requestLayout();
        this.c = findViewById(R.id.clear);
        this.c.setVisibility(8);
        this.d = (TextView) findViewById(R.id.balance);
        this.e = (TextView) findViewById(R.id.all);
        this.f = (Button) findViewById(R.id.btn_done);
        this.a.setText(this.n);
        this.d.setText(String.format("可提现金额¥%s", new Object[]{Util.formatMoney(this.m.doubleValue())}));
    }

    private void g() {
        this.e.setOnClickListener(new agq(this));
        this.c.setOnClickListener(new agr(this));
        this.b.addTextChangedListener(this.j);
        this.f.setOnClickListener(new ags(this));
        checkInputValid();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.j != null) {
            this.b.removeTextChangedListener(this.j);
        }
        if (this.h != null) {
            this.h.cancel(true);
        }
        if (this.i != null) {
            this.i.cancel(true);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 11 && i2 == -1 && intent != null) {
            this.o = intent.getStringExtra(PayPWDUniversalActivity.KEY);
            if (!TextUtils.isEmpty(this.o)) {
                withdraw();
            }
        }
    }

    public void checkInputValid() {
        try {
            this.l = Double.parseDouble(this.b.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            this.l = 0.0d;
        }
        Button button = this.f;
        boolean z = this.l >= this.k && this.l <= this.m.doubleValue();
        button.setEnabled(z);
        button = this.f;
        r0 = this.l < this.k ? "最低提现" + Util.formatMoney(this.k) + "元" : this.l > this.m.doubleValue() ? "提现金额超过余额" : "确认提现";
        button.setText(r0);
    }

    public void getWithdrawInfo() {
        showProgress();
        this.i = new EncryptHttpTask(null, Constants.WALLET_WITHDRAW_FEE, new agt(this));
        Map hashMap = new HashMap();
        hashMap.put(PayPWDUniversalActivity.MONEY, this.l + "");
        this.i.setMapParams(hashMap);
        this.i.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void withdraw() {
        showProgress();
        this.h = new EncryptHttpTask(null, Constants.WALLET_WITHDRAW, new agu(this));
        this.h.setMapParams(getWithDrawParams());
        this.h.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public Map<String, Object> getWithDrawParams() {
        Map hashMap = new HashMap();
        hashMap.put(PayPWDUniversalActivity.MONEY, this.l + "");
        hashMap.put("pay_password", this.o);
        return hashMap;
    }

    public void showProgress() {
        this.g.show();
    }

    public void dismissProgress() {
        if (this.g != null && this.g.isShowing()) {
            this.g.dismiss();
        }
    }
}
