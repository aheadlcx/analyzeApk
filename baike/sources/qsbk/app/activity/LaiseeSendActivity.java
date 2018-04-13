package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import java.math.BigDecimal;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.pay.LaiseePaymentActivity;
import qsbk.app.fragments.LaiseeSendFragment;
import qsbk.app.fragments.LaiseeVoiceSendFragment;
import qsbk.app.model.Laisee;
import qsbk.app.utils.ToastUtil;

public class LaiseeSendActivity extends BaseActionBarActivity {
    private static final String e = LaiseeSendActivity.class.getSimpleName();
    private static final String[] f = new String[]{LaiseeSendFragment.class.getSimpleName(), LaiseeVoiceSendFragment.class.getSimpleName()};
    int a;
    int b;
    String c;
    TabLayout d;

    public static void launch(Context context) {
        Intent intent = new Intent(context, LaiseeSendActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launchP2P(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, LaiseeSendActivity.class);
        intent.putExtra("kEY_ID", str);
        intent.putExtra("KEY_TYPE", 0);
        activity.startActivityForResult(intent, i);
    }

    public static void launchTribe(Activity activity, String str, int i, int i2) {
        Intent intent = new Intent(activity, LaiseeSendActivity.class);
        intent.putExtra("kEY_ID", str);
        intent.putExtra("member_num", i);
        intent.putExtra("KEY_TYPE", 2);
        activity.startActivityForResult(intent, i2);
    }

    protected int e_() {
        return R.style.Theme.Laisee;
    }

    protected CharSequence getCustomTitle() {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(getString(R.string.send_laisee));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow_laisee)), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    protected boolean f_() {
        return false;
    }

    protected int a() {
        return R.layout.activity_laisee_send;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_laisee_send, menu);
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
        this.b = intent.getIntExtra("member_num", 0);
        this.c = intent.getStringExtra("kEY_ID");
        this.a = intExtra;
        g();
        i();
    }

    private void g() {
        this.d = (TabLayout) findViewById(R.id.tab);
        this.d.addTab(this.d.newTab().setCustomView(R.layout.layout_laisee_type_tab).setText("普通红包"));
        this.d.addTab(this.d.newTab().setCustomView(R.layout.layout_laisee_type_tab).setText("语音红包"));
        this.d.addOnTabSelectedListener(new qx(this));
        navigateToFragment(R.id.fragment_container, f[0]);
    }

    public void navigateToFragment(int i, String str) {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(str);
        if (findFragmentByTag == null) {
            if (!TextUtils.equals(str, LaiseeSendFragment.class.getSimpleName())) {
                switch (this.a) {
                    case 0:
                        findFragmentByTag = LaiseeVoiceSendFragment.newInstance(this.c);
                        break;
                    case 2:
                        findFragmentByTag = LaiseeVoiceSendFragment.newInstance(this.c, this.b);
                        break;
                    default:
                        break;
                }
            }
            switch (this.a) {
                case 0:
                    findFragmentByTag = LaiseeSendFragment.newInstance(this.c, this.a);
                    break;
                case 2:
                    findFragmentByTag = LaiseeSendFragment.newInstance(this.c, this.a, this.b);
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(i, findFragmentByTag, str).commit();
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        for (String str2 : f) {
            if (!str2.equals(str)) {
                Fragment findFragmentByTag2 = getSupportFragmentManager().findFragmentByTag(str2);
                if (findFragmentByTag2 != null) {
                    beginTransaction.hide(findFragmentByTag2);
                }
            }
        }
        beginTransaction.show(findFragmentByTag).commit();
    }

    private void i() {
        LaiseeChargeActivity.checkPaypass();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 == -1) {
                Intent intent2 = new Intent();
                intent2.putExtra("laisee", intent.getSerializableExtra("laisee"));
                setResult(-1, intent2);
                finish();
            } else if (i2 != 0) {
            }
        } else if (i == 11 && i2 == -1) {
            ToastUtil.Short("为了您的资金安全，请先设置支付密码");
            PayPasswordSetActivity.launch(this);
        }
    }

    public void laiseePay(Laisee laisee, BigDecimal bigDecimal, HashMap<String, Object> hashMap, int i) {
        LaiseePaymentActivity.launch(this, laisee, bigDecimal, hashMap, i, 1);
    }
}
