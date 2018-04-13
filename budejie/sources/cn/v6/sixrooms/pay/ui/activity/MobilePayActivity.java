package cn.v6.sixrooms.pay.ui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.pay.bean.OrderBean;
import cn.v6.sixrooms.pay.bean.PaySelectBean;
import cn.v6.sixrooms.pay.contants.CommonPay;
import cn.v6.sixrooms.pay.engine.MakeOrderEngine;
import cn.v6.sixrooms.pay.engine.YeepayCardStatusEngine;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

@SuppressLint({"HandlerLeak"})
public class MobilePayActivity extends SlidingActivity implements OnClickListener {
    protected static final String TAG = "MobilePayActivity";
    private Resources a;
    private PaySelectBean b;
    private int c;
    private TextView d;
    private TextView e;
    private InputMethodManager f;
    private EditText g;
    private EditText h;
    private MakeOrderEngine i;
    private String j;
    private boolean k = true;
    private RelativeLayout l;
    private int m;
    private OrderBean n;
    private Handler o = new ap(this);
    private Dialog p;
    private Dialog q;
    private Dialog r;
    private YeepayCardStatusEngine s;

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_mobilepay);
        this.m = getWindowManager().getDefaultDisplay().getWidth();
        this.f = (InputMethodManager) getSystemService("input_method");
        this.a = getResources();
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), getResources().getString(R.string.confirm), null, "", new al(this), new an(this));
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setShadowDrawable(R.drawable.shadowleft);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new ao(this));
        this.d = (TextView) findViewById(R.id.tv_supply);
        this.e = (TextView) findViewById(R.id.tv_recharge_count);
        this.g = (EditText) findViewById(R.id.et_card_account);
        this.h = (EditText) findViewById(R.id.et_card_password);
        this.l = (RelativeLayout) findViewById(R.id.mobliePayView);
        Bundle extras = getIntent().getExtras();
        this.b = (PaySelectBean) extras.getSerializable("curSelectedMoney");
        this.c = extras.getInt("id");
        if (this.c == 0) {
            setTitleText(this.a.getString(R.string.str_pay_mobile_m_title));
            this.d.setText(this.a.getString(R.string.str_pay_mobile_m_card));
            this.j = CommonPay.GATETYPE_YEEPAYSZX;
        } else if (this.c == 1) {
            setTitleText(this.a.getString(R.string.str_pay_mobile_u_title));
            this.d.setText(this.a.getString(R.string.str_pay_mobile_u_card));
            this.j = CommonPay.GATETYPE_YEEPAYUNICOM;
        }
        String str = this.a.getString(R.string.str_recharge_money) + this.b.getFormatMoney();
        CharSequence spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getColor(R.color.red_pay_text)), str.indexOf(this.b.getFormatMoney()), str.length(), 33);
        this.e.setText(spannableStringBuilder);
        this.o.sendEmptyMessageDelayed(0, 500);
        Animation translateAnimation = new TranslateAnimation((float) this.m, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.l.startAnimation(translateAnimation);
    }

    public void onClick(View view) {
        view.getId();
    }

    public void makeOrder() {
        if (this.i == null) {
            this.i = new MakeOrderEngine(new aq(this));
        }
        Object obj = this.g.getText().toString();
        Object obj2 = this.h.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            showToast(this.a.getString(R.string.str_card_account_empty));
        } else if (TextUtils.isEmpty(obj2)) {
            showToast(this.a.getString(R.string.str_card_password_empty));
        } else {
            this.i.makeOrder(this.j, GlobleValue.getUserBean().getId(), SaveUserInfoUtils.getEncpass(this), this.b.getMoney(), this.b.getCoin6(), "", obj, obj2);
            if (this.p == null) {
                this.p = DialogUtils.createProgressDialog(this, getString(R.string.str_submiting));
            }
            if (!this.p.isShowing()) {
                this.p.show();
            }
        }
    }

    protected void showOtherErrorDialog(String str) {
        new DialogUtils(this).createConfirmDialog(CommonInts.USER_MANAGER_REQUEST_CODE, str, new at(this)).show();
    }

    protected void showSucessDialog() {
        new DialogUtils(this).createConfirmDialogs(23, "提示", "充值成功", "确定", new au(this)).show();
    }

    protected void showErrorDialog() {
        new DialogUtils(this).createConfirmDialogs(28, "提示", "卡号或者密码错误", "确定", new am(this)).show();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        showMenu();
        return true;
    }

    public void dissSubmitDialog() {
        if (this.p != null && this.p.isShowing()) {
            this.p.dismiss();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.o != null) {
            this.o.removeCallbacksAndMessages(null);
        }
    }

    static /* synthetic */ void a(MobilePayActivity mobilePayActivity, String str) {
        mobilePayActivity.k = true;
        if (mobilePayActivity.s == null) {
            mobilePayActivity.s = new YeepayCardStatusEngine(new ar(mobilePayActivity));
        }
        new as(mobilePayActivity, str).execute(new Void[0]);
    }

    static /* synthetic */ void l(MobilePayActivity mobilePayActivity) {
        if (!mobilePayActivity.isFinishing()) {
            new DialogUtils(mobilePayActivity).createDiaglog(mobilePayActivity.a.getString(R.string.str_pay_delayed)).show();
        }
    }
}
