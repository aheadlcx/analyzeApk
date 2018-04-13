package cn.v6.sixrooms.pay.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.pay.engine.PayInfoEngine;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;

public class RechargeActivity extends BaseFragmentActivity implements OnClickListener {
    public static final int LOGIN_REQUESTCODE = 10001;
    private static final String a = RechargeActivity.class.getSimpleName();
    private Resources b;
    private RelativeLayout c;
    private int d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout g;
    private LinearLayout h;
    private TextView i;
    private UserInfoEngine j;
    private String k;

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_recharge);
        this.k = getIntent().getStringExtra(HistoryOpenHelper.COLUMN_UID);
        this.d = getWindowManager().getDefaultDisplay().getWidth();
        this.b = getResources();
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, null, getResources().getString(R.string.charge_btn_str), new bc(this), null);
        this.c = (RelativeLayout) findViewById(R.id.mRechargeView);
        this.e = (LinearLayout) findViewById(R.id.ll_recharge_zfb);
        this.f = (LinearLayout) findViewById(R.id.ll_recharge_ylsj);
        this.g = (LinearLayout) findViewById(R.id.ll_recharge_czk);
        this.h = (LinearLayout) findViewById(R.id.ll_recharge_wx);
        this.i = (TextView) findViewById(R.id.tv_warn_info);
        if (TextUtils.isEmpty(SaveUserInfoUtils.getEncpass(this))) {
            showLoginDialog();
        } else if (GlobleValue.getUserBean() != null) {
            a();
        } else {
            LogUtils.e(a, "getUserInfo");
            LogUtils.e(a, "getUserInfo");
            if (this.j == null) {
                this.j = new UserInfoEngine(new bd(this));
            }
            this.j.getUserInfo(SaveUserInfoUtils.getEncpass(this), "");
        }
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        Animation translateAnimation = new TranslateAnimation((float) this.d, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.c.startAnimation(translateAnimation);
    }

    private void a() {
        PayInfoEngine payInfoEngine = new PayInfoEngine(new be(this));
        String encpass = SaveUserInfoUtils.getEncpass(this);
        String id = GlobleValue.getUserBean().getId();
        if (!(TextUtils.isEmpty(this.k) || this.k.equals(id))) {
            Toast.makeText(this, "充值账号与登录游戏的账号不统一", 1).show();
            finish();
        }
        payInfoEngine.getPayInfo(encpass, id);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ll_recharge_zfb) {
            startActivity(new Intent(this, AlipayActivity.class));
        } else if (view.getId() == R.id.ll_recharge_ylsj) {
            startActivity(new Intent(this, BanklineActivity.class));
        } else if (view.getId() == R.id.ll_recharge_czk) {
            startActivity(new Intent(this, PayCardActivity.class));
        } else if (view.getId() != R.id.ll_recharge_wx) {
        } else {
            if (SixRoomsUtils.isWeixinAvilible(this)) {
                startActivity(new Intent(this, WeixinPayActivity.class));
            } else {
                ToastUtils.showToast("请安装微信");
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10001) {
            return;
        }
        if (GlobleValue.getUserBean() == null || TextUtils.isEmpty(SaveUserInfoUtils.getEncpass(this))) {
            finish();
        } else {
            a();
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        finish();
        return true;
    }

    protected void onNewIntent(Intent intent) {
        intent.getStringExtra("");
        super.onNewIntent(intent);
    }

    public void finish() {
        LogUtils.e("rechargeactivity", "finish");
        super.finish();
    }
}
