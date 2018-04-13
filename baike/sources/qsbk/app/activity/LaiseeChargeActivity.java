package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.math.BigDecimal;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Laisee;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.ToastUtil;
import qsbk.app.utils.Util;
import qsbk.app.widget.BlackProgressDialog;

public class LaiseeChargeActivity extends BaseActionBarActivity {
    private static final String o = LaiseeChargeActivity.class.getSimpleName();
    TextView a;
    TextView b;
    EditText c;
    TextView d;
    TextView e;
    Button f;
    BlackProgressDialog g;
    double h;
    double i = 0.0d;
    int j;
    BigDecimal k;
    String l;
    TextWatcher m = new qf(this);
    TextWatcher n = new qg(this);
    private String p;
    private EncryptHttpTask q;
    private Laisee r;
    private boolean s;

    public static void launch(Context context) {
        Intent intent = new Intent(context, LaiseeChargeActivity.class);
        intent.putExtra("KEY_TYPE", 3);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void checkPaypass() {
        if (QsbkApp.currentUser != null && !QsbkApp.currentUser.hasPaypass()) {
            new SimpleHttpTask(Constants.MY_INFO, new qh()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    protected int e_() {
        return R.style.Theme.Laisee;
    }

    protected CharSequence getCustomTitle() {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(getString(R.string.send_laisee));
        if (this.j == 3) {
            spannableStringBuilder = new SpannableStringBuilder(getString(R.string.charge));
        }
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow_laisee)), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    protected boolean f_() {
        return false;
    }

    protected int a() {
        return R.layout.activity_laisee_charge;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.help:
                SimpleWebActivity.launch(this, "https://m2.qiushibaike.com/static/packet.html");
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        int intExtra = intent.getIntExtra("KEY_TYPE", 0);
        if (intExtra < 0) {
            finish();
            return;
        }
        this.j = intExtra;
        i();
        j();
        checkPaypass();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.c != null) {
            this.c.removeTextChangedListener(this.m);
            this.c.removeTextChangedListener(this.n);
        }
        if (this.q != null && !this.q.isCancelled()) {
            this.q.cancel(true);
            this.q = null;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 == -1) {
                ToastAndDialog.makeText(this, "充值成功").show();
                setResult(-1, new Intent());
                finish();
            } else if (i2 != 0) {
            }
        } else if (i == 11 && i2 == -1) {
            ToastUtil.Short("为了您的资金安全，请先设置支付密码");
            PayPasswordSetActivity.launch(this);
        }
    }

    private void i() {
        this.g = new BlackProgressDialog(this);
        this.a = (TextView) findViewById(R.id.tips);
        this.b = (TextView) findViewById(R.id.amount_desc);
        this.c = (EditText) findViewById(R.id.amount);
        this.d = (TextView) findViewById(R.id.yuan);
        this.e = (TextView) findViewById(R.id.amount_copy);
        this.f = (Button) findViewById(R.id.submit);
        this.f.setOnClickListener(new qi(this));
        this.c.addTextChangedListener(this.n);
        this.c.addTextChangedListener(this.m);
        this.c.setLongClickable(false);
        this.c.setFilters(new InputFilter[]{new LengthFilter(10)});
    }

    private void j() {
        this.b.setText("单个金额");
        this.b.setText("充值金额");
        this.f.setText("充值");
    }

    boolean f() {
        double parseDouble;
        try {
            parseDouble = Double.parseDouble(this.c.getText().toString());
        } catch (Exception e) {
            parseDouble = 0.0d;
        }
        switch (this.j) {
            case 0:
            case 2:
            case 3:
                this.i = parseDouble;
                this.e.setText("￥" + Util.formatMoney(this.i));
                break;
            case 1:
                this.e.setText("￥0.00");
                break;
        }
        switch (this.j) {
            case 0:
            case 1:
            case 3:
                this.h = parseDouble;
                this.h = parseDouble;
                break;
        }
        if (this.j == 3) {
            if (this.h > 2000000.0d) {
                this.l = "单次充值金额不超过2000000元";
                return false;
            } else if (this.h <= 0.0d) {
                return false;
            } else {
                return true;
            }
        } else if (this.h > 200.0d && this.j != 2) {
            this.l = "单个红包金额不超过200元";
            return false;
        } else if (1 == this.j || this.i <= 20000.0d) {
            return true;
        } else {
            this.l = "单次支付总额不超过20000元";
            return false;
        }
    }

    boolean g() {
        if (!f()) {
            return false;
        }
        if (this.h <= 2000000.0d) {
            return true;
        }
        this.l = "单次充值金额不超过2000000元";
        return false;
    }

    private HashMap<String, Object> k() {
        HashMap<String, Object> hashMap = new HashMap();
        if (this.j == 0) {
            hashMap.put(PayPWDUniversalActivity.MONEY, this.h + "");
            hashMap.put("content", this.p);
        } else {
            hashMap.put(PayPWDUniversalActivity.MONEY, this.j == 1 ? this.h + "" : this.i + "");
            hashMap.put("distribute", this.j == 1 ? "average" : "random");
            hashMap.put("content", this.p);
        }
        return hashMap;
    }
}
