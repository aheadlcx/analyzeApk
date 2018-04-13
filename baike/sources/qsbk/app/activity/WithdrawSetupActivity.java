package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
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
import qsbk.app.widget.BlackProgressDialog;

public class WithdrawSetupActivity extends BaseActionBarActivity {
    public static final String ALIPAY_NAME = "alipay_name";
    public static final String KEY_QQ_GROUP = "0n5GIv1VKIaZbiiXnv_xH8EcdmcYAOLt";
    public static final String TIP = "1.如果手机号绑定了多个支付宝账号，请使用支付宝的邮箱地址。\n2.设置提现账户后，暂不提供修改，请谨慎操作。\n3.钱袋余额需达到20元，才能进行提现\n4.有疑问请加QQ群：";
    EditText a;
    EditText b;
    Button c;
    TextView d;
    HttpTask e;
    String f;
    String g;
    String h;
    BlackProgressDialog i;
    TextWatcher j = new agx(this);

    public static void launchForResult(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, WithdrawSetupActivity.class), i);
    }

    protected CharSequence getCustomTitle() {
        return getString(R.string.setup_withdraw_info);
    }

    protected int a() {
        return R.layout.activity_withdraw_setup;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.i = new BlackProgressDialog(this);
        this.a = (EditText) findViewById(R.id.alipay_account);
        this.b = (EditText) findViewById(R.id.alipay_name);
        this.c = (Button) findViewById(R.id.btn_done);
        this.c.setOnClickListener(new agy(this));
        this.a.addTextChangedListener(this.j);
        this.b.addTextChangedListener(this.j);
        this.d = (TextView) findViewById(R.id.tip);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(TIP);
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append("274070027");
        int length2 = spannableStringBuilder.length();
        spannableStringBuilder.setSpan(new agz(this), length, length2, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#ff639ce0")), length, length2, 33);
        this.d.setText(spannableStringBuilder);
        this.d.setMovementMethod(LinkMovementMethod.getInstance());
        this.d.setHighlightColor(getResources().getColor(R.color.transparent));
    }

    private void f() {
        showProgressDialog();
        this.e = new EncryptHttpTask(null, Constants.WALLET_BIND_WITHDRAW_ACCOUNT, new aha(this));
        this.e.setMapParams(getPostParams());
        this.e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.a.removeTextChangedListener(this.j);
        this.b.removeTextChangedListener(this.j);
        if (this.e != null) {
            this.e.cancel(true);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 102 && i2 == -1 && intent != null) {
            this.f = intent.getStringExtra(PayPWDUniversalActivity.KEY);
            if (!TextUtils.isEmpty(this.f)) {
                f();
            }
        }
    }

    private void g() {
        this.g = this.a.getText().toString().trim();
        this.h = this.b.getText().toString().trim();
        if (TextUtils.isEmpty(this.g) || TextUtils.isEmpty(this.h)) {
            this.c.setEnabled(false);
        } else {
            this.c.setEnabled(true);
        }
    }

    public Map<String, Object> getPostParams() {
        Map hashMap = new HashMap();
        hashMap.put("alipay_account", this.g);
        hashMap.put(ALIPAY_NAME, this.h);
        hashMap.put("pay_password", this.f);
        return hashMap;
    }

    public void showProgressDialog() {
        this.i.show();
    }

    public void dismissProgressDialog() {
        if (this.i != null && this.i.isShowing()) {
            this.i.dismiss();
        }
    }
}
