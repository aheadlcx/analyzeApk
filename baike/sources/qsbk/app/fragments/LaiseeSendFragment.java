package qsbk.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.math.BigDecimal;
import java.util.HashMap;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.LaiseeChargeActivity;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.model.Laisee;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.widget.BlackProgressDialog;
import qsbk.app.widget.VerticalImageSpan;

public class LaiseeSendFragment extends BaseFragment {
    private boolean A;
    private String B;
    private EncryptHttpTask C;
    TextView a;
    TextView b;
    EditText c;
    TextView d;
    TextView e;
    View f;
    EditText g;
    TextView h;
    TextView i;
    TextView j;
    EditText k;
    TextView l;
    Button m;
    BlackProgressDialog n;
    double o;
    double p = 0.0d;
    int q;
    int r;
    int s;
    String t;
    BigDecimal u;
    String v;
    TextWatcher w = new dr(this);
    TextWatcher x = new ds(this);
    TextWatcher y = new dt(this);
    private Laisee z;

    public static LaiseeSendFragment newInstance(String str, int i) {
        LaiseeSendFragment laiseeSendFragment = new LaiseeSendFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_TYPE", i);
        bundle.putString("kEY_ID", str);
        laiseeSendFragment.setArguments(bundle);
        return laiseeSendFragment;
    }

