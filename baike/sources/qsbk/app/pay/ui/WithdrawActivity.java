package qsbk.app.pay.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.r0adkll.slidr.Slidr;
import java.math.BigDecimal;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.widget.SimpleDialog;
import qsbk.app.pay.R;

public class WithdrawActivity extends BaseActivity implements OnClickListener {
    private double A;
    private double B;
    private int C;
    private int D;
    private long E;
    private String F;
    private String G;
    private String H;
    private int I;
    private double J;
    private String K;
    FrameLayout a;
    ScrollView b;
    LinearLayout c;
    RelativeLayout d;
    private TextView e;
    private EditText f;
    private EditText g;
    private TextView h;
    private TextView i;
    private ProgressDialog j;
    private TextView k;
    private TextView l;
    private TextView m;
    private ImageView n;
    private RelativeLayout o;
    private TextView p;
    private RelativeLayout q;
    private TextView r;
    private TextView s;
    private double t;
    private double u;
    private double v;
    private String w;
    private String x;
    private double y;
    private double z;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
    }

    protected void initView() {
        setTitle(getString(R.string.pay_my_profit_withraw));
        setUp();
        this.e = (TextView) findViewById(R.id.pay_withdraw_tips);
        this.f = (EditText) findViewById(R.id.et_withdraw_account);
        this.g = (EditText) findViewById(R.id.et_withdraw_name);
        this.h = (TextView) findViewById(R.id.tv_btn_withdraw);
        this.i = (TextView) findViewById(R.id.tv_withdraw_record);
        this.a = (FrameLayout) findViewById(R.id.fl_withdraw);
        this.b = (ScrollView) findViewById(R.id.scrollView);
        this.c = (LinearLayout) findViewById(R.id.ll_withdraw);
        this.d = (RelativeLayout) findViewById(R.id.header);
        this.o = (RelativeLayout) $(R.id.rl_withdraw_info);
        this.p = (TextView) $(R.id.tv_withdraw_info);
        this.q = (RelativeLayout) $(R.id.ll_notice);
        this.k = (TextView) $(R.id.tv_available_money);
        this.l = (TextView) $(R.id.tv_money_today);
        this.m = (TextView) $(R.id.tv_money_total);
        this.n = (ImageView) $(R.id.iv_question);
        this.r = (TextView) $(R.id.tv_notice);
        this.s = (TextView) $(R.id.tv_withdraw_diamond);
        a();
        this.h.setEnabled(false);
        this.s.setEnabled(false);
        this.h.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.n.setOnClickListener(this);
    }

    private void a() {
        CharSequence string = PreferenceUtils.instance().getString("alipay_account_" + AppUtils.getInstance().getUserInfoProvider().getUserId(), "");
        CharSequence string2 = PreferenceUtils.instance().getString("alipay_name_" + AppUtils.getInstance().getUserInfoProvider().getUserId(), "");
        if (string == null || string.length() <= 0 || string2 == null || string2.length() <= 0) {
            this.f.setText("");
            this.g.setText("");
            this.f.setEnabled(true);
            this.g.setEnabled(true);
            return;
        }
        this.f.setText(string);
        this.g.setText(string2);
        this.f.setEnabled(false);
        this.g.setEnabled(false);
        this.f.setTextColor(Color.parseColor("#ff929292"));
        this.g.setTextColor(Color.parseColor("#ff929292"));
    }

    protected void initData() {
        Object string = getString(R.string.pay_withdraw_tips);
        CharSequence spannableString = new SpannableString(string);
        String str = "热猫直播";
        int indexOf = string.indexOf("热猫直播");
        if (indexOf != -1) {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), indexOf, indexOf + 4, 33);
            spannableString.setSpan(new q(this), indexOf, indexOf + 4, 33);
            this.e.setMovementMethod(LinkMovementMethod.getInstance());
        }
        this.e.setText(spannableString);
        f();
    }

    private void b() {
        Dialog dialog = new Dialog(this, R.style.AppTheme_Dialog);
        dialog.setContentView(R.layout.pay_withdraw_dialog);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_fee);
        TextView textView2 = (TextView) dialog.findViewById(R.id.tv_alipay);
        TextView textView3 = (TextView) dialog.findViewById(R.id.tv_name);
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.cancel);
        LinearLayout linearLayout2 = (LinearLayout) dialog.findViewById(R.id.confirm);
        TextView textView4 = (TextView) dialog.findViewById(R.id.tv_acutal);
        ((TextView) dialog.findViewById(R.id.tv_money)).setText(Double.toString(this.B) + "元");
        textView.setText(Double.toString(this.t) + "元");
        textView4.setText(Double.toString(this.v) + "元");
        textView2.setText(this.w);
        textView3.setText(this.x);
        linearLayout.setOnClickListener(new u(this, dialog));
        linearLayout2.setOnClickListener(new v(this, dialog));
        dialog.show();
    }

    private void a(String str) {
        new Builder(this, R.style.AppcompatDialog).setTitle(R.string.pay_withdraw_success).setMessage(str).setPositiveButton(R.string.pay_withdraw_confirm, new w(this)).setCancelable(false).show();
    }

    private void c() {
        this.j = ProgressDialog.show(this, "", AppUtils.getInstance().getAppContext().getString(R.string.pay_get_payinfo), false, true);
        NetRequest.getInstance().post(UrlConstants.WITHDRAW, new x(this), "withdraw", true);
    }

    protected int getLayoutId() {
        return R.layout.pay_withdraw_activity;
    }

    private void d() {
        this.k.setText(Double.toString(this.y));
    }

    private void e() {
        this.t = Math.max(this.t, this.u);
        this.B = Math.min((double) this.C, this.y);
        this.v = a(this.B, this.t);
        this.w = this.f.getText().toString();
        this.x = this.g.getText().toString();
    }

    private double a(double d, double d2) {
        return new BigDecimal(Double.toString(d)).subtract(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    private void f() {
        NetRequest.getInstance().get(UrlConstants.WITHDRAW_INFO, new y(this));
    }

    public void onClick(View view) {
        Intent intent;
        if (view == this.n) {
            intent = new Intent();
            intent.putExtra("url", this.H + "?package=" + getPackageName());
            intent.setClass(this, WithdrawHelpActivity.class);
            startActivity(intent);
        } else if (view == this.h) {
            e();
            if (TextUtils.isEmpty(this.w) || TextUtils.isEmpty(this.x)) {
                ToastUtil.Long(R.string.pay_withdraw_not_empty);
            } else {
                b();
            }
        } else if (view == this.i || view == this.o) {
            intent = new Intent();
            intent.setClass(this, WithdrawRecordActivity.class);
            startActivity(intent);
        } else if (view == this.q) {
            intent = new Intent();
            intent.setClass(this, WithdrawNoticeActivity.class);
            intent.putExtra("url", this.F);
            startActivity(intent);
        } else if (view == this.s) {
            g();
        }
    }

    private void g() {
        Dialog zVar = new z(this, this, R.style.AppTheme_Dialog);
        zVar.setContentView(R.layout.pay_withdraw_diamond_dialog);
        TextView textView = (TextView) zVar.findViewById(R.id.tv_cancel);
        TextView textView2 = (TextView) zVar.findViewById(R.id.tv_confirm);
        TextView textView3 = (TextView) zVar.findViewById(R.id.tv_exchange_all);
        TextView textView4 = (TextView) zVar.findViewById(R.id.tv_tips);
        EditText editText = (EditText) zVar.findViewById(R.id.et_money);
        int min = (int) Math.min((double) this.C, this.y);
        CharSequence spannableString = new SpannableString(String.format(getString(R.string.pay_withdraw_diamond_tips_hint), new Object[]{Integer.valueOf(min)}));
        spannableString.setSpan(new AbsoluteSizeSpan(16, true), 0, spannableString.length(), 33);
        editText.setHint(spannableString);
        if (!TextUtils.isEmpty(this.K)) {
            textView4.setText(this.K);
        }
        textView.setOnClickListener(new aa(this, zVar));
        textView2.setOnClickListener(new ab(this, editText, zVar));
        textView3.setOnClickListener(new r(this, editText, min));
        zVar.show();
    }

    private void a(int i) {
        NetRequest.getInstance().post(UrlConstants.WITHDRAW_DIAMOND, new s(this, i));
    }

    private void h() {
        SimpleDialog.Builder tVar = new t(this, R.style.SimpleDialog);
        tVar.message(getString(R.string.pay_withdraw_diamond_tips_more)).positiveAction(getString(R.string.pay_withdraw_to_buy)).negativeAction(getString(R.string.pay_withdraw_giveup));
        AppUtils.showDialogFragment(this, tVar);
    }
}