    public static LaiseeSendFragment newInstance(String str, int i, int i2) {
        LaiseeSendFragment laiseeSendFragment = new LaiseeSendFragment();
        Bundle bundle = new Bundle();
        bundle.putString("kEY_ID", str);
        bundle.putInt("KEY_TYPE", i);
        bundle.putInt("member_num", i2);
        laiseeSendFragment.setArguments(bundle);
        return laiseeSendFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_laisee_send, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        e();
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.c != null) {
            this.c.removeTextChangedListener(this.x);
            this.c.removeTextChangedListener(this.y);
        }
        if (this.g != null) {
            this.g.removeTextChangedListener(this.x);
            this.g.removeTextChangedListener(this.w);
        }
        if (this.C != null && !this.C.isCancelled()) {
            this.C.cancel(true);
            this.C = null;
        }
    }

    private void e() {
        Bundle arguments = getArguments();
        this.s = arguments.getInt("member_num", 0);
        this.t = arguments.getString("kEY_ID");
        this.r = arguments.getInt("KEY_TYPE");
        f();
        j();
    }

    private void a(View view) {
        this.n = new BlackProgressDialog(getActivity());
        this.a = (TextView) view.findViewById(R.id.tips);
        this.b = (TextView) view.findViewById(R.id.amount_desc);
        this.c = (EditText) view.findViewById(R.id.amount);
        this.d = (TextView) view.findViewById(R.id.yuan);
        this.e = (TextView) view.findViewById(R.id.type);
        this.e.setHighlightColor(0);
        this.f = view.findViewById(R.id.count_container);
        this.g = (EditText) view.findViewById(R.id.count);
        this.h = (TextView) view.findViewById(R.id.count_desc);
        this.i = (TextView) view.findViewById(R.id.ge);
        this.j = (TextView) view.findViewById(R.id.group_members_count);
        this.k = (EditText) view.findViewById(R.id.message);
        this.l = (TextView) view.findViewById(R.id.amount_copy);
        this.m = (Button) view.findViewById(R.id.submit);
        this.m.setOnClickListener(new du(this));
        this.c.addTextChangedListener(this.y);
        this.c.addTextChangedListener(this.x);
        this.g.addTextChangedListener(this.w);
        this.g.addTextChangedListener(this.x);
    }

    private void f() {
        this.e.setVisibility(0);
        this.f.setVisibility(0);
        this.j.setVisibility(0);
        this.j.setText(String.format("本群%d人", new Object[]{Integer.valueOf(this.s)}));
        this.b.setText("单个金额");
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        int length;
        switch (this.r) {
            case 0:
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                this.j.setVisibility(8);
                return;
            case 1:
                spannableStringBuilder.append("群里每人收到固定金额，");
                length = spannableStringBuilder.length();
                spannableStringBuilder.append("改为拼手气红包");
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue)), length, spannableStringBuilder.length(), 33);
                spannableStringBuilder.setSpan(new dw(this), length, spannableStringBuilder.length(), 33);
                this.e.setText(spannableStringBuilder);
                this.e.setMovementMethod(LinkMovementMethod.getInstance());
                return;
            case 2:
                spannableStringBuilder.append("每人抽到的金额随机，");
                length = spannableStringBuilder.length();
                spannableStringBuilder.append("改为普通红包");
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue)), length, spannableStringBuilder.length(), 33);
                spannableStringBuilder.setSpan(new dx(this), length, spannableStringBuilder.length(), 33);
                this.e.setText(spannableStringBuilder);
                this.e.setMovementMethod(LinkMovementMethod.getInstance());
                spannableStringBuilder = new SpannableStringBuilder();
                length = spannableStringBuilder.length();
                spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                int length2 = spannableStringBuilder.length();
                spannableStringBuilder.append("总金额");
                VerticalImageSpan verticalImageSpan = new VerticalImageSpan(getResources().getDrawable(R.drawable.ic_laisee_ping));
                verticalImageSpan.setMargin(0, UIHelper.dip2px(getActivity(), 2.0f));
                spannableStringBuilder.setSpan(verticalImageSpan, length, length2, 33);
                this.b.setText(spannableStringBuilder);
                return;
            default:
                return;
        }
    }

    private void g() {
        f();
        this.y.afterTextChanged(this.c.getText());
        this.w.afterTextChanged(this.g.getText());
        this.x.afterTextChanged(this.c.getText());
        this.x.afterTextChanged(this.g.getText());
    }

    boolean a() {
        double parseDouble;
        try {
            parseDouble = Double.parseDouble(this.c.getText().toString());
        } catch (Exception e) {
            parseDouble = 0.0d;
        }
        switch (this.r) {
            case 0:
            case 2:
                this.p = parseDouble;
                this.l.setText("￥" + Util.formatMoney(this.p));
                break;
            case 1:
                this.l.setText("￥0.00");
                break;
        }
        switch (this.r) {
            case 0:
            case 1:
                this.o = parseDouble;
                this.o = parseDouble;
                break;
        }
        if (this.o > 200.0d && this.r != 2) {
            this.v = "单个红包金额不超过200元";
            return false;
        } else if (1 == this.r || this.p <= 20000.0d) {
            return true;
        } else {
            this.v = "单次支付总额不超过20000元";
            return false;
        }
    }

    boolean b() {
        try {
            this.q = Integer.parseInt(this.g.getText().toString());
        } catch (Exception e) {
            this.q = 0;
        }
        if (this.r == 0 && this.q == 0) {
            this.q = 1;
        }
        if (this.q > 100) {
            this.v = "一次最多可发100个红包";
            return false;
        } else if (this.q >= 1) {
            return true;
        } else {
            this.v = "至少需要设置1个红包";
            return false;
        }
    }

    boolean c() {
        if (!b() || !a()) {
            return false;
        }
        boolean z;
        switch (this.r) {
            case 1:
                this.p = this.o * ((double) this.q);
                this.l.setText("￥" + Util.formatMoney(this.p));
                break;
        }
        switch (this.r) {
            case 2:
                double doubleValue = new BigDecimal(this.p * 100.0d).divide(new BigDecimal(this.q), 2, 4).doubleValue();
                this.o = doubleValue >= 1.0d ? doubleValue / 100.0d : 0.0d;
                break;
        }
        if (this.o > 200.0d) {
            this.v = "单个红包金额不超过200元";
            z = false;
        } else if (this.p > 20000.0d) {
            this.v = "单次支付总额不超过20000元";
            z = false;
        } else if (this.o < 0.01d) {
            this.v = "单个红包金额不能低于0.01元";
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    void d() {
        this.n.show();
        this.C = new EncryptHttpTask(null, Constants.WALLET_BALANCE, new dy(this));
        this.C.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private HashMap<String, Object> h() {
        HashMap<String, Object> hashMap = new HashMap();
        if (this.r == 0) {
            hashMap.put("toid", this.t);
            hashMap.put(PayPWDUniversalActivity.MONEY, this.o + "");
            hashMap.put("content", this.B);
        } else {
            hashMap.put("tribe_id", this.t);
            hashMap.put(PayPWDUniversalActivity.MONEY, this.r == 1 ? this.o + "" : this.p + "");
            hashMap.put("count", Integer.valueOf(this.q));
            hashMap.put("distribute", this.r == 1 ? "average" : "random");
            hashMap.put("content", this.B);
        }
        return hashMap;
    }

    private void i() {
        if (this.n != null && this.n.isShowing()) {
            this.n.dismiss();
        }
    }

    private void j() {
        LaiseeChargeActivity.checkPaypass();
    }
}
